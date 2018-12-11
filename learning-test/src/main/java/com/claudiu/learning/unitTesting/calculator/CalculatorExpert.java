package com.claudiu.learning.unitTesting.calculator;

import java.util.List;

public class CalculatorExpert {
	
	private Calculator calculatorBasic;
	

	public double addMultiple(List<Double> items) {
		double sum = 0d;
		for (Double d : items) {
			if (d.doubleValue() != 0d) {
				sum = this.calculatorBasic.add(sum, d);
			}
		}
		return sum;
	}


	public void setCalculatorBasic(Calculator calculatorBasic) {
		this.calculatorBasic = calculatorBasic;
		
	}

}
