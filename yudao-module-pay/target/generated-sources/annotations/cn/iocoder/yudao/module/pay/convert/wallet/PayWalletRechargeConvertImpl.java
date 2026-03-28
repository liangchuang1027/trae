package cn.iocoder.yudao.module.pay.convert.wallet;

import cn.iocoder.yudao.module.pay.controller.app.wallet.vo.recharge.AppPayWalletRechargeCreateRespVO;
import cn.iocoder.yudao.module.pay.dal.dataobject.wallet.PayWalletRechargeDO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-28T10:44:09+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class PayWalletRechargeConvertImpl implements PayWalletRechargeConvert {

    @Override
    public PayWalletRechargeDO convert(Long walletId, Integer payPrice, Integer bonusPrice, Long packageId) {
        if ( walletId == null && payPrice == null && bonusPrice == null && packageId == null ) {
            return null;
        }

        PayWalletRechargeDO payWalletRechargeDO = new PayWalletRechargeDO();

        payWalletRechargeDO.setWalletId( walletId );
        payWalletRechargeDO.setPayPrice( payPrice );
        payWalletRechargeDO.setBonusPrice( bonusPrice );
        payWalletRechargeDO.setPackageId( packageId );
        payWalletRechargeDO.setTotalPrice( payPrice + bonusPrice );

        return payWalletRechargeDO;
    }

    @Override
    public AppPayWalletRechargeCreateRespVO convert(PayWalletRechargeDO bean) {
        if ( bean == null ) {
            return null;
        }

        AppPayWalletRechargeCreateRespVO appPayWalletRechargeCreateRespVO = new AppPayWalletRechargeCreateRespVO();

        appPayWalletRechargeCreateRespVO.setId( bean.getId() );
        appPayWalletRechargeCreateRespVO.setPayOrderId( bean.getPayOrderId() );

        return appPayWalletRechargeCreateRespVO;
    }
}
