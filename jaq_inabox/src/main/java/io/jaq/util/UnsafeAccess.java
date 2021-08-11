package io.jaq.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author cy
 * @className UnsafeAccess
 * @description TODO
 * @date 2021/8/11 11:40
 */
public class UnsafeAccess {

    public static final Unsafe UNSAFE;

    static
    {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe)field.get(null);
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
