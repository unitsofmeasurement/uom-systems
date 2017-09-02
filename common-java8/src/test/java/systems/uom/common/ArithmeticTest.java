/*
 * Units of Measurement Systems
 * Copyright (c) 2005-2017, Jean-Marie Dautelle, Werner Keil and others.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 *    and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of JSR-363, Units of Measurement nor the names of their contributors may be used to
 *    endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package systems.uom.common;

import static org.junit.Assert.*;
import static tec.uom.se.unit.Units.METRE;

import java.math.BigDecimal;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;

import org.junit.Before;
import org.junit.Test;

import tec.uom.se.quantity.Quantities;
import tec.uom.se.unit.Units;

public class ArithmeticTest {

	private Quantity<Length> sut;
	
	@Before
	public void init() {
		sut = Quantities.getQuantity(10, METRE);
	}
	
	@Test
	public void testValue() {
		assertEquals(Integer.valueOf(10), sut.getValue());
	}
	
	@Test
	public void testToString() {
		assertEquals("10 m", sut.toString());
	}
	
	@Test
	public void testAdd() {
		Quantity<Length> km = Quantities.getQuantity(1000, Units.METRE);
		Quantity<Length> mile = Quantities.getQuantity(1, USCustomary.MILE);
		Quantity<Length> result = km.add(mile);
		assertEquals(BigDecimal.valueOf(2609.344), result.getValue());
		assertEquals(Units.METRE, result.getUnit());
	}
	
	@Test
	public void testAdd2() {
		Quantity<Mass> kg = Quantities.getQuantity(50d, Units.KILOGRAM);
		Quantity<Mass> p = Quantities.getQuantity(10d, USCustomary.POUND);
		Quantity<Mass> result = kg.add(p);
		assertEquals(54.5359237d, result.getValue());
		assertEquals(Units.KILOGRAM, result.getUnit());
	}

	@Test
	public void testSubtract() {
		Quantity<Length> km = Quantities.getQuantity(2000, Units.METRE);
		Quantity<Length> mile = Quantities.getQuantity(1, USCustomary.MILE);
		Quantity<Length> result = km.subtract(mile);
		assertEquals(BigDecimal.valueOf(390.656), result.getValue());
		assertEquals(Units.METRE, result.getUnit());
	}
	
	@Test
	public void testSubtract2() {
		Quantity<Mass> kg = Quantities.getQuantity(50d, Units.KILOGRAM);
		Quantity<Mass> p = Quantities.getQuantity(10d, USCustomary.POUND);
		Quantity<Mass> result = kg.subtract(p);
		assertEquals(45.4640763d, result.getValue());
		assertEquals(Units.KILOGRAM, result.getUnit());
	}
}
