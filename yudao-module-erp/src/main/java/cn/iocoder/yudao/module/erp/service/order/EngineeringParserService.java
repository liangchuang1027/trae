package cn.iocoder.yudao.module.erp.service.order;


import cn.iocoder.yudao.module.erp.controller.admin.order.vo.EngineeringInfo;
import cn.iocoder.yudao.module.erp.controller.admin.order.vo.ParseResponse;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.regex.*;

@Service
public class EngineeringParserService {
    
    // 字段映射配置
    private static final Map<String, String[]> FIELD_PATTERNS = new LinkedHashMap<String, String[]>() {{
        put("orderName", new String[]{"订单名称", "订单"});
        put("productName", new String[]{"产品名称", "产品"});

        put("customerName", new String[]{"客户名称", "客户", "委托单位"});
        put("projectName", new String[]{"项目名称", "项目", "工程名称"});
        put("constructionTeam", new String[]{"施工队", "施工单位", "施工队伍"});
        put("concreteGrade", new String[]{"砼标号", "混凝土标号", "标号", "强度等级"});
        put("volume", new String[]{"方量", "混凝土方量", "数量", "体积"});
        put("pouringPart", new String[]{"浇筑部位", "部位", "施工部位"});
        put("slump", new String[]{"塌落度", "坍落度", "slump"});
        put("deliveryMethod", new String[]{"自卸或泵送", "运输方式", "输送方式", "泵送方式"});
        put("time", new String[]{"时间", "浇筑时间", "到场时间", "施工时间"});
        put("contactPhone", new String[]{"联系电话", "电话", "手机", "联系方式"});
    }};
    
    // 特殊字段处理规则
    private static final Set<String> SPECIAL_FIELDS = new HashSet<>(Arrays.asList(
        "projectName", "constructionTeam", "pouringPart", "time"
    ));
    
    /**
     * 解析工程信息
     */
    public ParseResponse parseEngineeringInfo(String content) {
        try {
            if (content == null || content.trim().isEmpty()) {
                return ParseResponse.builder()
                        .success(false)
                        .message("解析内容为空")
                        .data(null)
                        .build();
            }
            
            // 清理和预处理文本
            String cleanedContent = preprocessContent(content);
            
            // 提取字段
            EngineeringInfo info = extractAllFields(cleanedContent, content);
            
            // 后处理验证
            validateAndAdjust(info);
            
            return ParseResponse.builder()
                    .success(true)
                    .message("解析成功")
                    .data(info)
                    .build();
            
        } catch (Exception e) {

            System.out.println("解析异常: " + e.getMessage());
            return ParseResponse.builder()
                    .success(false)
                    .message("解析失败: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }
    
    /**
     * 预处理文本
     */
    private String preprocessContent(String content) {
        // 统一换行符
        String cleaned = content.replaceAll("\\r\\n|\\r", "\n");
        
        // 统一中文标点
        cleaned = cleaned.replaceAll("：", ":");
        cleaned = cleaned.replaceAll("，", ",");
        cleaned = cleaned.replaceAll("；", ";");
        
        // 处理连续空格和制表符（但保留换行符）
        // 先替换每行内的连续空格
        String[] lines = cleaned.split("\n");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].replaceAll("[ \\t]+", " ").trim();
            if (i > 0) {
                result.append("\n");
            }
            result.append(line);
        }
        
        return result.toString();
    }
    
    /**
     * 提取所有字段
     */
    private EngineeringInfo extractAllFields(String cleanedContent, String originalContent) {
        EngineeringInfo.EngineeringInfoBuilder builder = EngineeringInfo.builder()
                .originalText(originalContent);
        
        // 用于跟踪已设置的字段值
        Map<String, String> fieldValues = new HashMap<>();
        
        // 分割为行
        String[] lines = cleanedContent.split("\n");
        
        // 用于跟踪已处理的行
        boolean[] processed = new boolean[lines.length];
        
        // 第一轮：精确匹配已知字段
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.isEmpty()) {
                processed[i] = true;
                continue;
            }
            
            for (Map.Entry<String, String[]> entry : FIELD_PATTERNS.entrySet()) {
                String fieldKey = entry.getKey();
                String[] fieldNames = entry.getValue();
                
                for (String fieldName : fieldNames) {
                    // 检查是否为完整字段行
                    if (isFieldLine(line, fieldName)) {
                        String value = extractFieldValue(line, fieldName, lines, i);
                        
                        // 设置到对应的字段
                        setFieldValue(builder, fieldKey, value);
                        // 同时记录到 fieldValues Map 中
                        fieldValues.put(fieldKey, value);
                        
                        // 标记为已处理
                        processed[i] = true;
                        
                        // 如果该字段可能跨行，标记后续相关的行
                        if (SPECIAL_FIELDS.contains(fieldKey) && value.contains("\n")) {
                            int lineCount = value.split("\n").length;
                            for (int j = i + 1; j < Math.min(i + lineCount, lines.length); j++) {
                                processed[j] = true;
                            }
                        }
                        break;
                    }
                }
                if (processed[i]) break;
            }
        }
        
        // 第二轮：处理可能的无标签字段（如施工队）
        for (int i = 0; i < lines.length; i++) {
            if (!processed[i]) {
                String line = lines[i].trim();
                if (!line.isEmpty() && !line.contains(":")) {
                    // 检查是否为施工队（没有标签的单独一行）
                    // 情况1：包含"队"或"组"字
                    if (fieldValues.get("constructionTeam") == null &&
                        line.matches(".*[队组].*")) {
                        builder.constructionTeam(line);
                        fieldValues.put("constructionTeam", line);
                        processed[i] = true;
                        continue;
                    }
                    
                    // 情况2：在项目名称之后、砼标号之前的无标签行，很可能是施工队
                    if (fieldValues.get("constructionTeam") == null &&
                        fieldValues.get("projectName") != null &&
                        i > 0 && i < lines.length - 1) {
                        String prevLine = lines[i-1].trim();
                        String nextLine = lines[i+1].trim();
                        // 上一行包含"项目名称"，下一行包含"砼标号"
                        if (prevLine.contains("项目名称") && nextLine.contains("砼标号")) {
                            builder.constructionTeam(line);
                            fieldValues.put("constructionTeam", line);
                            processed[i] = true;
                            continue;
                        }
                    }
                }
            }
        }
        
        // 收集未识别的内容作为备注
        StringBuilder remarks = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            if (!processed[i]) {
                String line = lines[i].trim();
                if (!line.isEmpty()) {
                    if (remarks.length() > 0) remarks.append("; ");
                    remarks.append(line);
                }
            }
        }
        builder.remarks(remarks.length() > 0 ? remarks.toString() : "");
        
        return builder.build();
    }
    
    /**
     * 判断是否为字段行
     */
    private boolean isFieldLine(String line, String fieldName) {
        // 精确匹配
        if (line.startsWith(fieldName + ":")) {
            return true;
        }
        
        // 模糊匹配（允许中间有空格）
        Pattern pattern = Pattern.compile("^" + fieldName + "\\s*:.*", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(line).matches();
    }
    
    /**
     * 提取字段值
     */
    private String extractFieldValue(String line, String fieldName, String[] lines, int currentIndex) {
        // 提取冒号后的内容
        String[] parts = line.split(":", 2);
        if (parts.length == 2) {
            String value = parts[1].trim();
            
            // 对于特殊字段，检查是否需要合并后续行
            if (SPECIAL_FIELDS.contains(getFieldKeyByDisplayName(fieldName))) {
                return mergeMultiLineValue(value, lines, currentIndex);
            }
            
            return value;
        }
        
        return "";
    }
    
    /**
     * 合并多行值（用于长项目名称等）
     */
    private String mergeMultiLineValue(String firstLineValue, String[] lines, int currentIndex) {
        StringBuilder value = new StringBuilder(firstLineValue);
        
        // 检查后续行是否为该字段的延续（没有新字段标签）
        for (int i = currentIndex + 1; i < lines.length; i++) {
            String nextLine = lines[i].trim();
            
            // 如果下一行是空的，跳过
            if (nextLine.isEmpty()) continue;
            
            // 如果下一行以字段标签开头，停止合并
            if (isFieldLabelLine(nextLine)) break;
            
            // 如果下一行看起来像是另一个字段的值（比如包含"队"、"组"等关键字），停止合并
            // 这样可以避免将"李家墨施工队"这样的无标签行错误合并到项目名称中
            if (isLikelyAnotherField(nextLine)) break;
            
            value.append(" ").append(nextLine);
        }
        
        return value.toString().trim();
    }
    
    /**
     * 判断一行是否可能是另一个字段的值（而不是当前字段的延续）
     */
    private boolean isLikelyAnotherField(String line) {
        // 如果包含"队"或"组"，可能是施工队字段
        if (line.matches(".*[队组].*")) {
            return true;
        }
        // 如果看起来像是电话号码（全是数字或包含数字和常见分隔符）
        if (line.matches(".*1[3-9]\\d{9}.*") || line.matches("^\\d{11}$")) {
            return true;
        }
        // 如果看起来像是方量（包含数字和"方"字）
        if (line.matches(".*\\d+方.*")) {
            return true;
        }
        return false;
    }
    
    /**
     * 判断是否为字段标签行
     */
    private boolean isFieldLabelLine(String line) {
        for (String[] fieldNames : FIELD_PATTERNS.values()) {
            for (String fieldName : fieldNames) {
                if (line.startsWith(fieldName + ":") || 
                    line.matches("^" + fieldName + "\\s*:.*")) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 根据显示名称获取字段key
     */
    private String getFieldKeyByDisplayName(String displayName) {
        for (Map.Entry<String, String[]> entry : FIELD_PATTERNS.entrySet()) {
            for (String name : entry.getValue()) {
                if (name.equals(displayName)) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }
    
    /**
     * 设置字段值
     */
    private void setFieldValue(EngineeringInfo.EngineeringInfoBuilder builder, 
                               String fieldKey, String value) {
        switch (fieldKey) {
            case "orderName":
                builder.orderName(value);
                break;
            case "productName":
                builder.productName(value);
                break;
            case "customerName":
                builder.customerName(value);
                break;
            case "projectName":
                builder.projectName(value);
                break;
            case "constructionTeam":
                builder.constructionTeam(value);
                break;
            case "concreteGrade":
                builder.concreteGrade(value);
                break;
            case "volume":
                builder.volume(value);
                break;
            case "pouringPart":
                builder.pouringPart(value);
                break;
            case "slump":
                builder.slump(value);
                break;
            case "deliveryMethod":
                builder.deliveryMethod(value);
                break;
            case "time":
                builder.time(value);
                break;
            case "contactPhone":
                builder.contactPhone(value);
                break;
        }
    }
    
    /**
     * 验证和调整数据
     */
    private void validateAndAdjust(EngineeringInfo info) {
        // 清理电话号码（只保留数字）
        if (info.getContactPhone() != null && !info.getContactPhone().isEmpty()) {
            String cleaned = info.getContactPhone().replaceAll("[^0-9]", "").trim();
            info.setContactPhone(cleaned.isEmpty() ? null : cleaned);
        }
        
        // 清理方量中的单位（只保留数字和小数点）
        if (info.getVolume() != null && !info.getVolume().isEmpty()) {
            String cleaned = info.getVolume().replaceAll("[^0-9.]", "").trim();
            info.setVolume(cleaned.isEmpty() ? null : cleaned);
        }
        
        // 确保备注字段不为null
        if (info.getRemarks() == null) {
            info.setRemarks("");
        }
    }
    
    /**
     * 智能解析（容错性更强的解析）
     */
    public EngineeringInfo smartParse(String content) {
        Map<String, String> extracted = new HashMap<>();
        String remaining = content;
        
        // 按顺序尝试提取每个字段
        for (Map.Entry<String, String[]> entry : FIELD_PATTERNS.entrySet()) {
            String fieldKey = entry.getKey();
            String[] fieldNames = entry.getValue();
            
            for (String fieldName : fieldNames) {
                Pattern pattern = Pattern.compile(
                    fieldName + "\\s*[:：]\\s*(.*?)(?=\\n|$|" + 
                    String.join("|", getAllFieldPatterns()) + "\\s*[:：])",
                    Pattern.DOTALL
                );
                
                Matcher matcher = pattern.matcher(remaining);
                if (matcher.find()) {
                    extracted.put(fieldKey, matcher.group(1).trim());
                    break;
                }
            }
        }
        
        return EngineeringInfo.builder()
                .orderName(extracted.get("orderName"))
                .productName(extracted.get("productName"))
                .customerName(extracted.get("customerName"))
                .projectName(extracted.get("projectName"))
                .constructionTeam(extracted.get("constructionTeam"))
                .concreteGrade(extracted.get("concreteGrade"))
                .volume(extracted.get("volume"))
                .pouringPart(extracted.get("pouringPart"))
                .slump(extracted.get("slump"))
                .deliveryMethod(extracted.get("deliveryMethod"))
                .time(extracted.get("time"))
                .contactPhone(extracted.get("contactPhone"))
                .originalText(content)
                .build();
    }
    
    private List<String> getAllFieldPatterns() {
        List<String> patterns = new ArrayList<>();
        for (String[] names : FIELD_PATTERNS.values()) {
            patterns.addAll(Arrays.asList(names));
        }
        return patterns;
    }
}