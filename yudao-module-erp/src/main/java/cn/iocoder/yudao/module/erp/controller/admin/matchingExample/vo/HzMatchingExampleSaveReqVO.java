package cn.iocoder.yudao.module.erp.controller.admin.matchingExample.vo;

import cn.iocoder.yudao.module.erp.controller.admin.matchingExampleRaw.vo.HzMatchingExampleRawSaveReqVO;
import cn.iocoder.yudao.module.erp.controller.admin.matchingFormRaw.vo.HzMatchingFormRawSaveReqVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 配比实例新增/修改 Request VO")
@Data
public class HzMatchingExampleSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "25731")
    private Long id;

    @Schema(description = "订单id", requiredMode = Schema.RequiredMode.REQUIRED, example = "19889")
    @NotNull(message = "订单id不能为空")
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

    @Schema(description = "配比模版原料实例")
    private List<HzMatchingExampleRawSaveReqVO> raws;

}