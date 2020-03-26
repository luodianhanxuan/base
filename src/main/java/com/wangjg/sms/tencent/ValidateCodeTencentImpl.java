package com.wangjg.sms.tencent;

/**
 * @author wangjg
 * 2020/3/25
 */
public class ValidateCodeTencentImpl extends ValidateCodeLocalCacheImpl {
    private Sms sms;

    public ValidateCodeTencentImpl() {
        sms = new Sms();
    }

    @Override
    protected boolean doSend(ValidateCodeTypeEnum validateCodeTypeEnum, String mobile, String code) {
        return sms.sendSmsSingle(mobile, validateCodeTypeEnum.getTempalteId(), new String[]{code});
    }
}
