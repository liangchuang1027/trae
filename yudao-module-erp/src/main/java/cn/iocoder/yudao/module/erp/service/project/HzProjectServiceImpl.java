package cn.iocoder.yudao.module.erp.service.project;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.erp.controller.admin.product.vo.product.ErpProductRespVO;
import cn.iocoder.yudao.module.erp.dal.dataobject.guest.GuestDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.order.HzOrderDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.product.ErpProductDO;
import cn.iocoder.yudao.module.erp.dal.mysql.guest.GuestMapper;
import cn.iocoder.yudao.module.erp.dal.mysql.order.HzOrderMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.erp.controller.admin.project.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.project.HzProjectDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.erp.dal.mysql.project.HzProjectMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.erp.enums.ErrorCodeConstants.*;

/**
 * 红正建材项目 Service 实现类
 *
 * @author liangchuang
 */
@Service
@Validated
public class HzProjectServiceImpl implements HzProjectService {
    @Resource
    private HzOrderMapper hzOrderMapper;
    @Resource
    private HzProjectMapper hzProjectMapper;
    @Resource
    private GuestMapper guestMapper;
    @Override
    public Long createHzProject(HzProjectSaveReqVO createReqVO) {
        // 插入
        HzProjectDO hzProject = BeanUtils.toBean(createReqVO, HzProjectDO.class);
        hzProject.setStatus(0);
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        // 插入
        if (loginUser != null) {
            hzProject.setCreator(loginUser.getInfo().get("nickname"));
            hzProject.setUpdater(loginUser.getInfo().get("nickname"));
        }
        hzProjectMapper.insert(hzProject);

        // 返回
        return hzProject.getId();
    }

    @Override
    public void updateHzProject(HzProjectSaveReqVO updateReqVO) {
        // 校验存在
        validateHzProjectExists(updateReqVO.getId());
        // 更新
        HzProjectDO updateObj = BeanUtils.toBean(updateReqVO, HzProjectDO.class);
        hzProjectMapper.updateById(updateObj);
    }

    @Override
    public void deleteHzProject(Long id) {
        // 校验存在
        validateHzProjectExists(id);
        // 删除
        hzProjectMapper.deleteById(id);
    }

    @Override
        public void deleteHzProjectListByIds(List<Long> ids) {
        // 删除
        hzProjectMapper.deleteByIds(ids);
        }


    private void validateHzProjectExists(Long id) {
        if (hzProjectMapper.selectById(id) == null) {
            throw exception(HZ_PROJECT_NOT_EXISTS);
        }
    }

    @Override
    public HzProjectDO getHzProject(Long id) {
        return hzProjectMapper.selectById(id);
    }

    @Override
    public PageResult<HzProjectDO> getHzProjectPage(HzProjectPageReqVO pageReqVO) {
        PageResult<HzProjectDO> hzProjectDOPageResult = hzProjectMapper.selectPage(pageReqVO);
        hzProjectDOPageResult.getList().forEach(hzProjectDO -> {
            List<Long> longList = Arrays.stream(hzProjectDO.getCustomer().split(","))
                    .map(String::trim)
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            hzProjectDO.setGuesyList(guestMapper.selectList(GuestDO::getId, longList)) ;

            hzProjectDO.setOrderCount(hzOrderMapper.selectCount(HzOrderDO::getProjectId, hzProjectDO.getId()));
        });

        return hzProjectDOPageResult;
    }

    @Override
    public List<HzProjectDO> getProjectVOListByStatus(Integer status) {

        List<HzProjectDO> hzProjectDOS = hzProjectMapper.selectListByStatus(status);
        hzProjectDOS.forEach(hzProjectDO -> {
            List<Long> longList = Arrays.stream(hzProjectDO.getCustomer().split(","))
                    .map(String::trim)
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            hzProjectDO.setGuesyList(guestMapper.selectList(GuestDO::getId, longList)) ;
        });

        return  hzProjectDOS;
    }
}