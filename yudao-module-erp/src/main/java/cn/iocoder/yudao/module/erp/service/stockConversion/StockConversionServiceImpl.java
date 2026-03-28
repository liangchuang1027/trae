package cn.iocoder.yudao.module.erp.service.stockConversion;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.enums.OrderStatus;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.erp.dal.dataobject.order.HzOrderDO;
import cn.iocoder.yudao.module.erp.enums.stock.ErpStockRecordBizTypeEnum;
import cn.iocoder.yudao.module.erp.service.stock.ErpStockRecordServiceImpl;
import cn.iocoder.yudao.module.erp.service.stock.bo.ErpStockRecordCreateReqBO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.erp.controller.admin.stockConversion.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.stockConversion.StockConversionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.erp.dal.mysql.stockConversion.StockConversionMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.erp.enums.ErrorCodeConstants.*;

/**
 * 在库形态转换 Service 实现类
 *
 * @author liangchuang
 */
@Service
@Validated
public class StockConversionServiceImpl implements StockConversionService {
    @Resource
    private ErpStockRecordServiceImpl erpStockRecordServiceImpl;
    @Resource
    private StockConversionMapper stockConversionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createStockConversion(StockConversionSaveReqVO createReqVO) {
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();

        // 插入
        StockConversionDO stockConversion = BeanUtils.toBean(createReqVO, StockConversionDO.class);
        stockConversion.setParentId(0L);
        if (loginUser != null) {
            stockConversion.setCreator(loginUser.getInfo().get("nickname"));
        }
        //插入子级
        stockConversionMapper.insert(stockConversion);
        createReqVO.getChildList().forEach(child -> {
            StockConversionDO stockConversionDO = BeanUtils.toBean(child, StockConversionDO.class);
            stockConversionDO.setParentId(stockConversion.getId());
            stockConversionMapper.insert(stockConversionDO);
        });

        //库存明细出入库+增减库存
            StockConversionDO stockConversionDO = stockConversionMapper.selectById(stockConversion.getId());

            //创建主体成品出库
            ErpStockRecordCreateReqBO erpStockRecordCreateReqBO = new ErpStockRecordCreateReqBO();
            erpStockRecordCreateReqBO.setProductId(stockConversionDO.getProductId());
            erpStockRecordCreateReqBO.setWarehouseId(createReqVO.getWarehouseId());
            erpStockRecordCreateReqBO.setCount(stockConversionDO.getCount().negate());
            erpStockRecordCreateReqBO.setBizType(ErpStockRecordBizTypeEnum.ORDER_CONVERSION_OUT.getType());
            erpStockRecordCreateReqBO.setBizId(stockConversion.getId());
            erpStockRecordCreateReqBO.setBizItemId(stockConversion.getId());
            erpStockRecordCreateReqBO.setBizNo("1");
            erpStockRecordServiceImpl.createStockRecord(erpStockRecordCreateReqBO);


            //创建转换后产品入库
            List<StockConversionDO> stockConversionDOS = stockConversionMapper.selectByParentId(stockConversion.getId());

            stockConversionDOS.forEach(stock -> {
                ErpStockRecordCreateReqBO erpStockRecord = new ErpStockRecordCreateReqBO();
                erpStockRecord.setProductId(stock.getProductId());
                erpStockRecord.setWarehouseId(stock.getWarehouseId());
                erpStockRecord.setCount(stock.getCount());
                erpStockRecord.setBizType(ErpStockRecordBizTypeEnum.ORDER_CONVERSION_IN.getType());
                erpStockRecord.setBizId(stockConversion.getId());
                erpStockRecord.setBizItemId(stockConversion.getId());
                erpStockRecord.setBizNo("1");
                erpStockRecordServiceImpl.createStockRecord(erpStockRecord);
            });

        // 返回
        return stockConversion.getId();
    }

    @Override
    public void updateStockConversion(StockConversionSaveReqVO updateReqVO) {
        // 校验存在
        validateStockConversionExists(updateReqVO.getId());
        // 更新
        StockConversionDO updateObj = BeanUtils.toBean(updateReqVO, StockConversionDO.class);
        stockConversionMapper.updateById(updateObj);
        //删除原子级
        stockConversionMapper.deleteByParentId(updateReqVO.getId());

        updateReqVO.getChildList().forEach(child -> {
            StockConversionDO stockConversionDO = BeanUtils.toBean(child, StockConversionDO.class);
            stockConversionDO.setParentId(updateObj.getId());
            stockConversionMapper.insert(stockConversionDO);
        });
    }

    @Override
    public void deleteStockConversion(Long id) {
        // 校验存在
        validateStockConversionExists(id);
        // 删除
        stockConversionMapper.deleteById(id);
    }

    @Override
        public void deleteStockConversionListByIds(List<Long> ids) {
        // 删除
        stockConversionMapper.deleteByIds(ids);
        }


    private void validateStockConversionExists(Long id) {
        if (stockConversionMapper.selectById(id) == null) {
            throw exception(STOCK_CONVERSION_NOT_EXISTS);
        }
    }

    @Override
    public StockConversionDO getStockConversion(Long id) {

        StockConversionDO stockConversionDO = stockConversionMapper.selectById(id);
        stockConversionDO.setChildList(stockConversionMapper.selectByParentId(stockConversionDO.getId()));

        return stockConversionDO;
    }

    @Override
    public PageResult<StockConversionDO> getStockConversionPage(StockConversionPageReqVO pageReqVO) {
        PageResult<StockConversionDO> stockConversionDOPageResult = stockConversionMapper.selectPage(pageReqVO);
        stockConversionDOPageResult.getList().forEach(stockConversionDO -> {
            stockConversionDO.setChildList(stockConversionMapper.selectByParentId(stockConversionDO.getId()));
        });
        return stockConversionDOPageResult;
    }

//    @Override
//    public void review(StockConversionSaveReqVO updateReqVO) {
//        // 校验存在
//        validateStockConversionExists(updateReqVO.getId());
//
//        //审核通过逻辑
//       if (updateReqVO.getStatus() == 2){
//            StockConversionDO stockConversionDO = stockConversionMapper.selectById(updateReqVO.getId());
//
//            //创建主体成品出库
//            ErpStockRecordCreateReqBO erpStockRecordCreateReqBO = new ErpStockRecordCreateReqBO();
//            erpStockRecordCreateReqBO.setProductId(stockConversionDO.getProductId());
//            erpStockRecordCreateReqBO.setWarehouseId(7L);
//            erpStockRecordCreateReqBO.setCount(stockConversionDO.getCount().negate());
//            erpStockRecordCreateReqBO.setBizType(ErpStockRecordBizTypeEnum.ORDER_CONVERSION_OUT.getType());
//            erpStockRecordCreateReqBO.setBizId(updateReqVO.getId());
//            erpStockRecordCreateReqBO.setBizItemId(updateReqVO.getId());
//            erpStockRecordCreateReqBO.setBizNo("1");
//            erpStockRecordServiceImpl.createStockRecord(erpStockRecordCreateReqBO);
//
//
//            //创建转换后产品入库
//            List<StockConversionDO> stockConversionDOS = stockConversionMapper.selectByParentId(updateReqVO.getId());
//
//            stockConversionDOS.forEach(stockConversion -> {
//                ErpStockRecordCreateReqBO erpStockRecord = new ErpStockRecordCreateReqBO();
//                erpStockRecord.setProductId(stockConversion.getProductId());
//                erpStockRecord.setWarehouseId(7L);
//                erpStockRecord.setCount(stockConversion.getCount());
//                erpStockRecord.setBizType(ErpStockRecordBizTypeEnum.ORDER_CONVERSION_IN.getType());
//                erpStockRecord.setBizId(updateReqVO.getId());
//                erpStockRecord.setBizItemId(updateReqVO.getId());
//                erpStockRecord.setBizNo("1");
//                erpStockRecordServiceImpl.createStockRecord(erpStockRecord);
//            });
//        }
//
//
//        // 更新
//        StockConversionDO updateObj = BeanUtils.toBean(updateReqVO, StockConversionDO.class);
//        stockConversionMapper.updateById(updateObj);
//
//
//    }

}