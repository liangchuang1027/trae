package cn.iocoder.yudao.module.erp.controller.admin.stockConversion.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 在库形态转换新增/修改 Request VO")
@Data
public class StockConversionSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1256")
    private Long id;

    @Schema(description = "父级id", requiredMode = Schema.RequiredMode.REQUIRED, example = "717")
    private Long parentId;

    @Schema(description = "产品编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "20384")
    @NotNull(message = "产品编号不能为空")
    private Long productId;

    @Schema(description = "产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "产品名称不能为空")
    private String productName;

    @Schema(description = "产品数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "11304")
    @NotNull(message = "产品数量不能为空")
    private BigDecimal count;

    @Schema(description = "单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "单位不能为空")
    private String unit;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "拒绝原因", example = "不好")
    private String refusalReason;


    // 子级
    private List<StockConversionSaveReqVO> childList;



    @Schema(description = "类型1.形态转换  2.原料转运", example = "1")
    private Long type;

    @Schema(description = "仓库id", example = "1")
    private Long warehouseId;

    @Schema(description = "损耗", example = "1")
    private Long loss;


}