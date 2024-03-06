package com.example.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class FormUtils {
    public static <T> T toModel(Class<T> clazz, HttpServletRequest request) {
        T object = null;
        try {
            object = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.populate(object, request.getParameterMap());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            System.out.print(e.getMessage());
        }
        return object;
    }
}
