package cn.iocoder.yudao.module.erp.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class HzOrderCallBackVO {


    @Schema(description = "仓库编号")
    private Long wareHouseId;

    @Schema(description = "产品id", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Long productId;

    @Schema(description = "产品数量")
    private BigDecimal productAmount;






    private Long orderId;
    private Long receiveOrderId;


    @Schema(description = "状态 1转移 2作废")
    private Long status;




}