package cn.iocoder.yudao.module.erp.controller.admin.hzMatchingForm.vo;

import cn.iocoder.yudao.module.erp.controller.admin.matchingFormRaw.vo.HzMatchingFormRawSaveReqVO;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.matchingFormRaw.HzMatchingFormRawDO;

@Schema(description = "管理后台 - 配比模版新增/修改 Request VO")
@Data
public class HzMatchingFormSaveReqVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "10069")
    private Long id;

    @Schema(description = "产品类型id", requiredMode = Schema.RequiredMode.REQUIRED, example = "2471")
    @NotNull(message = "产品类型id")
    private Long productCategoryId;

    @Schema(description = "配比模版名称")
    private String name;

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

    @Schema(description = "状态 0开启1关闭")
    private Integer status;

    @Schema(description = "配比模版原料")
    private List<HzMatchingFormRawSaveReqVO> raws;

}