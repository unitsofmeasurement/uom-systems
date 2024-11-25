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
package systems.uom.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.measure.spi.ServiceProvider;
import javax.measure.spi.SystemOfUnits;
import javax.measure.spi.SystemOfUnitsService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SystemOfUnitsServiceTest {
	private static final String DEFAULT_SERVICE_CLASSNAME = "systems.uom.common.spi.CommonSystemService";
	private static final String COMMON_SERVICE_CLASSNAME = "systems.uom.common.spi.CommonSystemService";
	private static final Logger LOGGER = Logger.getLogger(SystemOfUnitsServiceTest.class.getName());
	private static final Level LOGLEVEL = Level.INFO;
		
	private static final int NUM_OF_UNITS_US = 47;
	private static final int NUM_OF_UNITS_IMPER = 25;
	private static final int NUM_OF_UNITS_CGS = 12;
	private static final int NUM_OF_UNITS_MKPS = 12;
	private static final int NUM_OF_UNITS_DEFAULT = NUM_OF_UNITS_US;
	
	private static SystemOfUnitsService defaultService;

	@BeforeAll
	public static void setUp() {
		defaultService = ServiceProvider.current().getSystemOfUnitsService();
	}

	@Test
	public void testDefaultUnitSystemService() {
		assertNotNull(defaultService);
		assertEquals(DEFAULT_SERVICE_CLASSNAME, defaultService.getClass().getName());
		SystemOfUnits system = defaultService.getSystemOfUnits();
		assertNotNull(system);
		assertEquals("systems.uom.common.USCustomary", system.getClass().getName());
		assertEquals("United States Customary Units", system.getName());
		assertNotNull(system.getUnits());
		assertEquals(NUM_OF_UNITS_DEFAULT, system.getUnits().size());
	}
	
	@Test
	public void testCommonUnitSystemService() {
		final ServiceProvider commonProvider = ServiceProvider.of("Common");
		assertNotNull(commonProvider);
		final SystemOfUnitsService commonService = commonProvider.getSystemOfUnitsService();
		assertNotNull(commonService);
		assertEquals("systems.uom.common.spi.CommonSystemService", commonService.getClass().getName());
		SystemOfUnits system = commonService.getSystemOfUnits();
		assertNotNull(system);
		assertEquals("systems.uom.common.USCustomary", system.getClass().getName());
		assertEquals("United States Customary Units", system.getName());
		assertNotNull(system.getUnits());
		assertEquals(NUM_OF_UNITS_US, system.getUnits().size());
	}

	@Test
	// TODO consolidate asserts
	public void testCommonUnitSystemServiceAlias() {
		final ServiceProvider commonProvider = ServiceProvider.of("Common");
		assertNotNull(commonProvider);
		final SystemOfUnitsService commonService = commonProvider.getSystemOfUnitsService();
		assertNotNull(commonService);
		assertEquals(COMMON_SERVICE_CLASSNAME, commonService.getClass().getName());
		SystemOfUnits system = commonService.getSystemOfUnits("USCustomary");
		assertNotNull(system);
		assertEquals("systems.uom.common.USCustomary", system.getClass().getName());
		assertEquals("United States Customary Units", system.getName());
		assertNotNull(system.getUnits());
		assertEquals(NUM_OF_UNITS_US, system.getUnits().size());
		SystemOfUnits system2 = commonService.getSystemOfUnits("US");
		assertEquals(system, system2);
		
		system = commonService.getSystemOfUnits("Imperial");
		assertNotNull(system);
		assertEquals("systems.uom.common.Imperial", system.getClass().getName());
		assertEquals("Imperial Units", system.getName());
		assertNotNull(system.getUnits());
		assertEquals(NUM_OF_UNITS_IMPER, system.getUnits().size());
		system2 = commonService.getSystemOfUnits("UK");
		assertEquals(system, system2);

		system = commonService.getSystemOfUnits("CGS");
		assertNotNull(system);
		assertEquals("Centimetre–gram–second System of Units", system.getName());
		system2 = commonService.getSystemOfUnits("Centimetre–gram–second");
		assertEquals(system, system2);
		assertEquals(NUM_OF_UNITS_CGS, system.getUnits().size());
		
		system = commonService.getSystemOfUnits("MKpS");
		assertNotNull(system);
		assertEquals("Gravitational metric system", system.getName());
		assertEquals(NUM_OF_UNITS_MKPS, system.getUnits().size());		
		system2 = commonService.getSystemOfUnits("Gravitational metric system");
		assertEquals(system, system2);
		SystemOfUnits system3 = commonService.getSystemOfUnits("MKfS");
		assertEquals(system, system3);
		assertEquals(system2, system3);
	}

	@Test
	public void testOtherUnitSystemServices() {
		Collection<ServiceProvider> services = ServiceProvider.available();
		assertNotNull(services);
		assertEquals(3, services.size());
		for (ServiceProvider provider : services) {
			LOGGER.log(LOGLEVEL, String.valueOf(provider));
			// TODO change to DEBUG or lower after https://github.com/unitsofmeasurement/unit-api/issues/195 was resolved
		}
		// for (SystemOfUnitsService service : services) {
		// checkService(service);
		// }
	}
}
