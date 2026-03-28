package cn.iocoder.yudao.module.erp.controller.admin.order.vo;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class ParseResponse {
    private boolean success;
    private String message;
    private EngineeringInfo data;
}