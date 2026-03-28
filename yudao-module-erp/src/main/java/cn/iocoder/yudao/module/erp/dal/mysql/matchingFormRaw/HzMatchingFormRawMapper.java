package cn.iocoder.yudao.module.erp.dal.mysql.matchingFormRaw;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.erp.dal.dataobject.matchingFormRaw.HzMatchingFormRawDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.erp.controller.admin.matchingFormRaw.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 配比模版原料 Mapper
 *
 * @author liangchuang
 */
@Mapper
public interface HzMatchingFormRawMapper extends BaseMapperX<HzMatchingFormRawDO> {

    default PageResult<HzMatchingFormRawDO> selectPage(HzMatchingFormRawPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<HzMatchingFormRawDO>()
                .eqIfPresent(HzMatchingFormRawDO::getMatchingFormId, reqVO.getMatchingFormId())
                .eqIfPresent(HzMatchingFormRawDO::getProductId, reqVO.getProductId())
                .eqIfPresent(HzMatchingFormRawDO::getStandard, reqVO.getStandard())
                .eqIfPresent(HzMatchingFormRawDO::getRatio, reqVO.getRatio())
                .orderByDesc(HzMatchingFormRawDO::getId));
    }

    @Delete("DELETE FROM erp_hz_matching_form_raw WHERE matching_form_id = #{matchingFormId}")
    void deleteByMatchingFormId(@Param("matchingFormId") Long matchingFormId);

    @Delete("DELETE FROM erp_hz_matching_form_raw WHERE matching_form_id IN (#{matchingFormIds})")
    void deleteByMatchingFormIds(@Param("matchingFormIds") List<Long> matchingFormIds);

    @Select("SELECT * FROM erp_hz_matching_form_raw WHERE matching_form_id = #{id}")
    List<HzMatchingFormRawDO> selectByMatchingFormId(@Param("id") Long id);

}