package cn.iocoder.yudao.module.erp.dal.mysql.order;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.erp.dal.dataobject.order.HzOrderDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.order.HzOrderProgressDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.erp.controller.admin.order.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 红正建材订单表
 Mapper
 *
 * @author liangchuang
 */
@Mapper
public interface HzOrderMapper extends BaseMapperX<HzOrderDO> {

    default PageResult<HzOrderDO> selectPage(HzOrderPageReqVO reqVO,List<Integer>  list) {
        return selectPage(reqVO, new LambdaQueryWrapperX<HzOrderDO>()
                .likeIfPresent(HzOrderDO::getOrderName, reqVO.getOrderName())
                .eqIfPresent(HzOrderDO::getProjectId, reqVO.getProjectId())
                .eqIfPresent(HzOrderDO::getPause, reqVO.getPause())
                .likeIfPresent(HzOrderDO::getCustomer, reqVO.getCustomer())
                .betweenIfPresent(HzOrderDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(HzOrderDO::getCreator, reqVO.getCreator())
                .eqIfPresent(HzOrderDO::getStatus, reqVO.getStatus())
                .in(reqVO.getStatus() == null , HzOrderDO::getStatus,list)
                .orderByDesc(HzOrderDO::getId));
    }

   @Select("SELECT * FROM erp_hz_order WHERE order_number = #{no}")
    HzOrderDO selectByNo(@Param("no") String no);

    @Select("SELECT * FROM erp_hz_order_progress WHERE order_id = #{orderId} order by create_time DESC")
    List<HzOrderProgressDO> getProgress(Long orderId);

    @Select("SELECT * FROM erp_hz_order WHERE status = #{status} and pause = 0 and deleted = 0")
    List<HzOrderDO> getOrderByStatus(Long status);
}