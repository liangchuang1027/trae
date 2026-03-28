package cn.iocoder.yudao.module.erp.dal.dataobject.project;

import cn.iocoder.yudao.module.erp.dal.dataobject.guest.GuestDO;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 红正建材项目 DO
 *
 * @author liangchuang
 */
@TableName("erp_hz_project")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HzProjectDO extends BaseDO {

    /**
     * 项目编号
     */
    @TableId
    private Long id;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 所属客户
     */
    private String customer;
    /**
     * 项目状态(0开启，1关闭)
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;



    @TableField(exist = false)
    private Long orderCount;

    /**
     * 多个客户
     */
    @TableField(exist = false)
    private List<GuestDO> guesyList;


}