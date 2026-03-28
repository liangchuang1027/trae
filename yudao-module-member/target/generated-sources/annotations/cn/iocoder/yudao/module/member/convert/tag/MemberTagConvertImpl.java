package cn.iocoder.yudao.module.member.convert.tag;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.member.controller.admin.tag.vo.MemberTagCreateReqVO;
import cn.iocoder.yudao.module.member.controller.admin.tag.vo.MemberTagRespVO;
import cn.iocoder.yudao.module.member.controller.admin.tag.vo.MemberTagUpdateReqVO;
import cn.iocoder.yudao.module.member.dal.dataobject.tag.MemberTagDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-28T10:44:08+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class MemberTagConvertImpl implements MemberTagConvert {

    @Override
    public MemberTagDO convert(MemberTagCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        MemberTagDO.MemberTagDOBuilder memberTagDO = MemberTagDO.builder();

        memberTagDO.name( bean.getName() );

        return memberTagDO.build();
    }

    @Override
    public MemberTagDO convert(MemberTagUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        MemberTagDO.MemberTagDOBuilder memberTagDO = MemberTagDO.builder();

        memberTagDO.id( bean.getId() );
        memberTagDO.name( bean.getName() );

        return memberTagDO.build();
    }

    @Override
    public MemberTagRespVO convert(MemberTagDO bean) {
        if ( bean == null ) {
            return null;
        }

        MemberTagRespVO memberTagRespVO = new MemberTagRespVO();

        memberTagRespVO.setName( bean.getName() );
        memberTagRespVO.setId( bean.getId() );
        memberTagRespVO.setCreateTime( bean.getCreateTime() );

        return memberTagRespVO;
    }

    @Override
    public List<MemberTagRespVO> convertList(List<MemberTagDO> list) {
        if ( list == null ) {
            return null;
        }

        List<MemberTagRespVO> list1 = new ArrayList<MemberTagRespVO>( list.size() );
        for ( MemberTagDO memberTagDO : list ) {
            list1.add( convert( memberTagDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<MemberTagRespVO> convertPage(PageResult<MemberTagDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<MemberTagRespVO> pageResult = new PageResult<MemberTagRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }
}
