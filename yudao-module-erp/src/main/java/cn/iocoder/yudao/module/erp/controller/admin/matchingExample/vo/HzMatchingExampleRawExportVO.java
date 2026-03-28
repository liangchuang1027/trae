package cn.iocoder.yudao.module.erp.controller.admin.matchingExample.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;
import com.alibaba.excel.annotation.*;

/**
 * 配比原料实例导出 VO（用于Excel导出）
 *
 * @author liangchuang
 */
@Schema(description = "管理后台 - 配比原料实例导出 VO")
@Data
@ExcelIgnoreUnannotated
public class HzMatchingExampleRawExportVO {

    @Schema(description = "材料名称")
    @ExcelProperty("材料名称")
    private String productName;

    @Schema(description = "厂家及规格")
    @ExcelProperty("厂家及规格")
    private String standard;

    @Schema(description = "生产配比(Kg/m³)")
    @ExcelProperty("生产配比(Kg/m³)")
    private BigDecimal ratio;

    @Schema(description = "预估原料总质量(KG)")
    @ExcelProperty("预估原料总质量(KG)")
    private BigDecimal number;

    @Schema(description = "转换为吨")
    @ExcelProperty("转换为吨")
    private BigDecimal numberInTon;

}

