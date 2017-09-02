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
package systems.uom.unicode;

import static tec.units.ri.unit.MetricPrefix.*;
import static tec.units.ri.unit.Units.CUBIC_METRE;
import static tec.units.ri.unit.Units.METRE;
import static tec.units.ri.unit.Units.SQUARE_METRE;
import static tec.units.ri.AbstractUnit.ONE;

import systems.uom.quantity.Concentration;
import systems.uom.quantity.Consumption;
import systems.uom.quantity.Information;
import systems.uom.quantity.InformationRate;
import tec.units.ri.*;
import tec.units.ri.format.SimpleUnitFormat;
import tec.units.ri.function.PiMultiplierConverter;
import tec.units.ri.function.RationalConverter;
import tec.units.ri.unit.AlternateUnit;
import tec.units.ri.unit.ProductUnit;
import tec.units.ri.unit.TransformedUnit;
import tec.units.ri.unit.Units;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.*;

/**
 * <p>
 * This class contains {@linkplain SI} and Non-SI units as defined in the
 * <a href="http//cldr.unicode.org/">Unicode CLDR Project</a>.
 * </p>
 *
 * <p>
 * Compatibility with {@linkplain SI} units has been given priority over strict
 * adherence to the standard. We have attempted to note every place where the
 * definitions in this class deviate from the CLDR standard, but such notes are
 * likely to be incomplete.
 * </p>
 * 
 * @noextend This class is not intended to be extended by clients.
 *
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @see <a href="http://cldr.unicode.org">Unicode CLDR</a>
 * @version 0.7, $Date: 2017-06-18 $
 */
public final class CLDR extends AbstractSystemOfUnits {

    /**
     * The singleton instance.
     */
    private static final CLDR INSTANCE = new CLDR();

    /**
     * Default constructor (prevents this class from being instantiated).
     */
    private CLDR() {
    }

    /**
     * Returns the singleton instance of this class.
     *
     * @return the CLDR system instance.
     */
    public static CLDR getInstance() {
	return INSTANCE;
    }

    ////////////
    // Length //
    ////////////
    /**
     * US name for {@link Units#METRE}. Constant for unit of length: meter
     * 
     * @stable ICU 53.
     */
    public static final Unit<Length> METER = addUnit(METRE);

    /**
     * Constant for unit of length: millimeter<br>
     * This is a <b>convenience method</b> for <code>MILLI(METER)</code>.
     * 
     * @stable ICU 53.
     */
    public static final Unit<Length> MILLIMETER = MILLI(METER);

    /**
     * Constant for unit of length: centimeter<br>
     * This is a <b>convenience method</b> for <code>CENTI(METER)</code>.
     * 
     * @stable ICU 53.
     */
    public static final Unit<Length> CENTIMETER = CENTI(METER);

    /**
     * Constant for unit of length: kilometer<br>
     * This is a <b>convenience method</b> for <code>KILO(METER)</code>.
     * 
     * @stable ICU 53
     */
    public static final Unit<Length> KILOMETER = KILO(METRE);

    /**
     * A unit of length equal to <code>0.3048 m</code> (standard name
     * <code>ft</code>).
     */
    public static final Unit<Length> FOOT = addUnit(METER.multiply(3048).divide(10000));

    /**
     * Constant for unit of length: furlong<br>
     * A unit of length equal to <code>201.168 m</code> (standard name
     * <code>fur</code>).
     * 
     * @see <a href="https://en.wikipedia.org/wiki/Furlong">Wikipedia:
     *      Furlong</a>
     * @stable ICU 54.
     */
    public static final Unit<Length> FURLONG = addUnit(FOOT.multiply(660));

    /**
     * A unit of length equal to <code>0.9144 m</code> (standard name
     * <code>yd</code>).
     */
    public static final Unit<Length> YARD = addUnit(FOOT.multiply(3));

    /**
     * A unit of length equal to <code>0.0254 m</code> (standard name
     * <code>in</code>).
     */
    public static final Unit<Length> INCH = addUnit(FOOT.divide(12));

    /**
     * Constant for unit of length: mile<br>
     * A unit of length equal to <code>1609.344 m</code> (standard name
     * <code>mi</code>).
     * 
     * @stable ICU 53
     */
    public static final Unit<Length> MILE = addUnit(METER.multiply(1609344).divide(1000));

    /**
     * A unit of length equal to the average distance from the center of the
     * Earth to the center of the Sun (standard name <code>ua</code>).
     */
    public static final Unit<Length> ASTRONOMICAL_UNIT = addUnit(METRE.multiply(149597870691.0));

    /**
     * Constant for unit of length: fathom<br>
     * A unit of length equal to <code>1.8288 m</code> (standard name
     * <code>fm</code>).
     * 
     * @stable ICU 53.
     */
    public static final Unit<Length> FATHOM = addUnit(FOOT.multiply(6));

    /**
     * Constant for unit of length: mile-scandinavian
     * 
     * @draft ICU 56
     * @provisional This API might change or be removed in a future release.
     * @see <a href="https://en.wikipedia.org/wiki/Scandinavian_mile">Wikipedia:
     *      Scandinavian mile</a>
     */
    public static final Unit<Length> MILE_SCANDINAVIAN = addUnit(KILOMETER.multiply(10));

    /**
     * Constant for unit of duration: second
     * @stable ICU 4.0
     */
    public static final Unit<Time> SECOND = addUnit(Units.SECOND);

    /**
     * Constant for unit of duration: week<br>
     * A unit of duration equal to 7 {@link #DAY} (standard name <code>week</code>).
     * @stable ICU 4.0
     */
    public static final Unit<Time> WEEK = addUnit(Units.WEEK);
    
    /**
     * Constant for unit of angle: radian
     * 
     * @stable ICU 54
     */
    public static final Unit<Angle> RADIAN = addUnit(Units.RADIAN);

    /**
     * Constant for unit of angle: revolution<br>
     * A unit of angle equal to a full circle or <code>2<i>&pi;</i>
     * * {@link #RADIAN}</code> (standard name <code>rev</code>).
     * 
     * @draft ICU 56
     * @provisional This API might change or be removed in a future release.
     */
    public static final Unit<Angle> REVOLUTION_ANGLE = addUnit(RADIAN.multiply(2).multiply(Math.PI).asType(Angle.class),
	    "rev", true);

    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    public static final Unit<Temperature> KELVIN = addUnit(Units.KELVIN);

    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    private static final Unit<LuminousIntensity> CANDELA = addUnit(Units.CANDELA);

    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    public static final Unit<Dimensionless> PI = addUnit(ONE.transform(new PiMultiplierConverter()));
    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    public static final Unit<Dimensionless> PERCENT = addUnit(ONE.divide(100), "Percent", "%");

    ////////////////////
    // SI UNITS: CLDR //
    ////////////////////
    /**
     * We deviate slightly from the standard here, to maintain compatibility
     * with the existing SI units. In CLDR, the mole is no longer a base unit,
     * but is defined as <code>Unit.ONE.multiply(6.0221367E23)</code>.
     */
    private static final Unit<AmountOfSubstance> MOLE = addUnit(Units.MOLE);
    /**
     * We deviate slightly from the standard here, to maintain compatibility
     * with the existing SI units. In CLDR, the steradian is defined as
     * <code>RADIAN.pow(2)</code>.
     */
    public static final Unit<SolidAngle> STERADIAN = addUnit(Units.STERADIAN);

    public static final Unit<Frequency> HERTZ = addUnit(Units.HERTZ);
    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    public static final Unit<Force> NEWTON = addUnit(Units.NEWTON);
    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    public static final Unit<Pressure> PASCAL = addUnit(Units.PASCAL);

    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    // private static final Unit<Pressure> METER_OF_WATER_COLUMN =
    // KILO(PASCAL).multiply(980665).divide(100000);
    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    private static final Unit<Pressure> METER_OF_MERCURY_COLUMN = KILO(PASCAL).multiply(1333220).divide(10000);
    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    // private static final Unit<Pressure> INCH_OF_WATER_COLUMN =
    // new
    // ProductUnit<Pressure>(METER_OF_WATER_COLUMN.multiply(INCH).divide(METER));
    /**
     * Constant for unit of pressure: inch-hg
     * 
     * @stable ICU 53
     */
    public static final Unit<Pressure> INCH_HG = addUnit(
	    new ProductUnit<Pressure>(METER_OF_MERCURY_COLUMN.multiply(INCH).divide(METER)));

    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    public static final Unit<Energy> JOULE = addUnit(Units.JOULE);
    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    public static final Unit<Power> WATT = addUnit(Units.WATT);
    /**
     * We deviate slightly from the standard here, to maintain compatability
     * with the existing SI units. In CLDR, the ampere is defined as
     * <code>COULOMB.divide(SECOND)</code>.
     */
    public static final Unit<ElectricCurrent> AMPERE = addUnit(Units.AMPERE);

    /**
     * We deviate slightly from the standard here, to maintain compatibility
     * with the existing SI units. In CLDR, the volt is defined as
     * <code>JOULE.divide(COULOMB)</code>.
     */
    public static final Unit<ElectricPotential> VOLT = addUnit(Units.VOLT);

    /**
     * Constant for unit of electric: ohm
     * 
     * @stable ICU 54
     */
    public static final Unit<ElectricResistance> OHM = addUnit(Units.OHM);
    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    public static final Unit<Temperature> CELSIUS = addUnit(Units.CELSIUS);
    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    public static final Unit<ElectricInductance> HENRY = addUnit(Units.HENRY);

    /**
     * Constant for unit of light: lux
     * 
     * @stable ICU 54
     */
    public static final Unit<Illuminance> LUX = addUnit(Units.LUX);

    /////////////////////////////////////////////////////////////////
    // Units outside the SI that are accepted for use with the SI. //
    /////////////////////////////////////////////////////////////////

    /**
     * An angle unit accepted for use with SI units (standard name
     * <code>deg/code>).
     */
    static final Unit<Angle> DEGREE_ANGLE = new TransformedUnit<Angle>(RADIAN,
	    new PiMultiplierConverter().concatenate(new RationalConverter(1, 180)));

    /**
     * An angle unit accepted for use with SI units (standard name
     * <code>'/code>).
     */
    static final Unit<Angle> MINUTE_ANGLE = new TransformedUnit<Angle>(RADIAN,
	    new PiMultiplierConverter().concatenate(new RationalConverter(1, 180 * 60)));

    /**
     * An angle unit accepted for use with SI units (standard name
     * <code>''</code>).
     */
    static final Unit<Angle> SECOND_ANGLE = new TransformedUnit<Angle>(RADIAN,
	    new PiMultiplierConverter().concatenate(new RationalConverter(1, 180 * 60 * 60)));

    /**
     * We deviate slightly from the standard here, to maintain compatibility
     * with the existing NonSI units. In CLDR, the degree is defined as
     * <code>PI.multiply(RADIAN.divide(180))</code>.
     */
    public static final Unit<Angle> DEGREE = addUnit(DEGREE_ANGLE);

    /**
     * As per CLDR standard.
     */
    public static final Unit<Angle> ARC_MINUTE = addUnit(MINUTE_ANGLE);

    public static final Unit<Angle> ARC_SECOND = addUnit(SECOND_ANGLE);

    //////////
    // Area //
    //////////
    /**
     * Constant for unit of area: square-foot<br>
     * A unit of area (standard name <code>sft</code> ).
     * @stable ICU 53
     */
    public static final Unit<Area> SQUARE_FOOT = addUnit(new ProductUnit<Area>((AbstractUnit<?>) FOOT.multiply(FOOT)));

    /**
     * A unit of area equal to <code>100 m²</code> (standard name <code>a</code>
     * ).
     */
    private static final Unit<Area> ARE = SQUARE_METRE.multiply(100);

    /**
     * A unit of area equal to <code>100 {@link #ARE}</code> (standard name
     * <code>ha</code>).
     * 
     * @stable ICU 53.
     */
    public static final Unit<Area> HECTARE = addUnit(ARE.multiply(100)); // Exact.

    /**
     * The acre is a unit of area used in the imperial and U.S. customary
     * systems. It is equivalent to <code>43,560 square feet</code>. An acre is
     * about 40% of a <code>HECTARE</code> – slightly smaller than an American
     * football field. (standard name <code>ac</code> ).
     * 
     * @see <a href="http://en.wikipedia.org/wiki/Acre">Wikipedia: Acre</a>
     */
    public static final Unit<Area> ACRE = addUnit(SQUARE_FOOT.multiply(43560));

    /**
     * Constant for unit of area: square-inch
     * 
     * @stable ICU 54
     */
    public static final Unit<Area> SQUARE_INCH = addUnit(
	    new ProductUnit<Area>(INCH.pow(2)));
    
    /**
     * Constant for unit of area: square-yard
     * @stable ICU 54
     */
    public static final Unit<Area> SQUARE_YARD = addUnit(
	    new ProductUnit<Area>(YARD.pow(2)));

    ////////////
    // Volume //
    ////////////
    /**
     * Constant for unit of volume: liter<br>
     * A unit of volume equal to one cubic decimeter (default label
     * <code>L</code>, also recognized <code>µL, mL, cL, dL</code>).
     * 
     * @stable ICU 53
     */
    public static final Unit<Volume> LITER = new TransformedUnit<Volume>(CUBIC_METRE, new RationalConverter(1, 1000));
    // private static final Unit<Volume> LITRE = addUnit(Units.LITRE);

    /**
     * Constant for unit of volume: cubic-meter (<code>m³</code>).
     * 
     * @stable ICU 54.
     */
    public static final Unit<Volume> CUBIC_METER = addUnit(CUBIC_METRE);

    /**
     * A unit of volume equal to one cubic inch (<code>in³</code>).
     */
    public static final Unit<Volume> CUBIC_INCH = addUnit(INCH.pow(3).asType(Volume.class));

    /**
     * Constant for unit of volume: gallon A unit of volume equal to one US
     * gallon, Liquid Unit. The U.S. liquid gallon is based on the Queen Anne or
     * Wine gallon occupying 231 cubic inches (standard name <code>gal</code>).
     * 
     * @stable ICU 54.
     */
    public static final Unit<Volume> GALLON = addUnit(CUBIC_INCH.multiply(231));

    /**
     * Constant for unit of volume: gallon-imperial
     * 
     * @stable ICU 57.
     */
    public static final Unit<Volume> GALLON_IMPERIAL = addUnit(LITER.multiply(454609).divide(100000));

    /**
     * Constant for unit of volume: cubic-foot<br>
     * The cubic foot is an imperial and US customary (non-metric) unit of
     * volume, used in the United States, Canada, and the United Kingdom. It is
     * defined as the volume of a cube with sides of one foot (0.3048 m) in
     * length. Its volume is 28.3168 liters or about 1⁄35 of a cubic meter. (
     * <code>ft³</code>).
     * 
     * @stable ICU 54.
     */
    public static final Unit<Volume> CUBIC_FOOT = addUnit(CUBIC_INCH.multiply(1728).asType(Volume.class));

    /**
     * Constant for unit of volume: cubic-mile<br>
     * A unit of volume equal to one cubic-mile (<code>cu mi</code>).
     * 
     * @stable ICU 53.
     */
    public static final Unit<Volume> CUBIC_MILE = addUnit(CUBIC_FOOT.multiply(147197952000L));

    /**
     * Constant for unit of volume: cubic-yard<br>
     * A unit of volume equal to one cubic-yard (<code>cyd.</code>).
     * 
     * @stable ICU 54.
     */
    public static final Unit<Volume> CUBIC_YARD = addUnit(CUBIC_FOOT.multiply(27));

    /**
     * A unit of volume equal to <code>1 / 128 {@link #GALLON_LIQUID}</code>
     * (standard name <code>oz_fl</code>).
     */
    public static final Unit<Volume> FLUID_OUNCE = addUnit(GALLON.divide(128));

    /**
     * An acre-foot is a unit of volume commonly used in the United States in
     * reference to large-scale water resources, such as reservoirs, aqueducts,
     * canals, sewer flow capacity, irrigation water, and river flows.
     */
    public static final Unit<Volume> ACRE_FOOT = addUnit(CUBIC_FOOT.multiply(43560));

    /**
     * Constant for unit of volume: bushel
     */
    public static final Unit<Volume> BUSHEL = addUnit(CUBIC_INCH.multiply(215042).divide(100));

    /**
     * Constant for unit of volume: cup<br>
     * The cup is a unit of measurement for volume, used in cooking to measure
     * liquids (fluid measurement) and bulk foods such as granulated sugar (dry
     * measurement). A cup is equal to <code>8 {@link #FLUID_OUNCE}</code>
     * (standard name <code>cup</code>).
     * 
     * @stable ICU 54.
     */
    public static final Unit<Volume> CUP = addUnit(FLUID_OUNCE.multiply(8));

    /**
     * Constant for unit of volume: cup-metric
     * 
     * @see <a href=
     *      "https://en.wikipedia.org/wiki/Cup_(unit)#Metric_cup">Wikipedia:
     *      Metric cup</a>
     * @stable ICU 56.
     */
    public static final Unit<Volume> CUP_METRIC = addUnit(MILLI(LITER).multiply(250));

    /**
     * Constant for unit of volume: pint<br>
     * A unit of volume equal to <code>20 {@link #FLUID_OUNCE}</code> (standard
     * name <code>pt</code>).
     * 
     * @stable ICU 54
     */
    public static final Unit<Volume> PINT = addUnit(FLUID_OUNCE.multiply(20), "Pint", "pt", true);

    /**
     * Constant for unit of volume: pint-metric
     * 
     * @draft ICU 56
     * @provisional This API might change or be removed in a future release.
     */
    public static final Unit<Volume> PINT_METRIC = addUnit(MILLI(LITER).multiply(500), "Metric Pint", "metr. pt", true);

    /**
     * Constant for unit of volume: quart<br>
     * A unit of volume equal to <code>40 {@link #FLUID_OUNCE}</code> (standard
     * name <code>qt</code>).
     * 
     * @stable ICU 54
     */
    public static final Unit<Volume> QUART = addUnit(FLUID_OUNCE.multiply(40), "Quart", "qt");

    /**
     * A unit of volume <code>~ 1 drop or 0.95 grain of water </code> (standard
     * name <code>min</code>).
     */
    private static final Unit<Volume> MINIM = MICRO(LITER).multiply(61.61152d);

    /**
     * Constant for unit of volume: teaspoon<br>
     * A unit of volume equal to <code>80 {@link #MINIM}</code> (standard name
     * <code>tsp</code>).
     * @stable ICU 54
     */
    public static final Unit<Volume> TEASPOON = addUnit(MINIM.multiply(80));

    /**
     * Constant for unit of volume: tablespoon<br>
     * A unit of volume equal to <code>3 {@link #TEASPOON}</code> (standard name
     * <code>Tbsp</code>).
     * @stable ICU 54
     */
    public static final Unit<Volume> TABLESPOON = addUnit(TEASPOON.multiply(3));

    //////////
    // Time //
    //////////
    /**
     * Constant for unit of duration: minute
     * 
     * @stable ICU 4.0
     */
    public static final Unit<Time> MINUTE = addUnit(Units.MINUTE);
    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    public static final Unit<Time> HOUR = addUnit(Units.HOUR);
    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    public static final Unit<Time> DAY = addUnit(Units.DAY);

    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    static final Unit<Time> YEAR_JULIAN = addUnit(Units.DAY.multiply(365.25));

    /**
     * Constant for unit of duration: year<br>
     * A time unit accepted for use with SI units (standard name <code>y</code> ).
     * @stable ICU 4.0
     */
    public static final Unit<Time> YEAR = addUnit(Units.DAY.multiply(365.25));

    /**
     * Constant for unit of duration: month
     * 
     * @stable ICU 4.0
     */
    public static final Unit<Time> MONTH = addUnit(YEAR_JULIAN.divide(12));

    /**
     * Constant for unit of time: century
     * 
     * @see <a href="http://www.aqua-calc.com/what-is/time/century">What Is
     *      century?</a>
     * @stable ICU 56.
     */
    public static final Unit<Time> CENTURY = addUnit(YEAR.multiply(100));

    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    private static final Unit<Pressure> BAR = addUnit(Units.PASCAL.multiply(100000));
    public static final Unit<Mass> GRAM = addUnit(Units.GRAM);

    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    public static final Unit<Mass> TONNE = addUnit(Units.KILOGRAM.multiply(1000));

    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    private static final Unit<Mass> GRAIN = MILLI(GRAM).multiply(6479891).divide(100000);
    /**
     * Constant for unit of mass: pound
     * 
     * @stable ICU 53
     */
    public static final Unit<Mass> POUND = addUnit(GRAIN.multiply(7000));

    /**
     * Constant for unit of mass: ounce<br>
     * A unit of mass equal to <code>1 / 16 {@link #POUND}</code> (standard name
     * <code>oz</code>).
     * 
     * @stable ICU 53
     */
    public static final Unit<Mass> OUNCE = addUnit(POUND.divide(16), "oz", true);

    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    private static final Unit<Mass> PENNYWEIGHT_TROY = GRAIN.multiply(24);
    /**
     * Constant for unit of mass: ounce-troy
     * 
     * @stable ICU 54
     */
    public static final Unit<Mass> OUNCE_TROY = addUnit(PENNYWEIGHT_TROY.multiply(20));
    
    /**
     * Constant for unit of area: square-yard
     * @stable ICU 54
     */
    public static final Unit<Mass> STONE = addUnit(POUND.multiply(14));
    
    ///////////////////
    // NATURAL UNITS //
    ///////////////////
    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    static final Unit<Speed> C = addUnit(Units.METRE_PER_SECOND.multiply(299792458));
    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    // public static final Unit<Action> PLANCK = addUnit(SI.JOULE_SECOND
    // .multiply(6.6260755E-24)); // FIXME get rid of JXQ import (where
    // from??) */

    /**
     * Constant for unit of acceleration: g-force
     * 
     * @stable ICU 53.
     */
    public static final Unit<Acceleration> G_FORCE = addUnit(Units.METRE_PER_SQUARE_SECOND.multiply(9.80665));

    ////////////
    // Length //
    ////////////
    /**
     * Constant for unit of length: light-year<br>
     * A unit of length equal to the distance that light travels in one year
     * through a vacuum (standard name <code>ly</code>).
     * 
     * @stable ICU 53
     */
    public static final Unit<Length> LIGHT_YEAR = addUnit(new ProductUnit<Length>(C.multiply(YEAR_JULIAN)));
    /**
     * A unit of length equal to the distance that light travels in one year
     * through a vacuum (standard name <code>ly</code>).
     */
    // static final Unit<Length> LIGHT_YEAR = addUnit(METRE
    // .multiply(9.460528405e15));

    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    static final Unit<Length> INCH_INTERNATIONAL = addUnit(CENTI(METRE).multiply(254).divide(100));
    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    static final Unit<Length> FOOT_INTERNATIONAL = addUnit(INCH_INTERNATIONAL.multiply(12));
    /**
     * Constant for unit of length: nautical-mile
     * 
     * @stable ICU 54
     */
    public static final Unit<Length> NAUTICAL_MILE = addUnit(METRE.multiply(1852));

    /**
     * Constant for unit of speed: knot
     * 
     * @draft ICU 56
     * @provisional This API might change or be removed in a future release.
     */
    public static final Unit<Speed> KNOT = addUnit(new ProductUnit<Speed>(NAUTICAL_MILE.divide(HOUR)));

    /**
     * Constant for unit of speed: meter-per-second<br>
     * The unit for speed (standard name <code>m/s</code>).
     * 
     * @stable ICU 53
     */
    public static final Unit<Speed> METER_PER_SECOND = addUnit(new ProductUnit<Speed>(METER.divide(SECOND)),
	    Speed.class);

    /**
     * Constant for unit of speed: mile-per-hour<br>
     * A unit of velocity expressing the number of international {@link #MILE
     * miles} per {@link #HOUR hour} (abbreviation <code>mph</code>).
     * 
     * @stable ICU 53
     */
    public static final Unit<Speed> MILE_PER_HOUR = addUnit(MILE.divide(HOUR).asType(Speed.class), "Mile per hour",
	    "mph");

    /**
     * Constant for unit of speed: mile-per-hour<br>
     * The unit for acceleration (standard name <code>m/s2</code> ).
     * 
     * @stable ICU 53
     */
    public static final Unit<Acceleration> METER_PER_SECOND_SQUARED = addUnit(
	    new ProductUnit<Acceleration>(METER_PER_SECOND.divide(SECOND)), Acceleration.class);

    /**
     * Constant for unit of mass: carat<br>
     * Carat (mass) is a unit of {@link Mass} for gems. It is equal to 0.2 gram
     * (standard name <code>ct</code>).</br>
     * In the United States, carat almost exclusively means the unit of mass.
     * 
     * @see <a href="https://en.wikipedia.org/wiki/Carat_(mass)">Wikipedia:
     *      Carat (mass)</a>
     *
     * @stable ICU 54
     */
    public static final Unit<Mass> CARAT = addUnit(GRAM.divide(5));
    // public static final Unit<Mass> CARAT = addUnit((KILOGRAM.divide(5000)));

    /**
     * Constant for unit of proportion: karat
     * 
     * @stable ICU 54
     */
    public static final Unit<Dimensionless> KARAT = addUnit(ONE.divide(24));

    private static final Unit<Force> POUND_FORCE = addUnit(POUND.multiply(G_FORCE).asType(Force.class));

    /**
     * Constant for unit of length: parsec<br>
     * A unit of length equal to the distance at which a star would appear to
     * shift its position by one arcsecond over the course the time (about 3
     * months) in which the Earth moves a distance of {@link #ASTRONOMICAL_UNIT}
     * in the direction perpendicular to the direction to the star (standard
     * name <code>pc</code>).
     * 
     * @stable ICU 54.
     */
    public static final Unit<Length> PARSEC = addUnit(METRE.multiply(30856770e9));

    ///////////////////////////////
    // TYPESETTER'S LENGTH UNITS //
    ///////////////////////////////
    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    static final Unit<Length> LINE = addUnit(INCH_INTERNATIONAL.divide(12));
    /**
     * A unit of length equal to <code>0.013837 {@link #INCH}</code> exactly
     * (standard name <code>pt</code>).
     * 
     * @see #PIXEL
     */
    /*
     * public static final Unit<Length> POINT = addUnit(LINE.divide(6)); //
     * static final Unit<Length> POINT = addUnit(INCH.multiply(13837) //
     * .divide(1000000));
     * 
     * /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. public
     * static final Unit<Length> PICA = addUnit(POINT.multiply(12)); /** As per
     * <a href="http//cldr.unicode.org/">CLDR</a> standard. public static final
     * Unit<Length> POINT_PRINTER =
     * addUnit(INCH_INTERNATIONAL.multiply(13837).divide(1000000)); /** As per
     * <a href="http//cldr.unicode.org/">CLDR</a> standard. public static final
     * Unit<Length> PICA_PRINTER = addUnit(POINT_PRINTER.multiply(12));
     */
    //////////////////////////////
    // OTHER LEGACY UNITS: CLDR //
    //////////////////////////////
    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    public static final Unit<Temperature> FAHRENHEIT = addUnit(KELVIN.multiply(5).divide(9).shift(459.67));

    /**
     * Constant for unit of energy: foodcalorie.
     * 
     * @see <a href=
     *      "http://www.convertunits.com/info/calorie+[nutritional]">Calorie
     *      (nutritional)</a>
     * 
     * @stable ICU 54
     */
    public static final Unit<Energy> FOODCALORIE = addUnit(JOULE.multiply(4186.8));

    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    private static final Unit<Energy> CALORIE_THERMOCHEMICAL = addUnit(JOULE.multiply(4184).divide(1000));
    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    public static final Unit<Energy> CALORIE = addUnit(CALORIE_THERMOCHEMICAL);
    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    // private static final Unit<Energy> CALORIE_FOOD =
    // addUnit(KILO(CALORIE_THERMOCHEMICAL));

    /**
     * Constant for unit of power: horsepower
     * 
     * @stable ICU 53
     */
    public static final Unit<Power> HORSEPOWER = addUnit(
	    new ProductUnit<Power>(FOOT_INTERNATIONAL.multiply(POUND_FORCE).divide(SECOND)));

    /**
     * Constant for unit of pressure: pound-per-square-inch
     * 
     * @stable ICU 54
     */
    public static final Unit<Pressure> POUND_PER_SQUARE_INCH = addUnit(
	    new ProductUnit<Pressure>(POUND_FORCE.divide(INCH_INTERNATIONAL.pow(2))));
    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    // private static final Unit<Angle> CIRCLE = new
    // ProductUnit<Angle>(PI.multiply(RADIAN.multiply(2)));

    /** As per <a href="http//cldr.unicode.org/">CLDR</a> standard. */
    public static final Unit<SolidAngle> SPHERE = addUnit(
	    new ProductUnit<SolidAngle>(PI.multiply(STERADIAN.multiply(4))));

    /**
     * The unit for binary information (standard name <code>bit</code>).
     * 
     * @stable ICU 54
     */
    public static final Unit<Information> BIT = addUnit(new AlternateUnit<Information>(ONE, "bit"), "Bit", "bit",
	    Information.class);

    /**
     * A unit of data amount equal to <code>8 {@link #BIT}</code> (BinarY TErm,
     * standard name <code>byte</code>).
     */
    public static final Unit<Information> BYTE = addUnit(BIT.multiply(8), "Byte", "byte");

    /**
     * The unit for binary information rate (standard name <code>bit/s</code>).
     * 
     * @draft Non-ICU
     */
    static final Unit<InformationRate> BIT_PER_SECOND = addUnit(new ProductUnit<InformationRate>(BIT.divide(SECOND)),
	    InformationRate.class);

    /**
     * Equivalent {@link #BYTE}
     */
    static final Unit<Information> OCTET = BYTE;

    /**
     * A unit used to measure the frequency (rate) at which an imaging device
     * produces unique consecutive images (standard name <code>fps</code>).
     * 
     * @draft Non-ICU
     */
    static final Unit<Frequency> FRAME_PER_SECOND = ONE.divide(SECOND).asType(Frequency.class);

    ///////////////////
    // Concentration //
    ///////////////////

    /**
     * Constant for unit of concentr: milligram-per-deciliter
     * 
     * @stable ICU 57
     */
    @SuppressWarnings("unchecked")
    public static final Unit<Concentration<Mass>> MILLIGRAM_PER_DECILITER = addUnit(
	    MILLI(GRAM).divide(DECI(LITER)).asType(Concentration.class));

    ///////////////////
    // Consumption //
    ///////////////////

    /**
     * Constant for unit of consumption: liter-per-100kilometers
     * 
     * @draft ICU 56
     * @provisional This API might change or be removed in a future release.
     */
    @SuppressWarnings("unchecked")
    public static final Unit<Consumption<Volume>> LITER_PER_100KILOMETERS = addUnit(
	    (KILOMETER.multiply(100)).divide(LITER).asType(Consumption.class));;

    /**
     * Constant for unit of consumption: liter-per-kilometer
     * 
     * @stable ICU 54
     */
    @SuppressWarnings("unchecked")
    public static final Unit<Consumption<Volume>> LITER_PER_KILOMETER = addUnit(
	    KILOMETER.divide(LITER).asType(Consumption.class));

    /**
     * Constant for unit of consumption: mile-per-gallon
     * 
     * @stable ICU 54
     */
    @SuppressWarnings("unchecked")
    public static final Unit<Consumption<Volume>> MILE_PER_GALLON = addUnit(
	    MILE.divide(GALLON).asType(Consumption.class));

    /////////////////////
    // Collection View //
    /////////////////////

    @Override
    public String getName() {
	return "Unicode CLDR";
    }

    /**
     * Adds a new unit not mapped to any specified quantity type.
     *
     * @param unit
     *            the unit being added.
     * @return <code>unit</code>.
     */
    private static <U extends Unit<Q>, Q extends Quantity<Q>> U addUnit(U unit) {
	INSTANCE.units.add(unit);
	return unit;
    }

    /**
     * Adds a new unit and maps it to the specified quantity type.
     *
     * @param unit
     *            the unit being added.
     * @param type
     *            the quantity type.
     * @return <code>unit</code>.
     */
    private static <U extends Unit<?>> U addUnit(U unit, Class<? extends Quantity<?>> type) {
	INSTANCE.units.add(unit);
	INSTANCE.quantityToUnit.put(type, unit);
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
     * @param text
     *            the string to use as label or symbol
     * @param isLabel
     *            if the string should be used as a label or not
     * @return <code>unit</code>.
     */
    private static <U extends Unit<?>> U addUnit(U unit, String name, String text) {
	return addUnit(unit, name, text, true);
    }

    /**
     * Adds a new unit and maps it to the specified quantity type.
     *
     * @param unit
     *            the unit being added.
     * @param type
     *            the quantity type.
     * @return <code>unit</code>.
     */
    private static <U extends AbstractUnit<?>> U addUnit(U unit, String name, String label,
	    Class<? extends Quantity<?>> type) {
	INSTANCE.quantityToUnit.put(type, unit);
	return addUnit(unit, name, label);
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

    // //////////////////////////////////////////////////////////////////////////
    // Label adjustments for CLDR system
    static {
	SimpleUnitFormat.getInstance().alias(BYTE, "B");
	SimpleUnitFormat.getInstance().label(KARAT, "kt");
	SimpleUnitFormat.getInstance().label(CARAT, "ct");
	SimpleUnitFormat.getInstance().label(POUND, "lb");
	SimpleUnitFormat.getInstance().label(BAR, "b");
	SimpleUnitFormat.getInstance().label(PARSEC, "pc");
	SimpleUnitFormat.getInstance().label(SQUARE_FOOT, "sft");
    }
}
