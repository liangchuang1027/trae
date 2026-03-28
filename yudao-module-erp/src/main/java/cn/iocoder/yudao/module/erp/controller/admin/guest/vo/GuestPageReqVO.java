package cn.iocoder.yudao.module.erp.controller.admin.guest.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 红正客户分页 Request VO")
@Data
public class GuestPageReqVO extends PageParam {

    @Schema(description = "类型")
    private Integer type;

    @Schema(description = "客户姓名")
    private String name;

    @Schema(description = "电子邮箱")
    private String email;

    @Schema(description = "传真")
    private String fax;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "开启状态", example = "1")
    private Integer status;

    @Schema(description = "纳税人识别号")
    private String taxNo;

    @Schema(description = "税率")
    private BigDecimal taxPercent;

    @Schema(description = "开户行", example = "赵六")
    private String bankName;

    @Schema(description = "开户账号", example = "15924")
    private String bankAccount;

    @Schema(description = "开户地址")
    private String bankAddress;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}