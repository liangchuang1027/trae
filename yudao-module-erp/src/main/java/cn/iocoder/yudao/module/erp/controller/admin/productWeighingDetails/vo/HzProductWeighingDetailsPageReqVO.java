package cn.iocoder.yudao.module.erp.controller.admin.productWeighingDetails.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 成品出库过磅明细表分页 Request VO")
@Data
public class HzProductWeighingDetailsPageReqVO extends PageParam {

    @Schema(description = "流水号")
    private String serialNumber;

    @Schema(description = "车号")
    private String carNumber;

    @Schema(description = "货名", example = "赵六")
    private String productName;

    @Schema(description = "类型", example = "1，2")
    private Long type;


    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}