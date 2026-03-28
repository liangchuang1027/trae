package cn.iocoder.yudao.module.erp.controller.admin.matchingExample.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 配比实例分页 Request VO")
@Data
public class HzMatchingExamplePageReqVO extends PageParam {

    @Schema(description = "订单id", example = "19889")
    private Long orderId;

    @Schema(description = "原料配比单号")
    private String exampleNumber;

    @Schema(description = "品种")
    private String variety;

    @Schema(description = "强度等级")
    private String strengthGrade;

    @Schema(description = "砂率")
    private String sandRatio;

    @Schema(description = "容重")
    private String bulkDensity;

    @Schema(description = "水胶比")
    private String waterBinderRatio;

    @Schema(description = "依据标准")
    private String criterion;

    @Schema(description = "水泥生产厂家")
    private String manufacturer;

    @Schema(description = "级配型号")
    private String gradation;

    @Schema(description = "混合料种类")
    private String typesOfMixtures;

    @Schema(description = "沥青含量")
    private String asphaltContent;

    @Schema(description = "备注")
    private String remarks;

    @Schema(description = "0开启1关闭", example = "2")
    private Long status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}