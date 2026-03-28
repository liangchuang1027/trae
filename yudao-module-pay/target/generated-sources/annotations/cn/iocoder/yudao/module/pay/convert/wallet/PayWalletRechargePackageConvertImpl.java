package cn.iocoder.yudao.module.pay.convert.wallet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.pay.controller.admin.wallet.vo.rechargepackage.WalletRechargePackageCreateReqVO;
import cn.iocoder.yudao.module.pay.controller.admin.wallet.vo.rechargepackage.WalletRechargePackageRespVO;
import cn.iocoder.yudao.module.pay.controller.admin.wallet.vo.rechargepackage.WalletRechargePackageUpdateReqVO;
import cn.iocoder.yudao.module.pay.dal.dataobject.wallet.PayWalletRechargePackageDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-28T10:44:09+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class PayWalletRechargePackageConvertImpl implements PayWalletRechargePackageConvert {

    @Override
    public PayWalletRechargePackageDO convert(WalletRechargePackageCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        PayWalletRechargePackageDO payWalletRechargePackageDO = new PayWalletRechargePackageDO();

        payWalletRechargePackageDO.setName( bean.getName() );
        payWalletRechargePackageDO.setPayPrice( bean.getPayPrice() );
        payWalletRechargePackageDO.setBonusPrice( bean.getBonusPrice() );
        if ( bean.getStatus() != null ) {
            payWalletRechargePackageDO.setStatus( bean.getStatus().intValue() );
        }

        return payWalletRechargePackageDO;
    }

    @Override
    public PayWalletRechargePackageDO convert(WalletRechargePackageUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        PayWalletRechargePackageDO payWalletRechargePackageDO = new PayWalletRechargePackageDO();

        payWalletRechargePackageDO.setId( bean.getId() );
        payWalletRechargePackageDO.setName( bean.getName() );
        payWalletRechargePackageDO.setPayPrice( bean.getPayPrice() );
        payWalletRechargePackageDO.setBonusPrice( bean.getBonusPrice() );
        if ( bean.getStatus() != null ) {
            payWalletRechargePackageDO.setStatus( bean.getStatus().intValue() );
        }

        return payWalletRechargePackageDO;
    }

    @Override
    public WalletRechargePackageRespVO convert(PayWalletRechargePackageDO bean) {
        if ( bean == null ) {
            return null;
        }

        WalletRechargePackageRespVO walletRechargePackageRespVO = new WalletRechargePackageRespVO();

        walletRechargePackageRespVO.setName( bean.getName() );
        walletRechargePackageRespVO.setPayPrice( bean.getPayPrice() );
        walletRechargePackageRespVO.setBonusPrice( bean.getBonusPrice() );
        if ( bean.getStatus() != null ) {
            walletRechargePackageRespVO.setStatus( bean.getStatus().byteValue() );
        }
        walletRechargePackageRespVO.setId( bean.getId() );
        walletRechargePackageRespVO.setCreateTime( bean.getCreateTime() );

        return walletRechargePackageRespVO;
    }

    @Override
    public List<WalletRechargePackageRespVO> convertList(List<PayWalletRechargePackageDO> list) {
        if ( list == null ) {
            return null;
        }

        List<WalletRechargePackageRespVO> list1 = new ArrayList<WalletRechargePackageRespVO>( list.size() );
        for ( PayWalletRechargePackageDO payWalletRechargePackageDO : list ) {
            list1.add( convert( payWalletRechargePackageDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<WalletRechargePackageRespVO> convertPage(PageResult<PayWalletRechargePackageDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<WalletRechargePackageRespVO> pageResult = new PageResult<WalletRechargePackageRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }
}
