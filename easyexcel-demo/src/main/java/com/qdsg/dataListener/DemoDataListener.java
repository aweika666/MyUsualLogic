package com.qdsg.dataListener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * @author: Aweika_Fang
 * @date: 2020/10/19
 * @description:
 */
public class DemoDataListener<T,K> extends AnalysisEventListener<T> {

    private List<T> holder = new ArrayList<>();
    //完成读取后需要调用的引用
    private K pointer;
    //完成读取后上面引用需要调用的方法
    private String methodName;

    /**
     * 注意: 传入的方法 入参只能是一个: 数据集合 list
     *
     * @param pointer
     * @param methodName
     */
    public DemoDataListener(K pointer, String methodName) {
        this.pointer = pointer;
        this.methodName = methodName;
    }

    /**
     * 这个每一条数据解析都会来调用
     */
    @Override
    public void invoke(T data, AnalysisContext analysisContext) {
        //放入容器 之后一起处理
        holder.add(data);
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        Method method;
        try {
            method = pointer.getClass().getDeclaredMethod(methodName);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException("方法不存在");
        }
        method.setAccessible(true);
        try {
            method.invoke(pointer,holder);
        } catch (IllegalAccessException  | InvocationTargetException e) {
            throw new RuntimeException("引用不正确");
        }
    }
}
