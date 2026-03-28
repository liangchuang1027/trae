package cn.iocoder.yudao.module.erp.service.project;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.erp.controller.admin.project.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.project.HzProjectDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 红正建材项目 Service 接口
 *
 * @author liangchuang
 */
public interface HzProjectService {

    /**
     * 创建红正建材项目
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createHzProject(@Valid HzProjectSaveReqVO createReqVO);

    /**
     * 更新红正建材项目
     *
     * @param updateReqVO 更新信息
     */
    void updateHzProject(@Valid HzProjectSaveReqVO updateReqVO);

    /**
     * 删除红正建材项目
     *
     * @param id 编号
     */
    void deleteHzProject(Long id);

    /**
    * 批量删除红正建材项目
    *
    * @param ids 编号
    */
    void deleteHzProjectListByIds(List<Long> ids);

    /**
     * 获得红正建材项目
     *
     * @param id 编号
     * @return 红正建材项目
     */
    HzProjectDO getHzProject(Long id);

    /**
     * 获得红正建材项目分页
     *
     * @param pageReqVO 分页查询
     * @return 红正建材项目分页
     */
    PageResult<HzProjectDO> getHzProjectPage(HzProjectPageReqVO pageReqVO);

    List<HzProjectDO> getProjectVOListByStatus(Integer status);
}