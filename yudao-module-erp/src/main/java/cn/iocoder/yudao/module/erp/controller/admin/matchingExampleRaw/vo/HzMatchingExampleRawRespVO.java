package cn.iocoder.yudao.module.erp.controller.admin.matchingExampleRaw.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 配比原料实例 Response VO")
@Data
@ExcelIgnoreUnannotated
public class HzMatchingExampleRawRespVO {

    @Schema(description = "id编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "6546")
    @ExcelProperty("id编号")
    private Long id;

    @Schema(description = "配比模版表id", requiredMode = Schema.RequiredMode.REQUIRED, example = "10524")
    @ExcelProperty("配比模版表id")
    private Long matchingExampleId;

    @Schema(description = "产品id", requiredMode = Schema.RequiredMode.REQUIRED, example = "14426")
    @ExcelProperty("产品id")
    private Long productId;

    @Schema(description = "厂家及规格", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("厂家及规格")
    private String standard;

    @Schema(description = "生产配比", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("生产配比")
    private BigDecimal ratio;

    @Schema(description = "需求数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("需求数量")
    private BigDecimal number;

    @ExcelProperty("类型")
    @Schema(description = "类型 0无特殊类型 1面料 2底料", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long type;

    @ExcelProperty("注释")
    @Schema(description = "注释", requiredMode = Schema.RequiredMode.REQUIRED)
    private String note;

}