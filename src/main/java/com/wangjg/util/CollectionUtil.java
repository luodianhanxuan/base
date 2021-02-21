package com.wangjg.util;

import com.sun.istack.internal.Nullable;
import java.util.Collection;

public class CollectionUtil {

    public static boolean isEmpty(@Nullable Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

}
