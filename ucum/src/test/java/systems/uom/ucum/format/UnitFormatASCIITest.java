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
package systems.uom.ucum.format;

import static systems.uom.ucum.format.StringUtils.isPureAscii;
import static org.junit.jupiter.api.Assertions.*;
import static systems.uom.ucum.format.UCUMFormat.Variant.*;
import javax.measure.*;
import javax.measure.format.*;

import org.junit.jupiter.api.Test;

import systems.uom.ucum.UCUM;
import systems.uom.ucum.format.UCUMFormat;

/**
 * @author <a href="mailto:werner@uom.systems">Werner Keil</a>
 *
 */
public class UnitFormatASCIITest extends UCUMFormatTestBase {

    @Test
    public void testUnitsUCUM() {
	final UnitFormat cs = UCUMFormat.getInstance(CASE_SENSITIVE);
	final UnitFormat ci = UCUMFormat.getInstance(CASE_INSENSITIVE);
	final UnitFormat pr = UCUMFormat.getInstance(PRINT);

	for (Unit<?> u : UCUM.getInstance().getUnits()) {
	    // try {
	    // Unit<?> v = format.parse("1/" + u.toString());
	    //LOGGER.log(LOG_LEVEL, String.format("%s @ %s @ %s @ %s", 
		    assertTrue(isPureAscii(cs.format(u)), String.format("CS format %s contains non-ASCII characters", cs.format(u)));
		    assertTrue(isPureAscii(ci.format(u)), String.format("CI format %s contains non-ASCII characters", ci.format(u)));
//		    assertTrue(String.format("Print format %s contains non-ASCII characters", pr.format(u)), isPureAscii(pr.format(u)));
	    // } catch (ParserException pex) {
	    // logger.log(Level.WARNING, String.format(" %s parsing %s", pex,"
	    // u));
	    // }
	}
    }
}
