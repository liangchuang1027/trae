package cn.iocoder.yudao.module.mp.convert.message;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.mp.controller.admin.message.vo.autoreply.MpAutoReplyCreateReqVO;
import cn.iocoder.yudao.module.mp.controller.admin.message.vo.autoreply.MpAutoReplyRespVO;
import cn.iocoder.yudao.module.mp.controller.admin.message.vo.autoreply.MpAutoReplyUpdateReqVO;
import cn.iocoder.yudao.module.mp.dal.dataobject.message.MpAutoReplyDO;
import cn.iocoder.yudao.module.mp.dal.dataobject.message.MpMessageDO;
import cn.iocoder.yudao.module.mp.service.message.bo.MpMessageSendOutReqBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-28T10:44:07+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class MpAutoReplyConvertImpl implements MpAutoReplyConvert {

    @Override
    public MpMessageSendOutReqBO convert(String openid, MpAutoReplyDO reply) {
        if ( openid == null && reply == null ) {
            return null;
        }

        MpMessageSendOutReqBO mpMessageSendOutReqBO = new MpMessageSendOutReqBO();

        if ( reply != null ) {
            mpMessageSendOutReqBO.setAppId( reply.getAppId() );
            mpMessageSendOutReqBO.setType( reply.getResponseMessageType() );
            mpMessageSendOutReqBO.setContent( reply.getResponseContent() );
            mpMessageSendOutReqBO.setMediaId( reply.getResponseMediaId() );
            mpMessageSendOutReqBO.setTitle( reply.getResponseTitle() );
            mpMessageSendOutReqBO.setDescription( reply.getResponseDescription() );
            List<MpMessageDO.Article> list = reply.getResponseArticles();
            if ( list != null ) {
                mpMessageSendOutReqBO.setArticles( new ArrayList<MpMessageDO.Article>( list ) );
            }
        }
        mpMessageSendOutReqBO.setOpenid( openid );

        return mpMessageSendOutReqBO;
    }

    @Override
    public PageResult<MpAutoReplyRespVO> convertPage(PageResult<MpAutoReplyDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<MpAutoReplyRespVO> pageResult = new PageResult<MpAutoReplyRespVO>();

        pageResult.setList( mpAutoReplyDOListToMpAutoReplyRespVOList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public MpAutoReplyRespVO convert(MpAutoReplyDO bean) {
        if ( bean == null ) {
            return null;
        }

        MpAutoReplyRespVO mpAutoReplyRespVO = new MpAutoReplyRespVO();

        mpAutoReplyRespVO.setType( bean.getType() );
        mpAutoReplyRespVO.setRequestKeyword( bean.getRequestKeyword() );
        mpAutoReplyRespVO.setRequestMatch( bean.getRequestMatch() );
        mpAutoReplyRespVO.setRequestMessageType( bean.getRequestMessageType() );
        mpAutoReplyRespVO.setResponseMessageType( bean.getResponseMessageType() );
        mpAutoReplyRespVO.setResponseContent( bean.getResponseContent() );
        mpAutoReplyRespVO.setResponseMediaId( bean.getResponseMediaId() );
        mpAutoReplyRespVO.setResponseMediaUrl( bean.getResponseMediaUrl() );
        mpAutoReplyRespVO.setResponseThumbMediaId( bean.getResponseThumbMediaId() );
        mpAutoReplyRespVO.setResponseThumbMediaUrl( bean.getResponseThumbMediaUrl() );
        mpAutoReplyRespVO.setResponseTitle( bean.getResponseTitle() );
        mpAutoReplyRespVO.setResponseDescription( bean.getResponseDescription() );
        List<MpMessageDO.Article> list = bean.getResponseArticles();
        if ( list != null ) {
            mpAutoReplyRespVO.setResponseArticles( new ArrayList<MpMessageDO.Article>( list ) );
        }
        mpAutoReplyRespVO.setResponseMusicUrl( bean.getResponseMusicUrl() );
        mpAutoReplyRespVO.setResponseHqMusicUrl( bean.getResponseHqMusicUrl() );
        mpAutoReplyRespVO.setId( bean.getId() );
        mpAutoReplyRespVO.setAccountId( bean.getAccountId() );
        mpAutoReplyRespVO.setAppId( bean.getAppId() );
        mpAutoReplyRespVO.setCreateTime( bean.getCreateTime() );

        return mpAutoReplyRespVO;
    }

    @Override
    public MpAutoReplyDO convert(MpAutoReplyCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        MpAutoReplyDO mpAutoReplyDO = new MpAutoReplyDO();

        mpAutoReplyDO.setAccountId( bean.getAccountId() );
        mpAutoReplyDO.setType( bean.getType() );
        mpAutoReplyDO.setRequestKeyword( bean.getRequestKeyword() );
        mpAutoReplyDO.setRequestMatch( bean.getRequestMatch() );
        mpAutoReplyDO.setRequestMessageType( bean.getRequestMessageType() );
        mpAutoReplyDO.setResponseMessageType( bean.getResponseMessageType() );
        mpAutoReplyDO.setResponseContent( bean.getResponseContent() );
        mpAutoReplyDO.setResponseMediaId( bean.getResponseMediaId() );
        mpAutoReplyDO.setResponseMediaUrl( bean.getResponseMediaUrl() );
        mpAutoReplyDO.setResponseTitle( bean.getResponseTitle() );
        mpAutoReplyDO.setResponseDescription( bean.getResponseDescription() );
        mpAutoReplyDO.setResponseThumbMediaId( bean.getResponseThumbMediaId() );
        mpAutoReplyDO.setResponseThumbMediaUrl( bean.getResponseThumbMediaUrl() );
        List<MpMessageDO.Article> list = bean.getResponseArticles();
        if ( list != null ) {
            mpAutoReplyDO.setResponseArticles( new ArrayList<MpMessageDO.Article>( list ) );
        }
        mpAutoReplyDO.setResponseMusicUrl( bean.getResponseMusicUrl() );
        mpAutoReplyDO.setResponseHqMusicUrl( bean.getResponseHqMusicUrl() );

        return mpAutoReplyDO;
    }

    @Override
    public MpAutoReplyDO convert(MpAutoReplyUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        MpAutoReplyDO mpAutoReplyDO = new MpAutoReplyDO();

        mpAutoReplyDO.setId( bean.getId() );
        mpAutoReplyDO.setType( bean.getType() );
        mpAutoReplyDO.setRequestKeyword( bean.getRequestKeyword() );
        mpAutoReplyDO.setRequestMatch( bean.getRequestMatch() );
        mpAutoReplyDO.setRequestMessageType( bean.getRequestMessageType() );
        mpAutoReplyDO.setResponseMessageType( bean.getResponseMessageType() );
        mpAutoReplyDO.setResponseContent( bean.getResponseContent() );
        mpAutoReplyDO.setResponseMediaId( bean.getResponseMediaId() );
        mpAutoReplyDO.setResponseMediaUrl( bean.getResponseMediaUrl() );
        mpAutoReplyDO.setResponseTitle( bean.getResponseTitle() );
        mpAutoReplyDO.setResponseDescription( bean.getResponseDescription() );
        mpAutoReplyDO.setResponseThumbMediaId( bean.getResponseThumbMediaId() );
        mpAutoReplyDO.setResponseThumbMediaUrl( bean.getResponseThumbMediaUrl() );
        List<MpMessageDO.Article> list = bean.getResponseArticles();
        if ( list != null ) {
            mpAutoReplyDO.setResponseArticles( new ArrayList<MpMessageDO.Article>( list ) );
        }
        mpAutoReplyDO.setResponseMusicUrl( bean.getResponseMusicUrl() );
        mpAutoReplyDO.setResponseHqMusicUrl( bean.getResponseHqMusicUrl() );

        return mpAutoReplyDO;
    }

    protected List<MpAutoReplyRespVO> mpAutoReplyDOListToMpAutoReplyRespVOList(List<MpAutoReplyDO> list) {
        if ( list == null ) {
            return null;
        }

        List<MpAutoReplyRespVO> list1 = new ArrayList<MpAutoReplyRespVO>( list.size() );
        for ( MpAutoReplyDO mpAutoReplyDO : list ) {
            list1.add( convert( mpAutoReplyDO ) );
        }

        return list1;
    }
}
