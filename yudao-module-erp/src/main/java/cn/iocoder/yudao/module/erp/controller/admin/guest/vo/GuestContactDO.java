package cn.iocoder.yudao.module.erp.controller.admin.guest.vo;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

/**
 * 红正客户联系人
 *
 * @author liangchuang
 */
@TableName("erp_guest_contact")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestContactDO extends BaseDO {

    /**
     * 联系人id
     */
    @TableId
    private Long id;

    private Long guestId;

    /**
     * 联系人姓名
     */
    private String name;

    /**
     * 联系人性别
     */
    private Integer sex;
    /**
     * 手机号
     */
    private String phone;



}