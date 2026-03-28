package cn.iocoder.yudao.module.erp.controller.admin.hzRawConsume;


import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.erp.dal.dataobject.hzRawConsume.HzRawConsumeDO;
import cn.iocoder.yudao.module.erp.enums.ErrorCodeConstants;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import io.swagger.v3.oas.annotations.Operation;


import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.error;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.erp.controller.admin.hzRawConsume.vo.*;
import cn.iocoder.yudao.module.erp.service.hzRawConsume.HzRawConsumeService;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/erp/hz-raw-consume")
@Validated
public class HzRawConsumeController {

    @Resource
    private HzRawConsumeService hzRawConsumeService;




    @PostMapping("/importRawConsume")
    @Operation(summary = "录入原料消耗")
    public CommonResult<Boolean> importRawConsume(@RequestParam("file") MultipartFile file
            ,@RequestParam(value = "orderId") Long orderId
            ,@RequestParam(value = "warehouseId") Long warehouseId)  {
        List<HzRawConsumeEx> list;
        try {
            list = ExcelUtils.read(file, HzRawConsumeEx.class);
        }catch (Exception e){
            return CommonResult.error(ErrorCodeConstants.RUCAN_ERROR);
        }
        return success(hzRawConsumeService.importRawConsume(list,orderId,warehouseId));
    }



    @GetMapping("/page")
    @Operation(summary = "获得订单原料消耗表分页")
    public CommonResult<PageResult<HzRawConsumeRespVO>> getHzRawConsumePage(@Valid HzRawConsumePageReqVO pageReqVO) {
        pageReqVO.setPageSize(-1);
        if(pageReqVO.getOrderId() == null || pageReqVO.getCreateTime() == null) {
            return error(ErrorCodeConstants.RU_CAN_ERROR);
        }

        PageResult<HzRawConsumeDO> pageResult = hzRawConsumeService.getHzRawConsumePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, HzRawConsumeRespVO.class));
    }



    @GetMapping("/getByOrderId")
    @Operation(summary = "获得订单使用的原料")
    public CommonResult< List<HzRawConsumeRespVO>> getHzRawConsume(@RequestParam("orderId") Long orderId) {
        List<HzRawConsumeDO> hzRawConsume = hzRawConsumeService.getByOrderId(orderId);
        return success(BeanUtils.toBean(hzRawConsume, HzRawConsumeRespVO.class));
    }




//    @PostMapping("/create")
//    @Operation(summary = "创建订单原料消耗表
//")
//    @PreAuthorize("@ss.hasPermission('erp:hz-raw-consume:create')")
//    public CommonResult<Long> createHzRawConsume(@Valid @RequestBody HzRawConsumeSaveReqVO createReqVO) {
//        return success(hzRawConsumeService.createHzRawConsume(createReqVO));
//    }
//
//    @PutMapping("/update")
//    @Operation(summary = "更新订单原料消耗表
//")
//    @PreAuthorize("@ss.hasPermission('erp:hz-raw-consume:update')")
//    public CommonResult<Boolean> updateHzRawConsume(@Valid @RequestBody HzRawConsumeSaveReqVO updateReqVO) {
//        hzRawConsumeService.updateHzRawConsume(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除订单原料消耗表
//")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('erp:hz-raw-consume:delete')")
//    public CommonResult<Boolean> deleteHzRawConsume(@RequestParam("id") Long id) {
//        hzRawConsumeService.deleteHzRawConsume(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除订单原料消耗表
//")
//                @PreAuthorize("@ss.hasPermission('erp:hz-raw-consume:delete')")
//    public CommonResult<Boolean> deleteHzRawConsumeList(@RequestParam("ids") List<Long> ids) {
//        hzRawConsumeService.deleteHzRawConsumeListByIds(ids);
//        return success(true);
//    }
//
//    @GetMapping("/get")
//    @Operation(summary = "获得订单原料消耗表
//")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('erp:hz-raw-consume:query')")
//    public CommonResult<HzRawConsumeRespVO> getHzRawConsume(@RequestParam("id") Long id) {
//        HzRawConsumeDO hzRawConsume = hzRawConsumeService.getHzRawConsume(id);
//        return success(BeanUtils.toBean(hzRawConsume, HzRawConsumeRespVO.class));
//    }
//
//    @GetMapping("/page")
//    @Operation(summary = "获得订单原料消耗表
//分页")
//    @PreAuthorize("@ss.hasPermission('erp:hz-raw-consume:query')")
//    public CommonResult<PageResult<HzRawConsumeRespVO>> getHzRawConsumePage(@Valid HzRawConsumePageReqVO pageReqVO) {
//        PageResult<HzRawConsumeDO> pageResult = hzRawConsumeService.getHzRawConsumePage(pageReqVO);
//        return success(BeanUtils.toBean(pageResult, HzRawConsumeRespVO.class));
//    }
//
//    @GetMapping("/export-excel")
//    @Operation(summary = "导出订单原料消耗表
// Excel")
//    @PreAuthorize("@ss.hasPermission('erp:hz-raw-consume:export')")
//    @ApiAccessLog(operateType = EXPORT)
//    public void exportHzRawConsumeExcel(@Valid HzRawConsumePageReqVO pageReqVO,
//              HttpServletResponse response) throws IOException {
//        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
//        List<HzRawConsumeDO> list = hzRawConsumeService.getHzRawConsumePage(pageReqVO).getList();
//        // 导出 Excel
//        ExcelUtils.write(response, "订单原料消耗表
//.xls", "数据", HzRawConsumeRespVO.class,
//                        BeanUtils.toBean(list, HzRawConsumeRespVO.class));
//    }

}