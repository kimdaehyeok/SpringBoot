package com.example.demo.functioninterface;

import java.util.function.Function;

/* A functional interface is an interface that has just one abstract method (aside from the methods of Object), 
 * and thus represents a single function contract.
 * 
 * :: is called Method Reference. It is basically a reference to a single method. i.e. 
 * it refers to an existing method by name.
 * */

/* Functional Interfaces are mainly used in Lambda expressions, Method reference and constructor references.
 * In functional programming, code can be treated as data. 
 * For this purpose Lambda expressions are introduced. 
 * They can be used to pass a block of code to another method or object.
 * Functional Interface serves as a data type for Lambda expressions. 
 * Since a Functional interface contains only one abstract method, 
 * the implementation of that method becomes the code that gets passed as an argument to another method.
 * */

public class FunctionalInterfaceTestMain 
{
	public static void main(String[] args) 
	{
		MethodCollection methodCollection = new MethodCollection();
		
		Function<Double, Double> square = methodCollection::square;
		double ans = square.apply(23d);
		System.out.println(ans);

		ArithmeticOperator addIntegerObj = methodCollection::addInteger;
		System.out.println(addIntegerObj.operate(677));
		
		ArithmeticOperatorParam<Integer, Integer> multipleintegerObj = methodCollection::mulipleInteger;
		System.out.println(multipleintegerObj.multipleInteger(10));
		
		HaveTwoNumber haveTwoNumberObj = new HaveTwoNumber();
		haveTwoNumberObj.setNumber1(5);
		haveTwoNumberObj.setNumber2(10);
		
		ArithmeticOperatorObject<HaveTwoNumber, Integer> functionInterfaceObjectArgument = methodCollection::objectSum;
		System.out.println(functionInterfaceObjectArgument.multipleNumber(haveTwoNumberObj));
	}
}

class MethodCollection 
{
	public double square(double num) 
	{
		return Math.pow(num, 2);
	}
	
	public int addInteger(int number)
	{
		return number + 100;
	}
	
	public int mulipleInteger(int param)
	{
		return param * param;
	}
	
	public int objectSum(HaveTwoNumber object)
	{
		int number1 = object.getNumber1();
		int number2 = object.getNumber2();
				
		return number1 + number2;
	}
}

