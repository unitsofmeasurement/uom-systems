/**
 * Unit-API - Units of Measurement API for Java
 * Copyright (c) 2014 Jean-Marie Dautelle, Werner Keil, V2COM
 * All rights reserved.
 *
 * See LICENSE.txt for details.
 */
package systems.uom.test.unit;

import javax.measure.Unit;
import systems.uom.quantity.InformationRate;
import javax.measure.test.TestUnit;

/**
 * @author Werner Keil
 * @version 1.1
 */
public class BitRateUnit extends TestUnit<InformationRate> {

    public static final BitRateUnit bps = new BitRateUnit("bps", 1.0); // reference Unit
    public static final BitRateUnit REF_UNIT = bps; // reference Unit
    public static final BitRateUnit kb = new BitRateUnit("kb", 1.0e3);

    public BitRateUnit(String name2, double convF) {
    	super(name2);
        multFactor = convF;
    }

    @Override
    public Unit<InformationRate> getSystemUnit() {
        return REF_UNIT;
    }
}
