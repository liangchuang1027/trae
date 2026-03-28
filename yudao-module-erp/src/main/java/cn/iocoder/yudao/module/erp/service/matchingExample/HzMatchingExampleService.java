package cn.iocoder.yudao.module.erp.service.matchingExample;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.erp.controller.admin.matchingExample.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.matchingExample.HzMatchingExampleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 配比实例 Service 接口
 *
 * @author liangchuang
 */
public interface HzMatchingExampleService {

    /**
     * 创建配比实例
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createHzMatchingExample(@Valid HzMatchingExampleSaveReqVO createReqVO);

    /**
     * 更新配比实例
     *
     * @param updateReqVO 更新信息
     */
    void updateHzMatchingExample(@Valid HzMatchingExampleSaveReqVO updateReqVO);

    /**
     * 删除配比实例
     *
     * @param id 编号
     */
    void deleteHzMatchingExample(Long id);

    /**
    * 批量删除配比实例
    *
    * @param ids 编号
    */
    void deleteHzMatchingExampleListByIds(List<Long> ids);

    /**
     * 获得配比实例
     *
     * @param id 编号
     * @return 配比实例
     */
    HzMatchingExampleDO getHzMatchingExample(Long id);

    /**
     * 获得配比实例分页
     *
     * @param pageReqVO 分页查询
     * @return 配比实例分页
     */
    PageResult<HzMatchingExampleDO> getHzMatchingExamplePage(HzMatchingExamplePageReqVO pageReqVO);

}