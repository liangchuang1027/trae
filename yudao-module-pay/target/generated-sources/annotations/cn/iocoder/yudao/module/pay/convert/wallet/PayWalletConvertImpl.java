package cn.iocoder.yudao.module.pay.convert.wallet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.pay.controller.admin.wallet.vo.wallet.PayWalletRespVO;
import cn.iocoder.yudao.module.pay.controller.app.wallet.vo.wallet.AppPayWalletRespVO;
import cn.iocoder.yudao.module.pay.dal.dataobject.wallet.PayWalletDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-28T10:44:09+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class PayWalletConvertImpl implements PayWalletConvert {

    @Override
    public AppPayWalletRespVO convert(PayWalletDO bean) {
        if ( bean == null ) {
            return null;
        }

        AppPayWalletRespVO appPayWalletRespVO = new AppPayWalletRespVO();

        appPayWalletRespVO.setBalance( bean.getBalance() );
        appPayWalletRespVO.setTotalExpense( bean.getTotalExpense() );
        appPayWalletRespVO.setTotalRecharge( bean.getTotalRecharge() );

        return appPayWalletRespVO;
    }

    @Override
    public PayWalletRespVO convert02(PayWalletDO bean) {
        if ( bean == null ) {
            return null;
        }

        PayWalletRespVO payWalletRespVO = new PayWalletRespVO();

        payWalletRespVO.setUserId( bean.getUserId() );
        payWalletRespVO.setUserType( bean.getUserType() );
        payWalletRespVO.setBalance( bean.getBalance() );
        payWalletRespVO.setTotalExpense( bean.getTotalExpense() );
        payWalletRespVO.setTotalRecharge( bean.getTotalRecharge() );
        payWalletRespVO.setFreezePrice( bean.getFreezePrice() );
        payWalletRespVO.setId( bean.getId() );
        payWalletRespVO.setCreateTime( bean.getCreateTime() );

        return payWalletRespVO;
    }

    @Override
    public PageResult<PayWalletRespVO> convertPage(PageResult<PayWalletDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<PayWalletRespVO> pageResult = new PageResult<PayWalletRespVO>();

        pageResult.setList( payWalletDOListToPayWalletRespVOList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    protected List<PayWalletRespVO> payWalletDOListToPayWalletRespVOList(List<PayWalletDO> list) {
        if ( list == null ) {
            return null;
        }

        List<PayWalletRespVO> list1 = new ArrayList<PayWalletRespVO>( list.size() );
        for ( PayWalletDO payWalletDO : list ) {
            list1.add( convert02( payWalletDO ) );
        }

        return list1;
    }
}
