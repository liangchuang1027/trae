package cn.iocoder.yudao.module.erp.service.matchingExample;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.erp.controller.admin.matchingExampleRaw.vo.HzMatchingExampleRawSaveReqVO;
import cn.iocoder.yudao.module.erp.controller.admin.matchingFormRaw.vo.HzMatchingFormRawSaveReqVO;
import cn.iocoder.yudao.module.erp.dal.dataobject.hzMatchingForm.HzMatchingFormDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.matchingExampleRaw.HzMatchingExampleRawDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.matchingFormRaw.HzMatchingFormRawDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.order.HzOrderDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.stock.ErpStockDO;
import cn.iocoder.yudao.module.erp.dal.mysql.matchingExampleRaw.HzMatchingExampleRawMapper;
import cn.iocoder.yudao.module.erp.dal.mysql.order.HzOrderMapper;
import cn.iocoder.yudao.module.erp.dal.redis.no.ErpNoRedisDAO;
import cn.iocoder.yudao.module.erp.service.stock.ErpStockService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import cn.iocoder.yudao.module.erp.controller.admin.matchingExample.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.matchingExample.HzMatchingExampleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.erp.dal.mysql.matchingExample.HzMatchingExampleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.erp.enums.ErrorCodeConstants.*;

/**
 * 配比实例 Service 实现类
 *
 * @author liangchuang
 */
@Service
@Validated
public class HzMatchingExampleServiceImpl implements HzMatchingExampleService {
    @Resource
    private ErpNoRedisDAO noRedisDAO;
    @Resource
    private HzMatchingExampleMapper hzMatchingExampleMapper;
    @Resource
    private HzMatchingExampleRawMapper hzMatchingExampleRawMapper;
    @Resource
    private HzOrderMapper hzOrderMapper;

    @Resource
    private ErpStockService stockService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createHzMatchingExample(HzMatchingExampleSaveReqVO createReqVO) {

        Long orderId = createReqVO.getOrderId();
        //删除已有记录
        HzMatchingExampleDO hzMatchingExampleDO =  hzMatchingExampleMapper.selectByOrderId(orderId);
        if (hzMatchingExampleDO != null) {
            Long id = hzMatchingExampleDO.getId();
            hzMatchingExampleRawMapper.deleteByMatchingExampleId(id);
            hzMatchingExampleMapper.deleteByIdR(id);
        }

        // 插入
        HzMatchingExampleDO hzMatchingExample = BeanUtils.toBean(createReqVO, HzMatchingExampleDO.class);
        hzMatchingExample.setStatus(0L);
        //生成原料配比单号，并校验唯一性
        String no = noRedisDAO.generate(ErpNoRedisDAO.RAW_NO_PREFIX);
        if (hzOrderMapper.selectByNo(no) != null) {
            throw exception(PURCHASE_ORDER_NO_EXISTS);
        }
        hzMatchingExample.setExampleNumber(no);
        //获取创建者
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        if (loginUser != null) {
            hzMatchingExample.setCreator(loginUser.getInfo().get("nickname"));
        }
        hzMatchingExampleMapper.insert(hzMatchingExample);
        HzOrderDO hzOrderDO = hzOrderMapper.selectById(hzMatchingExample.getOrderId());
        BigDecimal productAmount = hzOrderDO.getProductAmount();
        //插入模版多条原料子表
        List<HzMatchingExampleRawSaveReqVO> raws = createReqVO.getRaws();
        raws.forEach(
                raw -> {
                    if(raw.getRatio() != null){
                        BigDecimal divide = raw.getRatio().divide(BigDecimal.valueOf(100));
                        raw.setRatio(divide);
                    }

                    raw.setMatchingExampleId(hzMatchingExample.getId());
                    if (raw.getNumber() == null){
                        raw.setNumber(productAmount.multiply(raw.getRatio()));
                    }
                }
        );
        List<HzMatchingExampleRawDO> beans = BeanUtils.toBean(raws, HzMatchingExampleRawDO.class);
        hzMatchingExampleRawMapper.insertBatch(beans);

        // 返回
        return hzMatchingExample.getId();
    }

    @Override
    public void updateHzMatchingExample(HzMatchingExampleSaveReqVO updateReqVO) {
        // 校验存在
        validateHzMatchingExampleExists(updateReqVO.getId());
        // 更新
        HzMatchingExampleDO updateObj = BeanUtils.toBean(updateReqVO, HzMatchingExampleDO.class);
        hzMatchingExampleMapper.updateById(updateObj);
    }

    @Override
    public void deleteHzMatchingExample(Long id) {
        // 校验存在
        validateHzMatchingExampleExists(id);
        // 删除
        hzMatchingExampleMapper.deleteById(id);
    }

    @Override
        public void deleteHzMatchingExampleListByIds(List<Long> ids) {
        // 删除
        hzMatchingExampleMapper.deleteByIds(ids);
        }


    private void validateHzMatchingExampleExists(Long id) {
        if (hzMatchingExampleMapper.selectById(id) == null) {
            throw exception(HZ_MATCHING_EXAMPLE_NOT_EXISTS);
        }
    }

    @Override
    public HzMatchingExampleDO getHzMatchingExample(Long orderId) {
        HzMatchingExampleDO hzMatchingExampleDO = hzMatchingExampleMapper.selectByOrderId(orderId);
        if (hzMatchingExampleDO == null) {
            return null;
        }
        List<HzMatchingExampleRawDO> hzMatchingExampleRawDOS = hzMatchingExampleRawMapper.selectByMatchingExampleId(hzMatchingExampleDO.getId());
        hzMatchingExampleRawDOS.forEach(e->{
            if (e.getRatio()!= null){
                e.setRatio(e.getRatio().multiply(BigDecimal.valueOf(100)));
            }
            ErpStockDO stock = stockService.getStock(e.getProductId(), 7L);
            //库存为null代表没有库存记录 也是=0
            if (stock == null) {
                e.setStock(BigDecimal.ZERO);
            }else {
                e.setStock(stock.getCount());
            }
        });

        hzMatchingExampleDO.setRaws(hzMatchingExampleRawDOS);
        return hzMatchingExampleDO;
    }

    @Override
    public PageResult<HzMatchingExampleDO> getHzMatchingExamplePage(HzMatchingExamplePageReqVO pageReqVO) {
        PageResult<HzMatchingExampleDO> hzMatchingExampleDOPageResult = hzMatchingExampleMapper.selectPage(pageReqVO);
        hzMatchingExampleDOPageResult.getList().forEach(e->{
            List<HzMatchingExampleRawDO> hzMatchingExampleRawDOS = hzMatchingExampleRawMapper.selectByMatchingExampleId(e.getId());
            e.setRaws(hzMatchingExampleRawDOS);
        });
        return hzMatchingExampleDOPageResult;
    }

}