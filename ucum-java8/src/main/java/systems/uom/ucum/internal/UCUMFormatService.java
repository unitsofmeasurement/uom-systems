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
package systems.uom.ucum.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.measure.format.UnitFormat;
import javax.measure.spi.UnitFormatService;

import systems.uom.ucum.format.UCUMFormat;
import systems.uom.ucum.format.UCUMFormat.Variant;

/**
 * UCUM format service.
 *
 * @author Werner Keil
 * @version 0.5, March 16, 2017
 */
final class UCUMFormatService implements UnitFormatService {

    private static final String DEFAULT_FORMAT = Variant.CASE_SENSITIVE.name();

    private final Map<String, UnitFormat> formats = new HashMap<>();
    private final Map<String, String> aliases = new HashMap<>();

    UCUMFormatService() {
	formats.put(DEFAULT_FORMAT, UCUMFormat.getInstance(Variant.CASE_SENSITIVE));
	formats.put(Variant.CASE_INSENSITIVE.name(), UCUMFormat.getInstance(Variant.CASE_INSENSITIVE));
	formats.put(Variant.PRINT.name(), UCUMFormat.getInstance(Variant.PRINT));

	aliases.put("CS", DEFAULT_FORMAT);
	aliases.put("C/S", DEFAULT_FORMAT);
	aliases.put("CASE SENSITIVE", DEFAULT_FORMAT);
	aliases.put("CI", Variant.CASE_INSENSITIVE.name());
	aliases.put("C/I", Variant.CASE_INSENSITIVE.name());
	aliases.put("CASE INSENSITIVE", DEFAULT_FORMAT);
    }

    /*
     * (non-Javadoc)
     * 
     * @see UnitFormatService#getUnitFormat(String)
     */
    @Override
    public UnitFormat getUnitFormat(String name) {
	Objects.requireNonNull(name, "Format name or alias required");
	String alias = aliases.get(name.toUpperCase());
	if (alias != null && alias.length() > 0) {
	    return formats.get(alias);
	}
	return formats.get(name.toUpperCase());
    }

    /*
     * (non-Javadoc)
     * 
     * @see UnitFormatService#getUnitFormat()
     */
    @Override
    public UnitFormat getUnitFormat() {
	return getUnitFormat(DEFAULT_FORMAT);
    }

    public Set<String> getAvailableFormatNames() {
	return formats.keySet();
    }
}
