package com.wangjg.sms.tencent;

import com.wangjg.cache.CacheManager;

import java.util.concurrent.TimeUnit;

/**
 * @author wangjg
 * 2020/3/25
 */
public abstract class ValidateCodeLocalCacheImpl extends ValidateCode {

    @Override
    protected String getCode(ValidateCodeTypeEnum validateCodeTypeEnum, String mobile) {
        Object codeObj = CacheManager.get(validateCodeTypeEnum.getKey4Code(mobile));
        return codeObj == null ? null : codeObj.toString();
    }

    @Override
    protected boolean saveSendTimes(ValidateCodeTypeEnum validateCodeTypeEnum, String mobile, int times) {
        return CacheManager.put(validateCodeTypeEnum.getKey4SendTimes(mobile), times, 1, TimeUnit.DAYS);
    }

    @Override
    protected boolean saveCode(ValidateCodeTypeEnum validateCodeTypeEnum, String mobile, String code) {
        return CacheManager.put(validateCodeTypeEnum.getKey4Code(mobile), code, 5, TimeUnit.MINUTES);
    }

    @Override
    protected int getTodayTimes(ValidateCodeTypeEnum validateCodeTypeEnum, String mobile) {
        return CacheManager.get(validateCodeTypeEnum.getKey4SendTimes(mobile)) == null ? 0 : (int) CacheManager.get(validateCodeTypeEnum.getKey4SendTimes(mobile));
    }


    public static void main(String[] args) {
        CacheManager.put("test", 1);
        System.out.println((int) CacheManager.get("test"));
    }
}
