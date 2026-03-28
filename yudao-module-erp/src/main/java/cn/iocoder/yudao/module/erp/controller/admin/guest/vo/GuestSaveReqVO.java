package cn.iocoder.yudao.module.erp.controller.admin.guest.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 红正客户新增/修改 Request VO")
@Data
public class GuestSaveReqVO {

    @Schema(description = "客户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "8212")
    private Long id;

    @Schema(description = "类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "所属类型不能为空")
    private Integer type;


    @Schema(description = "客户姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "客户姓名")
    private String name;

    @Schema(description = "电子邮箱")
    private String email;

    @Schema(description = "传真")
    private String fax;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "开启状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "开启状态不能为空")
    private Integer status;

    @Schema(description = "纳税人识别号")
    private String taxNo;

    @Schema(description = "税率")
    private BigDecimal taxPercent;

    @Schema(description = "开户行", example = "赵六")
    private String bankName;

    @Schema(description = "开户账号", example = "15924")
    private String bankAccount;

    @Schema(description = "开户地址")
    private String bankAddress;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "联系人集合")
    private List<GuestContactDO> contactList;



}