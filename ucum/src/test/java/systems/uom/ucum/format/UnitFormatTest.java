/*
 * Units of Measurement Systems
 * Copyright (c) 2005-2020, Jean-Marie Dautelle, Werner Keil and others.
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

import static javax.measure.MetricPrefix.KILO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static systems.uom.ucum.format.UCUMFormat.Variant.PRINT;
import static systems.uom.ucum.format.StringUtils.isPureAscii;

import javax.measure.MeasurementException;
import javax.measure.Unit;
import javax.measure.format.UnitFormat;

import org.junit.jupiter.api.Test;

import systems.uom.ucum.UCUM;

/**
 * @author <a href="mailto:werner@uom.systems">Werner Keil</a>
 *
 */
public class UnitFormatTest extends UCUMFormatTestBase {

    @Test
    public void testUnitsUCUMpureASCII() {
	for (Unit<?> u : UCUM.getInstance().getUnits()) {
	    // try {
	    // Unit<?> v = format.parse("1/" + u.toString());
	    //LOGGER.log(LOG_LEVEL, String.format("%s @ %s @ %s @ %s", 
		    assertTrue(isPureAscii(FORMAT_CS.format(u)), String.format("CS format %s contains non-ASCII characters", FORMAT_CS.format(u)));
		    assertTrue(isPureAscii(FORMAT_CI.format(u)), String.format("CI format %s contains non-ASCII characters", FORMAT_CI.format(u)));
//		    assertTrue(String.format("Print format %s contains non-ASCII characters", pr.format(u)), isPureAscii(pr.format(u)));
	    // } catch (ParserException pex) {
	    // logger.log(Level.WARNING, String.format(" %s parsing %s", pex,"
	    // u));
	    // }
	}
    }
    
    @Test
    public void testUnitsUCUM() {
	for (Unit<?> u : UCUM.getInstance().getUnits()) {
	    // try {
	    // Unit<?> v = format.parse("1/" + u.toString());
	    //LOGGER.log(LOG_LEVEL, String.format("%s @ %s @ %s @ %s", FORMAT_CS.format(u), FORMAT_CI.format(u), FORMAT_PRINT.format(u), u));
	    // } catch (ParserException pex) {
	    // logger.log(Level.WARNING, String.format(" %s parsing %s", pex,
	    // u));
	    // }
	}
    }
    
    @Test
    public void testInvertUCUM() {
    	for (Unit<?> u : UCUM.getInstance().getUnits()) {
		    try {
			Unit<?> v = FORMAT_CS.parse("1/" + FORMAT_CS.format(u));
	//		LOGGER.log(LOG_LEVEL, String.format("%s @ %s @ %s @ %s -> %s @ %s", FORMAT_CI.format(u), FORMAT_CI.format(u),
	//			FORMAT_PRINT.format(u), u, FORMAT_PRINT.format(v), v));
		    } catch (MeasurementException mex) {
	//		LOGGER.log(Level.WARNING, String.format(" %s parsing %s", mex, u));
		    }
		}
    }
    
    @Test
    public void testNewton() {
    	final UnitFormat format = UCUMFormat.getInstance(PRINT);
    	Unit newton = KILO(UCUM.GRAM).multiply(UCUM.METER).divide(UCUM.SECOND).divide(UCUM.SECOND);
    	assertEquals("kg.m/s2", format.format(newton));
    }
}
