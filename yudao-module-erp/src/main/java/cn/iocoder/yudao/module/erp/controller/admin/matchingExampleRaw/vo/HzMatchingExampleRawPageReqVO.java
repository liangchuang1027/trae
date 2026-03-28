package cn.iocoder.yudao.module.erp.controller.admin.matchingExampleRaw.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 配比原料实例分页 Request VO")
@Data
public class HzMatchingExampleRawPageReqVO extends PageParam {

    @Schema(description = "配比模版表id", example = "10524")
    private Long matchingExampleId;

    @Schema(description = "产品id", example = "14426")
    private Long productId;

    @Schema(description = "厂家及规格")
    private String standard;

    @Schema(description = "生产配比")
    private BigDecimal ratio;

    @Schema(description = "需求数量")
    private BigDecimal number;

}