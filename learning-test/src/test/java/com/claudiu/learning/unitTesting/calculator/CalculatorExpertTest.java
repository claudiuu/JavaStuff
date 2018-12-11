package com.claudiu.learning.unitTesting.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;

public class CalculatorExpertTest {


	@Test
	public void testAddMultiple() {
		Calculator calculatorMock = Mockito.mock(Calculator.class);
		when(calculatorMock.add(anyDouble(), anyDouble())).thenCallRealMethod();

		ArgumentCaptor<Double> doubleCaptor = ArgumentCaptor.forClass(Double.class);
		
		CalculatorExpert expert = new CalculatorExpert();
		expert.setCalculatorBasic(calculatorMock);
		
		assertEquals(expert.addMultiple(Arrays.asList(1d, 2d, 3d)), 6d);
		Mockito.verify(calculatorMock, VerificationModeFactory.times(3)).add(anyDouble(), anyDouble());

		assertFalse(doubleCaptor.getAllValues().contains(0d));
		
	}
}
