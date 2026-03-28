package cn.iocoder.yudao.module.erp.controller.admin.project.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 红正建材项目分页 Request VO")
@Data
public class HzProjectPageReqVO extends PageParam {

    @Schema(description = "项目名称", example = "张三")
    private String projectName;

    @Schema(description = "所属客户")
    private String customer;

    @Schema(description = "项目状态", example = "1")
    private Integer status;

}