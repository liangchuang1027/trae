package cn.iocoder.yudao.module.erp.service.hzMatchingForm;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.erp.controller.admin.matchingFormRaw.vo.HzMatchingFormRawSaveReqVO;
import cn.iocoder.yudao.module.erp.dal.dataobject.order.HzOrderDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.product.ErpProductCategoryDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.product.ErpProductDO;
import cn.iocoder.yudao.module.erp.dal.mysql.product.ErpProductCategoryMapper;
import cn.iocoder.yudao.module.erp.dal.mysql.product.ErpProductMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import cn.iocoder.yudao.module.erp.controller.admin.hzMatchingForm.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.hzMatchingForm.HzMatchingFormDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.matchingFormRaw.HzMatchingFormRawDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.erp.dal.mysql.hzMatchingForm.HzMatchingFormMapper;
import cn.iocoder.yudao.module.erp.dal.mysql.matchingFormRaw.HzMatchingFormRawMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.erp.enums.ErrorCodeConstants.*;

/**
 * 配比模版 Service 实现类
 *
 * @author liangchuang
 */
@Service
@Validated
public class HzMatchingFormServiceImpl implements HzMatchingFormService {

    @Resource
    private HzMatchingFormMapper hzMatchingFormMapper;
    @Resource
    private HzMatchingFormRawMapper hzMatchingFormRawMapper;
    @Resource
    private ErpProductMapper productMapper;

    @Resource
    private ErpProductCategoryMapper erpProductCategoryMapper;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createHzMatchingForm(HzMatchingFormSaveReqVO createReqVO) {
        // 插入
        HzMatchingFormDO hzMatchingForm = BeanUtils.toBean(createReqVO, HzMatchingFormDO.class);
       // 设置默认状态开启
        hzMatchingForm.setStatus(0);
        //获取创建者
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        if (loginUser != null) {
            hzMatchingForm.setCreator(loginUser.getInfo().get("nickname"));
        }
        hzMatchingFormMapper.insert(hzMatchingForm);
        //插入模版多条原料子表
        List<HzMatchingFormRawSaveReqVO> raws = createReqVO.getRaws();
        raws.forEach(raw -> {
            raw.setMatchingFormId(hzMatchingForm.getId());
            BigDecimal divide = raw.getRatio().divide(BigDecimal.valueOf(100));
            raw.setRatio(divide);

        });
        List<HzMatchingFormRawDO> beans = BeanUtils.toBean(raws, HzMatchingFormRawDO.class);
        hzMatchingFormRawMapper.insertBatch(beans);

        // 返回
        return hzMatchingForm.getId();
    }

    @Override
    public void updateHzMatchingForm(HzMatchingFormSaveReqVO updateReqVO) {
        // 校验存在
        validateHzMatchingFormExists(updateReqVO.getId());
        // 更新
        HzMatchingFormDO updateObj = BeanUtils.toBean(updateReqVO, HzMatchingFormDO.class);
        hzMatchingFormMapper.updateById(updateObj);
        //插入模版多条原料子表
        List<HzMatchingFormRawDO> beans = BeanUtils.toBean(updateReqVO.getRaws(), HzMatchingFormRawDO.class);
        hzMatchingFormRawMapper.updateBatch(beans);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteHzMatchingForm(Long id) {
        // 校验存在
        validateHzMatchingFormExists(id);
        // 删除
        hzMatchingFormMapper.deleteById(id);

        // 删除子表
        deleteHzMatchingFormRawByMatchingFormId(id);
    }

    @Override
        @Transactional(rollbackFor = Exception.class)
    public void deleteHzMatchingFormListByIds(List<Long> ids) {
        // 删除
        hzMatchingFormMapper.deleteByIds(ids);
    
    // 删除子表
           deleteHzMatchingFormRawByMatchingFormIds(ids);
    }


    private void validateHzMatchingFormExists(Long id) {
        if (hzMatchingFormMapper.selectById(id) == null) {
            throw exception(HZ_MATCHING_FORM_NOT_EXISTS);
        }
    }

    @Override
    public HzMatchingFormDO getHzMatchingForm(Long id) {
        HzMatchingFormDO hzMatchingFormDO = hzMatchingFormMapper.selectById(id);
        List<HzMatchingFormRawDO> hzMatchingFormRawDOS = hzMatchingFormRawMapper.selectByMatchingFormId(id);
        hzMatchingFormRawDOS.forEach(hzMatchingFormRawDO ->  {
            hzMatchingFormRawDO.setRatio(hzMatchingFormRawDO.getRatio().multiply(BigDecimal.valueOf(100)));
        });
        hzMatchingFormDO.setRaws(hzMatchingFormRawDOS);
        return hzMatchingFormDO;
    }

    @Override
    public PageResult<HzMatchingFormDO> getHzMatchingFormPage(HzMatchingFormPageReqVO pageReqVO) {
        PageResult<HzMatchingFormDO> hzMatchingFormDOPageResult = hzMatchingFormMapper.selectPage(pageReqVO);
        hzMatchingFormDOPageResult.getList().forEach(hzMatchingFormDO -> {
            ErpProductCategoryDO erpProductCategoryDO = erpProductCategoryMapper.selectById(hzMatchingFormDO.getProductCategoryId());
            hzMatchingFormDO.setProductCategory(erpProductCategoryDO);

        });
        return hzMatchingFormDOPageResult;
    }

    @Override
    public List<HzMatchingFormDO>  selectByProductCategoryId(Long productCategoryId) {
        List<HzMatchingFormDO> hzMatchingFormDOList = hzMatchingFormMapper.selectByProductCategoryId(productCategoryId);
//        if(CollectionUtils.isAnyEmpty(hzMatchingFormDOList)){
//            return null;
//        }
        hzMatchingFormDOList.forEach(hzMatchingFormDO -> {
            List<HzMatchingFormRawDO> hzMatchingFormRawDOS = hzMatchingFormRawMapper.selectByMatchingFormId(hzMatchingFormDO.getId());
            hzMatchingFormRawDOS.forEach(hzMatchingFormRawDO ->  {
                hzMatchingFormRawDO.setRatio(hzMatchingFormRawDO.getRatio().multiply(BigDecimal.valueOf(100)));
            });
            hzMatchingFormDO.setRaws(hzMatchingFormRawDOS);

        });
        return hzMatchingFormDOList;
    }


    private void validateHzMatchingFormRawExists(Long id) {
        if (hzMatchingFormRawMapper.selectById(id) == null) {
            throw exception(HZ_MATCHING_FORM_RAW_NOT_EXISTS);
        }
    }

    private void deleteHzMatchingFormRawByMatchingFormId(Long orderFormId) {
        hzMatchingFormRawMapper.deleteByMatchingFormId(orderFormId);
    }
//
	private void deleteHzMatchingFormRawByMatchingFormIds(List<Long> orderFormIds) {
        hzMatchingFormRawMapper.deleteByMatchingFormIds(orderFormIds);
	}

}