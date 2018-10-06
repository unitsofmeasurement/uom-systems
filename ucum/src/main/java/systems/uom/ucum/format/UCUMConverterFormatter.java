/*
 * Units of Measurement Systems
 * Copyright (c) 2005-2018 Jean-Marie Dautelle, Werner Keil and others.
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
 * 3. Neither the name of JSR-363, Units of Measurement nor the names of their contributors may be used to
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

import java.math.BigInteger;

import javax.measure.UnitConverter;
import javax.measure.Prefix;

import tech.units.indriya.AbstractConverter;
import tech.units.indriya.format.SymbolMap;
import tech.units.indriya.function.MultiplyConverter;
import tech.units.indriya.function.RationalConverter;

/**
 * @author keilw
 */
class UCUMConverterFormatter {
    /**
     * Formats the given converter to the given StringBuilder. This is similar to what <type>ConverterFormatter</type> does, but there's no need to
     * worry about operator precedence here, since UCUM only supports multiplication, division, and exponentiation and expressions are always
     * evaluated left- to-right.
     * 
     * @param converter
     *            the converter to be formatted
     * @param continued
     *            <code>true</code> if the converter expression should begin with an operator, otherwise <code>false</code>. This will always be true
     *            unless the unit being modified is equal to AbstractUnit.ONE.
     * @param buffer
     *            the <code>StringBuffer</code> to append to. Contains the already-formatted unit being modified by the given converter.
     */
    static void formatConverter(UnitConverter converter, boolean continued, StringBuilder buffer, final SymbolMap symbolMap) {
        boolean unitIsExpression = ((buffer.indexOf(".") >= 0) || (buffer.indexOf("/") >= 0));
        Prefix prefix = symbolMap.getPrefix(converter);
        if ((prefix != null) && (!unitIsExpression)) {
            buffer.insert(0, symbolMap.getSymbol(prefix));
        } else if (converter == AbstractConverter.IDENTITY) {
            // do nothing
        } else if (converter instanceof MultiplyConverter) {
            if (unitIsExpression) {
                buffer.insert(0, '(');
                buffer.append(')');
            }
            MultiplyConverter multiplyConverter = (MultiplyConverter) converter;
            double factor = multiplyConverter.getFactor();
            long lFactor = (long) factor;
            if ((lFactor < Long.MIN_VALUE) || (lFactor > Long.MAX_VALUE)) { // (lFactor != factor) ||
                throw new IllegalArgumentException("Only integer factors are supported in UCUM");
            }
            if (continued) {
                buffer.append('.');
            }
            buffer.append(lFactor);
        } else if (converter instanceof RationalConverter) {
            if (unitIsExpression) {
                buffer.insert(0, '(');
                buffer.append(')');
            }
            RationalConverter rationalConverter = (RationalConverter) converter;
            if (!rationalConverter.getDividend().equals(BigInteger.ONE)) {
                if (continued) {
                    buffer.append('.');
                }
                buffer.append(rationalConverter.getDividend());
            }
            if (!rationalConverter.getDivisor().equals(BigInteger.ONE)) {
                buffer.append('/');
                buffer.append(rationalConverter.getDivisor());
            }
        } else { // All other converter type (e.g. exponential) we use the
            // string representation.
            buffer.insert(0, converter.toString() + "(");
            buffer.append(")");
        }
    }
}
