package cn.iocoder.yudao.module.erp.controller.admin.matchingFormRaw.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@Schema(description = "管理后台 - 配比模版原料分页 Request VO")
@Data
public class HzMatchingFormRawPageReqVO extends PageParam {

    @Schema(description = "配比模版表id", example = "3439")
    private Long matchingFormId;

    @Schema(description = "产品id", example = "29009")
    private Long productId;

    @Schema(description = "厂家及规格")
    private String standard;

    @Schema(description = "生产配比")
    private BigDecimal ratio;

}