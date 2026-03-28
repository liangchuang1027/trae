package cn.iocoder.yudao.module.erp.controller.admin.order.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 红正建材订单表分页 Request VO")
@Data
public class HzOrderPageReqVO extends PageParam {

    @Schema(description = "订单名称", example = "李四")
    private String orderName;

    @Schema(description = "所属项目id")
    private Integer projectId;

    @Schema(description = "所属客户")
    private String customer;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "创建人")
    private String creator;

    @Schema(description = "订单状态", example = "1")
    private Integer status;

    /**
     * 是否挂起 0正常  1挂起
     */
    @Schema(description = "是否挂起", example = "1")
    private Integer pause;

}