package cn.iocoder.yudao.module.erp.service.matchingExampleRaw;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.erp.controller.admin.matchingExampleRaw.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.matchingExampleRaw.HzMatchingExampleRawDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 配比原料实例 Service 接口
 *
 * @author liangchuang
 */
public interface HzMatchingExampleRawService {

    /**
     * 创建配比原料实例
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createHzMatchingExampleRaw(@Valid HzMatchingExampleRawSaveReqVO createReqVO);

    /**
     * 更新配比原料实例
     *
     * @param updateReqVO 更新信息
     */
    void updateHzMatchingExampleRaw(@Valid HzMatchingExampleRawSaveReqVO updateReqVO);

    /**
     * 删除配比原料实例
     *
     * @param id 编号
     */
    void deleteHzMatchingExampleRaw(Long id);

    /**
    * 批量删除配比原料实例
    *
    * @param ids 编号
    */
    void deleteHzMatchingExampleRawListByIds(List<Long> ids);

    /**
     * 获得配比原料实例
     *
     * @param id 编号
     * @return 配比原料实例
     */
    HzMatchingExampleRawDO getHzMatchingExampleRaw(Long id);

    /**
     * 获得配比原料实例分页
     *
     * @param pageReqVO 分页查询
     * @return 配比原料实例分页
     */
    PageResult<HzMatchingExampleRawDO> getHzMatchingExampleRawPage(HzMatchingExampleRawPageReqVO pageReqVO);

    List<HzMatchingExampleRawDO> getByOrderId(Long orderId);

}