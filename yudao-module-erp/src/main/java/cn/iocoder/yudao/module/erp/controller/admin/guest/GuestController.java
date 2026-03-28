package cn.iocoder.yudao.module.erp.controller.admin.guest;

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

import cn.iocoder.yudao.module.erp.controller.admin.guest.vo.*;
import cn.iocoder.yudao.module.erp.dal.dataobject.guest.GuestDO;
import cn.iocoder.yudao.module.erp.service.guest.GuestService;

@Tag(name = "管理后台 - 红正客户")
@RestController
@RequestMapping("/erp/guest")
@Validated
public class GuestController {

    @Resource
    private GuestService guestService;

    @PostMapping("/create")
    @Operation(summary = "创建红正客户")
    @PreAuthorize("@ss.hasPermission('erp:guest:create')")
    public CommonResult<Long> createGuest(@Valid @RequestBody GuestSaveReqVO createReqVO) {
        return success(guestService.createGuest(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新红正客户")
    @PreAuthorize("@ss.hasPermission('erp:guest:update')")
    public CommonResult<Boolean> updateGuest(@Valid @RequestBody GuestSaveReqVO updateReqVO) {
        guestService.updateGuest(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除红正客户")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('erp:guest:delete')")
    public CommonResult<Boolean> deleteGuest(@RequestParam("id") Long id) {
        guestService.deleteGuest(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除红正客户")
                @PreAuthorize("@ss.hasPermission('erp:guest:delete')")
    public CommonResult<Boolean> deleteGuestList(@RequestParam("ids") List<Long> ids) {
        guestService.deleteGuestListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得红正客户")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('erp:guest:query')")
    public CommonResult<GuestRespVO> getGuest(@RequestParam("id") Long id) {
        GuestDO guest = guestService.getGuest(id);
        return success(BeanUtils.toBean(guest, GuestRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得红正客户分页")
    @PreAuthorize("@ss.hasPermission('erp:guest:query')")
    public CommonResult<PageResult<GuestRespVO>> getGuestPage(@Valid GuestPageReqVO pageReqVO) {
        PageResult<GuestDO> pageResult = guestService.getGuestPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GuestRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出红正客户 Excel")
    @PreAuthorize("@ss.hasPermission('erp:guest:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGuestExcel(@Valid GuestPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GuestDO> list = guestService.getGuestPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "红正客户.xls", "数据", GuestRespVO.class,
                        BeanUtils.toBean(list, GuestRespVO.class));
    }


    @GetMapping("/simple-list")
    @Operation(summary = "获得客户精简列表", description = "只包含被开启的产品，主要用于前端的下拉选项")
    public CommonResult<List<GuestRespVO>> getGuestSimpleList() {
        List<GuestRespVO> guestVOListByStatus = guestService.getGuestVOListByStatus(CommonStatusEnum.ENABLE.getStatus());
        return success(guestVOListByStatus);
    }


    @GetMapping("/getByType")
    @Operation(summary = "根据类型搜索客户", description = "根据类型搜索客户")
    public CommonResult<List<GuestRespVO>> getByType(@RequestParam("type")Integer type) {
        if (type == null){
            return success(new ArrayList<>());
        }
        List<GuestRespVO> guestVOListByStatus = guestService.getByType(type);
        return success(guestVOListByStatus);
    }


    @GetMapping("/getContactByGuestId")
    @Operation(summary = "搜索客户下联系人列表", description = "搜索客户下联系人列表")
    public CommonResult<List<GuestContactDO>> getContactByGuestId(@RequestParam("id")Long id) {

        List<GuestContactDO> guestVOListByStatus = guestService.getContactByGuestId(id);
        return success(guestVOListByStatus);
    }

}