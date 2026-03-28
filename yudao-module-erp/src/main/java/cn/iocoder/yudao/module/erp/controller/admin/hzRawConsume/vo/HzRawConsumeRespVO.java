package cn.iocoder.yudao.module.erp.controller.admin.hzRawConsume.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 订单原料消耗表Response VO")
@Data
@ExcelIgnoreUnannotated
public class HzRawConsumeRespVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "4487")
    @ExcelProperty("id")
    private Long id;

    @Schema(description = "订单id", requiredMode = Schema.RequiredMode.REQUIRED, example = "24061")
    @ExcelProperty("订单id")
    private Long orderId;

    @Schema(description = "原料id", example = "3761")
    @ExcelProperty("原料id")
    private Long productId;

    @Schema(description = "原料名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("原料名称")
    private String productName;


    private String standard;

    @Schema(description = "目标值")
    @ExcelProperty("目标值")
    private BigDecimal targetValue;

    @Schema(description = "秤量值")
    @ExcelProperty("秤量值")
    private BigDecimal factValue;

    @Schema(description = "误差值")
    @ExcelProperty("误差值")
    private BigDecimal errorValue;

    @Schema(description = "误差率")
    @ExcelProperty("误差率")
    private BigDecimal errorRate;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}