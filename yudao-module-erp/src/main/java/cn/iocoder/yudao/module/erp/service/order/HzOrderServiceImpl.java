package cn.iocoder.yudao.module.erp.service.order;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.enums.OrderStatus;
import cn.iocoder.yudao.framework.common.enums.OrderStatusEnum;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.erp.dal.dataobject.matchingExampleRaw.HzMatchingExampleRawDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.order.HzOrderProgressDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.product.ErpProductCategoryDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.product.ErpProductDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.product.ErpProductUnitDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.project.HzProjectDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.reviewrRecord.HzReviewRecordDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.stock.ErpStockDO;
import cn.iocoder.yudao.module.erp.dal.mysql.matchingExampleRaw.HzMatchingExampleRawMapper;
import cn.iocoder.yudao.module.erp.dal.mysql.order.HzOrderProgressMapper;
import cn.iocoder.yudao.module.erp.dal.mysql.product.ErpProductCategoryMapper;
import cn.iocoder.yudao.module.erp.dal.mysql.product.ErpProductMapper;
import cn.iocoder.yudao.module.erp.dal.mysql.product.ErpProductUnitMapper;
import cn.iocoder.yudao.module.erp.dal.mysql.project.HzProjectMapper;
import cn.iocoder.yudao.module.erp.dal.mysql.reviewrRecord.HzReviewRecordMapper;
import cn.iocoder.yudao.module.erp.dal.mysql.stock.ErpStockMapper;
import cn.iocoder.yudao.module.erp.dal.redis.no.ErpNoRedisDAO;
import cn.iocoder.yudao.module.erp.enums.stock.ErpStockRecordBizTypeEnum;
import cn.iocoder.yudao.module.erp.service.stock.ErpStockRecordServiceImpl;
import cn.iocoder.yudao.module.erp.service.stock.ErpStockService;
import cn.iocoder.yudao.module.erp.service.stock.bo.ErpStockRecordCreateReqBO;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.dal.dataobject.logger.LoginLogDO;
import cn.iocoder.yudao.module.system.dal.dataobject.permission.UserRoleDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.dal.mysql.dept.DeptMapper;
import cn.iocoder.yudao.module.system.dal.mysql.logger.LoginLogMapper;
import cn.iocoder.yudao.module.system.dal.mysql.permission.UserRoleMapper;
import cn.iocoder.yudao.module.system.dal.mysql.user.AdminUserMapper;
import cn.iocoder.yudao.module.system.service.permission.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.gson.JsonObject;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.erp.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.order.HzOrderDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.erp.dal.mysql.order.HzOrderMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.erp.enums.ErrorCodeConstants.*;

/**
 * 红正建材订单表
 Service 实现类
 *
 * @author liangchuang
 */
@Service
@Validated
public class HzOrderServiceImpl implements HzOrderService {
    @Resource
    private AdminUserMapper userMapper;
    @Resource
    private LoginLogMapper loginLogMapper;
    @Resource
    private HzReviewRecordMapper hzReviewRecordMapper;

    @Resource
    private HzOrderMapper hzOrderMapper;
    @Resource
    private HzProjectMapper hzProjectMapper;
    @Resource
    private ErpProductMapper erpProductMapper;

    @Resource
    private ErpNoRedisDAO noRedisDAO;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private ErpStockService stockService;

    @Resource
    private HzMatchingExampleRawMapper hzMatchingExampleRawMapper;

    @Resource
    private ErpStockRecordServiceImpl erpStockRecordServiceImpl;
    @Resource
    private ErpProductMapper productMapper;
    @Resource
    private ErpProductUnitMapper erpProductUnitMapper;
    @Resource
    private ErpProductCategoryMapper erpProductCategoryMapper;
    @Resource
    private DeptMapper deptMapper;

    @Resource
    private HzOrderProgressMapper hzOrderProgressMapper;

    @Resource
    private ErpStockMapper stockMapper;

    @Override
    public Long createHzOrder(HzOrderSaveReqVO createReqVO) {
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        // 插入
        HzOrderDO hzOrder = BeanUtils.toBean(createReqVO, HzOrderDO.class);
        if (loginUser != null) {
            hzOrder.setCreator(loginUser.getInfo().get("nickname"));
        }
        // 1.4 生成订单号，并校验唯一性
        String no = noRedisDAO.generate(ErpNoRedisDAO.PRODUCTION_ORDER_NO_PREFIX);
        if (hzOrderMapper.selectByNo(no) != null) {
            throw exception(PURCHASE_ORDER_NO_EXISTS);
        }
        hzOrder.setOrderNumber(no);
        //新建订单默认为  1 待审核
        hzOrder.setStatus(3);
        //订单默认不挂起
        hzOrder.setPause(0);
        hzOrderMapper.insert(hzOrder);
        if (hzOrder.getId() != null){
            insertProgress(hzOrder.getId(),"订单创建。");
        }
        // 返回
        return hzOrder.getId();
    }

    @Override
    public void updateHzOrder(HzOrderSaveReqVO updateReqVO) {
        // 校验存在
        validateHzOrderExists(updateReqVO.getId());
        // 更新
        HzOrderDO updateObj = BeanUtils.toBean(updateReqVO, HzOrderDO.class);
        hzOrderMapper.updateById(updateObj);
    }


    @Override
    public void updatePause(HzOrderSaveReqVO updateReqVO) {
        // 校验存在
        validateHzOrderExists(updateReqVO.getId());
        // 更新
        HzOrderDO updateObj = BeanUtils.toBean(updateReqVO, HzOrderDO.class);
        hzOrderMapper.updateById(updateObj);
        if (updateReqVO.getId() != null && updateReqVO.getPause() == 1){
            insertProgress(updateReqVO.getId(),"订单挂起（挂起原因："+updateReqVO.getRefuseReasons()+")");
        }
    }

    @Override
    public void deleteHzOrder(Long id) {
        // 校验存在
        validateHzOrderExists(id);
        // 删除
        hzOrderMapper.deleteById(id);
    }

    @Override
        public void deleteHzOrderListByIds(List<Long> ids) {
        // 删除
        hzOrderMapper.deleteByIds(ids);
        }


    private void validateHzOrderExists(Long id) {
        if (hzOrderMapper.selectById(id) == null) {
            throw exception(HZ_ORDER_NOT_EXISTS);
        }
    }

    @Override
    public HzOrderDO getHzOrder(Long id) {
        HzOrderDO hzOrderDO = hzOrderMapper.selectById(id);
        hzOrderDO.setProductName(erpProductMapper.selectById(hzOrderDO.getProductId()).getName());
        hzOrderDO.setProjectName(hzProjectMapper.selectById(hzOrderDO.getProjectId()).getProjectName());
        return hzOrderDO;
    }

    @Override
    public PageResult<HzOrderDO> getHzOrderPage(HzOrderPageReqVO pageReqVO) {
        List<Integer> list = new ArrayList<>();


        if(pageReqVO.getStatus() == null){
            LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();

            if (loginUser != null){
                List<UserRoleDO> userRoleDOS = userRoleMapper.selectListByUserId(loginUser.getId());
                Long roleId = userRoleDOS.get(0).getRoleId();
                     if (Objects.equals(roleId, OrderStatusEnum.WU_ZI.getRoleId())){
                    list = OrderStatusEnum.WU_ZI.getStatus();
                }else if (Objects.equals(roleId, OrderStatusEnum.XIAO_SHOU.getRoleId())){
                    list = OrderStatusEnum.XIAO_SHOU.getStatus();
                }else if (Objects.equals(roleId, OrderStatusEnum.SHENG_CHAN.getRoleId())){
                    list = OrderStatusEnum.SHENG_CHAN.getStatus();
                }else if (Objects.equals(roleId, OrderStatusEnum.KU_FANG.getRoleId())){
                    list = OrderStatusEnum.KU_FANG.getStatus();
                }else if (Objects.equals(roleId, OrderStatusEnum.BANG_FANG.getRoleId())){
                    list = OrderStatusEnum.BANG_FANG.getStatus();
                }else if (Objects.equals(roleId, OrderStatusEnum.YAN_FA.getRoleId())){
                    list = OrderStatusEnum.YAN_FA.getStatus();
               }else if (Objects.equals(roleId, OrderStatusEnum.SHI_CHANG_BU_ZHANG.getRoleId())){
                     list = OrderStatusEnum.SHI_CHANG_BU_ZHANG.getStatus();
                     }else {
                    list = OrderStatusEnum.SUPER_ADMIN.getStatus();
                }
            }
        }


        PageResult<HzOrderDO> hzOrderDOPageResult = hzOrderMapper.selectPage(pageReqVO,list);

        hzOrderDOPageResult.getList().forEach(e->{
                    HzProjectDO hzProjectDO = hzProjectMapper.selectById(e.getProjectId());
                    if (hzProjectDO != null){
                        e.setProjectName(hzProjectDO.getProjectName());
                        ErpProductDO erpProductDO = erpProductMapper.selectById(e.getProductId());
                        e.setProductName(erpProductDO.getName());
                    }
                }
        );
        return hzOrderDOPageResult;
    }

    @Override
    public void updateStatus(HzOrderSaveReqVO updateReqVO) {
        // 校验存在
        validateHzOrderExists(updateReqVO.getId());
        // 更新
        HzOrderDO updateObj = BeanUtils.toBean(updateReqVO, HzOrderDO.class);
        //1待审核；2审核驳回；3待生产；5生产中；6待成品出库；7待过磅；8已过磅  ；9待配比原料；  10待原料出库 11 已取消生产 12 已作废 13 已转移 14 待交付

        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();

        //审核拒绝保存记录
//        if (updateReqVO.getStatus() == 2){
//            hzReviewRecordMapper.deleteByOrderId(updateReqVO.getId());
//            HzReviewRecordDO hzReviewRecordDO = new HzReviewRecordDO();
//            hzReviewRecordDO.setName(updateObj.getOrderName());
//            hzReviewRecordDO.setResault(0);
//            hzReviewRecordDO.setOrderId(updateReqVO.getId());
//            hzReviewRecordDO.setRefuseReasons(updateReqVO.getRefuseReasons());
//            // 插入
//            if (loginUser != null) {
//                hzReviewRecordDO.setCreator(loginUser.getInfo().get("nickname"));
//                hzReviewRecordDO.setUpdater(loginUser.getInfo().get("nickname"));
//            }
//            hzReviewRecordMapper.insert(hzReviewRecordDO);
//            if (updateReqVO.getId() != null){
//                insertProgress(updateReqVO.getId(),"订单审核驳回。");
//            }
//
//        }
        //点击进入生产环节，status = 10。
//        if (updateReqVO.getStatus() == 10){
//            if (updateReqVO.getId() != null){
//                insertProgress(updateReqVO.getId(),"进入生产，待原料出库。");
//            }
//        }
        //点击进入生产中，status = 5。
        if (updateReqVO.getStatus() == 5){
            if (updateReqVO.getId() != null){
                insertProgress(updateReqVO.getId(),"生产中。");
            }
        }
        //点击过磅完成，status =14。
        if (updateReqVO.getStatus() == 14){
            if (updateReqVO.getId() != null){
                insertProgress(updateReqVO.getId(),"过磅完成，订单待交付。");
            }
        }

        //点击过磅完成，status =11。
        if (updateReqVO.getStatus() == 11){
            if (updateReqVO.getId() != null){
                insertProgress(updateReqVO.getId(),"取消生产。");
            }
        }

        if (loginUser != null) {
        updateObj.setUpdater(loginUser.getInfo().get("nickname"));}
        hzOrderMapper.updateById(updateObj);
    }

    @Override
    public void approved(HzOrderSaveReqVO updateReqVO) {
        // 校验存在
        validateHzOrderExists(updateReqVO.getId());
        // 更新
        HzOrderDO order = BeanUtils.toBean(updateReqVO, HzOrderDO.class);
        //订单状态  1待审核；2审核驳回；3待生产；5生产中；6待成品出库；7待过磅；8已过磅  ；9待配比原料；  10待原料出库
        //获取产品库存
        Long productId = order.getProductId();
       // BigDecimal stockCount = stockService.getStockCount(productId);
        ErpStockDO erpStockDO = stockMapper.selectByProductIdAndWarehouseId(productId, 7L);
        BigDecimal stockCount = new BigDecimal(0);
        if (erpStockDO != null){
             stockCount = erpStockDO.getCount().subtract(erpStockDO.getFreezeCount());
        }

        //获取订单数量
        BigDecimal productAmount = order.getProductAmount();
        //判断库存是否充足
        if (stockCount.compareTo(productAmount) < 0){
            order.setStatus(9);
            hzOrderMapper.updateById(order);
            if (updateReqVO.getId() != null){
                insertProgress(updateReqVO.getId(),"订单审核通过，待原料配比。");
            }
        }else {
            //冻结属于订单的库存
            stockMapper.updateById(erpStockDO.setFreezeCount(erpStockDO.getFreezeCount().add(productAmount)));

            order.setStatus(OrderStatus.DAI_CHENG_PIN_CHU_KU.getStatus());
            hzOrderMapper.updateById(order);
            if (updateReqVO.getId() != null){
                insertProgress(updateReqVO.getId(),"库存充足，待成品出库。");
            }
        }
    }

    @Override
    @Transactional
    public Boolean rawOut(HzOrderDO order) {
        Long id = order.getId();

        List<HzMatchingExampleRawDO> rawList = hzMatchingExampleRawMapper.getByOrderId(id);

        //校验所有原料库存是否充足
        for (HzMatchingExampleRawDO hzMatchingExampleRawDO : rawList) {
            BigDecimal stockCount = stockService.getStockCount(hzMatchingExampleRawDO.getProductId());
            if (stockCount.compareTo(hzMatchingExampleRawDO.getNumber()) < 0){
             return false;
            }

        }

        //创建所有原料出库
        rawList.forEach(e->{
            ErpStockRecordCreateReqBO erpStockRecordCreateReqBO = new ErpStockRecordCreateReqBO();
            erpStockRecordCreateReqBO.setProductId(e.getProductId());
            erpStockRecordCreateReqBO.setWarehouseId(7L);
            erpStockRecordCreateReqBO.setCount(e.getNumber().negate());
            erpStockRecordCreateReqBO.setBizType(ErpStockRecordBizTypeEnum.RAW_OUT.getType());
            erpStockRecordCreateReqBO.setBizId(id);
            erpStockRecordCreateReqBO.setBizItemId(id);
            erpStockRecordCreateReqBO.setBizNo(order.getOrderNumber());

            erpStockRecordServiceImpl.createStockRecord(erpStockRecordCreateReqBO);
        });

        order.setStatus(OrderStatus.DAI_SHENG_CHAN.getStatus());
        hzOrderMapper.updateById(order);
        if (order.getId() != null){
            insertProgress(order.getId(),"原料出库完成，进入待生产。");
        }
        return true;
    }

    @Override
    public Boolean productFinished(HzOrderDO order) {
        Long id = order.getId();
        Long productId = order.getProductId();
        BigDecimal productAmount = order.getProductAmount();

        //创建成品入库
            ErpStockRecordCreateReqBO erpStockRecordCreateReqBO = new ErpStockRecordCreateReqBO();
            erpStockRecordCreateReqBO.setProductId(productId);
            erpStockRecordCreateReqBO.setWarehouseId(7L);
            erpStockRecordCreateReqBO.setCount(productAmount);
            erpStockRecordCreateReqBO.setBizType(ErpStockRecordBizTypeEnum.ORDER_PRODUCT_IN.getType());
            erpStockRecordCreateReqBO.setBizId(id);
            erpStockRecordCreateReqBO.setBizItemId(id);
            erpStockRecordCreateReqBO.setBizNo(order.getOrderNumber());
            erpStockRecordServiceImpl.createStockRecord(erpStockRecordCreateReqBO);

        //冻结属于订单的库存
        ErpStockDO erpStockDO = stockMapper.selectByProductIdAndWarehouseId(productId, 7L);
        stockMapper.updateById(erpStockDO.setFreezeCount(erpStockDO.getFreezeCount().add(productAmount)));

        order.setStatus(OrderStatus.DAI_GUO_BANG.getStatus());
        hzOrderMapper.updateById(order);
        if (id != null){
            insertProgress(id,"生产完成，待成品出库。");
        }
        return true;




    }

    @Override
    public Boolean productOut(HzOrderDO order) {
        Long id = order.getId();
        Long productId = order.getProductId();
        BigDecimal productAmount = order.getProductAmount();

        //创建成品出库
        ErpStockRecordCreateReqBO erpStockRecordCreateReqBO = new ErpStockRecordCreateReqBO();
        erpStockRecordCreateReqBO.setProductId(productId);
        erpStockRecordCreateReqBO.setWarehouseId(7L);
        erpStockRecordCreateReqBO.setCount(productAmount.negate());
        erpStockRecordCreateReqBO.setBizType(ErpStockRecordBizTypeEnum.ORDER_PRODUCT_OUT.getType());
        erpStockRecordCreateReqBO.setBizId(id);
        erpStockRecordCreateReqBO.setBizItemId(id);
        erpStockRecordCreateReqBO.setBizNo(order.getOrderNumber());
        erpStockRecordServiceImpl.createStockRecord(erpStockRecordCreateReqBO);


        //清除冻结库存
        ErpStockDO erpStockDO = stockMapper.selectByProductIdAndWarehouseId(productId, 7L);
        stockMapper.updateById(erpStockDO.setFreezeCount(erpStockDO.getFreezeCount().subtract(productAmount)));

        order.setStatus(OrderStatus.YI_GUO_BANG.getStatus());
        hzOrderMapper.updateById(order);
        if (id != null){
            insertProgress(id,"成品出库完成，交付完成，订单结束。");
        }
        return true;
    }

    @Override
    public HashMap<String, Object> getStockByOrderId(HzOrderDO order) {

        Long productId = order.getProductId();
        BigDecimal productAmount = order.getProductAmount();
        ErpStockDO stock = stockService.getStock(productId, 7L);
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("stockCount",stock.getCount());
        stringObjectHashMap.put("orderCount",productAmount);

        return stringObjectHashMap;

    }

    @Override
    public HashMap<String, Object> getOrderCount() {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();

        //获取所有订单
        List<HzOrderDO> hzOrderDOS = hzOrderMapper.selectList();
        //获取个状态订单数量
        Map<Integer, Long> statusCountMap = hzOrderDOS.stream()
                .filter(order -> order.getStatus() >= 1 && order.getStatus() <= 10)
                .collect(Collectors.groupingBy(
                        HzOrderDO::getStatus,
                        Collectors.counting()
                ));
        statusCountMap.put(0, (long) hzOrderDOS.size());
        stringObjectHashMap.put("statusCountMap",statusCountMap);

        //获取各类型订单数量
        hzOrderDOS.forEach(order -> {
            ErpProductDO erpProductDO = productMapper.selectById(order.getProductId());
            ErpProductCategoryDO erpProductCategoryDO = erpProductCategoryMapper.selectById(erpProductDO.getCategoryId());
            order.setCategoryName(erpProductCategoryDO.getName());
        });

        Map<String, Long> typeCountMap = hzOrderDOS.stream()
                .collect(Collectors.groupingBy(
                        HzOrderDO::getCategoryName,
                        Collectors.counting()
                ));
        stringObjectHashMap.put("typeCountMap",typeCountMap);
       //获取可库存商品库存量

        List<ErpProductDO> erpProductDOS = erpProductMapper.selectList(new LambdaQueryWrapperX<ErpProductDO>().eq(ErpProductDO::getIsRepertory,1).eq(ErpProductDO::getIsCommodity,1).eq(ErpProductDO::getDeleted,0));

        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        erpProductDOS.forEach(product -> {
            BigDecimal stockCount = stockService.getStockCount(product.getId());
            ErpProductUnitDO erpProductUnitDO = erpProductUnitMapper.selectById(product.getUnitId());
            HashMap<Object, Object> objectObjectHashMap1 = new HashMap<>();
            objectObjectHashMap1.put(stockCount, erpProductUnitDO.getName());
            objectObjectHashMap.put(product.getName()+"-"+product.getStandard(),objectObjectHashMap1);
        });
        stringObjectHashMap.put("stockCountMap",objectObjectHashMap);
        //获取各部门人员登录频次
        HashMap<String, Object> stringObjectHashMap1 = new HashMap<>();

        List<DeptDO> deptDOS = deptMapper.selectList(new LambdaQueryWrapperX<DeptDO>().eq(DeptDO::getDeleted, 0).eq(DeptDO::getParentId, 100));
        deptDOS.forEach(dept -> {
            List<AdminUserDO> adminUserDOS = userMapper.selectList(new LambdaQueryWrapperX<AdminUserDO>().eq(AdminUserDO::getDeptId, dept.getId()));
            List<Long> collect = adminUserDOS.stream().collect(Collectors.mapping(AdminUserDO::getId, Collectors.toList()));

            Long l = loginLogMapper.selectCount(new LambdaQueryWrapperX<LoginLogDO>().in(LoginLogDO::getUserId, collect).eq(LoginLogDO::getLogType, 100));

            stringObjectHashMap1.put(dept.getName(),l);
        });
        stringObjectHashMap.put("loginCountMap",stringObjectHashMap1);
        return stringObjectHashMap;
    }

    @Override
    public List<HzOrderProgressDO> getProgress(Long orderId) {

       return hzOrderMapper.getProgress(orderId);
    }

    @Override
    @Transactional
    public Boolean rawCallback(List<HzOrderCallBackVO> list) {

        try {
            list.forEach(vo -> {
                //创建原料入库（回调）
                ErpStockRecordCreateReqBO erpStockRecordCreateReqBO = new ErpStockRecordCreateReqBO();
                erpStockRecordCreateReqBO.setProductId(vo.getProductId());
                erpStockRecordCreateReqBO.setWarehouseId(vo.getWareHouseId());
                erpStockRecordCreateReqBO.setCount(vo.getProductAmount());
                erpStockRecordCreateReqBO.setBizType(ErpStockRecordBizTypeEnum.RAW_IN.getType());
                erpStockRecordCreateReqBO.setBizId(1L);
                erpStockRecordCreateReqBO.setBizItemId(1L);
                erpStockRecordCreateReqBO.setBizNo("1");
                erpStockRecordServiceImpl.createStockRecord(erpStockRecordCreateReqBO);

            });
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Boolean orderCallback(HzOrderCallBackVO vo) {
        try {

            HzOrderDO hzOrderDO = new HzOrderDO();
            hzOrderDO.setId(vo.getOrderId());
            //状态 1转移 2作废
            //1待审核；2审核驳回；3待生产；5生产中；6待成品出库；7待过磅；8已过磅  ；9待配比原料；  10待原料出库 11 已取消生产 12 已作废 13 已转移 14 待交付
            if(vo.getStatus() == 1){
                HzOrderDO newOrder = hzOrderMapper.selectById(vo.getReceiveOrderId());
                newOrder.setReceiveAmount(vo.getProductAmount());
                //转移数量大于或等于生产总量，则该笔订单直接进入“待过磅”状态。
                if(newOrder.getProductAmount().compareTo(vo.getProductAmount()) <= 0){
                    newOrder.setStatus(7);
                }
                hzOrderMapper.updateById(newOrder);
                hzOrderDO.setStatus(13);
            }else {
                hzOrderDO.setStatus(12);
            }
            hzOrderMapper.updateById(hzOrderDO);

        } catch (Exception e) {
            return false;
        }

       return true;
    }

    @Override
    public List<HzOrderDO> getOrderByStatus(Long status) {
        return hzOrderMapper.getOrderByStatus(status);

    }


    //插入订单进度
    public void insertProgress(Long orderId, String  progress) {
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        HzOrderProgressDO hzOrderProgressDO = new HzOrderProgressDO();

        if (loginUser != null) {
        Long id = loginUser.getId();
        AdminUserDO adminUserDO = userMapper.selectById(id);


            hzOrderProgressDO.setPhone(adminUserDO.getMobile());
            hzOrderProgressDO.setCreator(loginUser.getInfo().get("nickname"));
        }

        hzOrderProgressDO.setOrderId(orderId);
        hzOrderProgressDO.setProgress(progress);
        hzOrderProgressMapper.insert( hzOrderProgressDO);

    }

}