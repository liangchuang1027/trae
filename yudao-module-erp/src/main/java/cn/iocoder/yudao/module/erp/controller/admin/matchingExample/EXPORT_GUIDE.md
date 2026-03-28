# 父子关系Excel导出实现指南

## 问题说明
需要将 `HzMatchingExampleDO` 和其子表 `List<HzMatchingExampleRawDO> raws` 的数据一起导出到Excel中。

## 解决方案

### 方案一：展开为多行（推荐）

将父子关系展开为多行数据，每行包含父表字段和子表字段。如果父记录有N条子记录，则生成N行数据；如果没有子记录，也生成一行（子表字段为空）。

#### 步骤1：创建扁平化的Excel导出VO

参考已创建的 `HzMatchingExampleExcelExportVO.java`，包含：
- 父表的所有需要导出的字段（添加 `@ExcelProperty` 注解）
- 子表的所有需要导出的字段（添加 `@ExcelProperty` 注解）

#### 步骤2：修改导出方法

在你的 `HzMatchingExampleController` 的 `exportHzMatchingExampleExcel` 方法中，按以下步骤实现：

```java
@GetMapping("/export-excel")
@Operation(summary = "导出匹配示例 Excel")
@PreAuthorize("@ss.hasPermission('erp:hz-matching-example:export')")
@ApiAccessLog(operateType = EXPORT)
public void exportHzMatchingExampleExcel(@Valid HzMatchingExamplePageReqVO pageReqVO,
                                         HttpServletResponse response) throws IOException {
    // 1. 设置不分页，查询所有数据
    pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
    
    // 2. 查询父表数据列表
    List<HzMatchingExampleDO> parentList = matchingExampleService.getMatchingExamplePage(pageReqVO).getList();
    
    // 3. 查询所有子表数据并建立映射关系
    List<Long> parentIds = convertSet(parentList, HzMatchingExampleDO::getId);
    List<HzMatchingExampleRawDO> rawList = matchingExampleService.getRawListByParentIds(parentIds);
    Map<Long, List<HzMatchingExampleRawDO>> rawMap = convertMultiMap(rawList, HzMatchingExampleRawDO::getParentId);
    
    // 4. 将父子关系展开为扁平化的Excel导出VO列表
    List<HzMatchingExampleExcelExportVO> exportList = new ArrayList<>();
    for (HzMatchingExampleDO parent : parentList) {
        List<HzMatchingExampleRawDO> raws = rawMap.get(parent.getId());
        
        if (CollUtil.isEmpty(raws)) {
            // 如果父记录没有子记录，也要导出一行（子表字段为空）
            HzMatchingExampleExcelExportVO exportVO = buildExportVO(parent, null);
            exportList.add(exportVO);
        } else {
            // 如果父记录有子记录，每个子记录都生成一行
            for (HzMatchingExampleRawDO raw : raws) {
                HzMatchingExampleExcelExportVO exportVO = buildExportVO(parent, raw);
                exportList.add(exportVO);
            }
        }
    }
    
    // 5. 导出 Excel
    ExcelUtils.write(response, "匹配示例.xls", "数据", HzMatchingExampleExcelExportVO.class, exportList);
}

/**
 * 构建Excel导出VO
 */
private HzMatchingExampleExcelExportVO buildExportVO(HzMatchingExampleDO parent, HzMatchingExampleRawDO raw) {
    HzMatchingExampleExcelExportVO exportVO = new HzMatchingExampleExcelExportVO();
    
    // 填充父表字段
    exportVO.setId(parent.getId());
    exportVO.setName(parent.getName());
    exportVO.setCode(parent.getCode());
    exportVO.setStatus(parent.getStatus());
    exportVO.setRemark(parent.getRemark());
    exportVO.setCreateTime(parent.getCreateTime());
    
    // 填充子表字段（如果子表数据不为空）
    if (raw != null) {
        exportVO.setRawId(raw.getId());
        exportVO.setRawField1(raw.getField1()); // 替换为实际的字段名
        exportVO.setRawField2(raw.getField2()); // 替换为实际的字段名
        exportVO.setRawCount(raw.getCount());   // 替换为实际的字段名
        exportVO.setRawAmount(raw.getAmount()); // 替换为实际的字段名
        exportVO.setRawRemark(raw.getRemark()); // 替换为实际的字段名
    }
    
    return exportVO;
}
```

#### 需要的导入

```java
import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertMultiMap;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertSet;
```

### 方案二：合并子表数据为一个字段

如果子表数据较少且简单，可以将子表数据合并为一个字符串字段导出（例如用逗号分隔）。

```java
// 在buildExportVO方法中，可以这样处理：
if (CollUtil.isNotEmpty(raws)) {
    String rawInfo = raws.stream()
        .map(raw -> raw.getField1() + ":" + raw.getField2())
        .collect(Collectors.joining("; "));
    exportVO.setRawInfo(rawInfo);
}
```

## 注意事项

1. **字段映射**：请根据实际的 `HzMatchingExampleDO` 和 `HzMatchingExampleRawDO` 的字段名来调整 `HzMatchingExampleExcelExportVO` 和 `buildExportVO` 方法。

2. **服务方法**：确保你的 Service 中有根据父表ID列表查询子表数据的方法，例如：
   ```java
   List<HzMatchingExampleRawDO> getRawListByParentIds(List<Long> parentIds);
   ```

3. **性能考虑**：如果数据量很大，建议使用批量查询的方式获取子表数据，避免N+1查询问题。

4. **Excel列顺序**：`@ExcelProperty` 注解中的列顺序就是Excel中的列顺序，可以根据需要调整。

## 示例Excel输出效果

| 主表编号 | 主表名称 | 主表编码 | ... | 子表编号 | 子表字段1 | 子表字段2 | ... |
|---------|---------|---------|-----|---------|----------|----------|-----|
| 1       | 示例1   | CODE001 | ... | 101     | 值1      | 值2      | ... |
| 1       | 示例1   | CODE001 | ... | 102     | 值3      | 值4      | ... |
| 2       | 示例2   | CODE002 | ... | (空)    | (空)     | (空)     | ... |

如上所示，父记录"示例1"有2条子记录，所以生成2行；父记录"示例2"没有子记录，生成1行（子表字段为空）。

