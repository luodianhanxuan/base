package com.wangjg.util.id;

import java.util.UUID;

/**
 * @author wangjg
 * 2020/5/8
 */
public class IdOfRandomUUIDGenerator implements IdGenerator<String> {

    @Override
    public String generate() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
