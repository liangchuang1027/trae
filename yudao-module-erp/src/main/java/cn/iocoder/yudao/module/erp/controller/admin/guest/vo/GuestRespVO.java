package cn.iocoder.yudao.module.erp.controller.admin.guest.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 红正客户 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GuestRespVO {

    @Schema(description = "客户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "8212")
    @ExcelProperty("客户编号")
    private Long id;

    @Schema(description = "类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("类型")
    private Integer type;


    @Schema(description = "客户姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("客户姓名")
    private String name;

    @Schema(description = "电子邮箱")
    @ExcelProperty("电子邮箱")
    private String email;

    @Schema(description = "传真")
    @ExcelProperty("传真")
    private String fax;

    @Schema(description = "备注", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "开启状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("开启状态")
    private Integer status;

    @Schema(description = "纳税人识别号")
    @ExcelProperty("纳税人识别号")
    private String taxNo;

    @Schema(description = "税率")
    @ExcelProperty("税率")
    private BigDecimal taxPercent;

    @Schema(description = "开户行", example = "赵六")
    @ExcelProperty("开户行")
    private String bankName;

    @Schema(description = "开户账号", example = "15924")
    @ExcelProperty("开户账号")
    private String bankAccount;

    @Schema(description = "开户地址")
    @ExcelProperty("开户地址")
    private String bankAddress;

    @Schema(description = "地址")
    @ExcelProperty("地址")
    private String address;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;
    @Schema(description = "联系人集合")
    private List<GuestContactDO> contactList;
}