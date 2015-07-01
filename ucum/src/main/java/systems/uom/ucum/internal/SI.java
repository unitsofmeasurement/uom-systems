/**
 *  Unit-API - Units of Measurement API for Java
 *  Copyright (c) 2005-2015, Jean-Marie Dautelle, Werner Keil, V2COM.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of JSR-363 nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
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
package systems.uom.ucum.internal;

import static tec.uom.se.AbstractUnit.ONE;

import java.util.HashMap;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.*; 

import si.uom.quantity.Action;
import si.uom.quantity.DynamicViscosity;
import si.uom.quantity.IonizingRadiation;
import si.uom.quantity.KinematicViscosity;
import si.uom.quantity.MagneticPermeability;
//import si.uom.quantity.*;
import si.uom.quantity.MagnetomotiveForce;
import si.uom.quantity.WaveNumber;
import tec.uom.se.AbstractUnit;
import tec.uom.se.function.LogConverter;
import tec.uom.se.function.MultiplyConverter;
import tec.uom.se.function.PiMultiplierConverter;
import tec.uom.se.function.RationalConverter;
import tec.uom.se.unit.AlternateUnit;
import tec.uom.se.unit.ProductUnit;
import tec.uom.se.unit.TransformedUnit;
import tec.uom.se.unit.Units;

/**
 * <p> This class defines all SI (Système International d'Unités) base units and
 *     derived units as well as units that are accepted for use with the
 *     SI units.</p>
 *
 * @see <a href="http://en.wikipedia.org/wiki/International_System_of_Units">Wikipedia: International System of Units</a>
 * @see <a href="http://physics.nist.gov/cuu/Units/outside.html>Units outside the SI that are accepted for use with the SI</a>
 * @see <a href="http://www.bipm.org/utils/common/pdf/si_brochure_8.pdf>SI 2006 - Official Specification</a>
 * @see SIPrefix
 *
 * @author  <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @author  <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 0.7, July 1, 2015
*/
public final class SI extends Units {

    /**
     * The singleton instance.
     */
    private static final SI INSTANCE = new SI();

    /**
     * Holds the mapping quantity to unit.
     */
    private final HashMap<Class<? extends Quantity>, AbstractUnit>
            quantityToUnit = new HashMap<Class<? extends Quantity>, AbstractUnit>();

    /**
     * Default constructor (prevents this class from being instantiated).
     */
    private SI() {
    }

    /**
     * Returns the singleton instance of this class.
     *
     * @return the metric system instance.
     */
    public static SI getInstance() {
        return INSTANCE;
    }
 
    ////////////////////////////////
    // SI DERIVED ALTERNATE UNITS //
    ////////////////////////////////

    /**
     * The SI unit for magnetomotive force (standard name <code>At</code>).
     */
    public static final AlternateUnit<MagnetomotiveForce> AMPERE_TURN
            = addUnit(new AlternateUnit<MagnetomotiveForce>(SI.AMPERE, "At"), 
            		MagnetomotiveForce.class);
    
    //////////////////////////////
    // SI DERIVED PRODUCT UNITS //
    //////////////////////////////

    /**
     * The SI unit for acceleration quantities (standard name <code>m/s2</code>).
     */
    public static final Unit<Acceleration> METRES_PER_SQUARE_SECOND
            = addUnit(new ProductUnit<Acceleration>(
            METRES_PER_SECOND.divide(SECOND)), Acceleration.class);

    /**
     * The SI unit for action quantities (standard name <code>j.s</code>).
     */
    public static final Unit<Action> JOULE_SECOND
            = addUnit(new ProductUnit<Action>(
            JOULE.multiply(SECOND)), Action.class);

    /**
     * The SI unit for electric permittivity quantities (standard name <code>F/m</code>).
     */
    public static final Unit<ElectricPermittivity> FARADS_PER_METRE
            = addUnit(new ProductUnit<ElectricPermittivity>(
            FARAD.divide(METRE)), ElectricPermittivity.class);
    
    /**
     * The SI unit for magnetic permeability quantities (standard name <code>N/A2</code>).
     */
    public static final Unit<MagneticPermeability> NEWTONS_PER_SQUARE_AMPERE
            = addUnit(new ProductUnit<MagneticPermeability>(
            NEWTON.divide(AMPERE.pow(2))), MagneticPermeability.class);

    /**
     * The SI unit for wave number quantities (standard name <code>1/m</code>).
     */
    public static final Unit<WaveNumber> RECIPROCAL_METRE
            = addUnit(new ProductUnit<WaveNumber>(
            METRE.pow(-1)), WaveNumber.class);

    /**
     * The SI unit for dynamic viscosity quantities (standard name <code>Pa.s</code>).
     */
    public static final Unit<DynamicViscosity> PASCAL_SECOND
            = addUnit(new ProductUnit<DynamicViscosity>(
            PASCAL.multiply(SECOND)), DynamicViscosity.class);

    /**
     * The SI unit for luminance quantities (standard name <code>cd/m2</code>).
     */
    public static final Unit<Luminance> CANDELAS_PER_SQUARE_METRE
            = addUnit(new ProductUnit<Luminance>(
            CANDELA.divide(SQUARE_METRE)), Luminance.class);

    /**
     * The SI unit for kinematic viscosity quantities (standard name <code>m2/s"</code>).
     */
    public static final Unit<KinematicViscosity> SQUARE_METRES_PER_SECOND
            = addUnit(new ProductUnit<KinematicViscosity>(
            SQUARE_METRE.divide(SECOND)), KinematicViscosity.class);

    /**
     * The SI unit for magnetic field strength quantities (standard name <code>A/m"</code>).
     */
    public static final Unit<MagneticFieldStrength> AMPERES_PER_METRE
            = addUnit(new ProductUnit<MagneticFieldStrength>(
            AMPERE.divide(METRE)), MagneticFieldStrength.class);

    /**
     * The SI unit for ionizing radiation quantities (standard name <code>C/kg"</code>).
     */
    public static final Unit<IonizingRadiation> COULOMBS_PER_KILOGRAM
            = addUnit(new ProductUnit<IonizingRadiation>(
            COULOMB.divide(KILOGRAM)), IonizingRadiation.class);

    /////////////////////////////////////////////////////////////////
    // Units outside the SI that are accepted for use with the SI. //
    /////////////////////////////////////////////////////////////////

    /**
     * An angle unit accepted for use with SI units (standard name <code>deg/code>).
     */
    public static final Unit<Angle> DEGREE_ANGLE
        = new TransformedUnit<Angle>(RADIAN, new PiMultiplierConverter().concatenate(new RationalConverter(1, 180)));

    /**
     * An angle unit accepted for use with SI units (standard name <code>'/code>).
     */
    public static final Unit<Angle> MINUTE_ANGLE
        = new TransformedUnit<Angle>(RADIAN, new PiMultiplierConverter().concatenate(new RationalConverter(1, 180 * 60)));

    /**
     * An angle unit accepted for use with SI units (standard name <code>''</code>).
     */
    public static final Unit<Angle> SECOND_ANGLE
        = new TransformedUnit<Angle>(RADIAN,  new PiMultiplierConverter().concatenate(new RationalConverter(1, 180 * 60 * 60)));

    /**
     * A volume unit accepted for use with SI units (standard name <code>l</code>).
     */
    public static final Unit<Volume> LITRE
        = new TransformedUnit<Volume>(CUBIC_METRE, new RationalConverter(1, 1000));

    /**
     * A mass unit accepted for use with SI units (standard name <code>t</code>).
     */
    public static final Unit<Mass> TONNE
        = new TransformedUnit<Mass>(KILOGRAM, new RationalConverter(1000, 1));

    /**
     * A dimensionless unit accepted for use with SI units (standard name <code>Np</code>).
     * Although the neper is coherent with SI units and is accepted by the CIPM,
     * it has not been adopted by the General Conference on Weights and Measures
     * (CGPM, Conférence Générale des Poids et Mesures) and is thus not an SI unit.
     */
    public static final Unit<Dimensionless> NEPER
        = new TransformedUnit<Dimensionless>(ONE, new LogConverter(E).inverse());

    /**
     * A dimensionless unit accepted for use with SI units (standard name <code>B</code>).
     * The bel is most commonly used with the SI prefix deci: 1 dB = 0.1 B
     */
    public static final Unit<Dimensionless> BEL
        = new TransformedUnit<Dimensionless>(ONE, new LogConverter(10).inverse());

    /**
     * An energy unit accepted for use with SI units (standard name <code>eV</code>).
     * The electronvolt is the kinetic energy acquired by an electron passing
     * through a potential difference of 1 V in vacuum. 
     * The value must be obtained by experiment, and is therefore not known exactly.
     */
    public static final Unit<Energy> ELECTRON_VOLT
        = new TransformedUnit<Energy>(JOULE, new MultiplyConverter(1.602176487E-19));
        // CODATA 2006 - http://physics.nist.gov/cuu/Constants/codata.pdf
            
    /**
     * A mass unit accepted for use with SI units (standard name <code>u</code>).
     *  The unified atomic mass unit is equal to 1/12 of the mass of an unbound
     * atom of the nuclide 12C, at rest and in its ground state. The value must
     * be obtained by experiment, and is therefore not known exactly.
     */
    public static final Unit<Mass> UNIFIED_ATOMIC_MASS
        = new TransformedUnit<Mass>(KILOGRAM, new MultiplyConverter(1.660538782E-27));
        // CODATA 2006 - http://physics.nist.gov/cuu/Constants/codata.pdf

    /**
     * A length unit accepted for use with SI units (standard name <code>UA</code>).
     * The astronomical unit is a unit of length. Its value is such that,
     * when used to describe the motion of bodies in the solar system,
     * the heliocentric gravitation constant is (0.017 202 098 95)2 ua3·d-2.
     * The value must be obtained by experiment, and is therefore not known exactly.
     */
    public static final Unit<Length> ASTRONOMICAL_UNIT
        = addUnit(new TransformedUnit<Length>(METRE, new MultiplyConverter(149597871000.0)));
        // Best estimate source: http://maia.usno.navy.mil/NSFA/CBE.html
    
    /**
     *  An angle unit accepted for use with SI units (standard name <code>rev</code>).
     */
    public static final Unit<Angle> REVOLUTION
            = new TransformedUnit<Angle>(RADIAN, new PiMultiplierConverter().concatenate(new RationalConverter(2, 1)));

    /**
     *  An angle unit accepted for use with SI units (standard name <code>ha</code>).
     */
    public static final Unit<Area> HECTARE
            = new TransformedUnit<Area>(SQUARE_METRE, new RationalConverter(10000, 1));

    /////////////////////
    // Collection View //
    /////////////////////

    @Override
    public String getName() {
        return "SI";
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public <Q extends Quantity<Q>> AbstractUnit<Q> getUnit(Class<Q> quantityType) {
        return quantityToUnit.get(quantityType);
    }

    /**
     * Adds a new unit not mapped to any specified quantity type.
     *
     * @param  unit the unit being added.
     * @return <code>unit</code>.
     */
    private static <U extends Unit<?>>  U addUnit(U unit) {
        INSTANCE.units.add(unit);
        return unit;
    }

    /**
     * Adds a new unit and maps it to the specified quantity type.
     *
     * @param  unit the unit being added.
     * @param type the quantity type.
     * @return <code>unit</code>.
     */
    private static <U extends AbstractUnit<?>>  U addUnit(U unit, Class<? extends Quantity<?>> type) {
        INSTANCE.units.add(unit);
        INSTANCE.quantityToUnit.put(type, unit);
        return unit;
    }
}
