package cn.iocoder.yudao.module.erp.dal.dataobject.stockConversion;

import cn.iocoder.yudao.module.erp.controller.admin.stockConversion.vo.StockConversionSaveReqVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 在库形态转换 DO
 *
 * @author liangchuang
 */
@TableName("erp_stock_conversion")
@KeySequence("erp_stock_conversion_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockConversionDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 父级id
     */
    private Long parentId;
    /**
     * 产品编号
     */
    private Long productId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品数量
     */
    private BigDecimal count;
    /**
     * 单位
     */
    private String unit;
    /**
     * 备注
     */
    private String remark;
    /**
     * 拒绝原因
     */
    private String refusalReason;

    // 子级
    @TableField(exist = false)
    private List<StockConversionDO> childList;

    @Schema(description = "类型1.形态转换  2.原料转运", example = "1")
    private Long type;

    @Schema(description = "仓库id", example = "1")
    private Long warehouseId;

    @Schema(description = "损耗", example = "1")
    private Long loss;
}