package cn.iocoder.yudao.module.erp.controller.admin.productWeighingDetails.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 过磅信息导入 Response VO")
@Data
@Builder
public class WeighingImportRespVO {

    @Schema(description = "创建成功的流水号数组", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> createSerialNumber;

    @Schema(description = "更新成功的流水号数组", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> updateSerialNumber;

    @Schema(description = "导入失败的流水号集合，key 为流水号，value 为失败原因", requiredMode = Schema.RequiredMode.REQUIRED)
    private Map<String, String> failureSerialNumber;

}
