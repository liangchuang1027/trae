package cn.iocoder.yudao.module.erp.controller.admin.matchingFormRaw.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 配比模版原料新增/修改 Request VO")
@Data
public class HzMatchingFormRawSaveReqVO {

    @Schema(description = "id编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "13176")
    private Long id;

    @Schema(description = "配比模版表id", requiredMode = Schema.RequiredMode.REQUIRED, example = "3439")
    @NotNull(message = "配比模版表id不能为空")
    private Long matchingFormId;

    @Schema(description = "产品id", requiredMode = Schema.RequiredMode.REQUIRED, example = "29009")
    @NotEmpty(message = "产品id不能为空")
    private Long productId;

    @Schema(description = "厂家及规格", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "厂家及规格不能为空")
    private String standard;

    @Schema(description = "生产配比", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "生产配比不能为空")
    private BigDecimal ratio;

}