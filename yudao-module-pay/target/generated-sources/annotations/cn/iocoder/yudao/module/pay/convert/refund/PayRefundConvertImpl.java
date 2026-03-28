package cn.iocoder.yudao.module.pay.convert.refund;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.pay.api.refund.dto.PayRefundCreateReqDTO;
import cn.iocoder.yudao.module.pay.controller.admin.refund.vo.PayRefundDetailsRespVO;
import cn.iocoder.yudao.module.pay.controller.admin.refund.vo.PayRefundExcelVO;
import cn.iocoder.yudao.module.pay.controller.admin.refund.vo.PayRefundPageItemRespVO;
import cn.iocoder.yudao.module.pay.dal.dataobject.order.PayOrderDO;
import cn.iocoder.yudao.module.pay.dal.dataobject.refund.PayRefundDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-28T10:44:08+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class PayRefundConvertImpl implements PayRefundConvert {

    @Override
    public PayRefundDetailsRespVO convert(PayRefundDO bean) {
        if ( bean == null ) {
            return null;
        }

        PayRefundDetailsRespVO payRefundDetailsRespVO = new PayRefundDetailsRespVO();

        payRefundDetailsRespVO.setNo( bean.getNo() );
        payRefundDetailsRespVO.setAppId( bean.getAppId() );
        payRefundDetailsRespVO.setChannelId( bean.getChannelId() );
        payRefundDetailsRespVO.setChannelCode( bean.getChannelCode() );
        payRefundDetailsRespVO.setOrderId( bean.getOrderId() );
        payRefundDetailsRespVO.setMerchantOrderId( bean.getMerchantOrderId() );
        payRefundDetailsRespVO.setMerchantRefundId( bean.getMerchantRefundId() );
        payRefundDetailsRespVO.setNotifyUrl( bean.getNotifyUrl() );
        payRefundDetailsRespVO.setStatus( bean.getStatus() );
        if ( bean.getPayPrice() != null ) {
            payRefundDetailsRespVO.setPayPrice( bean.getPayPrice().longValue() );
        }
        if ( bean.getRefundPrice() != null ) {
            payRefundDetailsRespVO.setRefundPrice( bean.getRefundPrice().longValue() );
        }
        payRefundDetailsRespVO.setReason( bean.getReason() );
        payRefundDetailsRespVO.setUserIp( bean.getUserIp() );
        payRefundDetailsRespVO.setChannelOrderNo( bean.getChannelOrderNo() );
        payRefundDetailsRespVO.setChannelRefundNo( bean.getChannelRefundNo() );
        payRefundDetailsRespVO.setSuccessTime( bean.getSuccessTime() );
        payRefundDetailsRespVO.setChannelErrorCode( bean.getChannelErrorCode() );
        payRefundDetailsRespVO.setChannelErrorMsg( bean.getChannelErrorMsg() );
        payRefundDetailsRespVO.setChannelNotifyData( bean.getChannelNotifyData() );
        payRefundDetailsRespVO.setId( bean.getId() );
        payRefundDetailsRespVO.setCreateTime( bean.getCreateTime() );
        payRefundDetailsRespVO.setUpdateTime( bean.getUpdateTime() );

        return payRefundDetailsRespVO;
    }

    @Override
    public PayRefundDetailsRespVO.Order convert(PayOrderDO bean) {
        if ( bean == null ) {
            return null;
        }

        PayRefundDetailsRespVO.Order order = new PayRefundDetailsRespVO.Order();

        order.setSubject( bean.getSubject() );

        return order;
    }

    @Override
    public PageResult<PayRefundPageItemRespVO> convertPage(PageResult<PayRefundDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<PayRefundPageItemRespVO> pageResult = new PageResult<PayRefundPageItemRespVO>();

        pageResult.setList( payRefundDOListToPayRefundPageItemRespVOList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public PayRefundDO convert(PayRefundCreateReqDTO bean) {
        if ( bean == null ) {
            return null;
        }

        PayRefundDO.PayRefundDOBuilder payRefundDO = PayRefundDO.builder();

        payRefundDO.merchantOrderId( bean.getMerchantOrderId() );
        payRefundDO.merchantRefundId( bean.getMerchantRefundId() );
        payRefundDO.reason( bean.getReason() );
        payRefundDO.userIp( bean.getUserIp() );

        return payRefundDO.build();
    }

    @Override
    public PayRefundExcelVO convertExcel(PayRefundDO bean) {
        if ( bean == null ) {
            return null;
        }

        PayRefundExcelVO payRefundExcelVO = new PayRefundExcelVO();

        payRefundExcelVO.setId( bean.getId() );
        payRefundExcelVO.setCreateTime( bean.getCreateTime() );
        payRefundExcelVO.setPayPrice( bean.getPayPrice() );
        payRefundExcelVO.setRefundPrice( bean.getRefundPrice() );
        payRefundExcelVO.setMerchantRefundId( bean.getMerchantRefundId() );
        payRefundExcelVO.setNo( bean.getNo() );
        payRefundExcelVO.setChannelRefundNo( bean.getChannelRefundNo() );
        payRefundExcelVO.setMerchantOrderId( bean.getMerchantOrderId() );
        payRefundExcelVO.setChannelOrderNo( bean.getChannelOrderNo() );
        payRefundExcelVO.setStatus( bean.getStatus() );
        payRefundExcelVO.setChannelCode( bean.getChannelCode() );
        payRefundExcelVO.setSuccessTime( bean.getSuccessTime() );
        payRefundExcelVO.setReason( bean.getReason() );

        return payRefundExcelVO;
    }

    protected PayRefundPageItemRespVO payRefundDOToPayRefundPageItemRespVO(PayRefundDO payRefundDO) {
        if ( payRefundDO == null ) {
            return null;
        }

        PayRefundPageItemRespVO payRefundPageItemRespVO = new PayRefundPageItemRespVO();

        payRefundPageItemRespVO.setNo( payRefundDO.getNo() );
        payRefundPageItemRespVO.setAppId( payRefundDO.getAppId() );
        payRefundPageItemRespVO.setChannelId( payRefundDO.getChannelId() );
        payRefundPageItemRespVO.setChannelCode( payRefundDO.getChannelCode() );
        payRefundPageItemRespVO.setOrderId( payRefundDO.getOrderId() );
        payRefundPageItemRespVO.setMerchantOrderId( payRefundDO.getMerchantOrderId() );
        payRefundPageItemRespVO.setMerchantRefundId( payRefundDO.getMerchantRefundId() );
        payRefundPageItemRespVO.setNotifyUrl( payRefundDO.getNotifyUrl() );
        payRefundPageItemRespVO.setStatus( payRefundDO.getStatus() );
        if ( payRefundDO.getPayPrice() != null ) {
            payRefundPageItemRespVO.setPayPrice( payRefundDO.getPayPrice().longValue() );
        }
        if ( payRefundDO.getRefundPrice() != null ) {
            payRefundPageItemRespVO.setRefundPrice( payRefundDO.getRefundPrice().longValue() );
        }
        payRefundPageItemRespVO.setReason( payRefundDO.getReason() );
        payRefundPageItemRespVO.setUserIp( payRefundDO.getUserIp() );
        payRefundPageItemRespVO.setChannelOrderNo( payRefundDO.getChannelOrderNo() );
        payRefundPageItemRespVO.setChannelRefundNo( payRefundDO.getChannelRefundNo() );
        payRefundPageItemRespVO.setSuccessTime( payRefundDO.getSuccessTime() );
        payRefundPageItemRespVO.setChannelErrorCode( payRefundDO.getChannelErrorCode() );
        payRefundPageItemRespVO.setChannelErrorMsg( payRefundDO.getChannelErrorMsg() );
        payRefundPageItemRespVO.setChannelNotifyData( payRefundDO.getChannelNotifyData() );
        payRefundPageItemRespVO.setId( payRefundDO.getId() );
        payRefundPageItemRespVO.setCreateTime( payRefundDO.getCreateTime() );

        return payRefundPageItemRespVO;
    }

    protected List<PayRefundPageItemRespVO> payRefundDOListToPayRefundPageItemRespVOList(List<PayRefundDO> list) {
        if ( list == null ) {
            return null;
        }

        List<PayRefundPageItemRespVO> list1 = new ArrayList<PayRefundPageItemRespVO>( list.size() );
        for ( PayRefundDO payRefundDO : list ) {
            list1.add( payRefundDOToPayRefundPageItemRespVO( payRefundDO ) );
        }

        return list1;
    }
}
