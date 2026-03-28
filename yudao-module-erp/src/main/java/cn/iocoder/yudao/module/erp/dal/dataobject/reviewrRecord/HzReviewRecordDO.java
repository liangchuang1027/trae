package cn.iocoder.yudao.module.erp.dal.dataobject.reviewrRecord;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 红正建材审核记录 DO
 *
 * @author liangchuang
 */
@TableName("erp_hz_review_record")
@KeySequence("erp_hz_review_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HzReviewRecordDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 0拒绝1通过
     */
    private Integer resault;
    /**
     * 生产订单id
     */
    private Long orderId;
    /**
     * 采购订单id
     */
    private Long purchaseId;

    /**
     * 拒绝原因
     */
    private String refuseReasons;


}