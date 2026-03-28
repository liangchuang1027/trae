package cn.iocoder.yudao.module.mp.convert.account;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.mp.controller.admin.account.vo.MpAccountCreateReqVO;
import cn.iocoder.yudao.module.mp.controller.admin.account.vo.MpAccountRespVO;
import cn.iocoder.yudao.module.mp.controller.admin.account.vo.MpAccountSimpleRespVO;
import cn.iocoder.yudao.module.mp.controller.admin.account.vo.MpAccountUpdateReqVO;
import cn.iocoder.yudao.module.mp.dal.dataobject.account.MpAccountDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-28T10:44:07+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class MpAccountConvertImpl implements MpAccountConvert {

    @Override
    public MpAccountDO convert(MpAccountCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        MpAccountDO.MpAccountDOBuilder mpAccountDO = MpAccountDO.builder();

        mpAccountDO.name( bean.getName() );
        mpAccountDO.account( bean.getAccount() );
        mpAccountDO.appId( bean.getAppId() );
        mpAccountDO.appSecret( bean.getAppSecret() );
        mpAccountDO.token( bean.getToken() );
        mpAccountDO.aesKey( bean.getAesKey() );
        mpAccountDO.remark( bean.getRemark() );

        return mpAccountDO.build();
    }

    @Override
    public MpAccountDO convert(MpAccountUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        MpAccountDO.MpAccountDOBuilder mpAccountDO = MpAccountDO.builder();

        mpAccountDO.id( bean.getId() );
        mpAccountDO.name( bean.getName() );
        mpAccountDO.account( bean.getAccount() );
        mpAccountDO.appId( bean.getAppId() );
        mpAccountDO.appSecret( bean.getAppSecret() );
        mpAccountDO.token( bean.getToken() );
        mpAccountDO.aesKey( bean.getAesKey() );
        mpAccountDO.remark( bean.getRemark() );

        return mpAccountDO.build();
    }

    @Override
    public MpAccountRespVO convert(MpAccountDO bean) {
        if ( bean == null ) {
            return null;
        }

        MpAccountRespVO mpAccountRespVO = new MpAccountRespVO();

        mpAccountRespVO.setName( bean.getName() );
        mpAccountRespVO.setAccount( bean.getAccount() );
        mpAccountRespVO.setAppId( bean.getAppId() );
        mpAccountRespVO.setAppSecret( bean.getAppSecret() );
        mpAccountRespVO.setToken( bean.getToken() );
        mpAccountRespVO.setAesKey( bean.getAesKey() );
        mpAccountRespVO.setRemark( bean.getRemark() );
        mpAccountRespVO.setId( bean.getId() );
        mpAccountRespVO.setQrCodeUrl( bean.getQrCodeUrl() );
        mpAccountRespVO.setCreateTime( bean.getCreateTime() );

        return mpAccountRespVO;
    }

    @Override
    public List<MpAccountRespVO> convertList(List<MpAccountDO> list) {
        if ( list == null ) {
            return null;
        }

        List<MpAccountRespVO> list1 = new ArrayList<MpAccountRespVO>( list.size() );
        for ( MpAccountDO mpAccountDO : list ) {
            list1.add( convert( mpAccountDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<MpAccountRespVO> convertPage(PageResult<MpAccountDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<MpAccountRespVO> pageResult = new PageResult<MpAccountRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public List<MpAccountSimpleRespVO> convertList02(List<MpAccountDO> list) {
        if ( list == null ) {
            return null;
        }

        List<MpAccountSimpleRespVO> list1 = new ArrayList<MpAccountSimpleRespVO>( list.size() );
        for ( MpAccountDO mpAccountDO : list ) {
            list1.add( mpAccountDOToMpAccountSimpleRespVO( mpAccountDO ) );
        }

        return list1;
    }

    protected MpAccountSimpleRespVO mpAccountDOToMpAccountSimpleRespVO(MpAccountDO mpAccountDO) {
        if ( mpAccountDO == null ) {
            return null;
        }

        MpAccountSimpleRespVO mpAccountSimpleRespVO = new MpAccountSimpleRespVO();

        mpAccountSimpleRespVO.setId( mpAccountDO.getId() );
        mpAccountSimpleRespVO.setName( mpAccountDO.getName() );

        return mpAccountSimpleRespVO;
    }
}
