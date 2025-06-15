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
package systems.uom.common;

import javax.measure.Prefix;
import javax.measure.Quantity;
import javax.measure.Unit;

/**
 * Utility class holding prefixes used today in India, Pakistan, Bangladesh, Nepal
 * and Myanmar (Burma); based on grouping by two decimal places, rather than the
 * three decimal places common in most parts of the world. [code] import static
 * org.eclipse.uomo.units.IndianPrefix.*; // Static import. ... Unit<Pressure>
 * LAKH_PASCAL = LAKH(PASCAL);
 * Unit<Length>CRORE_METER = CRORE(METER); [/code]
 * 
 * @author <a href="mailto:werner@uom.systems">Werner Keil</a>
 * @version 2.0 $Date: 2019-06-19 $
 * @see <a
 *      href="http://en.wikipedia.org/wiki/Indian_numbering_system">Wikipedia: Indian numbering system</a>
 */
public enum IndianPrefix implements Prefix {
    /** Prefix for 10<sup>24</sup>. */
	EK("E", 1, 1),
    /** Prefix for 10<sup>21</sup>. */
	DAS("D", 10, 1),
    /** Prefix for 10<sup>18</sup>. */
	SAU("S", 10, 2),
    /** Prefix for 10<sup>15</sup>. */
	SAHASR("SA", 10, 3),
    /** Prefix for 10<sup>12</sup>. */
	LAKH("Lk", 10, 5),
    /** Prefix for 10<sup>9</sup>. */
	CRORE("cr", 10, 7),
    /** Prefix for 10<sup>6</sup>. */
	ARAWB("A", 10, 9),
    /** Prefix for 10<sup>3</sup>. */
	KHARAWB("K", 10, 11),
    /** Prefix for 10<sup>2</sup>. */
	NEEL("N", 10, 13),
    /** Prefix for 10<sup>1</sup>. */
	PADMA("Pa", 10, 15),
    /** Prefix for 10<sup>-1</sup>. */
	SHANKH("SH", 10, 17),
    /** Prefix for 10<sup>-2</sup>. */
	MAHASHANKH("M", 10, 19);
	
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
    private IndianPrefix(String symbol, int base, int exponent) {
        this.symbol = symbol;
        this.base = base;
        this.exponent = exponent;
    }
    
    /**
     * Base part of the associated factor in base^exponent representation. For metric prefix, this is always 10.
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
		return unit.prefix(DAS);
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
		return unit.prefix(SAU);
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
		return unit.prefix(SAHASR);
	}

	/**
	 * <p>
	 * हजार (Hazaar)
	 * </p>
	 * Equivalent to {@link #SAHASR}.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> HAZAAR(Unit<Q> unit) {
		return SAHASR(unit);
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
		return unit.prefix(LAKH);
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
		return unit.prefix(CRORE);
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
		return unit.prefix(ARAWB);
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
		return unit.prefix(KHARAWB);
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
		return unit.prefix(NEEL);
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
		return unit.prefix(PADMA);
	}

	/**
	 * <p>
	 * शंख (Shankh)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>17</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(1e17)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> SHANKH(Unit<Q> unit) {
		return unit.prefix(SHANKH);
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
		return unit.prefix(MAHASHANKH);
	}
}
