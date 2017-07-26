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
import static tec.uom.se.AbstractUnit.ONE;

import java.util.logging.Level;
import javax.measure.*;
import javax.measure.format.*;
import javax.measure.quantity.*;

import systems.uom.ucum.UCUM;
import systems.uom.ucum.format.UCUMFormat;
import systems.uom.ucum.internal.format.TokenException;
import tec.uom.se.format.LocalUnitFormat;
import tec.uom.se.quantity.Quantities;
import tec.uom.se.unit.ProductUnit;
import tec.uom.se.unit.Units;

import org.junit.*;

/**
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 *
 */
public class UCUMFormatTable2Test extends UCUMFormatTestBase {
    private Quantity<Length> sut;

    @Before
    public void init() {
	// sut =
	// QuantityFactoryProvider.getQuantityFactory(Length.class).create(10,
	// METER);
	sut = Quantities.getQuantity(10, METER);
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
	assertEquals(METER, sut.getUnit());
	assertEquals("the formatter isn't working with a unit which there's a specific symbol on the symbolMap for it",
		"m", FORMAT_PRINT.format(METER));

	Unit<Speed> v = new ProductUnit<Speed>(sut.getUnit().divide(SECOND));
	assertEquals("the formatter isn't working with a product unit", "m/s", FORMAT_PRINT.format(v));
    }

    @Test
    public void testFormatUCUMCS() {
	assertEquals(METER, sut.getUnit());
	assertEquals("the formatter isn't working with a unit which there's a specific symbol on the symbolMap for it",
		"m", FORMAT_CS.format(METER));

	Unit<Speed> v = new ProductUnit<Speed>(METER.divide(SECOND));

	assertEquals("the formatter isn't working with a product unit", "m/s", FORMAT_CS.format(v));
    }

    @Test
    public void testFormatUCUMCI() {
	assertEquals(METER, sut.getUnit());
	assertEquals("the formatter isn't working with a unit which there's a specific symbol on the symbolMap for it",
		"M", FORMAT_CI.format(METER));

	Unit<Speed> v = new ProductUnit<Speed>(METER.divide(SECOND));
	assertEquals("the formatter isn't working with a product unit", "M/S", FORMAT_CI.format(v));
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

    @Test(expected = UnsupportedOperationException.class)
    public void testParseUCUMPrint() {
	final UnitFormat format = UCUMFormat.getInstance(PRINT);

	format.parse("g");
    }

    @Test
    public void testParseUCUMCITemperatureInverse() throws Exception {
	Unit<?> parsedUnit = FORMAT_CI.parse("1/K");
	assertEquals("The Unit<Temperature> in  parsed string doesn't match", ONE.divide(Units.KELVIN), parsedUnit);
    }
    
    @Test
    public void testParseUCUMCSTemperatureInverse() throws Exception {
	Unit<?> parsedUnit = FORMAT_CS.parse("1/K");
	assertEquals("The Unit<Temperature> in  parsed string doesn't match", ONE.divide(Units.KELVIN), parsedUnit);
    }
}
