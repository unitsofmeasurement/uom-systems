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
import static systems.uom.ucum.UCUM.LITER;
import static systems.uom.ucum.UCUM.LITER_DM3;
import static tech.units.indriya.unit.MetricPrefix.DECI;
import static tech.units.indriya.unit.MetricPrefix.MICRO;

import javax.measure.Unit;

import org.junit.Test;

import systems.uom.ucum.UCUM;
import systems.uom.ucum.internal.format.TokenException;

/**
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 *
 */
public class UCUMFormatTable5Test extends UCUMFormatTestBase {

    @Test
    public void testParseUCUMCILowercaseLiter() {
	assertEquals(LITER_DM3, FORMAT_CI.parse("l"));
    }

    @Test
    public void testParseUCUMCIUppercaseLiter() {
	assertEquals(LITER_DM3, FORMAT_CI.parse("L"));
    }
    
    @Test
    public void testParseUCUMCSLowercaseLiter() {
	assertEquals(LITER_DM3, FORMAT_CS.parse("l"));
    }

    @Test
    public void testParseUCUMCSUppercaseLiter() {
	assertEquals(LITER, FORMAT_CS.parse("L"));
    }
    

    @Test
    public void testFormatUCUMCI() {
	assertEquals("L", FORMAT_CI.format(UCUM.LITER_DM3));
    }
    
    @Test
    public void testFormatUCUMCS() {
	assertEquals("l", FORMAT_CS.format(UCUM.LITER_DM3));
    }
    
    @Test
    public void testFormatUCUMPrint() {
	assertEquals("L", FORMAT_PRINT.format(UCUM.LITER));
    }
    
    @Test
    public void testFormatUCUMPrintDm3() {
	assertEquals("l", FORMAT_PRINT.format(UCUM.LITER_DM3));
    }
    
    @Test
    public void testFormatUCUMCIDeciDm3() {
	final Unit<?> deciliter = DECI(UCUM.LITER_DM3);
	assertEquals("DL", FORMAT_CI.format(deciliter));
    }
    
    @Test
    public void testFormatUCUMCSDeciDm3() {
	final Unit<?> deciliter = DECI(UCUM.LITER_DM3);
	assertEquals("dl", FORMAT_CS.format(deciliter));
    }

    @Test
    public void testParseUCUMCIDeciDm3() {
	final Unit<?> deciliter2 = FORMAT_CI.parse("dl");
	assertEquals(DECI(UCUM.LITER_DM3), deciliter2);
    }

    @Test
    public void testParseUCUMCSDeciDm3() {
	final Unit<?> dl3 = FORMAT_CS.parse("dl");
	assertEquals(DECI(UCUM.LITER_DM3), dl3);
    }
    
    @Test
    public void testParseUCUMCSMicroDm3() {
	final Unit<?> microliter = FORMAT_CS.parse("ul");
	assertEquals(MICRO(UCUM.LITER_DM3), microliter);
    }
    
    @Test(expected=TokenException.class)
    public void testParseUCUMCSMicroFail() {
	final Unit<?> microliter = FORMAT_CS.parse("UL");
	assertEquals(MICRO(UCUM.LITER_DM3), microliter);
    }

    @Test
    public void testParseUCUMCIMicro() {
	final Unit<?> microliter = FORMAT_CI.parse("UL");
	assertEquals(MICRO(UCUM.LITER_DM3), microliter); 
	// Here we get LITER_DM3, not LITER because LITER has no c/i representation in UCUM
    }
    
    @Test
    public void testParseUCUMCSMicro() {
	final Unit<?> microliter = FORMAT_CS.parse("uL");
	assertEquals(MICRO(UCUM.LITER), microliter);
    }
}
