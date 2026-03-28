package cn.iocoder.yudao.module.erp.controller.admin.hzRawConsume.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 订单原料消耗表 新增/修改 Request VO")
@Data
public class HzRawConsumeSaveReqVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "4487")
    private Long id;

    @Schema(description = "订单id", requiredMode = Schema.RequiredMode.REQUIRED, example = "24061")
    @NotNull(message = "订单id不能为空")
    private Long orderId;

    @Schema(description = "原料id", example = "3761")
    private Long productId;

    @Schema(description = "原料名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "原料名称不能为空")
    private String productName;

    @Schema(description = "目标值")
    private BigDecimal targetValue;

    @Schema(description = "秤量值")
    private BigDecimal factValue;

    @Schema(description = "误差值")
    private BigDecimal errorValue;

    @Schema(description = "误差率")
    private BigDecimal errorRate;

}