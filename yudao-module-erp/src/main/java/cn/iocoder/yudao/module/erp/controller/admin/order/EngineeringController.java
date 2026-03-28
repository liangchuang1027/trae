package cn.iocoder.yudao.module.erp.controller.admin.order;


import cn.iocoder.yudao.module.erp.controller.admin.order.vo.ParseRequest;
import cn.iocoder.yudao.module.erp.controller.admin.order.vo.ParseResponse;
import cn.iocoder.yudao.module.erp.service.order.EngineeringParserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/engineering")
@Validated
public class EngineeringController {
    
    @Autowired
    private EngineeringParserService parserService;
    
    /**
     * 解析工程信息
     * POST /api/engineering/parse
     */
    @PostMapping("/parse")
    public ParseResponse parse(@Valid @RequestBody ParseRequest request) {
        return parserService.parseEngineeringInfo(request.getContent());
    }
    
    /**
     * 快速解析（GET方式，方便测试）
     * GET /api/engineering/quick-
     */
    @GetMapping("/quick-parse")
    public ParseResponse quickParse(@RequestParam String content) {
        return parserService.parseEngineeringInfo(content);
    }
    
    /**
     * 健康检查
     * GET /api/engineering/health
     */
    @GetMapping("/health")
    public String health() {
        return "Engineering Parser API is running";
    }
}