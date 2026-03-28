package cn.iocoder.yudao.module.pay.convert.channel;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.pay.controller.admin.channel.vo.PayChannelCreateReqVO;
import cn.iocoder.yudao.module.pay.controller.admin.channel.vo.PayChannelRespVO;
import cn.iocoder.yudao.module.pay.controller.admin.channel.vo.PayChannelUpdateReqVO;
import cn.iocoder.yudao.module.pay.dal.dataobject.channel.PayChannelDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-28T10:44:09+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class PayChannelConvertImpl implements PayChannelConvert {

    @Override
    public PayChannelDO convert(PayChannelCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        PayChannelDO.PayChannelDOBuilder payChannelDO = PayChannelDO.builder();

        payChannelDO.code( bean.getCode() );
        payChannelDO.status( bean.getStatus() );
        payChannelDO.feeRate( bean.getFeeRate() );
        payChannelDO.remark( bean.getRemark() );
        payChannelDO.appId( bean.getAppId() );

        return payChannelDO.build();
    }

    @Override
    public PayChannelDO convert(PayChannelUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        PayChannelDO.PayChannelDOBuilder payChannelDO = PayChannelDO.builder();

        payChannelDO.id( bean.getId() );
        payChannelDO.status( bean.getStatus() );
        payChannelDO.feeRate( bean.getFeeRate() );
        payChannelDO.remark( bean.getRemark() );
        payChannelDO.appId( bean.getAppId() );

        return payChannelDO.build();
    }

    @Override
    public PayChannelRespVO convert(PayChannelDO bean) {
        if ( bean == null ) {
            return null;
        }

        PayChannelRespVO payChannelRespVO = new PayChannelRespVO();

        payChannelRespVO.setStatus( bean.getStatus() );
        payChannelRespVO.setRemark( bean.getRemark() );
        payChannelRespVO.setFeeRate( bean.getFeeRate() );
        payChannelRespVO.setAppId( bean.getAppId() );
        payChannelRespVO.setId( bean.getId() );
        payChannelRespVO.setCreateTime( bean.getCreateTime() );
        payChannelRespVO.setCode( bean.getCode() );

        payChannelRespVO.setConfig( cn.iocoder.yudao.framework.common.util.json.JsonUtils.toJsonString(bean.getConfig()) );

        return payChannelRespVO;
    }

    @Override
    public PageResult<PayChannelRespVO> convertPage(PageResult<PayChannelDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<PayChannelRespVO> pageResult = new PageResult<PayChannelRespVO>();

        pageResult.setList( payChannelDOListToPayChannelRespVOList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    protected List<PayChannelRespVO> payChannelDOListToPayChannelRespVOList(List<PayChannelDO> list) {
        if ( list == null ) {
            return null;
        }

        List<PayChannelRespVO> list1 = new ArrayList<PayChannelRespVO>( list.size() );
        for ( PayChannelDO payChannelDO : list ) {
            list1.add( convert( payChannelDO ) );
        }

        return list1;
    }
}
