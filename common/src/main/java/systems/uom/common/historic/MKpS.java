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

import static javax.measure.MetricPrefix.KILO;
import static tech.units.indriya.unit.Units.JOULE;
import static tech.units.indriya.unit.Units.PASCAL;
import static tech.units.indriya.unit.Units.WATT;
import static systems.uom.common.historic.CGS.DYNE;

import java.util.Objects;

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

import tech.units.indriya.AbstractSystemOfUnits;
import tech.units.indriya.AbstractUnit;
import tech.units.indriya.format.SimpleUnitFormat;
import tech.units.indriya.function.MultiplyConverter;
import tech.units.indriya.unit.BaseUnit;
import tech.units.indriya.unit.ProductUnit;
import tech.units.indriya.unit.TransformedUnit;
import tech.units.indriya.unit.UnitDimension;
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
 * @version 0.9, $Date: 2021-03-28$
 * @see <a href= "https://en.wikipedia.org/wiki/Gravitational_metric_system">Wikipedia: Gravitational metric system</a>
 * @see <a href= "http://ww2.cnam.fr/physique/PHR011_ELECTRICITE/ap2/SI_Anglo.htm">Systèmes de mesure - Measure systems</a>
 *      
 * @since 2.1
 */
public final class MKpS extends AbstractSystemOfUnits {
    private static final String SYSTEM_NAME = "Gravitational metric system";

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

    private static final MKpS INSTANCE = new MKpS();

    ////////////
    // Length //
    ////////////
    
	/**
	 * The metre, symbol m, is the SI unit of length. It is defined by taking the
	 * fixed numerical value of the speed of light in vacuum c to be 299 792 458
	 * when expressed in the unit m s⁻¹, where the second is defined in terms of the
	 * caesium frequency ∆νCs.
	 *
	 * This definition implies the exact relation c = 299 792 458 m s⁻¹. Inverting
	 * this relation gives an exact expression for the metre in terms of the
	 * defining constants c and ∆νCs:
	 * <code>
	 * 1 m = (c / 299 792 458)s = 9 192 631 770 c / 299 792 458 ∆νCs ≈ 30.663 319 c
	 * / ∆νCs
	 * </code>
     * <dl>
     * <dt><span class="strong">Implementation Note:</span></dt><dd>SI Base Unit</dd>
     * </dl>
	 */
	public static final Unit<Length> METRE = addUnit(Units.METRE);

    //////////
    // Mass //
    //////////
    
	/**
	 * The kilogram, symbol kg, is the SI unit of mass. It is defined by taking the
	 * fixed numerical value of the Planck constant h to be 6.626 070 15 Ã— 10â�»Â³â�´
	 * when expressed in the unit J s, which is equal to kg mÂ² sâˆ’1, where the metre
	 * and the second are defined in terms of c and âˆ†Î½Cs.
	 *
	 * This definition implies the exact relation h = 6.626 070 15 Ã— 10âˆ’34 kg mÂ²
	 * sâ�»Â¹. Inverting this relation gives an exact expression for the kilogram in
	 * terms of the three defining constants h, âˆ†Î½Cs and c:
	 *<code>
	 * 1 kg = (h / 6.626 070 15 Ã— 10â�»Â³â�´) mâ�»Â² s
	 *</code>
	 * <dl>
     * <dt><span class="strong">Implementation Note:</span></dt><dd>SI Base Unit</dd>
     * </dl>
	 * @see <a href="https://en.wikipedia.org/wiki/Kilogram">Wikipedia: Kilogram</a>
	 * @see #METRE
	 * @see #SECOND
	 */
	public static final Unit<Mass> KILOGRAM = addUnit(new BaseUnit<Mass>("kg", "Kilogram", UnitDimension.MASS), Mass.class);

	/**
	 * The hyl, metric slug (mug), or TME (German: technische Masseneinheit, lit. 'technical mass unit'), is the mass that accelerates at 1 m/s2 under a force of 1 kgf.[4] The hyl has also been used as the unit of mass in a metre–gram-force–second (mgfs) system.
	 */
	public static final Unit<Mass> HYL = addUnit(KILOGRAM.multiply(9.80665), "Hyl", "hyl");
	
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
    public static final Unit<Time> SECOND = addUnit(Units.SECOND);

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
    public static final Unit<Energy> KILOPOND_METRE = addUnit(JOULE.multiply(9.80665), "Kilopond-metre", "kpm");

    ///////////
    // Force //
    ///////////        
    /**
     * In English contexts the unit of force is usually formed by simply appending the suffix "force" to the name of the unit of mass, thus gram-force (gf) or kilogram-force (kgf), which follows the tradition of pound-force (lbf). In other, international, contexts the special name pond (p) or kilopond (kp) respectively is more frequent.
     */
    public static final Unit<Force> POND = addUnit(DYNE.multiply(980.665), "Pond", "p");

    ///////////
    // Power //
    ///////////
    /**
     * Horsepower (HP) is the name of several units of measurement of power. The most common definitions equal between 735.5 and 750 watts. Horsepower
     * was originally defined to compare the output of steam engines with the power of draft horses. The unit was widely adopted to measure the output
     * of piston engines, turbines, electric motors, and other machinery. The definition of the unit varied between geographical regions. Most
     * countries now use the SI unit watt for measurement of power. With the implementation of the EU Directive 80/181/EEC on January 1, 2010, the use
     * of horsepower in the EU is only permitted as supplementary unit.
     */
    public static final Unit<Power> HORSEPOWER = addUnit(WATT.multiply(735.499), "Horsepower", "HP");

    //////////////
    // Pressure //
    //////////////
    /**
     * The gravitational unit of pressure is the technical atmosphere (at). It is the gravitational force of one kilogram, i.e. 1 kgf, exerted on an area of one square centimetre.
     * <code>1 at = 1 kp/cm2 = 10 000 × gn kg/m2 = 98 066.5 kg/(m⋅s2) = 98.066 5 kPa</code>
     */
    public static final Unit<Pressure> TECHNICAL_ATMOSPHERE = addUnit(KILO(PASCAL).multiply(98.06650), "Technical atmosphere", "at");

    /////////////////////
    // Collection View //
    /////////////////////

    public String getName() {
        return SYSTEM_NAME;
    }

    @Override
    public Unit<?> getUnit(String string) {
        Objects.requireNonNull(string);
        return this.getUnits().stream()
                  .filter((u) -> string.equals(u.toString()))
                  .findAny()
                  .orElse(null);
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
	 * Adds a new unit and maps it to the specified quantity type.
	 *
	 * @param unit the unit being added.
	 * @param type the quantity type.
	 * @return <code>unit</code>.
	 */
	private static <U extends AbstractUnit<?>> U addUnit(U unit, Class<? extends Quantity<?>> type) {
		INSTANCE.units.add(unit);
		INSTANCE.quantityToUnit.put(type, unit);
		return unit;
	}
}
