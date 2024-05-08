package com.kelton.tinymybatis.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Enumeration;

public class ClassScanner {
     /**
     * 扫描指定包下的所有类
     * @param packageName 包名
     * @return 类集合
     */
    public static Set<Class<?>> scanPackage(String packageName) {
        String path = packageName.replace('.', '/');
        try {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);
            Set<File> dirs = new HashSet<>();
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                dirs.add(new File(resource.getFile()));
            }
            Set<Class<?>> classes = new HashSet<>();
            for (File directory : dirs) {
                classes.addAll(findClasses(directory, packageName));
            }
            return classes;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return Collections.emptySet();
    }

    /**
     * 递归获取指定包下的所有类
     * @param directory 目录
     * @param packageName 包名
     * @return 类集合
     */
    private static Set<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        Set<Class<?>> classes = new HashSet<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    classes.addAll(findClasses(file, packageName + "." + file.getName()));
                } else if (file.getName().endsWith(".class")) {
                    classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
                }
            }
        }
        return classes;
    }
}