package cn.iocoder.yudao.module.erp.service.hzRawConsume;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.util.validation.ValidationUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.erp.controller.admin.productWeighingDetails.vo.HzProductWeighingDetailsSaveReqVO;
import cn.iocoder.yudao.module.erp.controller.admin.productWeighingDetails.vo.WeighingImportRespVO;
import cn.iocoder.yudao.module.erp.dal.dataobject.order.HzOrderDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.product.ErpProductDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.productWeighingDetails.HzProductWeighingDetailsDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.stock.ErpStockDO;
import cn.iocoder.yudao.module.erp.enums.stock.ErpStockRecordBizTypeEnum;
import cn.iocoder.yudao.module.erp.service.stock.ErpStockRecordServiceImpl;
import cn.iocoder.yudao.module.erp.service.stock.bo.ErpStockRecordCreateReqBO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.erp.controller.admin.hzRawConsume.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.hzRawConsume.HzRawConsumeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.erp.dal.mysql.hzRawConsume.HzRawConsumeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.erp.enums.ErrorCodeConstants.*;

/**
 * 订单原料消耗表
 Service 实现类
 *
 * @author 超管
 */
@Service
@Validated
public class HzRawConsumeServiceImpl implements HzRawConsumeService {

    @Resource
    private HzRawConsumeMapper hzRawConsumeMapper;
    @Resource
    private ErpStockRecordServiceImpl erpStockRecordServiceImpl;
    @Override
    public Long createHzRawConsume(HzRawConsumeSaveReqVO createReqVO) {
        // 插入
        HzRawConsumeDO hzRawConsume = BeanUtils.toBean(createReqVO, HzRawConsumeDO.class);
        hzRawConsumeMapper.insert(hzRawConsume);

        // 返回
        return hzRawConsume.getId();
    }

    @Override
    public void updateHzRawConsume(HzRawConsumeSaveReqVO updateReqVO) {
        // 校验存在
        // 更新
        HzRawConsumeDO updateObj = BeanUtils.toBean(updateReqVO, HzRawConsumeDO.class);
        hzRawConsumeMapper.updateById(updateObj);
    }

    @Override
    public void deleteHzRawConsume(Long id) {
        // 校验存在
        // 删除
        hzRawConsumeMapper.deleteById(id);
    }

    @Override
        public void deleteHzRawConsumeListByIds(List<Long> ids) {
        // 删除
        hzRawConsumeMapper.deleteByIds(ids);
        }




    @Override
    public HzRawConsumeDO getHzRawConsume(Long id) {
        return hzRawConsumeMapper.selectById(id);
    }

    @Override
    public PageResult<HzRawConsumeDO> getHzRawConsumePage(HzRawConsumePageReqVO pageReqVO) {
        return hzRawConsumeMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional
    public Boolean importRawConsume(List<HzRawConsumeEx> list,Long orderId,Long warehouseId) {

        List<HzRawConsumeDO> bean = BeanUtils.toBean(list, HzRawConsumeDO.class);
        // 参数校验
        if (CollUtil.isEmpty(list)) {
            throw exception(WEIGHING_DETAILS_IMPORT_LIST_IS_EMPTY);
        }
        bean.forEach(item -> {
            ErpProductDO productDO =  hzRawConsumeMapper.getProduct(item.getProductName(), item.getStandard());
            item.setProductId(productDO.getId());
            item.setOrderId(orderId);
            item.setWarehouseId(warehouseId);
            if (productDO == null) {
                throw exception(PRODUCT_NOT_EXIST);
            }

            ErpStockDO stock =  hzRawConsumeMapper.selectStock(productDO.getId(),warehouseId);
            if (stock.getCount().compareTo(item.getTargetValue()) < 0) {
                throw exception(WEIGHING_DETAILS_IMPORT_STOCK_NOT_ENOUGH);
            }
                ErpStockRecordCreateReqBO erpStockRecordCreateReqBO = new ErpStockRecordCreateReqBO();
                erpStockRecordCreateReqBO.setProductId(productDO.getId());
                erpStockRecordCreateReqBO.setWarehouseId(warehouseId);
                erpStockRecordCreateReqBO.setCount(item.getTargetValue().negate());
                erpStockRecordCreateReqBO.setBizType(ErpStockRecordBizTypeEnum.RAW_OUT.getType());
                erpStockRecordCreateReqBO.setBizId(orderId);
                erpStockRecordCreateReqBO.setBizItemId(orderId);
                erpStockRecordCreateReqBO.setBizNo(orderId.toString());
                erpStockRecordServiceImpl.createStockRecord(erpStockRecordCreateReqBO);


        });


        return hzRawConsumeMapper.insertBatch(bean);

    }

    @Override
    public List<HzRawConsumeDO> getByOrderId(Long orderId) {

        return hzRawConsumeMapper.getByOrderId(orderId);

    }

}