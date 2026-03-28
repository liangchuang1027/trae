package cn.iocoder.yudao.module.erp.controller.admin.reviewrRecord;

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

import cn.iocoder.yudao.module.erp.controller.admin.reviewrRecord.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.reviewrRecord.HzReviewRecordDO;
import cn.iocoder.yudao.module.erp.service.reviewrRecord.HzReviewRecordService;

@Tag(name = "管理后台 - 红正建材审核记录")
@RestController
@RequestMapping("/erp/hz-review-record")
@Validated
public class HzReviewRecordController {

    @Resource
    private HzReviewRecordService hzReviewRecordService;

    @PostMapping("/create")
    @Operation(summary = "创建红正建材审核记录")
    @PreAuthorize("@ss.hasPermission('erp:hz-review-record:create')")
    public CommonResult<Long> createHzReviewRecord(@Valid @RequestBody HzReviewRecordSaveReqVO createReqVO) {
        return success(hzReviewRecordService.createHzReviewRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新红正建材审核记录")
    @PreAuthorize("@ss.hasPermission('erp:hz-review-record:update')")
    public CommonResult<Boolean> updateHzReviewRecord(@Valid @RequestBody HzReviewRecordSaveReqVO updateReqVO) {
        hzReviewRecordService.updateHzReviewRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除红正建材审核记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('erp:hz-review-record:delete')")
    public CommonResult<Boolean> deleteHzReviewRecord(@RequestParam("id") Long id) {
        hzReviewRecordService.deleteHzReviewRecord(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除红正建材审核记录")
                @PreAuthorize("@ss.hasPermission('erp:hz-review-record:delete')")
    public CommonResult<Boolean> deleteHzReviewRecordList(@RequestParam("ids") List<Long> ids) {
        hzReviewRecordService.deleteHzReviewRecordListByIds(ids);
        return success(true);
    }

    @GetMapping("/getByOrderId")
    @Operation(summary = "获得红正建材审核记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<HzReviewRecordRespVO> getByOrderId(@RequestParam("id") Long id) {
        HzReviewRecordDO hzReviewRecord = hzReviewRecordService.getByOrderId(id);
        return success(BeanUtils.toBean(hzReviewRecord, HzReviewRecordRespVO.class));
    }

    @GetMapping("/getByPurchaseId")
    @Operation(summary = "获得采购订单审核记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<HzReviewRecordRespVO> getByPurchaseId(@RequestParam("id") Long id) {
        HzReviewRecordDO hzReviewRecord = hzReviewRecordService.getByPurchaseId(id);
        return success(BeanUtils.toBean(hzReviewRecord, HzReviewRecordRespVO.class));
    }


    @GetMapping("/get")
    @Operation(summary = "获得红正建材审核记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('erp:hz-review-record:query')")
    public CommonResult<HzReviewRecordRespVO> getHzReviewRecord(@RequestParam("id") Long id) {
        HzReviewRecordDO hzReviewRecord = hzReviewRecordService.getHzReviewRecord(id);
        return success(BeanUtils.toBean(hzReviewRecord, HzReviewRecordRespVO.class));
    }
    @GetMapping("/page")
    @Operation(summary = "获得红正建材审核记录分页")
    @PreAuthorize("@ss.hasPermission('erp:hz-review-record:query')")
    public CommonResult<PageResult<HzReviewRecordRespVO>> getHzReviewRecordPage(@Valid HzReviewRecordPageReqVO pageReqVO) {
        PageResult<HzReviewRecordDO> pageResult = hzReviewRecordService.getHzReviewRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, HzReviewRecordRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出红正建材审核记录 Excel")
    @PreAuthorize("@ss.hasPermission('erp:hz-review-record:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHzReviewRecordExcel(@Valid HzReviewRecordPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<HzReviewRecordDO> list = hzReviewRecordService.getHzReviewRecordPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "红正建材审核记录.xls", "数据", HzReviewRecordRespVO.class,
                        BeanUtils.toBean(list, HzReviewRecordRespVO.class));
    }

}