package cn.iocoder.yudao.module.erp.controller.admin.hzRawConsume.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 订单原料消耗表分页 Request VO")
@Data
public class HzRawConsumePageReqVO extends PageParam {

    @Schema(description = "订单id", example = "24061")
    private Long orderId;

    @Schema(description = "原料id", example = "3761")
    private Long productId;

    @Schema(description = "原料名称", example = "张三")
    private String productName;

    @Schema(description = "规格")
    private String standard;

    @Schema(description = "目标值")
    private BigDecimal targetValue;

    @Schema(description = "秤量值")
    private BigDecimal factValue;

    @Schema(description = "误差值")
    private BigDecimal errorValue;

    @Schema(description = "误差率")
    private BigDecimal errorRate;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}