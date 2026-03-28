package cn.iocoder.yudao.module.erp.dal.mysql.project;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.erp.dal.dataobject.product.ErpProductDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.project.HzProjectDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.erp.controller.admin.project.vo.*;

/**
 * 红正建材项目 Mapper
 *
 * @author liangchuang
 */
@Mapper
public interface HzProjectMapper extends BaseMapperX<HzProjectDO> {

    default PageResult<HzProjectDO> selectPage(HzProjectPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<HzProjectDO>()
                .likeIfPresent(HzProjectDO::getProjectName, reqVO.getProjectName())
                .likeIfPresent(HzProjectDO::getCustomer, reqVO.getCustomer())
                .eqIfPresent(HzProjectDO::getStatus, reqVO.getStatus())
                .orderByDesc(HzProjectDO::getId));
    }
    default List<HzProjectDO> selectListByStatus(Integer status) {
        return selectList(HzProjectDO::getStatus, status);
    }
}