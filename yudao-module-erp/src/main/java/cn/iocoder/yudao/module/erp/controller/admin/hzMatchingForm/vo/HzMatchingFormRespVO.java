package cn.iocoder.yudao.module.erp.controller.admin.hzMatchingForm.vo;

import cn.iocoder.yudao.module.erp.dal.dataobject.matchingFormRaw.HzMatchingFormRawDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.product.ErpProductCategoryDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.product.ErpProductDO;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 配比模版 Response VO")
@Data
@ExcelIgnoreUnannotated
public class HzMatchingFormRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "10069")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "产品类型编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "2471")
    @ExcelProperty("产品类型编号")
    private Long productCategoryId;

    @ExcelProperty("产品编号")
    @Schema(description = "配比模版名称")
    private String name;


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

    @Schema(description = "状态 0开启1关闭")
    @ExcelProperty("状态 0开启1关闭")
    private Integer status;

    private List<HzMatchingFormRawDO> raws;

    private ErpProductCategoryDO productCategory;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String creator;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}