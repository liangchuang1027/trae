package cn.iocoder.yudao.module.pay.convert.wallet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.pay.controller.admin.wallet.vo.transaction.PayWalletTransactionRespVO;
import cn.iocoder.yudao.module.pay.dal.dataobject.wallet.PayWalletTransactionDO;
import cn.iocoder.yudao.module.pay.service.wallet.bo.WalletTransactionCreateReqBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-28T10:44:09+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class PayWalletTransactionConvertImpl implements PayWalletTransactionConvert {

    @Override
    public PageResult<PayWalletTransactionRespVO> convertPage2(PageResult<PayWalletTransactionDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<PayWalletTransactionRespVO> pageResult = new PageResult<PayWalletTransactionRespVO>();

        pageResult.setList( payWalletTransactionDOListToPayWalletTransactionRespVOList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public PayWalletTransactionDO convert(WalletTransactionCreateReqBO bean) {
        if ( bean == null ) {
            return null;
        }

        PayWalletTransactionDO payWalletTransactionDO = new PayWalletTransactionDO();

        payWalletTransactionDO.setWalletId( bean.getWalletId() );
        payWalletTransactionDO.setBizType( bean.getBizType() );
        payWalletTransactionDO.setBizId( bean.getBizId() );
        payWalletTransactionDO.setTitle( bean.getTitle() );
        payWalletTransactionDO.setPrice( bean.getPrice() );
        payWalletTransactionDO.setBalance( bean.getBalance() );

        return payWalletTransactionDO;
    }

    protected PayWalletTransactionRespVO payWalletTransactionDOToPayWalletTransactionRespVO(PayWalletTransactionDO payWalletTransactionDO) {
        if ( payWalletTransactionDO == null ) {
            return null;
        }

        PayWalletTransactionRespVO payWalletTransactionRespVO = new PayWalletTransactionRespVO();

        payWalletTransactionRespVO.setId( payWalletTransactionDO.getId() );
        payWalletTransactionRespVO.setWalletId( payWalletTransactionDO.getWalletId() );
        payWalletTransactionRespVO.setBizType( payWalletTransactionDO.getBizType() );
        if ( payWalletTransactionDO.getPrice() != null ) {
            payWalletTransactionRespVO.setPrice( payWalletTransactionDO.getPrice().longValue() );
        }
        payWalletTransactionRespVO.setTitle( payWalletTransactionDO.getTitle() );
        if ( payWalletTransactionDO.getBalance() != null ) {
            payWalletTransactionRespVO.setBalance( payWalletTransactionDO.getBalance().longValue() );
        }
        payWalletTransactionRespVO.setCreateTime( payWalletTransactionDO.getCreateTime() );

        return payWalletTransactionRespVO;
    }

    protected List<PayWalletTransactionRespVO> payWalletTransactionDOListToPayWalletTransactionRespVOList(List<PayWalletTransactionDO> list) {
        if ( list == null ) {
            return null;
        }

        List<PayWalletTransactionRespVO> list1 = new ArrayList<PayWalletTransactionRespVO>( list.size() );
        for ( PayWalletTransactionDO payWalletTransactionDO : list ) {
            list1.add( payWalletTransactionDOToPayWalletTransactionRespVO( payWalletTransactionDO ) );
        }

        return list1;
    }
}
