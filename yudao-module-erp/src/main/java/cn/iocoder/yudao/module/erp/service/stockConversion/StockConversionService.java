package cn.iocoder.yudao.module.erp.service.stockConversion;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.erp.controller.admin.stockConversion.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.stockConversion.StockConversionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 在库形态转换 Service 接口
 *
 * @author liangchuang
 */
public interface StockConversionService {

    /**
     * 创建在库形态转换
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStockConversion(@Valid StockConversionSaveReqVO createReqVO);

    /**
     * 更新在库形态转换
     *
     * @param updateReqVO 更新信息
     */
    void updateStockConversion(@Valid StockConversionSaveReqVO updateReqVO);

    /**
     * 删除在库形态转换
     *
     * @param id 编号
     */
    void deleteStockConversion(Long id);

    /**
    * 批量删除在库形态转换
    *
    * @param ids 编号
    */
    void deleteStockConversionListByIds(List<Long> ids);

    /**
     * 获得在库形态转换
     *
     * @param id 编号
     * @return 在库形态转换
     */
    StockConversionDO getStockConversion(Long id);

    /**
     * 获得在库形态转换分页
     *
     * @param pageReqVO 分页查询
     * @return 在库形态转换分页
     */
    PageResult<StockConversionDO> getStockConversionPage(StockConversionPageReqVO pageReqVO);

//    void review(@Valid StockConversionSaveReqVO updateReqVO);

}