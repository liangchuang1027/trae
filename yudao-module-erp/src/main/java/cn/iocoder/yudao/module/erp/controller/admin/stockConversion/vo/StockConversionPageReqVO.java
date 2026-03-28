package cn.iocoder.yudao.module.erp.controller.admin.stockConversion.vo;

import cn.iocoder.yudao.module.erp.dal.dataobject.stockConversion.StockConversionDO;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 在库形态转换分页 Request VO")
@Data
public class StockConversionPageReqVO extends PageParam {

    @Schema(description = "父级id", example = "717")
    private Long parentId;

    @Schema(description = "产品编号", example = "20384")
    private Long productId;

    @Schema(description = "产品名称", example = "芋艿")
    private String productName;

    @Schema(description = "产品数量", example = "11304")
    private BigDecimal count;

    @Schema(description = "单位")
    private String unit;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "拒绝原因", example = "不好")
    private String refusalReason;


    @Schema(description = "类型1.形态转换  2.原料转运", example = "1")
    private Long type;

    @Schema(description = "仓库id", example = "1")
    private Long warehouseId;


}