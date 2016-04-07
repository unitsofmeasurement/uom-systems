/**
 *  Unit-API - Units of Measurement API for Java
 *  Copyright (c) 2005-2016, Jean-Marie Dautelle, Werner Keil, V2COM.
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
 * 3. Neither the name of JSR-363, Unit-API nor the names of its contributors may be used to endorse or promote products
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
package systems.uom.common;

import static org.junit.Assert.assertEquals;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import si.uom.SI;
import si.uom.impl.quantity.AccelerationAmount;
import si.uom.impl.quantity.ForceAmount;
import si.uom.impl.quantity.MassAmount;

/**
 *
 * @author Chris Senior
 * @author Werner Keil
 *
 */
public class NewtonsSecondLawTest {
    static final Logger logger = Logger.getLogger(NewtonsSecondLawTest.class
	    .getName());

    // @Test
    // public void testCalculateForce() {
    // MassAmount m = new MassAmount(1000, SI.KILOGRAM);
    // AccelerationAmount a = new AccelerationAmount(2.5,
    // SI.METRES_PER_SQUARE_SECOND);
    // ForceAmount force = NewtonsSecondLaw.calculateForce(m, a);
    // System.out.println("ForceAmountL= " + force.longValue(SI.NEWTON));
    // assertEquals(2500, force.doubleValue(SI.NEWTON), 0.0001);
    // }

    // TODO change System.out.println to OutputHelper.println

    @Test
    public void testCalculateForce() {
	logger.log(Level.INFO, "Calculate Force");
	MassAmount m = new MassAmount(1000, SI.GRAM);
	AccelerationAmount a = new AccelerationAmount(2.5,
		SI.METRES_PER_SQUARE_SECOND);
	ForceAmount force = NewtonsSecondLaw.calculateForce(m, a);
	logger.log(Level.INFO, "ForceAmount = " + force.doubleValue(SI.NEWTON));
	assertEquals(2500000, force.doubleValue(SI.NEWTON), 0.0001);
    }

    @Test
    public void testCalculateForceKg() {
	MassAmount m = new MassAmount(1, SI.KILOGRAM);
	AccelerationAmount a = new AccelerationAmount(2.5,
		SI.METRES_PER_SQUARE_SECOND);
	ForceAmount force = NewtonsSecondLaw.calculateForce(m, a);
	// println("ForceAmount = " + force.doubleValue(SI.NEWTON));
	assertEquals(2.5, force.doubleValue(SI.NEWTON), 0.0001);
    }

    /*
     * @Test public void testWithOddUnits() { println("Test with Odd Units");
     * final MassAmount m = new MassAmount(100, US.POUND);
     * 
     * @SuppressWarnings("unchecked") // we know this creates an acceleration!
     * Unit<Acceleration> inch_per_square_second =
     * (Unit<Acceleration>)US.INCH.divide(SI.SECOND).divide(SI.SECOND);
     * println(inch_per_square_second); AccelerationAmount a = new
     * AccelerationAmount(100, inch_per_square_second); ForceAmount force =
     * NewtonsSecondLaw.calculateForce(m, a); println(force);
     * assertEquals(867961.6621451874, force.doubleValue(SI.NEWTON),
     * 0.0000000001); // Pound-force (http://en.wikipedia.org/wiki/Pound-force)
     * is a unit for Force in English engineering units and British
     * gravitational units Unit<Force> poundForce =
     * SI.NEWTON.multiply(4.448222); assertEquals(3860886.16071079,
     * force.doubleValue(poundForce), 0.0000000001); println(poundForce); }
     */
}
