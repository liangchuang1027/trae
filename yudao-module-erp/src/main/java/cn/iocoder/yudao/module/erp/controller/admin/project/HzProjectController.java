package cn.iocoder.yudao.module.erp.controller.admin.project;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.module.erp.controller.admin.product.vo.product.ErpProductRespVO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;

import cn.iocoder.yudao.module.erp.controller.admin.project.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.project.HzProjectDO;
import cn.iocoder.yudao.module.erp.service.project.HzProjectService;

@Tag(name = "管理后台 - hz - 项目")
@RestController
@RequestMapping("/erp/hz-project")
@Validated
public class HzProjectController {

    @Resource
    private HzProjectService hzProjectService;

    @PostMapping("/create")
    @Operation(summary = "创建红正建材项目")
    @PreAuthorize("@ss.hasPermission('erp:hz-project:create')")
    public CommonResult<Long> createHzProject(@Valid @RequestBody HzProjectSaveReqVO createReqVO) {
        return success(hzProjectService.createHzProject(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新红正建材项目")
    @PreAuthorize("@ss.hasPermission('erp:hz-project:update')")
    public CommonResult<Boolean> updateHzProject(@Valid @RequestBody HzProjectSaveReqVO updateReqVO) {
        hzProjectService.updateHzProject(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除红正建材项目")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('erp:hz-project:delete')")
    public CommonResult<Boolean> deleteHzProject(@RequestParam("id") Long id) {
        hzProjectService.deleteHzProject(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除红正建材项目")
                @PreAuthorize("@ss.hasPermission('erp:hz-project:delete')")
    public CommonResult<Boolean> deleteHzProjectList(@RequestParam("ids") List<Long> ids) {
        hzProjectService.deleteHzProjectListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得红正建材项目")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('erp:hz-project:query')")
    public CommonResult<HzProjectRespVO> getHzProject(@RequestParam("id") Long id) {
        HzProjectDO hzProject = hzProjectService.getHzProject(id);
        return success(BeanUtils.toBean(hzProject, HzProjectRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得红正建材项目分页")
    @PreAuthorize("@ss.hasPermission('erp:hz-project:query')")
    public CommonResult<PageResult<HzProjectRespVO>> getHzProjectPage(@Valid HzProjectPageReqVO pageReqVO) {
        PageResult<HzProjectDO> pageResult = hzProjectService.getHzProjectPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, HzProjectRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出红正建材项目 Excel")
    @PreAuthorize("@ss.hasPermission('erp:hz-project:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHzProjectExcel(@Valid HzProjectPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<HzProjectDO> list = hzProjectService.getHzProjectPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "红正建材项目.xls", "数据", HzProjectRespVO.class,
                        BeanUtils.toBean(list, HzProjectRespVO.class));
    }

    @GetMapping("/simple-list")
    @Operation(summary = "获得产品精简列表", description = "只包含被开启的产品，主要用于前端的下拉选项")
    public CommonResult<List<HzProjectRespVO>> getProductSimpleList() {
        List<HzProjectDO> list = hzProjectService.getProjectVOListByStatus(CommonStatusEnum.ENABLE.getStatus());
        return success(convertList(list, project -> new HzProjectRespVO().setId(project.getId())
                .setProjectName(project.getProjectName()).setGuesyList(project.getGuesyList())));
    }

}