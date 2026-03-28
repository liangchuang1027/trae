package cn.iocoder.yudao.module.erp.service.reviewrRecord;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.erp.controller.admin.reviewrRecord.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.reviewrRecord.HzReviewRecordDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 红正建材审核记录 Service 接口
 *
 * @author liangchuang
 */
public interface HzReviewRecordService {

    /**
     * 创建红正建材审核记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createHzReviewRecord(@Valid HzReviewRecordSaveReqVO createReqVO);

    /**
     * 更新红正建材审核记录
     *
     * @param updateReqVO 更新信息
     */
    void updateHzReviewRecord(@Valid HzReviewRecordSaveReqVO updateReqVO);

    /**
     * 删除红正建材审核记录
     *
     * @param id 编号
     */
    void deleteHzReviewRecord(Long id);

    /**
    * 批量删除红正建材审核记录
    *
    * @param ids 编号
    */
    void deleteHzReviewRecordListByIds(List<Long> ids);

    /**
     * 获得红正建材审核记录
     *
     * @param id 编号
     * @return 红正建材审核记录
     */
    HzReviewRecordDO getHzReviewRecord(Long id);

    /**
     * 获得红正建材审核记录分页
     *
     * @param pageReqVO 分页查询
     * @return 红正建材审核记录分页
     */
    PageResult<HzReviewRecordDO> getHzReviewRecordPage(HzReviewRecordPageReqVO pageReqVO);

    HzReviewRecordDO getByOrderId(Long id);

    HzReviewRecordDO getByPurchaseId(Long id);

}