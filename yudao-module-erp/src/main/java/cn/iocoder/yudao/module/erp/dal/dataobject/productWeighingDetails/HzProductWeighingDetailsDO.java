package cn.iocoder.yudao.module.erp.dal.dataobject.productWeighingDetails;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 成品出库过磅明细表
 DO
 *
 * @author liangchuang
 */
@TableName("erp_hz_product_weighing_details")
@KeySequence("erp_hz_product_weighing_details_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HzProductWeighingDetailsDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 流水号
     */
    private String serialNumber;
    /**
     * 车号
     */
    private String carNumber;
    /**
     * 发货单位
     */
    private String shipper;
    /**
     * 收货单位
     */
    private String consignee;
    /**
     * 货名
     */
    private String productName;
    /**
     * 规格
     */
    private String standard;
    /**
     * 毛重
     */
    private Long roughWeight;
    /**
     * 皮重
     */
    private Long tareWeight;
    /**
     * 净重
     */
    private Long netWeight;
    /**
     * 毛重时间
     */
    private LocalDateTime roughTime;
    /**
     * 皮重时间
     */
    private LocalDateTime tareTime;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 计划单号
     */
    private String planOrderNumber;
    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 1.采购过磅 2.成品过磅
     */
    private Long type;


}