package cn.iocoder.yudao.module.mp.convert.tag;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.mp.controller.admin.tag.vo.MpTagRespVO;
import cn.iocoder.yudao.module.mp.controller.admin.tag.vo.MpTagSimpleRespVO;
import cn.iocoder.yudao.module.mp.controller.admin.tag.vo.MpTagUpdateReqVO;
import cn.iocoder.yudao.module.mp.dal.dataobject.account.MpAccountDO;
import cn.iocoder.yudao.module.mp.dal.dataobject.tag.MpTagDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-28T10:44:07+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class MpTagConvertImpl implements MpTagConvert {

    @Override
    public WxUserTag convert(MpTagUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        WxUserTag wxUserTag = new WxUserTag();

        wxUserTag.setId( bean.getId() );
        wxUserTag.setName( bean.getName() );

        return wxUserTag;
    }

    @Override
    public MpTagRespVO convert(WxUserTag bean) {
        if ( bean == null ) {
            return null;
        }

        MpTagRespVO mpTagRespVO = new MpTagRespVO();

        mpTagRespVO.setName( bean.getName() );
        mpTagRespVO.setId( bean.getId() );
        mpTagRespVO.setCount( bean.getCount() );

        return mpTagRespVO;
    }

    @Override
    public List<MpTagRespVO> convertList(List<WxUserTag> list) {
        if ( list == null ) {
            return null;
        }

        List<MpTagRespVO> list1 = new ArrayList<MpTagRespVO>( list.size() );
        for ( WxUserTag wxUserTag : list ) {
            list1.add( convert( wxUserTag ) );
        }

        return list1;
    }

    @Override
    public PageResult<MpTagRespVO> convertPage(PageResult<MpTagDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<MpTagRespVO> pageResult = new PageResult<MpTagRespVO>();

        pageResult.setList( mpTagDOListToMpTagRespVOList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public MpTagDO convert(WxUserTag tag, MpAccountDO account) {
        if ( tag == null && account == null ) {
            return null;
        }

        MpTagDO.MpTagDOBuilder mpTagDO = MpTagDO.builder();

        if ( tag != null ) {
            mpTagDO.tagId( tag.getId() );
            mpTagDO.name( tag.getName() );
            mpTagDO.count( tag.getCount() );
        }
        if ( account != null ) {
            mpTagDO.accountId( account.getId() );
            mpTagDO.appId( account.getAppId() );
        }

        return mpTagDO.build();
    }

    @Override
    public MpTagRespVO convert(MpTagDO mpTagDO) {
        if ( mpTagDO == null ) {
            return null;
        }

        MpTagRespVO mpTagRespVO = new MpTagRespVO();

        mpTagRespVO.setName( mpTagDO.getName() );
        mpTagRespVO.setId( mpTagDO.getId() );
        mpTagRespVO.setCount( mpTagDO.getCount() );
        mpTagRespVO.setCreateTime( mpTagDO.getCreateTime() );

        return mpTagRespVO;
    }

    @Override
    public List<MpTagSimpleRespVO> convertList02(List<MpTagDO> list) {
        if ( list == null ) {
            return null;
        }

        List<MpTagSimpleRespVO> list1 = new ArrayList<MpTagSimpleRespVO>( list.size() );
        for ( MpTagDO mpTagDO : list ) {
            list1.add( mpTagDOToMpTagSimpleRespVO( mpTagDO ) );
        }

        return list1;
    }

    protected List<MpTagRespVO> mpTagDOListToMpTagRespVOList(List<MpTagDO> list) {
        if ( list == null ) {
            return null;
        }

        List<MpTagRespVO> list1 = new ArrayList<MpTagRespVO>( list.size() );
        for ( MpTagDO mpTagDO : list ) {
            list1.add( convert( mpTagDO ) );
        }

        return list1;
    }

    protected MpTagSimpleRespVO mpTagDOToMpTagSimpleRespVO(MpTagDO mpTagDO) {
        if ( mpTagDO == null ) {
            return null;
        }

        MpTagSimpleRespVO mpTagSimpleRespVO = new MpTagSimpleRespVO();

        mpTagSimpleRespVO.setId( mpTagDO.getId() );
        mpTagSimpleRespVO.setTagId( mpTagDO.getTagId() );
        mpTagSimpleRespVO.setName( mpTagDO.getName() );

        return mpTagSimpleRespVO;
    }
}
