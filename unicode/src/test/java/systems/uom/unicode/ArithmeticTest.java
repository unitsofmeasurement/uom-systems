/*
 *  Unit-API - Units of Measurement API for Java
 *  Copyright (c) 2005-2016, Jean-Marie Dautelle, Werner Keil, V2COM.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of JSR-363 nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
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
package systems.uom.unicode;

import static org.junit.Assert.*;
import javax.measure.Quantity;
import javax.measure.quantity.Mass;

import org.junit.Test;

import tec.units.ri.quantity.Quantities;
import tec.units.ri.unit.Units;

public class ArithmeticTest {
	
	@Test
	public void testAdd() {
		Quantity<Mass> kg = Quantities.getQuantity(5d, Units.KILOGRAM);
		Quantity<Mass> p = Quantities.getQuantity(10d, CLDR.CARAT);
		Quantity<Mass> result = kg.add(p);
		assertEquals(5.002d, result.getValue());
		assertEquals(Units.KILOGRAM, result.getUnit());
	}

	@Test
	public void testSubtract2() {
		Quantity<Mass> kg = Quantities.getQuantity(5d, Units.KILOGRAM);
		Quantity<Mass> p = Quantities.getQuantity(10d, CLDR.CARAT);
		Quantity<Mass> result = kg.subtract(p);
		assertEquals(4.998d, result.getValue());
		assertEquals(Units.KILOGRAM, result.getUnit());
	}
}
