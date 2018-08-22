package com.shuxiajian.frame.common;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 实体类属性注解执行器
 *
 * @author pengzx
 * @create 2018-08-02 15:21
 */

public class AttributeProcessor {
    public static Map<String,Attribute> processAnnotations(Class clazz){
        Map<String,Attribute> map = new HashMap<String,Attribute>();

        for (Field field : clazz.getDeclaredFields()){
            map.put(field.getName(),field.getAnnotation(Attribute.class));
        }
        return map;
    }
}
