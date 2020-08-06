package com.aweika;

/**
 * @author: Michael
 * @date: 2020/3/31
 * @description: 泛型接口
 * 泛型接口与泛型类的定义及使用基本相同。泛型接口常被用在各种类的生产器中
 */
public interface Generator<T> {
     T next();
}
