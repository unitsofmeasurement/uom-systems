/*
 * Units of Measurement Systems
 * Copyright (c) 2005-2025, Jean-Marie Dautelle, Werner Keil and others.
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
import static systems.uom.ucum.format.UCUMFormat.Variant.*;
import static tech.units.indriya.AbstractUnit.ONE;

import javax.measure.*;
import javax.measure.format.*;
import javax.measure.quantity.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import systems.uom.ucum.format.UCUMFormat;
import systems.uom.ucum.internal.format.TokenException;
import tech.units.indriya.format.LocalUnitFormat;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.ProductUnit;
import tech.units.indriya.unit.Units;

/**
 * @author <a href="mailto:werner@uom.systems">Werner Keil</a>
 *
 */
public class UCUMFormatTable2Test extends UCUMFormatTestBase {
	private static final String FORMAT_SYMBOL_ERR_MSG = "the formatter isn't working with a unit which there's a specific symbol on the symbolMap for it";
	
	private Quantity<Length> sut;

	@BeforeEach
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
		assertEquals(
				"m", FORMAT_PRINT.format(METER), FORMAT_SYMBOL_ERR_MSG);

		Unit<Speed> v = new ProductUnit<Speed>(sut.getUnit().divide(SECOND));
		assertEquals("m/s", FORMAT_PRINT.format(v), "the formatter isn't working with a product unit");
	}

	@Test
	public void testFormatUCUMCS() {
		assertEquals(METER, sut.getUnit());
		assertEquals(
				"m", FORMAT_CS.format(METER), FORMAT_SYMBOL_ERR_MSG);

		Unit<Speed> v = new ProductUnit<Speed>(METER.divide(SECOND));

		assertEquals("m/s", FORMAT_CS.format(v), "the formatter isn't working with a product unit");
	}

	@Test
	public void testFormatUCUMCI() {
		assertEquals(METER, sut.getUnit());
		assertEquals(
				"M", FORMAT_CI.format(METER), FORMAT_SYMBOL_ERR_MSG);

		Unit<Speed> v = new ProductUnit<Speed>(METER.divide(SECOND));
		assertEquals("M/S", FORMAT_CI.format(v), "the formatter isn't working with a product unit");
	}

	@Test
	public void testParseLocal() {
		final UnitFormat format = LocalUnitFormat.getInstance();
		assertEquals("min", format.parse("min").getSymbol());
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

	@Test
	public void testParseUCUMCSError() {
		assertThrows(TokenException.class, () -> {
			final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);
			format.parse("MIN");
		});
	}

	@Test
	public void testParseUCUMPrint() {
		assertThrows(UnsupportedOperationException.class, () -> {
			final UnitFormat format = UCUMFormat.getInstance(PRINT);
			format.parse("g");
		});
	}

	@Test
	public void testParseUCUMCITemperatureInverse() throws Exception {
		Unit<?> parsedUnit = FORMAT_CI.parse("1/K");
		assertEquals(ONE.divide(Units.KELVIN), parsedUnit, "The Unit<Temperature> in  parsed string doesn't match");
	}

	@Test
	public void testParseUCUMCSTemperatureInverse() throws Exception {
		Unit<?> parsedUnit = FORMAT_CS.parse("1/K");
		assertEquals(ONE.divide(Units.KELVIN), parsedUnit, "The Unit<Temperature> in  parsed string doesn't match");
	}
}
