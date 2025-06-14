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
package systems.uom.common.spi;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.measure.spi.SystemOfUnits;
import systems.uom.common.historic.CGS;
import systems.uom.common.historic.MKpS;
import systems.uom.common.historic.ObsoleteUnits;
import systems.uom.common.Imperial;
import systems.uom.common.USCustomary;
import tech.units.indriya.spi.AbstractSystemOfUnitsService;

/**
 * @author <a href="mailto:werner@uom.systems">Werner Keil</a>
 * @version 2.3, June 15, 2025
 */
public class CommonSystemService extends AbstractSystemOfUnitsService {
	private static final String US_SYSTEM_NAME = "USCustomary";
	private static final String CGS_KEY = "CGS";
	private static final String MKPS_KEY = "MKpS";
	private static final String OBS_KEY = "ObsoleteUnits";
	
	private final Map<String, String> aliases = new HashMap<>();

	public CommonSystemService() {
		souMap.put("Imperial", Imperial.getInstance());
		souMap.put(US_SYSTEM_NAME, USCustomary.getInstance());
		souMap.put(CGS_KEY, CGS.getInstance());
		souMap.put(MKPS_KEY, MKpS.getInstance());		
		souMap.put(OBS_KEY, ObsoleteUnits.getInstance());
		aliases.put("US", US_SYSTEM_NAME);
		aliases.put("UK", "Imperial");
		aliases.put("Centimetre–gram–second", CGS_KEY);
		aliases.put("Gravitational metric system", MKPS_KEY);
		aliases.put("MKfS", MKPS_KEY);
		aliases.put("Obsolete", OBS_KEY);
	}

	public Collection<SystemOfUnits> getAvailableSystemsOfUnits() {
		return souMap.values();
	}

	@Override
	public SystemOfUnits getSystemOfUnits() {
		return getSystemOfUnits(US_SYSTEM_NAME); // We assume US Customary as the most
		// common system here
	}

	@Override
	public SystemOfUnits getSystemOfUnits(String name) {
		String alias = aliases.get(name);
		if (alias != null && alias.length() > 0) {
			return souMap.get(alias);
		}
		return souMap.get(name);
	}
}
