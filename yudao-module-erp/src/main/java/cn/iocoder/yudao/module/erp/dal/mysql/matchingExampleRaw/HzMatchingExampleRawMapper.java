package cn.iocoder.yudao.module.erp.dal.mysql.matchingExampleRaw;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.erp.dal.dataobject.matchingExampleRaw.HzMatchingExampleRawDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.erp.controller.admin.matchingExampleRaw.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 配比原料实例 Mapper
 *
 * @author liangchuang
 */
@Mapper
public interface HzMatchingExampleRawMapper extends BaseMapperX<HzMatchingExampleRawDO> {

    default PageResult<HzMatchingExampleRawDO> selectPage(HzMatchingExampleRawPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<HzMatchingExampleRawDO>()
                .eqIfPresent(HzMatchingExampleRawDO::getMatchingExampleId, reqVO.getMatchingExampleId())
                .eqIfPresent(HzMatchingExampleRawDO::getProductId, reqVO.getProductId())
                .eqIfPresent(HzMatchingExampleRawDO::getStandard, reqVO.getStandard())
                .eqIfPresent(HzMatchingExampleRawDO::getRatio, reqVO.getRatio())
                .eqIfPresent(HzMatchingExampleRawDO::getNumber, reqVO.getNumber())
                .orderByDesc(HzMatchingExampleRawDO::getId));
    }

    @Delete("DELETE FROM erp_hz_matching_example_raw WHERE matching_example_id = #{id}")
    int deleteByMatchingExampleId(@Param("id") Long id);

    @Select("SELECT r.* FROM erp_hz_matching_example m left join erp_hz_matching_example_raw r on m.id =r.matching_example_id  WHERE m.order_id =  #{orderId}")
    List<HzMatchingExampleRawDO> getByOrderId(@Param("orderId") Long orderId);

    @Select("SELECT * FROM erp_hz_matching_example_raw WHERE matching_example_id = #{id}")
    List<HzMatchingExampleRawDO> selectByMatchingExampleId(Long id);
}