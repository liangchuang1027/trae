package cn.iocoder.yudao.module.erp.controller.admin.hzMatchingForm;

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

import cn.iocoder.yudao.module.erp.controller.admin.hzMatchingForm.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.hzMatchingForm.HzMatchingFormDO;
import cn.iocoder.yudao.module.erp.service.hzMatchingForm.HzMatchingFormService;

@Tag(name = "管理后台 - hz -  配比模版")
@RestController
@RequestMapping("/erp/hz-matching-form")
@Validated
public class HzMatchingFormController {

    @Resource
    private HzMatchingFormService hzMatchingFormService;

    @PostMapping("/create")
    @Operation(summary = "创建配比模版")
    @PreAuthorize("@ss.hasPermission('erp:hz-matching-form:create')")
    public CommonResult<Long> createHzMatchingForm(@Valid @RequestBody HzMatchingFormSaveReqVO createReqVO) {
        return success(hzMatchingFormService.createHzMatchingForm(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新配比模版")
    @PreAuthorize("@ss.hasPermission('erp:hz-matching-form:update')")
    public CommonResult<Boolean> updateHzMatchingForm(@Valid @RequestBody HzMatchingFormSaveReqVO updateReqVO) {
        hzMatchingFormService.updateHzMatchingForm(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除配比模版")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('erp:hz-matching-form:delete')")
    public CommonResult<Boolean> deleteHzMatchingForm(@RequestParam("id") Long id) {
        hzMatchingFormService.deleteHzMatchingForm(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除配比模版")
                @PreAuthorize("@ss.hasPermission('erp:hz-matching-form:delete')")
    public CommonResult<Boolean> deleteHzMatchingFormList(@RequestParam("ids") List<Long> ids) {
        hzMatchingFormService.deleteHzMatchingFormListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得配比模版")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('erp:hz-matching-form:query')")
    public CommonResult<HzMatchingFormRespVO> getHzMatchingForm(@RequestParam("id") Long id) {
        HzMatchingFormDO hzMatchingForm = hzMatchingFormService.getHzMatchingForm(id);
        return success(BeanUtils.toBean(hzMatchingForm, HzMatchingFormRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得配比模版分页")
    @PreAuthorize("@ss.hasPermission('erp:hz-matching-form:query')")
    public CommonResult<PageResult<HzMatchingFormRespVO>> getHzMatchingFormPage(@Valid HzMatchingFormPageReqVO pageReqVO) {
        PageResult<HzMatchingFormDO> pageResult = hzMatchingFormService.getHzMatchingFormPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, HzMatchingFormRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出配比模版 Excel")
    @PreAuthorize("@ss.hasPermission('erp:hz-matching-form:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHzMatchingFormExcel(@Valid HzMatchingFormPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<HzMatchingFormDO> list = hzMatchingFormService.getHzMatchingFormPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "配比模版.xls", "数据", HzMatchingFormRespVO.class,
                        BeanUtils.toBean(list, HzMatchingFormRespVO.class));
    }

    @GetMapping("/getByProductCategoryId")
    @Operation(summary = "根据产品类型id获得配比模版")
    @Parameter(name = "productId", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('erp:hz-matching-form:query')")
    public CommonResult<List<HzMatchingFormDO>> getByProductId(@RequestParam("productCategoryId") Long productCategoryId) {
        List<HzMatchingFormDO>  hzMatchingForm = hzMatchingFormService.selectByProductCategoryId(productCategoryId);
        return success(hzMatchingForm);
    }



    // ==================== 子表（配比模版原料） ====================
//
//    @GetMapping("/hz-matching-form-raw/page")
//    @Operation(summary = "获得配比模版原料分页")
//    @Parameter(name = "orderFormId", description = "配比模版表id")
//    @PreAuthorize("@ss.hasPermission('erp:hz-matching-form:query')")
//    public CommonResult<PageResult<HzMatchingFormRawDO>> getHzMatchingFormRawPage(PageParam pageReqVO,
//                                                                                        @RequestParam("orderFormId") Integer orderFormId) {
//        return success(hzMatchingFormService.getHzMatchingFormRawPage(pageReqVO, orderFormId));
//    }
//
//    @PostMapping("/hz-matching-form-raw/create")
//    @Operation(summary = "创建配比模版原料")
//    @PreAuthorize("@ss.hasPermission('erp:hz-matching-form:create')")
//    public CommonResult<Long> createHzMatchingFormRaw(@Valid @RequestBody HzMatchingFormRawDO hzMatchingFormRaw) {
//        return success(hzMatchingFormService.createHzMatchingFormRaw(hzMatchingFormRaw));
//    }
//
//    @PutMapping("/hz-matching-form-raw/update")
//    @Operation(summary = "更新配比模版原料")
//    @PreAuthorize("@ss.hasPermission('erp:hz-matching-form:update')")
//    public CommonResult<Boolean> updateHzMatchingFormRaw(@Valid @RequestBody HzMatchingFormRawDO hzMatchingFormRaw) {
//        hzMatchingFormService.updateHzMatchingFormRaw(hzMatchingFormRaw);
//        return success(true);
//    }
//
//    @DeleteMapping("/hz-matching-form-raw/delete")
//    @Parameter(name = "id", description = "编号", required = true)
//    @Operation(summary = "删除配比模版原料")
//    @PreAuthorize("@ss.hasPermission('erp:hz-matching-form:delete')")
//    public CommonResult<Boolean> deleteHzMatchingFormRaw(@RequestParam("id") Long id) {
//        hzMatchingFormService.deleteHzMatchingFormRaw(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/hz-matching-form-raw/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除配比模版原料")
//    @PreAuthorize("@ss.hasPermission('erp:hz-matching-form:delete')")
//    public CommonResult<Boolean> deleteHzMatchingFormRawList(@RequestParam("ids") List<Long> ids) {
//        hzMatchingFormService.deleteHzMatchingFormRawListByIds(ids);
//        return success(true);
//    }
//
//	@GetMapping("/hz-matching-form-raw/get")
//	@Operation(summary = "获得配比模版原料")
//	@Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('erp:hz-matching-form:query')")
//	public CommonResult<HzMatchingFormRawDO> getHzMatchingFormRaw(@RequestParam("id") Long id) {
//	    return success(hzMatchingFormService.getHzMatchingFormRaw(id));
//	}

}