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

import static org.junit.Assert.*;
import static systems.uom.ucum.UCUM.*;
import static systems.uom.ucum.format.UCUMFormat.Variant.*;
import javax.measure.format.*;
import systems.uom.ucum.format.UCUMFormat;
import org.junit.*;

/**
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
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
}
