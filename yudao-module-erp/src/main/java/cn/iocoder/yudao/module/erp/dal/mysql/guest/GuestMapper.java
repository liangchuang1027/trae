package cn.iocoder.yudao.module.erp.dal.mysql.guest;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.erp.dal.dataobject.guest.GuestDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.product.ErpProductDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.erp.controller.admin.guest.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 红正客户 Mapper
 *
 * @author liangchuang
 */
@Mapper
public interface GuestMapper extends BaseMapperX<GuestDO> {

    default PageResult<GuestDO> selectPage(GuestPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GuestDO>()
                .eqIfPresent(GuestDO::getType, reqVO.getType())
                .likeIfPresent(GuestDO::getName, reqVO.getName())
                .eqIfPresent(GuestDO::getEmail, reqVO.getEmail())
                .eqIfPresent(GuestDO::getFax, reqVO.getFax())
                .eqIfPresent(GuestDO::getRemark, reqVO.getRemark())
                .eqIfPresent(GuestDO::getStatus, reqVO.getStatus())
                .eqIfPresent(GuestDO::getTaxNo, reqVO.getTaxNo())
                .eqIfPresent(GuestDO::getTaxPercent, reqVO.getTaxPercent())
                .likeIfPresent(GuestDO::getBankName, reqVO.getBankName())
                .eqIfPresent(GuestDO::getBankAccount, reqVO.getBankAccount())
                .eqIfPresent(GuestDO::getBankAddress, reqVO.getBankAddress())
                .betweenIfPresent(GuestDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GuestDO::getId));
    }

    default List<GuestDO> selectListByStatus(Integer status) {
        return selectList(GuestDO::getStatus, status);
    }

    @Select("SELECT * FROM erp_guest WHERE type = #{type} and status = 0")
    List<GuestDO> selectListByType(@Param("type") Integer type);
}