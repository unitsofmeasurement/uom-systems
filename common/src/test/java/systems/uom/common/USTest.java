/*
 * Units of Measurement Systems
 * Copyright (c) 2005-2024, Jean-Marie Dautelle, Werner Keil and others.
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
package systems.uom.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static systems.uom.common.USCustomary.METER;
import static systems.uom.common.USCustomary.POUND; 
import static systems.uom.common.USCustomary.HORSEPOWER;
import static systems.uom.common.USCustomary.ELECTRICAL_HORSEPOWER;
import static javax.measure.BinaryPrefix.*;
import static javax.measure.MetricPrefix.*;
import static tech.units.indriya.unit.Units.METRE;

import javax.measure.Unit;

import static tech.units.indriya.unit.Units.GRAM;
import static tech.units.indriya.unit.Units.KILOGRAM;

import org.junit.jupiter.api.Test;

public class USTest {
	@Test
	public void testPoundPrefixes() {	
		assertEquals("lb", POUND.toString());
		assertEquals("klb", KILO(POUND).toString());
		assertEquals("Kilb", KIBI(POUND).toString());
	}
	
	@Test
	public void testHorsepower() {	
		assertEquals("hp", HORSEPOWER.toString());
	}
	
	@Test
	public void testElectricalHorsepower() {	
		assertEquals("hp(E)", ELECTRICAL_HORSEPOWER.toString());
	}
	
	
	@Test
	public void testMetreMeter() {	
		assertEquals(METRE, METER);
	}
	
	@Test
	public void testGetSymbol() {
		// TODO see https://github.com/unitsofmeasurement/uom-se/issues/54 / https://java.net/jira/browse/UNITSOFMEASUREMENT-109
		assertEquals("kg", KILOGRAM.getSymbol());
		assertNull(KILO(GRAM).getSymbol());
		assertEquals("g", GRAM.toString());
		assertNull(GRAM.getSymbol());
		assertEquals("lb", POUND.toString());
		assertNull(POUND.getSymbol());
	}
	
	@Test
	public void testName() {
		USCustomary.getInstance().getUnits().forEach((Unit<?> unit) -> assertNotNull(unit.getName(), 
				String.format("%s has no name", unit)));
	}
	
	@Test
	public void testToString() {
		USCustomary.getInstance().getUnits().forEach((Unit<?> unit) -> assertNotNull(unit.toString()));
	}
}
