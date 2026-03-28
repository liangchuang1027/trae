package cn.iocoder.yudao.module.erp.controller.admin.matchingExampleRaw.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 配比原料实例新增/修改 Request VO")
@Data
public class HzMatchingExampleRawSaveReqVO {

    @Schema(description = "id编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "6546")
    private Long id;

    @Schema(description = "配比模版表id", requiredMode = Schema.RequiredMode.REQUIRED, example = "10524")
    @NotNull(message = "配比模版表id不能为空")
    private Long matchingExampleId;

    @Schema(description = "产品id", requiredMode = Schema.RequiredMode.REQUIRED, example = "14426")
    @NotNull(message = "产品id不能为空")
    private Long productId;

    @Schema(description = "厂家及规格", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "厂家及规格不能为空")
    private String standard;

    @Schema(description = "生产配比", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal ratio;

    @Schema(description = "需求数量", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal number;

    @Schema(description = "类型 0无特殊类型 1面料 2底料", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long type;

    @Schema(description = "注释", requiredMode = Schema.RequiredMode.REQUIRED)
    private String note;

}