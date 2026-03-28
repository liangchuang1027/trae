package cn.iocoder.yudao.module.erp.controller.admin.matchingExample;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 匹配示例 Excel 导出 VO（扁平化，包含父子关系）
 * 用于将父子关系展开为多行导出
 *
 * @author 芋道源码
 */
@Data
@ExcelIgnoreUnannotated
public class HzMatchingExampleExcelExportVO {

    // ========== 父表字段（HzMatchingExampleDO） ==========
    
    @ExcelProperty("主表编号")
    private Long id;

    @ExcelProperty("主表名称")
    private String name;

    @ExcelProperty("主表编码")
    private String code;

    @ExcelProperty("主表状态")
    private Integer status;

    @ExcelProperty("主表备注")
    private String remark;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    // ========== 子表字段（HzMatchingExampleRawDO） ==========
    
    @ExcelProperty("子表编号")
    private Long rawId;

    @ExcelProperty("子表字段1")
    private String rawField1;

    @ExcelProperty("子表字段2")
    private String rawField2;

    @ExcelProperty("子表数量")
    private Integer rawCount;

    @ExcelProperty("子表金额")
    private java.math.BigDecimal rawAmount;

    @ExcelProperty("子表备注")
    private String rawRemark;

    // 注意：请根据实际的HzMatchingExampleDO和HzMatchingExampleRawDO的字段来调整上面的属性

}

