package cn.iocoder.yudao.module.erp.controller.admin.reviewrRecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 红正建材审核记录分页 Request VO")
@Data
public class HzReviewRecordPageReqVO extends PageParam {

    @Schema(description = "名称", example = "李四")
    private String name;

    @Schema(description = "0拒绝1通过")
    private Integer resault;

    @Schema(description = "生产订单id", example = "24209")
    private Long orderId;

    @Schema(description = "采购订单id", example = "7781")
    private Long purchaseId;


    @Schema(description = "拒绝原因")
    private String refuseReasons;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}