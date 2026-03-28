package cn.iocoder.yudao.module.erp.dal.mysql.guest;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.erp.controller.admin.guest.vo.GuestContactDO;
import cn.iocoder.yudao.module.erp.controller.admin.guest.vo.GuestPageReqVO;
import cn.iocoder.yudao.module.erp.dal.dataobject.guest.GuestDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 红正客户 Mapper
 *
 * @author liangchuang
 */
@Mapper
public interface GuestContactMapper extends BaseMapperX<GuestContactDO> {



        @Select("SELECT * FROM erp_guest_contact WHERE guest_id = #{id}")
    List<GuestContactDO> selectListByGuestId(@Param("id") Long id);




       @Delete("DELETE FROM erp_guest_contact WHERE guest_id = #{id}")
        void deleteByGuestId(@Param("id") Long id);
}