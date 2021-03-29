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
package systems.uom.unicode;

import static org.junit.jupiter.api.Assertions.*;
import static systems.uom.unicode.CLDR.*;

import javax.measure.Unit;
import javax.measure.quantity.Volume;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import systems.uom.quantity.Consumption;

/**
 * Test unit conversions.
 */
@Disabled("currently fails: https://github.com/unitsofmeasurement/uom-systems/issues/177")
public class FuelConsumptionTest {

//    @Test
//    public void testLiterPer100KilometersToMilesPerGallon() {
//        // Just comment that the only value that seems to be working fine is 1 liter per 100 km.
//        final double literPer100Kilometers = 10;
//        final double milesPerGallonActual = LITER_PER_100KILOMETERS.getConverterTo(MILE_PER_GALLON).convert(literPer100Kilometers);
//        final double milesPerGallonExpected = (100 * 3.785411784) / (1.609344 * literPer100Kilometers);
//        assertEquals(milesPerGallonExpected, milesPerGallonActual, 0.001);
//    }
    
    @Test
    public void testLiterPerilometerToMilesPerGallon() {
        // Just comment that the only value that seems to be working fine is 1 liter per 100 km.
        final double literPer100Kilometers = 10;
        final double milesPerGallonActual = LITER_PER_KILOMETER.getConverterTo(MILE_PER_GALLON).convert(literPer100Kilometers);
        final double milesPerGallonExpected = (100 * 3.785411784) / (1.609344 * literPer100Kilometers);
        assertEquals(milesPerGallonExpected, milesPerGallonActual, 0.001);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testLiterPer100KilometersToMilesPerGallonImperial() {
        final double literPer100Kilometers = 10;
        final Unit<Consumption<Volume>> MILE_PER_GALLON_IMPERIAL = CLDR.MILE.divide(CLDR.GALLON_IMPERIAL).asType(Consumption.class);
        final double milesPerGallonImperialActual = CLDR.LITER_PER_100KILOMETERS.getConverterTo(MILE_PER_GALLON_IMPERIAL).convert(literPer100Kilometers);
        final double milesPerGallonImperialExpected = 282.480936332 / literPer100Kilometers;
        assertEquals(milesPerGallonImperialExpected, milesPerGallonImperialActual, 0.001);
    }
}

