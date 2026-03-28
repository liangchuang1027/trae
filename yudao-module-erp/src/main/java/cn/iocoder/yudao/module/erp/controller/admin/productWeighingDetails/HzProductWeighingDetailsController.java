package cn.iocoder.yudao.module.erp.controller.admin.productWeighingDetails;

import cn.iocoder.yudao.module.erp.enums.ErrorCodeConstants;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import jakarta.servlet.http.*;

import java.time.LocalDateTime;
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

import cn.iocoder.yudao.module.erp.controller.admin.productWeighingDetails.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.productWeighingDetails.HzProductWeighingDetailsDO;
import cn.iocoder.yudao.module.erp.service.productWeighingDetails.HzProductWeighingDetailsService;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "管理后台 - 成品出库过磅明细表")
@RestController
@RequestMapping("/erp/hz-product-weighing-details")
@Validated
public class HzProductWeighingDetailsController {

    @Resource
    private HzProductWeighingDetailsService hzProductWeighingDetailsService;

    @PostMapping("/create")
    @Operation(summary = "创建过磅明细表")
    @PreAuthorize("@ss.hasPermission('erp:hz-product-weighing-details:create')")
    public CommonResult<Long> createHzProductWeighingDetails(@Valid @RequestBody HzProductWeighingDetailsSaveReqVO createReqVO) {
        return success(hzProductWeighingDetailsService.createHzProductWeighingDetails(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新过磅明细表")
    @PreAuthorize("@ss.hasPermission('erp:hz-product-weighing-details:update')")
    public CommonResult<Boolean> updateHzProductWeighingDetails(@Valid @RequestBody HzProductWeighingDetailsSaveReqVO updateReqVO) {
        hzProductWeighingDetailsService.updateHzProductWeighingDetails(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除过磅明细表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('erp:hz-product-weighing-details:delete')")
    public CommonResult<Boolean> deleteHzProductWeighingDetails(@RequestParam("id") Long id) {
        hzProductWeighingDetailsService.deleteHzProductWeighingDetails(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除过磅明细表")
                @PreAuthorize("@ss.hasPermission('erp:hz-product-weighing-details:delete')")
    public CommonResult<Boolean> deleteHzProductWeighingDetailsList(@RequestParam("ids") List<Long> ids) {
        hzProductWeighingDetailsService.deleteHzProductWeighingDetailsListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得过磅明细表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('erp:hz-product-weighing-details:query')")
    public CommonResult<HzProductWeighingDetailsRespVO> getHzProductWeighingDetails(@RequestParam("id") Long id) {
        HzProductWeighingDetailsDO hzProductWeighingDetails = hzProductWeighingDetailsService.getHzProductWeighingDetails(id);
        return success(BeanUtils.toBean(hzProductWeighingDetails, HzProductWeighingDetailsRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得过磅明细表分页")
    @PreAuthorize("@ss.hasPermission('erp:hz-product-weighing-details:query')")
    public CommonResult<PageResult<HzProductWeighingDetailsRespVO>> getHzProductWeighingDetailsPage(@Valid HzProductWeighingDetailsPageReqVO pageReqVO) {
        PageResult<HzProductWeighingDetailsDO> pageResult = hzProductWeighingDetailsService.getHzProductWeighingDetailsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, HzProductWeighingDetailsRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出过磅明细表Excel")
    @PreAuthorize("@ss.hasPermission('erp:hz-product-weighing-details:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHzProductWeighingDetailsExcel(@Valid HzProductWeighingDetailsPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<HzProductWeighingDetailsDO> list = hzProductWeighingDetailsService.getHzProductWeighingDetailsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "成品出库过磅明细表.xls", "数据", HzProductWeighingDetailsRespVO.class,
                        BeanUtils.toBean(list, HzProductWeighingDetailsRespVO.class));
    }


    @GetMapping("/get-import-template")
    @Operation(summary = "获得导入过磅信息模板")
    public void importTemplate(HttpServletResponse response) throws IOException {
        // 手动创建导出 demo
        List<WeighingImportExcelVO> list = Arrays.asList(
                WeighingImportExcelVO.builder().carNumber("冀C888888").planOrderNumber("我是计划单号").serialNumber("A202511270001").shipper("我是发货单位")
                        .netWeight(70020L).roughWeight(95180L).standard("我是规格").roughTime(LocalDateTime.now()).
                        tareTime(LocalDateTime.now()).tareWeight(25160L).productName("我是产品名称").consignee("我是收货单位").remarks("我是备注").build(),
                WeighingImportExcelVO.builder().carNumber("冀C888888").planOrderNumber("我是计划单号").serialNumber("A202511270001").shipper("我是发货单位")
                        .netWeight(70020L).roughWeight(95180L).standard("我是规格").roughTime(LocalDateTime.now()).
                        tareTime(LocalDateTime.now()).tareWeight(25160L).productName("我是产品名称").consignee("我是收货单位").remarks("我是备注").build()

        );
        // 输出
        ExcelUtils.write(response, "过磅信息模板.xls", "过磅信息", WeighingImportExcelVO.class, list);
    }

    @PostMapping("/import")
    @Operation(summary = "导入过磅信息")
    @Parameters({
            @Parameter(name = "file", description = "Excel 文件", required = true),
            @Parameter(name = "updateSupport", description = "是否支持更新，默认为 false", example = "true")
    })
    public CommonResult<WeighingImportRespVO> importExcel(@RequestParam("file") MultipartFile file,
                                                      @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport,
                                                          @RequestParam(value = "orderId", required = true) Long orderId) throws Exception {
        List<WeighingImportExcelVO> list;
        try {
            list = ExcelUtils.read(file, WeighingImportExcelVO.class);
        }catch (Exception e){
            return CommonResult.error(ErrorCodeConstants.RUCAN_ERROR);
        }
        return success(hzProductWeighingDetailsService.importProductWeighingList(list, updateSupport, orderId));
    }

    @GetMapping("/getWeighingDetailsByOrderId")
    @Operation(summary = "根据订单id获取成品过磅记录")
    @Parameter(name = "orderId", description = "订单id", required = true)
    @PreAuthorize("@ss.hasPermission('erp:hz-product-weighing-details:query')")
    public CommonResult<List<HzProductWeighingDetailsRespVO>> getWeighingDetailsByOrderId(@RequestParam("orderId") Long orderId) {
        List<HzProductWeighingDetailsDO>  hzProductWeighingDetails  = hzProductWeighingDetailsService.getWeighingDetailsByOrderId(orderId);
        return success(BeanUtils.toBean(hzProductWeighingDetails, HzProductWeighingDetailsRespVO.class));
    }




    @PostMapping("/import/purchase")
    @Operation(summary = "导入采购过磅信息")
    public CommonResult<WeighingImportRespVO> importPurchase(@RequestParam("file") MultipartFile file) {
        List<WeighingImportExcelVO> list;
        try {
            list = ExcelUtils.read(file, WeighingImportExcelVO.class);
        }catch (Exception e){
            return CommonResult.error(ErrorCodeConstants.RUCAN_ERROR);
        }
        return success(hzProductWeighingDetailsService.importPurchase(list));
    }


    @PostMapping("/import/productOut")
    @Operation(summary = "导入成品出库过磅信息")
    public CommonResult<WeighingImportRespVO> productOut(@RequestParam("file") MultipartFile file)  {
        List<WeighingImportExcelVO> list;
        try {
            list = ExcelUtils.read(file, WeighingImportExcelVO.class);
        }catch (Exception e){
            return CommonResult.error(ErrorCodeConstants.RUCAN_ERROR);
        }
        return success(hzProductWeighingDetailsService.productOut(list));
    }



    @GetMapping("/checkOrder")
    @Operation(summary = "校验订单是否有过磅信息绑定")
    public CommonResult<Integer> checkOrder(@RequestParam(value = "orderId") Long orderId,
                                            @RequestParam(value = "type") Long type)  {
        return success(hzProductWeighingDetailsService.checkOrder(orderId,type));
    }







}