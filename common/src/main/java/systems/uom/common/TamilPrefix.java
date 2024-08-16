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

import javax.measure.Prefix;
import javax.measure.Quantity;
import javax.measure.Unit;

/**
 * Utility class holding Tamil-System prefixes used today in parts of India and Sri Lanka;
 * based on grouping by two decimal places, rather than the
 * three decimal places common in most parts of the world.<br>
 * <code>import static systems.uom.common.TamilPrefix.*; // Static import.<br>
 * {@literal Unit<Pressure>} KOTI_PASCAL = KOTI(PASCAL);<br> 
 * {@literal Unit<Length>} PATTUK_KOTI_METER = METER.prefix(PATTUK_KOTI);</code>
 * 
 * @author <a href="mailto:werner@uom.systems">Werner Keil</a>
 * @version 1.6, $Date: 2021-03-29 $
 * @see <a
 *      href="https://en.wikipedia.org/wiki/Tamil_numerals#Tamil-System">Wikipedia:
 *      Tamil numerals - Tamil-System</a>
 */
public enum TamilPrefix implements Prefix {
    /** Prefix for 10<sup>5</sup>. */
	ILATCAM("௱௲", "ilaṭcam", 10, 5),
    /** Prefix for 10<sup>6</sup>. */
	PATTU_ILATCAM("௲௲", "pattu ilaṭcam", 10, 6),
    /** Prefix for 10<sup>7</sup>. */
	KOTI("௰௲௲", "kōṭi", 10, 7),
    /** Prefix for 10<sup>8</sup>. */
	PATTUK_KOTI("௱௲௲", "pattuk kōṭi", 10, 8),
    /** Prefix for 10<sup>9</sup>. */
	ARPUTAM("௲௲௲", "aṟputam", 10, 9),
    /** Prefix for 10<sup>11</sup>. */
	NIKARPPUTAM("௲௲௲", "nikarpputam", 10, 11),
    /** Prefix for 10<sup>13</sup>. */
	KARVAM("௲௲௲௲", "karvam", 10, 13),
    /** Prefix for 10<sup>15</sup>. */
	SANKAM("௲௲௲௲௲", "śaṅkam", 10, 15),
    /** Prefix for 10<sup>17</sup>. */
	ARTTAM("௲௲௲௲௲௲", "arttam", 10, 17),
    /** Prefix for 10<sup>19</sup>. */
	PURIYAM("௱௲௲௲௲௲௲", "pūriyam", 10, 19),
    /** Prefix for 10<sup>21</sup>. */
	MUKKOTI("௲௲௲௲௲௲௲", "mukkoṭi", 10, 21),
    /** Prefix for 10<sup>25</sup>. */
	MAYUKAM("௰௲௲௲௲௲௲௲", "māyukam", 10, 25);
	
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
    private TamilPrefix(String symbol, String name, int base, int exponent) {
        this.symbol = symbol;
        this.name= name;
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

	/**
	 * <p>
	 * ௱௲ (ilaṭcam)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>5</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(1e5)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> ILATCAM(Unit<Q> unit) {
		return unit.prefix(ILATCAM);
	}
	
	/**
	 * <p>
	 * ௲௲ (pattu ilaṭcam)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>6</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(1e6)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> PATTU_ILATCAM(Unit<Q> unit) {
		return unit.prefix(PATTU_ILATCAM);
	}

	/**
	 * <p>
	 * ௰௲௲, (kōṭi)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>7</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(1e7)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> KOTI(Unit<Q> unit) {
		return unit.prefix(KOTI);
	}

	/**
	 * <p>
	 * பத்து கோடி (pathu kōdi)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>8</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(1e8)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> PATTUK_KOTI(Unit<Q> unit) {
		return unit.prefix(PATTUK_KOTI);
	}

	/**
	 * <p>
	 * ௲௲௲ (aṟputam)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>9</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(1e9)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> ARPUTAM(Unit<Q> unit) {
		return unit.prefix(ARPUTAM);
	}

	/**
	 * <p>
	 * ௲௲௲ (nikarpputam)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>11</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(1e11)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> NIKARPPUTAM(Unit<Q> unit) {
		return unit.prefix(NIKARPPUTAM);
	}

	/**
	 * <p>
	 * ௲௲௲௲ (karvam)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>13</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(1e13)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> KARVAM(Unit<Q> unit) {
		return unit.prefix(KARVAM);
	}

	/**
	 * <p>
	 * ௲௲௲௲௲ (śaṅkam)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>15</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(1e15)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> SANKAM(Unit<Q> unit) {
		return unit.prefix(SANKAM);
	}	
	
	/**
	 * <p>
	 * ௲௲௲௲௲௲ (arttam)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>17</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(1e17)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> ARTTAM(Unit<Q> unit) {
		return unit.prefix(ARTTAM);
	}
	
	/**
	 * <p>
	 * ௱௲௲௲௲௲௲ (pūriyam)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>19</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(1e19)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> PURIYAM(Unit<Q> unit) {
		return unit.prefix(PURIYAM);
	}
	
	/**
	 * <p>
	 * ௲௲௲௲௲௲௲ (mukkoṭi)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>21</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(1e21)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> MUKKOTI(Unit<Q> unit) {
		return unit.prefix(MUKKOTI);
	}
	
	/**
	 * <p>
	 * ௰௲௲௲௲௲௲௲ (māyukam)
	 * </p>
	 * Returns the specified unit multiplied by the factor
	 * <code>10<sup>25</sup></code>
	 * 
	 * @param unit
	 *            any unit.
	 * @return <code>unit.times(1e25)</code>.
	 */
	public static final <Q extends Quantity<Q>> Unit<Q> MAYUKAM(Unit<Q> unit) {
		return unit.prefix(MAYUKAM);
	}
}
