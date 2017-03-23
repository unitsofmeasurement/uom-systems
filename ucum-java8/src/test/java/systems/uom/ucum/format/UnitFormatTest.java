/*
 * Units of Measurement Systems for Java
 * Copyright (c) 2005-2017, Jean-Marie Dautelle, Werner Keil, V2COM.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of JSR-363, Units of Measurement nor the names of their contributors may be used to endorse or promote products derived from this software without specific prior written permission.
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

import static org.junit.Assert.*;
import static systems.uom.ucum.UCUM.*;
import static systems.uom.ucum.format.UCUMFormat.Variant.*;
import static tec.uom.se.unit.MetricPrefix.*;

import javax.measure.*;
import javax.measure.format.*;
import javax.measure.quantity.*;

import systems.uom.ucum.format.UCUMFormat;
import systems.uom.ucum.internal.format.TokenException;
import tec.uom.se.format.LocalUnitFormat;
import tec.uom.se.quantity.Quantities;
import tec.uom.se.unit.ProductUnit;

import org.junit.*;

/**
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 *
 */
public class UnitFormatTest {
	private Quantity<Length> sut;

	@Before
	public void init() {
		//sut = QuantityFactoryProvider.getQuantityFactory(Length.class).create(10, METER);
		sut = Quantities.getQuantity(10, METER);
	}

    @Ignore
    @Test
    public void testFormatUCUMCSWithNegativePrefix() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);
        Unit<Frequency> hertzSubmultiple;

        hertzSubmultiple = DECI(HERTZ);
        assertEquals("The DECI prefix didn't work", "dHz", format.format(hertzSubmultiple));

        hertzSubmultiple = CENTI(HERTZ);
        assertEquals("The CENTI prefix didn't work", "cHz", format.format(hertzSubmultiple));

        hertzSubmultiple = MILLI(HERTZ);
        assertEquals("The MILLI prefix didn't work", "mHz", format.format(hertzSubmultiple));

        hertzSubmultiple = MICRO(HERTZ);
        assertEquals("The MICRO prefix didn't work", "µHz", format.format(hertzSubmultiple));

        hertzSubmultiple = NANO(HERTZ);
        assertEquals("The NANO prefix didn't work", "nHz", format.format(hertzSubmultiple));

        hertzSubmultiple = PICO(HERTZ);
        assertEquals("The PICO prefix didn't work", "pHz", format.format(hertzSubmultiple));

        hertzSubmultiple = FEMTO(HERTZ);
        assertEquals("The FEMTO prefix didn't work", "fHz", format.format(hertzSubmultiple));

        hertzSubmultiple = ATTO(HERTZ);
        assertEquals("The ATTO prefix didn't work", "aHz", format.format(hertzSubmultiple));

        hertzSubmultiple = ZEPTO(HERTZ);
        assertEquals("The ZEPTO prefix didn't work", "zHz", format.format(hertzSubmultiple));

        hertzSubmultiple = YOCTO(HERTZ);
        assertEquals("The YOCTO prefix didn't work", "yHz", format.format(hertzSubmultiple));
    }

    @Ignore
    @Test
    public void testFormatUCUMCSWithPositivePrefix() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);
        Unit<Frequency> hertzMultiple;

        hertzMultiple = DEKA(HERTZ);
        assertEquals("The DEKA prefix didn't work", "daHz", format.format(hertzMultiple));

        hertzMultiple = HECTO(HERTZ);
        assertEquals("The HECTO prefix didn't work", "hHz", format.format(hertzMultiple));

        hertzMultiple = KILO(HERTZ);
        assertEquals("The KILO prefix didn't work", "kHz", format.format(hertzMultiple));

        hertzMultiple = MEGA(HERTZ);
        assertEquals("The MEGA prefix didn't work", "MHz", format.format(hertzMultiple));

        hertzMultiple = GIGA(HERTZ);
        assertEquals("The GIGA prefix didn't work", "GHz", format.format(hertzMultiple));

        hertzMultiple = TERA(HERTZ);
        assertEquals("The TERA prefix didn't work", "THz", format.format(hertzMultiple));

        hertzMultiple = PETA(HERTZ);
        assertEquals("The PETA prefix didn't work", "PHz", format.format(hertzMultiple));

        hertzMultiple = EXA(HERTZ);
        assertEquals("The EXA prefix didn't work", "EHz", format.format(hertzMultiple));

        hertzMultiple = ZETTA(HERTZ);
        assertEquals("The ZETTA prefix didn't work", "ZHz", format.format(hertzMultiple));

        hertzMultiple = YOTTA(HERTZ);
        assertEquals("The YOTTA prefix didn't work", "YHz", format.format(hertzMultiple));
    }

    @Ignore
    @Test
    public void testFormatUCUMCIWithNegativePrefix() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);
        Unit<Frequency> hertzSubmultiple;

        hertzSubmultiple = DECI(HERTZ);
        assertEquals("The DECI prefix didn't work", "DHz", format.format(hertzSubmultiple));

        hertzSubmultiple = CENTI(HERTZ);
        assertEquals("The CENTI prefix didn't work", "CHz", format.format(hertzSubmultiple));

        hertzSubmultiple = MILLI(HERTZ);
        assertEquals("The MILLI prefix didn't work", "MHz", format.format(hertzSubmultiple));

        hertzSubmultiple = MICRO(HERTZ);
        assertEquals("The MICRO prefix didn't work", "UHz", format.format(hertzSubmultiple));

        hertzSubmultiple = NANO(HERTZ);
        assertEquals("The NANO prefix didn't work", "NHz", format.format(hertzSubmultiple));

        hertzSubmultiple = PICO(HERTZ);
        assertEquals("The PICO prefix didn't work", "PHz", format.format(hertzSubmultiple));

        hertzSubmultiple = FEMTO(HERTZ);
        assertEquals("The FEMTO prefix didn't work", "FHz", format.format(hertzSubmultiple));

        hertzSubmultiple = ATTO(HERTZ);
        assertEquals("The ATTO prefix didn't work", "AHz", format.format(hertzSubmultiple));

        hertzSubmultiple = ZEPTO(HERTZ);
        assertEquals("The ZEPTO prefix didn't work", "ZOHz", format.format(hertzSubmultiple));

        hertzSubmultiple = YOCTO(HERTZ);
        assertEquals("The YOCTO prefix didn't work", "YOHz", format.format(hertzSubmultiple));
    }

    @Ignore
    @Test
    public void testFormatUCUMCIWithPositivePrefix() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);
        Unit<Frequency> hertzMultiple;

        hertzMultiple = DEKA(HERTZ);
        assertEquals("The DEKA prefix didn't work", "DAHz", format.format(hertzMultiple));

        hertzMultiple = HECTO(HERTZ);
        assertEquals("The HECTO prefix didn't work", "HHz", format.format(hertzMultiple));

        hertzMultiple = KILO(HERTZ);
        assertEquals("The KILO prefix didn't work", "KHz", format.format(hertzMultiple));

        hertzMultiple = MEGA(HERTZ);
        assertEquals("The MEGA prefix didn't work", "MAHz", format.format(hertzMultiple));

        hertzMultiple = GIGA(HERTZ);
        assertEquals("The GIGA prefix didn't work", "GAHz", format.format(hertzMultiple));

        hertzMultiple = TERA(HERTZ);
        assertEquals("The TERA prefix didn't work", "TRHz", format.format(hertzMultiple));

        hertzMultiple = PETA(HERTZ);
        assertEquals("The PETA prefix didn't work", "PTHz", format.format(hertzMultiple));

        hertzMultiple = EXA(HERTZ);
        assertEquals("The EXA prefix didn't work", "EXHz", format.format(hertzMultiple));

        hertzMultiple = ZETTA(HERTZ);
        assertEquals("The ZETTA prefix didn't work", "ZAHz", format.format(hertzMultiple));

        hertzMultiple = YOTTA(HERTZ);
        assertEquals("The YOTTA prefix didn't work", "YAHz", format.format(hertzMultiple));
    }

	@Test
	public void testFormatLocal() {
		final UnitFormat format = LocalUnitFormat.getInstance();

		assertEquals(METER, sut.getUnit());
		assertEquals("m", format.format(METER));

		Unit<Speed> v = new ProductUnit<Speed>(METER.divide(SECOND));

		assertEquals("m/s", format.format(v));
	}

	@Test
	public void testFormatUCUMPrint() {
		final UnitFormat format = UCUMFormat.getInstance(PRINT);

		assertEquals(METER, sut.getUnit());
		assertEquals("m", format.format(METER));

		Unit<Speed> v = new ProductUnit<Speed>(sut.getUnit().divide(SECOND));

		assertEquals("m/s", format.format(v));
	}

	@Test
	public void testFormatUCUMCS() {
		final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

		assertEquals(METER, sut.getUnit());
		assertEquals("m", format.format(METER));

		Unit<Speed> v = new ProductUnit<Speed>(METER.divide(SECOND));

		assertEquals("m/s", format.format(v));
	}

	@Test
	public void testFormatUCUMCI() {
		final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);

		assertEquals(METER, sut.getUnit());
		assertEquals("M", format.format(METER));
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testParseLocal() {
		final UnitFormat format = LocalUnitFormat.getInstance();

		assertEquals("min", format.parse("min").getSymbol());
	}

	@Test
	public void testParseUCUMCS() {
		final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

		assertEquals(MINUTE, format.parse("min"));
	}

    @Test(expected=TokenException.class)
    public void testParseUCUMCSError() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

        assertEquals(MINUTE, format.parse("MIN"));
    }

    @Test
    public void testParseUCUMCSLowercaseLiter() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

        assertEquals(LITER, format.parse("l"));
    }

    @Test
    public void testParseUCUMCSUppercaseLiter() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

        assertEquals(LITER, format.parse("L"));
    }

	@Test
	public void testParseUCUMCI() {
		final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);

		assertEquals(METER, format.parse("M"));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testParseUCUMPrint() {
		final UnitFormat format = UCUMFormat.getInstance(PRINT);

		assertEquals(KILO(GRAM), format.parse("kg"));
	}
}
