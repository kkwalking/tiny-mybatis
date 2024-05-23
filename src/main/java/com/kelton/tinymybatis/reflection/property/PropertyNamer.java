package com.kelton.tinymybatis.reflection.property;

import java.util.Locale;

/**
 * @Author zhouzekun
 * @Date 2024/5/20 11:06
 */
public class PropertyNamer {

    private PropertyNamer() {
    }

    /**
     * 方法转换为属性
     */
    public static String methodToProperty(String name) {
        if (name.startsWith("is")) {
            name = name.substring(2);
        } else if (name.startsWith("get") || name.startsWith("set")) {
            name = name.substring(3);
        } else {
            throw new RuntimeException("Error parsing property name '" + name + "'.  Didn't start with 'is', 'get' or 'set'.");
        }

        if(name.length() == 1 || ( !Character.isUpperCase(name.charAt(1)))) {
            name =name.substring(0, 1).toLowerCase(Locale.ENGLISH) +name.substring(1);
        }
        return name;
    }

    /**
     * 开头判断get/set/is
     */
    public static boolean isProperty(String name) {
        return isGetter(name) || isSetter(name);
    }

    /**
     * 是否为 getter
     */
    public static boolean isGetter(String name) {
        return name.startsWith("get") || name.startsWith("is");
    }

    /**
     * 是否为 setter
     */
    public static boolean isSetter(String name) {
        return name.startsWith("set");
    }

}
