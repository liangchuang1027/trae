package cn.iocoder.yudao.module.erp.controller.admin.matchingExample;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.erp.controller.admin.matchingExample.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.matchingExample.HzMatchingExampleDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.matchingExampleRaw.HzMatchingExampleRawDO;
import cn.iocoder.yudao.module.erp.service.matchingExample.HzMatchingExampleService;
import cn.iocoder.yudao.module.erp.service.product.ErpProductService;
import cn.iocoder.yudao.module.erp.dal.dataobject.product.ErpProductDO;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Tag(name = "管理后台 - hz - 配比实例")
@RestController
@RequestMapping("/erp/hz-matching-example")
@Validated
public class HzMatchingExampleController {

    @Resource
    private HzMatchingExampleService hzMatchingExampleService;
    
    @Resource
    private ErpProductService productService;

    @PostMapping("/create")
    @Operation(summary = "创建配比实例")
    public CommonResult<Long> createHzMatchingExample(@Valid @RequestBody HzMatchingExampleSaveReqVO createReqVO) {
        return success(hzMatchingExampleService.createHzMatchingExample(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新配比实例")
    public CommonResult<Boolean> updateHzMatchingExample(@Valid @RequestBody HzMatchingExampleSaveReqVO updateReqVO) {
        hzMatchingExampleService.updateHzMatchingExample(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除配比实例")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('erp:hz-matching-example:delete')")
    public CommonResult<Boolean> deleteHzMatchingExample(@RequestParam("id") Long id) {
        hzMatchingExampleService.deleteHzMatchingExample(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除配比实例")
                @PreAuthorize("@ss.hasPermission('erp:hz-matching-example:delete')")
    public CommonResult<Boolean> deleteHzMatchingExampleList(@RequestParam("ids") List<Long> ids) {
        hzMatchingExampleService.deleteHzMatchingExampleListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得配比实例")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<HzMatchingExampleRespVO> getHzMatchingExample(@RequestParam("id") Long id) {
        HzMatchingExampleDO hzMatchingExample = hzMatchingExampleService.getHzMatchingExample(id);
        return success(BeanUtils.toBean(hzMatchingExample, HzMatchingExampleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得配比实例分页")
    @PreAuthorize("@ss.hasPermission('erp:hz-matching-example:query')")
    public CommonResult<PageResult<HzMatchingExampleRespVO>> getHzMatchingExamplePage(@Valid HzMatchingExamplePageReqVO pageReqVO) {
        PageResult<HzMatchingExampleDO> pageResult = hzMatchingExampleService.getHzMatchingExamplePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, HzMatchingExampleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出配比实例 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHzMatchingExampleExcel(@Valid HzMatchingExamplePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<HzMatchingExampleDO> list = hzMatchingExampleService.getHzMatchingExamplePage(pageReqVO).getList();

        if (CollUtil.isEmpty(list)) {
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + 
                cn.iocoder.yudao.framework.common.util.http.HttpUtils.encodeUtf8("配比实例.xls"));
            return;
        }

        // 只支持1个HzMatchingExampleDO，取第一个
        HzMatchingExampleDO parent = list.get(0);
        List<HzMatchingExampleRawDO> raws = parent.getRaws();
        
        // 准备子表导出数据
        List<HzMatchingExampleRawExportVO> rawExportList = new ArrayList<>();
        if (CollUtil.isNotEmpty(raws)) {
            // 获取产品信息
            Set<Long> productIds = new HashSet<>();
            raws.forEach(raw -> {
                if (raw.getProductId() != null) {
                    productIds.add(raw.getProductId());
                }
            });
            Map<Long, ErpProductDO> productMap = new HashMap<>();
            if (CollUtil.isNotEmpty(productIds)) {
                productIds.forEach(productId -> {
                    ErpProductDO product = productService.getProduct(productId);
                    if (product != null) {
                        productMap.put(productId, product);
                    }
                });
            }
            
            // 构建子表导出数据
            for (HzMatchingExampleRawDO raw : raws) {
                HzMatchingExampleRawExportVO exportVO = new HzMatchingExampleRawExportVO();
                ErpProductDO product = productMap.get(raw.getProductId());
                exportVO.setProductName(product != null ? product.getName() : "");
                exportVO.setStandard(raw.getStandard());
                exportVO.setRatio(raw.getRatio());
                exportVO.setNumber(raw.getNumber());
                // 转换为吨（1吨=1000KG）
                if (raw.getNumber() != null) {
                    exportVO.setNumberInTon(raw.getNumber().divide(new BigDecimal("1000"), 2, RoundingMode.HALF_UP));
                }
                rawExportList.add(exportVO);
            }
        }

        // 设置响应头
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + 
            cn.iocoder.yudao.framework.common.util.http.HttpUtils.encodeUtf8("配比实例.xls"));

        // 创建样式策略
        WriteCellStyle headStyle = new WriteCellStyle();
        headStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        headStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        WriteFont headFont = new WriteFont();
        headFont.setFontHeightInPoints((short) 12);
        headFont.setBold(true);
        headStyle.setWriteFont(headFont);
        headStyle.setBorderTop(BorderStyle.THIN);
        headStyle.setBorderBottom(BorderStyle.THIN);
        headStyle.setBorderLeft(BorderStyle.THIN);
        headStyle.setBorderRight(BorderStyle.THIN);

        WriteCellStyle contentStyle = new WriteCellStyle();
        contentStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        contentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentStyle.setBorderTop(BorderStyle.THIN);
        contentStyle.setBorderBottom(BorderStyle.THIN);
        contentStyle.setBorderLeft(BorderStyle.THIN);
        contentStyle.setBorderRight(BorderStyle.THIN);
        WriteFont contentFont = new WriteFont();
        contentFont.setFontHeightInPoints((short) 11);
        contentStyle.setWriteFont(contentFont);

        HorizontalCellStyleStrategy styleStrategy = new HorizontalCellStyleStrategy(headStyle, contentStyle);

        // 创建自定义SheetWriteHandler
        MatchingExampleSheetWriteHandler sheetWriteHandler = new MatchingExampleSheetWriteHandler(parent, rawExportList.size());
        
        // 使用EasyExcel写入，注册自定义SheetWriteHandler来添加父表信息
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream())
                .registerWriteHandler(styleStrategy)
                .registerWriteHandler(sheetWriteHandler)
                .build();
        // 设置相对表头行索引，让表头从第2行开始（索引从0开始，所以是1）
        // 第1行是生产任务单号标题
        WriteSheet writeSheet = EasyExcel.writerSheet(0, "配比实例")
                .relativeHeadRowIndex(1)
                .head(HzMatchingExampleRawExportVO.class)
                .build();

        excelWriter.write(rawExportList, writeSheet);
        
        // 在数据写入完成后添加汇总信息
        sheetWriteHandler.addSummaryInfo(
            excelWriter.writeContext().writeSheetHolder().getSheet(),
            excelWriter.writeContext().writeWorkbookHolder().getWorkbook()
        );
        
        excelWriter.finish();
    }




}