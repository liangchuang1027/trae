package cn.iocoder.yudao.module.pay.convert.app;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.pay.controller.admin.app.vo.PayAppCreateReqVO;
import cn.iocoder.yudao.module.pay.controller.admin.app.vo.PayAppPageItemRespVO;
import cn.iocoder.yudao.module.pay.controller.admin.app.vo.PayAppRespVO;
import cn.iocoder.yudao.module.pay.controller.admin.app.vo.PayAppUpdateReqVO;
import cn.iocoder.yudao.module.pay.dal.dataobject.app.PayAppDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-28T10:44:09+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class PayAppConvertImpl implements PayAppConvert {

    @Override
    public PayAppPageItemRespVO pageConvert(PayAppDO bean) {
        if ( bean == null ) {
            return null;
        }

        PayAppPageItemRespVO payAppPageItemRespVO = new PayAppPageItemRespVO();

        payAppPageItemRespVO.setAppKey( bean.getAppKey() );
        payAppPageItemRespVO.setName( bean.getName() );
        payAppPageItemRespVO.setStatus( bean.getStatus() );
        payAppPageItemRespVO.setRemark( bean.getRemark() );
        payAppPageItemRespVO.setOrderNotifyUrl( bean.getOrderNotifyUrl() );
        payAppPageItemRespVO.setRefundNotifyUrl( bean.getRefundNotifyUrl() );
        payAppPageItemRespVO.setTransferNotifyUrl( bean.getTransferNotifyUrl() );
        payAppPageItemRespVO.setId( bean.getId() );
        payAppPageItemRespVO.setCreateTime( bean.getCreateTime() );

        return payAppPageItemRespVO;
    }

    @Override
    public PayAppDO convert(PayAppCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        PayAppDO.PayAppDOBuilder payAppDO = PayAppDO.builder();

        payAppDO.appKey( bean.getAppKey() );
        payAppDO.name( bean.getName() );
        payAppDO.status( bean.getStatus() );
        payAppDO.remark( bean.getRemark() );
        payAppDO.orderNotifyUrl( bean.getOrderNotifyUrl() );
        payAppDO.refundNotifyUrl( bean.getRefundNotifyUrl() );
        payAppDO.transferNotifyUrl( bean.getTransferNotifyUrl() );

        return payAppDO.build();
    }

    @Override
    public PayAppDO convert(PayAppUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        PayAppDO.PayAppDOBuilder payAppDO = PayAppDO.builder();

        payAppDO.id( bean.getId() );
        payAppDO.appKey( bean.getAppKey() );
        payAppDO.name( bean.getName() );
        payAppDO.status( bean.getStatus() );
        payAppDO.remark( bean.getRemark() );
        payAppDO.orderNotifyUrl( bean.getOrderNotifyUrl() );
        payAppDO.refundNotifyUrl( bean.getRefundNotifyUrl() );
        payAppDO.transferNotifyUrl( bean.getTransferNotifyUrl() );

        return payAppDO.build();
    }

    @Override
    public PayAppRespVO convert(PayAppDO bean) {
        if ( bean == null ) {
            return null;
        }

        PayAppRespVO payAppRespVO = new PayAppRespVO();

        payAppRespVO.setName( bean.getName() );
        payAppRespVO.setStatus( bean.getStatus() );
        payAppRespVO.setRemark( bean.getRemark() );
        payAppRespVO.setOrderNotifyUrl( bean.getOrderNotifyUrl() );
        payAppRespVO.setRefundNotifyUrl( bean.getRefundNotifyUrl() );
        payAppRespVO.setTransferNotifyUrl( bean.getTransferNotifyUrl() );
        payAppRespVO.setId( bean.getId() );
        payAppRespVO.setAppKey( bean.getAppKey() );
        payAppRespVO.setCreateTime( bean.getCreateTime() );

        return payAppRespVO;
    }

    @Override
    public List<PayAppRespVO> convertList(List<PayAppDO> list) {
        if ( list == null ) {
            return null;
        }

        List<PayAppRespVO> list1 = new ArrayList<PayAppRespVO>( list.size() );
        for ( PayAppDO payAppDO : list ) {
            list1.add( convert( payAppDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<PayAppPageItemRespVO> convertPage(PageResult<PayAppDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<PayAppPageItemRespVO> pageResult = new PageResult<PayAppPageItemRespVO>();

        pageResult.setList( payAppDOListToPayAppPageItemRespVOList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    protected List<PayAppPageItemRespVO> payAppDOListToPayAppPageItemRespVOList(List<PayAppDO> list) {
        if ( list == null ) {
            return null;
        }

        List<PayAppPageItemRespVO> list1 = new ArrayList<PayAppPageItemRespVO>( list.size() );
        for ( PayAppDO payAppDO : list ) {
            list1.add( pageConvert( payAppDO ) );
        }

        return list1;
    }
}
