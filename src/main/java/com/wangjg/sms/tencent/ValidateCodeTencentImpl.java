package com.wangjg.sms.tencent;

import java.util.Scanner;

import static com.wangjg.sms.tencent.ValidateCodeTypeEnum.REGISTER;

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
        return sms.sendSmsSingle(mobile, validateCodeTypeEnum.getTempalteId(), new String[]{code,"5"});
    }

    public static void main(String[] args) {
        ValidateCode validateCode = new ValidateCodeTencentImpl();

        boolean send = validateCode.send(REGISTER, "13363066261");
        System.out.println(send);

        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String next = input.next();
            boolean b = validateCode.verifyCode(REGISTER,"13363066261",next);
            System.out.println(b);
        }

    }
}
