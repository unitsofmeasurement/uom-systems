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

import static tec.units.indriya.AbstractUnit.ONE;
import static tec.units.indriya.unit.MetricPrefix.CENTI;
import static tec.units.indriya.unit.Units.JOULE;
import static tec.units.indriya.unit.Units.METRE;
import static tec.units.indriya.unit.Units.NEWTON;
import static tec.units.indriya.unit.Units.PASCAL;

import javax.measure.Unit;
import javax.measure.quantity.Acceleration;
import javax.measure.quantity.Energy;
import javax.measure.quantity.Force;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Power;
import javax.measure.quantity.Pressure;
import javax.measure.quantity.Speed;
import javax.measure.quantity.Time;

import si.uom.quantity.DynamicViscosity;
import si.uom.quantity.KinematicViscosity;
import tec.units.indriya.AbstractSystemOfUnits;
import tec.units.indriya.AbstractUnit;
import tec.units.indriya.format.SimpleUnitFormat;
import tec.units.indriya.unit.ProductUnit;
import tec.units.indriya.unit.Units;

/**
 * <p>
 * This class contains the centimetre–gram–second system of units.
 * </p>
 * 
 * <p>
 * This class is not intended to be implemented by clients.
 * </p>
 * 
 * @noextend This class is not intended to be extended by clients.
 * 
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 0.5, $Date: 2017-03-05$
 * @see <a href=
 *      "https://en.wikipedia.org/wiki/Centimetre%E2%80%93gram%E2%80%93second_system_of_units">Wikipedia:
 *      Centimetre–gram–second system of units</a>
 * @since 0.6
 */
public final class CGS extends AbstractSystemOfUnits {
    private static final String SYSTEM_NAME = "Centimetre–gram–second System of Units";

    /**
     * Holds the avoirdupois pound: 0.45359237 kg exact
     */
    static final int AVOIRDUPOIS_POUND_DIVIDEND = 45359237;

    static final int AVOIRDUPOIS_POUND_DIVISOR = 100000000;

    /**
     * Default constructor (prevents this class from being instantiated).
     */
    private CGS() {
    }

    /**
     * Returns the unique instance of this class.
     * 
     * @return the NonSI instance.
     */
    public static CGS getInstance() {
	return INSTANCE;
    }

    private static final CGS INSTANCE = new CGS();

    ////////////
    // Length //
    ////////////

    /**
     * A unit of length equal to <code>1/100 of metre</code> (standard name
     * <code>cm</code>).
     */
    public static final Unit<Length> CENTIMETRE = addUnit(CENTI(METRE));

    //////////
    // Mass //
    //////////
    /**
     * A unit of mass equal to 1/12 the mass of the carbon-12 atom (standard
     * name <code>g</code>).
     */
    public static final Unit<Mass> GRAM = addUnit(Units.GRAM);

    //////////
    // Time //
    //////////
    /**
     * The SI base unit for duration quantities (standard name <code>s</code>).
     * It is defined as the duration of 9,192,631,770 cycles of radiation
     * corresponding to the transition between two hyperfine levels of the
     * ground state of cesium (1967 Standard).
     * 
     */
    public static final Unit<Time> SECOND = addUnit(Units.SECOND);

    //////////////
    // Velocity //
    //////////////
    /**
     * A unit of velocity (cgs unit, standard name <code>cm/s</code>.
     */
    public static final Unit<Speed> CENTIMETRE_PER_SECOND = addUnit(CENTIMETRE.divide(SECOND).asType(Speed.class),
	    "centimetre per second", "cm/s");

    //////////////////
    // Acceleration //
    //////////////////

    /**
     * A unit of acceleration (cgs unit, standard name <code>Gal</code>).
     * 
     * @see <a href="https://en.wikipedia.org/wiki/Gal_(unit)">Wikipedia:
     *      Gal</a>
     */
    public static final Unit<Acceleration> GAL = addUnit(
	    new ProductUnit<Acceleration>(CENTIMETRE_PER_SECOND.divide(SECOND)).asType(Acceleration.class), "Gal",
	    "Gal");

    ////////////
    // Energy //
    ////////////
    /**
     * A unit of energy equal to <code>1E-7 J</code> (standard name
     * <code>erg</code>).
     */
    public static final Unit<Energy> ERG = addUnit(JOULE.divide(10000000), "Erg", "erg");

    ///////////
    // Force //
    ///////////
    /**
     * A unit of force equal to <code>1E-5 N</code> (standard name
     * <code>dyn</code>).
     */
    public static final Unit<Force> DYNE = addUnit(NEWTON.divide(100000), "Dyne", "dyn");

    ///////////
    // Power //
    ///////////

    /**
     * A unit of power (cgs unit, standard name <code>erg/s</code>).
     */
    public static final Unit<Power> ERG_PER_SECOND = addUnit(ERG.divide(SECOND).asType(Power.class), "Erg per second",
	    "erg/s");

    //////////////
    // Pressure //
    //////////////

    /**
     * The barye (symbol: <code>Ba</code>), or sometimes barad, barrie, bary,
     * baryd, baryed, or barie, is the centimetre–gram–second (CGS) unit of
     * pressure. It is equal to 1 dyne per square centimetre.
     * <p>
     * <code>1 Ba = 0.1 Pa = 1×10−6 bar = 1×10−4 pieze = 0.1 N/m2 = 1 g⋅cm−1⋅s−2</code>
     */
    public static final Unit<Pressure> BARYE = addUnit(PASCAL.divide(10), "Barye", "Ba");

    ///////////////
    // Viscosity //
    ///////////////
    
    /**
     * A unit of dynamic viscosity equal to <code>1 g/(cmÂ·s)</code> (cgs unit
     * standard name <code>P</code>.
     * 
     * @see <a href="https://de.wikipedia.org/wiki/Poise">Wikipedia: Poise</a>
     */
    public static final Unit<DynamicViscosity> POISE = addUnit(
	    GRAM.divide(CENTI(METRE).multiply(SECOND)).asType(DynamicViscosity.class), "Poise", "P");

    /**
     * A unit of kinematic viscosity equal to <code>1 cm²/s</code> (cgs unit,
     * standard name <code>St</code>).
     */
    public static final Unit<KinematicViscosity> STOKES = addUnit(
	    CENTI(METRE).pow(2).divide(SECOND).asType(KinematicViscosity.class), "Stokes", "St");

    ////////////////
    // Wavenumber //
    ////////////////
    
    /**
     * A unit of wavenumber equal to <code>1/cm</code> (cgs unit,
     * standard name <code>cm&#8315;&#185;</code>).
     */
    public static final Unit<KinematicViscosity> KAYSER = addUnit(
	    ONE.divide(CENTIMETRE).asType(KinematicViscosity.class), "Kayser", "cm\u207B\u00B9");
    
    /////////////////////
    // Collection View //
    /////////////////////

    public String getName() {
	return SYSTEM_NAME;
    }

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
	if (isLabel && text != null) {
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
     *            the string to use as label
     * @return <code>unit</code>.
     */
    private static <U extends Unit<?>> U addUnit(U unit, String name, String text) {
	return addUnit(unit, name, text, true);
    }
}
