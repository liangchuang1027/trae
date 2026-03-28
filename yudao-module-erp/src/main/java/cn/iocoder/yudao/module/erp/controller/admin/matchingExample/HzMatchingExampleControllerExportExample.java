package cn.iocoder.yudao.module.erp.controller.admin.matchingExample;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;

/**
 * 匹配示例 Controller（导出示例）
 * 
 * 这是一个示例文件，展示如何将父子关系数据导出到Excel
 * 实际使用时，请将exportHzMatchingExampleExcel方法合并到你的HzMatchingExampleController中
 *
 * @author 芋道源码
 */
@RestController
@RequestMapping("/erp/hz-matching-example")
public class HzMatchingExampleControllerExportExample {

    /**
     * 导出匹配示例 Excel（包含父子关系）
     * 
     * 核心思路：
     * 1. 查询父表数据列表
     * 2. 查询所有子表数据并建立映射关系
     * 3. 将父子关系展开为扁平化的Excel导出VO列表
     * 4. 如果父记录没有子记录，也需要导出一行（子表字段为空）
     */
    @GetMapping("/export-excel")
    @io.swagger.v3.oas.annotations.Operation(summary = "导出匹配示例 Excel")
    @PreAuthorize("@ss.hasPermission('erp:hz-matching-example:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHzMatchingExampleExcel(@Valid HzMatchingExamplePageReqVO pageReqVO,
                                             HttpServletResponse response) throws IOException {
        // 1. 设置不分页，查询所有数据
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        
        // 2. 查询父表数据列表
        // List<HzMatchingExampleDO> parentList = matchingExampleService.getMatchingExamplePage(pageReqVO).getList();
        // 注意：这里需要替换为实际的服务调用
        
        // 3. 查询所有子表数据并建立映射关系
        // List<Long> parentIds = convertSet(parentList, HzMatchingExampleDO::getId);
        // List<HzMatchingExampleRawDO> rawList = matchingExampleService.getRawListByParentIds(parentIds);
        // Map<Long, List<HzMatchingExampleRawDO>> rawMap = convertMultiMap(rawList, HzMatchingExampleRawDO::getParentId);
        
        // 4. 将父子关系展开为扁平化的Excel导出VO列表
        List<HzMatchingExampleExcelExportVO> exportList = new ArrayList<>();
        
        // 模拟数据转换过程（请替换为实际的数据转换逻辑）
        /*
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
        */
        
        // 5. 导出 Excel
        ExcelUtils.write(response, "匹配示例.xls", "数据", HzMatchingExampleExcelExportVO.class, exportList);
    }

    /**
     * 构建Excel导出VO
     * 
     * @param parent 父表数据
     * @param raw 子表数据（可为null）
     * @return Excel导出VO
     */
    private HzMatchingExampleExcelExportVO buildExportVO(/* HzMatchingExampleDO parent, HzMatchingExampleRawDO raw */) {
        HzMatchingExampleExcelExportVO exportVO = new HzMatchingExampleExcelExportVO();
        
        // 填充父表字段
        /*
        exportVO.setId(parent.getId());
        exportVO.setName(parent.getName());
        exportVO.setCode(parent.getCode());
        exportVO.setStatus(parent.getStatus());
        exportVO.setRemark(parent.getRemark());
        exportVO.setCreateTime(parent.getCreateTime());
        */
        
        // 填充子表字段（如果子表数据不为空）
        /*
        if (raw != null) {
            exportVO.setRawId(raw.getId());
            exportVO.setRawField1(raw.getField1());
            exportVO.setRawField2(raw.getField2());
            exportVO.setRawCount(raw.getCount());
            exportVO.setRawAmount(raw.getAmount());
            exportVO.setRawRemark(raw.getRemark());
        }
        */
        
        return exportVO;
    }

    // 占位类，实际使用时请删除
    public static class HzMatchingExamplePageReqVO {
        private Integer pageSize;
        public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }
    }
}

