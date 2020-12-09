package com.wangjg.util.id;

/**
 * @author wangjg
 * 2020/5/8
 */
public interface IdGenerator<ReturnType> {
    ReturnType generate();
}
