package cn.iocoder.yudao.module.erp.controller.admin.stockConversion.vo;

import cn.iocoder.yudao.module.erp.dal.dataobject.stockConversion.StockConversionDO;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 在库形态转换 Response VO")
@Data
@ExcelIgnoreUnannotated
public class StockConversionRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1256")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "父级id", requiredMode = Schema.RequiredMode.REQUIRED, example = "717")
    @ExcelProperty("父级id")
    private Long parentId;

    @Schema(description = "产品编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "20384")
    @ExcelProperty("产品编号")
    private Long productId;

    @Schema(description = "产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("产品名称")
    private String productName;

    @Schema(description = "产品数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "11304")
    @ExcelProperty("产品数量")
    private BigDecimal count;

    @Schema(description = "单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("单位")
    private String unit;


    @Schema(description = "备注", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "拒绝原因", example = "不好")
    @ExcelProperty("拒绝原因")
    private String refusalReason;
    private String creator;

    // 子级
    private List<StockConversionDO> childList;

    @Schema(description = "类型1.形态转换  2.原料转运", example = "1")
    private Long type;

    @Schema(description = "仓库id", example = "1")
    private Long warehouseId;

}