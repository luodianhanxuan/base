package com.wangjg.sms.tencent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author wangjg
 * 2020/3/25
 */
public enum ValidateCodeTypeEnum {
    REGISTER(563837, "validatecode:register:times:%s:%s", "validatecode:register:code:%s"),
    FORGET_PASSWORD(563837, "validatecode:forgetpwd:times:%s:%s", "validatecode:forgetpwd:code:%s"),
    ;

    /**
     * 腾讯云短信模板ID
     */
    private int templateId;

    /**
     * 单日单用户发送的次数存储 key
     */
    private String keyFormat4SendTimes;
    /**
     * 用户的验证码 存储 key
     */
    private String keyFormat4Code;

    ValidateCodeTypeEnum(int templateId, String keyFormat4SendTimes, String keyFormat4Code) {
        this.templateId = templateId;
        this.keyFormat4SendTimes = keyFormat4SendTimes;
        this.keyFormat4Code = keyFormat4Code;
    }

    public int getTempalteId() {
        return this.templateId;
    }

    public String getKey4SendTimes(String mobile) {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return String.format(keyFormat4SendTimes, today, mobile);
    }

    public String getKey4Code(String mobile) {
        return String.format(this.keyFormat4Code, mobile);
    }


}
