package cn.iocoder.yudao.module.erp.dal.dataobject.matchingExampleRaw;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 配比原料实例 DO
 *
 * @author liangchuang
 */
@TableName("erp_hz_matching_example_raw")
@KeySequence("erp_hz_matching_example_raw_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HzMatchingExampleRawDO {

    /**
     * id编号
     */
    @TableId
    private Long id;
    /**
     * 配比模版表id
     */
    private Long matchingExampleId;
    /**
     * 产品id
     */
    private Long productId;
    /**
     * 厂家及规格
     */
    private String standard;
    /**
     * 生产配比
     */
    private BigDecimal ratio;
    /**
     * 需求数量
     */
    private BigDecimal number;

    /**
     * 类型 0无特殊类型 1面料 2底料
     */
    private Long type;

    /**
     * 注释
     */
    private String note;

    /**
     * 库存
     */
    @TableField(exist = false)
    private BigDecimal stock;


}