package com.aweika.common.utils;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.WeakReference;

import static org.dozer.loader.api.TypeMappingOptions.mapEmptyString;
import static org.dozer.loader.api.TypeMappingOptions.mapNull;

/**
 * Created by YS on 2017/7/27.
 */
public class DozerUtil {

    private  final static Logger logger = LoggerFactory.getLogger(DozerUtil.class);

    public static <T> T copy(Object object, Class<T> c) {
        WeakReference weakReference = new WeakReference(new DozerBeanMapper());
        Mapper mapper = new DozerBeanMapper();
        try {
            return mapper.map(object, c);
        } catch (Exception e) {
            return null;
        }
    }

    public static void copyProperties(final Object sources, final Object destination) {
        WeakReference weakReference = new WeakReference(new DozerBeanMapper());
        DozerBeanMapper mapper = (DozerBeanMapper) weakReference.get();
        mapper.addMapping(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(sources.getClass(), destination.getClass(), mapNull(false), mapEmptyString(false));
            }
        });
        try {
            mapper.map(sources, destination);
        } catch (Exception e) {
            logger.error("转换出错",e);
        }
        mapper.destroy();
        weakReference.clear();
    }

    public static void copyAll(final Object sources, final Object destination){
        WeakReference weakReference = new WeakReference(new DozerBeanMapper());
        DozerBeanMapper mapper = (DozerBeanMapper) weakReference.get();
        try {
            mapper.map(sources, destination);
        } catch (Exception e) {
            logger.error("转换出错",e);
        }
        mapper.destroy();
        weakReference.clear();
    }
}
