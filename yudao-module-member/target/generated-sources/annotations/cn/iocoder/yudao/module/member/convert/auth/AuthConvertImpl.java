package cn.iocoder.yudao.module.member.convert.auth;

import cn.iocoder.yudao.framework.common.biz.system.oauth2.dto.OAuth2AccessTokenRespDTO;
import cn.iocoder.yudao.module.member.controller.app.auth.vo.AppAuthLoginRespVO;
import cn.iocoder.yudao.module.member.controller.app.auth.vo.AppAuthSmsLoginReqVO;
import cn.iocoder.yudao.module.member.controller.app.auth.vo.AppAuthSmsSendReqVO;
import cn.iocoder.yudao.module.member.controller.app.auth.vo.AppAuthSmsValidateReqVO;
import cn.iocoder.yudao.module.member.controller.app.auth.vo.AppAuthSocialLoginReqVO;
import cn.iocoder.yudao.module.member.controller.app.social.vo.AppSocialUserUnbindReqVO;
import cn.iocoder.yudao.module.member.controller.app.user.vo.AppMemberUserResetPasswordReqVO;
import cn.iocoder.yudao.module.system.api.sms.dto.code.SmsCodeSendReqDTO;
import cn.iocoder.yudao.module.system.api.sms.dto.code.SmsCodeUseReqDTO;
import cn.iocoder.yudao.module.system.api.sms.dto.code.SmsCodeValidateReqDTO;
import cn.iocoder.yudao.module.system.api.social.dto.SocialUserBindReqDTO;
import cn.iocoder.yudao.module.system.api.social.dto.SocialUserUnbindReqDTO;
import cn.iocoder.yudao.module.system.api.social.dto.SocialWxJsapiSignatureRespDTO;
import cn.iocoder.yudao.module.system.enums.sms.SmsSceneEnum;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-28T10:44:07+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class AuthConvertImpl implements AuthConvert {

    @Override
    public SocialUserBindReqDTO convert(Long userId, Integer userType, AppAuthSocialLoginReqVO reqVO) {
        if ( userId == null && userType == null && reqVO == null ) {
            return null;
        }

        SocialUserBindReqDTO socialUserBindReqDTO = new SocialUserBindReqDTO();

        if ( reqVO != null ) {
            socialUserBindReqDTO.setCode( reqVO.getCode() );
            socialUserBindReqDTO.setState( reqVO.getState() );
        }
        socialUserBindReqDTO.setUserId( userId );
        socialUserBindReqDTO.setUserType( userType );

        return socialUserBindReqDTO;
    }

    @Override
    public SocialUserUnbindReqDTO convert(Long userId, Integer userType, AppSocialUserUnbindReqVO reqVO) {
        if ( userId == null && userType == null && reqVO == null ) {
            return null;
        }

        SocialUserUnbindReqDTO socialUserUnbindReqDTO = new SocialUserUnbindReqDTO();

        if ( reqVO != null ) {
            socialUserUnbindReqDTO.setOpenid( reqVO.getOpenid() );
        }
        socialUserUnbindReqDTO.setUserId( userId );
        socialUserUnbindReqDTO.setUserType( userType );

        return socialUserUnbindReqDTO;
    }

    @Override
    public SmsCodeSendReqDTO convert(AppAuthSmsSendReqVO reqVO) {
        if ( reqVO == null ) {
            return null;
        }

        SmsCodeSendReqDTO smsCodeSendReqDTO = new SmsCodeSendReqDTO();

        smsCodeSendReqDTO.setMobile( reqVO.getMobile() );
        smsCodeSendReqDTO.setScene( reqVO.getScene() );

        return smsCodeSendReqDTO;
    }

    @Override
    public SmsCodeUseReqDTO convert(AppMemberUserResetPasswordReqVO reqVO, SmsSceneEnum scene, String usedIp) {
        if ( reqVO == null && scene == null && usedIp == null ) {
            return null;
        }

        SmsCodeUseReqDTO smsCodeUseReqDTO = new SmsCodeUseReqDTO();

        if ( reqVO != null ) {
            smsCodeUseReqDTO.setMobile( reqVO.getMobile() );
            smsCodeUseReqDTO.setCode( reqVO.getCode() );
        }
        if ( scene != null ) {
            smsCodeUseReqDTO.setScene( scene.getScene() );
        }
        smsCodeUseReqDTO.setUsedIp( usedIp );

        return smsCodeUseReqDTO;
    }

    @Override
    public SmsCodeUseReqDTO convert(AppAuthSmsLoginReqVO reqVO, Integer scene, String usedIp) {
        if ( reqVO == null && scene == null && usedIp == null ) {
            return null;
        }

        SmsCodeUseReqDTO smsCodeUseReqDTO = new SmsCodeUseReqDTO();

        if ( reqVO != null ) {
            smsCodeUseReqDTO.setMobile( reqVO.getMobile() );
            smsCodeUseReqDTO.setCode( reqVO.getCode() );
        }
        smsCodeUseReqDTO.setScene( scene );
        smsCodeUseReqDTO.setUsedIp( usedIp );

        return smsCodeUseReqDTO;
    }

    @Override
    public AppAuthLoginRespVO convert(OAuth2AccessTokenRespDTO bean, String openid) {
        if ( bean == null && openid == null ) {
            return null;
        }

        AppAuthLoginRespVO.AppAuthLoginRespVOBuilder appAuthLoginRespVO = AppAuthLoginRespVO.builder();

        if ( bean != null ) {
            appAuthLoginRespVO.userId( bean.getUserId() );
            appAuthLoginRespVO.accessToken( bean.getAccessToken() );
            appAuthLoginRespVO.refreshToken( bean.getRefreshToken() );
            appAuthLoginRespVO.expiresTime( bean.getExpiresTime() );
        }
        appAuthLoginRespVO.openid( openid );

        return appAuthLoginRespVO.build();
    }

    @Override
    public SmsCodeValidateReqDTO convert(AppAuthSmsValidateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        SmsCodeValidateReqDTO smsCodeValidateReqDTO = new SmsCodeValidateReqDTO();

        smsCodeValidateReqDTO.setMobile( bean.getMobile() );
        smsCodeValidateReqDTO.setScene( bean.getScene() );
        smsCodeValidateReqDTO.setCode( bean.getCode() );

        return smsCodeValidateReqDTO;
    }

    @Override
    public SocialWxJsapiSignatureRespDTO convert(SocialWxJsapiSignatureRespDTO bean) {
        if ( bean == null ) {
            return null;
        }

        SocialWxJsapiSignatureRespDTO socialWxJsapiSignatureRespDTO = new SocialWxJsapiSignatureRespDTO();

        socialWxJsapiSignatureRespDTO.setAppId( bean.getAppId() );
        socialWxJsapiSignatureRespDTO.setNonceStr( bean.getNonceStr() );
        socialWxJsapiSignatureRespDTO.setTimestamp( bean.getTimestamp() );
        socialWxJsapiSignatureRespDTO.setUrl( bean.getUrl() );
        socialWxJsapiSignatureRespDTO.setSignature( bean.getSignature() );

        return socialWxJsapiSignatureRespDTO;
    }
}
