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
package systems.uom.ucum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collection;

import javax.measure.spi.ServiceProvider;
import javax.measure.spi.SystemOfUnits;
import javax.measure.spi.SystemOfUnitsService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SystemOfUnitsServiceTest {
    private static final String EXPECTED_SYSTEM_NAME = "Unified Code for Units of Measure";
    private static final int NUM_OF_UNITS = 245;

    private static SystemOfUnitsService defaultService;

    @BeforeAll
    public static void setUp() {
    	defaultService = ServiceProvider.current().getSystemOfUnitsService();
    }

    @Test
    public void testDefaultUnitSystemService() {
		assertNotNull(defaultService);
		assertEquals("systems.uom.ucum.spi.UCUMSystemService", defaultService.getClass().getName());
		SystemOfUnits system = defaultService.getSystemOfUnits();
		assertNotNull(system);
		assertEquals("systems.uom.ucum.UCUM", system.getClass().getName());
		assertEquals(EXPECTED_SYSTEM_NAME, system.getName());
		assertNotNull(system.getUnits());
		assertEquals(NUM_OF_UNITS, system.getUnits().size());
    }

    @Test
    // TODO consolidate asserts
    public void testUnitSystemServiceAlias() {
		assertNotNull(defaultService);
		assertEquals("systems.uom.ucum.spi.UCUMSystemService", defaultService.getClass().getName());
		SystemOfUnits system = defaultService.getSystemOfUnits("UCUM");
		assertNotNull(system);
		assertEquals("systems.uom.ucum.UCUM", system.getClass().getName());
		assertEquals("Unified Code for Units of Measure", system.getName());
		assertNotNull(system.getUnits());
		assertEquals(NUM_OF_UNITS, system.getUnits().size());
		SystemOfUnits system2 = defaultService.getSystemOfUnits("Unified Code for Units of Measure");
		assertEquals(system, system2);
    }

    @Test
    public void testOtherUnitSystemServices() {
		Collection<ServiceProvider> services = ServiceProvider.available();
		assertNotNull(services);
		assertEquals(3, services.size());
		// for (SystemOfUnitsService service : services) {
		// checkService(service);
		// }
    }
}
