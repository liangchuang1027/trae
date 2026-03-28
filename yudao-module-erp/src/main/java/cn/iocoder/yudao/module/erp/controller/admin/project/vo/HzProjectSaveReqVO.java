package cn.iocoder.yudao.module.erp.controller.admin.project.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 红正建材项目新增/修改 Request VO")
@Data
public class HzProjectSaveReqVO {

    @Schema(description = "项目编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "28128")
    @ExcelProperty("项目编号")
    private Long id;

    @Schema(description = "项目名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "项目名称不能为空")
    private String projectName;

    @Schema(description = "所属客户", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属客户不能为空")
    private String customer;

    @Schema(description = "备注", example = "随便")
    private String remark;

    /**
     * 项目状态(0开启，1关闭)
     */
    @Schema(description = "备注", example = "随便")
    private Integer status;


}