package cn.iocoder.yudao.module.erp.controller.admin.order.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 红正建材订单表新增/修改 Request VO")
@Data
public class HzOrderSaveReqVO {

    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "581")
    private Long id;

    @Schema(description = "订单名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "订单名称不能为空")
    private String orderName;

    @Schema(description = "产品id", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "产品id")
    private Integer productId;

    @Schema(description = "所属项目", requiredMode = Schema.RequiredMode.REQUIRED, example = "11092")
    @NotNull(message = "所属项目不能为空")
    private Long projectId;

    @Schema(description = "所属客户")
    private String customer;

    @Schema(description = "订单备注", example = "随便")
    private String remark;


    @Schema(description = "是否挂起" )
    private Integer pause;

    //1待审核；2审核驳回；3待生产；5生产中；6待成品出库；7待过磅；8已过磅  ；9待配比原料；  10待原料出库 11 已取消生产 12 已作废 13 已转移 14 待交付
    @Schema(description = "订单状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer status;


    @Schema(description = "产品需求数量")
    private BigDecimal productAmount;

    @Schema(description = "产品需求单位")
    private String productUnit;

    @Schema(description = "送货开始时间")
    private LocalDateTime deliveryStartTime;

    @Schema(description = "送货结束时间")
    private LocalDateTime deliveryEndTime;

    @Schema(description = "送货地址")
    private String deliveryAddress;

    @Schema(description = "施工单位")
    private String constructionUnit;

    @Schema(description = "使用部位")
    private String partUsed;

    @Schema(description = "主办技术员")
    private String technician;

    @Schema(description = "主办技术员手机号")
    private String technicianPhone;

    @Schema(description = "砼标号")
    private String concreteGrade;

    @Schema(description = "浇筑部位")
    private String pouringPart;

    @Schema(description = "塌落度")
    private String slump;

    @Schema(description = "提货方式")
    private String unloadingMethod;

    @Schema(description = "施工部位")
    private String constructionSite;

    @Schema(description = "规格")
    private String specification;

    @Schema(description = "规格单位")
    private String specificationUnit;


    @Schema(description = "联系人名称")
    private String contactName;

    @Schema(description = "联系人手机号")
    private String contactPhone;


    /**
     * 拒绝原因
     */
    private String refuseReasons;
    /**
     * 审核人
     */
    private String reviewUser;

}