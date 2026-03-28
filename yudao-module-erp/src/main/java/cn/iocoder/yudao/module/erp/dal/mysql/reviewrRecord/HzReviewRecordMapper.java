package cn.iocoder.yudao.module.erp.dal.mysql.reviewrRecord;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.erp.dal.dataobject.reviewrRecord.HzReviewRecordDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.erp.controller.admin.reviewrRecord.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 红正建材审核记录 Mapper
 *
 * @author liangchuang
 */
@Mapper
public interface HzReviewRecordMapper extends BaseMapperX<HzReviewRecordDO> {

    default PageResult<HzReviewRecordDO> selectPage(HzReviewRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<HzReviewRecordDO>()
                .likeIfPresent(HzReviewRecordDO::getName, reqVO.getName())
                .eqIfPresent(HzReviewRecordDO::getResault, reqVO.getResault())
                .eqIfPresent(HzReviewRecordDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(HzReviewRecordDO::getPurchaseId, reqVO.getPurchaseId())
                .eqIfPresent(HzReviewRecordDO::getRefuseReasons, reqVO.getRefuseReasons())
                .betweenIfPresent(HzReviewRecordDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(HzReviewRecordDO::getId));
    }

    @Select("select * from erp_hz_review_record where order_id = #{id} and deleted = 0")
    HzReviewRecordDO selectByOrderId(@Param("id") Long id);

    @Select("select * from erp_hz_review_record where purchase_id = #{id} and deleted = 0 ")
    HzReviewRecordDO getByPurchaseId(@Param("id") Long id);

    @Delete("delete from erp_hz_review_record where order_id = #{id}")
    void deleteByOrderId(@Param("id") Long id);

    @Delete("delete from erp_hz_review_record where purchase_id = #{id}")
    void deleteByPurchaseId(@Param("id") Long id);

}