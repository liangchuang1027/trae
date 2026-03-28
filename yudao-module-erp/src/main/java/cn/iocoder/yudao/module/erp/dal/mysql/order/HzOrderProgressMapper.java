package cn.iocoder.yudao.module.erp.dal.mysql.order;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.erp.controller.admin.order.vo.HzOrderPageReqVO;
import cn.iocoder.yudao.module.erp.dal.dataobject.order.HzOrderDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.order.HzOrderProgressDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 红正建材订单进度
 *
 * @author liangchuang
 */
@Mapper
public interface HzOrderProgressMapper extends BaseMapperX<HzOrderProgressDO> {



}