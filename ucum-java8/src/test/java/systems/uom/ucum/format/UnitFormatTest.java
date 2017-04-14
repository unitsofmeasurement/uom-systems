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

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.measure.*;
import javax.measure.format.*;
import javax.measure.quantity.*;

import systems.uom.ucum.UCUM;
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
    private static final Level LOG_LEVEL = Level.INFO;
    private static final Logger LOGGER = Logger.getLogger(UnitFormatTest.class.getName());

    private Quantity<Length> sut;

    @Before
    public void init() {
	// sut =
	// QuantityFactoryProvider.getQuantityFactory(Length.class).create(10,
	// METER);
	sut = Quantities.getQuantity(10, METER);
    }

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
	assertEquals("The MICRO prefix didn't work", "uHz", format.format(hertzSubmultiple));

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

	assertEquals("The MILLI prefix didn't work with a product unit", "mm/s",
		format.format(MILLI(METER).divide(SECOND)));
    }

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

	assertEquals("The KILO prefix didn't work with a product unit", "km/s",
		format.format(KILO(METER).divide(SECOND)));
    }

    @Test
    public void testFormatUCUMCIWithNegativePrefix() {
	final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);
	Unit<Frequency> hertzSubmultiple;

	hertzSubmultiple = DECI(HERTZ);
	assertEquals("The DECI prefix didn't work", "DHZ", format.format(hertzSubmultiple));

	hertzSubmultiple = CENTI(HERTZ);
	assertEquals("The CENTI prefix didn't work", "CHZ", format.format(hertzSubmultiple));

	hertzSubmultiple = MILLI(HERTZ);
	assertEquals("The MILLI prefix didn't work", "MHZ", format.format(hertzSubmultiple));

	hertzSubmultiple = MICRO(HERTZ);
	assertEquals("The MICRO prefix didn't work", "UHZ", format.format(hertzSubmultiple));

	hertzSubmultiple = NANO(HERTZ);
	assertEquals("The NANO prefix didn't work", "NHZ", format.format(hertzSubmultiple));

	hertzSubmultiple = PICO(HERTZ);
	assertEquals("The PICO prefix didn't work", "PHZ", format.format(hertzSubmultiple));

	hertzSubmultiple = FEMTO(HERTZ);
	assertEquals("The FEMTO prefix didn't work", "FHZ", format.format(hertzSubmultiple));

	hertzSubmultiple = ATTO(HERTZ);
	assertEquals("The ATTO prefix didn't work", "AHZ", format.format(hertzSubmultiple));

	hertzSubmultiple = ZEPTO(HERTZ);
	assertEquals("The ZEPTO prefix didn't work", "ZOHZ", format.format(hertzSubmultiple));

	hertzSubmultiple = YOCTO(HERTZ);
	assertEquals("The YOCTO prefix didn't work", "YOHZ", format.format(hertzSubmultiple));

	assertEquals("The MILLI prefix didn't work with a product unit", "MM/S",
		format.format(MILLI(METER).divide(SECOND)));
    }

    @Test
    public void testFormatUCUMCIWithPositivePrefix() {
	final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);
	Unit<Frequency> hertzMultiple;

	hertzMultiple = DEKA(HERTZ);
	assertEquals("The DEKA prefix didn't work", "DAHZ", format.format(hertzMultiple));

	hertzMultiple = HECTO(HERTZ);
	assertEquals("The HECTO prefix didn't work", "HHZ", format.format(hertzMultiple));

	hertzMultiple = KILO(HERTZ);
	assertEquals("The KILO prefix didn't work", "KHZ", format.format(hertzMultiple));

	hertzMultiple = MEGA(HERTZ);
	assertEquals("The MEGA prefix didn't work", "MAHZ", format.format(hertzMultiple));

	hertzMultiple = GIGA(HERTZ);
	assertEquals("The GIGA prefix didn't work", "GAHZ", format.format(hertzMultiple));

	hertzMultiple = TERA(HERTZ);
	assertEquals("The TERA prefix didn't work", "TRHZ", format.format(hertzMultiple));

	hertzMultiple = PETA(HERTZ);
	assertEquals("The PETA prefix didn't work", "PTHZ", format.format(hertzMultiple));

	hertzMultiple = EXA(HERTZ);
	assertEquals("The EXA prefix didn't work", "EXHZ", format.format(hertzMultiple));

	hertzMultiple = ZETTA(HERTZ);
	assertEquals("The ZETTA prefix didn't work", "ZAHZ", format.format(hertzMultiple));

	hertzMultiple = YOTTA(HERTZ);
	assertEquals("The YOTTA prefix didn't work", "YAHZ", format.format(hertzMultiple));

	assertEquals("The KILO prefix didn't work with a product unit", "KM/S",
		format.format(KILO(METER).divide(SECOND)));
    }

    @Test
    public void testParseUCUMCSWithNegativePrefix() {
	final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

	assertEquals("The DECI prefix didn't work", DECI(HERTZ), format.parse("dHz"));

	assertEquals("The CENTI prefix didn't work", CENTI(HERTZ), format.parse("cHz"));

	assertEquals("The MILLI prefix didn't work", MILLI(HERTZ), format.parse("mHz"));

	assertEquals("The MICRO prefix didn't work", MICRO(HERTZ), format.parse("uHz"));

	assertEquals("The NANO prefix didn't work", NANO(HERTZ), format.parse("nHz"));

	assertEquals("The PICO prefix didn't work", PICO(HERTZ), format.parse("pHz"));

	assertEquals("The FEMTO prefix didn't work", FEMTO(HERTZ), format.parse("fHz"));

	assertEquals("The ATTO prefix didn't work", ATTO(HERTZ), format.parse("aHz"));

	assertEquals("The ZEPTO prefix didn't work", ZEPTO(HERTZ), format.parse("zHz"));

	assertEquals("The YOCTO prefix didn't work", YOCTO(HERTZ), format.parse("yHz"));

	assertEquals("The MILLI prefix didn't work with a product unit", MILLI(METER).divide(SECOND),
		format.parse("mm/s"));
    }

    @Test
    public void testParseUCUMCSWithPositivePrefix() {
	final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

	assertEquals("The DEKA prefix didn't work", DEKA(HERTZ), format.parse("daHz"));

	assertEquals("The HECTO prefix didn't work", HECTO(HERTZ), format.parse("hHz"));

	assertEquals("The KILO prefix didn't work", KILO(HERTZ), format.parse("kHz"));

	assertEquals("The MEGA prefix didn't work", MEGA(HERTZ), format.parse("MHz"));

	assertEquals("The GIGA prefix didn't work", GIGA(HERTZ), format.parse("GHz"));

	assertEquals("The TERA prefix didn't work", TERA(HERTZ), format.parse("THz"));

	assertEquals("The PETA prefix didn't work", PETA(HERTZ), format.parse("PHz"));

	assertEquals("The EXA prefix didn't work", EXA(HERTZ), format.parse("EHz"));

	assertEquals("The ZETTA prefix didn't work", ZETTA(HERTZ), format.parse("ZHz"));

	assertEquals("The YOTTA prefix didn't work", YOTTA(HERTZ), format.parse("YHz"));

	assertEquals("The KILO prefix didn't work with a product unit", KILO(METER).divide(SECOND),
		format.parse("km/s"));
    }

    @Test
    public void testParseUCUMCIWithNegativePrefix() {
	final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);

	assertEquals("The DECI prefix didn't work", DECI(HERTZ), format.parse("DHz"));

	assertEquals("The CENTI prefix didn't work", CENTI(HERTZ), format.parse("CHz"));

	assertEquals("The MILLI prefix didn't work", MILLI(HERTZ), format.parse("MHz"));

	assertEquals("The MICRO prefix didn't work", MICRO(HERTZ), format.parse("UHz"));

	assertEquals("The NANO prefix didn't work", NANO(HERTZ), format.parse("NHz"));

	assertEquals("The PICO prefix didn't work", PICO(HERTZ), format.parse("PHz"));

	assertEquals("The FEMTO prefix didn't work", FEMTO(HERTZ), format.parse("FHz"));

	assertEquals("The ATTO prefix didn't work", ATTO(HERTZ), format.parse("AHz"));

	assertEquals("The ZEPTO prefix didn't work", ZEPTO(HERTZ), format.parse("ZOHz"));

	assertEquals("The YOCTO prefix didn't work", YOCTO(HERTZ), format.parse("YOHz"));

	assertEquals("The MILLI prefix didn't work with a product unit", MILLI(METER).divide(SECOND),
		format.parse("MM/S"));
    }

    @Test
    public void testParseUCUMCIWithPositivePrefix() {
	final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);

	assertEquals("The DEKA prefix didn't work", DEKA(HERTZ), format.parse("DAHz"));

	assertEquals("The HECTO prefix didn't work", HECTO(HERTZ), format.parse("HHz"));

	assertEquals("The KILO prefix didn't work", KILO(HERTZ), format.parse("KHz"));

	assertEquals("The MEGA prefix didn't work", MEGA(HERTZ), format.parse("MAHz"));

	assertEquals("The GIGA prefix didn't work", GIGA(HERTZ), format.parse("GAHz"));

	assertEquals("The TERA prefix didn't work", TERA(HERTZ), format.parse("TRHz"));

	assertEquals("The PETA prefix didn't work", PETA(HERTZ), format.parse("PTHz"));

	assertEquals("The EXA prefix didn't work", EXA(HERTZ), format.parse("EXHz"));

	assertEquals("The ZETTA prefix didn't work", ZETTA(HERTZ), format.parse("ZAHz"));

	assertEquals("The YOTTA prefix didn't work", YOTTA(HERTZ), format.parse("YAHz"));

	assertEquals("The KILO prefix didn't work with a product unit", KILO(METER).divide(SECOND),
		format.parse("KM/S"));
    }

    @Test
    public void testFormatUCUMPrintWithNegativePrefix() {
	final UnitFormat format = UCUMFormat.getInstance(PRINT);
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

	assertEquals("The MILLI prefix didn't work with a product unit", "mm/s",
		format.format(MILLI(METER).divide(SECOND)));
    }

    @Test
    public void testFormatUCUMPrintWithPositivePrefix() {
	final UnitFormat format = UCUMFormat.getInstance(PRINT);
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

	assertEquals("The KILO prefix didn't work with a product unit", "km/s",
		format.format(KILO(METER).divide(SECOND)));
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
	assertEquals("the formatter isn't working with a unit which there's a specific symbol on the symbolMap for it",
		"m", format.format(METER));

	Unit<Speed> v = new ProductUnit<Speed>(sut.getUnit().divide(SECOND));
	assertEquals("the formatter isn't working with a product unit", "m/s", format.format(v));
    }

    @Test
    public void testFormatUCUMCS() {
	final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

	assertEquals(METER, sut.getUnit());
	assertEquals("the formatter isn't working with a unit which there's a specific symbol on the symbolMap for it",
		"m", format.format(METER));

	Unit<Speed> v = new ProductUnit<Speed>(METER.divide(SECOND));

	assertEquals("the formatter isn't working with a product unit", "m/s", format.format(v));
    }

    @Test
    public void testFormatUCUMCI() {
	final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);

	assertEquals(METER, sut.getUnit());
	assertEquals("the formatter isn't working with a unit which there's a specific symbol on the symbolMap for it",
		"M", format.format(METER));

	Unit<Speed> v = new ProductUnit<Speed>(METER.divide(SECOND));
	assertEquals("the formatter isn't working with a product unit", "M/S", format.format(v));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testParseLocal() {
	final UnitFormat format = LocalUnitFormat.getInstance();

	format.parse("min").getSymbol();
    }

    @Test
    public void testParseUCUMCS() {
	final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

	assertEquals(MINUTE, format.parse("min"));
    }

    @Test
    public void testParseUCUMCI() {
	final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);

	assertEquals(METER, format.parse("M"));
    }

    @Test(expected = TokenException.class)
    public void testParseUCUMCSError() {
	final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

	format.parse("MIN");
    }

    @Test
    public void testParseUCUMCSLowercaseLiter() {
	final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

	assertEquals(LITER_DM3, format.parse("l"));
    }

    @Test
    public void testParseUCUMCSUppercaseLiter() {
	final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

	assertEquals(LITER, format.parse("L"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testParseUCUMPrint() {
	final UnitFormat format = UCUMFormat.getInstance(PRINT);

	format.parse("g");
    }

    @Test
    public void testUnitsUCUM() {
	final UnitFormat cs = UCUMFormat.getInstance(CASE_SENSITIVE);
	final UnitFormat ci = UCUMFormat.getInstance(CASE_INSENSITIVE);
	final UnitFormat pr = UCUMFormat.getInstance(PRINT);

	for (Unit<?> u : UCUM.getInstance().getUnits()) {
	    // try {
	    // Unit<?> v = format.parse("1/" + u.toString());
	    LOGGER.log(LOG_LEVEL, String.format("%s @ %s @ %s @ %s", cs.format(u), ci.format(u), pr.format(u), u));
	    // } catch (ParserException pex) {
	    // logger.log(Level.WARNING, String.format(" %s parsing %s", pex,
	    // u));
	    // }
	}
    }
}
