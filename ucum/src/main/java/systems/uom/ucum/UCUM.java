/*
 * Units of Measurement Systems
 * Copyright (c) 2005-2020, Jean-Marie Dautelle, Werner Keil and others.
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
package systems.uom.ucum;

import static javax.measure.MetricPrefix.*;
import static tech.units.indriya.AbstractUnit.ONE;

import si.uom.quantity.*;
import systems.uom.quantity.Acidity;
import systems.uom.quantity.Concentration;
import systems.uom.quantity.Drag;
import systems.uom.quantity.Information;
import systems.uom.quantity.InformationRate;
import si.uom.quantity.Level;
import si.uom.SI;
import tech.units.indriya.*;
import tech.units.indriya.format.SimpleUnitFormat;
import tech.units.indriya.function.LogConverter;
import tech.units.indriya.function.MultiplyConverter;
import tech.units.indriya.unit.AlternateUnit;
import tech.units.indriya.unit.ProductUnit;
import tech.units.indriya.unit.TransformedUnit;
import tech.units.indriya.unit.Units;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.*;

/**
 * <p>
 * This class contains {@link SI} and Non-SI units as defined in the
 * <a href="http://unitsofmeasure.org/">Unified Code for Units of Measure</a>.
 * </p>
 *
 * <p>
 * Compatibility with {@link SI} units has been given priority over strict
 * adherence to the standard. We have attempted to note every place where the
 * definitions in this class deviate from the UCUM standard, but such notes 
 * could be incomplete.
 * </p>
 *
 * @author <a href="mailto:eric-r@northwestern.edu">Eric Russell</a>
 * @author <a href="mailto:werner@uom.systems">Werner Keil</a>
 * @see <a href="http://www.unitsofmeasure.org">UCUM</a>
 * @version 2.5, $Date: 2020-11-16 $
 */
public final class UCUM extends AbstractSystemOfUnits {

    /**
     * The singleton instance.
     */
    private static final UCUM INSTANCE = new UCUM();

    /**
     * Default constructor (prevents this class from being instantiated).
     */
    private UCUM() {
    }

    /**
     * Returns the singleton instance of this class.
     *
     * @return the UCUM system instance.
     */
    public static UCUM getInstance() {
        return INSTANCE;
    }

    //////////////////////////////
    // BASE UNITS: UCUM 4.2 §28 //
    //////////////////////////////
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> METER = addUnit(Units.METRE);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Time> SECOND = addUnit(Units.SECOND);
    /**
     * We deviate slightly from the standard here, to maintain compatibility
     * with the existing SI units. In UCUM, the gram is the base unit of mass,
     * rather than the kilogram. This doesn't have much effect on the units
     * themselves, but it does make formatting the units a challenge.
     */
    public static final Unit<Mass> GRAM = addUnit(Units.GRAM);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Angle> RADIAN = addUnit(Units.RADIAN);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Temperature> KELVIN = addUnit(Units.KELVIN);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<ElectricCharge> COULOMB = addUnit(Units.COULOMB);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<LuminousIntensity> CANDELA = addUnit(Units.CANDELA);

    ///////////////////////////////////////////////
    // DIMENSIONLESS DERIVED UNITS: UCUM 4.3 §29 //
    ///////////////////////////////////////////////
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Dimensionless> TRILLIONS = addUnit(ONE.multiply(1000000000000L));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Dimensionless> BILLIONS = addUnit(ONE.multiply(1000000000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Dimensionless> MILLIONS = addUnit(ONE.multiply(1000000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Dimensionless> THOUSANDS = addUnit(ONE.multiply(1000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Dimensionless> HUNDREDS = addUnit(ONE.multiply(100));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Dimensionless> PI = addUnit(ONE.transform(MultiplyConverter.ofPiExponent(1)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Dimensionless> PERCENT = addUnit(ONE.divide(100));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Dimensionless> PER_THOUSAND = addUnit(ONE.divide(1000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Dimensionless> PER_MILLION = addUnit(ONE.divide(1000000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Dimensionless> PER_BILLION = addUnit(ONE.divide(1000000000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Dimensionless> PER_TRILLION = addUnit(ONE.divide(1000000000000L));
    ////////////////////////////
    // SI UNITS: UCUM 4.3 §30 //
    ////////////////////////////
    /**
     * We deviate slightly from the standard here, to maintain compatibility
     * with the existing SI units. In UCUM, the mole is no longer a base unit,
     * but is defined as <code>Unit.ONE.multiply(6.0221367E23)</code>.
     */
    public static final Unit<AmountOfSubstance> MOLE = addUnit(Units.MOLE);
    /**
     * We deviate slightly from the standard here, to maintain compatibility
     * with the existing SI units. In UCUM, the steradian is defined as
     * <code>RADIAN.pow(2)</code>.
     */
    public static final Unit<SolidAngle> STERADIAN = addUnit(Units.STERADIAN);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Frequency> HERTZ = addUnit(Units.HERTZ);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Force> NEWTON = addUnit(Units.NEWTON);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Pressure> PASCAL = addUnit(Units.PASCAL);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Energy> JOULE = addUnit(Units.JOULE);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Power> WATT = addUnit(Units.WATT);
    /**
     * We deviate slightly from the standard here, to maintain compatibility
     * with the existing SI units. In UCUM, the ampere is defined as
     * <code>COULOMB.divide(SECOND)</code>.
     */
    public static final Unit<ElectricCurrent> AMPERE = addUnit(Units.AMPERE, "Ampère", "A");

    /**
     * We deviate slightly from the standard here, to maintain compatibility
     * with the existing SI units. In UCUM, the volt is defined as
     * <code>JOULE.divide(COULOMB)</code>.
     */
    public static final Unit<ElectricPotential> VOLT = addUnit(Units.VOLT);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<ElectricCapacitance> FARAD = addUnit(Units.FARAD);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<ElectricResistance> OHM = addUnit(Units.OHM);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<ElectricConductance> SIEMENS = addUnit(Units.SIEMENS);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<MagneticFlux> WEBER = addUnit(Units.WEBER);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Temperature> CELSIUS = addUnit(Units.CELSIUS);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<MagneticFluxDensity> TESLA = addUnit(Units.TESLA);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<ElectricInductance> HENRY = addUnit(Units.HENRY);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<LuminousFlux> LUMEN = addUnit(Units.LUMEN);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Illuminance> LUX = addUnit(Units.LUX);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Radioactivity> BECQUEREL = addUnit(Units.BECQUEREL);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<RadiationDoseAbsorbed> GRAY = addUnit(Units.GRAY);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<RadiationDoseEffective> SIEVERT = addUnit(Units.SIEVERT);

    ///////////////////////////////////////////////////////////////////////
    // OTHER UNITS FROM ISO 1000, ISO 2955, AND ANSI X3.50: UCUM 4.3 §31 //
    ///////////////////////////////////////////////////////////////////////
    // The order of GON and DEGREE has been inverted because GON is defined in
    // terms of DEGREE
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Angle> DEGREE = addUnit(new ProductUnit<Angle>(PI.multiply(RADIAN.divide(180))));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Angle> GRADE = addUnit(DEGREE.multiply(0.9));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Angle> GON = GRADE;
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Angle> MINUTE_ANGLE = addUnit(DEGREE.divide(60));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Angle> SECOND_ANGLE = addUnit(MINUTE_ANGLE.divide(60));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> LITER = addUnit(Units.LITRE,  "liter", "L", true);
    /**
     * As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. Liter has
     * <b>two</b> definitions.
     * 
     * @see <a href="http://unitsofmeasure.org/ucum.html#iso1000">UCUM Table
     *      5</a>
     */
    public static final Unit<Volume> LITER_DM3 = addUnit(DECI(Units.METRE).pow(3).asType(Volume.class), "liter", "l", true);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Area> ARE = addUnit(Units.SQUARE_METRE.multiply(100));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Time> MINUTE = addUnit(Units.MINUTE);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Time> HOUR = addUnit(Units.HOUR);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Time> DAY = addUnit(Units.DAY);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Time> YEAR_TROPICAL = addUnit(Units.DAY.multiply(365.24219));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Time> YEAR_JULIAN = addUnit(Units.DAY.multiply(365.25));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Time> YEAR_GREGORIAN = addUnit(Units.DAY.multiply(365.2425));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Time> YEAR = addUnit(Units.DAY.multiply(365.25));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Time> WEEK = addUnit(Units.DAY.multiply(7));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Time> MONTH_SYNODAL = addUnit(Units.DAY.multiply(29.53059));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Time> MONTH_JULIAN = addUnit(YEAR_JULIAN.divide(12));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Time> MONTH_GREGORIAN = addUnit(YEAR_GREGORIAN.divide(12));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Time> MONTH = addUnit(YEAR_JULIAN.divide(12));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> TONNE = addUnit(Units.KILOGRAM.multiply(1000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Pressure> BAR = addUnit(Units.PASCAL.multiply(100000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> ATOMIC_MASS_UNIT = addUnit(SI.UNIFIED_ATOMIC_MASS);

    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Energy> ELECTRON_VOLT = addUnit(SI.ELECTRON_VOLT);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> ASTRONOMIC_UNIT = addUnit(SI.ASTRONOMICAL_UNIT);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> PARSEC = addUnit(Units.METRE.multiply(3.085678E16));

    /////////////////////////////////
    // NATURAL UNITS: UCUM 4.3 §32 //
    /////////////////////////////////
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Speed> VELOCITY_OF_LIGHT = addUnit(Units.METRE_PER_SECOND.multiply(299792458));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Action> PLANCK = addUnit(SI.JOULE_SECOND.multiply(6.6260755E-34));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<?> BOLTZMAN = addUnit(JOULE.divide(KELVIN).multiply(1.380658E-23));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<ElectricPermittivity> PERMITTIVITY_OF_VACUUM = addUnit(
	    SI.FARAD_PER_METRE.multiply(8.854187817E-12));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<MagneticPermeability> PERMEABILITY_OF_VACUUM = addUnit(
	    new ProductUnit<MagneticPermeability>(SI.NEWTON_PER_SQUARE_AMPERE.multiply(PI.multiply(4).divide(1E7))),
	    MagneticPermeability.class);
    // public static final Unit<MagneticPermeability> PERMEABILITY_OF_VACUUM =
    // addUnit(
    // new ProductUnit<MagneticPermeability>(Units.NEWTONS_PER_SQUARE_AMPERE
    // .multiply(PI).multiply(4).divide(1E7)),
    // MagneticPermeability.class);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<ElectricCharge> ELEMENTARY_CHARGE = addUnit(
	    Units.COULOMB.transform(((AbstractUnit<Energy>) SI.ELECTRON_VOLT).getSystemConverter()));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> ELECTRON_MASS = addUnit(GRAM.multiply(9.1093897E-28));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> PROTON_MASS = addUnit(GRAM.multiply(1.6726231E-24));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<?> NEWTON_CONSTANT_OF_GRAVITY = addUnit(
	    METER.pow(3).multiply(Units.KILOGRAM.pow(-1)).multiply(SECOND.pow(-2)).multiply(6.67259E-11));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Acceleration> ACCELERATION_OF_FREEFALL = addUnit(
	    Units.METRE_PER_SQUARE_SECOND.multiply(9.80665));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Pressure> ATMOSPHERE = addUnit(Units.PASCAL.multiply(101325));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> LIGHT_YEAR = addUnit(
	    new ProductUnit<Length>(VELOCITY_OF_LIGHT.multiply(YEAR_JULIAN)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Force> GRAM_FORCE = addUnit(
	    new ProductUnit<Force>(GRAM.multiply(ACCELERATION_OF_FREEFALL)));
    // POUND_FORCE contains a forward reference to avoirdupois pound weight, so
    // it has been moved after section §39 below

    /////////////////////////////
    // CGS UNITS: UCUM 4.3 §33 //
    /////////////////////////////
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<WaveNumber> KAYSER = addUnit(SI.RECIPROCAL_METRE.divide(100));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Acceleration> GAL = addUnit(
	    new ProductUnit<Acceleration>(CENTI(METER).divide(SECOND.pow(2))));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Force> DYNE = addUnit(
	    new ProductUnit<Force>(Units.GRAM.multiply(CENTI(Units.METRE).divide(Units.SECOND.pow(2)))));
    // public static final Unit<Force> DYNE = addUnit(new ProductUnit<Force>(
    // Units.GRAM.multiply(new
    // ProductUnit(CENTI(Units.METRE)).divide(Units.SECOND
    // .pow(2)))));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Energy> ERG = addUnit(new ProductUnit<Energy>(DYNE.multiply(CENTI(Units.METRE))));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<DynamicViscosity> POISE = addUnit(
	    new ProductUnit<DynamicViscosity>(DYNE.multiply(SECOND).divide(CENTI(Units.METRE).pow(2))));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<ElectricCurrent> BIOT = addUnit(AMPERE.multiply(10));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<KinematicViscosity> STOKES = addUnit(
	    new ProductUnit<KinematicViscosity>(CENTI(Units.METRE).pow(2).divide(Units.SECOND)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<MagneticFlux> MAXWELL = addUnit(Units.WEBER.divide(1E8));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<MagneticFluxDensity> GAUSS = addUnit(Units.TESLA.divide(1E4));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<MagneticFieldStrength> OERSTED = addUnit(
	    new ProductUnit<MagneticFieldStrength>(SI.AMPERE_PER_METRE.multiply(250).divide(PI)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<MagnetomotiveForce> GILBERT = addUnit(
	    new ProductUnit<MagnetomotiveForce>(OERSTED.multiply(CENTI(Units.METRE))));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Luminance> STILB = addUnit(
	    new ProductUnit<Luminance>(CANDELA.divide(CENTI(METER).pow(2))));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Luminance> LAMBERT = addUnit(new ProductUnit<Luminance>(STILB.divide(PI)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Illuminance> PHOT = addUnit(LUX.divide(1E4));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Radioactivity> CURIE = addUnit(Units.BECQUEREL.multiply(3.7E10));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<IonizingRadiation> ROENTGEN = addUnit(SI.COULOMB_PER_KILOGRAM.multiply(2.58E-4));
    // add later when JMQ issue fixed
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<RadiationDoseAbsorbed> RAD = addUnit(
	    new ProductUnit<RadiationDoseAbsorbed>(ERG.divide(Units.GRAM.multiply(100))));
    // public static final Unit<RadiationDoseAbsorbed> RAD = addUnit(new
    // ProductUnit<RadiationDoseAbsorbed>(
    // ERG.divide(Units.GRAM).multiply(100)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<RadiationDoseEffective> REM = addUnit(
	    new ProductUnit<RadiationDoseEffective>(ERG.divide(Units.GRAM.multiply(100))));
    // public static final Unit<RadiationDoseEffective> REM = addUnit(new
    // AlternateUnit<RadiationDoseEffective>(
    // RAD, RAD.getSymbol())); // TODO are symbols for RAD and REM same?
    /////////////////////////////////////////////////
    // INTERNATIONAL CUSTOMARY UNITS: UCUM 4.4 §34 //
    /////////////////////////////////////////////////
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> INCH_INTERNATIONAL = addUnit(CENTI(METER).multiply(254).divide(100));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> FOOT_INTERNATIONAL = addUnit(INCH_INTERNATIONAL.multiply(12));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> YARD_INTERNATIONAL = addUnit(FOOT_INTERNATIONAL.multiply(3));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> MILE_INTERNATIONAL = addUnit(FOOT_INTERNATIONAL.multiply(5280));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> FATHOM_INTERNATIONAL = addUnit(FOOT_INTERNATIONAL.multiply(6));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> NAUTICAL_MILE_INTERNATIONAL = addUnit(METER.multiply(1852));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Speed> KNOT_INTERNATIONAL = addUnit(
	    new ProductUnit<Speed>(NAUTICAL_MILE_INTERNATIONAL.divide(HOUR)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Area> SQUARE_INCH_INTERNATIONAL = addUnit(
	    new ProductUnit<Area>(INCH_INTERNATIONAL.pow(2)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Area> SQUARE_FOOT_INTERNATIONAL = addUnit(
	    new ProductUnit<Area>(FOOT_INTERNATIONAL.pow(2)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Area> SQUARE_YARD_INTERNATIONAL = addUnit(
	    new ProductUnit<Area>(YARD_INTERNATIONAL.pow(2)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> CUBIC_INCH_INTERNATIONAL = addUnit(
	    new ProductUnit<Volume>(INCH_INTERNATIONAL.pow(3)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> CUBIC_FOOT_INTERNATIONAL = addUnit(
	    new ProductUnit<Volume>(FOOT_INTERNATIONAL.pow(3)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> CUBIC_YARD_INTERNATIONAL = addUnit(
	    new ProductUnit<Volume>(YARD_INTERNATIONAL.pow(3)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> BOARD_FOOT_INTERNATIONAL = addUnit(CUBIC_INCH_INTERNATIONAL.multiply(144));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> CORD_INTERNATIONAL = addUnit(CUBIC_FOOT_INTERNATIONAL.multiply(128));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> MIL_INTERNATIONAL = addUnit(INCH_INTERNATIONAL.divide(1000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Area> CIRCULAR_MIL_INTERNATIONAL = addUnit(
	    new ProductUnit<Area>(MIL_INTERNATIONAL.pow(2).multiply(PI.divide(4))));
    // public static final Unit<Area> CIRCULAR_MIL_INTERNATIONAL = addUnit(new
    // ProductUnit<Area>(
    // MIL_INTERNATIONAL.pow(2).multiply(PI).divide(4)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> HAND_INTERNATIONAL = addUnit(INCH_INTERNATIONAL.multiply(4));
    //////////////////////////////////////////
    // US SURVEY LENGTH UNITS: UCUM 4.4 §35 //
    //////////////////////////////////////////
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> FOOT_US_SURVEY = addUnit(METER.multiply(1200).divide(3937));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> YARD_US_SURVEY = addUnit(FOOT_US_SURVEY.multiply(3));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> INCH_US_SURVEY = addUnit(FOOT_US_SURVEY.divide(12));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> ROD_US_SURVEY = addUnit(FOOT_US_SURVEY.multiply(33).divide(2));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> CHAIN_US_SURVEY = addUnit(ROD_US_SURVEY.multiply(4));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> LINK_US_SURVEY = addUnit(CHAIN_US_SURVEY.divide(100));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> RAMDEN_CHAIN_US_SURVEY = addUnit(FOOT_US_SURVEY.multiply(100));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> RAMDEN_LINK_US_SURVEY = addUnit(CHAIN_US_SURVEY.divide(100));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> FATHOM_US_SURVEY = addUnit(FOOT_US_SURVEY.multiply(6));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> FURLONG_US_SURVEY = addUnit(ROD_US_SURVEY.multiply(40));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> MILE_US_SURVEY = addUnit(FURLONG_US_SURVEY.multiply(8));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Area> ACRE_US_SURVEY = addUnit(new ProductUnit<Area>(ROD_US_SURVEY.pow(2)).multiply(160));
    // public static final Unit<Area> ACRE_US_SURVEY = addUnit(new
    // ProductUnit<Area>(
    // ROD_US_SURVEY.pow(2).multiply(160)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Area> SQUARE_ROD_US_SURVEY = addUnit(new ProductUnit<Area>(ROD_US_SURVEY.pow(2)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Area> SQUARE_MILE_US_SURVEY = addUnit(new ProductUnit<Area>(MILE_US_SURVEY.pow(2)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Area> SECTION_US_SURVEY = addUnit(new ProductUnit<Area>(MILE_US_SURVEY.pow(2)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Area> TOWNSHP_US_SURVEY = addUnit(SECTION_US_SURVEY.multiply(36));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> MIL_US_SURVEY = addUnit(INCH_US_SURVEY.divide(1000));
    /////////////////////////////////////////////////
    // BRITISH IMPERIAL LENGTH UNITS: UCUM 4.4 §36 //
    /////////////////////////////////////////////////
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> INCH_BRITISH = addUnit(CENTI(METER).multiply(2539998).divide(1000000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> FOOT_BRITISH = addUnit(INCH_BRITISH.multiply(12));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> ROD_BRITISH = addUnit(FOOT_BRITISH.multiply(33).divide(2));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> CHAIN_BRITISH = addUnit(ROD_BRITISH.multiply(4));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> LINK_BRITISH = addUnit(CHAIN_BRITISH.divide(100));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> FATHOM_BRITISH = addUnit(FOOT_BRITISH.multiply(6));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> PACE_BRITISH = addUnit(FOOT_BRITISH.multiply(5).divide(2));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> YARD_BRITISH = addUnit(FOOT_BRITISH.multiply(3));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> MILE_BRITISH = addUnit(FOOT_BRITISH.multiply(5280));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> NAUTICAL_MILE_BRITISH = addUnit(FOOT_BRITISH.multiply(6080));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Speed> KNOT_BRITISH = addUnit(new ProductUnit<Speed>(NAUTICAL_MILE_BRITISH.divide(HOUR)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Area> ACRE_BRITISH = addUnit(new ProductUnit<Area>(YARD_BRITISH.pow(2)).multiply(4840));
    // public static final Unit<Area> ACRE_BRITISH = addUnit(new
    // ProductUnit<Area>(
    // YARD_BRITISH.pow(2).multiply(4840)));
    ///////////////////////////////////
    // US VOLUME UNITS: UCUM 4.4 §37 //
    ///////////////////////////////////
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> GALLON_US = addUnit(CUBIC_INCH_INTERNATIONAL.multiply(231), "Queen Anne's wine gallon", "gal_us");
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> BARREL_US = addUnit(GALLON_US.multiply(42), "barrel", "bbl_us");
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> QUART_US = addUnit(GALLON_US.divide(4));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> PINT_US = addUnit(QUART_US.divide(2));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> GILL_US = addUnit(PINT_US.divide(4));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> FLUID_OUNCE_US = addUnit(GILL_US.divide(4));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> FLUID_DRAM_US = addUnit(FLUID_OUNCE_US.divide(8));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> MINIM_US = addUnit(FLUID_DRAM_US.divide(60));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> CORD_US = addUnit(CUBIC_FOOT_INTERNATIONAL.multiply(128));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> BUSHEL_US = addUnit(CUBIC_INCH_INTERNATIONAL.multiply(215042).divide(100));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> GALLON_WINCHESTER = addUnit(BUSHEL_US.divide(8));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> PECK_US = addUnit(BUSHEL_US.divide(4));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> DRY_QUART_US = addUnit(PECK_US.divide(8));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> DRY_PINT_US = addUnit(DRY_QUART_US.divide(2));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> TABLESPOON_US = addUnit(FLUID_OUNCE_US.divide(2));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> TEASPOON_US = addUnit(TABLESPOON_US.divide(3));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> CUP_US = addUnit(TABLESPOON_US.multiply(16));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> METRIC_FLUID_OUNCE_US = addUnit(MILLI(LITER).multiply(30));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> METRIC_CUP_US = addUnit(MILLI(LITER).multiply(240));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> METRIC_TEASPOON_CUP_US = addUnit(MILLI(LITER).multiply(5));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> METRIC_TABLESPOON_CUP_US = addUnit(MILLI(LITER).multiply(15));
    /////////////////////////////////////////////////
    // BRITISH IMPERIAL VOLUME UNITS: UCUM 4.4 §38 //
    /////////////////////////////////////////////////
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> GALLON_BRITISH = addUnit(LITER.multiply(454609).divide(100000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> PECK_BRITISH = addUnit(GALLON_BRITISH.multiply(2));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> BUSHEL_BRITISH = addUnit(PECK_BRITISH.multiply(4));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> QUART_BRITISH = addUnit(GALLON_BRITISH.divide(4));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> PINT_BRITISH = addUnit(QUART_BRITISH.divide(2));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> GILL_BRITISH = addUnit(PINT_BRITISH.divide(4));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> FLUID_OUNCE_BRITISH = addUnit(GILL_BRITISH.divide(5));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> FLUID_DRAM_BRITISH = addUnit(FLUID_OUNCE_BRITISH.divide(8));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> MINIM_BRITISH = addUnit(FLUID_DRAM_BRITISH.divide(60));
    ////////////////////////////////////////////
    // AVOIRDUPOIS WEIGHT UNITS: UCUM 4.4 §39 //
    ////////////////////////////////////////////
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> GRAIN = addUnit(MILLI(GRAM).multiply(6479891).divide(100000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> POUND = addUnit(GRAIN.multiply(7000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> OUNCE = addUnit(POUND.divide(16));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> DRAM = addUnit(OUNCE.divide(16));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> SHORT_HUNDREDWEIGHT = addUnit(POUND.multiply(100));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> LONG_HUNDREDWEIGHT = addUnit(POUND.multiply(112));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> SHORT_TON = addUnit(SHORT_HUNDREDWEIGHT.multiply(20));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> LONG_TON = addUnit(LONG_HUNDREDWEIGHT.multiply(20));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> STONE = addUnit(POUND.multiply(14));
    // CONTINUED FROM SECTION §32
    // contains a forward reference to POUND, so we had to move it here, below
    // section §39
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Force> POUND_FORCE = addUnit(
	    POUND.multiply(ACCELERATION_OF_FREEFALL).asType(Force.class));

    /////////////////////////////////////
    // TROY WEIGHT UNITS: UCUM 4.4 §40 //
    /////////////////////////////////////
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> PENNYWEIGHT_TROY = addUnit(GRAIN.multiply(24));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> OUNCE_TROY = addUnit(PENNYWEIGHT_TROY.multiply(20));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> POUND_TROY = addUnit(OUNCE_TROY.multiply(12));
    /////////////////////////////////////////////
    // APOTECARIES' WEIGHT UNITS: UCUM 4.4 §41 //
    /////////////////////////////////////////////
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> SCRUPLE_APOTHECARY = addUnit(GRAIN.multiply(20));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> DRAM_APOTHECARY = addUnit(SCRUPLE_APOTHECARY.multiply(3));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> OUNCE_APOTHECARY = addUnit(DRAM_APOTHECARY.multiply(8));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> POUND_APOTHECARY = addUnit(OUNCE_APOTHECARY.multiply(12));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> METRIC_OUNCE = addUnit(GRAM.multiply(28));

    /////////////////////////////////////////////
    // TYPESETTER'S LENGTH UNITS: UCUM 4.4 §42 //
    /////////////////////////////////////////////
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> LINE = addUnit(INCH_INTERNATIONAL.divide(12));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> POINT = addUnit(LINE.divide(6));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> PICA = addUnit(POINT.multiply(12));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> POINT_PRINTER = addUnit(INCH_INTERNATIONAL.multiply(13837).divide(1000000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> PICA_PRINTER = addUnit(POINT_PRINTER.multiply(12));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> PIED = addUnit(CENTI(METER).multiply(3248).divide(100));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> POUCE = addUnit(PIED.divide(12));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> LIGNE = addUnit(POUCE.divide(12));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> DIDOT = addUnit(LIGNE.divide(6));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> CICERO = addUnit(DIDOT.multiply(12));
    
    //////////////////////////////////////
    // OTHER LEGACY UNITS: UCUM 4.5 §43 //
    //////////////////////////////////////
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Temperature> RANKINE = addUnit(KELVIN.divide(9).multiply(5));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Temperature> FAHRENHEIT = addUnit(RANKINE.shift(459.67));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Temperature> REAUMUR = addUnit((KELVIN.multiply(4).divide(5)).shift(218.52));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Energy> CALORIE_AT_15C = addUnit(JOULE.multiply(41858).divide(10000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Energy> CALORIE_AT_20C = addUnit(JOULE.multiply(41819).divide(10000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Energy> CALORIE_MEAN = addUnit(JOULE.multiply(419002).divide(100000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Energy> CALORIE_INTERNATIONAL_TABLE = addUnit(JOULE.multiply(41868).divide(10000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Energy> CALORIE_THERMOCHEMICAL = addUnit(JOULE.multiply(4184).divide(1000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Energy> CALORIE = addUnit(CALORIE_THERMOCHEMICAL);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Energy> CALORIE_FOOD = addUnit(KILO(CALORIE_THERMOCHEMICAL));

    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Energy> BTU_AT_39F = addUnit(KILO(JOULE).multiply(105967).divide(100000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Energy> BTU_AT_59F = addUnit(KILO(JOULE).multiply(105480).divide(100000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Energy> BTU_AT_60F = addUnit(KILO(JOULE).multiply(105468).divide(100000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Energy> BTU_MEAN = addUnit(KILO(JOULE).multiply(105587).divide(100000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Energy> BTU_INTERNATIONAL_TABLE = addUnit(
	    KILO(JOULE).multiply(105505585262L).divide(100000000000L));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Energy> BTU_THERMOCHEMICAL = addUnit(KILO(JOULE).multiply(105435).divide(100000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Energy> BTU = addUnit(BTU_THERMOCHEMICAL);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Power> HORSEPOWER = addUnit(
	    new ProductUnit<Power>(FOOT_INTERNATIONAL.multiply(POUND_FORCE).divide(SECOND)));

    ////////////////////////////////////////////
    // CLINICAL MEDICINE UNITS: UCUM 4.5 §44 //
    ///////////////////////////////////////////
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Pressure> METER_OF_WATER_COLUMN = addUnit(KILO(PASCAL).multiply(980665).divide(100000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Pressure> METER_OF_MERCURY_COLUMN = addUnit(KILO(PASCAL).multiply(1333220).divide(10000));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Pressure> INCH_OF_WATER_COLUMN = addUnit(
	    new ProductUnit<Pressure>(METER_OF_WATER_COLUMN.multiply(INCH_INTERNATIONAL).divide(METER)));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Pressure> INCH_OF_MERCURY_COLUMN = addUnit(
	    new ProductUnit<Pressure>(METER_OF_MERCURY_COLUMN.multiply(INCH_INTERNATIONAL).divide(METER)));

    public static final Unit<Drag> PERIPHERAL_VASCULAR_RESISTANCE = addUnit(
	    MILLI(METER_OF_MERCURY_COLUMN).multiply(SECOND).divide(MILLI(LITER)).asType(Drag.class));
    public static final Unit<Drag> WOOD = addUnit(MILLI(METER_OF_MERCURY_COLUMN).multiply(MINUTE).divide(LITER).asType(Drag.class));
    // public static final Unit DIOPTER = addUnit(ONE.divide(METER));
    // public static final Unit PRISM_DIOPTER =
    // addUnit(ONE.multiply(100).multiply(Math.tan(1)));
    // public static final Unit PERCENT_OF_SLOPE =
    // addUnit(ONE.multiply(100).multiply(Math.tan(1)));
    // public static final Unit MESH = addUnit(ONE.divide(INCH_INTERNATIONAL));
    // public static final Unit CHARRIERE = addUnit(MILLI(METER).divide(3));

    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Volume> DROP = addUnit(MILLI(LITER).divide(20));

    // public static final Unit HOUNSFIELD = addUnit(ONE);
    // public static final Unit METABOLIC_EQUIVALENT =
    // addUnit(MILLI(LITER).divide(MINUTE).divide(KILO(GRAM)));

    // public static final Unit HOMEOPATHIC_POTENCY_OF_DECIMAL =
    // addUnit(ONE.multiply(-1).multiply(Math.log10(1)));
    // public static final Unit HOMEOPATHIC_POTENCY_OF_CENTESIMAL =
    // addUnit(ONE.multiply(-1).multiply(Math.log(1)).divide(Math.log(100)));
    // public static final Unit HOMEOPATHIC_POTENCY_OF_MILLESIMAL =
    // addUnit(ONE.multiply(-1).multiply(Math.log(1)).divide(Math.log(1000)));
    // public static final Unit HOMEOPATHIC_POTENCY_OF_QUINTALLESIMAL =
    // addUnit(ONE.multiply(-1).multiply(Math.log(1)).divide(Math.log(50000)));

    // public static final Unit HOMEOPATHIC_POTENCY_OF_DECIMAL_HAHNEMANNIAN =
    // UNDEFINED;
    // public static final Unit HOMEOPATHIC_POTENCY_OF_CENTESIMAL_HAHNEMANNIAN =
    // UNDEFINED;
    // public static final Unit HOMEOPATHIC_POTENCY_OF_MILLESIMAL_HAHNEMANNIAN =
    // UNDEFINED;
    // public static final Unit
    // HOMEOPATHIC_POTENCY_OF_QUINTAMILLESIMAL_HAHNEMANNIAN = UNDEFINED;
    // public static final Unit HOMEOPATHIC_POTENCY_OF_DECIMAL_KORSAKOVIAN =
    // UNDEFINED;
    // public static final Unit HOMEOPATHIC_POTENCY_OF_CENTESIMAL_KORSAKOVIAN =
    // UNDEFINED;
    // public static final Unit HOMEOPATHIC_POTENCY_OF_MILLESIMAL_KORSAKOVIAN =
    // UNDEFINED;
    // public static final Unit
    // HOMEOPATHIC_POTENCY_OF_QUINTAMILLESIMAL_KORSAKOVIAN = UNDEFINED;

    //////////////////////////////////////////////////
    // CHEMICAL AND BIOCHEMICAL UNITS: UCUM 4.5 §45 //
    //////////////////////////////////////////////////
    /**
     * amount of substance
     * @since 2.4
     */
    public static final Unit<AmountOfSubstance> EQUIVALENTS = addUnit(new AlternateUnit<AmountOfSubstance>(MOLE, "eq"));
    /**
     * amount of substance (dissolved particles)
     * @since 2.4
     */
    public static final Unit<AmountOfSubstance> OSMOLE = addUnit(new AlternateUnit<AmountOfSubstance>(MOLE, "osm"));

    public static final Unit<Acidity> PH = addUnit(
	    MOLE.divide(LITER).transform(new LogConverter(10)).multiply(-1).asType(Acidity.class));

    @SuppressWarnings("unchecked")
    public static final Unit<Concentration<Mass>> GRAM_PERCENT = addUnit(
	    GRAM.divide(DECI(LITER)).asType(Concentration.class));

    /**
     * sedimentation coefficient
     * @since 2.4
     */
    public static final Unit<Time> SVEDBERG = addUnit(SECOND.multiply(1E-13));

    public static final Unit<Dimensionless> HIGH_POWER_FIELD = addUnit(ONE);
    public static final Unit<Dimensionless> LOW_POWER_FIELD = addUnit(ONE.multiply(100));

	/**
	 * The SI unit for catalytic activity (standard name <code>kat</code>).
	 * @since 2.3
	 */
	public static final Unit<CatalyticActivity> KATAL = addUnit(Units.KATAL);

	/**
	 * catalytic activity
	 * @since 2.3
	 */	
    public static final Unit<CatalyticActivity> UNIT = addUnit(MICRO(MOLE).divide(MINUTE).asType(CatalyticActivity.class));

	/**
	 * arbitrary
	 * @since 2.3
	 */	
    public static final Unit<Dimensionless> INTERNATIONAL_UNIT = addUnit(new AlternateUnit<Dimensionless>(ONE, "IU"), "International Unit", "IU");
    
	/**
	 * arbitrary
	 * @since 2.3
	 */	
    public static final Unit<Dimensionless> INTERNATIONAL_UNIT_ALT = addUnit(new AlternateUnit<Dimensionless>(INTERNATIONAL_UNIT, "i.U."), "International Unit", "i.U.");
    
	/**
	 * arbitrary
	 * @since 2.3
	 */	
    public static final Unit<Dimensionless> ARBITRARY_UNIT = addUnit(new AlternateUnit<Dimensionless>(ONE, "arb. U"), "Arbitrary Unit", "arb. U");
    // public static final Unit US_PHARMACOPEIA = UNDEFINED;
    // public static final Unit GPL = UNDEFINED;
    // public static final Unit MPL = UNDEFINED;
    // public static final Unit APL = UNDEFINED;
    // public static final Unit BETHESDA = UNDEFINED;
    // public static final Unit ANTI_FACTOR_XA = UNDEFINED;
    // public static final Unit TODD = UNDEFINED;
    // public static final Unit DYE = UNDEFINED;
    // public static final Unit SOMOGYI = UNDEFINED;
    // public static final Unit BODANSKY = UNDEFINED;
    // public static final Unit KING_ARMSTRONG = UNDEFINED;
    // public static final Unit KUNKEL = UNDEFINED;
    // public static final Unit MAC_LAGAN = UNDEFINED;
    // public static final Unit TUBERCULIN = UNDEFINED;
    // public static final Unit CELL_CULTURE_INFECTIOUS_50_PERCENT_DOSE =
    // UNDEFINED;
    // public static final Unit TISSUE_CULTURE_INFECTIOUS_50_PERCENT_DOSE =
    // UNDEFINED;
    // public static final Unit EMBRYO_CULTURE_INFECTIOUS_50_PERCENT_DOSE =
    // UNDEFINED;
    // public static final Unit PLAQUE_FORMING = UNDEFINED;
    // public static final Unit FOCUS_FORMING = UNDEFINED;
    // public static final Unit COLONY_FORMING = UNDEFINED;
    // public static final Unit INDEX_OF_REACTIVITY = UNDEFINED;
    // public static final Unit BIOEQUIVALENT_ALLERGEN = UNDEFINED;
    // public static final Unit ALLERGEN = UNDEFINED;
    // public static final Unit ALLERGEN_FOR_AMBROSIA_ARTEMISIIFOLIA =
    // UNDEFINED;
    // public static final Unit PROTEIN_NITROGEN = UNDEFINED;
    // public static final Unit LIMIT_OF_FLOCCULATION = UNDEFINED;
    // public static final Unit D_ANTIGEN = UNDEFINED;
    // public static final Unit FIBRINOGEN_EQUIVALENT = UNDEFINED;
    // public static final Unit ELISA = UNDEFINED;
    // public static final Unit EHRLICH = UNDEFINED;
    // public static final Unit CHEMICAL = UNDEFINED;

    /////////////////////////////////
    // LEVELS UNITS: UCUM 4.5 §46 //
    ////////////////////////////////
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static final Unit<Level<Dimensionless>> NEPER = (Unit) addUnit(
	    ONE.transform(new LogConverter(Math.E)));
    /**
     * A logarithmic unit used to describe a power {@link Level} ratio (standard
     * name <code>dB</code>).
     */
    // public static final Unit<Level<Power>> DECIBEL = addUnit(NEPER
    // .transform(new LogConverter(10).inverse().concatenate(
    // RationalConverter.of(1d, 10d))));

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static final Unit<Level<Dimensionless>> BEL = (Unit) addUnit(
	    ONE.transform(new LogConverter(10)));

    @SuppressWarnings("unchecked")
    public static final Unit<Level<Pressure>> BEL_SOUND = addUnit(
	    PASCAL.divide(1E5).multiply(2).transform(new LogConverter(10)).multiply(2).asType(Level.class));

    @SuppressWarnings("unchecked")
    public static final Unit<Level<ElectricPotential>> BEL_VOLT = addUnit(
	    VOLT.transform(new LogConverter(10)).multiply(2).asType(Level.class));

    @SuppressWarnings("unchecked")
    public static final Unit<Level<ElectricPotential>> BEL_MILLIVOLT = addUnit(
	    MILLI(VOLT).transform(new LogConverter(10)).multiply(2).asType(Level.class));

    @SuppressWarnings("unchecked")
    public static final Unit<Level<ElectricPotential>> BEL_MICROVOLT = addUnit(
	    MICRO(VOLT).transform(new LogConverter(10)).multiply(2).asType(Level.class));

    @SuppressWarnings("unchecked")
    public static final Unit<Level<ElectricPotential>> BEL_10_NANOVOLT = addUnit(
	    NANO(VOLT).multiply(10).transform(new LogConverter(10)).multiply(2).asType(Level.class));

    @SuppressWarnings("unchecked")
    public static final Unit<Level<Power>> BEL_WATT = addUnit(
	    WATT.transform(new LogConverter(10)).asType(Level.class));

    @SuppressWarnings("unchecked")
    /** power level */
    public static final Unit<Level<Power>> BEL_KILOWATT = addUnit(
	    KILO(WATT).transform(new LogConverter(10)).asType(Level.class));

    ///////////////////////////////////////
    // MISCELLANEOUS UNITS: UCUM 4.5 §47 //
    ///////////////////////////////////////
    /** temporary helper for MHO */
    private static final Unit<? extends Quantity<?>> TMP_MHO = SIEMENS.alternate("mho");

    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */ 
    public static final Unit<Volume> STERE = addUnit(new TransformedUnit<Volume>("st", Units.CUBIC_METRE, Units.CUBIC_METRE, MultiplyConverter.identity()));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> ANGSTROM = addUnit(NANO(METER).divide(10));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Area> BARN = addUnit(new ProductUnit<Area>(FEMTO(METER).pow(2)).multiply(100));

    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Pressure> ATMOSPHERE_TECHNICAL = addUnit(
	    new ProductUnit<Pressure>(KILO(GRAM_FORCE).divide(CENTI(METER).pow(2))));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<ElectricConductance> MHO = addUnit(
	    new AlternateUnit<ElectricConductance>(TMP_MHO, TMP_MHO.getSymbol()));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Pressure> POUND_PER_SQUARE_INCH = addUnit(
	    new ProductUnit<Pressure>(POUND_FORCE.divide(INCH_INTERNATIONAL.pow(2))));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Angle> CIRCLE = addUnit(new ProductUnit<Angle>(PI.multiply(RADIAN.multiply(2))));

    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<SolidAngle> SPHERE = addUnit(
	    new ProductUnit<SolidAngle>(PI.multiply(STERADIAN.multiply(4))));

    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Mass> CARAT_METRIC = addUnit(GRAM.divide(5));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Dimensionless> CARAT_GOLD = addUnit(ONE.divide(24));
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Length> SMOOT = addUnit(INCH_INTERNATIONAL.multiply(67));

    ////////////////////////////////////////////////
    // INFORMATION TECHNOLOGY UNITS: UCUM 4.6 §48 //
    ////////////////////////////////////////////////
    /**
     * The unit for binary information (standard name <code>bit</code>).
     * As per <a href="http://unitsofmeasure.org/">UCUM</a> standard.
     */
    public static final Unit<Information> BIT = addUnit(new AlternateUnit<Information>(ONE, "bit"), Information.class);
    
    /**
     * The bit is defined twice. One definition with a subscript letter ‘s‘ is defined as the logarithmus dualis of the number of distinct signals. However this unit can not practically be used to express more than 1000 bits. Especially when the bit is used to express transmission rate or memory capacities, floating point registers would quickly overflow. Therefore we define a second symbol for bit, without the suffix, to be the dimensionless unit 1.
     * @since 2.3
     */
    public static final Unit<Information> BIT_S = addUnit(new AlternateUnit<Information>(BIT, "bit\\u2082"));
    
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<Information> BYTE = addUnit(BIT.multiply(8));
    /**
     * The SI unit for binary information rate (standard name
     * <code>bit/s</code>).
     */
    protected static final ProductUnit<InformationRate> BITS_PER_SECOND = addUnit(
	    new ProductUnit<InformationRate>(BIT.divide(SECOND)), InformationRate.class);
    /** As per <a href="http://unitsofmeasure.org/">UCUM</a> standard. */
    public static final Unit<InformationRate> BAUD = addUnit(BITS_PER_SECOND);

    /////////////////////
    // Collection View //
    /////////////////////

    @Override
    public String getName() {
        return "Unified Code for Units of Measure";
    }

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
    private static <U extends AbstractUnit<?>> U addUnit(U unit, Class<? extends Quantity<?>> type) {
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
     * as label.
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
    
    ////////////////////////////////////////////////////////////////////////////
    // Label adjustments for UCUM system
    static {
		SimpleUnitFormat.getInstance().label(ATOMIC_MASS_UNIT, "AMU");
		//SimpleUnitFormat.getInstance().label(LITER, "L");
		//SimpleUnitFormat.getInstance().label(LITER_DM3, "l");
		SimpleUnitFormat.getInstance().label(OUNCE, "oz");
		SimpleUnitFormat.getInstance().label(POUND, "lb");
		SimpleUnitFormat.getInstance().label(PLANCK, "h");
		// TODO maybe we can find a better solution, but it would require to
		// "harvest" the entire UCUMFormat ResourceBundle and label every
		// matching UCUM unit in a loop.
    }
}
