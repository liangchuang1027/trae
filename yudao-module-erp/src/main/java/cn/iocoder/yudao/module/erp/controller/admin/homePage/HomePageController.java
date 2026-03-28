package cn.iocoder.yudao.module.erp.controller.admin.homePage;

import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.enums.OrderStatus;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.erp.controller.admin.order.vo.HzOrderPageReqVO;
import cn.iocoder.yudao.module.erp.controller.admin.order.vo.HzOrderRespVO;
import cn.iocoder.yudao.module.erp.controller.admin.order.vo.HzOrderSaveReqVO;
import cn.iocoder.yudao.module.erp.dal.dataobject.order.HzOrderDO;
import cn.iocoder.yudao.module.erp.enums.ErrorCodeConstants;
import cn.iocoder.yudao.module.erp.service.order.HzOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台首页")
@RestController
@RequestMapping("/erp/home")
@Validated
public class HomePageController {

    @Resource
    private HzOrderService hzOrderService;

    @PostMapping("/getOrderCount")
    public CommonResult<HashMap<String, Object>> getOrderCount() {
        HashMap<String, Object> count =   hzOrderService.getOrderCount();
        return success(count);
    }

}