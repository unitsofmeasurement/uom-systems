/*
 * Units of Measurement Systems for Java
 * Copyright (c) 2005-2017, Jean-Marie Dautelle, Werner Keil and others.
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
import static tec.uom.se.unit.MetricPrefix.*;
import javax.measure.*;
import javax.measure.format.*;
import javax.measure.spi.ServiceProvider;

import org.junit.*;

import systems.uom.ucum.UCUM;
import systems.uom.ucum.internal.format.TokenException;

/**
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 *
 */
public class UCUMFormatVolumeTest extends UCUMFormatTestBase {

    @Test
    public void testFormatUCUMCI() {
	final UnitFormat unitFormat = ServiceProvider.current().getUnitFormatService().getUnitFormat("CI");
	assertEquals("L", unitFormat.format(UCUM.LITER_DM3)); // prints "CSTR"!
    }
    
    @Test
    public void testFormatUCUMCS() {
	final UnitFormat unitFormat = ServiceProvider.current().getUnitFormatService().getUnitFormat("CS");
	assertEquals("l", unitFormat.format(UCUM.LITER_DM3));
    }
    
    @Test
    public void testFormatUCUMCIDeciDm3() {
	final UnitFormat unitFormat = ServiceProvider.current().getUnitFormatService().getUnitFormat("CI");
	final Unit<?> deciliter = DECI(UCUM.LITER_DM3);
	assertEquals("dL", unitFormat.format(deciliter)); // prints "CM3"
    }
    
    @Test
    public void testFormatUCUMCSDeciDm3() {
	final UnitFormat unitFormat = ServiceProvider.current().getUnitFormatService().getUnitFormat("CS");
	final Unit<?> deciliter = DECI(UCUM.LITER_DM3);
	assertEquals("dl", unitFormat.format(deciliter)); // prints "cm3"
    }

    @Test
    public void testParseUCUMCIDeciDm3() {
	final UnitFormat unitFormat = ServiceProvider.current().getUnitFormatService().getUnitFormat("CI");
	final Unit<?> deciliter2 = unitFormat.parse("dl");
	assertEquals(DECI(UCUM.LITER_DM3), deciliter2);
    }

    @Test
    public void testParseUCUMCSDeciDm3() {
	final UnitFormat unitFormat2 = ServiceProvider.current().getUnitFormatService().getUnitFormat("CS");
	final Unit<?> dl3 = unitFormat2.parse("dl");
	assertEquals(DECI(UCUM.LITER_DM3), dl3);
    }
    
    @Test
    public void testParseUCUMCSMicroDm3() {
	final UnitFormat unitFormat = ServiceProvider.current().getUnitFormatService().getUnitFormat("CS");
	final Unit<?> microliter = unitFormat.parse("ul");
	assertEquals(MICRO(UCUM.LITER_DM3), microliter);
    }
    
    @Test(expected=TokenException.class)
    public void testParseUCUMCSMicroFail() {
	final UnitFormat unitFormat = ServiceProvider.current().getUnitFormatService().getUnitFormat("CS");
	final Unit<?> microliter = unitFormat.parse("UL");
	assertEquals(MICRO(UCUM.LITER_DM3), microliter);
    }

    @Test
    public void testParseUCUMCIMicro() {
	final UnitFormat unitFormat = ServiceProvider.current().getUnitFormatService().getUnitFormat("CI");
	final Unit<?> microliter = unitFormat.parse("UL");
	assertEquals(MICRO(UCUM.LITER_DM3), microliter); // Here we get LITER_DM3, not LITER
    }
    
    @Test
    public void testParseUCUMCSMicro() {
	final UnitFormat unitFormat = ServiceProvider.current().getUnitFormatService().getUnitFormat("CS");
	final Unit<?> microliter = unitFormat.parse("uL");
	assertEquals(MICRO(UCUM.LITER), microliter);
    }

    @Test
    public void testParseUCUMCIDeciSt() {
	final UnitFormat unitFormat = ServiceProvider.current().getUnitFormatService().getUnitFormat("CI");
	final Unit<?> dst = unitFormat.parse("DSTR");
	assertEquals(DECI(UCUM.STERE), dst);
    }
    
    @Test
    public void testParseUCUMCIMicroSt() {
	final UnitFormat unitFormat = ServiceProvider.current().getUnitFormatService().getUnitFormat("CI");
	final Unit<?> ust = unitFormat.parse("USTR");
	assertEquals(MICRO(UCUM.STERE), ust);
    }
    
    @Test
    public void testParseUCUMCSDeciSt() {
	final UnitFormat unitFormat = ServiceProvider.current().getUnitFormatService().getUnitFormat("CS");
	final Unit<?> dst = unitFormat.parse("dst");
	assertEquals(DECI(UCUM.STERE), dst);
    }
    
    @Test
    public void testParseUCUMCSMicroSt() {
	final UnitFormat unitFormat = ServiceProvider.current().getUnitFormatService().getUnitFormat("CS");
	final Unit<?> ust = unitFormat.parse("ust");
	assertEquals(MICRO(UCUM.STERE), ust);
    }
    
    @Test
    public void testFormatUCUMCSGill() {
	assertEquals("[gil_br]", FORMAT_CS.format(UCUM.GILL_BRITISH));
    }
    
    @Test
    @Ignore("There is no print format for gil in UCUM and no multiples intended with the parse format")
    public void testFormatUCUMCSmGill() {
	assertEquals("[gil_br]", FORMAT_CS.format(MILLI(UCUM.GILL_BRITISH)));
    }
}
