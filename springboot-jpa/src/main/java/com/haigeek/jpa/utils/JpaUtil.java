package com.haigeek.jpa.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @desc 使用原生sql进行复杂查询时，返回List<Object[]>数据
 * 需要解析该数据，封装进对应的实体中
 * @author zhaohj
 * @date 2019/05/06
 */
public class JpaUtil {
    private final static Logger LOG = LoggerFactory.getLogger(JpaUtil.class);

    /**
     * 将Object[]转换成实体bean
     * @param sourceObjList
     * @param targetType
     * @param <T>
     * @return
     */
    public static <T> List<T> convertObjectArrayToBean(List<Object[]> sourceObjList, Class targetType){
        List<T> resultList = new ArrayList<>();
        //使用反射获取实体字段
        Field[] fields = targetType.getDeclaredFields();
        //分析出所有方法
        List<Class> fieldsTypeList = Arrays.stream(fields).map(Field::getType)
                .collect(Collectors.toList());
        sourceObjList.forEach(item->{
            try {
                Class[] fieldTypeClasses = new Class[fieldsTypeList.size()];
                fieldsTypeList.toArray(fieldTypeClasses);
                //获取实体bean的构造器
                Constructor<T> constructor = targetType.getConstructor(fieldTypeClasses);
                T t = constructor.newInstance(item);
                resultList.add(t);
            } catch (NoSuchMethodException e) {
                LOG.info("请检查类的构造方法是否满足转换规则...");
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        return resultList;
    }
}
