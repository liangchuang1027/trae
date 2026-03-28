package cn.iocoder.yudao.module.mp.convert.message;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.mp.controller.admin.message.vo.message.MpMessageRespVO;
import cn.iocoder.yudao.module.mp.dal.dataobject.message.MpMessageDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-28T10:44:07+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class MpMessageConvertImpl implements MpMessageConvert {

    @Override
    public MpMessageRespVO convert(MpMessageDO bean) {
        if ( bean == null ) {
            return null;
        }

        MpMessageRespVO mpMessageRespVO = new MpMessageRespVO();

        if ( bean.getId() != null ) {
            mpMessageRespVO.setId( bean.getId().intValue() );
        }
        mpMessageRespVO.setMsgId( bean.getMsgId() );
        mpMessageRespVO.setAccountId( bean.getAccountId() );
        mpMessageRespVO.setAppId( bean.getAppId() );
        mpMessageRespVO.setUserId( bean.getUserId() );
        mpMessageRespVO.setOpenid( bean.getOpenid() );
        mpMessageRespVO.setType( bean.getType() );
        mpMessageRespVO.setSendFrom( bean.getSendFrom() );
        mpMessageRespVO.setContent( bean.getContent() );
        mpMessageRespVO.setMediaId( bean.getMediaId() );
        mpMessageRespVO.setMediaUrl( bean.getMediaUrl() );
        mpMessageRespVO.setRecognition( bean.getRecognition() );
        mpMessageRespVO.setFormat( bean.getFormat() );
        mpMessageRespVO.setTitle( bean.getTitle() );
        mpMessageRespVO.setDescription( bean.getDescription() );
        mpMessageRespVO.setThumbMediaId( bean.getThumbMediaId() );
        mpMessageRespVO.setThumbMediaUrl( bean.getThumbMediaUrl() );
        mpMessageRespVO.setUrl( bean.getUrl() );
        mpMessageRespVO.setLocationX( bean.getLocationX() );
        mpMessageRespVO.setLocationY( bean.getLocationY() );
        mpMessageRespVO.setScale( bean.getScale() );
        mpMessageRespVO.setLabel( bean.getLabel() );
        List<MpMessageDO.Article> list = bean.getArticles();
        if ( list != null ) {
            mpMessageRespVO.setArticles( new ArrayList<MpMessageDO.Article>( list ) );
        }
        mpMessageRespVO.setMusicUrl( bean.getMusicUrl() );
        mpMessageRespVO.setHqMusicUrl( bean.getHqMusicUrl() );
        mpMessageRespVO.setEvent( bean.getEvent() );
        mpMessageRespVO.setEventKey( bean.getEventKey() );
        mpMessageRespVO.setCreateTime( bean.getCreateTime() );

        return mpMessageRespVO;
    }

    @Override
    public List<MpMessageRespVO> convertList(List<MpMessageDO> list) {
        if ( list == null ) {
            return null;
        }

        List<MpMessageRespVO> list1 = new ArrayList<MpMessageRespVO>( list.size() );
        for ( MpMessageDO mpMessageDO : list ) {
            list1.add( convert( mpMessageDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<MpMessageRespVO> convertPage(PageResult<MpMessageDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<MpMessageRespVO> pageResult = new PageResult<MpMessageRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public MpMessageDO convert(WxMpXmlMessage bean) {
        if ( bean == null ) {
            return null;
        }

        MpMessageDO mpMessageDO = new MpMessageDO();

        mpMessageDO.setType( bean.getMsgType() );
        mpMessageDO.setMsgId( bean.getMsgId() );
        mpMessageDO.setContent( bean.getContent() );
        mpMessageDO.setMediaId( bean.getMediaId() );
        mpMessageDO.setRecognition( bean.getRecognition() );
        mpMessageDO.setFormat( bean.getFormat() );
        mpMessageDO.setTitle( bean.getTitle() );
        mpMessageDO.setDescription( bean.getDescription() );
        mpMessageDO.setThumbMediaId( bean.getThumbMediaId() );
        mpMessageDO.setUrl( bean.getUrl() );
        mpMessageDO.setLocationX( bean.getLocationX() );
        mpMessageDO.setLocationY( bean.getLocationY() );
        mpMessageDO.setScale( bean.getScale() );
        mpMessageDO.setLabel( bean.getLabel() );
        mpMessageDO.setEvent( bean.getEvent() );
        mpMessageDO.setEventKey( bean.getEventKey() );

        return mpMessageDO;
    }

    @Override
    public List<WxMpXmlOutNewsMessage.Item> convertList02(List<MpMessageDO.Article> list) {
        if ( list == null ) {
            return null;
        }

        List<WxMpXmlOutNewsMessage.Item> list1 = new ArrayList<WxMpXmlOutNewsMessage.Item>( list.size() );
        for ( MpMessageDO.Article article : list ) {
            list1.add( articleToItem( article ) );
        }

        return list1;
    }

    @Override
    public List<WxMpKefuMessage.WxArticle> convertList03(List<MpMessageDO.Article> list) {
        if ( list == null ) {
            return null;
        }

        List<WxMpKefuMessage.WxArticle> list1 = new ArrayList<WxMpKefuMessage.WxArticle>( list.size() );
        for ( MpMessageDO.Article article : list ) {
            list1.add( articleToWxArticle( article ) );
        }

        return list1;
    }

    @Override
    public MpMessageDO convert(WxMpKefuMessage bean) {
        if ( bean == null ) {
            return null;
        }

        MpMessageDO mpMessageDO = new MpMessageDO();

        mpMessageDO.setType( bean.getMsgType() );
        mpMessageDO.setContent( bean.getContent() );
        mpMessageDO.setMediaId( bean.getMediaId() );
        mpMessageDO.setTitle( bean.getTitle() );
        mpMessageDO.setDescription( bean.getDescription() );
        mpMessageDO.setThumbMediaId( bean.getThumbMediaId() );
        mpMessageDO.setArticles( wxArticleListToArticleList( bean.getArticles() ) );
        mpMessageDO.setMusicUrl( bean.getMusicUrl() );
        mpMessageDO.setHqMusicUrl( bean.getHqMusicUrl() );

        return mpMessageDO;
    }

    protected WxMpXmlOutNewsMessage.Item articleToItem(MpMessageDO.Article article) {
        if ( article == null ) {
            return null;
        }

        WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();

        item.setTitle( article.getTitle() );
        item.setDescription( article.getDescription() );
        item.setPicUrl( article.getPicUrl() );
        item.setUrl( article.getUrl() );

        return item;
    }

    protected WxMpKefuMessage.WxArticle articleToWxArticle(MpMessageDO.Article article) {
        if ( article == null ) {
            return null;
        }

        WxMpKefuMessage.WxArticle wxArticle = new WxMpKefuMessage.WxArticle();

        wxArticle.setTitle( article.getTitle() );
        wxArticle.setDescription( article.getDescription() );
        wxArticle.setUrl( article.getUrl() );
        wxArticle.setPicUrl( article.getPicUrl() );

        return wxArticle;
    }

    protected MpMessageDO.Article wxArticleToArticle(WxMpKefuMessage.WxArticle wxArticle) {
        if ( wxArticle == null ) {
            return null;
        }

        MpMessageDO.Article article = new MpMessageDO.Article();

        article.setTitle( wxArticle.getTitle() );
        article.setDescription( wxArticle.getDescription() );
        article.setPicUrl( wxArticle.getPicUrl() );
        article.setUrl( wxArticle.getUrl() );

        return article;
    }

    protected List<MpMessageDO.Article> wxArticleListToArticleList(List<WxMpKefuMessage.WxArticle> list) {
        if ( list == null ) {
            return null;
        }

        List<MpMessageDO.Article> list1 = new ArrayList<MpMessageDO.Article>( list.size() );
        for ( WxMpKefuMessage.WxArticle wxArticle : list ) {
            list1.add( wxArticleToArticle( wxArticle ) );
        }

        return list1;
    }
}
