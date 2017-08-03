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
package systems.uom.ucum;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static systems.uom.ucum.UCUM.*;

import javax.measure.Quantity;
import javax.measure.UnitConverter;
import javax.measure.quantity.Volume;

import org.junit.Test;

import tec.uom.se.quantity.Quantities;

/**
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 *
 */
public class UCUMTest {

    @Test
    public void testLiterToDm3() {
	final Quantity<Volume> oneLiter = Quantities.getQuantity(1, LITER);
	final Quantity<Volume> oneDm3 = Quantities.getQuantity(1, LITER_DM3);
	assertEquals(1d, oneLiter.to(LITER_DM3).getValue());
	//assertEquals(oneLiter, oneDm3);
    }
    
    @Test
    public void testUnitNotEqualsLiters() {
	assertNotEquals(LITER, LITER_DM3);
    }
    
    @Test
    public void testNameEqualsLiters() {
	assertEquals(LITER.getName(), LITER_DM3.getName());
    }
    
    @Test
    public void testConvEqualsLiters() {
	UnitConverter c1 = LITER.getConverterTo(LITER_DM3);
	UnitConverter c2 = LITER_DM3.getConverterTo(LITER);
	assertEquals(c1, c2);
    }
    
    @Test
    public void testStToDm3() {
	final Quantity<Volume> oneLiter = Quantities.getQuantity(1, STERE);
	assertEquals(1000d, oneLiter.to(LITER_DM3).getValue());
    }
    
    @Test
    public void testStToLiter() {
	final Quantity<Volume> oneLiter = Quantities.getQuantity(1, STERE);
	assertEquals(1000d, oneLiter.to(LITER).getValue());
    }
}
