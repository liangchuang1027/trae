package cn.iocoder.yudao.module.erp.controller.admin.matchingExample;

import cn.iocoder.yudao.module.erp.dal.dataobject.matchingExample.HzMatchingExampleDO;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 配比实例Excel写入处理器
 * 用于在Excel中添加父表信息和样式
 *
 * @author liangchuang
 */
public class MatchingExampleSheetWriteHandler implements SheetWriteHandler {

    private final HzMatchingExampleDO parent;
    private final int dataRowCount;

    public MatchingExampleSheetWriteHandler(HzMatchingExampleDO parent, int dataRowCount) {
        this.parent = parent;
        this.dataRowCount = dataRowCount;
    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        // Sheet创建后，数据写入前，插入父表信息行
        Sheet sheet = writeSheetHolder.getSheet();
        Workbook workbook = writeWorkbookHolder.getWorkbook();

        // 创建样式
        CellStyle titleStyle = workbook.createCellStyle();
        Font titleFont = workbook.createFont();
        titleFont.setFontHeightInPoints((short) 14);
        titleFont.setBold(true);
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.LEFT);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        CellStyle labelStyle = workbook.createCellStyle();
        Font labelFont = workbook.createFont();
        labelFont.setFontHeightInPoints((short) 11);
        labelFont.setBold(true);
        labelStyle.setFont(labelFont);
        labelStyle.setAlignment(HorizontalAlignment.LEFT);
        labelStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        labelStyle.setBorderTop(BorderStyle.THIN);
        labelStyle.setBorderBottom(BorderStyle.THIN);
        labelStyle.setBorderLeft(BorderStyle.THIN);
        labelStyle.setBorderRight(BorderStyle.THIN);
        labelStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        labelStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle valueStyle = workbook.createCellStyle();
        Font valueFont = workbook.createFont();
        valueFont.setFontHeightInPoints((short) 11);
        valueStyle.setFont(valueFont);
        valueStyle.setAlignment(HorizontalAlignment.LEFT);
        valueStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        valueStyle.setBorderTop(BorderStyle.THIN);
        valueStyle.setBorderBottom(BorderStyle.THIN);
        valueStyle.setBorderLeft(BorderStyle.THIN);
        valueStyle.setBorderRight(BorderStyle.THIN);

        // 只创建标题行（生产任务单号）
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("原料配比单号：" + (StrUtil.isNotBlank(parent.getExampleNumber()) ? parent.getExampleNumber() : ""));
        titleCell.setCellStyle(titleStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));

        // 调整列宽
        for (int i = 0; i < 10; i++) {
            sheet.setColumnWidth(i, 4000);
        }
    }
    
        // 使用自定义方法在数据写入后添加汇总信息
    public void addSummaryInfo(Sheet sheet, Workbook workbook) {
        // 创建样式
        CellStyle labelStyle = workbook.createCellStyle();
        Font labelFont = workbook.createFont();
        labelFont.setFontHeightInPoints((short) 11);
        labelFont.setBold(true);
        labelStyle.setFont(labelFont);
        labelStyle.setAlignment(HorizontalAlignment.LEFT);
        labelStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        labelStyle.setBorderTop(BorderStyle.THIN);
        labelStyle.setBorderBottom(BorderStyle.THIN);
        labelStyle.setBorderLeft(BorderStyle.THIN);
        labelStyle.setBorderRight(BorderStyle.THIN);
        labelStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        labelStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle valueStyle = workbook.createCellStyle();
        Font valueFont = workbook.createFont();
        valueFont.setFontHeightInPoints((short) 11);
        valueStyle.setFont(valueFont);
        valueStyle.setAlignment(HorizontalAlignment.LEFT);
        valueStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        valueStyle.setBorderTop(BorderStyle.THIN);
        valueStyle.setBorderBottom(BorderStyle.THIN);
        valueStyle.setBorderLeft(BorderStyle.THIN);
        valueStyle.setBorderRight(BorderStyle.THIN);

        // 在数据行之后添加所有父表字段
        // 第1行是生产任务单号标题（索引0）
        // 第2行是表头（索引1，由relativeHeadRowIndex=1指定）
        // 第3行开始是数据（索引2开始）
        int headerRowIndex = 1; // 表头行索引（由relativeHeadRowIndex指定）
        int dataStartRow = headerRowIndex + 1; // 数据开始行（索引2）
        int summaryStartRow = dataStartRow + dataRowCount; // 汇总信息开始行
        
        // 准备所有父表字段（除了生产任务单号，已经在顶部显示了）
        // 每行3个字段，每个字段占2列（标签+值），所以每行占6列
        String[][] allFields = {
            {"订单ID", parent.getOrderId() != null ? parent.getOrderId().toString() : ""},
            {"品种", parent.getVariety() != null ? parent.getVariety() : ""},
            {"强度等级", parent.getStrengthGrade() != null ? parent.getStrengthGrade() : ""},
            {"砂率", parent.getSandRatio() != null ? parent.getSandRatio() : ""},
            {"容重", parent.getBulkDensity() != null ? parent.getBulkDensity() : ""},
            {"水胶比", parent.getWaterBinderRatio() != null ? parent.getWaterBinderRatio() : ""},
            {"依据标准", parent.getCriterion() != null ? parent.getCriterion() : ""},
            {"水泥生产厂家", parent.getManufacturer() != null ? parent.getManufacturer() : ""},
            {"级配型号", parent.getGradation() != null ? parent.getGradation() : ""},
            {"混合料种类", parent.getTypesOfMixtures() != null ? parent.getTypesOfMixtures() : ""},
            {"沥青含量", parent.getAsphaltContent() != null ? parent.getAsphaltContent() : ""},
            {"备注", parent.getRemarks() != null ? parent.getRemarks() : ""},
            {"状态", parent.getStatus() != null && parent.getStatus() == 0 ? "开启" : "关闭"}
        };
        
        // 每行3个字段，计算需要多少行
        int fieldsPerRow = 3;
        int totalRows = (int) Math.ceil((double) allFields.length / fieldsPerRow);
        
        // 创建汇总信息行
        for (int rowIndex = 0; rowIndex < totalRows; rowIndex++) {
            Row summaryRow = sheet.createRow(summaryStartRow + rowIndex);
            int startFieldIndex = rowIndex * fieldsPerRow;
            int endFieldIndex = Math.min(startFieldIndex + fieldsPerRow, allFields.length);
            
            for (int fieldIndex = startFieldIndex; fieldIndex < endFieldIndex; fieldIndex++) {
                int colInRow = fieldIndex - startFieldIndex; // 在当前行中的列索引（0, 1, 2）
                int colIndex = colInRow * 2; // 实际列索引（每个字段占2列：标签+值）
                
                Cell labelCell = summaryRow.createCell(colIndex);
                labelCell.setCellValue(allFields[fieldIndex][0]);
                labelCell.setCellStyle(labelStyle);
                
                Cell valueCell = summaryRow.createCell(colIndex + 1);
                valueCell.setCellValue(allFields[fieldIndex][1]);
                valueCell.setCellStyle(valueStyle);
            }
        }
    }
}

