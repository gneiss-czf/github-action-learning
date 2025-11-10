package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MainTest {

	@Test
	void testNormalAddition() {
		// 测试正常加法：a >= 0, b >= 0, 无溢出
		Main main = new Main();
		int result = main.add(1, 1);
		assertEquals(2, result);
	}
	
	@Test
	void testAddWithZero() {
		// 测试边界条件：a = 0, b >= 0
		Main main = new Main();
		int result = main.add(0, 5);
		assertEquals(5, result);
	}
	
	@Test
	void testAddBothZero() {
		// 测试边界条件：a = 0, b = 0
		Main main = new Main();
		int result = main.add(0, 0);
		assertEquals(0, result);
	}
	
	@Test
	void testNegativeFirstArgument() {
		// 测试条件：a < 0 为真
		Main main = new Main();
		IllegalArgumentException exception = assertThrows(
			IllegalArgumentException.class,
			() -> main.add(-1, 5)
		);
		assertEquals("Both numbers must be non-negative.", exception.getMessage());
	}
	
	@Test
	void testNegativeSecondArgument() {
		// 测试条件：b < 0 为真
		Main main = new Main();
		IllegalArgumentException exception = assertThrows(
			IllegalArgumentException.class,
			() -> main.add(5, -1)
		);
		assertEquals("Both numbers must be non-negative.", exception.getMessage());
	}
	
	@Test
	void testBothNegativeArguments() {
		// 测试条件：a < 0 为真，b < 0 为真
		Main main = new Main();
		IllegalArgumentException exception = assertThrows(
			IllegalArgumentException.class,
			() -> main.add(-1, -1)
		);
		assertEquals("Both numbers must be non-negative.", exception.getMessage());
	}
	
	@Test
	void testIntegerOverflow() {
		// 测试条件：Integer.MAX_VALUE - a < b 为真（溢出）
		Main main = new Main();
		ArithmeticException exception = assertThrows(
			ArithmeticException.class,
			() -> main.add(Integer.MAX_VALUE, 1)
		);
		assertTrue(exception.getMessage().contains("Integer overflow"));
	}
	
	@Test
	void testNearMaxValueNoOverflow() {
		// 测试条件：Integer.MAX_VALUE - a < b 为假（不溢出）
		Main main = new Main();
		int result = main.add(Integer.MAX_VALUE - 10, 5);
		assertEquals(Integer.MAX_VALUE - 5, result);
	}
	
	@Test
	void testMaxValueWithZero() {
		// 测试边界条件：a = MAX_VALUE, b = 0
		Main main = new Main();
		int result = main.add(Integer.MAX_VALUE, 0);
		assertEquals(Integer.MAX_VALUE, result);
	}

}
