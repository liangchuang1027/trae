package cn.iocoder.yudao.module.erp.controller.admin.reviewrRecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 红正建材审核记录新增/修改 Request VO")
@Data
public class HzReviewRecordSaveReqVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "11304")
    private Long id;

    @Schema(description = "名称", example = "李四")
    private String name;

    @Schema(description = "0拒绝1通过", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "0拒绝1通过不能为空")
    private Integer resault;

    @Schema(description = "生产订单id", example = "24209")
    private Long orderId;

    @Schema(description = "采购订单id", example = "7781")
    private Long purchaseId;


    @Schema(description = "拒绝原因", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "拒绝原因不能为空")
    private String refuseReasons;

}