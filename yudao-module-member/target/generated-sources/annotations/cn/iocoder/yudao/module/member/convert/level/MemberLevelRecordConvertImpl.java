package cn.iocoder.yudao.module.member.convert.level;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.member.controller.admin.level.vo.record.MemberLevelRecordRespVO;
import cn.iocoder.yudao.module.member.dal.dataobject.level.MemberLevelRecordDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-28T10:44:07+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class MemberLevelRecordConvertImpl implements MemberLevelRecordConvert {

    @Override
    public MemberLevelRecordRespVO convert(MemberLevelRecordDO bean) {
        if ( bean == null ) {
            return null;
        }

        MemberLevelRecordRespVO memberLevelRecordRespVO = new MemberLevelRecordRespVO();

        memberLevelRecordRespVO.setUserId( bean.getUserId() );
        memberLevelRecordRespVO.setLevelId( bean.getLevelId() );
        memberLevelRecordRespVO.setLevel( bean.getLevel() );
        memberLevelRecordRespVO.setDiscountPercent( bean.getDiscountPercent() );
        memberLevelRecordRespVO.setExperience( bean.getExperience() );
        memberLevelRecordRespVO.setUserExperience( bean.getUserExperience() );
        memberLevelRecordRespVO.setRemark( bean.getRemark() );
        memberLevelRecordRespVO.setDescription( bean.getDescription() );
        memberLevelRecordRespVO.setId( bean.getId() );
        memberLevelRecordRespVO.setCreateTime( bean.getCreateTime() );

        return memberLevelRecordRespVO;
    }

    @Override
    public List<MemberLevelRecordRespVO> convertList(List<MemberLevelRecordDO> list) {
        if ( list == null ) {
            return null;
        }

        List<MemberLevelRecordRespVO> list1 = new ArrayList<MemberLevelRecordRespVO>( list.size() );
        for ( MemberLevelRecordDO memberLevelRecordDO : list ) {
            list1.add( convert( memberLevelRecordDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<MemberLevelRecordRespVO> convertPage(PageResult<MemberLevelRecordDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<MemberLevelRecordRespVO> pageResult = new PageResult<MemberLevelRecordRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }
}
