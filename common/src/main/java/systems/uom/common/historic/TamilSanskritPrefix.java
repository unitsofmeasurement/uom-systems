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
package systems.uom.common.historic;

import javax.measure.Prefix;

/**
 * Utility class holding  traditional numbers of the Ancient Tamil Country, Tamizhakam.
 * Unit<Length> PATHU_METER = PATHU(METER); </code>
 * 
 * @author <a href="mailto:werner@uom.systems">Werner Keil</a>
 * @version 1.1, $Date: 2021-03-29 $
 * @see <a
 *      href="http://en.wikipedia.org/wiki/Tamil_units_of_measurement#Whole_numbers">Wikipedia:
 *      Tamil units of measurement - Sanskritized version</a> *      
 * @draft 2.1
 */
// FIXME Update
public enum TamilSanskritPrefix implements Prefix {
    /** <p>
	 * ௰ (pathu)
	 * </p> Prefix for 10<sup>21</sup>. */
	PATHU("P", 10, 1),
    /** Prefix for 10<sup>18</sup>. */
	nūru("S", 10, 2),
    /** Prefix for 10<sup>15</sup>. */
	āyiram("SA", 10, 3),
    /** Prefix for 10<sup>12</sup>. */
	pattāyiram("Lk", 10, 4),
    /** Prefix for 10<sup>9</sup>. */
	nūraiyiram("Cr", 10, 5),
    /** Prefix for 10<sup>6</sup>. */
	meiyyiram("A", 10, 6),
    /** Prefix for 10<sup>3</sup>. */
	tollun("K", 10, 9),
    /** Prefix for 10<sup>2</sup>. */
	īkiyam("N", 10, 12),
    /** Prefix for 10<sup>1</sup>. */
	neļai("Pa", 10, 15),
    /** Prefix for 10<sup>-1</sup>. */
	iļañci("SH", 10, 18),
    /** Prefix for 10<sup>-2</sup>. */
	veļļam("M", 10, 20),
    /** Prefix for 10<sup>-2</sup>. */
	āmpal("M", 10, 21);
	
	
    /**
     * The symbol of this prefix, as returned by {@link #getSymbol}.
     *
     * @serial
     * @see #getSymbol()
     */
    private final String symbol;

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
     * @param exponent
     *          part of the associated factor in base^exponent representation.
     */
    private TamilSanskritPrefix(String symbol, int base, int exponent) {
        this.symbol = symbol;
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
        return name();
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
