/**
 * Unit-API - Units of Measurement API for Java
 * Copyright (c) 2014 Jean-Marie Dautelle, Werner Keil, V2COM
 * All rights reserved.
 *
 * See LICENSE.txt for details.
 */
package systems.uom.test.quantity;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import systems.uom.test.unit.BitUnit;

public class BitQuantityTest {
    BitQuantity bq;
    BitUnit bu;

    @Before
    public void setUp() {
        bu = BitUnit.bit;
        bq = new BitQuantity(100, bu);
    }

    @Test
    public void testAddBitQuantity() {
        BitQuantity bq1 = bq.add(new BitQuantity(100, bu));
        assertEquals(Double.valueOf(200), Double.valueOf(bq1.scalar));
    }

//    @Test
//    public void testDivideTimeQuantity() {
//        BitRateQuantity brq = bq.divide(new TimeQuantity(20d, TimeUnit.s));
//        assertEquals(Double.valueOf(5), Double.valueOf(brq.scalar));
//        assertEquals("5.0 bps", brq.toString());
//    }
}
