package cn.iocoder.yudao.module.erp.dal.dataobject.order;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 红正建材订单表
 DO
 *
 * @author liangchuang
 */
@TableName("erp_hz_order")
@KeySequence("erp_hz_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HzOrderDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;


    /**
     * 订单编号
     */
    private String  orderNumber;
    /**
     * 订单名称
     */
    private String orderName;
    /**
     * 产品id
     */
    private Long productId;

    /**
     * 产品名称
     */
    @TableField(exist = false)
    private String productName;
    /**
     * 所属项目
     */
    private Integer projectId;
    /**
     * 所属项目名称
     */
    @TableField(exist = false)
    private String projectName;
    /**
     * 所属客户
     */
    private String customer;
    /**
     * 订单备注
     */
    private String remark;

    /**
     * 是否挂起 0正常  1挂起
     */
    private Integer pause;

    /**
     * 订单状态 1待审核；2审核驳回；3待生产；5生产中；6待成品出库；7待过磅；8已过磅  ；9待配比原料；  10待原料出库 11 已取消 12 已作废 13 已转移 14 待交付
     */
    private Integer status;

    /**
     * 产品需求数量
     */
    private BigDecimal productAmount;
    /**
     * 产品需求单位
     */
    private String productUnit;
    /**
     * 送货开始时间
     */
    private LocalDateTime deliveryStartTime;
    /**
     * 送货结束时间
     */
    private LocalDateTime deliveryEndTime;
    /**
     * 送货地址
     */
    private String deliveryAddress;
    /**
     * 施工单位
     */
    private String constructionUnit;
    /**
     * 使用部位
     */
    private String partUsed;
    /**
     * 主办技术员
     */
    private String technician;

    /**
     * 主办技术员手机号
     */
    private String technicianPhone;
    /**
     * 砼标号
     */
    private String concreteGrade;
    /**
     * 浇筑部位
     */
    private String pouringPart;
    /**
     * 塌落度
     */
    private String slump;
    /**
     * 提货方式
     */
    private String unloadingMethod;
    /**
     * 施工部位
     */
    private String constructionSite;
    /**
     * 规格
     */
    private String specification;
    /**
     * 规格单位
     */
    private String specificationUnit;
    /**
     * 联系人名称
     */
    private String contactName;
    /**
     * 联系人手机号
     */
    private String contactPhone;


    @TableField(exist = false)
    private String categoryName;


    /**
     * 接收转移的数量
     */
    private BigDecimal receiveAmount;



}