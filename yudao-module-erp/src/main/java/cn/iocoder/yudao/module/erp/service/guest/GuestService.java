package cn.iocoder.yudao.module.erp.service.guest;

import java.util.*;

import cn.iocoder.yudao.module.erp.controller.admin.product.vo.product.ErpProductRespVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.erp.controller.admin.guest.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.guest.GuestDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 红正客户 Service 接口
 *
 * @author liangchuang
 */
public interface GuestService {

    /**
     * 创建红正客户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGuest(@Valid GuestSaveReqVO createReqVO);

    /**
     * 更新红正客户
     *
     * @param updateReqVO 更新信息
     */
    void updateGuest(@Valid GuestSaveReqVO updateReqVO);

    /**
     * 删除红正客户
     *
     * @param id 编号
     */
    void deleteGuest(Long id);

    /**
    * 批量删除红正客户
    *
    * @param ids 编号
    */
    void deleteGuestListByIds(List<Long> ids);

    /**
     * 获得红正客户
     *
     * @param id 编号
     * @return 红正客户
     */
    GuestDO getGuest(Long id);

    /**
     * 获得红正客户分页
     *
     * @param pageReqVO 分页查询
     * @return 红正客户分页
     */
    PageResult<GuestDO> getGuestPage(GuestPageReqVO pageReqVO);
    /**
     * 获得指定状态的产品 VO 列表
     *
     * @param status 状态
     * @return 产品 VO 列表
     */
    List<GuestRespVO>  getGuestVOListByStatus(Integer status);

    List<GuestRespVO> getByType(Integer type);

    List<GuestContactDO> getContactByGuestId(Long id);

}