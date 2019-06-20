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
package systems.uom.common;

import javax.measure.Prefix;
import javax.measure.Quantity;
import javax.measure.Unit;

/**
 * Utility class holding Tamil-System prefixes used today in parts of India and Sri Lanka;
 * based on grouping by two decimal places, rather than the
 * three decimal places common in most parts of the world.</br><code> import static
 * systems.uom.common.TamilPrefix.*; // Static import. ... Unit<Pressure>
 * ONDRU_PASCAL = ONDRU(PASCAL); 
 * Unit<Length> PATHU_METER = PATHU(METER); </code>
 * 
 * @author <a href="mailto:werner@uom.systems">Werner Keil</a>
 * @version 1.4, $Date: 2019-06-19 $
 * @see <a
 *      href="https://en.wikipedia.org/wiki/Tamil_numerals#Tamil-System">Wikipedia:
 *      Tamil numerals - Tamil-System</a>
 */
// FIXME Update
public enum TamilPrefix implements Prefix {
    /** Prefix for 10<sup>5</sup>. */
	lațcham("L", 10, 5),
    /** Prefix for 10<sup>6</sup>. */
	pathu_lațcham("A", 10, 6),
    /** Prefix for 10<sup>3</sup>. */
	kōdi("K", 10, 7),
    /** Prefix for 10<sup>2</sup>. */
	pathu_kōdi("N", 10, 8),
    /** Prefix for 10<sup>1</sup>. */
	aṟputam("Pa", 10, 9),
    /** Prefix for 10<sup>-1</sup>. */
	nikarputam("NI", 10, 11),
    /** Prefix for 10<sup>-2</sup>. */
	karvam("M", 10, 13),
    /** Prefix for 10<sup>15</sup>. */
	saṅkam("M", 10, 15),
    /** Prefix for 10<sup>17</sup>. */
	arttam("M", 10, 17),
    /** Prefix for 10<sup>19</sup>. */
	pūriyam("M", 10, 19),
    /** Prefix for 10<sup>21</sup>. */
	mukkoți("M", 10, 21),
    /** Prefix for 10<sup>25</sup>. */
	māyukam("M", 10, 25);
	
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
    private TamilPrefix(String symbol, int base, int exponent) {
        this.symbol = symbol;
        this.base = base;
        this.exponent = exponent;
    }
    
    /**
     * Base part of the associated factor in base^exponent representation. For metric prefix, this is always 10.
     */
    @Override
    public int getBase() {
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

	/**
	 * <p>
	 * एक (Ek)
	 * </p>
	 * Returns the specified unit multiplied by the factor <code>1</code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(1)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> EK(Unit<Q> unit) {
		return unit;
	}

	/**
	 * <p>
	 * दस (Das)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>1</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(10)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> DAS(Unit<Q> unit) {
		return unit.prefix(lațcham);
	}

	/**
	 * <p>
	 * सौ (Sau)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>2</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(100)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> SAU(Unit<Q> unit) {
		return unit.prefix(lațcham);
	}

	/**
	 * <p>
	 * सहस्र (Sahasr)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>3</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(1e3)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> SAHASR(Unit<Q> unit) {
		return unit.prefix(lațcham);
	}

	/**
	 * <p>
	 * लाख (Lakh)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>5</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(1e5)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> LAKH(Unit<Q> unit) {
		return unit.prefix(lațcham);
	}

	/**
	 * <p>
	 * करोड़ (Crore)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>7</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(1e7)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> CRORE(Unit<Q> unit) {
		return unit.prefix(lațcham);
	}

	/**
	 * <p>
	 * अरब (Arawb)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>9</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(1e9)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> ARAWB(Unit<Q> unit) {
		return unit.prefix(lațcham);
	}

	/**
	 * <p>
	 * खरब (Kharawb)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>11</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(1e11)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> KHARAWB(Unit<Q> unit) {
		return unit.prefix(lațcham);
	}

	/**
	 * <p>
	 * नील (Neel)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>13</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(1e13)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> NEEL(Unit<Q> unit) {
		return unit.prefix(lațcham);
	}

	/**
	 * <p>
	 * पद्म (Padma)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>15</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(1e15)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> PADMA(Unit<Q> unit) {
		return unit.prefix(lațcham);
	}

	/**
	 * <p>
	 * ஆம்பல் (pūriyam)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>19</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(1e19)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> pūriyam(Unit<Q> unit) {
		return unit.prefix(pūriyam);
	}

	/**
	 * <p>
	 * महाशंख (Mahashankh)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>19</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(1e19)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> MAHASHANKH(Unit<Q> unit) {
		return unit.prefix(lațcham);
	}
}
