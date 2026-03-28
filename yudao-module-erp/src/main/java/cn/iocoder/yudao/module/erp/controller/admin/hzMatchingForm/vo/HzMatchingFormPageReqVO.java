package cn.iocoder.yudao.module.erp.controller.admin.hzMatchingForm.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 配比模版分页 Request VO")
@Data
public class HzMatchingFormPageReqVO extends PageParam {

    @Schema(description = "产品类型编号", example = "2471")
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


}