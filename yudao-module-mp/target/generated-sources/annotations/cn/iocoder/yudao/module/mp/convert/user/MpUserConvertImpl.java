package cn.iocoder.yudao.module.mp.convert.user;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.mp.controller.admin.user.vo.MpUserRespVO;
import cn.iocoder.yudao.module.mp.controller.admin.user.vo.MpUserUpdateReqVO;
import cn.iocoder.yudao.module.mp.dal.dataobject.user.MpUserDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-28T10:44:07+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class MpUserConvertImpl implements MpUserConvert {

    @Override
    public MpUserRespVO convert(MpUserDO bean) {
        if ( bean == null ) {
            return null;
        }

        MpUserRespVO mpUserRespVO = new MpUserRespVO();

        mpUserRespVO.setId( bean.getId() );
        mpUserRespVO.setOpenid( bean.getOpenid() );
        mpUserRespVO.setUnionId( bean.getUnionId() );
        mpUserRespVO.setSubscribeStatus( bean.getSubscribeStatus() );
        mpUserRespVO.setSubscribeTime( bean.getSubscribeTime() );
        mpUserRespVO.setUnsubscribeTime( bean.getUnsubscribeTime() );
        mpUserRespVO.setNickname( bean.getNickname() );
        mpUserRespVO.setHeadImageUrl( bean.getHeadImageUrl() );
        mpUserRespVO.setLanguage( bean.getLanguage() );
        mpUserRespVO.setCountry( bean.getCountry() );
        mpUserRespVO.setProvince( bean.getProvince() );
        mpUserRespVO.setCity( bean.getCity() );
        mpUserRespVO.setRemark( bean.getRemark() );
        List<Long> list = bean.getTagIds();
        if ( list != null ) {
            mpUserRespVO.setTagIds( new ArrayList<Long>( list ) );
        }
        mpUserRespVO.setAccountId( bean.getAccountId() );
        mpUserRespVO.setAppId( bean.getAppId() );
        mpUserRespVO.setCreateTime( bean.getCreateTime() );

        return mpUserRespVO;
    }

    @Override
    public List<MpUserRespVO> convertList(List<MpUserDO> list) {
        if ( list == null ) {
            return null;
        }

        List<MpUserRespVO> list1 = new ArrayList<MpUserRespVO>( list.size() );
        for ( MpUserDO mpUserDO : list ) {
            list1.add( convert( mpUserDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<MpUserRespVO> convertPage(PageResult<MpUserDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<MpUserRespVO> pageResult = new PageResult<MpUserRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public MpUserDO convert(WxMpUser wxMpUser) {
        if ( wxMpUser == null ) {
            return null;
        }

        MpUserDO.MpUserDOBuilder mpUserDO = MpUserDO.builder();

        mpUserDO.openid( wxMpUser.getOpenId() );
        mpUserDO.unionId( wxMpUser.getUnionId() );
        mpUserDO.headImageUrl( wxMpUser.getHeadImgUrl() );
        mpUserDO.nickname( wxMpUser.getNickname() );
        mpUserDO.language( wxMpUser.getLanguage() );
        mpUserDO.remark( wxMpUser.getRemark() );
        mpUserDO.tagIds( longArrayToLongList( wxMpUser.getTagIds() ) );

        return mpUserDO.build();
    }

    @Override
    public MpUserDO convert(MpUserUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        MpUserDO.MpUserDOBuilder mpUserDO = MpUserDO.builder();

        mpUserDO.id( bean.getId() );
        mpUserDO.nickname( bean.getNickname() );
        mpUserDO.remark( bean.getRemark() );
        List<Long> list = bean.getTagIds();
        if ( list != null ) {
            mpUserDO.tagIds( new ArrayList<Long>( list ) );
        }

        return mpUserDO.build();
    }

    protected List<Long> longArrayToLongList(Long[] longArray) {
        if ( longArray == null ) {
            return null;
        }

        List<Long> list = new ArrayList<Long>( longArray.length );
        for ( Long long1 : longArray ) {
            list.add( long1 );
        }

        return list;
    }
}
