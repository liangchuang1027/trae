package cn.iocoder.yudao.module.mp.convert.material;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.mp.controller.admin.material.vo.MpMaterialRespVO;
import cn.iocoder.yudao.module.mp.controller.admin.material.vo.MpMaterialUploadRespVO;
import cn.iocoder.yudao.module.mp.dal.dataobject.account.MpAccountDO;
import cn.iocoder.yudao.module.mp.dal.dataobject.material.MpMaterialDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-28T10:44:07+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class MpMaterialConvertImpl implements MpMaterialConvert {

    @Override
    public MpMaterialDO convert(String mediaId, String type, String url, MpAccountDO account, String name) {
        if ( mediaId == null && type == null && url == null && account == null && name == null ) {
            return null;
        }

        MpMaterialDO.MpMaterialDOBuilder mpMaterialDO = MpMaterialDO.builder();

        if ( account != null ) {
            mpMaterialDO.accountId( account.getId() );
            mpMaterialDO.appId( account.getAppId() );
        }
        mpMaterialDO.mediaId( mediaId );
        mpMaterialDO.type( type );
        mpMaterialDO.url( url );
        mpMaterialDO.name( name );

        return mpMaterialDO.build();
    }

    @Override
    public MpMaterialDO convert(String mediaId, String type, String url, MpAccountDO account, String name, String title, String introduction, String mpUrl) {
        if ( mediaId == null && type == null && url == null && account == null && name == null && title == null && introduction == null && mpUrl == null ) {
            return null;
        }

        MpMaterialDO.MpMaterialDOBuilder mpMaterialDO = MpMaterialDO.builder();

        if ( account != null ) {
            mpMaterialDO.accountId( account.getId() );
            mpMaterialDO.appId( account.getAppId() );
        }
        mpMaterialDO.mediaId( mediaId );
        mpMaterialDO.type( type );
        mpMaterialDO.url( url );
        mpMaterialDO.name( name );
        mpMaterialDO.title( title );
        mpMaterialDO.introduction( introduction );
        mpMaterialDO.mpUrl( mpUrl );

        return mpMaterialDO.build();
    }

    @Override
    public MpMaterialUploadRespVO convert(MpMaterialDO bean) {
        if ( bean == null ) {
            return null;
        }

        MpMaterialUploadRespVO mpMaterialUploadRespVO = new MpMaterialUploadRespVO();

        mpMaterialUploadRespVO.setMediaId( bean.getMediaId() );
        mpMaterialUploadRespVO.setUrl( bean.getUrl() );

        return mpMaterialUploadRespVO;
    }

    @Override
    public PageResult<MpMaterialRespVO> convertPage(PageResult<MpMaterialDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<MpMaterialRespVO> pageResult = new PageResult<MpMaterialRespVO>();

        pageResult.setList( mpMaterialDOListToMpMaterialRespVOList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    protected MpMaterialRespVO mpMaterialDOToMpMaterialRespVO(MpMaterialDO mpMaterialDO) {
        if ( mpMaterialDO == null ) {
            return null;
        }

        MpMaterialRespVO mpMaterialRespVO = new MpMaterialRespVO();

        mpMaterialRespVO.setId( mpMaterialDO.getId() );
        mpMaterialRespVO.setAccountId( mpMaterialDO.getAccountId() );
        mpMaterialRespVO.setAppId( mpMaterialDO.getAppId() );
        mpMaterialRespVO.setMediaId( mpMaterialDO.getMediaId() );
        mpMaterialRespVO.setType( mpMaterialDO.getType() );
        mpMaterialRespVO.setPermanent( mpMaterialDO.getPermanent() );
        mpMaterialRespVO.setUrl( mpMaterialDO.getUrl() );
        mpMaterialRespVO.setName( mpMaterialDO.getName() );
        mpMaterialRespVO.setMpUrl( mpMaterialDO.getMpUrl() );
        mpMaterialRespVO.setTitle( mpMaterialDO.getTitle() );
        mpMaterialRespVO.setIntroduction( mpMaterialDO.getIntroduction() );
        mpMaterialRespVO.setCreateTime( mpMaterialDO.getCreateTime() );

        return mpMaterialRespVO;
    }

    protected List<MpMaterialRespVO> mpMaterialDOListToMpMaterialRespVOList(List<MpMaterialDO> list) {
        if ( list == null ) {
            return null;
        }

        List<MpMaterialRespVO> list1 = new ArrayList<MpMaterialRespVO>( list.size() );
        for ( MpMaterialDO mpMaterialDO : list ) {
            list1.add( mpMaterialDOToMpMaterialRespVO( mpMaterialDO ) );
        }

        return list1;
    }
}
