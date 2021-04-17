/*
 * Units of Measurement Systems
 * Copyright (c) 2005-2021, Jean-Marie Dautelle, Werner Keil and others.
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
package systems.uom.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static systems.uom.common.IndianPrefix.CRORE;
import static systems.uom.common.IndianPrefix.LAKH;
import static javax.measure.MetricPrefix.KILO;
import static tech.units.indriya.unit.Units.METRE;

import javax.measure.Unit;
import javax.measure.quantity.Length;

import org.junit.jupiter.api.Test;

import tech.units.indriya.function.MultiplyConverter;

public class IndianTest {
	@Test
	public void testLakhMethod() {
		assertEquals(MultiplyConverter.ofTenExponent(2), 
			LAKH(METRE).getConverterTo(KILO(METRE)));
	}
	
	@Test
	public void testLakhPrefix() {
		assertEquals(MultiplyConverter.ofTenExponent(2), 
			METRE.prefix(LAKH).getConverterTo(KILO(METRE)));
	}
	
	@Test
	public void testCroreMethod() {
		assertEquals(MultiplyConverter.ofTenExponent(4), 
			CRORE(METRE).getConverterTo(KILO(METRE)));
	}
	
	@Test
	public void testCrorePrefix() {
		final Unit<Length> croreMetre = METRE.prefix(CRORE);
		assertEquals(MultiplyConverter.ofTenExponent(4), 
			croreMetre.getConverterTo(KILO(METRE)));
		//assertEquals("crm", croreMetre.toString()); 
		// FIXME registering new/unknown prefixes with SimpleUnitFormat does not work right now
	}
	
}
