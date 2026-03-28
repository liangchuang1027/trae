package cn.iocoder.yudao.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * 红正订单状态枚举
 *
 *
 * @author liangchuang
 */
@Getter
@AllArgsConstructor
public enum OrderStatus {

    //1待审核；2审核驳回；3待生产；5生产中；6待成品出库；7待过磅；8已过磅  ；9待配比原料；  10待原料出库 11 已取消 12 已作废 13 已转移 14 待交付

    DAI_SHEN_HE(1),

    SHEN_HE_BO_HUI(2),

    DAI_SHENG_CHAN(3),

//    YAN_FA(4),

    SHENG_CHAN_ZHONG(5),

    DAI_CHENG_PIN_CHU_KU(6),

    DAI_GUO_BANG(7),

    YI_GUO_BANG(8),

    DAI_PEI_BI_YUAN_LIAO(9),

    DAI_YUAN_LIAO_CHU_KU(10),

    YI_QU_XIAO(11),
    YI_ZUO_FEI(12),
    YI_ZHUAN_YI(13),
    DAI_JIAO_FU(14);

    private final Integer status;
}