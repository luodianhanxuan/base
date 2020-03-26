package com.wangjg.sms.tencent;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static com.wangjg.sms.tencent.ValidateCodeTypeEnum.REGISTER;

/**
 * @author wangjg
 * 2020/3/25
 */
@Slf4j
@SuppressWarnings("unused")
public class Sms {
    private static final String TAG = "腾讯云短信服务工具类";

    /**
     * 腾讯短信应用 sdk appId
     */
    private int appid = 1400341121; // AppID 以1400开头

    /**
     * 短信应用 SDK AppKey
     */
    private String appkey = "9b55c3c5f1182972065a099e98858a61";

    // 签名 : 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请
    String smsSign = "宽拓智融";


    public boolean sendSmsSingle(String mobile, int templateId, ArrayList<String> params) {
        String[] paramArr = new String[params.size()];
        params.toArray(paramArr);
        return sendSmsSingle(mobile, templateId, paramArr);
    }

    public boolean sendSmsSingle(String mobile, int templateId, String[] params) {
        log.info(String.format("%s: mobile [ %s ] templateId [ %s ] params [ %s ]", TAG, mobile, templateId, Arrays.toString(params)));
        SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
        try {
            SmsSingleSenderResult result;
            int count = 0;
            do {
                count++;
                if (count > 5) {
                    log.info(String.format("%s: 发送失败次数【%s】太多了", TAG, count));
                    return false;
                }
                result = ssender.sendWithParam("86", mobile,
                        templateId, params, smsSign, "", "");
            } while (result != null && result.result != 0);
        } catch (IOException | HTTPException e) {
            log.error(String.format("%s: 短信发送失败", TAG), e);
            return false;
        }
        log.info(String.format("%s: 短信发送成功", TAG));
        return true;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public void setSmsSign(String smsSign) {
        this.smsSign = smsSign;
    }

}
