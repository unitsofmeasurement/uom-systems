/*
 * Units of Measurement Systems
 * Copyright (c) 2005-2023, Jean-Marie Dautelle, Werner Keil and others.
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
package systems.uom.ucum.spi;

import static tech.units.indriya.format.FormatBehavior.LOCALE_SENSITIVE;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.measure.format.QuantityFormat;
import javax.measure.format.UnitFormat;
import javax.measure.spi.FormatService;
import systems.uom.ucum.format.UCUMFormat;
import systems.uom.ucum.format.UCUMFormat.Variant;
import tech.units.indriya.format.EBNFUnitFormat;
import tech.units.indriya.format.NumberDelimiterQuantityFormat;
import tech.units.indriya.format.SimpleQuantityFormat;

/**
 * UCUM format service.
 *
 * @author Werner Keil
 * @version 2.0, November 18, 2020
 */
public final class UCUMFormatService implements FormatService {

	private static final String UNIT_FORMAT_KEY_CASE_SENSITIVE = "UCUM_CS";
	private static final String UNIT_FORMAT_KEY_CASE_INSENSITIVE = "UCUM_CI";
	private static final String UNIT_FORMAT_KEY_PRINT = "UCUM_PRINT";
	private static final String DEFAULT_UNIT_FORMAT = UNIT_FORMAT_KEY_CASE_SENSITIVE;

	private final Map<String, UnitFormat> unitFormats = new HashMap<>();
	private final Map<String, String> unitFormatAliases = new HashMap<>();

	private static final String DEFAULT_QUANTITY_FORMAT = "Simple";

	/**
	 * Holds the default format instance (EBNFUnitFormat).
	 */
	private static final NumberDelimiterQuantityFormat EBNF_QUANTITY_FORMAT = new NumberDelimiterQuantityFormat.Builder()
			.setNumberFormat(NumberFormat.getInstance(Locale.ROOT)).setUnitFormat(EBNFUnitFormat.getInstance()).build();

	private final Map<String, QuantityFormat> quantityFormats = new HashMap<>();

	public UCUMFormatService() {
		quantityFormats.put(DEFAULT_QUANTITY_FORMAT, SimpleQuantityFormat.getInstance());
		quantityFormats.put("NumberDelimiter", NumberDelimiterQuantityFormat.getInstance());
		quantityFormats.put("EBNF", EBNF_QUANTITY_FORMAT);
		quantityFormats.put("Local", NumberDelimiterQuantityFormat.getInstance(LOCALE_SENSITIVE));

		unitFormats.put(DEFAULT_UNIT_FORMAT, UCUMFormat.getInstance(Variant.CASE_SENSITIVE));
		unitFormats.put(UNIT_FORMAT_KEY_CASE_INSENSITIVE, UCUMFormat.getInstance(Variant.CASE_INSENSITIVE));
		unitFormats.put(UNIT_FORMAT_KEY_PRINT, UCUMFormat.getInstance(Variant.PRINT));

		unitFormatAliases.put("UCUM", DEFAULT_UNIT_FORMAT);
		unitFormatAliases.put(Variant.CASE_SENSITIVE.name(), DEFAULT_UNIT_FORMAT);
		unitFormatAliases.put(Variant.CASE_INSENSITIVE.name(), UNIT_FORMAT_KEY_CASE_INSENSITIVE);
		unitFormatAliases.put(Variant.PRINT.name(), UNIT_FORMAT_KEY_PRINT);
		unitFormatAliases.put("CS", DEFAULT_UNIT_FORMAT);
		unitFormatAliases.put("C/S", DEFAULT_UNIT_FORMAT);
		unitFormatAliases.put("CASE SENSITIVE", DEFAULT_UNIT_FORMAT);
		unitFormatAliases.put("CI", UNIT_FORMAT_KEY_CASE_INSENSITIVE);
		unitFormatAliases.put("C/I", UNIT_FORMAT_KEY_CASE_INSENSITIVE);
		unitFormatAliases.put("CASE INSENSITIVE", UNIT_FORMAT_KEY_CASE_INSENSITIVE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see UnitFormatService#getUnitFormat(String)
	 */
	@Override
	public UnitFormat getUnitFormat(String key) {
		Objects.requireNonNull(key, "Format name or alias required");
		String alias = unitFormatAliases.get(key.toUpperCase());
		if (alias != null && alias.length() > 0) {
			return unitFormats.get(alias);
		}
		return unitFormats.get(key.toUpperCase());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see UnitFormatService#getUnitFormat()
	 */
	@Override
	public UnitFormat getUnitFormat() {
		return getUnitFormat(DEFAULT_UNIT_FORMAT);
	}

	public Set<String> getAvailableFormatNames() {
		return unitFormats.keySet();
	}

	@Override
	public QuantityFormat getQuantityFormat(String name) {
		return quantityFormats.get(name);
	}

	@Override
	public QuantityFormat getQuantityFormat() {
		return getQuantityFormat(DEFAULT_QUANTITY_FORMAT);
	}

	@Override
	public Set<String> getAvailableFormatNames(FormatType type) {
		switch (type) {
		case QUANTITY_FORMAT:
			return quantityFormats.keySet();
		default:
			return unitFormats.keySet();
		}
	}

	@Override
	public UnitFormat getUnitFormat(String name, String variant) {
		final StringBuilder sb = new StringBuilder(name);
		if (null != variant && !variant.isEmpty()) { 
			sb.append("_");
			sb.append(variant);
		}
		return getUnitFormat(sb.toString());
	}
}
