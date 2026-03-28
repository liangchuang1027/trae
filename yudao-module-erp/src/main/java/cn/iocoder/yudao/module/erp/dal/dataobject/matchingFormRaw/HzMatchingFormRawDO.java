package cn.iocoder.yudao.module.erp.dal.dataobject.matchingFormRaw;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 配比模版原料 DO
 *
 * @author liangchuang
 */
@TableName("erp_hz_matching_form_raw")
@KeySequence("erp_hz_matching_form_raw_seq")
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HzMatchingFormRawDO {

    /**
     * 原料id编号
     */
    @TableId
    private Long id;
    /**
     * 配比模版表id
     */
    private Long matchingFormId;
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


}