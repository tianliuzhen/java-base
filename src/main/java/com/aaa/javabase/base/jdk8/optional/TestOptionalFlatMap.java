package com.aaa.javabase.base.jdk8.optional;

import com.aaa.javabase.base.jdk8.optional.domain.Car;
import com.aaa.javabase.base.jdk8.optional.domain.Person;

import java.util.Optional;


/**
 * @author liuzhen.tian
 * @version 1.0 TestOptionalFlatMap.java  2021/3/22 22:43
 */
public class TestOptionalFlatMap {
    public static void main(String[] args) {
        // 构造对象: 属性为Optional类型
        Person person = new Person();
        Car car = new Car();
        car.setCarName("奥迪汽车");
        person.setCar(Optional.of(car));
        person.setName("小铭");
        Optional<Person> optionalPerson = Optional.of(person);

        // 通过 person 调用 car 的 carname

        // 错误写法：使用map链调用Optional对象，
        // String carName = optionalPerson.map(Person::getCar).map(Car::getCarName).get(); //无法编译

        // 正确写法： 使用flatMap链调用Optional对象，通过 person 调用 car 的 carname
        String carName = optionalPerson.flatMap(Person::getCar).map(Car::getCarName).get();
        System.out.println(carName);
    }
}
