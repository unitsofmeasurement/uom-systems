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
package systems.uom.ucum.format;

import static org.junit.Assert.assertEquals;
import static javax.measure.MetricPrefix.*;

import static systems.uom.ucum.UCUM.*;
import javax.measure.Unit;
import javax.measure.UnitConverter;
import javax.measure.quantity.Mass;

import org.junit.Test;

import tech.units.indriya.function.PowerOfIntConverter;

public class PrefixTest extends UCUMFormatTestBase {

    @Test
    public void testKilo() {
        Unit<Mass> m1 = KILO(GRAM);
        assertEquals("g.1000", FORMAT_PRINT.format(m1)); // FIXME make "kg" work
    }

    @Test
    public void testMega() {
        Unit<Mass> m1 = MEGA(GRAM);
        assertEquals("t", FORMAT_PRINT.format(TONNE));
        assertEquals("g.1000000", FORMAT_PRINT.format(m1)); // FIXME make Mg work as well
    }

    @Test
    public void testMega2() {
        Unit<Mass> m1 = MEGA(TONNE);
        assertEquals("t.1000000", FORMAT_PRINT.format(m1)); // FIXME make Mt work as well
    }

    @Test
    public void testNano() {
        Unit<Mass> m1 = NANO(GRAM);
        assertEquals("g/1000000000", FORMAT_PRINT.format(m1)); // FIXME make ng work as well
        // assertEquals("ng", FORMAT_EBNF.format(m1));
    }

    @Test
    public void testBetweenPrefixes() {
        UnitConverter conv = YOTTA(METER).getConverterTo(ZETTA(METER));
        assertEquals(PowerOfIntConverter.of(10, 3), conv);
    }

    @Test
    public void testBetweenPrefixes2() {
        UnitConverter conv = KILO(METER).getConverterTo(GIGA(METER));
        assertEquals(PowerOfIntConverter.of(10, -6), conv);
    }
}
