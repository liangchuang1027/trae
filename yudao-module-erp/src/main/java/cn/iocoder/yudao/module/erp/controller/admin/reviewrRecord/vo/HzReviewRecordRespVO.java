package cn.iocoder.yudao.module.erp.controller.admin.reviewrRecord.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;

import org.apache.ibatis.type.JdbcType;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 红正建材审核记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class HzReviewRecordRespVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "11304")
    @ExcelProperty("id")
    private Long id;

    @Schema(description = "名称", example = "李四")
    @ExcelProperty("名称")
    private String name;

    @Schema(description = "0拒绝1通过", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("0拒绝1通过")
    private Integer resault;

    @Schema(description = "生产订单id", example = "24209")
    @ExcelProperty("生产订单id")
    private Long orderId;

    @Schema(description = "采购订单id", example = "7781")
    @ExcelProperty("采购订单id")
    private Long purchaseId;


    @Schema(description = "拒绝原因", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("拒绝原因")
    private String refuseReasons;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人")
    private String creator;

}