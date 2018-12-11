package com.claudiu.learning.unitTesting.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class CalculatorTest2 {

	
	private Calculator calculatorSpy;

	@Before
	public void setup() {
		this.calculatorSpy = spy(Calculator.class);
		when(this.calculatorSpy.add(anyDouble(), anyDouble())).thenReturn(7d);
	}
	
	@Test
	public void testAdd() {
		assertEquals(this.calculatorSpy.add(3d, 4d), 7d);
	}
	
	@Test
	public void testDivide() {
		assertThrows(IllegalArgumentException.class, () -> this.calculatorSpy.divide(9d, 0d), "Division by zero is not allowed");
	}
}
