package cn.iocoder.yudao.module.erp.service.productWeighingDetails;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.erp.controller.admin.productWeighingDetails.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.productWeighingDetails.HzProductWeighingDetailsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 成品出库过磅明细表
 Service 接口
 *
 * @author liangchuang
 */
public interface HzProductWeighingDetailsService {

    /**
     * 创建成品出库过磅明细表

     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createHzProductWeighingDetails(@Valid HzProductWeighingDetailsSaveReqVO createReqVO);

    /**
     * 更新成品出库过磅明细表

     *
     * @param updateReqVO 更新信息
     */
    void updateHzProductWeighingDetails(@Valid HzProductWeighingDetailsSaveReqVO updateReqVO);

    /**
     * 删除成品出库过磅明细表

     *
     * @param id 编号
     */
    void deleteHzProductWeighingDetails(Long id);

    /**
    * 批量删除成品出库过磅明细表

    *
    * @param ids 编号
    */
    void deleteHzProductWeighingDetailsListByIds(List<Long> ids);

    /**
     * 获得成品出库过磅明细表

     *
     * @param id 编号
     * @return 成品出库过磅明细表

     */
    HzProductWeighingDetailsDO getHzProductWeighingDetails(Long id);

    /**
     * 获得成品出库过磅明细表
分页
     *
     * @param pageReqVO 分页查询
     * @return 成品出库过磅明细表
分页
     */
    PageResult<HzProductWeighingDetailsDO> getHzProductWeighingDetailsPage(HzProductWeighingDetailsPageReqVO pageReqVO);

    WeighingImportRespVO importProductWeighingList(List<WeighingImportExcelVO> list, Boolean updateSupport,Long orderId);

    List<HzProductWeighingDetailsDO> getWeighingDetailsByOrderId(Long orderId);


    WeighingImportRespVO importPurchase(List<WeighingImportExcelVO> list);

    WeighingImportRespVO productOut(List<WeighingImportExcelVO> list);


    int checkOrder(Long orderId, Long type);

}