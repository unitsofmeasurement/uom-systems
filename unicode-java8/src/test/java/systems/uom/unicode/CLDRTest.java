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
package systems.uom.unicode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static tec.uom.se.unit.MetricPrefix.*;
import static tec.uom.se.unit.Units.KILOGRAM;
import static systems.uom.unicode.CLDR.*;

import javax.measure.Quantity;
import javax.measure.quantity.Speed;

import org.junit.Test;

import tec.uom.se.quantity.Quantities;
import tec.uom.se.unit.Units;

public class CLDRTest {
	@Test
	public void testFormat() {	
		assertEquals("kg", KILOGRAM.toString());
		assertEquals("ct", CARAT.toString());
	}
	
	@Test
	public void testGetSymbol() {
		// TODO see https://github.com/unitsofmeasurement/uom-se/issues/54 / https://java.net/jira/browse/UNITSOFMEASUREMENT-109
		assertEquals("kg", KILOGRAM.getSymbol());
		assertNull(KILO(GRAM).getSymbol());
//		assertNull(GRAM.getSymbol()); TODO fix this in SE port, too
		assertEquals("g", GRAM.toString());
	}
	
	@Test
	public void testConvert() {
		Quantity<Speed> kph = Quantities.getQuantity(30, Units.KILOMETRE_PER_HOUR);
		Quantity<Speed> knots = kph.to(CLDR.KNOT);
		assertEquals(16.19871706263499, knots.getValue());
	}
}
