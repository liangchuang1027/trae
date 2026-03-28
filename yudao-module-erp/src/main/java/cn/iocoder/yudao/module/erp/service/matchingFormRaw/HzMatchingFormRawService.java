package cn.iocoder.yudao.module.erp.service.matchingFormRaw;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.erp.controller.admin.matchingFormRaw.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.matchingFormRaw.HzMatchingFormRawDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 配比模版原料 Service 接口
 *
 * @author liangchuang
 */
public interface HzMatchingFormRawService {

    /**
     * 创建配比模版原料
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createHzMatchingFormRaw(@Valid HzMatchingFormRawSaveReqVO createReqVO);

    /**
     * 更新配比模版原料
     *
     * @param updateReqVO 更新信息
     */
    void updateHzMatchingFormRaw(@Valid HzMatchingFormRawSaveReqVO updateReqVO);

    /**
     * 删除配比模版原料
     *
     * @param id 编号
     */
    void deleteHzMatchingFormRaw(Long id);

    /**
    * 批量删除配比模版原料
    *
    * @param ids 编号
    */
    void deleteHzMatchingFormRawListByIds(List<Long> ids);

    /**
     * 获得配比模版原料
     *
     * @param id 编号
     * @return 配比模版原料
     */
    HzMatchingFormRawDO getHzMatchingFormRaw(Long id);

    /**
     * 获得配比模版原料分页
     *
     * @param pageReqVO 分页查询
     * @return 配比模版原料分页
     */
    PageResult<HzMatchingFormRawDO> getHzMatchingFormRawPage(HzMatchingFormRawPageReqVO pageReqVO);

}