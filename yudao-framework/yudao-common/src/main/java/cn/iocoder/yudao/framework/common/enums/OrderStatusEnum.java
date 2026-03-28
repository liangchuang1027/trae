package cn.iocoder.yudao.framework.common.enums;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjUtil;
import cn.iocoder.yudao.framework.common.core.ArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 红正订单状态枚举
 *
 * 1待审核；2审核驳回；3待生产；5生产中；6待出库；7待过磅；8已过磅  ；9待配比原料；  10待原料出库
 *
 * @author liangchuang
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum  {

    //1待审核；2审核驳回；3待生产；5生产中；6待出库；7待过磅；8已过磅  ；9待配比原料；  10待原料出库

    SUPER_ADMIN(1L,Arrays.asList(1, 2, 3, 5, 6, 7, 8, 9, 10)),

    WU_ZI(160L, Arrays.asList(1, 2, 3, 5, 6, 7, 8, 9, 10)),

    XIAO_SHOU(161L, Arrays.asList(1, 2, 3, 5, 6, 7, 8, 9, 10)),

    YAN_FA(165L,Arrays.asList(3, 5, 6, 7, 8, 9, 10)),

    KU_FANG(163L,Arrays.asList( 3, 5, 6, 7, 8, 10)),

    SHENG_CHAN(162L,  Arrays.asList(3, 5, 6, 7, 8)),

    BANG_FANG(164L,Arrays.asList( 7, 8)),

    SHI_CHANG_BU_ZHANG(165L, Arrays.asList(1, 2, 3, 5, 6, 7, 8, 9, 10));


    private final Long roleId;
    private final List<Integer> status;
}