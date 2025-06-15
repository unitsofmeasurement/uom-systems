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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static javax.measure.BinaryPrefix.*;
import static javax.measure.MetricPrefix.*;

import static systems.uom.ucum.UCUM.*;
import javax.measure.Unit;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Volume;

import org.junit.jupiter.api.Test;

import systems.uom.quantity.Information;

public class UnitFormatPrefixTest extends UCUMFormatTestBase {

	// Metric Prefixes
	
    @Test
    public void testKiloMeter() {
        Unit<Length> m1 = KILO(METER);
        assertEquals("km", FORMAT_PRINT.format(m1));
    }
    
	@Test
	public void testKiloGram() {
		Unit<Mass> m1 = KILO(GRAM);
		assertEquals("kg", FORMAT_PRINT.format(m1));
	}

	@Test
	public void testMegaGram() {
		Unit<Mass> m1 = MEGA(GRAM);
		assertEquals("t", FORMAT_PRINT.format(TONNE));
		assertEquals("Mg", FORMAT_PRINT.format(m1));
	}

	@Test
	public void testMegaTonne() {
		Unit<Mass> m1 = MEGA(TONNE);
		assertEquals("Mt", FORMAT_PRINT.format(m1));
	}

	@Test
	public void testNanoGram() {
		Unit<Mass> m1 = NANO(GRAM);
		assertEquals("ng", FORMAT_PRINT.format(m1));
	}
	
	@Test
	public void testQuettabit() {
		Unit<Information> i1 = QUETTA(BIT);
		assertEquals("Qbit", FORMAT_PRINT.format(i1));
	}
	
	
	
	@Test
	public void testQuectoMeter() {
		Unit<Length> i1 = QUECTO(METER);
		assertEquals("qm", FORMAT_PRINT.format(i1));
	}
	
	@Test
	public void testRontoGram() {
		Unit<Mass> m1 = RONTO(GRAM);
		assertEquals("rg", FORMAT_PRINT.format(m1));
	}
	
	@Test
	public void testRonnaBit() {
		Unit<Information> i1 = RONNA(BIT);
		assertEquals("Rbit", FORMAT_PRINT.format(i1));
	}

	// Binary Prefixes
	
	@Test
	public void testGibiMeter() {
		Unit<Length> m1 = GIBI(METER);
		assertEquals("Gim", FORMAT_PRINT.format(m1));
	}

	@Test
	public void testKibiLiter() {
		Unit<Volume> v1 = KIBI(LITER);
		assertEquals("KiL", FORMAT_PRINT.format(v1));
	}

	@Test
	public void testKibit() {
		Unit<Information> i1 = KIBI(BIT);
		assertEquals("Kibit", FORMAT_PRINT.format(i1));
	}
}
