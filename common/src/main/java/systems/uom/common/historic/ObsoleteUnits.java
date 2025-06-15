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
package systems.uom.common.historic;

import static javax.measure.MetricPrefix.KILO;
import static tech.units.indriya.AbstractUnit.ONE;
import static tech.units.indriya.unit.Units.BECQUEREL;
import static tech.units.indriya.unit.Units.CELSIUS;
import static tech.units.indriya.unit.Units.COULOMB;
import static tech.units.indriya.unit.Units.GRAM;
import static tech.units.indriya.unit.Units.KILOGRAM;
import static tech.units.indriya.unit.Units.METRE;
import static tech.units.indriya.unit.Units.SECOND;
import static tech.units.indriya.unit.Units.STERADIAN;
import static systems.uom.common.USCustomary.CUBIC_FOOT;

import javax.measure.Unit;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Length;
import javax.measure.quantity.Radioactivity;
import javax.measure.quantity.SolidAngle;
import javax.measure.quantity.Temperature;
import javax.measure.quantity.Time;
import javax.measure.quantity.Volume;

import si.uom.quantity.IonizingRadiation;
import tech.units.indriya.AbstractSystemOfUnits;
import tech.units.indriya.AbstractUnit;
import tech.units.indriya.format.SimpleUnitFormat;

/**
 * <p>
 * This class contains obsolete units, no longer used with the SI system or other systems of units.
 * </p>
 * 
 * <p>
 * This class is not intended to be implemented by clients.
 * </p>
 * 
 * @noextend This class is not intended to be extended by clients.
 * 
 * @author <a href="mailto:werner@uom.systems">Werner Keil</a>
 * @version 1.1, 15 June 2025
 * @see <a href= "https://en.wikipedia.org/wiki/List_of_obsolete_units_of_measurement">Wikipedia: List of obsolete units of measurement
 *      </a>
 * @see <a href= "https://metricviews.uk/2024/08/30/which-non-si-units-are-accepted-for-use-with-the-si/">Metric Views: Which non-SI units are accepted for use with the SI?
 *      </a>      
 * @since 2.2
 */
public final class ObsoleteUnits extends AbstractSystemOfUnits {
    private static final String SYSTEM_NAME = "Obsolete Units";

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

	/**
	 * A unit of length equal to <code>0.3 m</code> (standard name <code>pied</code>).
	 * One pied (1 fuss) was equal to 0.30 m, according to the fixed value defined during the transition to the metric system.
	 */
	public static final Unit<Length> PIED = addUnit(METRE.multiply(0.3), "Pied", "pied");
	
	/**
	 * A unit of length equal to <code>1/144 pied</code> (Symbol <code>ligne</code>).
	 * 
	 * @see <a href="https://en.wikipedia.org/wiki/Ligne"> Wikipedia: Ligne</a> 
	 */
	public static final Unit<Length> LIGNE = addUnit(PIED.divide(144), "Ligne", "ligne");
	
	/**
	 * A toise (French pronunciation: [twaz]; symbol: <code>T</code>) is a unit of measure for length, 
	 * area and volume originating in pre-revolutionary France. 
	 * In North America, it was used in colonial French establishments in early New France, 
	 * French Louisiana (Louisiane), Acadia (Acadie) and Quebec. 
	 * The related toesa (Portuguese pronunciation: [tuˈezɐ]) was used in Portugal, Brazil, 
	 * and other parts of the Portuguese Empire until the adoption of the metric system.
	 * 
	 * @see <a href="https://en.wikipedia.org/wiki/Toise"> Wikipedia: Toise</a> 
	 */
	public static final Unit<Length> TOISE = addUnit(LIGNE.multiply(864), "Toise", "T");
	
	
	/**
	 * The rod, perch, or pole (sometimes also lug) is a surveyor's tool and 
	 * unit of length of various historical definitions. 
	 * In British imperial and US customary units, it is defined as 16+1⁄2 feet, 
	 * equal to exactly 1⁄320 of a mile, or 5+1⁄2 yards (a quarter of a surveyor's chain), 
	 * and is exactly 5.0292 meters. (Symbol <code>rod</code>)
	 * 
	 * @see <a href="https://en.wikipedia.org/wiki/Rod_(unit)"> Wikipedia: Rod (unit)</a> 
	 */
	public static final Unit<Length> ROD = addUnit(METRE.multiply(5.0292), "Rod", "rod");
	
	/**
	* The spat (symbol <code>S</code>) is an obsolete unit of distance used in astronomy. 
	* It is equal to 1,000,000,000 kilometres (620,000,000 mi; 6.7 au). A light-year is about 9460 S.
	 * 
	 * @see <a href="https://en.wikipedia.org/wiki/Spat_(distance_unit)"> Wikipedia: Spat (distance unit)</a> 
	 */
	public static final Unit<Length> SPAT = addUnit(KILO(METRE).multiply(1000000000L), "Spat", "S");
	
    //////////////////////////
    // Mass                 //
    //////////////////////////

    /**
	 * A pennyweight (dwt) is a unit of mass equal to 24 grains, 1⁄20 of a troy ounce, 1⁄240 of a troy pound, 
	 * 48⁄875 avoirdupois ounce and exactly 1.55517384 grams.
	 * It is abbreviated dwt, d standing for denarius – (an ancient Roman coin), 
	 * and later used as the symbol of an old British penny. 
     * @see <a href="https://en.wikipedia.org/wiki/Pennyweight"> Wikipedia:
     *  Pennyweight</a>
     */
    public static final Unit<Mass> PENNYWEIGHT = addUnit(GRAM.multiply(1.55517384),
    		"Pennyweight", "dwt");
	
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
    public static final Unit<SolidAngle> SPHERE = addUnit(
            STERADIAN.multiply(4).multiply(PI).asType(SolidAngle.class), "Sphere", "sphere");

    /////////////////
    // Temperature //
    /////////////////
    
    /**
     * The Réaumur scale (French pronunciation: [ʁeomy(ː)ʁ]; °Ré, °Re, °r), also known as the "octogesimal division", 
     * is a temperature scale for which the melting and boiling points of water are defined as 0 and 80 degrees respectively. 
     * (standard name <code>°Ré</code>)
     * 
     * @see <a href="https://en.wikipedia.org/wiki/R%C3%A9aumur_scale"> Wikipedia:
     * Réaumur scale</a>
     */
    public static final Unit<Temperature> REAUMUR = addUnit(CELSIUS.multiply(1.25), "Réaumur", "°Ré");
    
    ////////////
    // Time   //
    ////////////

    /**
     * A unit of duration equal to the time required for a complete rotation of the
     * earth in reference to any star or to the vernal equinox at the meridian,
     * equal to 23 hours, 56 minutes, 4.09 seconds (standard name
     * <code>day_sidereal</code>). 
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
    
    //////////////////////////
    // Volume               //
    //////////////////////////

    /**
     * A shipping ton, freight ton, measurement ton or ocean ton is a measure of volume used for shipments of freight in large vehicles, 
     * trains or ships. In the USA, it is equivalent to 40 cubic feet (1.1 m3) while in the UK it is 42 cubic feet (1.2 m3).
     *  
     * @see <a href="https://en.wikipedia.org/wiki/Shipping_ton"> Wikipedia:
     *  Shipping ton</a>
     */
    public static final Unit<Volume> SHIPPING_TON_US = addUnit(CUBIC_FOOT.multiply(40),
    		"Shipping Ton (US)", "sh_ton_us");

    /**
     * A shipping ton, freight ton, measurement ton or ocean ton is a measure of volume used for shipments of freight in large vehicles, 
     * trains or ships. In the USA, it is equivalent to 40 cubic feet (1.1 m3) while in the UK it is 42 cubic feet (1.2 m3).
     *  
     * @see <a href="https://en.wikipedia.org/wiki/Shipping_ton"> Wikipedia:
     *  Shipping ton</a>
     */
    public static final Unit<Volume> SHIPPING_TON_UK = addUnit(CUBIC_FOOT.multiply(42),
    		"Shipping Ton (UK)", "sh_ton_uk");
     
    /////////////////////
    // Collection View //
    /////////////////////

    public String getName() {
        return SYSTEM_NAME;
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
