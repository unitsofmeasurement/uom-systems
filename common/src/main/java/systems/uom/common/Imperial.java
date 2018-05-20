/*
 * Units of Measurement Systems
 * Copyright (c) 2005-2018, Jean-Marie Dautelle, Werner Keil and others.
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

import static tec.units.indriya.unit.MetricPrefix.MICRO;
import static tec.units.indriya.unit.Units.*;

import javax.measure.Unit;
import javax.measure.quantity.Area;
import javax.measure.quantity.Force;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Temperature;
import javax.measure.quantity.Time;
import javax.measure.quantity.Volume;
import javax.measure.spi.SystemOfUnits;

import tec.units.indriya.AbstractSystemOfUnits;
import tec.units.indriya.AbstractUnit;
import tec.units.indriya.format.SimpleUnitFormat;
import tec.units.indriya.unit.ProductUnit;

/**
 * <p>
 * This class contains units from the Imperial system.
 * </p>
 * <p>
 * 
 * @noextend This class is not intended to be extended by clients.
 * 
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 1.0.4, $Date: 2018-05-02 $
 * @see <a href="http://en.wikipedia.org/wiki/Imperial_unit">Wikipedia: Imperial
 *      Units</a>
 * @see <a href=
 *      "https://en.wikipedia.org/wiki/Imperial_and_US_customary_measurement_systems">
 * @since 0.2
 */
public final class Imperial extends AbstractSystemOfUnits {
    private static final String SYSTEM_NAME = "Imperial";
    
    /**
     * Holds the avoirdupois pound: 0.45359237 kg exact
     */
    static final int AVOIRDUPOIS_POUND_DIVIDEND = 45359237;

    static final int AVOIRDUPOIS_POUND_DIVISOR = 100000000;
    
    /**
     * Holds the standard gravity constant: 9.80665 m/sÂ² exact.
     */
    private static final int STANDARD_GRAVITY_DIVIDEND = 980665;

    private static final int STANDARD_GRAVITY_DIVISOR = 100000;

    /**
     * Default constructor (prevents this class from being instantiated).
     */
    private Imperial() {
    }

    /**
     * Returns the unique instance of this class.
     * 
     * @return the Imperial instance.
     */
    public static SystemOfUnits getInstance() {
	return INSTANCE;
    }

    private static final Imperial INSTANCE = new Imperial();

    // //////////
    // Length //
    // //////////

    /**
     * A unit of length equal to <code>0.0254 m</code> (standard name
     * <code>in</code>).
     */
    public static final Unit<Length> INCH = addUnit(USCustomary.INCH, "Inch", "in");

    // ////////
    // Mass //
    // ////////

    /**
     * A unit of mass equal to <code>453.59237 grams</code> (avoirdupois pound,
     * standard name <code>lb</code>).
     */
    public static final Unit<Mass> POUND = addUnit(
	    KILOGRAM.multiply(AVOIRDUPOIS_POUND_DIVIDEND).divide(AVOIRDUPOIS_POUND_DIVISOR), "Pound", "lb", true);
    // LABEL);
    /**
     * An English and imperial unit of weight or mass now equal to 14
     * avoirdupois pounds or 6.35029318 kg (<code>st</code>).
     */
    public static final Unit<Mass> STONE = addUnit(KILOGRAM.multiply(6.35029318), "st", true);

    /**
     * A unit of mass equal to <code>1 / 16 {@link #POUND}</code> (standard name
     * <code>oz</code>).
     */
    public static final Unit<Mass> OUNCE = addUnit(POUND.divide(16), "oz");

    /**
     * A unit of mass equal to <code>2240 {@link #POUND}</code> (long ton,
     * standard name <code>ton_uk</code>).
     */
    public static final Unit<Mass> TON_UK = addUnit(POUND.multiply(2240), "ton_uk");

    /**
     * A unit of mass equal to <code>1000 kg</code> (metric ton, standard name
     * <code>t</code>).
     */
    public static final Unit<Mass> METRIC_TON = addUnit(KILOGRAM.multiply(1000), "t");

    // ///////////////
    // Temperature //
    // ///////////////

    /**
     * A unit of temperature equal to <code>5/9 °K</code> (standard name
     * <code>°R</code>).
     */
    static final Unit<Temperature> RANKINE = addUnit(KELVIN.multiply(5).divide(9), "°R", true);

    /**
     * A unit of temperature equal to degree Rankine minus
     * <code>459.67 °R</code> (standard name <code>°F</code>).
     * 
     * @see #RANKINE
     */
    static final Unit<Temperature> FAHRENHEIT = addUnit(RANKINE.shift(459.67), "°F", true);

    //////////////
    // Time     //
    //////////////
    /**
     * A unit of time equal to <code>60 s</code> (standard name <code>min</code>
     * ).
     */
    static final Unit<Time> MINUTE = addUnit(SECOND.multiply(60));

    /**
     * A unit of duration equal to <code>60 {@link #MINUTE}</code> (standard
     * name <code>h</code>).
     */
    static final Unit<Time> HOUR = addUnit(MINUTE.multiply(60));
    
    //////////
    // Area //
    //////////

    /**
     * A unit of area (standard name <code>sft</code> ).
     */
    public static final Unit<Area> SQUARE_FOOT = addUnit(USCustomary.SQUARE_FOOT, "sft", true);

    /**
     * One acre is 43,560 <code>square feet</code> (standard name
     * <code>ac</code> ).
     */
    public static final Unit<Area> ACRE = addUnit(USCustomary.SQUARE_FOOT.multiply(43560), "Acre", "ac", true);

    ////////////
    // Volume //
    ////////////
    /**
     * A unit of volume equal to one cubic decimeter (default label
     * <code>L</code>, also recognized <code>ÂµL, mL, cL, dL</code>).
     */
    public static final Unit<Volume> LITRE = addUnit(CUBIC_METRE.divide(1000), "L", true);

    /**
     * A unit of volume equal to one cubic inch (<code>in³</code>).
     */
    public static final Unit<Volume> CUBIC_INCH = addUnit(new ProductUnit<Volume>(USCustomary.INCH.pow(3)),
	    "Cubic Inch", "in³");

    /**
     * A unit of volume equal to <code>4.546 09 {@link #LITRE}</code> (standard
     * name <code>gal_uk</code>).
     */
    public static final Unit<Volume> GALLON_UK = addUnit(LITRE.multiply(454609).divide(100000), "gal_uk");

    /**
     * A unit of volume equal to one UK gallon, Liquid Unit.
     */
    // public static final Unit<Volume> GALLON_LIQUID =
    // addUnit(CUBIC_INCH.multiply(277.42));

    /**
     * A unit of volume equal to <code>1 / 160 {@link #GALLON_UK}</code>
     * (standard name <code>fl_oz_uk</code>).
     */
    static final Unit<Volume> FLUID_OUNCE_UK = GALLON_UK.divide(160);

    /**
     * A unit of volume equal to <code>1 / 160 {@link #GALLON_LIQUID}</code>
     * (standard name <code>fl_oz</code>).
     */
    public static final Unit<Volume> FLUID_OUNCE = addUnit(FLUID_OUNCE_UK, "fl_oz", true);

    /**
     * A unit of volume equal to <code>1 / 160 {@link #GALLON_LIQUID}</code>
     * (standard name <code>fl_oz</code>).
     * @deprecated use FLUID_OUNCE
     */
    public static final Unit<Volume> OUNCE_LIQUID = FLUID_OUNCE_UK;

    /**
     * A unit of volume equal to <code>5 {@link #FLUID_OUNCE}</code> (standard
     * name <code>gi</code>).
     */
    public static final Unit<Volume> GILL = addUnit(FLUID_OUNCE.multiply(5), "Gill", "gi");

    /**
     * A unit of volume equal to <code>20 {@link #FLUID_OUNCE}</code> (standard
     * name <code>pt</code>).
     */
    public static final Unit<Volume> PINT = addUnit(FLUID_OUNCE.multiply(20), "Pint", "pt", true);

    /**
     * A unit of volume equal to <code>40 {@link #FLUID_OUNCE}</code> (standard
     * name <code>qt</code>).
     */
    public static final Unit<Volume> QUART = addUnit(FLUID_OUNCE.multiply(40), "Quart", "qt");

    /**
     * A unit of volume <code>~ 1 drop or 0.95 grain of water </code> (standard
     * name <code>min</code>).
     */
    public static final Unit<Volume> MINIM = addUnit(MICRO(LITRE).multiply(59.1938802d), "Minim", "min_br");

    /**
     * A unit of volume equal to <code>20 {@link #MINIM}</code> (standard name
     * <code>fl scr</code>).
     */
    public static final Unit<Volume> FLUID_SCRUPLE = addUnit(MINIM.multiply(60), "fl scr", true);

    /**
     * A unit of volume equal to <code>3 {@link #FLUID_SCRUPLE}</code> (standard
     * name <code>fl drc</code>).
     */
    public static final Unit<Volume> FLUID_DRACHM = addUnit(FLUID_SCRUPLE.multiply(3), "fl drc", true);
    
    /**
     * A unit of force equal to <code>{@link #POUND}Â·{@link #G}</code>
     * (standard name <code>lbf</code>).
     */
    public static final Unit<Force> POUND_FORCE = addUnit(
	    NEWTON.multiply(1L * AVOIRDUPOIS_POUND_DIVIDEND * STANDARD_GRAVITY_DIVIDEND)
		    .divide(1L * AVOIRDUPOIS_POUND_DIVISOR * STANDARD_GRAVITY_DIVISOR), "lbf");
    /**
     * A unit of force equal to <code>9.80665 N</code> (standard name
     * <code>kgf</code>).
     */
    static final Unit<Force> KILOGRAM_FORCE = addUnit(
	    NEWTON.multiply(STANDARD_GRAVITY_DIVIDEND).divide(STANDARD_GRAVITY_DIVISOR));

    
    /**
     * Adds a new unit not mapped to any specified quantity type.
     *
     * @param unit
     *            the unit being added.
     * @return <code>unit</code>.
     */
    private static <U extends Unit<?>> U addUnit(U unit) {
        INSTANCE.units.add(unit);
        return unit;
    }

    /**
     * Adds a new unit not mapped to any specified quantity type and puts a text
     * as symbol or label.
     *
     * @param unit
     *            the unit being added.
     * @param name
     *            the string to use as name
     * @param text
     *            the string to use as label or symbol
     * @param isLabel
     *            if the string should be used as a label or not
     * @return <code>unit</code>.
     */
    private static <U extends Unit<?>> U addUnit(U unit, String name, String text, boolean isLabel) {
    	if (isLabel) {
    	    SimpleUnitFormat.getInstance().label(unit, text);
    	}
    	if (name != null && unit instanceof AbstractUnit) {
    	    return Helper.addUnit(INSTANCE.units, unit, name);
    	} else {
    	    INSTANCE.units.add(unit);
    	}
    	return unit;
    }

    /**
     * Adds a new unit not mapped to any specified quantity type and puts a text
     * as symbol or label.
     *
     * @param unit
     *            the unit being added.
     * @param name
     *            the string to use as name
     * @param label
     *            the string to use as label
     * @return <code>unit</code>.
     */
    private static <U extends Unit<?>> U addUnit(U unit, String name, String label) {
        return addUnit(unit, name, label, true);
    }

    /**
     * Adds a new unit not mapped to any specified quantity type and puts a text
     * as symbol or label.
     *
     * @param unit
     *            the unit being added.
     * @param text
     *            the string to use as label or symbol
     * @param isLabel
     *            if the string should be used as a label or not
     * @return <code>unit</code>.
     */
    private static <U extends Unit<?>> U addUnit(U unit, String text, boolean isLabel) {
        return addUnit(unit, null, text, isLabel);
    }

    /**
     * Adds a new unit not mapped to any specified quantity type and puts a text
     * as label.
     *
     * @param unit
     *            the unit being added.
     * @param text
     *            the string to use as label or symbol
     * @return <code>unit</code>.
     */
    private static <U extends Unit<?>> U addUnit(U unit, String text) {
        return addUnit(unit, null, text, true);
    }

    // ///////////////////
    // Collection View //
    // ///////////////////

    @Override
    public String getName() {
        return SYSTEM_NAME;
    }
}
