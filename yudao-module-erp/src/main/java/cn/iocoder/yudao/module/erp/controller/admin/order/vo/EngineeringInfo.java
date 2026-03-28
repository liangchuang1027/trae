package cn.iocoder.yudao.module.erp.controller.admin.order.vo;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EngineeringInfo {
    private String orderName;        // 订单名称
    private String productName;         // 产品名称


    // 客户信息
    private String customerName;        // 客户名称
    private String projectName;         // 项目名称
    
    // 施工信息
    private String constructionTeam;    // 施工队
    private String concreteGrade;       // 砼标号
    private String volume;              // 方量
    private String pouringPart;         // 浇筑部位
    private String slump;               // 塌落度
    private String deliveryMethod;      // 自卸或泵送
    private String time;                // 时间
    private String contactPhone;        // 联系电话
    
    // 其他
    private String originalText;        // 原始文本
    private String remarks;             // 备注信息（解析后剩余的）
}