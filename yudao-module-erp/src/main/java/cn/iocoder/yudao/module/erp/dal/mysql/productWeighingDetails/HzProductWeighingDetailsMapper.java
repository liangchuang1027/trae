package cn.iocoder.yudao.module.erp.dal.mysql.productWeighingDetails;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.erp.dal.dataobject.productWeighingDetails.HzProductWeighingDetailsDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.erp.controller.admin.productWeighingDetails.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 成品出库过磅明细表
 Mapper
 *
 * @author liangchuang
 */
@Mapper
public interface HzProductWeighingDetailsMapper extends BaseMapperX<HzProductWeighingDetailsDO> {

    default PageResult<HzProductWeighingDetailsDO> selectPage(HzProductWeighingDetailsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<HzProductWeighingDetailsDO>()
                .eq(HzProductWeighingDetailsDO::getType, reqVO.getType())
                .likeIfPresent(HzProductWeighingDetailsDO::getSerialNumber, reqVO.getSerialNumber())
                .likeIfPresent(HzProductWeighingDetailsDO::getCarNumber, reqVO.getCarNumber())
                .likeIfPresent(HzProductWeighingDetailsDO::getProductName, reqVO.getProductName())
                .betweenIfPresent(HzProductWeighingDetailsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(HzProductWeighingDetailsDO::getId));
    }

    @Select("SELECT * FROM erp_hz_product_weighing_details WHERE serial_number = #{serialNumber}")
    HzProductWeighingDetailsDO selectBySerialNumber(@Param("serialNumber") String serialNumber);

    @Select("SELECT * FROM erp_hz_product_weighing_details WHERE order_id = #{orderId}")
    List<HzProductWeighingDetailsDO> getWeighingDetailsByOrderId(@Param("orderId") Long orderId);

    @Select("SELECT COUNT(1) FROM erp_hz_product_weighing_details WHERE order_id = #{orderId} AND type = #{type}")
    int getCountByOrderIdAndType(@Param("orderId") Long orderId,@Param("type") Long type);

}