package cn.iocoder.yudao.module.erp.service.guest;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.util.collection.MapUtils;
import cn.iocoder.yudao.module.erp.controller.admin.product.vo.product.ErpProductRespVO;
import cn.iocoder.yudao.module.erp.dal.dataobject.product.ErpProductCategoryDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.product.ErpProductDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.product.ErpProductUnitDO;
import cn.iocoder.yudao.module.erp.dal.mysql.guest.GuestContactMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.erp.controller.admin.guest.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.guest.GuestDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.erp.dal.mysql.guest.GuestMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.*;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertSet;
import static cn.iocoder.yudao.module.erp.enums.ErrorCodeConstants.*;

/**
 * 红正客户 Service 实现类
 *
 * @author liangchuang
 */
@Service
@Validated
public class GuestServiceImpl implements GuestService {

    @Resource
    private GuestMapper guestMapper;


    @Resource
    private GuestContactMapper guestContactMapper;

    @Override
    public Long createGuest(GuestSaveReqVO createReqVO) {
        // 插入
        GuestDO guest = BeanUtils.toBean(createReqVO, GuestDO.class);
        guestMapper.insert(guest);

        if (createReqVO.getContactList() != null){
            List<GuestContactDO> contactList = createReqVO.getContactList();
            for (GuestContactDO contact : contactList) {
                contact.setGuestId(guest.getId());
                guestContactMapper.insert(contact);
            }
        }

        // 返回
        return guest.getId();
    }

    @Override
    public void updateGuest(GuestSaveReqVO updateReqVO) {
        // 校验存在
        validateGuestExists(updateReqVO.getId());
        // 更新
        GuestDO updateObj = BeanUtils.toBean(updateReqVO, GuestDO.class);
        guestMapper.updateById(updateObj);

        if (updateReqVO.getContactList() != null){
            guestContactMapper.deleteByGuestId(updateObj.getId());
            List<GuestContactDO> contactList = updateReqVO.getContactList();
            for (GuestContactDO contact : contactList) {
                contact.setGuestId(updateObj.getId());
                guestContactMapper.insert(contact);
            }
        }


    }

    @Override
    public void deleteGuest(Long id) {
        // 校验存在
        validateGuestExists(id);
        // 删除
        guestMapper.deleteById(id);
    }

    @Override
        public void deleteGuestListByIds(List<Long> ids) {
        // 删除
        guestMapper.deleteByIds(ids);
        }


    private void validateGuestExists(Long id) {
        if (guestMapper.selectById(id) == null) {
            throw exception(GUEST_NOT_EXISTS);
        }
    }

    @Override
    public GuestDO getGuest(Long id) {
        GuestDO guestDO = guestMapper.selectById(id);
        guestDO.setContactList(guestContactMapper.selectListByGuestId(id));
        return guestDO;
    }

    @Override
    public PageResult<GuestDO> getGuestPage(GuestPageReqVO pageReqVO) {
        PageResult<GuestDO> guestDOPageResult = guestMapper.selectPage(pageReqVO);
        List<GuestDO> list = guestDOPageResult.getList();

        list.forEach(guestDO -> {
            List<GuestContactDO> contactList = guestContactMapper.selectListByGuestId(guestDO.getId());
            guestDO.setContactList(contactList);
        });

        return guestDOPageResult;
    }

    @Override
    public List<GuestRespVO> getGuestVOListByStatus(Integer status) {
        List<GuestDO> list = guestMapper.selectListByStatus(status);

        return  BeanUtils.toBean(list, GuestRespVO.class);
    }

    @Override
    public List<GuestRespVO> getByType(Integer type) {


        List<GuestDO> list = guestMapper.selectListByType(type);
        return BeanUtils.toBean(list, GuestRespVO.class);
    }

    @Override
    public List<GuestContactDO> getContactByGuestId(Long id) {

     return    guestContactMapper.selectListByGuestId(id);
    }


}