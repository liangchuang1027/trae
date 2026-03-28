package cn.iocoder.yudao.module.erp.controller.admin.matchingFormRaw.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 配比模版原料 Response VO")
@Data
@ExcelIgnoreUnannotated
public class HzMatchingFormRawRespVO {

    @Schema(description = "id编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "13176")
    @ExcelProperty("id编号")
    private Long id;

    @Schema(description = "配比模版表id", requiredMode = Schema.RequiredMode.REQUIRED, example = "3439")
    @ExcelProperty("配比模版表id")
    private Long matchingFormId;

    @Schema(description = "产品id", requiredMode = Schema.RequiredMode.REQUIRED, example = "29009")
    @ExcelProperty("产品id")
    private Long productId;

    @Schema(description = "厂家及规格", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("厂家及规格")
    private String standard;

    @Schema(description = "生产配比", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("生产配比")
    private BigDecimal ratio;

}