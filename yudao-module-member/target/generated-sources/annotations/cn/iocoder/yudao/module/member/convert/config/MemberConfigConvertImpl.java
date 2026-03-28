package cn.iocoder.yudao.module.member.convert.config;

import cn.iocoder.yudao.module.member.api.config.dto.MemberConfigRespDTO;
import cn.iocoder.yudao.module.member.controller.admin.config.vo.MemberConfigRespVO;
import cn.iocoder.yudao.module.member.controller.admin.config.vo.MemberConfigSaveReqVO;
import cn.iocoder.yudao.module.member.dal.dataobject.config.MemberConfigDO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-28T10:44:08+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class MemberConfigConvertImpl implements MemberConfigConvert {

    @Override
    public MemberConfigRespVO convert(MemberConfigDO bean) {
        if ( bean == null ) {
            return null;
        }

        MemberConfigRespVO memberConfigRespVO = new MemberConfigRespVO();

        memberConfigRespVO.setPointTradeDeductEnable( bean.getPointTradeDeductEnable() );
        memberConfigRespVO.setPointTradeDeductUnitPrice( bean.getPointTradeDeductUnitPrice() );
        memberConfigRespVO.setPointTradeDeductMaxPrice( bean.getPointTradeDeductMaxPrice() );
        memberConfigRespVO.setPointTradeGivePoint( bean.getPointTradeGivePoint() );
        memberConfigRespVO.setId( bean.getId() );

        return memberConfigRespVO;
    }

    @Override
    public MemberConfigDO convert(MemberConfigSaveReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        MemberConfigDO.MemberConfigDOBuilder memberConfigDO = MemberConfigDO.builder();

        memberConfigDO.pointTradeDeductEnable( bean.getPointTradeDeductEnable() );
        memberConfigDO.pointTradeDeductUnitPrice( bean.getPointTradeDeductUnitPrice() );
        memberConfigDO.pointTradeDeductMaxPrice( bean.getPointTradeDeductMaxPrice() );
        memberConfigDO.pointTradeGivePoint( bean.getPointTradeGivePoint() );

        return memberConfigDO.build();
    }

    @Override
    public MemberConfigRespDTO convert01(MemberConfigDO config) {
        if ( config == null ) {
            return null;
        }

        MemberConfigRespDTO memberConfigRespDTO = new MemberConfigRespDTO();

        memberConfigRespDTO.setPointTradeDeductEnable( config.getPointTradeDeductEnable() );
        memberConfigRespDTO.setPointTradeDeductUnitPrice( config.getPointTradeDeductUnitPrice() );
        memberConfigRespDTO.setPointTradeDeductMaxPrice( config.getPointTradeDeductMaxPrice() );
        memberConfigRespDTO.setPointTradeGivePoint( config.getPointTradeGivePoint() );

        return memberConfigRespDTO;
    }
}
