package cn.iocoder.yudao.module.erp.controller.admin.hzRawConsume.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false) // 设置 chain = false，避免用户导入有问题
public class HzRawConsumeEx{


    @ExcelProperty("原料名称")
    private String productName;
    @ExcelProperty("规格")
    private String standard;
    @ExcelProperty("目标值")
    private String targetValue;
    @ExcelProperty("秤量值")
    private String factValue;
    @ExcelProperty("误差值")
    private String errorValue;
    @ExcelProperty("误差率")
    private String errorRate;


}