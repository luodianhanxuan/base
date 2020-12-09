package com.wangjg.sms.tencent;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangjg
 * 2020/3/25
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
@Slf4j
public abstract class ValidateCode {
    private static final String TAG = "验证码工具类（基类）";

    protected int upLimit1Day;
    private Lock lock = new ReentrantLock();

    public ValidateCode() {
        upLimit1Day = 10;
    }

    public boolean verifyCode(ValidateCodeTypeEnum validateCodeTypeEnum, String mobile, String code) {
        mobile = mobile.trim();
        String savedCode = this.getCode(validateCodeTypeEnum, mobile);
        return Objects.equals(code, savedCode);
    }

    protected abstract String getCode(ValidateCodeTypeEnum validateCodeTypeEnum, String mobile);

    public boolean send(ValidateCodeTypeEnum validateCodeTypeEnum, String mobile) {
        try {
            mobile = mobile.trim();
            lock.lockInterruptibly();
            int times = this.getTodayTimes(validateCodeTypeEnum, mobile);
            if (times > upLimit1Day) {
                log.info(String.format("%s: 今日该手机号验证码发送已到上限", TAG));
                return false;
            }

            String code = this.generateCode();

            return this.doSend(validateCodeTypeEnum, mobile, code)
                    && this.saveCode(validateCodeTypeEnum, mobile, code)
                    && this.saveSendTimes(validateCodeTypeEnum, mobile, ++times);
        } catch (InterruptedException e) {
            log.info(String.format("%s: 等待锁被打断", TAG));
            return false;
        } finally {
            lock.unlock();
        }
    }

    private String generateCode() {
        Random random = new Random();
        return String.valueOf(random.nextInt(899999) + 100001);
    }

    protected abstract boolean saveSendTimes(ValidateCodeTypeEnum validateCodeTypeEnum, String mobile, int times);

    protected abstract boolean doSend(ValidateCodeTypeEnum validateCodeTypeEnum, String mobile, String code);

    protected abstract boolean saveCode(ValidateCodeTypeEnum validateCodeTypeEnum, String mobile, String code);

    protected abstract int getTodayTimes(ValidateCodeTypeEnum validateCodeTypeEnum, String mobile);

}
