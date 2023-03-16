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

import java.io.IOException;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.format.MeasurementParseException;
import javax.measure.quantity.Area;
import javax.measure.quantity.Length;
import javax.measure.quantity.Speed;
import javax.measure.spi.ServiceProvider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static tech.units.indriya.unit.Units.KILOGRAM;
import static tech.units.indriya.unit.Units.METRE;
import static tech.units.indriya.unit.Units.MINUTE;
import static tech.units.indriya.unit.Units.SECOND;

import tech.units.indriya.format.SimpleUnitFormat;
import tech.units.indriya.unit.Units;

/**
 * @author <a href="mailto:werner@uom.systems">Werner Keil</a>
 *
 */
public class UnitFormatTest {
	private Quantity<Length> sut;

	private SimpleUnitFormat format2;
	
	@BeforeEach
	public void init() {
		sut = ServiceProvider.current().getQuantityFactory(Length.class).create(10,
				METRE);
		
//		format = EBNFUnitFormat.getInstance();
		format2 = SimpleUnitFormat.getInstance();
		
//		format.label(SI.BEL, "B");
		format2.label(CLDR.CARAT, "ct");
		format2.label(CLDR.ACRE, "ac");
		format2.label(CLDR.TONNE, "t");
	}

	@Test
	public void testFormat2() {
		Unit<Speed> kph = Units.KILOMETRE_PER_HOUR;
		assertEquals("km/h", kph.toString());
	}
	
	@Test
	public void testFormat4() {
		Unit<Speed> kph = Units.KILOMETRE_PER_HOUR;
		assertEquals("km/h", kph.toString());
	}
	
	@Test
	public void testFormat6() {
		Unit<Area> b = CLDR.ACRE;
		assertEquals("ac", b.toString());
	}
	
	@Test
	public void testParseSimple() {
		try {
			Unit<?> u = format2.parse("s");
			assertEquals("s", u.getSymbol());
			assertEquals(SECOND, u);
		} catch (MeasurementParseException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testFormatFromQuantity() {
		final Appendable a = new StringBuilder();
		try {
			format2.format(METRE, a);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		assertEquals(METRE, sut.getUnit());
		assertEquals("m", a.toString());

		final Appendable a2 = new StringBuilder();
		@SuppressWarnings("unchecked")
		Unit<Speed> v = (Unit<Speed>) sut.getUnit().divide(SECOND);
		try {
			format2.format(v, a2);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		assertEquals("m/s", a2.toString());
	}

	@Test
	public void testParseSimple1() {
		try {
			Unit<?> u = format2.parse("min");
			// assertEquals("min", u.getSymbol());
			assertEquals(MINUTE, u);
		} catch (MeasurementParseException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testParseSimple2() {
		try {
			Unit<?> u = format2.parse("m");
			assertEquals("m", u.getSymbol());
			assertEquals(METRE, u);
		} catch (MeasurementParseException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testParseSimple3() {
		try {
			Unit<?> u = format2.parse("kg");
			assertEquals("kg", u.getSymbol());
			assertEquals(KILOGRAM, u);
		} catch (MeasurementParseException e) {
			fail(e.getMessage());
		}
	}
}
