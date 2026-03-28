package cn.iocoder.yudao.module.erp.service.order;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.erp.dal.dataobject.order.HzOrderProgressDO;
import com.google.gson.JsonObject;
import jakarta.validation.*;
import cn.iocoder.yudao.module.erp.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.order.HzOrderDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 红正建材订单表
 Service 接口
 *
 * @author liangchuang
 */
public interface HzOrderService {

    /**
     * 创建红正建材订单表

     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createHzOrder(@Valid HzOrderSaveReqVO createReqVO);

    /**
     * 更新红正建材订单表

     *
     * @param updateReqVO 更新信息
     */
    void updateHzOrder(@Valid HzOrderSaveReqVO updateReqVO);
    /**
     *   变更挂起

     *
     * @param updateReqVO 更新信息
     */
    void updatePause(@Valid HzOrderSaveReqVO updateReqVO);

    /**
     * 删除红正建材订单表

     *
     * @param id 编号
     */
    void deleteHzOrder(Long id);

    /**
    * 批量删除红正建材订单表

    *
    * @param ids 编号
    */
    void deleteHzOrderListByIds(List<Long> ids);

    /**
     * 获得红正建材订单表

     *
     * @param id 编号
     * @return 红正建材订单表

     */
    HzOrderDO getHzOrder(Long id);

    /**
     * 获得红正建材订单表
分页
     *
     * @param pageReqVO 分页查询
     * @return 红正建材订单表
分页
     */
    PageResult<HzOrderDO> getHzOrderPage(HzOrderPageReqVO pageReqVO);

    void updateStatus(@Valid HzOrderSaveReqVO updateReqVO);

    void approved(@Valid HzOrderSaveReqVO updateReqVO);

    Boolean rawOut(@Valid HzOrderDO order);

    Boolean productFinished(HzOrderDO order);

    Boolean productOut(HzOrderDO order);

    HashMap<String, Object> getStockByOrderId(HzOrderDO order);

    HashMap<String, Object> getOrderCount();


    List<HzOrderProgressDO> getProgress(Long orderId);

    Boolean rawCallback(List<HzOrderCallBackVO> vo);

    Boolean orderCallback(HzOrderCallBackVO vo);

    List<HzOrderDO> getOrderByStatus(Long status);

}