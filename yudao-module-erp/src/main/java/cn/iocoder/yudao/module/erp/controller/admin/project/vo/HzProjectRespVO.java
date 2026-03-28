package cn.iocoder.yudao.module.erp.controller.admin.project.vo;

import cn.iocoder.yudao.module.erp.dal.dataobject.guest.GuestDO;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 红正建材项目 Response VO")
@Data
@ExcelIgnoreUnannotated
public class HzProjectRespVO {

    @Schema(description = "项目编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "28128")
    @ExcelProperty("项目编号")
    private Long id;

    @Schema(description = "项目名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("项目名称")
    private String projectName;

    @Schema(description = "所属客户", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属客户")
    private String customer;

    @Schema(description = "项目状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("项目状态")
    private Integer status;

    @Schema(description = "备注", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String creator;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    private Long orderCount;

    private List<GuestDO> guesyList;


}