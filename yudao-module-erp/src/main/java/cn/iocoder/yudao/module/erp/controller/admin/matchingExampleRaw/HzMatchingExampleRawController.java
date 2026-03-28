package cn.iocoder.yudao.module.erp.controller.admin.matchingExampleRaw;

import cn.iocoder.yudao.module.erp.controller.admin.matchingExample.vo.HzMatchingExampleRespVO;
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

import cn.iocoder.yudao.module.erp.controller.admin.matchingExampleRaw.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.matchingExampleRaw.HzMatchingExampleRawDO;
import cn.iocoder.yudao.module.erp.service.matchingExampleRaw.HzMatchingExampleRawService;

@Tag(name = "管理后台 - 配比原料实例")
@RestController
@RequestMapping("/erp/hz-matching-example-raw")
@Validated
public class HzMatchingExampleRawController {

    @Resource
    private HzMatchingExampleRawService hzMatchingExampleRawService;

    @PostMapping("/create")
    @Operation(summary = "创建配比原料实例")
    @PreAuthorize("@ss.hasPermission('erp:hz-matching-example-raw:create')")
    public CommonResult<Long> createHzMatchingExampleRaw(@Valid @RequestBody HzMatchingExampleRawSaveReqVO createReqVO) {
        return success(hzMatchingExampleRawService.createHzMatchingExampleRaw(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新配比原料实例")
    @PreAuthorize("@ss.hasPermission('erp:hz-matching-example-raw:update')")
    public CommonResult<Boolean> updateHzMatchingExampleRaw(@Valid @RequestBody HzMatchingExampleRawSaveReqVO updateReqVO) {
        hzMatchingExampleRawService.updateHzMatchingExampleRaw(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除配比原料实例")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('erp:hz-matching-example-raw:delete')")
    public CommonResult<Boolean> deleteHzMatchingExampleRaw(@RequestParam("id") Long id) {
        hzMatchingExampleRawService.deleteHzMatchingExampleRaw(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除配比原料实例")
                @PreAuthorize("@ss.hasPermission('erp:hz-matching-example-raw:delete')")
    public CommonResult<Boolean> deleteHzMatchingExampleRawList(@RequestParam("ids") List<Long> ids) {
        hzMatchingExampleRawService.deleteHzMatchingExampleRawListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得配比原料实例")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('erp:hz-matching-example-raw:query')")
    public CommonResult<HzMatchingExampleRawRespVO> getHzMatchingExampleRaw(@RequestParam("id") Long id) {
        HzMatchingExampleRawDO hzMatchingExampleRaw = hzMatchingExampleRawService.getHzMatchingExampleRaw(id);
        return success(BeanUtils.toBean(hzMatchingExampleRaw, HzMatchingExampleRawRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得配比原料实例分页")
    @PreAuthorize("@ss.hasPermission('erp:hz-matching-example-raw:query')")
    public CommonResult<PageResult<HzMatchingExampleRawRespVO>> getHzMatchingExampleRawPage(@Valid HzMatchingExampleRawPageReqVO pageReqVO) {
        PageResult<HzMatchingExampleRawDO> pageResult = hzMatchingExampleRawService.getHzMatchingExampleRawPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, HzMatchingExampleRawRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出配比原料实例 Excel")
    @PreAuthorize("@ss.hasPermission('erp:hz-matching-example-raw:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHzMatchingExampleRawExcel(@Valid HzMatchingExampleRawPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<HzMatchingExampleRawDO> list = hzMatchingExampleRawService.getHzMatchingExampleRawPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "配比原料实例.xls", "数据", HzMatchingExampleRawRespVO.class,
                        BeanUtils.toBean(list, HzMatchingExampleRawRespVO.class));
    }

    @GetMapping("/getRawByOrderId")
    @Operation(summary = "根据订单id获得配比实例")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult< List<HzMatchingExampleRawRespVO>> getRawByOrderId(@RequestParam("orderId") Long orderId) {
        List<HzMatchingExampleRawDO> hzMatchingExampleRaw = hzMatchingExampleRawService.getByOrderId(orderId);
        return success(BeanUtils.toBean(hzMatchingExampleRaw, HzMatchingExampleRawRespVO.class));
    }


}