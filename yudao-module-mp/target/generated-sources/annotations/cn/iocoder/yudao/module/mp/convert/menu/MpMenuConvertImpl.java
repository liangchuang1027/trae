package cn.iocoder.yudao.module.mp.convert.menu;

import cn.iocoder.yudao.module.mp.controller.admin.menu.vo.MpMenuRespVO;
import cn.iocoder.yudao.module.mp.controller.admin.menu.vo.MpMenuSaveReqVO;
import cn.iocoder.yudao.module.mp.dal.dataobject.menu.MpMenuDO;
import cn.iocoder.yudao.module.mp.dal.dataobject.message.MpMessageDO;
import cn.iocoder.yudao.module.mp.service.message.bo.MpMessageSendOutReqBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-28T10:44:06+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class MpMenuConvertImpl implements MpMenuConvert {

    @Override
    public MpMenuRespVO convert(MpMenuDO bean) {
        if ( bean == null ) {
            return null;
        }

        MpMenuRespVO mpMenuRespVO = new MpMenuRespVO();

        mpMenuRespVO.setName( bean.getName() );
        mpMenuRespVO.setMenuKey( bean.getMenuKey() );
        mpMenuRespVO.setParentId( bean.getParentId() );
        mpMenuRespVO.setType( bean.getType() );
        mpMenuRespVO.setUrl( bean.getUrl() );
        mpMenuRespVO.setMiniProgramAppId( bean.getMiniProgramAppId() );
        mpMenuRespVO.setMiniProgramPagePath( bean.getMiniProgramPagePath() );
        mpMenuRespVO.setArticleId( bean.getArticleId() );
        mpMenuRespVO.setReplyMessageType( bean.getReplyMessageType() );
        mpMenuRespVO.setReplyContent( bean.getReplyContent() );
        mpMenuRespVO.setReplyMediaId( bean.getReplyMediaId() );
        mpMenuRespVO.setReplyMediaUrl( bean.getReplyMediaUrl() );
        mpMenuRespVO.setReplyThumbMediaId( bean.getReplyThumbMediaId() );
        mpMenuRespVO.setReplyThumbMediaUrl( bean.getReplyThumbMediaUrl() );
        mpMenuRespVO.setReplyTitle( bean.getReplyTitle() );
        mpMenuRespVO.setReplyDescription( bean.getReplyDescription() );
        List<MpMessageDO.Article> list = bean.getReplyArticles();
        if ( list != null ) {
            mpMenuRespVO.setReplyArticles( new ArrayList<MpMessageDO.Article>( list ) );
        }
        mpMenuRespVO.setReplyMusicUrl( bean.getReplyMusicUrl() );
        mpMenuRespVO.setReplyHqMusicUrl( bean.getReplyHqMusicUrl() );
        mpMenuRespVO.setId( bean.getId() );
        mpMenuRespVO.setAccountId( bean.getAccountId() );
        mpMenuRespVO.setAppId( bean.getAppId() );
        mpMenuRespVO.setCreateTime( bean.getCreateTime() );

        return mpMenuRespVO;
    }

    @Override
    public List<MpMenuRespVO> convertList(List<MpMenuDO> list) {
        if ( list == null ) {
            return null;
        }

        List<MpMenuRespVO> list1 = new ArrayList<MpMenuRespVO>( list.size() );
        for ( MpMenuDO mpMenuDO : list ) {
            list1.add( convert( mpMenuDO ) );
        }

        return list1;
    }

    @Override
    public MpMessageSendOutReqBO convert(String openid, MpMenuDO menu) {
        if ( openid == null && menu == null ) {
            return null;
        }

        MpMessageSendOutReqBO mpMessageSendOutReqBO = new MpMessageSendOutReqBO();

        if ( menu != null ) {
            mpMessageSendOutReqBO.setAppId( menu.getAppId() );
            mpMessageSendOutReqBO.setType( menu.getReplyMessageType() );
            mpMessageSendOutReqBO.setContent( menu.getReplyContent() );
            mpMessageSendOutReqBO.setMediaId( menu.getReplyMediaId() );
            mpMessageSendOutReqBO.setThumbMediaId( menu.getReplyThumbMediaId() );
            mpMessageSendOutReqBO.setTitle( menu.getReplyTitle() );
            mpMessageSendOutReqBO.setDescription( menu.getReplyDescription() );
            List<MpMessageDO.Article> list = menu.getReplyArticles();
            if ( list != null ) {
                mpMessageSendOutReqBO.setArticles( new ArrayList<MpMessageDO.Article>( list ) );
            }
            mpMessageSendOutReqBO.setMusicUrl( menu.getReplyMusicUrl() );
            mpMessageSendOutReqBO.setHqMusicUrl( menu.getReplyHqMusicUrl() );
        }
        mpMessageSendOutReqBO.setOpenid( openid );

        return mpMessageSendOutReqBO;
    }

    @Override
    public List<WxMenuButton> convert(List<MpMenuSaveReqVO.Menu> list) {
        if ( list == null ) {
            return null;
        }

        List<WxMenuButton> list1 = new ArrayList<WxMenuButton>( list.size() );
        for ( MpMenuSaveReqVO.Menu menu : list ) {
            list1.add( convert( menu ) );
        }

        return list1;
    }

    @Override
    public WxMenuButton convert(MpMenuSaveReqVO.Menu bean) {
        if ( bean == null ) {
            return null;
        }

        WxMenuButton wxMenuButton = new WxMenuButton();

        wxMenuButton.setKey( bean.getMenuKey() );
        wxMenuButton.setSubButtons( convert( bean.getChildren() ) );
        wxMenuButton.setAppId( bean.getMiniProgramAppId() );
        wxMenuButton.setPagePath( bean.getMiniProgramPagePath() );
        wxMenuButton.setType( bean.getType() );
        wxMenuButton.setName( bean.getName() );
        wxMenuButton.setUrl( bean.getUrl() );
        wxMenuButton.setArticleId( bean.getArticleId() );

        return wxMenuButton;
    }

    @Override
    public MpMenuDO convert02(MpMenuSaveReqVO.Menu menu) {
        if ( menu == null ) {
            return null;
        }

        MpMenuDO mpMenuDO = new MpMenuDO();

        mpMenuDO.setName( menu.getName() );
        mpMenuDO.setMenuKey( menu.getMenuKey() );
        mpMenuDO.setParentId( menu.getParentId() );
        mpMenuDO.setType( menu.getType() );
        mpMenuDO.setUrl( menu.getUrl() );
        mpMenuDO.setMiniProgramAppId( menu.getMiniProgramAppId() );
        mpMenuDO.setMiniProgramPagePath( menu.getMiniProgramPagePath() );
        mpMenuDO.setArticleId( menu.getArticleId() );
        mpMenuDO.setReplyMessageType( menu.getReplyMessageType() );
        mpMenuDO.setReplyContent( menu.getReplyContent() );
        mpMenuDO.setReplyMediaId( menu.getReplyMediaId() );
        mpMenuDO.setReplyMediaUrl( menu.getReplyMediaUrl() );
        mpMenuDO.setReplyTitle( menu.getReplyTitle() );
        mpMenuDO.setReplyDescription( menu.getReplyDescription() );
        mpMenuDO.setReplyThumbMediaId( menu.getReplyThumbMediaId() );
        mpMenuDO.setReplyThumbMediaUrl( menu.getReplyThumbMediaUrl() );
        List<MpMessageDO.Article> list = menu.getReplyArticles();
        if ( list != null ) {
            mpMenuDO.setReplyArticles( new ArrayList<MpMessageDO.Article>( list ) );
        }
        mpMenuDO.setReplyMusicUrl( menu.getReplyMusicUrl() );
        mpMenuDO.setReplyHqMusicUrl( menu.getReplyHqMusicUrl() );

        return mpMenuDO;
    }
}
