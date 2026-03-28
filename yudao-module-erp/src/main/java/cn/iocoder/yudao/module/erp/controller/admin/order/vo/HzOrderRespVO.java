package cn.iocoder.yudao.module.erp.controller.admin.order.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;

import org.apache.ibatis.type.JdbcType;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 红正建材订单表Response VO")
@Data
@ExcelIgnoreUnannotated
public class HzOrderRespVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "581")
    @ExcelProperty("id")
    private Long id;

    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "581")
    @ExcelProperty("订单编号")
    private String  orderNumber;

    @Schema(description = "订单名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("订单名称")
    private String orderName;

    @Schema(description = "产品id", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("产品id")
    private Long productId;

    @Schema(description = "产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("产品名称")
    private String productName;

    @Schema(description = "所属项目", requiredMode = Schema.RequiredMode.REQUIRED, example = "11092")
    @ExcelProperty("所属项目")
    private Integer projectId;

    @Schema(description = "所属项目名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("所属项目名称")
    private String projectName;

    @ExcelProperty("联系人名称")
    @Schema(description = "联系人名称")
    private String contactName;

    @ExcelProperty("联系人手机号")
    @Schema(description = "联系人手机号")
    private String contactPhone;

    @Schema(description = "所属客户")
    @ExcelProperty("所属客户")
    private String customer;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人")
    private String creator;

    @Schema(description = "订单备注", example = "随便")
    @ExcelProperty("订单备注")
    private String remark;


    @Schema(description = "是否挂起", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("是否挂起")
    private Integer pause;

    @Schema(description = "订单状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("项目状态")
    private Integer status;


    @Schema(description = "产品需求数量")
    @ExcelProperty("产品需求数量")
    private BigDecimal productAmount;

    @Schema(description = "产品需求单位")
    @ExcelProperty("产品需求单位")
    private String productUnit;

    @Schema(description = "送货开始时间")
    @ExcelProperty("送货开始时间")
    private LocalDateTime deliveryStartTime;

    @Schema(description = "送货结束时间")
    @ExcelProperty("送货结束时间")
    private LocalDateTime deliveryEndTime;

    @Schema(description = "送货地址")
    @ExcelProperty("送货地址")
    private String deliveryAddress;

    @Schema(description = "施工单位")
    @ExcelProperty("施工单位")
    private String constructionUnit;

    @Schema(description = "使用部位")
    @ExcelProperty("使用部位")
    private String partUsed;

    @Schema(description = "主办技术员")
    @ExcelProperty("主办技术员")
    private String technician;

    @Schema(description = "主办技术员手机号")
    @ExcelProperty("主办技术员手机号")
    private String technicianPhone;

    @Schema(description = "砼标号")
    @ExcelProperty("砼标号")
    private String concreteGrade;

    @Schema(description = "浇筑部位")
    @ExcelProperty("浇筑部位")
    private String pouringPart;

    @Schema(description = "塌落度")
    @ExcelProperty("塌落度")
    private String slump;

    @Schema(description = "提货方式")
    @ExcelProperty("提货方式")
    private String unloadingMethod;

    @Schema(description = "施工部位")
    @ExcelProperty("施工部位")
    private String constructionSite;

    @Schema(description = "规格")
    @ExcelProperty("规格")
    private String specification;

    @Schema(description = "规格单位")
    @ExcelProperty("规格单位")
    private String specificationUnit;

}