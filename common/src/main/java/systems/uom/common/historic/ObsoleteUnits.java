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
package systems.uom.common.historic;

import static tech.units.indriya.AbstractUnit.ONE;
import static tech.units.indriya.unit.Units.BECQUEREL;
import static tech.units.indriya.unit.Units.COULOMB;
import static tech.units.indriya.unit.Units.KILOGRAM;
import static tech.units.indriya.unit.Units.METRE;
import static tech.units.indriya.unit.Units.SECOND;
import static tech.units.indriya.unit.Units.STERADIAN;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Length;
import javax.measure.quantity.Radioactivity;
import javax.measure.quantity.SolidAngle;
import javax.measure.quantity.Time;

import si.uom.quantity.IonizingRadiation;
import tech.units.indriya.AbstractSystemOfUnits;
import tech.units.indriya.AbstractUnit;
import tech.units.indriya.format.SimpleUnitFormat;

/**
 * <p>
 * This class contains obsolete units, no longer used e.g. with the SI system.
 * </p>
 * 
 * <p>
 * This class is not intended to be implemented by clients.
 * </p>
 * 
 * @noextend This class is not intended to be extended by clients.
 * 
 * @author <a href="mailto:werner@uom.systems">Werner Keil</a>
 * @version 1.0, 14 June 2025
 * @see <a href= "https://en.wikipedia.org/wiki/List_of_obsolete_units_of_measurement">Wikipedia: List of obsolete units of measurement
 *      </a>
 * @since 2.2
 */
public final class ObsoleteUnits extends AbstractSystemOfUnits {
    private static final String SYSTEM_NAME = "Obsolete Units";

    /**
     * Holds the avoirdupois pound: 0.45359237 kg exact
     */
    static final int AVOIRDUPOIS_POUND_DIVIDEND = 45359237;

    static final int AVOIRDUPOIS_POUND_DIVISOR = 100000000;

    private static final ObsoleteUnits INSTANCE = new ObsoleteUnits();
    
    /**
     * Default constructor (prevents this class from being instantiated).
     */
    private ObsoleteUnits() {
    }

    /**
     * Returns the unique instance of this class.
     * 
     * @return the NonSI instance.
     */
    public static ObsoleteUnits getInstance() {
        return INSTANCE;
    }
    
    ///////////////////
    // Dimensionless //
    ///////////////////
    /**
     * A dimensionless unit equals to <code>pi</code> (standard name
     * <code>Ï€</code>). 
     */
    public static final Unit<Dimensionless> PI = addUnit(ONE.multiply(StrictMath.PI), "Pi", "pi");

    ////////////
    // Length //
    ////////////

    /**
     * A unit of length equal to <code>1E-10 m</code> (standard name
     * <code>Å</code>).
     * 
     * @see <a href="https://en.wikipedia.org/wiki/%C3%85ngstr%C3%B6m"> Wikipedia:
     *      Ångström</a>      
     */
    public static final Unit<Length> ANGSTROM = addUnit(METRE.divide(10000000000L), "\u00C5ngstr\u00F6m", "\u00C5");

    /**
     * A unit of length equal to the distance that light travels in one year through
     * a vacuum (standard name <code>ly</code>). 
     */
    public static final Unit<Length> LIGHT_YEAR = addUnit(METRE.multiply(9.460528405e15), "Light year", "ly");

    /**
     * A unit of length equal to the distance at which a star would appear to shift
     * its position by one arcsecond over the course the time (about 3 months) in
     * which the Earth moves a distance of {@link #ASTRONOMICAL_UNIT} in the
     * direction perpendicular to the direction to the star (standard name
     * <code>pc</code>).
     */
    public static final Unit<Length> PARSEC = addUnit(METRE.multiply(30856770e9), "Parsec", "pc");

    /**
     * A unit of length equal to <code>1852.0 m</code> (standard name
     * <code>nmi</code>).
     */
    public static final Unit<Length> NAUTICAL_MILE = addUnit(METRE.multiply(1852), "Nautical mile", "nmi");
    
	/**
	 * The Bohr radius (a0 or rBohr) is a physical constant, approximately equal to the most probable distance between the nucleus and the electron in a hydrogen atom in its ground state.
	 * It is named after Niels Bohr, due to its role in the Bohr model of an atom. Its value is 5.2917721067(12)×10−11 m.
	 * 
     * @see <a href="https://en.wikipedia.org/wiki/Niels_Bohr"> Wikipedia: Niels Bohr</a>
	 */
	public static final Unit<Length> BOHR_RADIUS = addUnit(METRE.multiply(5.291772106712E-11), "Bohr Radius", "a0");

    //////////////////////////
    // Radioactivity        //
    //////////////////////////

    /**
     * A unit of radioctive activity equal to the activity of a gram of radium
     * (standard name <code>Ci</code>).
     */
    public static final Unit<Radioactivity> CURIE = addUnit(BECQUEREL.multiply(37000000000L),
    		"Curie", "Ci");
    
    /**
     * A unit used to measure the ionizing ability of radiation (standard name
     * <code>R</code>).
     * 
     * @see <a href="https://en.wikipedia.org/wiki/Roentgen_(unit)"> Wikipedia:
     *      Roentgen</a>
     */
    public static final Unit<IonizingRadiation> ROENTGEN = addUnit(
            COULOMB.divide(KILOGRAM).multiply(2.58e-4).asType(IonizingRadiation.class), "Roentgen", "R", true);


    /////////////////
    // Solid angle //
    /////////////////
    /**
     * A unit of solid angle equal to <code>4 <i>&pi;</i> steradians</code>
     * (standard name <code>sphere</code>).
     *  
     */
    protected static final Unit<SolidAngle> SPHERE = addUnit(
            STERADIAN.multiply(4).multiply(PI).asType(SolidAngle.class), "Sphere", "sphere");

    ////////////
    // Time   //
    ////////////

    /**
     * A unit of duration equal to the time required for a complete rotation of the
     * earth in reference to any star or to the vernal equinox at the meridian,
     * equal to 23 hours, 56 minutes, 4.09 seconds (standard name
     * <code>day_sidereal</code>).
     * @deprecated Not supported with the SI anymore, will be moved to another module into ObsoleteUnits or similar 
     */
    public static final Unit<Time> DAY_SIDEREAL = addUnit(SECOND.multiply(86164.09),
    		"Day Sidereal", "day_sidereal");
	
    /**
     * A unit of duration equal to one complete revolution of the earth about the
     * sun, relative to the fixed stars, or 365 days, 6 hours, 9 minutes, 9.54
     * seconds (standard name <code>year_sidereal</code>).
     * 
     */
    public static final Unit<Time> YEAR_SIDEREAL = addUnit(SECOND.multiply(31558149.54),
    		"Year Sidereal", "year_sidereal");

    /**
     * The Julian year, as used in astronomy and other sciences, is a time unit
     * defined as exactly 365.25 days. This is the normal meaning of the unit "year"
     * (symbol "a" from the Latin annus, annata) used in various scientific
     * contexts. 
     */
    public static final Unit<Time> YEAR_JULIEN = addUnit(SECOND.multiply(31557600),
    		"Year Julien", "year_julien");
     
    /////////////////////
    // Collection View //
    /////////////////////

    public String getName() {
        return SYSTEM_NAME;
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
}
