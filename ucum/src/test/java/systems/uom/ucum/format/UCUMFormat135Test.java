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
package systems.uom.ucum.format;

import static org.junit.jupiter.api.Assertions.*;
import javax.measure.*;
import javax.measure.format.*;
import javax.measure.spi.ServiceProvider;

import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:werner@uom.systems">Werner Keil</a>
 *
 */
public class UCUMFormat135Test extends UCUMFormatTestBase {

    @Test
    public void testParseUCUMCSM3PerSecond() {
    	final UnitFormat unitFormat = ServiceProvider.current().getFormatService().getUnitFormat("CI");
    	final Unit<?> dst = unitFormat.parse("m3/s");
    	assertEquals("m3/s",FORMAT_CS.format(dst));
    }
    
    @Test
    public void testParseUCUMCSComplexDenominator() {
    	final UnitFormat unitFormat = ServiceProvider.current().getFormatService().getUnitFormat("CI");
    	final Unit<?> dst = unitFormat.parse("m/(bar.s)");
    	assertEquals("m/(bar.s)",FORMAT_CS.format(dst));
    }
}
