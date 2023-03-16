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

import static javax.measure.MetricPrefix.KILO;
import static tech.units.indriya.unit.Units.JOULE;
import static tech.units.indriya.unit.Units.KILOGRAM;
import static tech.units.indriya.unit.Units.PASCAL;
import static tech.units.indriya.unit.Units.WATT;
import static systems.uom.common.historic.CGS.DYNE;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Energy;
import javax.measure.quantity.Force;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Power;
import javax.measure.quantity.Pressure;
import javax.measure.quantity.Speed;
import javax.measure.quantity.Time;

import si.uom.quantity.Impulse;
import tech.units.indriya.AbstractSystemOfUnits;
import tech.units.indriya.AbstractUnit;
import tech.units.indriya.format.SimpleUnitFormat;
import tech.units.indriya.function.MultiplyConverter;
import tech.units.indriya.unit.ProductUnit;
import tech.units.indriya.unit.TransformedUnit;
import tech.units.indriya.unit.Units;

/**
 * <p>
 * This class contains the Gravitational metric system of units.
 * </p>
 * 
 * <p>
 * This class is not intended to be implemented by clients.
 * </p>
 * 
 * @noextend This class is not intended to be extended by clients.
 * 
 * @author <a href="mailto:werner@uom.systems">Werner Keil</a>
 * @version 1.0, $Date: 2021-03-28$
 * @see <a href= "https://en.wikipedia.org/wiki/Gravitational_metric_system">Wikipedia: Gravitational metric system</a>
 * @see <a href= "http://ww2.cnam.fr/physique/PHR011_ELECTRICITE/ap2/SI_Anglo.htm">Systèmes de mesure - Measure systems</a>
 *      
 * @since 2.1
 */
public final class MKpS extends AbstractSystemOfUnits {
    private static final String SYSTEM_NAME = "Gravitational metric system";

    private static final MKpS INSTANCE = new MKpS();
    
    /**
     * Default constructor (prevents this class from being instantiated).
     */
    private MKpS() {
    }

    /**
     * Returns the unique instance of this class.
     * 
     * @return the NonSI instance.
     */
    public static MKpS getInstance() {
        return INSTANCE;
    }


    ////////////
    // Length //
    ////////////    
	/**
	 * The unit of length is the metre, defined by the distance, at 0°, between the axes of the <br>
	 * two central lines marked on the bar of platinum–iridium kept at the Bureau International des Poids et Mesures <br>
	 * and declared Prototype of the metre by the 1st Conférence Générale des Poids et Mesures,<br>
	 * this bar being subject to standard atmospheric pressure and supported on two cylinders of at least one centimetre diameter, symmetrically placed in the same horizontal plane at a distance of 571 mm from each other.	 
	 */
	public static final Unit<Length> METRE = addUnit(Units.METRE, Length.class);

    //////////
    // Mass //
    //////////
	/**
	 * The hyl, metric slug (mug), or TME (German: technische Masseneinheit, lit. 'technical mass unit'), is the mass that accelerates at 1 m/s2 under a force of 1 kgf.[4] The hyl has also been used as the unit of mass in a metre–gram-force–second (mgfs) system.
	 */
	public static final Unit<Mass> HYL = addUnit(KILOGRAM.multiply(9.80665), "Hyl", "hyl", Mass.class);
	
	/**
	 * The TME (German: technische Masseneinheit, lit. 'technical mass unit'), is the mass that accelerates at 1 m/s2 under a force of 1 kgf.[4] The hyl has also been used as the unit of mass in a metre–gram-force–second (mgfs) system.
	 */
	public static final Unit<Mass> TME = addUnit(new TransformedUnit<Mass>("Technical mass unit", "TME", HYL, MultiplyConverter.identity()));
	
    //////////
    // Time //
    //////////
    /**
     * The SI base unit for duration quantities (standard name <code>s</code>). It is defined as the duration of 9,192,631,770 cycles of radiation
     * corresponding to the transition between two hyperfine levels of the ground state of cesium (1967 Standard).
     * 
     */
    public static final Unit<Time> SECOND = addUnit(Units.SECOND, Time.class);

    //////////////
    // Speed    //
    //////////////
	/**
	 * The standard unit for speed quantities (standard name <code>m/s</code>).
	 */
	public static final Unit<Speed> METRE_PER_SECOND = addUnit(new ProductUnit<>(METRE.divide(SECOND)), Speed.class);

    ////////////
    // Energy //
    ////////////
    /**
     * There is no dedicated name for the unit of energy, "metre" is simply appended to "kilopond", but usually the symbol of the kilopond-metre is written without the middle dot.
     * 
     * <code>1 kp⋅m = gn kg⋅m = 9.806 65 kg⋅m2/s2 = 9.806 65 J</code>
     */
    public static final Unit<Energy> KILOPOND_METRE = addUnit(JOULE.multiply(9.80665), "Kilopond-metre", "kpm", Energy.class);

    ///////////
    // Force //
    ///////////        
    /**
     * In English contexts the unit of force is usually formed by simply appending the suffix "force" to the name of the unit of mass, thus gram-force (gf) or kilogram-force (kgf), which follows the tradition of pound-force (lbf). In other, international, contexts the special name pond (p) or kilopond (kp) respectively is more frequent.
     */
    public static final Unit<Force> POND = addUnit(DYNE.multiply(980.665), "Pond", "p", Force.class);

    ///////////
    // Power //
    ///////////
    /**
     * Horsepower (HP) is the name of several units of measurement of power. The most common definitions equal between 735.5 and 750 watts. Horsepower
     * was originally defined to compare the output of steam engines with the power of draft horses. The unit was widely adopted to measure the output
     * of piston engines, turbines, electric motors, and other machinery. The definition of the unit varied between geographical regions. Most
     * countries now use the SI unit watt for measurement of power. With the implementation of the EU Directive 80/181/EEC on January 1, 2010, the use
     * of horsepower in the EU is only permitted as supplementary unit.
     * @see #PONCELET
     */
    public static final Unit<Power> HORSEPOWER = addUnit(WATT.multiply(735.499), "Horsepower", "HP");

    /**
     * In 19th-century France there was as a unit of power, the poncelet, which was defined as the power required to raise a mass of 1 quintal (1 q = 100 kg) at a velocity of 1 m/s.<br>
     * The German or metric horsepower (PS, Pferdestärke) is arbitrarily selected to be three quarters thereof.
     * <p>
     * <code>1 pq = 1 qf⋅m/s = 100 kp⋅m/s = 100 × gn kg⋅m/s = 980.665 kg⋅m2/s3 = 0.980 665 kW</code>
     * 
     * @see #HORSEPOWER
     */
    public static final Unit<Power> PONCELET = addUnit(WATT.multiply(980.665), "Poncelet", "Pq", Power.class);

    //////////////
    // Pressure //
    //////////////
    /**
     * The gravitational unit of pressure is the technical atmosphere (at). It is the gravitational force of one kilogram, i.e. 1 kgf, exerted on an area of one square centimetre.
     * <code>1 at = 1 kp/cm2 = 10 000 × gn kg/m2 = 98 066.5 kg/(m⋅s2) = 98.066 5 kPa</code>
     */
    public static final Unit<Pressure> TECHNICAL_ATMOSPHERE = addUnit(KILO(PASCAL).multiply(98.06650), "Technical atmosphere", "at", Pressure.class);
    
    /**
     * A metre, centimetre or millimetre of water are less commonly used measures of pressure derived from pressure head.
     *
     * @see <a href="https://en.wikipedia.org/wiki/Centimetre_or_millimetre_of_water">Wikipedia: Centimetre or millimetre of water</a>
     */
    public static final Unit<Pressure> METER_OF_WATER_COLUMN = addUnit(KILO(PASCAL).multiply(980665).divide(100000));

    /////////////
    // Impulse //
    /////////////
	/**
     * The kilopond-second unit of impulse.<br>
	 * 
	 * <code>1 kps = 1 kp·s = 9,806 65 kg·m/s</code>
	 * 
	 * @see <a href="https://en.wikipedia.org/wiki/Impulse_(physics)">Wikipedia: Impulse (physics)</a>
	 */
	public static final Unit<Impulse> KILOPOND_SECOND = addUnit(
			new ProductUnit<Impulse>(KILO(POND).multiply(SECOND)), Impulse.class);
    
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
     * Adds a new unit not mapped to any specified quantity type and puts a text as symbol or label.
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
     * Adds a new unit not mapped to any specified quantity type and puts a text as symbol or label.
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
    
    /**
     * Adds a new unit not mapped to any specified quantity type and puts a text as symbol or label.
     *
     * @param unit
     *            the unit being added.
     * @param name
     *            the string to use as name
     * @param text
     *            the string to use as label
     * @param type the quantity type.
     * @return <code>unit</code>.
     */
    private static <U extends Unit<?>> U addUnit(U unit, String name, String text, Class<? extends Quantity<?>> type) {
    	INSTANCE.quantityToUnit.put(type, unit);
        return addUnit(unit, name, text, true);
    }
    
	/**
	 * Adds a new unit and maps it to the specified quantity type.
	 *
	 * @param unit the unit being added.
	 * @param type the quantity type.
	 * @return <code>unit</code>.
	 */
	private static <U extends Unit<?>> U addUnit(U unit, Class<? extends Quantity<?>> type) {
		INSTANCE.units.add(unit);
		INSTANCE.quantityToUnit.put(type, unit);
		return unit;
	}
}
