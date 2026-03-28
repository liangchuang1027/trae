package cn.iocoder.yudao.module.mp.convert.statistics;

import cn.iocoder.yudao.module.mp.controller.admin.statistics.vo.MpStatisticsInterfaceSummaryRespVO;
import cn.iocoder.yudao.module.mp.controller.admin.statistics.vo.MpStatisticsUpstreamMessageRespVO;
import cn.iocoder.yudao.module.mp.controller.admin.statistics.vo.MpStatisticsUserCumulateRespVO;
import cn.iocoder.yudao.module.mp.controller.admin.statistics.vo.MpStatisticsUserSummaryRespVO;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeInterfaceResult;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeMsgResult;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeUserCumulate;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeUserSummary;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-28T10:44:07+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class MpStatisticsConvertImpl implements MpStatisticsConvert {

    @Override
    public List<MpStatisticsUserSummaryRespVO> convertList01(List<WxDataCubeUserSummary> list) {
        if ( list == null ) {
            return null;
        }

        List<MpStatisticsUserSummaryRespVO> list1 = new ArrayList<MpStatisticsUserSummaryRespVO>( list.size() );
        for ( WxDataCubeUserSummary wxDataCubeUserSummary : list ) {
            list1.add( wxDataCubeUserSummaryToMpStatisticsUserSummaryRespVO( wxDataCubeUserSummary ) );
        }

        return list1;
    }

    @Override
    public List<MpStatisticsUserCumulateRespVO> convertList02(List<WxDataCubeUserCumulate> list) {
        if ( list == null ) {
            return null;
        }

        List<MpStatisticsUserCumulateRespVO> list1 = new ArrayList<MpStatisticsUserCumulateRespVO>( list.size() );
        for ( WxDataCubeUserCumulate wxDataCubeUserCumulate : list ) {
            list1.add( wxDataCubeUserCumulateToMpStatisticsUserCumulateRespVO( wxDataCubeUserCumulate ) );
        }

        return list1;
    }

    @Override
    public List<MpStatisticsUpstreamMessageRespVO> convertList03(List<WxDataCubeMsgResult> list) {
        if ( list == null ) {
            return null;
        }

        List<MpStatisticsUpstreamMessageRespVO> list1 = new ArrayList<MpStatisticsUpstreamMessageRespVO>( list.size() );
        for ( WxDataCubeMsgResult wxDataCubeMsgResult : list ) {
            list1.add( convert( wxDataCubeMsgResult ) );
        }

        return list1;
    }

    @Override
    public MpStatisticsUpstreamMessageRespVO convert(WxDataCubeMsgResult bean) {
        if ( bean == null ) {
            return null;
        }

        MpStatisticsUpstreamMessageRespVO mpStatisticsUpstreamMessageRespVO = new MpStatisticsUpstreamMessageRespVO();

        mpStatisticsUpstreamMessageRespVO.setMessageUser( bean.getMsgUser() );
        mpStatisticsUpstreamMessageRespVO.setMessageCount( bean.getMsgCount() );

        mpStatisticsUpstreamMessageRespVO.setRefDate( dateFormat0(bean.getRefDate()) );

        return mpStatisticsUpstreamMessageRespVO;
    }

    @Override
    public List<MpStatisticsInterfaceSummaryRespVO> convertList04(List<WxDataCubeInterfaceResult> list) {
        if ( list == null ) {
            return null;
        }

        List<MpStatisticsInterfaceSummaryRespVO> list1 = new ArrayList<MpStatisticsInterfaceSummaryRespVO>( list.size() );
        for ( WxDataCubeInterfaceResult wxDataCubeInterfaceResult : list ) {
            list1.add( convert( wxDataCubeInterfaceResult ) );
        }

        return list1;
    }

    @Override
    public MpStatisticsInterfaceSummaryRespVO convert(WxDataCubeInterfaceResult bean) {
        if ( bean == null ) {
            return null;
        }

        MpStatisticsInterfaceSummaryRespVO mpStatisticsInterfaceSummaryRespVO = new MpStatisticsInterfaceSummaryRespVO();

        mpStatisticsInterfaceSummaryRespVO.setCallbackCount( bean.getCallbackCount() );
        mpStatisticsInterfaceSummaryRespVO.setFailCount( bean.getFailCount() );
        mpStatisticsInterfaceSummaryRespVO.setTotalTimeCost( bean.getTotalTimeCost() );
        mpStatisticsInterfaceSummaryRespVO.setMaxTimeCost( bean.getMaxTimeCost() );

        mpStatisticsInterfaceSummaryRespVO.setRefDate( dateFormat0(bean.getRefDate()) );

        return mpStatisticsInterfaceSummaryRespVO;
    }

    protected MpStatisticsUserSummaryRespVO wxDataCubeUserSummaryToMpStatisticsUserSummaryRespVO(WxDataCubeUserSummary wxDataCubeUserSummary) {
        if ( wxDataCubeUserSummary == null ) {
            return null;
        }

        MpStatisticsUserSummaryRespVO mpStatisticsUserSummaryRespVO = new MpStatisticsUserSummaryRespVO();

        if ( wxDataCubeUserSummary.getRefDate() != null ) {
            mpStatisticsUserSummaryRespVO.setRefDate( LocalDateTime.ofInstant( wxDataCubeUserSummary.getRefDate().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        mpStatisticsUserSummaryRespVO.setUserSource( wxDataCubeUserSummary.getUserSource() );
        mpStatisticsUserSummaryRespVO.setNewUser( wxDataCubeUserSummary.getNewUser() );
        mpStatisticsUserSummaryRespVO.setCancelUser( wxDataCubeUserSummary.getCancelUser() );

        return mpStatisticsUserSummaryRespVO;
    }

    protected MpStatisticsUserCumulateRespVO wxDataCubeUserCumulateToMpStatisticsUserCumulateRespVO(WxDataCubeUserCumulate wxDataCubeUserCumulate) {
        if ( wxDataCubeUserCumulate == null ) {
            return null;
        }

        MpStatisticsUserCumulateRespVO mpStatisticsUserCumulateRespVO = new MpStatisticsUserCumulateRespVO();

        if ( wxDataCubeUserCumulate.getRefDate() != null ) {
            mpStatisticsUserCumulateRespVO.setRefDate( LocalDateTime.ofInstant( wxDataCubeUserCumulate.getRefDate().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        mpStatisticsUserCumulateRespVO.setCumulateUser( wxDataCubeUserCumulate.getCumulateUser() );

        return mpStatisticsUserCumulateRespVO;
    }
}
