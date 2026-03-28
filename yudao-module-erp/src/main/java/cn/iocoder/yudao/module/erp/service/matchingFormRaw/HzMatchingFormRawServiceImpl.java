package cn.iocoder.yudao.module.erp.service.matchingFormRaw;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.erp.controller.admin.matchingFormRaw.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.matchingFormRaw.HzMatchingFormRawDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.erp.dal.mysql.matchingFormRaw.HzMatchingFormRawMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.erp.enums.ErrorCodeConstants.*;

/**
 * 配比模版原料 Service 实现类
 *
 * @author liangchuang
 */
@Service
@Validated
public class HzMatchingFormRawServiceImpl implements HzMatchingFormRawService {

    @Resource
    private HzMatchingFormRawMapper hzMatchingFormRawMapper;

    @Override
    public Long createHzMatchingFormRaw(HzMatchingFormRawSaveReqVO createReqVO) {
        // 插入
        HzMatchingFormRawDO hzMatchingFormRaw = BeanUtils.toBean(createReqVO, HzMatchingFormRawDO.class);
        hzMatchingFormRawMapper.insert(hzMatchingFormRaw);

        // 返回
        return hzMatchingFormRaw.getId();
    }

    @Override
    public void updateHzMatchingFormRaw(HzMatchingFormRawSaveReqVO updateReqVO) {
        // 校验存在
        validateHzMatchingFormRawExists(updateReqVO.getId());
        // 更新
        HzMatchingFormRawDO updateObj = BeanUtils.toBean(updateReqVO, HzMatchingFormRawDO.class);
        hzMatchingFormRawMapper.updateById(updateObj);
    }

    @Override
    public void deleteHzMatchingFormRaw(Long id) {
        // 校验存在
        validateHzMatchingFormRawExists(id);
        // 删除
        hzMatchingFormRawMapper.deleteById(id);
    }

    @Override
        public void deleteHzMatchingFormRawListByIds(List<Long> ids) {
        // 删除
        hzMatchingFormRawMapper.deleteByIds(ids);
        }


    private void validateHzMatchingFormRawExists(Long id) {
        if (hzMatchingFormRawMapper.selectById(id) == null) {
            throw exception(HZ_MATCHING_FORM_RAW_NOT_EXISTS);
        }
    }

    @Override
    public HzMatchingFormRawDO getHzMatchingFormRaw(Long id) {
        return hzMatchingFormRawMapper.selectById(id);
    }

    @Override
    public PageResult<HzMatchingFormRawDO> getHzMatchingFormRawPage(HzMatchingFormRawPageReqVO pageReqVO) {
        return hzMatchingFormRawMapper.selectPage(pageReqVO);
    }

}