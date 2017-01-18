/*
 *  Unit-API - Units of Measurement API for Java
 *  Copyright (c) 2005-2016, Jean-Marie Dautelle, Werner Keil, V2COM.
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
 * 3. Neither the name of JSR-363 nor the names of its contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
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
package systems.uom.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static systems.uom.common.USCustomary.POUND;
import static tec.uom.se.unit.MetricPrefix.*;
import static tec.uom.se.unit.Units.GRAM;
import static tec.uom.se.unit.Units.KILOGRAM;

import org.junit.Test;

import tec.uom.se.format.EBNFUnitFormat;

public class USTest {
    @Test
    public void testFormat() {
	assertEquals("lb", POUND.toString());
    }

    @Test
    public void testFormatEBNF() {
	EBNFUnitFormat format = EBNFUnitFormat.getInstance();

	format.label(POUND, "pd");
	format.label(POUND, "2pd");
	// assertEquals("lb", POUND.toString());
	assertEquals("lb", format.format(POUND));
    }

    @Test
    public void testGetSymbol() {
	// TODO see https://github.com/unitsofmeasurement/uom-se/issues/54 /
	// https://java.net/jira/browse/UNITSOFMEASUREMENT-109
	assertEquals("kg", KILOGRAM.getSymbol());
	// assertEquals("kg", SI.GRAM.getSymbol()); //"g"
	// assertEquals("kg", UCUM.POUND.getSymbol()); //"lb"
	// assertEquals("kg", UCUM.OUNCE.getSymbol());//"oz"
	assertEquals("kg", KILO(GRAM).getSymbol());
	// assertEquals("kg", UCUM.GRAM.getSymbol()); //"g"
	// assertEquals("kg", US.POUND.getSymbol()); //"lb"
	assertEquals("g", GRAM.toString());
//	assertNull(GRAM.getSymbol()); // FIXME this is inconsitent,
				      // symbol should be either null or "g",
				      // but not "kg" (works for RI)
	// assertNull(UCUM.OUNCE.getSymbol());
	assertEquals("lb", POUND.toString());
//	assertNull(POUND.getSymbol());
    }
}
