package cn.iocoder.yudao.module.erp.controller.admin.productWeighingDetails.vo;

import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.module.system.enums.DictTypeConstants;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 过磅信息 导入 VO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false) // 设置 chain = false，避免用户导入有问题
public class WeighingImportExcelVO {

    @ExcelProperty("流水号")
    private String serialNumber;

    @ExcelProperty("车号")
    private String carNumber;

    @ExcelProperty("发货单位")
    private String shipper;

    @ExcelProperty("收货单位")
    private String consignee;

    @ExcelProperty("货名")
    private String productName;

    @ExcelProperty("规格")
    private String standard;

    @ExcelProperty("毛重")
    private Long roughWeight;

    @ExcelProperty("皮重")
    private Long tareWeight;

    @ExcelProperty("净重")
    private Long netWeight;

    @ExcelProperty("毛重时间")
    private LocalDateTime roughTime;

    @ExcelProperty("皮重时间")
    private LocalDateTime tareTime;

    @ExcelProperty("备注")
    private String remarks;

    @ExcelProperty("计划单号")
    private String planOrderNumber;

}
