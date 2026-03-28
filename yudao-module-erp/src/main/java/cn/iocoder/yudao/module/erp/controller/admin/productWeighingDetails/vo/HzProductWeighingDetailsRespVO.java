package cn.iocoder.yudao.module.erp.controller.admin.productWeighingDetails.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 成品出库过磅明细表Response VO")
@Data
@ExcelIgnoreUnannotated
public class HzProductWeighingDetailsRespVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "15837")
    private Long id;

    @Schema(description = "流水号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("流水号")
    private String serialNumber;

    @Schema(description = "车号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车号")
    private String carNumber;

    @Schema(description = "发货单位")
    @ExcelProperty("发货单位")
    private String shipper;

    @Schema(description = "收货单位")
    @ExcelProperty("收货单位")
    private String consignee;

    @Schema(description = "货名", example = "赵六")
    @ExcelProperty("货名")
    private String productName;

    @Schema(description = "规格")
    @ExcelProperty("规格")
    private String standard;

    @Schema(description = "毛重")
    @ExcelProperty("毛重")
    private Long roughWeight;

    @Schema(description = "皮重")
    @ExcelProperty("皮重")
    private Long tareWeight;

    @Schema(description = "净重")
    @ExcelProperty("净重")
    private Long netWeight;

    @Schema(description = "毛重时间")
    @ExcelProperty("毛重时间")
    private LocalDateTime roughTime;

    @Schema(description = "皮重时间")
    @ExcelProperty("皮重时间")
    private LocalDateTime tareTime;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remarks;

    @Schema(description = "计划单号")
    @ExcelProperty("计划单号")
    private String planOrderNumber;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 1.采购过磅 2.成品过磅
     */
    private Long type;


}