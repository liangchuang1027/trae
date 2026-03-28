package cn.iocoder.yudao.module.erp.controller.admin.productWeighingDetails.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 成品出库过磅明细表新增/修改 Request VO")
@Data
public class HzProductWeighingDetailsSaveReqVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "15837")
    private Long id;

    @Schema(description = "流水号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "流水号不能为空")
    private String serialNumber;

    @Schema(description = "车号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车号不能为空")
    private String carNumber;

    @Schema(description = "发货单位")
    @NotEmpty(message = "发货单位不能为空")
    private String shipper;

    @Schema(description = "收货单位")
    @NotEmpty(message = "收货单位不能为空")
    private String consignee;

    @Schema(description = "货名", example = "赵六")
    @NotEmpty(message = "货名不能为空")
    private String productName;

    @Schema(description = "规格")
    private String standard;

    @Schema(description = "毛重")
    @NotNull(message = "毛重不能为空")
    private Long roughWeight;

    @Schema(description = "皮重")
    @NotNull(message = "皮重不能为空")
    private Long tareWeight;

    @Schema(description = "净重")
    @NotNull(message = "净重不能为空")
    private Long netWeight;

    @Schema(description = "毛重时间")
    @NotNull(message = "毛重时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime roughTime;

    @Schema(description = "皮重时间")
    @NotNull(message = "皮重时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tareTime;

    @Schema(description = "备注")
    private String remarks;

    @Schema(description = "计划单号")
    private String planOrderNumber;

}