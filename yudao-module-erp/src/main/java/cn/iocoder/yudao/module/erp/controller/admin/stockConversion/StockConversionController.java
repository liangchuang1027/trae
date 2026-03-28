package cn.iocoder.yudao.module.erp.controller.admin.stockConversion;

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

import cn.iocoder.yudao.module.erp.controller.admin.stockConversion.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.stockConversion.StockConversionDO;
import cn.iocoder.yudao.module.erp.service.stockConversion.StockConversionService;

@Tag(name = "管理后台 - 在库形态转换")
@RestController
@RequestMapping("/erp/stock-conversion")
@Validated
public class StockConversionController {

    @Resource
    private StockConversionService stockConversionService;

    @PostMapping("/create")
    @Operation(summary = "创建在库形态转换")
    @PreAuthorize("@ss.hasPermission('erp:stock-conversion:create')")
    public CommonResult<Long> createStockConversion(@Valid @RequestBody StockConversionSaveReqVO createReqVO) {
        return success(stockConversionService.createStockConversion(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新在库形态转换")
    @PreAuthorize("@ss.hasPermission('erp:stock-conversion:update')")
    public CommonResult<Boolean> updateStockConversion(@Valid @RequestBody StockConversionSaveReqVO updateReqVO) {
        stockConversionService.updateStockConversion(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除在库形态转换")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('erp:stock-conversion:delete')")
    public CommonResult<Boolean> deleteStockConversion(@RequestParam("id") Long id) {
        stockConversionService.deleteStockConversion(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除在库形态转换")
                @PreAuthorize("@ss.hasPermission('erp:stock-conversion:delete')")
    public CommonResult<Boolean> deleteStockConversionList(@RequestParam("ids") List<Long> ids) {
        stockConversionService.deleteStockConversionListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得在库形态转换")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('erp:stock-conversion:query')")
    public CommonResult<StockConversionRespVO> getStockConversion(@RequestParam("id") Long id) {
        StockConversionDO stockConversion = stockConversionService.getStockConversion(id);
        return success(BeanUtils.toBean(stockConversion, StockConversionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得在库形态转换分页")
    @PreAuthorize("@ss.hasPermission('erp:stock-conversion:query')")
    public CommonResult<PageResult<StockConversionRespVO>> getStockConversionPage(@Valid StockConversionPageReqVO pageReqVO) {
        PageResult<StockConversionDO> pageResult = stockConversionService.getStockConversionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, StockConversionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出在库形态转换 Excel")
    @PreAuthorize("@ss.hasPermission('erp:stock-conversion:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStockConversionExcel(@Valid StockConversionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StockConversionDO> list = stockConversionService.getStockConversionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "在库形态转换.xls", "数据", StockConversionRespVO.class,
                        BeanUtils.toBean(list, StockConversionRespVO.class));
    }


//    @PutMapping("/review")
//    @Operation(summary = "审核")
//    public CommonResult<Boolean> review(@Valid @RequestBody StockConversionSaveReqVO updateReqVO) {
//        stockConversionService.review(updateReqVO);
//        return success(true);
//    }




}