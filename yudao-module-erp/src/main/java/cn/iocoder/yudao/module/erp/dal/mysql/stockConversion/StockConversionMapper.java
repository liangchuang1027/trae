package cn.iocoder.yudao.module.erp.dal.mysql.stockConversion;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.erp.dal.dataobject.stockConversion.StockConversionDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.erp.controller.admin.stockConversion.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 在库形态转换 Mapper
 *
 * @author liangchuang
 */
@Mapper
public interface StockConversionMapper extends BaseMapperX<StockConversionDO> {

    default PageResult<StockConversionDO> selectPage(StockConversionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StockConversionDO>()
                .eqIfPresent(StockConversionDO::getParentId, 0)
                .eqIfPresent(StockConversionDO::getProductId, reqVO.getProductId())
                .eqIfPresent(StockConversionDO::getWarehouseId, reqVO.getWarehouseId())
                .eqIfPresent(StockConversionDO::getType, reqVO.getType())
                .likeIfPresent(StockConversionDO::getProductName, reqVO.getProductName())
                .eqIfPresent(StockConversionDO::getCount, reqVO.getCount())
                .eqIfPresent(StockConversionDO::getUnit, reqVO.getUnit())
                .eqIfPresent(StockConversionDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(StockConversionDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(StockConversionDO::getRefusalReason, reqVO.getRefusalReason())
                .orderByDesc(StockConversionDO::getId));
    }

    @Delete("DELETE FROM erp_stock_conversion WHERE parent_id = #{id}")
    void deleteByParentId(@Param("id") Long id);

   @Select("SELECT * FROM erp_stock_conversion WHERE parent_id = #{id}")
    List<StockConversionDO> selectByParentId(Long id);

}