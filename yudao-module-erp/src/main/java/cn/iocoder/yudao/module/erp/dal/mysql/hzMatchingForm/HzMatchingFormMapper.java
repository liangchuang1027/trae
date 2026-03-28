package cn.iocoder.yudao.module.erp.dal.mysql.hzMatchingForm;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.erp.dal.dataobject.hzMatchingForm.HzMatchingFormDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.erp.controller.admin.hzMatchingForm.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 配比模版 Mapper
 *
 * @author liangchuang
 */
@Mapper
public interface HzMatchingFormMapper extends BaseMapperX<HzMatchingFormDO> {

    default PageResult<HzMatchingFormDO> selectPage(HzMatchingFormPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<HzMatchingFormDO>()
                .eqIfPresent(HzMatchingFormDO::getName, reqVO.getName())

                .eqIfPresent(HzMatchingFormDO::getProductCategoryId, reqVO.getProductCategoryId())
                .eqIfPresent(HzMatchingFormDO::getVariety, reqVO.getVariety())
                .eqIfPresent(HzMatchingFormDO::getStrengthGrade, reqVO.getStrengthGrade())
                .eqIfPresent(HzMatchingFormDO::getSandRatio, reqVO.getSandRatio())
                .eqIfPresent(HzMatchingFormDO::getBulkDensity, reqVO.getBulkDensity())
                .eqIfPresent(HzMatchingFormDO::getWaterBinderRatio, reqVO.getWaterBinderRatio())
                .eqIfPresent(HzMatchingFormDO::getCriterion, reqVO.getCriterion())
                .eqIfPresent(HzMatchingFormDO::getManufacturer, reqVO.getManufacturer())
                .eqIfPresent(HzMatchingFormDO::getGradation, reqVO.getGradation())
                .eqIfPresent(HzMatchingFormDO::getTypesOfMixtures, reqVO.getTypesOfMixtures())
                .eqIfPresent(HzMatchingFormDO::getAsphaltContent, reqVO.getAsphaltContent())
                .eqIfPresent(HzMatchingFormDO::getRemarks, reqVO.getRemarks())
                .orderByDesc(HzMatchingFormDO::getId));
    }


    @Select("SELECT * FROM erp_hz_matching_form WHERE product_category_id = #{productCategoryId}")
    List<HzMatchingFormDO> selectByProductCategoryId(@Param("productCategoryId") Long productCategoryId);

}