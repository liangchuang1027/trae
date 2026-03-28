package cn.iocoder.yudao.module.erp.dal.dataobject.matchingExample;

import cn.iocoder.yudao.module.erp.dal.dataobject.matchingExampleRaw.HzMatchingExampleRawDO;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 配比实例 DO
 *
 * @author liangchuang
 */
@TableName("erp_hz_matching_example")
@KeySequence("erp_hz_matching_example_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HzMatchingExampleDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 原料配比单号
     */
    private String exampleNumber;
    /**
     * 品种
     */
    private String variety;
    /**
     * 强度等级
     */
    private String strengthGrade;
    /**
     * 砂率
     */
    private String sandRatio;
    /**
     * 容重
     */
    private String bulkDensity;
    /**
     * 水胶比
     */
    private String waterBinderRatio;
    /**
     * 依据标准
     */
    private String criterion;
    /**
     * 水泥生产厂家
     */
    private String manufacturer;
    /**
     * 级配型号
     */
    private String gradation;
    /**
     * 混合料种类
     */
    private String typesOfMixtures;
    /**
     * 沥青含量
     */
    private String asphaltContent;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 0开启1关闭
     */
    private Long status;


    @TableField(exist = false)
    private List<HzMatchingExampleRawDO> raws;

}