package com.aweika;

/**
 * @author: Michael
 * @date: 2020/3/31
 * @description: 泛型类
 * adress: https://www.cnblogs.com/coprince/p/8603492.html
 */
public class GenericClass<T> {
    private T tag;

    public T getTag() {
        return tag;
    }

    public GenericClass setTag(T tag) {
        this.tag = tag;
        return this;
    }
    public GenericClass() {

    }
    //泛型通配符
    public void wildcard(GenericClass<?> obj){
        //通配符的入参逻辑 和 这样写(GenericClass obj)是一样的
        System.out.println("通配符泛型测试" + obj.getTag());
    }
    //泛型方法
    public <K> K genericMethod(K inner){
        if (inner instanceof  String){
            //empty
            //运行时擦除,K在运行时就是个Object类型.
            // 编译器也是一样的策略,引用静态绑定到Object,所以只能调用Object的方法
        }
        System.out.println(inner.toString());
        return inner;
    }

    public static void main(String[] args) {
        GenericClass<String> a1 = new GenericClass<>();
        GenericClass<Integer> a2 = new GenericClass<>();
        a1.wildcard(a1);
        a1.wildcard(a2);
        System.out.println("//////////////////");
        GenericClass<String> genericClass = new GenericClass<>();
        User user = new User();
        user.setName("方启哲");
        User user1 = genericClass.genericMethod(user);
        System.out.println(user1);

    }
}
