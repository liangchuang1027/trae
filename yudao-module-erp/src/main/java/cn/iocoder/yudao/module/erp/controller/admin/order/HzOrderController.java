package cn.iocoder.yudao.module.erp.controller.admin.order;

import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.framework.common.enums.OrderStatus;
import cn.iocoder.yudao.framework.common.util.object.ObjectUtils;
import cn.iocoder.yudao.module.erp.dal.dataobject.order.HzOrderProgressDO;
import cn.iocoder.yudao.module.erp.enums.ErrorCodeConstants;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.erp.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.order.HzOrderDO;
import cn.iocoder.yudao.module.erp.service.order.HzOrderService;

@Tag(name = "管理后台- hz -订单表")
@RestController
@RequestMapping("/erp/hz-order")
@Validated
public class HzOrderController {

    @Resource
    private HzOrderService hzOrderService;

    @PostMapping("/create")
    @Operation(summary = "创建红正建材订单表")
    @PreAuthorize("@ss.hasPermission('erp:hz-order:create')")
    public CommonResult<Long> createHzOrder(@Valid @RequestBody HzOrderSaveReqVO createReqVO) {
        return success(hzOrderService.createHzOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新红正建材订单表")
    @PreAuthorize("@ss.hasPermission('erp:hz-order:update')")
    public CommonResult<Boolean> updateHzOrder(@Valid @RequestBody HzOrderSaveReqVO updateReqVO) {
        HzOrderDO hzOrder = hzOrderService.getHzOrder(updateReqVO.getId());
        if (hzOrder.getPause() == 1){
            return CommonResult.error(ErrorCodeConstants.ORDER_STATUS_ERROR);
        }
        hzOrderService.updateHzOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除红正建材订单表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('erp:hz-order:delete')")
    public CommonResult<Boolean> deleteHzOrder(@RequestParam("id") Long id) {
        hzOrderService.deleteHzOrder(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除红正建材订单表")
                @PreAuthorize("@ss.hasPermission('erp:hz-order:delete')")
    public CommonResult<Boolean> deleteHzOrderList(@RequestParam("ids") List<Long> ids) {
        hzOrderService.deleteHzOrderListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得红正建材订单表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('erp:hz-order:query')")
    public CommonResult<HzOrderRespVO> getHzOrder(@RequestParam("id") Long id) {
        HzOrderDO hzOrder = hzOrderService.getHzOrder(id);
        return success(BeanUtils.toBean(hzOrder, HzOrderRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得红正建材订单表分页")
    @PreAuthorize("@ss.hasPermission('erp:hz-order:query')")
    public CommonResult<PageResult<HzOrderRespVO>> getHzOrderPage(@Valid HzOrderPageReqVO pageReqVO) {
        PageResult<HzOrderDO> pageResult = hzOrderService.getHzOrderPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, HzOrderRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出红正建材订单表Excel")
    @PreAuthorize("@ss.hasPermission('erp:hz-order:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHzOrderExcel(@Valid HzOrderPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<HzOrderDO> list = hzOrderService.getHzOrderPage(pageReqVO).getList();
        ExcelUtils.write(response, "红正建材订单表.xls", "数据", HzOrderRespVO.class,
                        BeanUtils.toBean(list, HzOrderRespVO.class));
    }




    @PutMapping("/updateStatus")
    @Operation(summary = "变更订单状态")//只接受简单无逻辑的变更订单状态
    @PreAuthorize("@ss.hasPermission('erp:hz-order:query')")
    public CommonResult<Boolean> updateStatus(@Valid @RequestBody HzOrderSaveReqVO updateReqVO) {
        HzOrderDO hzOrder = hzOrderService.getHzOrder(updateReqVO.getId());
        if (hzOrder.getPause() == 1){
            return CommonResult.error(ErrorCodeConstants.ORDER_STATUS_ERROR);
        }
        hzOrderService.updateStatus(updateReqVO);
        return success(true);
    }

    @PutMapping("/approved")
    @Operation(summary = "审核通过")
    @PreAuthorize("@ss.hasPermission('erp:hz-order:query')")
    public CommonResult<Boolean> approved(@Valid @RequestBody HzOrderSaveReqVO updateReqVO) {
        hzOrderService.approved(updateReqVO);
        return success(true);
    }

    @PostMapping("/rawOut")
    @Operation(summary = "原料出库")
    @PreAuthorize("@ss.hasPermission('erp:hz-order:query')")
    public CommonResult<Boolean> rawOut(@RequestParam("orderId") Long orderId) {
        if (orderId == null){
            return CommonResult.error(ErrorCodeConstants.RU_CAN_ERROR);
        }
        HzOrderDO order = hzOrderService.getHzOrder(orderId);
        if (ObjectUtil.isNull(order)){
            return CommonResult.error(ErrorCodeConstants.HZ_ORDER_NOT_EXISTS);
        }

        if (order.getStatus() == null || !order.getStatus().equals(OrderStatus.DAI_YUAN_LIAO_CHU_KU.getStatus())) {
            return CommonResult.error(ErrorCodeConstants.ORDER_STATUS_ERROR);
        }

        if (order.getPause() == 1){
            return CommonResult.error(ErrorCodeConstants.ORDER_STATUS_ERROR);
        }
        Boolean  result =  hzOrderService.rawOut(order);
        return success( result);
    }

    @PostMapping("/productFinished")
    @Operation(summary = "生产完成并成品入库")
    @PreAuthorize("@ss.hasPermission('erp:hz-order:query')")
    public CommonResult<Boolean> productFinished(@RequestParam("orderId") Long orderId) {
        if (orderId == null){
            return CommonResult.error(ErrorCodeConstants.RU_CAN_ERROR);
        }
        HzOrderDO order = hzOrderService.getHzOrder(orderId);
        if (ObjectUtil.isNull(order)){
            return CommonResult.error(ErrorCodeConstants.HZ_ORDER_NOT_EXISTS);
        }

        if (order.getStatus() == null || !order.getStatus().equals(OrderStatus.SHENG_CHAN_ZHONG.getStatus())) {
            return CommonResult.error(ErrorCodeConstants.ORDER_STATUS_ERROR);
        }
        if (order.getPause() == 1){
            return CommonResult.error(ErrorCodeConstants.ORDER_STATUS_ERROR);
        }
        Boolean  result =  hzOrderService.productFinished(order);
        return success( result);
    }


    @PostMapping("/productOut")
    @Operation(summary = "点击待交付-成品出库")
    @PreAuthorize("@ss.hasPermission('erp:hz-order:query')")
    public CommonResult<Boolean> productOut(@RequestParam("orderId") Long orderId) {
        if (orderId == null){
            return CommonResult.error(ErrorCodeConstants.RU_CAN_ERROR);
        }
        HzOrderDO order = hzOrderService.getHzOrder(orderId);
        if (ObjectUtil.isNull(order)){
            return CommonResult.error(ErrorCodeConstants.HZ_ORDER_NOT_EXISTS);
        }

        if (order.getStatus() == null || !order.getStatus().equals(OrderStatus.DAI_JIAO_FU.getStatus())) {
            return CommonResult.error(ErrorCodeConstants.ORDER_STATUS_ERROR);
        }
        if (order.getPause() == 1){
            return CommonResult.error(ErrorCodeConstants.ORDER_STATUS_ERROR);
        }
        Boolean  result =  hzOrderService.productOut(order);
        return success( result);
    }

    @PostMapping("/getStockByOrderId")
    @Operation(summary = "根据订单id查询成品数量和库存")
    @PreAuthorize("@ss.hasPermission('erp:hz-order:query')")
    public CommonResult<HashMap<String, Object>> getStockByOrderId(@RequestParam("orderId") Long orderId) {
        if (orderId == null){
            return CommonResult.error(ErrorCodeConstants.RU_CAN_ERROR);
        }
        HzOrderDO order = hzOrderService.getHzOrder(orderId);
        if (ObjectUtil.isNull(order)){
            return CommonResult.error(ErrorCodeConstants.HZ_ORDER_NOT_EXISTS);
        }

        if (order.getStatus() == null || !order.getStatus().equals(OrderStatus.DAI_CHENG_PIN_CHU_KU.getStatus())) {
            return CommonResult.error(ErrorCodeConstants.ORDER_STATUS_ERROR);
        }
        HashMap<String, Object>  result =  hzOrderService.getStockByOrderId(order);
        return success( result);
    }


    @PutMapping("/updatePause")
    @Operation(summary = "变更挂起状态")
    public CommonResult<Boolean> updatePause(@Valid @RequestBody HzOrderSaveReqVO updateReqVO) {
        hzOrderService.updatePause(updateReqVO);
        return success(true);
    }


    @GetMapping("/getProgress")
    @Operation(summary = "获取订单进度流转")
    public CommonResult<List<HzOrderProgressDO>> getProgress(@RequestParam("orderId") Long orderId) {
        List<HzOrderProgressDO> progress = hzOrderService.getProgress(orderId);
        return success(progress);
    }



    @PostMapping("/rawCallback")
    @Operation(summary = "原料回调")
    public CommonResult<Boolean> rawCallback(@Valid @RequestBody List<HzOrderCallBackVO> list) {
        if (ObjectUtil.isNull( list)){
            return CommonResult.error(ErrorCodeConstants.RU_CAN_ERROR);
        }

        Boolean progress = hzOrderService.rawCallback(list);
        return success(progress);
    }


    @PostMapping("/orderCallback")
    @Operation(summary = "订单回调")
    public CommonResult<Boolean> orderCallback(@Valid @RequestBody HzOrderCallBackVO vo) {
        if (ObjectUtil.isNull(vo)
                || ObjectUtil.isNull(vo.getOrderId())
                || ObjectUtil.isNull(vo.getStatus())
                || ObjectUtil.isNull(vo.getReceiveOrderId())
                || ObjectUtil.isNull(vo.getProductAmount())){
            return CommonResult.error(ErrorCodeConstants.RU_CAN_ERROR);
        }

        return success(hzOrderService.orderCallback(vo));
    }


    @GetMapping("/getOrderByStatus")
    @Operation(summary = "根据状态获取订单表")
    public CommonResult<List<HzOrderRespVO>> getOrderByStatus(@RequestParam("status") Long status) {
        List<HzOrderDO> hzOrders = hzOrderService.getOrderByStatus(status);
        return success(BeanUtils.toBean(hzOrders, HzOrderRespVO.class));
    }














}