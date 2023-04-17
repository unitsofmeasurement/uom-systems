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
package systems.uom.ucum.format;

import static org.junit.jupiter.api.Assertions.*;
import static systems.uom.ucum.UCUM.*;
import static systems.uom.ucum.format.UCUMFormat.Variant.CASE_INSENSITIVE;
import static systems.uom.ucum.format.UCUMFormat.Variant.CASE_SENSITIVE;
import static systems.uom.ucum.format.UCUMFormat.Variant.PRINT;
import static javax.measure.MetricPrefix.ATTO;
import static javax.measure.MetricPrefix.CENTI;
import static javax.measure.MetricPrefix.DECI;
import static javax.measure.MetricPrefix.DECA;
import static javax.measure.MetricPrefix.DECA;
import static javax.measure.MetricPrefix.EXA;
import static javax.measure.MetricPrefix.FEMTO;
import static javax.measure.MetricPrefix.GIGA;
import static javax.measure.MetricPrefix.HECTO;
import static javax.measure.MetricPrefix.KILO;
import static javax.measure.MetricPrefix.MEGA;
import static javax.measure.MetricPrefix.MICRO;
import static javax.measure.MetricPrefix.MILLI;
import static javax.measure.MetricPrefix.NANO;
import static javax.measure.MetricPrefix.PETA;
import static javax.measure.MetricPrefix.PICO;
import static javax.measure.MetricPrefix.TERA;
import static javax.measure.MetricPrefix.YOCTO;
import static javax.measure.MetricPrefix.YOTTA;
import static javax.measure.MetricPrefix.ZEPTO;
import static javax.measure.MetricPrefix.ZETTA;

import javax.measure.Unit;
import javax.measure.format.UnitFormat;
import javax.measure.quantity.Frequency;

import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:werner@uom.systems">Werner Keil</a>
 *
 */
public class UCUMFormatTable4Test extends UCUMFormatTestBase {

	private static final String PREFIX_PATTERN = "The %s prefix didn't work";

	// TODO try to make most of these more generic, e.g. going over an enum or via parameterized tests
	
	@Test
	public void testFormatUCUMCSWithNegativePrefix() {
		final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);
		Unit<Frequency> hertzSubmultiple;

		hertzSubmultiple = DECI(HERTZ);
		assertEquals("dHz", format.format(hertzSubmultiple), String.format(PREFIX_PATTERN, DECI));

		hertzSubmultiple = CENTI(HERTZ);
		assertEquals("cHz", format.format(hertzSubmultiple));

		hertzSubmultiple = MILLI(HERTZ);
		assertEquals("mHz", format.format(hertzSubmultiple));

		hertzSubmultiple = MICRO(HERTZ);
		assertEquals("uHz", format.format(hertzSubmultiple));

		hertzSubmultiple = NANO(HERTZ);
		assertEquals("nHz", format.format(hertzSubmultiple));

		hertzSubmultiple = PICO(HERTZ);
		assertEquals("pHz", format.format(hertzSubmultiple));

		hertzSubmultiple = FEMTO(HERTZ);
		assertEquals("fHz", format.format(hertzSubmultiple));

		hertzSubmultiple = ATTO(HERTZ);
		assertEquals("aHz", format.format(hertzSubmultiple));

		hertzSubmultiple = ZEPTO(HERTZ);
		assertEquals("zHz", format.format(hertzSubmultiple));

		hertzSubmultiple = YOCTO(HERTZ);
		assertEquals("yHz", format.format(hertzSubmultiple));

		assertEquals("mm/s",
				format.format(MILLI(METER).divide(SECOND)), "The MILLI prefix didn't work with a product unit");
	}

	@Test
	public void testFormatUCUMCSWithPositivePrefix() {
		final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);
		Unit<Frequency> hertzMultiple;

		hertzMultiple = DECA(HERTZ);
		assertEquals("daHz", format.format(hertzMultiple), String.format(PREFIX_PATTERN, DECA));

		hertzMultiple = HECTO(HERTZ);
		assertEquals("hHz", format.format(hertzMultiple));

		hertzMultiple = KILO(HERTZ);
		assertEquals("kHz", format.format(hertzMultiple));

		hertzMultiple = MEGA(HERTZ);
		assertEquals("MHz", format.format(hertzMultiple));

		hertzMultiple = GIGA(HERTZ);
		assertEquals("GHz", format.format(hertzMultiple));

		hertzMultiple = TERA(HERTZ);
		assertEquals("THz", format.format(hertzMultiple));

		hertzMultiple = PETA(HERTZ);
		assertEquals("PHz", format.format(hertzMultiple));

		hertzMultiple = EXA(HERTZ);
		assertEquals("EHz", format.format(hertzMultiple));

		hertzMultiple = ZETTA(HERTZ);
		assertEquals("ZHz", format.format(hertzMultiple));

		hertzMultiple = YOTTA(HERTZ);
		assertEquals("YHz", format.format(hertzMultiple));

		assertEquals("km/s",
				format.format(KILO(METER).divide(SECOND)), "The KILO prefix didn't work with a product unit");
	}

	@Test
	public void testFormatUCUMCIWithNegativePrefix() {
		final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);
		Unit<Frequency> hertzSubmultiple;

		// TODO try to make this more generic, e.g. going over an enum.
		
		hertzSubmultiple = DECI(HERTZ);
		assertEquals("DHZ", format.format(hertzSubmultiple), String.format(PREFIX_PATTERN, DECI));

		hertzSubmultiple = CENTI(HERTZ);
		assertEquals("CHZ", format.format(hertzSubmultiple));

		hertzSubmultiple = MILLI(HERTZ);
		assertEquals("MHZ", format.format(hertzSubmultiple));

		hertzSubmultiple = MICRO(HERTZ);
		assertEquals("UHZ", format.format(hertzSubmultiple));

		hertzSubmultiple = NANO(HERTZ);
		assertEquals("NHZ", format.format(hertzSubmultiple));

		hertzSubmultiple = PICO(HERTZ);
		assertEquals("PHZ", format.format(hertzSubmultiple));

		hertzSubmultiple = FEMTO(HERTZ);
		assertEquals("FHZ", format.format(hertzSubmultiple));

		hertzSubmultiple = ATTO(HERTZ);
		assertEquals("AHZ", format.format(hertzSubmultiple));

		hertzSubmultiple = ZEPTO(HERTZ);
		assertEquals("ZOHZ", format.format(hertzSubmultiple));

		hertzSubmultiple = YOCTO(HERTZ);
		assertEquals("YOHZ", format.format(hertzSubmultiple));

		assertEquals("MM/S",
				format.format(MILLI(METER).divide(SECOND)), "The MILLI prefix didn't work with a product unit");
	}

	@Test
	public void testFormatUCUMCIWithPositivePrefix() {
		final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);
		Unit<Frequency> hertzMultiple;

		hertzMultiple = DECA(HERTZ);
		assertEquals("DAHZ", format.format(hertzMultiple), String.format(PREFIX_PATTERN, DECA));

		hertzMultiple = HECTO(HERTZ);
		assertEquals("HHZ", format.format(hertzMultiple));

		hertzMultiple = KILO(HERTZ);
		assertEquals("KHZ", format.format(hertzMultiple));

		hertzMultiple = MEGA(HERTZ);
		assertEquals("MAHZ", format.format(hertzMultiple));

		hertzMultiple = GIGA(HERTZ);
		assertEquals("GAHZ", format.format(hertzMultiple));

		hertzMultiple = TERA(HERTZ);
		assertEquals("TRHZ", format.format(hertzMultiple));

		hertzMultiple = PETA(HERTZ);
		assertEquals("PTHZ", format.format(hertzMultiple));

		hertzMultiple = EXA(HERTZ);
		assertEquals("EXHZ", format.format(hertzMultiple));

		hertzMultiple = ZETTA(HERTZ);
		assertEquals("ZAHZ", format.format(hertzMultiple));

		hertzMultiple = YOTTA(HERTZ);
		assertEquals("YAHZ", format.format(hertzMultiple));

		assertEquals("KM/S",
				format.format(KILO(METER).divide(SECOND)), "The KILO prefix didn't work with a product unit");
	}

	@Test
	public void testParseUCUMCSWithNegativePrefix() {
		final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

		assertEquals(DECI(HERTZ), format.parse("dHz"), String.format(PREFIX_PATTERN, DECI));
		assertEquals(CENTI(HERTZ), format.parse("cHz"), String.format(PREFIX_PATTERN, CENTI));
		assertEquals(MILLI(HERTZ), format.parse("mHz"), String.format(PREFIX_PATTERN, MILLI));
		assertEquals(MICRO(HERTZ), format.parse("uHz"), String.format(PREFIX_PATTERN, MICRO));
		assertEquals(NANO(HERTZ), format.parse("nHz"), String.format(PREFIX_PATTERN, NANO));
		assertEquals(PICO(HERTZ), format.parse("pHz"), String.format(PREFIX_PATTERN, PICO));
		assertEquals(FEMTO(HERTZ), format.parse("fHz"), String.format(PREFIX_PATTERN, FEMTO));
		assertEquals(ATTO(HERTZ), format.parse("aHz"), String.format(PREFIX_PATTERN, ATTO));
		assertEquals(ZEPTO(HERTZ), format.parse("zHz"), String.format(PREFIX_PATTERN, ZEPTO));
		assertEquals(YOCTO(HERTZ), format.parse("yHz"), String.format(PREFIX_PATTERN, YOCTO));

		assertEquals(MILLI(METER).divide(SECOND), format.parse("mm/s"),
				"The MILLI prefix didn't work with a product unit");
	}

	@Test
	public void testParseUCUMCSWithPositivePrefix() {
		final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

		assertEquals(DECA(HERTZ), format.parse("daHz"), String.format(PREFIX_PATTERN, DECA));

		assertEquals(HECTO(HERTZ), format.parse("hHz"), String.format(PREFIX_PATTERN, HECTO));

		assertEquals(KILO(HERTZ), format.parse("kHz"), String.format(PREFIX_PATTERN, KILO));

		assertEquals( MEGA(HERTZ), format.parse("MHz"), String.format(PREFIX_PATTERN, MEGA));

		assertEquals(GIGA(HERTZ), format.parse("GHz"), String.format(PREFIX_PATTERN, GIGA));

		assertEquals(TERA(HERTZ), format.parse("THz"), String.format(PREFIX_PATTERN, TERA));

		assertEquals(PETA(HERTZ), format.parse("PHz"), String.format(PREFIX_PATTERN, PETA));

		assertEquals(EXA(HERTZ), format.parse("EHz"), String.format(PREFIX_PATTERN, EXA));

		assertEquals(ZETTA(HERTZ), format.parse("ZHz"), String.format(PREFIX_PATTERN, ZETTA));

		assertEquals(YOTTA(HERTZ), format.parse("YHz"), String.format(PREFIX_PATTERN, YOTTA));

		assertEquals(KILO(METER).divide(SECOND), format.parse("km/s"), "The KILO prefix didn't work with a product unit");
	}

	@Test
	public void testParseUCUMCIWithNegativePrefix() {
		final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);

		assertEquals(DECI(HERTZ), format.parse("DHz"), "The DECI prefix didn't work");

		assertEquals(CENTI(HERTZ), format.parse("CHz"), "The CENTI prefix didn't work");

		assertEquals(MILLI(HERTZ), format.parse("MHz"), "The MILLI prefix didn't work");

		assertEquals(MICRO(HERTZ), format.parse("UHz"), "The MICRO prefix didn't work");

		assertEquals(NANO(HERTZ), format.parse("NHz"), "The NANO prefix didn't work");

		assertEquals(PICO(HERTZ), format.parse("PHz"), "The PICO prefix didn't work");

		assertEquals(FEMTO(HERTZ), format.parse("FHz"), "The FEMTO prefix didn't work");

		assertEquals(ATTO(HERTZ), format.parse("AHz"), "The ATTO prefix didn't work");

		assertEquals(ZEPTO(HERTZ), format.parse("ZOHz"), "The ZEPTO prefix didn't work");

		assertEquals(YOCTO(HERTZ), format.parse("YOHz"), "The YOCTO prefix didn't work");

		assertEquals(MILLI(METER).divide(SECOND), format.parse("MM/S"),
				"The MILLI prefix didn't work with a product unit");
	}

	@Test
	public void testParseUCUMCIWithPositivePrefix() {
		final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);

		assertEquals(DECA(HERTZ), format.parse("DAHz"), String.format(PREFIX_PATTERN, DECA));
		assertEquals(HECTO(HERTZ), format.parse("HHz"), String.format(PREFIX_PATTERN, HECTO));
		assertEquals(KILO(HERTZ), format.parse("KHz"), String.format(PREFIX_PATTERN, KILO));
		assertEquals(MEGA(HERTZ), format.parse("MAHz"), String.format(PREFIX_PATTERN, MEGA));
		assertEquals(GIGA(HERTZ), format.parse("GAHz"), String.format(PREFIX_PATTERN, GIGA));
		assertEquals(TERA(HERTZ), format.parse("TRHz"), String.format(PREFIX_PATTERN, TERA));
		assertEquals( PETA(HERTZ), format.parse("PTHz"), String.format(PREFIX_PATTERN, PETA));
		assertEquals(EXA(HERTZ), format.parse("EXHz"), String.format(PREFIX_PATTERN, EXA));
		assertEquals(ZETTA(HERTZ), format.parse("ZAHz"), String.format(PREFIX_PATTERN, ZETTA));
		assertEquals(YOTTA(HERTZ), format.parse("YAHz"), String.format(PREFIX_PATTERN, YOTTA));
		assertEquals(KILO(METER).divide(SECOND), format.parse("KM/S"), "The KILO prefix didn't work with a product unit");
	}

	@Test
	public void testFormatUCUMPrintWithNegativePrefix() {
		final UnitFormat format = UCUMFormat.getInstance(PRINT);
		Unit<Frequency> hertzSubmultiple;

		hertzSubmultiple = DECI(HERTZ);
		assertEquals("dHz", format.format(hertzSubmultiple), "The DECI prefix didn't work");

		hertzSubmultiple = CENTI(HERTZ);
		assertEquals("cHz", format.format(hertzSubmultiple), String.format(PREFIX_PATTERN, CENTI));

		hertzSubmultiple = MILLI(HERTZ);
		assertEquals("mHz", format.format(hertzSubmultiple), String.format(PREFIX_PATTERN, MILLI));

		hertzSubmultiple = MICRO(HERTZ);
		assertEquals("µHz", format.format(hertzSubmultiple));

		hertzSubmultiple = NANO(HERTZ);
		assertEquals("nHz", format.format(hertzSubmultiple));

		hertzSubmultiple = PICO(HERTZ);
		assertEquals("pHz", format.format(hertzSubmultiple));

		hertzSubmultiple = FEMTO(HERTZ);
		assertEquals("fHz", format.format(hertzSubmultiple));

		hertzSubmultiple = ATTO(HERTZ);
		assertEquals("aHz", format.format(hertzSubmultiple));

		hertzSubmultiple = ZEPTO(HERTZ);
		assertEquals("zHz", format.format(hertzSubmultiple));

		hertzSubmultiple = YOCTO(HERTZ);
		assertEquals("yHz", format.format(hertzSubmultiple));

		assertEquals("mm/s",
				format.format(MILLI(METER).divide(SECOND)), "The MILLI prefix didn't work with a product unit");
	}

	@Test
	public void testFormatUCUMPrintWithPositivePrefix() {
		final UnitFormat format = UCUMFormat.getInstance(PRINT);
		Unit<Frequency> hertzMultiple;

		hertzMultiple = DECA(HERTZ);
		assertEquals("daHz", format.format(hertzMultiple), String.format(PREFIX_PATTERN, DECA));

		hertzMultiple = HECTO(HERTZ);
		assertEquals("hHz", format.format(hertzMultiple));

		hertzMultiple = KILO(HERTZ);
		assertEquals("kHz", format.format(hertzMultiple));

		hertzMultiple = MEGA(HERTZ);
		assertEquals("MHz", format.format(hertzMultiple));

		hertzMultiple = GIGA(HERTZ);
		assertEquals("GHz", format.format(hertzMultiple));

		hertzMultiple = TERA(HERTZ);
		assertEquals("THz", format.format(hertzMultiple));

		hertzMultiple = PETA(HERTZ);
		assertEquals("PHz", format.format(hertzMultiple));

		hertzMultiple = EXA(HERTZ);
		assertEquals("EHz", format.format(hertzMultiple));

		hertzMultiple = ZETTA(HERTZ);
		assertEquals("ZHz", format.format(hertzMultiple));

		hertzMultiple = YOTTA(HERTZ);
		assertEquals("YHz", format.format(hertzMultiple), String.format(PREFIX_PATTERN, YOTTA));

		assertEquals("km/s", format.format(KILO(METER).divide(SECOND)), "The KILO prefix didn't work with a product unit");
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testNewton() {
		final UnitFormat format = UCUMFormat.getInstance(PRINT);
		@SuppressWarnings("rawtypes")
		Unit newton = KILO(GRAM).multiply(METER).divide(SECOND).divide(SECOND);
		assertTrue(newton.isEquivalentTo(NEWTON));
		assertEquals("kg.m/s2", format.format(newton));
	}

	
	@Test
	public void testBaud() {
		final UnitFormat format = UCUMFormat.getInstance(PRINT);		
		assertEquals("Bd", format.format(BAUD));
		final UnitFormat formatCS = UCUMFormat.getInstance(CASE_SENSITIVE);
		assertEquals(BAUD, formatCS.parse("Bd"));
	}
}
