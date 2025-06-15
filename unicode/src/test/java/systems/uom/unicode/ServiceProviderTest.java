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
package systems.uom.unicode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static javax.measure.spi.FormatService.FormatType.UNIT_FORMAT;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.measure.spi.ServiceProvider;

import org.junit.jupiter.api.Test;

public class ServiceProviderTest {
	private static final Logger LOGGER = Logger.getLogger(ServiceProviderTest.class.getName());
	private static final Level LOGLEVEL = Level.INFO;
	
	@Test
	public void testAvailables() {
		List<ServiceProvider> providers = ServiceProvider.available();
		assertNotNull(providers);
		assertEquals(2, providers.size());
		
		for (ServiceProvider provider : providers) {
			LOGGER.log(LOGLEVEL, String.valueOf(provider));
		}
	}

	@Test
	public void testCurrent() {
		ServiceProvider provider = ServiceProvider.current();
		assertNotNull(provider);
		assertEquals("systems.uom.unicode.spi.UnicodeServiceProvider", provider.getClass().getName());
		assertEquals("Unicode", provider.toString());
		
		assertNotNull(provider.getFormatService());
		assertNotNull(provider.getFormatService().getAvailableFormatNames(UNIT_FORMAT));
		assertEquals(4, provider.getFormatService().getAvailableFormatNames(UNIT_FORMAT).size());
		assertNotNull(provider.getSystemOfUnitsService());
		assertNotNull(provider.getSystemOfUnitsService().getAvailableSystemsOfUnits());
		assertEquals(1, provider.getSystemOfUnitsService().getAvailableSystemsOfUnits().size());
		assertEquals("Unicode CLDR", provider.getSystemOfUnitsService().getSystemOfUnits().getName());
	}
}
