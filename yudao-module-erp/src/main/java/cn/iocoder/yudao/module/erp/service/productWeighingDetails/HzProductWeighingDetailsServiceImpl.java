package cn.iocoder.yudao.module.erp.service.productWeighingDetails;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.validation.ValidationUtils;
import cn.iocoder.yudao.framework.datapermission.core.util.DataPermissionUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.erp.dal.dataobject.order.HzOrderDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.product.ErpProductDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.purchase.ErpPurchaseOrderDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.purchase.ErpPurchaseOrderItemDO;
import cn.iocoder.yudao.module.erp.dal.mysql.order.HzOrderMapper;
import cn.iocoder.yudao.module.erp.dal.mysql.product.ErpProductMapper;
import cn.iocoder.yudao.module.erp.dal.mysql.purchase.ErpPurchaseOrderItemMapper;
import cn.iocoder.yudao.module.erp.dal.mysql.purchase.ErpPurchaseOrderMapper;
import cn.iocoder.yudao.module.erp.dal.mysql.purchase.ErpSupplierMapper;
import cn.iocoder.yudao.module.erp.dal.mysql.stock.ErpWarehouseMapper;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.user.UserImportRespVO;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.user.UserSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import com.google.common.annotations.VisibleForTesting;
import jakarta.validation.ConstraintViolationException;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.erp.controller.admin.productWeighingDetails.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.productWeighingDetails.HzProductWeighingDetailsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.erp.dal.mysql.productWeighingDetails.HzProductWeighingDetailsMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.erp.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * 成品出库过磅明细表
 Service 实现类
 *
 * @author liangchuang
 */
@Service
@Validated
public class HzProductWeighingDetailsServiceImpl implements HzProductWeighingDetailsService {

    @Resource
    private ErpProductMapper productMapper;
    @Resource
    private ErpSupplierMapper supplierMapper;
    @Resource
    private HzProductWeighingDetailsMapper hzProductWeighingDetailsMapper;

    @Resource
    private ErpPurchaseOrderMapper purchaseOrderMapper;
    @Resource
    private ErpPurchaseOrderItemMapper purchaseOrderItemMapper;

    @Resource
    private ErpWarehouseMapper warehouseMapper;
    @Resource
    private HzOrderMapper hzOrderMapper;
    @Override
    public Long createHzProductWeighingDetails(HzProductWeighingDetailsSaveReqVO createReqVO) {
        // 插入
        HzProductWeighingDetailsDO hzProductWeighingDetails = BeanUtils.toBean(createReqVO, HzProductWeighingDetailsDO.class);
        hzProductWeighingDetailsMapper.insert(hzProductWeighingDetails);

        // 返回
        return hzProductWeighingDetails.getId();
    }

    @Override
    public void updateHzProductWeighingDetails(HzProductWeighingDetailsSaveReqVO updateReqVO) {
        // 校验存在
        validateHzProductWeighingDetailsExists(updateReqVO.getId());
        // 更新
        HzProductWeighingDetailsDO updateObj = BeanUtils.toBean(updateReqVO, HzProductWeighingDetailsDO.class);
        hzProductWeighingDetailsMapper.updateById(updateObj);
    }

    @Override
    public void deleteHzProductWeighingDetails(Long id) {
        // 校验存在
        validateHzProductWeighingDetailsExists(id);
        // 删除
        hzProductWeighingDetailsMapper.deleteById(id);
    }

    @Override
        public void deleteHzProductWeighingDetailsListByIds(List<Long> ids) {
        // 删除
        hzProductWeighingDetailsMapper.deleteByIds(ids);
        }


    private void validateHzProductWeighingDetailsExists(Long id) {
        if (hzProductWeighingDetailsMapper.selectById(id) == null) {
            throw exception(HZ_PRODUCT_WEIGHING_DETAILS_NOT_EXISTS);
        }
    }

    @Override
    public HzProductWeighingDetailsDO getHzProductWeighingDetails(Long id) {
        return hzProductWeighingDetailsMapper.selectById(id);
    }

    @Override
    public PageResult<HzProductWeighingDetailsDO> getHzProductWeighingDetailsPage(HzProductWeighingDetailsPageReqVO pageReqVO) {
        return hzProductWeighingDetailsMapper.selectPage(pageReqVO);
    }

    @Override
    public WeighingImportRespVO importProductWeighingList(List<WeighingImportExcelVO> importDetails, Boolean updateSupport,Long orderId) {

        // 1.1 参数校验
        if (CollUtil.isEmpty(importDetails)) {
            throw exception(WEIGHING_DETAILS_IMPORT_LIST_IS_EMPTY);
        }

        // 2. 遍历，逐个创建 or 更新
        WeighingImportRespVO respVO = WeighingImportRespVO.builder().createSerialNumber(new ArrayList<>())
                .updateSerialNumber(new ArrayList<>()).failureSerialNumber(new LinkedHashMap<>()).build();
        importDetails.forEach(importDetail -> {
            // 2.1.1 校验字段是否符合要求
            try {
                HzProductWeighingDetailsSaveReqVO bean = BeanUtils.toBean(importDetail, HzProductWeighingDetailsSaveReqVO.class);
                ValidationUtils.validate(bean);
            } catch (ConstraintViolationException ex){
                respVO.getFailureSerialNumber().put(importDetail.getSerialNumber(), ex.getMessage());
                return;
            }

            // 2.2.1 判断如果不存在，在进行插入
            HzProductWeighingDetailsDO hzProductWeighingDetailsDO = hzProductWeighingDetailsMapper.selectBySerialNumber(importDetail.getSerialNumber());

            if (hzProductWeighingDetailsDO == null) {
                //获取创建者
                LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
                HzProductWeighingDetailsDO bean = BeanUtils.toBean(importDetail, HzProductWeighingDetailsDO.class);

                if (loginUser != null){
                    bean.setCreator(loginUser.getInfo().get("nickname"));
                    bean.setUpdater(loginUser.getInfo().get("nickname"));
                }
                bean.setOrderId(orderId);
                hzProductWeighingDetailsMapper.insert(bean);
                respVO.getCreateSerialNumber().add(importDetail.getSerialNumber());
                return;
            }
            // 2.2.2 如果存在，判断是否允许更新
            if (!updateSupport) {
                respVO.getFailureSerialNumber().put(importDetail.getSerialNumber(), WEIGHING_DETAILS_EXISTS.getMsg());
                return;
            }
            HzProductWeighingDetailsDO updateUser = BeanUtils.toBean(importDetail, HzProductWeighingDetailsDO.class);
            updateUser.setId(hzProductWeighingDetailsDO.getId());
            hzProductWeighingDetailsMapper.updateById(updateUser);
            respVO.getUpdateSerialNumber().add(importDetail.getSerialNumber());
        });

        return respVO;


    }

    @Override
    public List<HzProductWeighingDetailsDO> getWeighingDetailsByOrderId(Long orderId) {
      return   hzProductWeighingDetailsMapper.getWeighingDetailsByOrderId(orderId);

    }

    @Override
    public WeighingImportRespVO importPurchase(List<WeighingImportExcelVO> importDetails) {

        // 参数校验
        if (CollUtil.isEmpty(importDetails)) {
            throw exception(WEIGHING_DETAILS_IMPORT_LIST_IS_EMPTY);
        }

        // 遍历
        WeighingImportRespVO respVO = WeighingImportRespVO.builder()
                .createSerialNumber(new ArrayList<>())
                .failureSerialNumber(new LinkedHashMap<>()).build();
        //查询所有待过磅状态的采购订单
        List<ErpPurchaseOrderDO> erpPurchaseOrderDOS = purchaseOrderMapper.selectList(ErpPurchaseOrderDO::getStatus, 3);

        //循环所有过磅记录
        importDetails.forEach(importDetail -> {
            //校验字段是否符合要求
            try {
                HzProductWeighingDetailsSaveReqVO bean = BeanUtils.toBean(importDetail, HzProductWeighingDetailsSaveReqVO.class);
                ValidationUtils.validate(bean);
            } catch (ConstraintViolationException ex){
                respVO.getFailureSerialNumber().put(importDetail.getSerialNumber(), ex.getMessage());
                return;
            }

            //判断如果不存在，在进行插入
            HzProductWeighingDetailsDO hzProductWeighingDetailsDO = hzProductWeighingDetailsMapper.selectBySerialNumber(importDetail.getSerialNumber());

            if (erpPurchaseOrderDOS != null && hzProductWeighingDetailsDO == null) {
                //循环所有带过磅订单
                erpPurchaseOrderDOS.forEach(erpPurchaseOrderDO -> {
                    List<ErpPurchaseOrderItemDO> erpPurchaseOrderItemDOS = purchaseOrderItemMapper.selectListByOrderId(erpPurchaseOrderDO.getId());

                    //组装该订单所有产品名称
                    ArrayList<String> productNameList = new ArrayList<>();
                    erpPurchaseOrderItemDOS.forEach(erpPurchaseOrderItemDO -> {
                        ErpProductDO erpProductDO = productMapper.selectOne(ErpProductDO::getId, erpPurchaseOrderItemDO.getProductId());
                        productNameList.add(erpProductDO.getName());
                    });

                    /**
                     *      1. 根据excel中的“发货单位”匹配系统词典中的“供应商”
                     *      2. 根据excel中的“货名”匹配系统中的“产品名称“
                     *      3. 根据excel中的“收货单位”匹配系统中的“仓库”
                     */
                    if (warehouseMapper.getWareName(erpPurchaseOrderDO.getWarehouseId()).equals(importDetail.getConsignee())
                            && supplierMapper.getSuName(erpPurchaseOrderDO.getSupplierId()).equals(importDetail.getShipper())
                            && productNameList.contains(importDetail.getProductName())) {


                        Long orderId = erpPurchaseOrderDO.getId();
                        //获取创建者
                        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
                        HzProductWeighingDetailsDO bean = BeanUtils.toBean(importDetail, HzProductWeighingDetailsDO.class);

                        if (loginUser != null){
                            bean.setCreator(loginUser.getInfo().get("nickname"));
                            bean.setUpdater(loginUser.getInfo().get("nickname"));
                        }
                        bean.setType(1L);
                        bean.setOrderId(orderId);
                        hzProductWeighingDetailsMapper.insert(bean);
                        respVO.getCreateSerialNumber().add(importDetail.getSerialNumber());
                    }
                });
            }
        });
        return respVO;

    }

    @Override
    public WeighingImportRespVO productOut(List<WeighingImportExcelVO> importDetails) {

        // 参数校验
        if (CollUtil.isEmpty(importDetails)) {
            throw exception(WEIGHING_DETAILS_IMPORT_LIST_IS_EMPTY);
        }

        // 遍历
        WeighingImportRespVO respVO = WeighingImportRespVO.builder()
                .createSerialNumber(new ArrayList<>())
                .failureSerialNumber(new LinkedHashMap<>()).build();
        //todo 查询所有待过磅状态的生产订单
        List<HzOrderDO> erpPurchaseOrderDOS = hzOrderMapper.selectList(HzOrderDO::getStatus, 7);

        //循环所有过磅记录
        importDetails.forEach(importDetail -> {
            //校验字段是否符合要求
            try {
                HzProductWeighingDetailsSaveReqVO bean = BeanUtils.toBean(importDetail, HzProductWeighingDetailsSaveReqVO.class);
                ValidationUtils.validate(bean);
            } catch (ConstraintViolationException ex){
                respVO.getFailureSerialNumber().put(importDetail.getSerialNumber(), ex.getMessage());
                return;
            }

            //判断如果不存在，在进行插入
            HzProductWeighingDetailsDO hzProductWeighingDetailsDO = hzProductWeighingDetailsMapper.selectBySerialNumber(importDetail.getSerialNumber());

            if (erpPurchaseOrderDOS != null && hzProductWeighingDetailsDO == null) {
                //循环所有带过磅生产订单
                erpPurchaseOrderDOS.forEach(erpPurchaseOrderDO -> {

                        ErpProductDO erpProductDO = productMapper.selectOne(ErpProductDO::getId, erpPurchaseOrderDO.getProductId());
                        String productName = erpProductDO.getName();


                    /**
                     *      1. 根据excel中的“收货单位”匹配订单管理中的“所属客户”
                     *      2. 根据excel中的“货名”匹配订单管理中的“产品名称“
                     *      3. 根据excel中的“规格”匹配订单管理中的“规格”
                     */
                    if (erpPurchaseOrderDO.getCustomer().equals(importDetail.getConsignee())
                            && erpPurchaseOrderDO.getSpecification().equals(importDetail.getStandard())
                            && productName.equals(importDetail.getProductName())) {


                        Long orderId = erpPurchaseOrderDO.getId();
                        //获取创建者
                        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
                        HzProductWeighingDetailsDO bean = BeanUtils.toBean(importDetail, HzProductWeighingDetailsDO.class);

                        if (loginUser != null){
                            bean.setCreator(loginUser.getInfo().get("nickname"));
                            bean.setUpdater(loginUser.getInfo().get("nickname"));
                        }
                        bean.setType(2L);
                        bean.setOrderId(orderId);
                        hzProductWeighingDetailsMapper.insert(bean);
                        respVO.getCreateSerialNumber().add(importDetail.getSerialNumber());
                    }
                });
            }
        });
        return respVO;

    }

    @Override
    public int checkOrder(Long orderId, Long type) {
       int i = hzProductWeighingDetailsMapper.getCountByOrderIdAndType(orderId,type);
        return i;
    }

}