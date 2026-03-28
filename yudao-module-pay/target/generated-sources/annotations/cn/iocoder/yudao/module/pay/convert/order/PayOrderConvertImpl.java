package cn.iocoder.yudao.module.pay.convert.order;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.pay.api.order.dto.PayOrderCreateReqDTO;
import cn.iocoder.yudao.module.pay.api.order.dto.PayOrderRespDTO;
import cn.iocoder.yudao.module.pay.controller.admin.order.vo.PayOrderDetailsRespVO;
import cn.iocoder.yudao.module.pay.controller.admin.order.vo.PayOrderExcelVO;
import cn.iocoder.yudao.module.pay.controller.admin.order.vo.PayOrderPageItemRespVO;
import cn.iocoder.yudao.module.pay.controller.admin.order.vo.PayOrderRespVO;
import cn.iocoder.yudao.module.pay.controller.admin.order.vo.PayOrderSubmitReqVO;
import cn.iocoder.yudao.module.pay.controller.admin.order.vo.PayOrderSubmitRespVO;
import cn.iocoder.yudao.module.pay.controller.app.order.vo.AppPayOrderSubmitRespVO;
import cn.iocoder.yudao.module.pay.dal.dataobject.order.PayOrderDO;
import cn.iocoder.yudao.module.pay.dal.dataobject.order.PayOrderExtensionDO;
import cn.iocoder.yudao.module.pay.framework.pay.core.client.dto.order.PayOrderUnifiedReqDTO;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-28T10:44:09+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class PayOrderConvertImpl implements PayOrderConvert {

    @Override
    public PayOrderRespVO convert(PayOrderDO bean) {
        if ( bean == null ) {
            return null;
        }

        PayOrderRespVO payOrderRespVO = new PayOrderRespVO();

        payOrderRespVO.setAppId( bean.getAppId() );
        payOrderRespVO.setChannelId( bean.getChannelId() );
        payOrderRespVO.setChannelCode( bean.getChannelCode() );
        payOrderRespVO.setMerchantOrderId( bean.getMerchantOrderId() );
        payOrderRespVO.setSubject( bean.getSubject() );
        payOrderRespVO.setBody( bean.getBody() );
        payOrderRespVO.setNotifyUrl( bean.getNotifyUrl() );
        if ( bean.getPrice() != null ) {
            payOrderRespVO.setPrice( bean.getPrice().longValue() );
        }
        payOrderRespVO.setChannelFeeRate( bean.getChannelFeeRate() );
        payOrderRespVO.setChannelFeePrice( bean.getChannelFeePrice() );
        payOrderRespVO.setStatus( bean.getStatus() );
        payOrderRespVO.setUserIp( bean.getUserIp() );
        payOrderRespVO.setExpireTime( bean.getExpireTime() );
        payOrderRespVO.setSuccessTime( bean.getSuccessTime() );
        payOrderRespVO.setExtensionId( bean.getExtensionId() );
        payOrderRespVO.setNo( bean.getNo() );
        if ( bean.getRefundPrice() != null ) {
            payOrderRespVO.setRefundPrice( bean.getRefundPrice().longValue() );
        }
        payOrderRespVO.setChannelUserId( bean.getChannelUserId() );
        payOrderRespVO.setChannelOrderNo( bean.getChannelOrderNo() );
        payOrderRespVO.setId( bean.getId() );
        payOrderRespVO.setCreateTime( bean.getCreateTime() );

        return payOrderRespVO;
    }

    @Override
    public PayOrderRespDTO convert2(PayOrderDO order) {
        if ( order == null ) {
            return null;
        }

        PayOrderRespDTO payOrderRespDTO = new PayOrderRespDTO();

        payOrderRespDTO.setId( order.getId() );
        payOrderRespDTO.setChannelCode( order.getChannelCode() );
        payOrderRespDTO.setMerchantOrderId( order.getMerchantOrderId() );
        payOrderRespDTO.setSubject( order.getSubject() );
        payOrderRespDTO.setPrice( order.getPrice() );
        payOrderRespDTO.setStatus( order.getStatus() );
        payOrderRespDTO.setSuccessTime( order.getSuccessTime() );
        payOrderRespDTO.setChannelUserId( order.getChannelUserId() );
        payOrderRespDTO.setChannelOrderNo( order.getChannelOrderNo() );

        return payOrderRespDTO;
    }

    @Override
    public PayOrderDetailsRespVO convertDetail(PayOrderDO bean) {
        if ( bean == null ) {
            return null;
        }

        PayOrderDetailsRespVO payOrderDetailsRespVO = new PayOrderDetailsRespVO();

        payOrderDetailsRespVO.setAppId( bean.getAppId() );
        payOrderDetailsRespVO.setChannelId( bean.getChannelId() );
        payOrderDetailsRespVO.setChannelCode( bean.getChannelCode() );
        payOrderDetailsRespVO.setMerchantOrderId( bean.getMerchantOrderId() );
        payOrderDetailsRespVO.setSubject( bean.getSubject() );
        payOrderDetailsRespVO.setBody( bean.getBody() );
        payOrderDetailsRespVO.setNotifyUrl( bean.getNotifyUrl() );
        if ( bean.getPrice() != null ) {
            payOrderDetailsRespVO.setPrice( bean.getPrice().longValue() );
        }
        payOrderDetailsRespVO.setChannelFeeRate( bean.getChannelFeeRate() );
        payOrderDetailsRespVO.setChannelFeePrice( bean.getChannelFeePrice() );
        payOrderDetailsRespVO.setStatus( bean.getStatus() );
        payOrderDetailsRespVO.setUserIp( bean.getUserIp() );
        payOrderDetailsRespVO.setExpireTime( bean.getExpireTime() );
        payOrderDetailsRespVO.setSuccessTime( bean.getSuccessTime() );
        payOrderDetailsRespVO.setExtensionId( bean.getExtensionId() );
        payOrderDetailsRespVO.setNo( bean.getNo() );
        if ( bean.getRefundPrice() != null ) {
            payOrderDetailsRespVO.setRefundPrice( bean.getRefundPrice().longValue() );
        }
        payOrderDetailsRespVO.setChannelUserId( bean.getChannelUserId() );
        payOrderDetailsRespVO.setChannelOrderNo( bean.getChannelOrderNo() );
        payOrderDetailsRespVO.setId( bean.getId() );
        payOrderDetailsRespVO.setCreateTime( bean.getCreateTime() );
        payOrderDetailsRespVO.setUpdateTime( bean.getUpdateTime() );

        return payOrderDetailsRespVO;
    }

    @Override
    public PayOrderDetailsRespVO.PayOrderExtension convert(PayOrderExtensionDO bean) {
        if ( bean == null ) {
            return null;
        }

        PayOrderDetailsRespVO.PayOrderExtension payOrderExtension = new PayOrderDetailsRespVO.PayOrderExtension();

        payOrderExtension.setNo( bean.getNo() );
        payOrderExtension.setChannelNotifyData( bean.getChannelNotifyData() );

        return payOrderExtension;
    }

    @Override
    public PageResult<PayOrderPageItemRespVO> convertPage(PageResult<PayOrderDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<PayOrderPageItemRespVO> pageResult = new PageResult<PayOrderPageItemRespVO>();

        pageResult.setList( payOrderDOListToPayOrderPageItemRespVOList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public PayOrderExcelVO convertExcel(PayOrderDO bean) {
        if ( bean == null ) {
            return null;
        }

        PayOrderExcelVO payOrderExcelVO = new PayOrderExcelVO();

        payOrderExcelVO.setId( bean.getId() );
        payOrderExcelVO.setCreateTime( bean.getCreateTime() );
        payOrderExcelVO.setPrice( bean.getPrice() );
        payOrderExcelVO.setRefundPrice( bean.getRefundPrice() );
        payOrderExcelVO.setChannelFeePrice( bean.getChannelFeePrice() );
        payOrderExcelVO.setMerchantOrderId( bean.getMerchantOrderId() );
        payOrderExcelVO.setNo( bean.getNo() );
        payOrderExcelVO.setChannelOrderNo( bean.getChannelOrderNo() );
        payOrderExcelVO.setStatus( bean.getStatus() );
        payOrderExcelVO.setChannelCode( bean.getChannelCode() );
        payOrderExcelVO.setSuccessTime( bean.getSuccessTime() );
        payOrderExcelVO.setExpireTime( bean.getExpireTime() );
        payOrderExcelVO.setSubject( bean.getSubject() );
        payOrderExcelVO.setBody( bean.getBody() );

        return payOrderExcelVO;
    }

    @Override
    public PayOrderDO convert(PayOrderCreateReqDTO bean) {
        if ( bean == null ) {
            return null;
        }

        PayOrderDO.PayOrderDOBuilder payOrderDO = PayOrderDO.builder();

        payOrderDO.merchantOrderId( bean.getMerchantOrderId() );
        payOrderDO.subject( bean.getSubject() );
        payOrderDO.body( bean.getBody() );
        payOrderDO.price( bean.getPrice() );
        payOrderDO.userIp( bean.getUserIp() );
        payOrderDO.expireTime( bean.getExpireTime() );

        return payOrderDO.build();
    }

    @Override
    public PayOrderExtensionDO convert(PayOrderSubmitReqVO bean, String userIp) {
        if ( bean == null && userIp == null ) {
            return null;
        }

        PayOrderExtensionDO.PayOrderExtensionDOBuilder payOrderExtensionDO = PayOrderExtensionDO.builder();

        if ( bean != null ) {
            payOrderExtensionDO.channelCode( bean.getChannelCode() );
            Map<String, String> map = bean.getChannelExtras();
            if ( map != null ) {
                payOrderExtensionDO.channelExtras( new LinkedHashMap<String, String>( map ) );
            }
        }
        payOrderExtensionDO.userIp( userIp );

        return payOrderExtensionDO.build();
    }

    @Override
    public PayOrderUnifiedReqDTO convert2(PayOrderSubmitReqVO reqVO, String userIp) {
        if ( reqVO == null && userIp == null ) {
            return null;
        }

        PayOrderUnifiedReqDTO payOrderUnifiedReqDTO = new PayOrderUnifiedReqDTO();

        if ( reqVO != null ) {
            payOrderUnifiedReqDTO.setReturnUrl( reqVO.getReturnUrl() );
            Map<String, String> map = reqVO.getChannelExtras();
            if ( map != null ) {
                payOrderUnifiedReqDTO.setChannelExtras( new LinkedHashMap<String, String>( map ) );
            }
            payOrderUnifiedReqDTO.setDisplayMode( reqVO.getDisplayMode() );
        }
        payOrderUnifiedReqDTO.setUserIp( userIp );

        return payOrderUnifiedReqDTO;
    }

    @Override
    public PayOrderSubmitRespVO convert(PayOrderDO order, cn.iocoder.yudao.module.pay.framework.pay.core.client.dto.order.PayOrderRespDTO respDTO) {
        if ( order == null && respDTO == null ) {
            return null;
        }

        PayOrderSubmitRespVO payOrderSubmitRespVO = new PayOrderSubmitRespVO();

        if ( order != null ) {
            payOrderSubmitRespVO.setStatus( order.getStatus() );
        }
        if ( respDTO != null ) {
            payOrderSubmitRespVO.setDisplayMode( respDTO.getDisplayMode() );
            payOrderSubmitRespVO.setDisplayContent( respDTO.getDisplayContent() );
        }

        return payOrderSubmitRespVO;
    }

    @Override
    public AppPayOrderSubmitRespVO convert3(PayOrderSubmitRespVO bean) {
        if ( bean == null ) {
            return null;
        }

        AppPayOrderSubmitRespVO appPayOrderSubmitRespVO = new AppPayOrderSubmitRespVO();

        appPayOrderSubmitRespVO.setStatus( bean.getStatus() );
        appPayOrderSubmitRespVO.setDisplayMode( bean.getDisplayMode() );
        appPayOrderSubmitRespVO.setDisplayContent( bean.getDisplayContent() );

        return appPayOrderSubmitRespVO;
    }

    protected PayOrderPageItemRespVO payOrderDOToPayOrderPageItemRespVO(PayOrderDO payOrderDO) {
        if ( payOrderDO == null ) {
            return null;
        }

        PayOrderPageItemRespVO payOrderPageItemRespVO = new PayOrderPageItemRespVO();

        payOrderPageItemRespVO.setAppId( payOrderDO.getAppId() );
        payOrderPageItemRespVO.setChannelId( payOrderDO.getChannelId() );
        payOrderPageItemRespVO.setChannelCode( payOrderDO.getChannelCode() );
        payOrderPageItemRespVO.setMerchantOrderId( payOrderDO.getMerchantOrderId() );
        payOrderPageItemRespVO.setSubject( payOrderDO.getSubject() );
        payOrderPageItemRespVO.setBody( payOrderDO.getBody() );
        payOrderPageItemRespVO.setNotifyUrl( payOrderDO.getNotifyUrl() );
        if ( payOrderDO.getPrice() != null ) {
            payOrderPageItemRespVO.setPrice( payOrderDO.getPrice().longValue() );
        }
        payOrderPageItemRespVO.setChannelFeeRate( payOrderDO.getChannelFeeRate() );
        payOrderPageItemRespVO.setChannelFeePrice( payOrderDO.getChannelFeePrice() );
        payOrderPageItemRespVO.setStatus( payOrderDO.getStatus() );
        payOrderPageItemRespVO.setUserIp( payOrderDO.getUserIp() );
        payOrderPageItemRespVO.setExpireTime( payOrderDO.getExpireTime() );
        payOrderPageItemRespVO.setSuccessTime( payOrderDO.getSuccessTime() );
        payOrderPageItemRespVO.setExtensionId( payOrderDO.getExtensionId() );
        payOrderPageItemRespVO.setNo( payOrderDO.getNo() );
        if ( payOrderDO.getRefundPrice() != null ) {
            payOrderPageItemRespVO.setRefundPrice( payOrderDO.getRefundPrice().longValue() );
        }
        payOrderPageItemRespVO.setChannelUserId( payOrderDO.getChannelUserId() );
        payOrderPageItemRespVO.setChannelOrderNo( payOrderDO.getChannelOrderNo() );
        payOrderPageItemRespVO.setId( payOrderDO.getId() );
        payOrderPageItemRespVO.setCreateTime( payOrderDO.getCreateTime() );

        return payOrderPageItemRespVO;
    }

    protected List<PayOrderPageItemRespVO> payOrderDOListToPayOrderPageItemRespVOList(List<PayOrderDO> list) {
        if ( list == null ) {
            return null;
        }

        List<PayOrderPageItemRespVO> list1 = new ArrayList<PayOrderPageItemRespVO>( list.size() );
        for ( PayOrderDO payOrderDO : list ) {
            list1.add( payOrderDOToPayOrderPageItemRespVO( payOrderDO ) );
        }

        return list1;
    }
}
