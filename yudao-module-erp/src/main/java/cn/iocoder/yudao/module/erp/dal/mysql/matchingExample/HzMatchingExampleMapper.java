package cn.iocoder.yudao.module.erp.dal.mysql.matchingExample;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.erp.dal.dataobject.matchingExample.HzMatchingExampleDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.erp.controller.admin.matchingExample.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 配比实例 Mapper
 *
 * @author liangchuang
 */
@Mapper
public interface HzMatchingExampleMapper extends BaseMapperX<HzMatchingExampleDO> {

    default PageResult<HzMatchingExampleDO> selectPage(HzMatchingExamplePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<HzMatchingExampleDO>()
                .eqIfPresent(HzMatchingExampleDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(HzMatchingExampleDO::getVariety, reqVO.getVariety())
                .eqIfPresent(HzMatchingExampleDO::getStrengthGrade, reqVO.getStrengthGrade())
                .eqIfPresent(HzMatchingExampleDO::getSandRatio, reqVO.getSandRatio())
                .eqIfPresent(HzMatchingExampleDO::getBulkDensity, reqVO.getBulkDensity())
                .eqIfPresent(HzMatchingExampleDO::getWaterBinderRatio, reqVO.getWaterBinderRatio())
                .eqIfPresent(HzMatchingExampleDO::getCriterion, reqVO.getCriterion())
                .eqIfPresent(HzMatchingExampleDO::getManufacturer, reqVO.getManufacturer())
                .eqIfPresent(HzMatchingExampleDO::getGradation, reqVO.getGradation())
                .eqIfPresent(HzMatchingExampleDO::getTypesOfMixtures, reqVO.getTypesOfMixtures())
                .eqIfPresent(HzMatchingExampleDO::getAsphaltContent, reqVO.getAsphaltContent())
                .eqIfPresent(HzMatchingExampleDO::getRemarks, reqVO.getRemarks())
                .eqIfPresent(HzMatchingExampleDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(HzMatchingExampleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(HzMatchingExampleDO::getId));
    }

    @Select("SELECT * FROM erp_hz_matching_example WHERE order_id = #{orderId}")
    HzMatchingExampleDO selectByOrderId(@Param("orderId") Long orderId);

    @Delete("DELETE FROM erp_hz_matching_example WHERE id = #{id}")
    void deleteByIdR(@Param("id") Long id);
}