/*
 * Units of Measurement Systems
 * Copyright (c) 2005-2023, Jean-Marie Dautelle, Werner Keil and others.
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
 * 3. Neither the name of JSR-385, Units of Measurement nor the names of their contributors may be used to
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

import static javax.measure.BinaryPrefix.*;
import static javax.measure.MetricPrefix.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static tech.units.indriya.unit.Units.KILOGRAM;
import static tech.units.indriya.unit.Units.METRE;
import static systems.uom.unicode.CLDR.*;

import java.math.BigDecimal;

import javax.measure.Quantity;
import javax.measure.quantity.Speed;

import org.junit.jupiter.api.Test;

import tech.units.indriya.AbstractUnit;
import tech.units.indriya.function.RationalNumber;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

public class CLDRTest {
	@Test
	public void testToString() {
		assertEquals("kg", KILOGRAM.toString());
		assertEquals("ct", CARAT.toString());
		assertEquals("in-hg", INCH_HG.toString());
		assertEquals("dct", DECI(CARAT).toString());
		assertEquals("st", STONE.toString());
		assertEquals("Kist", KIBI(STONE).toString());		
	}
	
	@Test
	public void testGetSymbol() {
		// TODO see https://github.com/unitsofmeasurement/uom-se/issues/54 / https://java.net/jira/browse/UNITSOFMEASUREMENT-109
		assertEquals("kg", KILOGRAM.getSymbol());
		assertNull(KILO(GRAM).getSymbol());
		assertNull(GRAM.getSymbol());
	}
	
	@Test
	public void testGetName() {
		assertEquals("Kilometer", KILOMETER.getName());
		assertEquals("Gram", GRAM.getName());
	}
	
	@Test
	public void testConvert() {
		Quantity<Speed> kph = Quantities.getQuantity(30, Units.KILOMETRE_PER_HOUR);
		Quantity<Speed> knots = kph.to(CLDR.KNOT);
		assertEquals(RationalNumber.of(new BigDecimal("16.19870410367170626349892008639309")), knots.getValue());
	}
	
	@Test
	public void testParse() {
		assertEquals(KILOGRAM, AbstractUnit.parse("kg"));
		assertEquals(CARAT, AbstractUnit.parse("ct"));
		assertEquals(DECI(METRE), AbstractUnit.parse("dm"));		
	}
}
