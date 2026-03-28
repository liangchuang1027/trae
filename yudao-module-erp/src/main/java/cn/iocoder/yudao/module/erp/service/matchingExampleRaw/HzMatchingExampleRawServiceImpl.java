package cn.iocoder.yudao.module.erp.service.matchingExampleRaw;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.erp.dal.dataobject.stock.ErpStockDO;
import cn.iocoder.yudao.module.erp.service.stock.ErpStockService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.erp.controller.admin.matchingExampleRaw.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.matchingExampleRaw.HzMatchingExampleRawDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.erp.dal.mysql.matchingExampleRaw.HzMatchingExampleRawMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.erp.enums.ErrorCodeConstants.*;

/**
 * 配比原料实例 Service 实现类
 *
 * @author liangchuang
 */
@Service
@Validated
public class HzMatchingExampleRawServiceImpl implements HzMatchingExampleRawService {

    @Resource
    private HzMatchingExampleRawMapper hzMatchingExampleRawMapper;

    @Override
    public Long createHzMatchingExampleRaw(HzMatchingExampleRawSaveReqVO createReqVO) {
        // 插入
        HzMatchingExampleRawDO hzMatchingExampleRaw = BeanUtils.toBean(createReqVO, HzMatchingExampleRawDO.class);
        hzMatchingExampleRawMapper.insert(hzMatchingExampleRaw);

        // 返回
        return hzMatchingExampleRaw.getId();
    }

    @Override
    public void updateHzMatchingExampleRaw(HzMatchingExampleRawSaveReqVO updateReqVO) {
        // 校验存在
        validateHzMatchingExampleRawExists(updateReqVO.getId());
        // 更新
        HzMatchingExampleRawDO updateObj = BeanUtils.toBean(updateReqVO, HzMatchingExampleRawDO.class);
        hzMatchingExampleRawMapper.updateById(updateObj);
    }

    @Override
    public void deleteHzMatchingExampleRaw(Long id) {
        // 校验存在
        validateHzMatchingExampleRawExists(id);
        // 删除
        hzMatchingExampleRawMapper.deleteById(id);
    }

    @Override
        public void deleteHzMatchingExampleRawListByIds(List<Long> ids) {
        // 删除
        hzMatchingExampleRawMapper.deleteByIds(ids);
        }


    private void validateHzMatchingExampleRawExists(Long id) {
        if (hzMatchingExampleRawMapper.selectById(id) == null) {
            throw exception(HZ_MATCHING_EXAMPLE_RAW_NOT_EXISTS);
        }
    }

    @Override
    public HzMatchingExampleRawDO getHzMatchingExampleRaw(Long id) {
        return hzMatchingExampleRawMapper.selectById(id);
    }

    @Override
    public PageResult<HzMatchingExampleRawDO> getHzMatchingExampleRawPage(HzMatchingExampleRawPageReqVO pageReqVO) {
        return hzMatchingExampleRawMapper.selectPage(pageReqVO);
    }

    @Override
    public List<HzMatchingExampleRawDO> getByOrderId(Long orderId) {

        return hzMatchingExampleRawMapper.getByOrderId(orderId);
    }

}