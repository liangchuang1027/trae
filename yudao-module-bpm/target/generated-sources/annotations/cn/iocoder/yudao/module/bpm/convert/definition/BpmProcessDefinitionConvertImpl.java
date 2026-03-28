package cn.iocoder.yudao.module.bpm.convert.definition;

import cn.iocoder.yudao.module.bpm.controller.admin.definition.vo.process.BpmProcessDefinitionRespVO;
import cn.iocoder.yudao.module.bpm.dal.dataobject.definition.BpmProcessDefinitionInfoDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-28T10:44:08+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class BpmProcessDefinitionConvertImpl implements BpmProcessDefinitionConvert {

    @Override
    public void copyTo(BpmProcessDefinitionInfoDO from, BpmProcessDefinitionRespVO to) {
        if ( from == null ) {
            return;
        }

        to.setIcon( from.getIcon() );
        to.setDescription( from.getDescription() );
        to.setFormType( from.getFormType() );
        to.setFormId( from.getFormId() );
        to.setFormCustomCreatePath( from.getFormCustomCreatePath() );
        to.setFormCustomViewPath( from.getFormCustomViewPath() );
        to.setVisible( from.getVisible() );
        if ( to.getStartUserIds() != null ) {
            List<Long> list = from.getStartUserIds();
            if ( list != null ) {
                to.getStartUserIds().clear();
                to.getStartUserIds().addAll( list );
            }
            else {
                to.setStartUserIds( null );
            }
        }
        else {
            List<Long> list = from.getStartUserIds();
            if ( list != null ) {
                to.setStartUserIds( new ArrayList<Long>( list ) );
            }
        }
        if ( to.getStartDeptIds() != null ) {
            List<Long> list1 = from.getStartDeptIds();
            if ( list1 != null ) {
                to.getStartDeptIds().clear();
                to.getStartDeptIds().addAll( list1 );
            }
            else {
                to.setStartDeptIds( null );
            }
        }
        else {
            List<Long> list1 = from.getStartDeptIds();
            if ( list1 != null ) {
                to.setStartDeptIds( new ArrayList<Long>( list1 ) );
            }
        }
        if ( to.getManagerUserIds() != null ) {
            List<Long> list2 = from.getManagerUserIds();
            if ( list2 != null ) {
                to.getManagerUserIds().clear();
                to.getManagerUserIds().addAll( list2 );
            }
            else {
                to.setManagerUserIds( null );
            }
        }
        else {
            List<Long> list2 = from.getManagerUserIds();
            if ( list2 != null ) {
                to.setManagerUserIds( new ArrayList<Long>( list2 ) );
            }
        }
        to.setAllowCancelRunningProcess( from.getAllowCancelRunningProcess() );
        to.setProcessIdRule( from.getProcessIdRule() );
        to.setAutoApprovalType( from.getAutoApprovalType() );
        to.setTitleSetting( from.getTitleSetting() );
        to.setSummarySetting( from.getSummarySetting() );
        to.setProcessBeforeTriggerSetting( from.getProcessBeforeTriggerSetting() );
        to.setProcessAfterTriggerSetting( from.getProcessAfterTriggerSetting() );
        to.setTaskBeforeTriggerSetting( from.getTaskBeforeTriggerSetting() );
        to.setTaskAfterTriggerSetting( from.getTaskAfterTriggerSetting() );
        to.setCategory( from.getCategory() );
        to.setModelType( from.getModelType() );
        to.setModelId( from.getModelId() );
        to.setFormConf( from.getFormConf() );
        if ( to.getFormFields() != null ) {
            List<String> list3 = from.getFormFields();
            if ( list3 != null ) {
                to.getFormFields().clear();
                to.getFormFields().addAll( list3 );
            }
            else {
                to.setFormFields( null );
            }
        }
        else {
            List<String> list3 = from.getFormFields();
            if ( list3 != null ) {
                to.setFormFields( new ArrayList<String>( list3 ) );
            }
        }
        to.setSimpleModel( from.getSimpleModel() );
        to.setSort( from.getSort() );
    }
}
