package cn.iocoder.yudao.module.erp.controller.admin.order.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ParseRequest {
    @NotBlank
    private String content;
}