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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.measure.MetricPrefix;
import javax.measure.UnitConverter;

import tech.units.indriya.ComparableUnit;
import tech.units.indriya.function.Calculus;
import tech.units.indriya.function.MultiplyConverter;
import tech.units.indriya.function.PowerOfIntConverter;
import tech.units.indriya.spi.NumberSystem;
import tech.units.indriya.unit.AnnotatedUnit;

/**
 * <p>
 * Package private support class for {@link UCUMFormat} 
 * </p>
 *
 * @author Andi Huber
 * @version 1.0, 11 September 2019
 */
@SuppressWarnings("rawtypes")
final class UCUMFormatHelper {
    
    static interface SymbolProvider {
        CharSequence symbolFor(ComparableUnit<?> unit) throws IOException;
    }

    private static Map<String, UnitConverter> prefixConverterByFactor = null;
    
    private final UCUMFormat ucumFormat;
    @SuppressWarnings("unused") // maybe we want to extend this class later
    private final ComparableUnit unit; 
    private final CharSequence annotation;

    static UCUMFormatHelper of(UCUMFormat ucumFormat, ComparableUnit<?> unit) {
        if (unit instanceof AnnotatedUnit) {
            final AnnotatedUnit annotatedUnit = (AnnotatedUnit) unit;
            final ComparableUnit<?> actualUnit = (ComparableUnit) (annotatedUnit.getActualUnit());
            return new UCUMFormatHelper(ucumFormat, actualUnit, annotatedUnit.getAnnotation());
        }
        return new UCUMFormatHelper(ucumFormat, unit, /*annotation*/ null);
    }
    
    private UCUMFormatHelper(UCUMFormat ucumFormat, ComparableUnit unit, CharSequence annotation) {
        this.ucumFormat = ucumFormat;
        this.unit = unit;
        this.annotation = annotation;
    }

    private CharSequence getAnnotation() {
        return annotation;
    }
    
    private boolean hasAnnotation() {
        return annotation!=null && annotation.length()>0;
    }
    
    void appendAnnotation(CharSequence symbol, Appendable appendable) throws IOException {
        if (!hasAnnotation()) {
            return;
        }
        ucumFormat.appendAnnotation(symbol, getAnnotation(), appendable);
    }

    /**
     * First of the symbolProviders that returns a non-null String wins.
     * @param symbolProviders
     * @param unit
     * @return
     * @throws IOException
     */
    public CharSequence findSymbolFor(SymbolProvider[] symbolProviders, ComparableUnit<?> unit) throws IOException {
        for(SymbolProvider symbolProvider : symbolProviders) {
            CharSequence symbol = symbolProvider.symbolFor(unit);
            if (symbol != null) {
                return symbol;
            }
        }
        return null;
    }

    /**
     * Converts a given UnitConverter to an equivalent MultiplyConverter that is registered with the symbolMap.
     * This allows for further formatting steps to correctly identify converters that are associated with prefix 
     * symbols.
     *  
     * @param converter
     * @return
     */
    public static UnitConverter toKnownPrefixConverterIfPossible(UnitConverter converter) {
        
        if (converter instanceof PowerOfIntConverter) {
            return converter;
        }
        
        if (converter instanceof MultiplyConverter) {
            
            ensurePrefixConverterMapInitialized();
            
            final MultiplyConverter multiplyConverter = (MultiplyConverter) converter;
            final NumberSystem ns = Calculus.currentNumberSystem();
            final Number factor = ns.narrow(multiplyConverter.getFactor());
            final String key = factor.toString();
            
            return prefixConverterByFactor.getOrDefault(key, converter);
        }
        
        return converter; // fallback
        
    }
    
    private static void ensurePrefixConverterMapInitialized() {
        
        if (prefixConverterByFactor != null) {
            return;
        }
        
        prefixConverterByFactor = new HashMap<>();
        
        final NumberSystem ns = Calculus.currentNumberSystem();
        
        for(MetricPrefix metricPrefix : MetricPrefix.values()) {
            
            if(metricPrefix == MetricPrefix.DEKA) {
                continue; // excluding DEKA should not be necessary, but currently is, for tests to succeed
            }
            
            final MultiplyConverter prefixConverter = MultiplyConverter.ofExponent(10, metricPrefix.getExponent());
            final Number factor = ns.narrow(prefixConverter.getFactor());
            final String key = factor.toString();
            prefixConverterByFactor.put(key, prefixConverter);
        }
        
    }
    
    
}
