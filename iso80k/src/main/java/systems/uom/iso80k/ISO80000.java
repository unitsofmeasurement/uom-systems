/*
 *  Unit-API - Units of Measurement API for Java
 *  Copyright (c) 2005-2015, Jean-Marie Dautelle, Werner Keil, V2COM.
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
 * 3. Neither the name of JSR-363 nor the names of its contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
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
package systems.uom.iso80k;

import static tec.units.ri.unit.MetricPrefix.*;
import static tec.units.ri.unit.Units.ONE;
import si.uom.quantity.Action;
import si.uom.quantity.DynamicViscosity;
import si.uom.quantity.ElectricPermittivity;
import si.uom.quantity.IonizingRadiation;
import si.uom.quantity.KinematicViscosity;
import si.uom.quantity.MagneticPermeability;
import si.uom.quantity.MagnetomotiveForce;
import si.uom.quantity.WaveNumber;
import systems.uom.quantity.Information;
import systems.uom.quantity.InformationRate;
import systems.uom.quantity.Level;
import si.uom.SI;
import tec.units.ri.*;
import tec.units.ri.format.SimpleUnitFormat;
import tec.units.ri.function.LogConverter;
import tec.units.ri.function.PiMultiplierConverter;
import tec.units.ri.function.RationalConverter;
import tec.units.ri.unit.AlternateUnit;
import tec.units.ri.unit.ProductUnit;
import tec.units.ri.unit.Units;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.*;

/**
 * <p>
 * This class contains {@link SI} and Non-SI units as defined in the <a
 * href="http//www.iso.org/"> ISO 80000</a>.
 * </p>
 *
 * <p>
 * Compatibility with {@link SI} units has been given priority over strict
 * adherence to the standard. We have attempted to note every place where the
 * definitions in this class deviate from the ISO80000 standard, but such notes are
 * likely to be incomplete.
 * </p>
 * 
 * @noextend This class is not intended to be extended by clients.
 *
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @see <a href="http://www.iso.org">ISO80000</a>
 * @see <a href="https://en.wikipedia.org/wiki/ISO_80000-3">ISO 80000-3</a>
 * @version 0.4, $Date: 2015-10-20 $
 */
public final class ISO80000 extends AbstractSystemOfUnits {

	/**
	 * The singleton instance.
	 */
	private static final ISO80000 INSTANCE = new ISO80000();

	/**
	 * Default constructor (prevents this class from being instantiated).
	 */
	private ISO80000() {
	}

	/**
	 * Returns the singleton instance of this class.
	 *
	 * @return the ISO80000 system instance.
	 */
	public static ISO80000 getInstance() {
		return INSTANCE;
	}

	// ////////////////////////////
	// BASE UNITS: ISO80000 4.2 §25 //
	// ////////////////////////////
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Length> METRE = addUnit(Units.METRE);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Time> SECOND = addUnit(Units.SECOND);
	/**
	 * We deviate slightly from the standard here, to maintain compatibility
	 * with the existing SI units. In ISO80000, the gram is the base unit of mass,
	 * rather than the kilogram. This doesn't have much effect on the units
	 * themselves, but it does make formatting the units a challenge.
	 */
	public static final Unit<Mass> GRAM = addUnit(Units.GRAM);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Angle> RADIAN = addUnit(Units.RADIAN);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Temperature> KELVIN = addUnit(Units.KELVIN);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<ElectricCharge> COULOMB = addUnit(Units.COULOMB);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<LuminousIntensity> CANDELA = addUnit(Units.CANDELA);
	
	// /////////////////////////////////////////////
	// DIMENSIONLESS DERIVED UNITS: ISO80000 4.3 §26 //
	// /////////////////////////////////////////////
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Dimensionless> TRILLIONS = addUnit(ONE
			.multiply(1000000000000L));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Dimensionless> BILLIONS = addUnit(ONE
			.multiply(1000000000));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Dimensionless> MILLIONS = addUnit(ONE
			.multiply(1000000));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Dimensionless> THOUSANDS = addUnit(ONE
			.multiply(1000));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Dimensionless> HUNDREDS = addUnit(ONE
			.multiply(100));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Dimensionless> PI = addUnit(ONE
			.transform(new PiMultiplierConverter()));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Dimensionless> PERCENT = addUnit(ONE
			.divide(100));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Dimensionless> PER_THOUSAND = addUnit(ONE
			.divide(1000));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Dimensionless> PER_MILLION = addUnit(ONE
			.divide(1000000));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Dimensionless> PER_BILLION = addUnit(ONE
			.divide(1000000000));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Dimensionless> PER_TRILLION = addUnit(ONE
			.divide(1000000000000L));
	// //////////////////////////
	// SI UNITS: ISO80000 4.3 §27 //
	// //////////////////////////
	/**
	 * We deviate slightly from the standard here, to maintain compatibility
	 * with the existing SI units. In ISO80000, the mole is no longer a base unit,
	 * but is defined as <code>Unit.ONE.multiply(6.0221367E23)</code>.
	 */
	public static final Unit<AmountOfSubstance> MOLE = addUnit(Units.MOLE);
	/**
	 * We deviate slightly from the standard here, to maintain compatibility
	 * with the existing SI units. In ISO80000, the steradian is defined as
	 * <code>RADIAN.pow(2)</code>.
	 */
	public static final Unit<SolidAngle> STERADIAN = addUnit(Units.STERADIAN);
	/** As per <a href="https://en.wikipedia.org/wiki/ISO_80000-8">ISO80000-8</a> standard. */
	public static final Unit<Frequency> HERTZ = addUnit(Units.HERTZ);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Force> NEWTON = addUnit(Units.NEWTON);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Pressure> PASCAL = addUnit(Units.PASCAL);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Energy> JOULE = addUnit(Units.JOULE);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Power> WATT = addUnit(Units.WATT);
	/**
	 * We deviate slightly from the standard here, to maintain compatability
	 * with the existing SI units. In ISO80000, the ampere is defined as
	 * <code>COULOMB.divide(SECOND)</code>.
	 */
	public static final Unit<ElectricCurrent> AMPERE = addUnit(Units.AMPERE);
//	public static final Unit<MagnetomotiveForce> AMPERE_TURN = addUnit(Units.AMPERE_TURN);
	/**
	 * We deviate slightly from the standard here, to maintain compatibility
	 * with the existing SI units. In ISO80000, the volt is defined as
	 * <code>JOULE.divide(COULOMB)</code>.
	 */
	public static final Unit<ElectricPotential> VOLT = addUnit(Units.VOLT);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<ElectricCapacitance> FARAD = addUnit(Units.FARAD);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<ElectricResistance> OHM = addUnit(Units.OHM);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<ElectricConductance> SIEMENS = addUnit(Units.SIEMENS);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<MagneticFlux> WEBER = addUnit(Units.WEBER);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Temperature> CELSIUS = addUnit(Units.CELSIUS);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<MagneticFluxDensity> TESLA = addUnit(Units.TESLA);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<ElectricInductance> HENRY = addUnit(Units.HENRY);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<LuminousFlux> LUMEN = addUnit(Units.LUMEN);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Illuminance> LUX = addUnit(Units.LUX);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Radioactivity> BECQUEREL = addUnit(Units.BECQUEREL);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<RadiationDoseAbsorbed> GRAY = addUnit(Units.GRAY);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<RadiationDoseEffective> SIEVERT = addUnit(Units.SIEVERT);

	/**
	 * Holds the Avogadro constant.
	 */
	private static final double AVOGADRO_CONSTANT = 6.02214199e23; // (1/mol).

	/**
	 * Holds the electric charge of one electron.
	 */
	private static final double ELEMENTARY_CHARGE_CONSTANT = 1.602176462e-19; // (C).
	
	// ///////////////////
	// Electric charge //
	// ///////////////////
	/**
	 * A unit of electric charge equal to the charge on one electron (standard
	 * name <code>e</code>).
	 */
	static final Unit<ElectricCharge> E = addUnit(COULOMB
			.multiply(ELEMENTARY_CHARGE_CONSTANT));

	/**
	 * A unit of electric charge equal to equal to the product of Avogadro's
	 * number (see {@link SI#MOLE}) and the charge (1 e) on a single electron
	 * (standard name <code>Fd</code>).
	 */
	static final Unit<ElectricCharge> FARADAY = addUnit(COULOMB
			.multiply(ELEMENTARY_CHARGE_CONSTANT * AVOGADRO_CONSTANT)); // e/mol

	/**
	 * A unit of electric charge which exerts a force of one dyne on an equal
	 * charge at a distance of one centimeter (standard name <code>Fr</code>).
	 */
	static final Unit<ElectricCharge> FRANKLIN = addUnit(COULOMB
			.multiply(3.3356e-10));
	
	// /////////////////////////////////////////////////////////////////////
	// OTHER UNITS FROM ISO 1000, ISO 2955, AND ANSI X3.50: ISO80000 4.3 §28 //
	// /////////////////////////////////////////////////////////////////////
	// The order of GON and DEGREE has been inverted because GON is defined in
	// terms of DEGREE
	/**
	 * We deviate slightly from the standard here, to maintain compatibility
	 * with the existing NonSI units. In ISO80000, the degree is defined as
	 * <code>PI.multiply(RADIAN.divide(180))</code>.
	 */
	public static final Unit<Angle> DEGREE = addUnit(SI.DEGREE_ANGLE);
	/** As per <a href="https://en.wikipedia.org/wiki/ISO_80000-3">ISO80000-3</a> standard. */
	public static final Unit<Angle> GRADE = addUnit(SI.DEGREE_ANGLE
			.multiply(0.9));
	/** As per <a href="https://en.wikipedia.org/wiki/ISO_80000-3">ISO80000-3</a> standard. */
	public static final Unit<Angle> GON = GRADE;
	/** As per <a href="https://en.wikipedia.org/wiki/ISO_80000-3">ISO80000-3</a> standard. */
	public static final Unit<Angle> MINUTE_ANGLE = addUnit(SI.MINUTE_ANGLE);
	/** As per <a href="https://en.wikipedia.org/wiki/ISO_80000-3">ISO80000-3</a> standard. */
	public static final Unit<Angle> SECOND_ANGLE = addUnit(SI.SECOND_ANGLE);
	/** As per <a href="https://en.wikipedia.org/wiki/ISO_80000-3">ISO80000-3</a> standard. */
	public static final Unit<Volume> LITRE = addUnit(Units.LITRE);
	/** As per <a href="https://en.wikipedia.org/wiki/ISO_80000-3">ISO80000-3</a> standard. */
	public static final Unit<Area> ARE = addUnit(Units.SQUARE_METRE.multiply(100));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Time> MINUTE = addUnit(Units.MINUTE);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Time> HOUR = addUnit(Units.HOUR);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Time> DAY = addUnit(Units.DAY);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Time> YEAR_TROPICAL = addUnit(Units.DAY
			.multiply(365.24219));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Time> YEAR_JULIAN = addUnit(Units.DAY
			.multiply(365.25));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Time> YEAR_GREGORIAN = addUnit(Units.DAY
			.multiply(365.2425));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Time> YEAR = addUnit(Units.DAY.multiply(365.25));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Time> MONTH_SYNODAL = addUnit(Units.DAY
			.multiply(29.53059));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Time> MONTH_JULIAN = addUnit(YEAR_JULIAN
			.divide(12));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Time> MONTH_GREGORIAN = addUnit(YEAR_GREGORIAN
			.divide(12));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Time> MONTH = addUnit(YEAR_JULIAN.divide(12));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Mass> TONNE = addUnit(Units.KILOGRAM.multiply(1000));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Pressure> BAR = addUnit(Units.PASCAL.multiply(100000));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Mass> ATOMIC_MASS_UNIT = addUnit(SI.UNIFIED_ATOMIC_MASS);
//	public static final Unit<Mass> ATOMIC_MASS_UNIT = addUnit(
//			new AlternateUnit<Mass>(Units.UNIFIED_ATOMIC_MASS,
//					Units.UNIFIED_ATOMIC_MASS.getSymbol()), Mass.class);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Energy> ELECTRON_VOLT = addUnit(SI.ELECTRON_VOLT);
	
	
	// ///////////////////////////////
	// NATURAL UNITS: ISO80000 4.3 §29 //
	// ///////////////////////////////
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Speed> C = addUnit(Units.METRES_PER_SECOND
			.multiply(299792458));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Action> PLANCK = addUnit(SI.JOULE_SECOND
			.multiply(6.6260755E-24)); //FIXME get rid of JXQ import (where from??) */
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<?> BOLTZMAN = addUnit(JOULE.divide(KELVIN)
			.multiply(1.380658E-23));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<ElectricPermittivity> PERMITTIVITY_OF_VACUUM = addUnit(SI.FARADS_PER_METRE
			.multiply(8.854187817E-12));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<MagneticPermeability> PERMEABILITY_OF_VACUUM = addUnit(
			new ProductUnit<MagneticPermeability>(SI.NEWTONS_PER_SQUARE_AMPERE
					.multiply(PI.multiply(4).divide(1E7))),
			MagneticPermeability.class);
//	public static final Unit<MagneticPermeability> PERMEABILITY_OF_VACUUM = addUnit(
//			new ProductUnit<MagneticPermeability>(Units.NEWTONS_PER_SQUARE_AMPERE
//					.multiply(PI).multiply(4).divide(1E7)),
//			MagneticPermeability.class);
		
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	
	public static final Unit<ElectricCharge> ELEMENTARY_CHARGE = addUnit(Units.COULOMB
			.transform(((AbstractUnit<Energy>)SI.ELECTRON_VOLT).getSystemConverter()));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Mass> ELECTRON_MASS = addUnit(GRAM
			.multiply(9.1093897E-28));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Mass> PROTON_MASS = addUnit(GRAM
			.multiply(1.6726231E-24));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<?> NEWTON_CONSTANT_OF_GRAVITY = addUnit(METRE
			.pow(3).multiply(Units.KILOGRAM.pow(-1)).multiply(SECOND.pow(-2))
			.multiply(6.67259E-11));
	/** As per <a href="https://en.wikipedia.org/wiki/ISO_80000-3">ISO80000-3</a> standard. */
	public static final Unit<Acceleration> ACCELLERATION_OF_FREEFALL = addUnit(Units.METRES_PER_SQUARE_SECOND
			.multiply(9.80665));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Pressure> ATMOSPHERE = addUnit(Units.PASCAL
			.multiply(101325));
	// //////////
	// Length //
	// //////////
	
	/**
	 * A unit of length equal to the distance that light travels in one year
	 * through a vacuum (standard name <code>ly</code>).
	 */
	public static final Unit<Length> LIGHT_YEAR = addUnit(new ProductUnit<Length>(
			C.multiply(YEAR_JULIAN)));
	/**
	 * A unit of length equal to the distance that light travels in one year
	 * through a vacuum (standard name <code>ly</code>).
	 */
	//static final Unit<Length> LIGHT_YEAR = addUnit(METRE
		//	.multiply(9.460528405e15));

	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Force> GRAM_FORCE = addUnit(new ProductUnit<Force>(
			GRAM.multiply(ACCELLERATION_OF_FREEFALL)));
	// POUND_FORCE contains a forward reference to avoirdupois pound weight, so
	// it has been moved after section §36 below
	
	// ///////////////////////////
	// CGS UNITS: ISO80000 4.3 §30 //
	// ///////////////////////////
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<WaveNumber> KAYSER = addUnit(SI.RECIPROCAL_METRE
			.divide(100)); // get rid of JXQ import (where from??) */
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Acceleration> GAL = addUnit(new ProductUnit<Acceleration>(
			CENTI(METRE).divide(SECOND.pow(2))));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Force> DYNE = addUnit(new ProductUnit<Force>(
			Units.GRAM.multiply(CENTI(Units.METRE).divide(Units.SECOND
					.pow(2)))));
//	public static final Unit<Force> DYNE = addUnit(new ProductUnit<Force>(
//			Units.GRAM.multiply(new ProductUnit(CENTI(Units.METRE)).divide(Units.SECOND
//					.pow(2)))));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Energy> ERG = addUnit(new ProductUnit<Energy>(
			DYNE.multiply(CENTI(Units.METRE))));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<DynamicViscosity> POISE = addUnit(new ProductUnit<DynamicViscosity>(
			DYNE.multiply(SECOND).divide(CENTI(Units.METRE).pow(2))));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<ElectricCurrent> BIOT = addUnit(AMPERE
			.multiply(10));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<KinematicViscosity> STOKES = addUnit(new ProductUnit<KinematicViscosity>(
			CENTI(Units.METRE).pow(2).divide(Units.SECOND)));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<MagneticFlux> MAXWELL = addUnit(Units.WEBER
			.divide(1E8));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<MagneticFluxDensity> GAUSS = addUnit(Units.TESLA
			.divide(1E4));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<MagneticFieldStrength> OERSTED = addUnit(new ProductUnit<MagneticFieldStrength>(
			SI.AMPERES_PER_METRE.multiply(250).divide(PI)));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<MagnetomotiveForce> GILBERT = addUnit(new ProductUnit<MagnetomotiveForce>(
			OERSTED.multiply(CENTI(Units.METRE))));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Luminance> STILB = addUnit(new ProductUnit<Luminance>(
			CANDELA.divide(CENTI(METRE).pow(2))));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Luminance> LAMBERT = addUnit(new ProductUnit<Luminance>(
			STILB.divide(PI)));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Illuminance> PHOT = addUnit(LUX.divide(1E4));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Radioactivity> CURIE = addUnit(Units.BECQUEREL
			.multiply(3.7E10));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<IonizingRadiation> ROENTGEN = addUnit(SI.COULOMBS_PER_KILOGRAM
			.multiply(2.58E-4)); // add later when JMQ issue fixed */
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<RadiationDoseAbsorbed> RAD = addUnit(new ProductUnit<RadiationDoseAbsorbed>(
			ERG.divide(Units.GRAM.multiply(100))));
//	public static final Unit<RadiationDoseAbsorbed> RAD = addUnit(new ProductUnit<RadiationDoseAbsorbed>(
//			ERG.divide(Units.GRAM).multiply(100)));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<RadiationDoseEffective> REM = addUnit(new ProductUnit<RadiationDoseEffective>(
			ERG.divide(Units.GRAM.multiply(100))));
//	public static final Unit<RadiationDoseEffective> REM = addUnit(new AlternateUnit<RadiationDoseEffective>(
//			RAD, RAD.getSymbol())); // TODO are symbols for RAD and REM same?
	// ///////////////////////////////////////////////
	// INTERNATIONAL CUSTOMARY UNITS: ISO80000 4.4 §31 //
	// ///////////////////////////////////////////////
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	static final Unit<Length> INCH_INTERNATIONAL = addUnit(CENTI(METRE)
			.multiply(254).divide(100));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	static final Unit<Length> FOOT_INTERNATIONAL = addUnit(INCH_INTERNATIONAL
			.multiply(12));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Length> NAUTICAL_MILE_INTERNATIONAL = addUnit(METRE
			.multiply(1852));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Speed> KNOT = addUnit(new ProductUnit<Speed>(
			NAUTICAL_MILE_INTERNATIONAL.divide(HOUR)));

	// //////////////////////////////////////////
	// AVOIRDUPOIS WIEGHT UNITS: ISO80000 4.4 §36 //
	// //////////////////////////////////////////
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	private static final Unit<Mass> GRAIN = addUnit(MILLI(GRAM)
			.multiply(6479891).divide(100000));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	static final Unit<Mass> POUND = addUnit(GRAIN.multiply(7000));

	// CONTINUED FROM SECTION §29
	// contains a forward reference to POUND, so we had to move it here, below
	// section §36
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
//	public static final Unit<Force> POUND_FORCE = addUnit(new ProductUnit<Force>(
//			POUND.multiply(ACCELLERATION_OF_FREEFALL)));
	private static final Unit<Force> POUND_FORCE = addUnit(POUND.multiply(ACCELLERATION_OF_FREEFALL).asType(Force.class));
	
	/**
	 * A unit of length equal to <code>0.3048 m</code> (standard name
	 * <code>ft</code>).
	 */
	static final Unit<Length> FOOT = addUnit(METRE.multiply(3048)
			.divide(10000));
	
	/**
	 * A unit of length equal to <code>0.0254 m</code> (standard name
	 * <code>in</code>).
	 */
	static final Unit<Length> INCH = addUnit(FOOT.divide(12));
	
	/**
	 * A unit of length equal to <code>1E-10 m</code> (standard name
	 * <code>\u00C5ngstr\u00F6m</code>).
	 */
	static final Unit<Length> ANGSTROM = addUnit(METRE
			.divide(10000000000L));

	/**
	 * A unit of length equal to the average distance from the center of the
	 * Earth to the center of the Sun (standard name <code>ua</code>).
	 */
	public static final Unit<Length> ASTRONOMICAL_UNIT = addUnit(METRE
			.multiply(149597870691.0));

	/**
	 * A unit of length equal to the distance at which a star would appear to
	 * shift its position by one arcsecond over the course the time (about 3
	 * months) in which the Earth moves a distance of {@link #ASTRONOMICAL_UNIT}
	 * in the direction perpendicular to the direction to the star (standard
	 * name <code>pc</code>).
	 */
	public static final Unit<Length> PARSEC = addUnit(METRE
			.multiply(30856770e9));

	/**
	 * A unit of length equal to <code>1/72 {@link #INCH}</code> (standard name
	 * <code>pixel</code>). It is the American point rounded to an even 1/72
	 * inch.
	 * 
	 * @see #POINT
	 * @deprecated Does not seem to be in ISO80k
	 */
	public static final Unit<Length> PIXEL = addUnit(INCH.divide(72));

	/**
	 * Equivalent {@link #PIXEL}
	 * @deprecated Does not seem to be in ISO80k
	 */
	public static final Unit<Length> COMPUTER_POINT = PIXEL;

	
	// ///////////////////////////////////////////
	// TYPESETTER'S LENGTH UNITS: ISO80000 4.4 §39 //
	// ///////////////////////////////////////////
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Length> LINE = addUnit(INCH_INTERNATIONAL
			.divide(12));
	/**
	 * A unit of length equal to <code>0.013837 {@link #INCH}</code> exactly
	 * (standard name <code>pt</code>).
	 * 
	 * @see #PIXEL
	 */
	public static final Unit<Length> POINT = addUnit(LINE.divide(6));
	//static final Unit<Length> POINT = addUnit(INCH.multiply(13837)
		//	.divide(1000000));

	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Length> PICA = addUnit(POINT.multiply(12));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Length> POINT_PRINTER = addUnit(INCH_INTERNATIONAL
			.multiply(13837).divide(1000000));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Length> PICA_PRINTER = addUnit(POINT_PRINTER
			.multiply(12));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Length> PIED = addUnit(CENTI(METRE).multiply(3248)
			.divide(100));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Length> POUCE = addUnit(PIED.divide(12));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Length> LINGE = addUnit(POUCE.divide(12));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Length> DIDOT = addUnit(LINGE.divide(6));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Length> CICERO = addUnit(DIDOT.multiply(12));
	// ////////////////////////////////////
	// OTHER LEGACY UNITS: ISO80000 4.5 §40 //
	// ////////////////////////////////////
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Temperature> FAHRENHEIT = addUnit(KELVIN
			.multiply(5).divide(9).shift(459.67));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Energy> CALORIE_AT_15C = addUnit(JOULE.multiply(
			41858).divide(10000));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Energy> CALORIE_AT_20C = addUnit(JOULE.multiply(
			41819).divide(10000));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Energy> CALORIE_MEAN = addUnit(JOULE.multiply(
			419002).divide(100000));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Energy> CALORIE_INTERNATIONAL_TABLE = addUnit(JOULE
			.multiply(41868).divide(10000));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Energy> CALORIE_THERMOCHEMICAL = addUnit(JOULE
			.multiply(4184).divide(1000));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Energy> CALORIE = addUnit(CALORIE_THERMOCHEMICAL);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Energy> CALORIE_FOOD = addUnit(KILO(CALORIE_THERMOCHEMICAL));
	
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Energy> BTU_AT_39F = addUnit(KILO(JOULE).multiply(
			105967).divide(100000));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Energy> BTU_AT_59F = addUnit(KILO(JOULE).multiply(
			105480).divide(100000));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Energy> BTU_AT_60F = addUnit(KILO(JOULE).multiply(
			105468).divide(100000));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Energy> BTU_MEAN = addUnit(KILO(JOULE).multiply(
			105587).divide(100000));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Energy> BTU_INTERNATIONAL_TABLE = addUnit(KILO(
			JOULE).multiply(105505585262L).divide(100000000000L));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Energy> BTU_THERMOCHEMICAL = addUnit(KILO(JOULE)
			.multiply(105735).divide(100000));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Energy> BTU = addUnit(BTU_THERMOCHEMICAL);
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Power> HORSEPOWER = addUnit(new ProductUnit<Power>(
			FOOT_INTERNATIONAL.multiply(POUND_FORCE).divide(SECOND)));
	// ///////////////////////////////////////////////////////
	// SECTIONS §41-§43 skipped; implement later if needed //
	// ///////////////////////////////////////////////////////
	// /////////////////////////////////////
	// MISCELLANEOUS UNITS: ISO80000 4.5 §44 //
	// /////////////////////////////////////
	/** temporary helper for MHO */
	private static final Unit<? extends Quantity<?>> TMP_MHO = SIEMENS
			.alternate("mho");

	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Volume> STERE = addUnit(new ProductUnit<Volume>(
			METRE.pow(3)));

	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Area> BARN = addUnit(new ProductUnit<Area>(FEMTO(
			METRE).pow(2)).multiply(100));
//	public static final Unit<Area> BARN = addUnit(new ProductUnit<Area>(FEMTO(
//			METER).pow(2).multiply(100)));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Pressure> ATMOSPHERE_TECHNICAL = addUnit(new ProductUnit<Pressure>(
			KILO(GRAM_FORCE).divide(CENTI(METRE).pow(2))));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<ElectricConductance> MHO = addUnit(new AlternateUnit<ElectricConductance>(
			TMP_MHO, TMP_MHO.getSymbol()));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Pressure> POUND_PER_SQUARE_INCH = addUnit(new ProductUnit<Pressure>(
			POUND_FORCE.divide(INCH_INTERNATIONAL.pow(2))));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Angle> CIRCLE = addUnit(new ProductUnit<Angle>(PI
			.multiply(RADIAN.multiply(2))));
//	public static final Unit<Angle> CIRCLE = addUnit(new ProductUnit<Angle>(PI
//			.multiply(RADIAN).multiply(2)));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<SolidAngle> SPHERE = addUnit(new ProductUnit<SolidAngle>(
			PI.multiply(STERADIAN.multiply(4))));
//	public static final Unit<SolidAngle> SPHERE = addUnit(new ProductUnit<SolidAngle>(
//			PI.multiply(STERADIAN).multiply(4)));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Mass> CARAT_METRIC = addUnit(GRAM.divide(5));
	/** As per <a href="http//www.iso.org/">ISO80000</a> standard. */
	public static final Unit<Dimensionless> CARAT_GOLD = addUnit(ONE
			.divide(24));
	
	// /////////////////////////////////////////////
	// IEC 80000-13 Information                   //
	// https://en.wikipedia.org/wiki/IEC_80000-13 //
	// /////////////////////////////////////////////
	/**
     * The unit for binary information (standard name <code>bit</code>).
     */
    public static final Unit<Information> BIT
            = addUnit(new AlternateUnit<Information>(ONE, "bit"), Information.class);
    
	/**
	 * A unit of data amount equal to <code>8 {@link SI#BIT}</code> (BinarY
	 * TErm, standard name <code>byte</code>).
	 */
	public static final Unit<Information> BYTE = addUnit(BIT.multiply(8));

	  /**
     * The ISO unit for binary information rate (standard name <code>bit/s</code>).
     */
    public static final ProductUnit<InformationRate> BITS_PER_SECOND
            = addUnit(new ProductUnit<InformationRate>(BIT.divide(SECOND)), InformationRate.class);
	
	/**
	 * Equivalent {@link #BYTE}
	 */
	static final Unit<Information> OCTET = BYTE;
	
	// TODO add more units in https://en.wikipedia.org/wiki/IEC_80000-13

	// /////////////////////
	// MISSING FROM ISO80000 //
	// /////////////////////

	/**
	 * To be added to the <a href="http//www.iso.org/">ISO80000</a>
	 * standard.
	 */
	public static final Unit<Frequency> FRAMES_PER_SECOND = addUnit(
			ONE.divide(SECOND)).asType(Frequency.class);

	
	//public static final Unit<Dimensionless> CURVATURE = addUnit(ONE.divide(METER).asType(Dimensionless.class));
	// TODO or shall we actually have a Quantity Curvature in systems-quantity?
	
	// /////////////////////////////////////////////////////////////////////////////
	// IEC 80000-3 Logarithmic quantities and units                               //
	// https://en.wikipedia.org/wiki/ISO_80000-3#Logarithmic_quantities_and_units //
	// /////////////////////////////////////////////////////////////////////////////
	
	@SuppressWarnings("unchecked")
	public static final Unit<Level<Power>> NEPER = addUnit(ONE.asType(Level.class));
	
	/**
	 * A logarithmic unit used to describe a power {@link Level} ratio (standard name
	 * <code>dB</code>).
	 */
	public static final Unit<Level<Power>> DECIBEL = addUnit(NEPER
			.transform(new LogConverter(10).inverse().concatenate(
					new RationalConverter(1d, 10d))));
	
	// ///////////////////
	// Collection View //
	// ///////////////////

	@Override
	public String getName() {
		return "ISO80000";
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
	
	// //////////////////////////////////////////////////////////////////////////
	// Label adjustments for ISO80000 system
	static {
		SimpleUnitFormat.getInstance().label(ATOMIC_MASS_UNIT, "AMU");
		SimpleUnitFormat.getInstance().label(BYTE, "B");
		SimpleUnitFormat.getInstance().label(CARAT_GOLD, "kt");
		SimpleUnitFormat.getInstance().label(CARAT_METRIC, "ct");
		SimpleUnitFormat.getInstance().label(DECIBEL, "dB");
		SimpleUnitFormat.getInstance().label(POUND, "lb");
		SimpleUnitFormat.getInstance().label(NEPER, "Np");
		SimpleUnitFormat.getInstance().label(BAR, "b");
	}
}
