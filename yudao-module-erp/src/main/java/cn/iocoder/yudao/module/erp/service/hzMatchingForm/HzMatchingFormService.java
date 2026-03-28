package cn.iocoder.yudao.module.erp.service.hzMatchingForm;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.erp.controller.admin.hzMatchingForm.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.hzMatchingForm.HzMatchingFormDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.matchingFormRaw.HzMatchingFormRawDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 配比模版 Service 接口
 *
 * @author liangchuang
 */
public interface HzMatchingFormService {

    /**
     * 创建配比模版
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createHzMatchingForm(@Valid HzMatchingFormSaveReqVO createReqVO);

    /**
     * 更新配比模版
     *
     * @param updateReqVO 更新信息
     */
    void updateHzMatchingForm(@Valid HzMatchingFormSaveReqVO updateReqVO);

    /**
     * 删除配比模版
     *
     * @param id 编号
     */
    void deleteHzMatchingForm(Long id);

    /**
    * 批量删除配比模版
    *
    * @param ids 编号
    */
    void deleteHzMatchingFormListByIds(List<Long> ids);

    /**
     * 获得配比模版
     *
     * @param id 编号
     * @return 配比模版
     */
    HzMatchingFormDO getHzMatchingForm(Long id);

    /**
     * 获得配比模版分页
     *
     * @param pageReqVO 分页查询
     * @return 配比模版分页
     */
    PageResult<HzMatchingFormDO> getHzMatchingFormPage(HzMatchingFormPageReqVO pageReqVO);

    List<HzMatchingFormDO>  selectByProductCategoryId(Long productCategoryId);


    // ==================== 子表（配比模版原料） ====================
//
//    /**
//     * 获得配比模版原料分页
//     *
//     * @param pageReqVO 分页查询
//     * @param orderFormId 配比模版表id
//     * @return 配比模版原料分页
//     */
//    PageResult<HzMatchingFormRawDO> getHzMatchingFormRawPage(PageParam pageReqVO, Integer orderFormId);
//
//    /**
//     * 创建配比模版原料
//     *
//     * @param hzMatchingFormRaw 创建信息
//     * @return 编号
//     */
//    Long createHzMatchingFormRaw(@Valid HzMatchingFormRawDO hzMatchingFormRaw);
//
//    /**
//     * 更新配比模版原料
//     *
//     * @param hzMatchingFormRaw 更新信息
//     */
//    void updateHzMatchingFormRaw(@Valid HzMatchingFormRawDO hzMatchingFormRaw);
//
//    /**
//     * 删除配比模版原料
//     *
//     * @param id 编号
//     */
//    void deleteHzMatchingFormRaw(Long id);
//
//    /**
//    * 批量删除配比模版原料
//    *
//    * @param ids 编号
//    */
//    void deleteHzMatchingFormRawListByIds(List<Long> ids);
//
//	/**
//	 * 获得配比模版原料
//	 *
//	 * @param id 编号
//     * @return 配比模版原料
//	 */
//    HzMatchingFormRawDO getHzMatchingFormRaw(Long id);

}