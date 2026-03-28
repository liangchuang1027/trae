package cn.iocoder.yudao.module.erp.dal.dataobject.hzRawConsume;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 订单原料消耗表
 DO
 *
 * @author 超管
 */
@TableName("erp_hz_raw_consume")
@KeySequence("erp_hz_raw_consume_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HzRawConsumeDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 原料id
     */
    private Long productId;
    /**
     * 原料名称
     */
    private String productName;

    /**
     * 规格
     */
    private String standard;

    /**
     * 原料id
     */
    private Long warehouseId;

    /**
     * 目标值
     */
    private BigDecimal targetValue;
    /**
     * 秤量值
     */
    private BigDecimal factValue;
    /**
     * 误差值
     */
    private BigDecimal errorValue;
    /**
     * 误差率
     */
    private BigDecimal errorRate;


}