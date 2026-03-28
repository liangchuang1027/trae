package cn.iocoder.yudao.module.erp.dal.dataobject.order;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.*;
import lombok.*;
import org.apache.ibatis.type.JdbcType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 红正建材订单表
 DO
 *
 * @author liangchuang
 */
@TableName("erp_hz_order_progress")
@KeySequence("erp_hz_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HzOrderProgressDO extends BaseDO{

    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 订单编号
     */
    private Long  orderId;
    /**
     *进度
     */
    private String progress;

    /**
     *手机号
     */
    private String phone;




}