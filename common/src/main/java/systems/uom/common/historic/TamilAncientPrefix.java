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
package systems.uom.common.historic;

import javax.measure.Prefix;

/**
 * Utility class holding  traditional numbers of the Ancient Tamil Country, Tamizhakam.<br>
 * <code>{@literal Unit<Length>} NURU_METER = METER.prefix(NURU); </code>
 * 
 * @author <a href="mailto:werner@uom.systems">Werner Keil</a>
 * @version 1.1, $Date: 2021-03-29 $
 * @see <a
 *      href="http://en.wikipedia.org/wiki/Tamil_units_of_measurement#Whole_numbers">Wikipedia:
 *      Tamil units of measurement - Whole numbers</a> 
 * @draft 3
 */
public enum TamilAncientPrefix implements Prefix {
    /** <p>
	 * ௰ (pathu)
	 * </p> Prefix for 10<sup>1</sup>. */
	PATHU("௰", "pathu", 10, 1),
    /** Prefix for 10<sup>2</sup>. */
	NURU("௱", "nūru", 10, 2),
    /** Prefix for 10<sup>15</sup>. */
	AYIRAM("௲", "āyiram", 10, 3),
    /** Prefix for 10<sup>4</sup>. */
	PATTAYIRAM("௰௲", "pattāyiram", 10, 4),
    /** Prefix for 10<sup>9</sup>. */
	NURAIYIRAM("௱௲", "nūraiyiram", 10, 5),
    /** Prefix for 10<sup>6</sup>. */
	MEIYYIRAM("௲௲", "meiyyiram", 10, 6),
    /** Prefix for 10<sup>9</sup>. */
	TOLLUN("௲௲௲", "tollun", 10, 9),
    /** Prefix for 10<sup>12</sup>. */
	IKIYAM("௲௲௲௲", "īkiyam", 10, 12),
    /** Prefix for 10<sup>1</sup>. */
	NELAI("௲௲௲௲௲", "neļai", 10, 15),
    /** Prefix for 10<sup>18</sup>. */
	ILANCI("௲௲௲௲௲௲", "iļañci", 10, 18),
    /** Prefix for 10<sup>20</sup>. */
	VELLAM("௱௲௲௲௲௲௲", "veļļam", 10, 20),
    /** Prefix for 10<sup>21</sup>. */
	AMPAL("௲௲௲௲௲௲௲", "āmpal", 10, 21);	
	
    /**
     * The symbol of this prefix, as returned by {@link #getSymbol}.
     *
     * @serial
     * @see #getSymbol()
     */
    private final String symbol;
    
    /**
     * The name of this prefix, as returned by {@link #getName}.
     *
     * @serial
     * @see #getName()
     */
    private final String name;

    /**
     * Base part of the associated factor in base^exponent representation.
     */
    private final int base;
    
    /**
     * Exponent part of the associated factor in base^exponent representation.
     */
    private final int exponent;

    /**
     * Creates a new prefix.
     *
     * @param symbol
     *          the symbol of this prefix.
     * @param name
     *          the (display) name of this prefix.
     * @param exponent
     *          part of the associated factor in base^exponent representation.
     */
    private TamilAncientPrefix(String symbol, String name, int base, int exponent) {
        this.symbol = symbol;
        this.name = name;
        this.base = base;
        this.exponent = exponent;
    }
    
    /**
     * Base part of the associated factor in {@code base^exponent} representation.
     */
    @Override
    public Integer getValue() {
        return base;
    }
    

    /**
     * Exponent part of the associated factor in base^exponent representation.
     */
    @Override
    public int getExponent() {
        return exponent;
    }

    /**
     * Returns the name of this prefix.
     *
     * @return this prefix name, not {@code null}.
     */
    @Override
    public String getName() {
        return name;
    }
    
    /**
     * Returns the symbol of this prefix.
     *
     * @return this prefix symbol, not {@code null}.
     */
    @Override
    public String getSymbol() {
        return symbol;
    }
}
