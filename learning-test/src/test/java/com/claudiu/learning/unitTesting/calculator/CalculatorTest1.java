package com.claudiu.learning.unitTesting.calculator;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class CalculatorTest1 {

	private Calculator calculatorMock;
	
	@Before
	public void setup() {
		this.calculatorMock = Mockito.mock(Calculator.class);
	}
	
	@Test
	public void testAdd() {
		when(this.calculatorMock.add(2d, 5d)).thenReturn(9d);
		assertEquals(this.calculatorMock.add(2d, 5d), 9d);
		
		assertNotEquals(this.calculatorMock.add(2d, 3d), 5d);
	}
	
	@Test
	public void testDivide() {
		when(this.calculatorMock.divide(anyDouble(), eq(0d))).thenReturn(0d);
		assertEquals(this.calculatorMock.divide(9d, 0d), 0d);
	}
}
