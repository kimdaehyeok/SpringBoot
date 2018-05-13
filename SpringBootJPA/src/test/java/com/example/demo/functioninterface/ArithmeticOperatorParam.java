package com.example.demo.functioninterface;


@FunctionalInterface
public interface ArithmeticOperatorParam<T, R> 
{
	R multipleInteger(T t);
}
