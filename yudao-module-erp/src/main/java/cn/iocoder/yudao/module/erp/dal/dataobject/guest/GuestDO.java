package cn.iocoder.yudao.module.erp.dal.dataobject.guest;

import cn.iocoder.yudao.module.erp.controller.admin.guest.vo.GuestContactDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 红正客户 DO
 *
 * @author liangchuang
 */
@TableName("erp_guest")
@KeySequence("erp_guest_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestDO extends BaseDO {

    /**
     * 客户编号
     */
    @TableId
    private Long id;
    /**
     * 客户类型
     */
    private Integer type;

    /**
     * 客户名称
     */
    private String name;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 传真
     */
    private String fax;
    /**
     * 备注
     */
    private String remark;
    /**
     * 开启状态
     */
    private Integer status;
    /**
     * 纳税人识别号
     */
    private String taxNo;
    /**
     * 税率
     */
    private BigDecimal taxPercent;
    /**
     * 开户行
     */
    private String bankName;
    /**
     * 开户账号
     */
    private String bankAccount;
    /**
     * 开户地址
     */
    private String bankAddress;

    /**
     * 客户地址
     */
    private String address;

    @TableField(exist = false)
    private List<GuestContactDO> contactList;
}