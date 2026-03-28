package cn.iocoder.yudao.module.erp.controller.admin.finance;


import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.erp.controller.admin.finance.vo.account.LaShi;
import com.google.gson.JsonObject;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Random;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@RestController
@RequestMapping("/lashi")
public class LaShiController {


    @PostMapping("/nila")
    @Operation(summary = "你拉了吗")
    public CommonResult<Long> nila(@RequestBody LaShi req) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        list.add("SMS_500455372");
        list.add("SMS_500940008");
        list.add("SMS_501290001");

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", req.getName());
        String string = jsonObject.toString();

        com.aliyun.dysmsapi20170525.Client client = createClient();
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setPhoneNumbers(req.getPhones())
                .setSignName("添融纳原")
                .setTemplateCode(list.get(new Random().nextInt(list.size())))
                .setTemplateParam(string);

            com.aliyun.dysmsapi20170525.models.SendSmsResponse resp = client.sendSmsWithOptions(sendSmsRequest, new com.aliyun.teautil.models.RuntimeOptions());
            System.out.println(new com.google.gson.Gson().toJson(resp));

        return success(resp.getStatusCode().longValue());
    }

    @PostMapping("/wola")
    @Operation(summary = "我拉完了")
    public CommonResult<Long> wola(@RequestBody LaShi req) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        list.add("SMS_501110008");
        list.add("SMS_501190007");

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", req.getName());
        String string = jsonObject.toString();

        com.aliyun.dysmsapi20170525.Client client = createClient();
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setPhoneNumbers(req.getPhones())
                .setSignName("添融纳原")
                .setTemplateCode(list.get(new Random().nextInt(list.size())))
                .setTemplateParam(string);

        com.aliyun.dysmsapi20170525.models.SendSmsResponse resp = client.sendSmsWithOptions(sendSmsRequest, new com.aliyun.teautil.models.RuntimeOptions());
        System.out.println(new com.google.gson.Gson().toJson(resp));

        return success(resp.getStatusCode().longValue());
    }

    public static com.aliyun.dysmsapi20170525.Client createClient() throws Exception {
        // 工程代码建议使用更安全的无AK方式，凭据配置方式请参见：https://help.aliyun.com/document_detail/378657.html。
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，System.getenv()表示从环境变量中获取AccessKey ID
                .setAccessKeyId("LTAI5tH49fJ4BeVWM2FTrUEW")
                // 必填，System.getenv()表示从环境变量中获取AccessKey Secret
                .setAccessKeySecret("HysJtXLoq474gvV3Bv4vddpSIlscPb");
        // Endpoint 请参考 https://api.aliyun.com/product/Dysmsapi
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }
}