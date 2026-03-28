package cn.iocoder.yudao.module.erp.dal.mysql.hzRawConsume;

import java.time.LocalTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.erp.dal.dataobject.hzRawConsume.HzRawConsumeDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.product.ErpProductDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.stock.ErpStockDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.erp.controller.admin.hzRawConsume.vo.*;
import org.apache.ibatis.annotations.Select;

/**
 * 订单原料消耗表
 Mapper
 *
 * @author 超管
 */
@Mapper
public interface HzRawConsumeMapper extends BaseMapperX<HzRawConsumeDO> {

    default PageResult<HzRawConsumeDO> selectPage(HzRawConsumePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<HzRawConsumeDO>()
                .eqIfPresent(HzRawConsumeDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(HzRawConsumeDO::getProductId, reqVO.getProductId())
                .likeIfPresent(HzRawConsumeDO::getProductName, reqVO.getProductName())
                .eqIfPresent(HzRawConsumeDO::getTargetValue, reqVO.getTargetValue())
                .eqIfPresent(HzRawConsumeDO::getFactValue, reqVO.getFactValue())
                .eqIfPresent(HzRawConsumeDO::getErrorValue, reqVO.getErrorValue())
                .eqIfPresent(HzRawConsumeDO::getErrorRate, reqVO.getErrorRate())
                .betweenIfPresent(HzRawConsumeDO::getCreateTime, reqVO.getCreateTime()[0].toLocalDate().atStartOfDay(),reqVO.getCreateTime()[0].toLocalDate().atTime(LocalTime.MAX))
                .orderByDesc(HzRawConsumeDO::getId));
    }

    @Select("SELECT *  FROM erp_stock  WHERE product_id = #{productId} and warehouse_id = #{warehouseId}")
    ErpStockDO selectStock(Long productId, Long warehouseId);

    @Select("SELECT * FROM erp_product WHERE name = #{productName} and standard = #{standard}")
    ErpProductDO getProduct(String productName, String standard);

    @Select("SELECT product_id as productId, product_name as productName , standard FROM erp_hz_raw_consume WHERE order_id = #{orderId} GROUP BY product_name , standard, product_id")
    List<HzRawConsumeDO> getByOrderId(Long orderId);

}