package cn.iocoder.yudao.module.erp.controller.admin.matchingExample.vo;

import cn.iocoder.yudao.module.erp.dal.dataobject.matchingExampleRaw.HzMatchingExampleRawDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 配比实例 Response VO")
@Data
@ExcelIgnoreUnannotated
public class HzMatchingExampleRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "25731")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "订单id", requiredMode = Schema.RequiredMode.REQUIRED, example = "19889")
    @ExcelProperty("订单id")
    private Long orderId;

    @Schema(description = "原料配比单号")
    @ExcelProperty("原料配比单号")
    private String exampleNumber;

    @Schema(description = "品种")
    @ExcelProperty("品种")
    private String variety;

    @Schema(description = "强度等级")
    @ExcelProperty("强度等级")
    private String strengthGrade;

    @Schema(description = "砂率")
    @ExcelProperty("砂率")
    private String sandRatio;

    @Schema(description = "容重")
    @ExcelProperty("容重")
    private String bulkDensity;

    @Schema(description = "水胶比")
    @ExcelProperty("水胶比")
    private String waterBinderRatio;

    @Schema(description = "依据标准")
    @ExcelProperty("依据标准")
    private String criterion;

    @Schema(description = "水泥生产厂家")
    @ExcelProperty("水泥生产厂家")
    private String manufacturer;

    @Schema(description = "级配型号")
    @ExcelProperty("级配型号")
    private String gradation;

    @Schema(description = "混合料种类")
    @ExcelProperty("混合料种类")
    private String typesOfMixtures;

    @Schema(description = "沥青含量")
    @ExcelProperty("沥青含量")
    private String asphaltContent;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remarks;

    @Schema(description = "0开启1关闭", example = "2")
    @ExcelProperty("0开启1关闭")
    private Long status;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    private List<HzMatchingExampleRawDO> raws;

}