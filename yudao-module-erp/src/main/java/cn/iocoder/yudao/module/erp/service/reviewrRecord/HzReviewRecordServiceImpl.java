package cn.iocoder.yudao.module.erp.service.reviewrRecord;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.erp.controller.admin.reviewrRecord.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.reviewrRecord.HzReviewRecordDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.erp.dal.mysql.reviewrRecord.HzReviewRecordMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.erp.enums.ErrorCodeConstants.*;

/**
 * 红正建材审核记录 Service 实现类
 *
 * @author liangchuang
 */
@Service
@Validated
public class HzReviewRecordServiceImpl implements HzReviewRecordService {

    @Resource
    private HzReviewRecordMapper hzReviewRecordMapper;

    @Override
    public Long createHzReviewRecord(HzReviewRecordSaveReqVO createReqVO) {
        // 插入
        HzReviewRecordDO hzReviewRecord = BeanUtils.toBean(createReqVO, HzReviewRecordDO.class);
        hzReviewRecordMapper.insert(hzReviewRecord);

        // 返回
        return hzReviewRecord.getId();
    }

    @Override
    public void updateHzReviewRecord(HzReviewRecordSaveReqVO updateReqVO) {
        // 校验存在
        validateHzReviewRecordExists(updateReqVO.getId());
        // 更新
        HzReviewRecordDO updateObj = BeanUtils.toBean(updateReqVO, HzReviewRecordDO.class);
        hzReviewRecordMapper.updateById(updateObj);
    }

    @Override
    public void deleteHzReviewRecord(Long id) {
        // 校验存在
        validateHzReviewRecordExists(id);
        // 删除
        hzReviewRecordMapper.deleteById(id);
    }

    @Override
        public void deleteHzReviewRecordListByIds(List<Long> ids) {
        // 删除
        hzReviewRecordMapper.deleteByIds(ids);
        }


    private void validateHzReviewRecordExists(Long id) {
        if (hzReviewRecordMapper.selectById(id) == null) {
            throw exception(HZ_REVIEW_RECORD_NOT_EXISTS);
        }
    }

    @Override
    public HzReviewRecordDO getHzReviewRecord(Long id) {
        return hzReviewRecordMapper.selectById(id);
    }

    @Override
    public PageResult<HzReviewRecordDO> getHzReviewRecordPage(HzReviewRecordPageReqVO pageReqVO) {
        return hzReviewRecordMapper.selectPage(pageReqVO);
    }

    @Override
    public HzReviewRecordDO getByOrderId(Long id) {
        return hzReviewRecordMapper.selectByOrderId(id);
    }

    @Override
    public HzReviewRecordDO getByPurchaseId(Long id) {
        return hzReviewRecordMapper.getByPurchaseId(id);


    }

}