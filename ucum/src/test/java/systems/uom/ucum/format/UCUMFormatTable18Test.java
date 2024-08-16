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
package systems.uom.ucum.format;

import static org.junit.jupiter.api.Assertions.*;
import static systems.uom.ucum.UCUM.*;
import static systems.uom.ucum.format.UCUMFormat.Variant.*;
import javax.measure.format.*;

import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:werner@uom.systems">Werner Keil</a>
 *
 */
public class UCUMFormatTable18Test {

    @Test
    public void testParsePRUCS() {
	final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

	assertEquals(PERIPHERAL_VASCULAR_RESISTANCE, format.parse("[PRU]"));
    }

    @Test
    public void testParsePRUCI() {
    final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);

    assertEquals(PERIPHERAL_VASCULAR_RESISTANCE, format.parse("[PRU]"));
    }
    
    @Test
    public void testFormatPRUCI() {
	final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);

	assertEquals("[PRU]", format.format(PERIPHERAL_VASCULAR_RESISTANCE));
    }
    
    @Test
    public void testFormatPRUCS() {
	final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

	assertEquals("[PRU]", format.format(PERIPHERAL_VASCULAR_RESISTANCE));
    }
    
    @Test
    public void testFormatPRUPrint() {
	final UnitFormat format = UCUMFormat.getInstance(PRINT);

	assertEquals("P.R.U.", format.format(PERIPHERAL_VASCULAR_RESISTANCE));
    }

    @Test
    public void testParseMWColCS() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

        assertEquals(METER_OF_WATER_COLUMN, format.parse("m[H2O]"));
    }

    @Test
    public void testParseMWColCI() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);

        assertEquals(METER_OF_WATER_COLUMN, format.parse("M[H2O]"));
    }

    @Test
    public void testFormatMWColCI() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);

        assertEquals("M[H2O]", format.format(METER_OF_WATER_COLUMN));
    }

    @Test
    public void testFormatMWColCS() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

        assertEquals("m[H2O]", format.format(METER_OF_WATER_COLUMN));
    }

    @Test
    public void testFormatMWColPrint() {
        final UnitFormat format = UCUMFormat.getInstance(PRINT);

        assertEquals("m H2O", format.format(METER_OF_WATER_COLUMN));
    }

    @Test
    public void testParseMHGCS() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

        assertEquals(METER_OF_MERCURY_COLUMN, format.parse("m[Hg]"));
    }

    @Test
    public void testParseMHGCI() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);

        assertEquals(METER_OF_MERCURY_COLUMN, format.parse("M[HG]"));
    }

    @Test
    public void testFormatMHGCI() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);

        assertEquals("M[HG]", format.format(METER_OF_MERCURY_COLUMN));
    }

    @Test
    public void testFormatMHGCS() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

        assertEquals("m[Hg]", format.format(METER_OF_MERCURY_COLUMN));
    }

    @Test
    public void testFormatMHGPrint() {
        final UnitFormat format = UCUMFormat.getInstance(PRINT);

        assertEquals("m Hg", format.format(METER_OF_MERCURY_COLUMN));
    }

    @Test
    public void testParseInMWColCS() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

        assertEquals(INCH_OF_WATER_COLUMN, format.parse("[in_i'H2O]"));
    }

    @Test
    public void testParseInMWColCI() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);

        assertEquals(INCH_OF_WATER_COLUMN, format.parse("[IN_I'H2O]"));
    }

    @Test
    public void testFormatInMWColCI() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);

        assertEquals("[IN_I'H2O]", format.format(INCH_OF_WATER_COLUMN));
    }

    @Test
    public void testFormatInMWColCS() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

        assertEquals("[in_i'H2O]", format.format(INCH_OF_WATER_COLUMN));
    }

    @Test
    public void testFormatInMWColPrint() {
        final UnitFormat format = UCUMFormat.getInstance(PRINT);

        assertEquals("in H2O", format.format(INCH_OF_WATER_COLUMN));
    }

    @Test
    public void testParseInMHGCS() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

        assertEquals(INCH_OF_MERCURY_COLUMN, format.parse("[in_i'Hg]"));
    }

    @Test
    public void testParseInMHGCI() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);

        assertEquals(INCH_OF_MERCURY_COLUMN, format.parse("[IN_I'HG]"));
    }

    @Test
    public void testFormatInMHGCI() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);

        assertEquals("[IN_I'HG]", format.format(INCH_OF_MERCURY_COLUMN));
    }

    @Test
    public void testFormatInMHGCS() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

        assertEquals("[in_i'Hg]", format.format(INCH_OF_MERCURY_COLUMN));
    }

    @Test
    public void testFormatInMHGPrint() {
        final UnitFormat format = UCUMFormat.getInstance(PRINT);

        assertEquals("in Hg", format.format(INCH_OF_MERCURY_COLUMN));
    }

    @Test
    public void testParseWoodCS() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

        assertEquals(WOOD, format.parse("[wood'U]"));
    }

    @Test
    public void testParseWoodCI() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);

        assertEquals(WOOD, format.parse("[WOOD'U]"));
    }

    @Test
    public void testFormatWoodCI() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);

        assertEquals("[WOOD'U]", format.format(WOOD));
    }

    @Test
    public void testFormatWoodCS() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

        assertEquals("[wood'U]", format.format(WOOD));
    }

    @Test
    public void testFormatWoodPrint() {
        final UnitFormat format = UCUMFormat.getInstance(PRINT);

        assertEquals("Wood U.", format.format(WOOD));
    }

    @Test
    public void testParseDropCS() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

        assertEquals(DROP, format.parse("[drp]"));
    }

    @Test
    public void testParseDropCI() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);

        assertEquals(DROP, format.parse("[DRP]"));
    }

    @Test
    public void testFormatDropCI() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_INSENSITIVE);

        assertEquals("[DRP]", format.format(DROP));
    }

    @Test
    public void testFormatDropCS() {
        final UnitFormat format = UCUMFormat.getInstance(CASE_SENSITIVE);

        assertEquals("[drp]", format.format(DROP));
    }

    @Test
    public void testFormatDropPrint() {
        final UnitFormat format = UCUMFormat.getInstance(PRINT);

        assertEquals("drp", format.format(DROP));
    }
}
