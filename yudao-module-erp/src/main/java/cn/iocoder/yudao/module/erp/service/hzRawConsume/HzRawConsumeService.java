package cn.iocoder.yudao.module.erp.service.hzRawConsume;

import java.util.*;

import cn.iocoder.yudao.module.erp.controller.admin.productWeighingDetails.vo.WeighingImportRespVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.erp.controller.admin.hzRawConsume.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.hzRawConsume.HzRawConsumeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 订单原料消耗表
 Service 接口
 *
 * @author 超管
 */
public interface HzRawConsumeService {

    /**
     * 创建订单原料消耗表

     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createHzRawConsume(@Valid HzRawConsumeSaveReqVO createReqVO);

    /**
     * 更新订单原料消耗表

     *
     * @param updateReqVO 更新信息
     */
    void updateHzRawConsume(@Valid HzRawConsumeSaveReqVO updateReqVO);

    /**
     * 删除订单原料消耗表

     *
     * @param id 编号
     */
    void deleteHzRawConsume(Long id);

    /**
    * 批量删除订单原料消耗表

    *
    * @param ids 编号
    */
    void deleteHzRawConsumeListByIds(List<Long> ids);

    /**
     * 获得订单原料消耗表

     *
     * @param id 编号
     * @return 订单原料消耗表

     */
    HzRawConsumeDO getHzRawConsume(Long id);

    /**
     * 获得订单原料消耗表
分页
     *
     * @param pageReqVO 分页查询
     * @return 订单原料消耗表
分页
     */
    PageResult<HzRawConsumeDO> getHzRawConsumePage(HzRawConsumePageReqVO pageReqVO);

    Boolean importRawConsume(List<HzRawConsumeEx> list,Long orderId,Long warehouseId);

    List<HzRawConsumeDO> getByOrderId(Long orderId);

}