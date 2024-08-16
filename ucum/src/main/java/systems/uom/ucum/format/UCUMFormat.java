/*
 * Units of Measurement Systems
 * Copyright (c) 2005-2024, Jean-Marie Dautelle, Werner Keil and others.
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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javax.measure.MetricPrefix;
import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.UnitConverter;
import javax.measure.format.MeasurementParseException;

import static systems.uom.ucum.format.UCUMConverterFormatter.formatConverter;
import static tech.units.indriya.AbstractUnit.ONE;

import si.uom.SI;
import systems.uom.ucum.format.UCUMFormatHelper.SymbolProvider;
import systems.uom.ucum.internal.format.UCUMFormatParser;
import tech.units.indriya.AbstractUnit;
import tech.units.indriya.format.AbstractUnitFormat;
import tech.units.indriya.format.SymbolMap;
import tech.units.indriya.format.TokenException;
import tech.units.indriya.format.TokenMgrError;
import tech.units.indriya.function.MultiplyConverter;
import tech.units.indriya.unit.TransformedUnit;

/**
 * <p>
 * This class provides the interface for formatting and parsing {@link Unit units} according to the
 * <a href="http://unitsofmeasure.org/">Uniform Code for CommonUnits of Measure</a> (UCUM).
 * </p>
 *
 * <p>
 * For a technical/historical overview of this format please read <a href="http://www.pubmedcentral.nih.gov/articlerender.fcgi?artid=61354">
 * CommonUnits of Measure in Clinical Information Systems</a>.
 * </p>
 *
 * <p>
 * As of revision 1.16, the BNF in the UCUM standard contains an <a href="http://unitsofmeasure.org/ticket/4">error</a>. I've attempted to work around
 * the problem by modifying the BNF productions for &lt;Term&gt;. Once the error in the standard is corrected, it may be necessary to modify the
 * productions in the UCUMFormatParser.jj file to conform to the standard.
 * </p>
 *
 * @author <a href="mailto:eric-r@northwestern.edu">Eric Russell</a>
 * @author <a href="mailto:werner@uom.systems">Werner Keil</a>
 * @author Andi Huber
 * @version 2.3, 7 December 2020
 */
public abstract class UCUMFormat extends AbstractUnitFormat {
    /**
     * 
     */
    // private static final long serialVersionUID = 8586656823290135155L;

    // A helper to declare bundle names for all instances
    private static final String BUNDLE_BASE = UCUMFormat.class.getName();

    // /////////////////
    // Class methods //
    // /////////////////

    /**
     * Returns the instance for formatting/parsing using the given variant
     * 
     * @param variant
     *            the <strong>UCUM</strong> variant to use
     * @return a {@link UCUMFormat} instance
     */
    public static UCUMFormat getInstance(Variant variant) {
        switch (variant) {
            case CASE_INSENSITIVE:
                return Parsing.DEFAULT_CI;
            case CASE_SENSITIVE:
                return Parsing.DEFAULT_CS;
            case PRINT:
                return Print.DEFAULT;
            default:
                throw new IllegalArgumentException("Unknown variant: " + variant);
        }
    }

    /**
     * Returns an instance for formatting and parsing using user defined symbols
     * 
     * @param variant
     *            the <strong>UCUM</strong> variant to use
     * @param symbolMap
     *            the map of user defined symbols to use
     * @return a {@link UCUMFormat} instance
     */
    public static UCUMFormat getInstance(Variant variant, SymbolMap symbolMap) {
        switch (variant) {
            case CASE_INSENSITIVE:
                return new Parsing(symbolMap, false);
            case CASE_SENSITIVE:
                return new Parsing(symbolMap, true);
            case PRINT:
                return new Print(symbolMap);
            default:
                throw new IllegalArgumentException("Unknown variant: " + variant);
        }
    }

    /**
     * The symbol map used by this instance to map between {@link AbstractUnit Unit}s and <code>String</code>s.
     */
    final SymbolMap symbolMap;

    /**
     * Get the symbol map used by this instance to map between {@link AbstractUnit Unit}s and <code>String</code>s, etc...
     * 
     * @return SymbolMap the current symbol map
     */    
    protected SymbolMap getSymbols() {
        return symbolMap;
    }

    //////////////////
    // Constructors //
    //////////////////
    /**
     * Base constructor.
     */
    UCUMFormat(SymbolMap symbolMap) {
        this.symbolMap = symbolMap;
    }

    /////////////
    // Parsing //
    /////////////
    public abstract Unit<? extends Quantity<?>> parse(CharSequence csq, ParsePosition cursor) throws MeasurementParseException;

    protected Unit<?> parse(CharSequence csq, int index) throws MeasurementParseException {
        return parse(csq, new ParsePosition(index));
    }

    @Override
    public abstract Unit<? extends Quantity<?>> parse(CharSequence csq) throws MeasurementParseException;

    ////////////////
    // Formatting //
    ////////////////
    @SuppressWarnings({ "rawtypes" })
    public Appendable format(final Unit<?> unknownUnit, Appendable appendable) throws IOException {
        
        if (!(unknownUnit instanceof AbstractUnit)) {
            throw new UnsupportedOperationException("The UCUM format supports only known units (Comparable units)");
        }
        
        final AbstractUnit unit = (AbstractUnit) unknownUnit;
        final UCUMFormatHelper formatHelper = UCUMFormatHelper.of(this, unit);
        final CharSequence symbol = formatHelper.findSymbolFor(symbolProviders, unit);
        
        if (symbol == null) {
            throw new IllegalArgumentException("Cannot format the given Object as UCUM units (unsupported unit " + unit.getClass().getName() + "). "
                    + "Custom units types should override the toString() method as the default implementation uses the UCUM format.");
        }

        appendable.append(symbol);
        formatHelper.appendAnnotation(symbol, appendable);

        return appendable;
    }
    
    // -- SYMBOL PROVIDERS
    
    /* processed in order of declaration, the first to return a non-null string wins */
    private final SymbolProvider[] symbolProviders = {
            this::symbolFromLookupMap,
            this::symbolForTransformedUnit,
            this::symbolForKilogram,
            this::symbolForProductUnits,
            this::symbolForNonSystemUnit,
            this::symbolFromField,
            };
    
    private CharSequence symbolFromLookupMap(AbstractUnit<?> unit) throws IOException {
        return symbolMap.getSymbol(unit);
    }
    
    private CharSequence symbolFromField(AbstractUnit<?> unit) throws IOException {
        return unit.getSymbol();
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private CharSequence symbolForTransformedUnit(AbstractUnit unit) throws IOException {
        if (!(unit instanceof TransformedUnit)) {
            return null;    
        }
        final StringBuilder sb = new StringBuilder();
        final Unit<?> parentUnit = ((TransformedUnit) unit).getParentUnit();
        final UnitConverter converter = 
                UCUMFormatHelper.toKnownPrefixConverterIfPossible(unit.getConverterTo(parentUnit));
        final boolean printSeparator = !ONE.equals(parentUnit);

        if (printSeparator && converter instanceof MultiplyConverter) { // workaround for #166
        	format(parentUnit, sb);
        }
        formatConverter(converter, printSeparator, sb, symbolMap);

        return sb;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private CharSequence symbolForProductUnits(AbstractUnit unit) throws IOException {
        final Map<? extends AbstractUnit<?>, Integer> productUnits = unit.getBaseUnits();
        
        if (productUnits == null) {
            return null;
        }
        
        final StringBuilder sb = new StringBuilder();
        final Map<AbstractUnit<?>, Integer> numeratorUnits = new LinkedHashMap<>();            
        final Map<AbstractUnit<?>, Integer> denominatorUnits = new LinkedHashMap<>();

        // divide units into numerators and denominators
        for (Entry<? extends AbstractUnit<?>, Integer> u : productUnits.entrySet()) {
            if (u.getValue() > 0) {
                numeratorUnits.put(u.getKey(), u.getValue());
            }else {
                denominatorUnits.put(u.getKey(), u.getValue());
            }
        }
        
        int numeratorCount = 1;
        for (Entry<? extends AbstractUnit<?>, Integer> u : numeratorUnits.entrySet()) {
            // add multiplication separators after first unit
            if (numeratorCount > 1){
                sb.append(".");
            }
            // add individual unit string
            format(u.getKey(),sb);
            // add power number if greater than 1
            if (u.getValue() > 1){
                sb.append(u.getValue());
            }
            numeratorCount++;
        }
        // special case if there is no numerator append one for inverse
        if (numeratorCount == 1) {
            sb.append("1");
        }
        if (denominatorUnits.size() > 0){
            // append division symbol
            sb.append("/");
            int denominatorCount = 1;
            for (Entry<? extends AbstractUnit<?>, Integer> u : denominatorUnits.entrySet()) {
                // if there is more than one denominator unit and this is the first, add open parenthesis 
                if (denominatorCount == 1 && denominatorUnits.size() > 1 ) {
                    sb.append("(");
                }
                // add multiplication separators after first unit
                if (denominatorCount > 1){
                    sb.append(".");
                }
                // add individual unit string
                format(u.getKey(),sb);
                // add power number if abs greater than 1
                if (u.getValue() < -1){
                    sb.append(-u.getValue());
                }
                // if there is more than one denominator unit and this is the last, add close parenthesis
                if (denominatorCount == denominatorUnits.size() && denominatorUnits.size() > 1 ) {
                    sb.append(")");
                }
                denominatorCount++;
            }
        }            
        return sb;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private CharSequence symbolForKilogram(AbstractUnit unit) throws IOException {
        
        final Unit<?> systemUnit = unit.getSystemUnit();
        if (!systemUnit.equals(SI.KILOGRAM)) {
            return null;
        }

        final UnitConverter converter = 
                UCUMFormatHelper.toKnownPrefixConverterIfPossible(
                        unit.getConverterTo(systemUnit)
                        .concatenate(MultiplyConverter.ofPrefix(MetricPrefix.KILO)));
        
        final StringBuilder sb = new StringBuilder();
        final boolean printSeparator = true;
        
        // A special case because KILOGRAM is a BaseUnit instead of
        // a transformed unit, for compatibility with existing SI
        // unit system.
        format(SI.GRAM, sb);
        formatConverter(converter, printSeparator, sb, symbolMap);    
        
        return sb;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private CharSequence symbolForNonSystemUnit(AbstractUnit unit) throws IOException {
        
        if (unit.isSystemUnit()) {
            return null;
        }
        
        final Unit<?> parentUnit = unit.getSystemUnit();
        final UnitConverter converter = unit.getConverterTo(parentUnit);
        final StringBuilder sb = new StringBuilder();
        final boolean printSeparator = !parentUnit.equals(ONE);
        
        format(parentUnit, sb);
        formatConverter(converter, printSeparator, sb, symbolMap);
        
        return sb;
    }
    
    // ---
    
    public void label(Unit<?> unit, String label) {
        throw new UnsupportedOperationException("label() not supported by this implementation");
    }

    public boolean isLocaleSensitive() {
        return false;
    }

    void appendAnnotation(CharSequence symbol, CharSequence annotation, Appendable appendable) throws IOException {
        appendable.append('{');
        appendable.append(annotation);
        appendable.append('}');
    }

    ///////////////////
    // Inner classes //
    ///////////////////

    /**
     * Variant of unit representation in the UCUM standard
     * 
     * @see <a href= "http://unitsofmeasure.org/ucum.html#section-Character-Set-and-Lexical-Rules"> UCUM - Character Set and Lexical Rules</a>
     */
    public static enum Variant {
        CASE_SENSITIVE, CASE_INSENSITIVE, PRINT
    }

    /**
     * The Print format is used to output units according to the "print" column in the UCUM standard. Because "print" symbols in UCUM are not unique,
     * this class of UCUMFormat may not be used for parsing, only for formatting.
     */
    private static final class Print extends UCUMFormat {

        /**
         *
         */
        // private static final long serialVersionUID = 2990875526976721414L;
        private static final SymbolMap PRINT_SYMBOLS = SymbolMap.of(ResourceBundle.getBundle(BUNDLE_BASE + "_Print"));
        private static final Print DEFAULT = new Print(PRINT_SYMBOLS);

        public Print(SymbolMap symbols) {
            super(symbols);
        }

        @Override
        public Unit<? extends Quantity<?>> parse(CharSequence csq, ParsePosition pos) throws IllegalArgumentException {
            throw new UnsupportedOperationException("The print format is for pretty-printing of units only. Parsing is not supported.");
        }

        @Override
        void appendAnnotation(CharSequence symbol, CharSequence annotation, Appendable appendable) throws IOException {
            if (symbol != null && symbol.length() > 0) {
                appendable.append('(');
                appendable.append(annotation);
                appendable.append(')');
            } else {
                appendable.append(annotation);
            }
        }

        @Override
        public Unit<? extends Quantity<?>> parse(CharSequence csq) throws IllegalArgumentException {
            return parse(csq, new ParsePosition(0));

        }
        
		@Override
		public String toString() {
			return "UCUM Print";
		}
    }

    /**
     * The Parsing format outputs formats and parses units according to the "c/s" or "c/i" column in the UCUM standard, depending on which SymbolMap
     * is passed to its constructor.
     */
    private static final class Parsing extends UCUMFormat {
        // private static final long serialVersionUID = -922531801940132715L;
        private static final SymbolMap CASE_SENSITIVE_SYMBOLS = SymbolMap
                .of(ResourceBundle.getBundle(BUNDLE_BASE + "_CS", new ResourceBundle.Control() {
                    @Override
                    public List<Locale> getCandidateLocales(String baseName, Locale locale) {
                        if (baseName == null)
                            throw new NullPointerException();
                        if (locale.equals(new Locale("", "CS"))) {
                            return Arrays.asList(locale, Locale.ROOT);
                        }
                        return super.getCandidateLocales(baseName, locale);
                    }
                }));
        private static final SymbolMap CASE_INSENSITIVE_SYMBOLS = SymbolMap
                .of(ResourceBundle.getBundle(BUNDLE_BASE + "_CI", new ResourceBundle.Control() {
                    @Override
                    public List<Locale> getCandidateLocales(String baseName, Locale locale) {
                        if (baseName == null)
                            throw new NullPointerException();
                        if (locale.equals(new Locale("", "CI"))) {
                            return Arrays.asList(locale, Locale.ROOT);
                        } else if (locale.equals(Locale.GERMANY)) {
                            // TODO why GERMANY?
                            return Arrays.asList(locale,
                                    // no Locale.GERMAN here
                                    Locale.ROOT);
                        }
                        return super.getCandidateLocales(baseName, locale);
                    }
                }));
        private static final Parsing DEFAULT_CS = new Parsing(CASE_SENSITIVE_SYMBOLS, true);
        private static final Parsing DEFAULT_CI = new Parsing(CASE_INSENSITIVE_SYMBOLS, false);
        private final boolean caseSensitive;

        public Parsing(SymbolMap symbols, boolean caseSensitive) {
            super(symbols);
            this.caseSensitive = caseSensitive;
        }

        @Override
        public Unit<? extends Quantity<?>> parse(CharSequence csq, ParsePosition cursor) throws MeasurementParseException {
            // Parsing reads the whole character sequence from the parse position.
            int start = cursor.getIndex();
            int end = csq.length();
            if (end <= start) {
                return ONE;
            }
            String source = csq.subSequence(start, end).toString().trim();
            if (source.length() == 0) {
                return ONE;
            }
            if (!caseSensitive) {
                source = source.toUpperCase();
            }
            UCUMFormatParser parser = new UCUMFormatParser(symbolMap, new ByteArrayInputStream(source.getBytes()));
            try {
                Unit<?> result = parser.parseUnit();
                cursor.setIndex(end);
                return result;
            } catch (TokenException e) {
                if (e.getToken() != null) {
                    cursor.setErrorIndex(start + e.getToken().endColumn);
                } else {
                    cursor.setErrorIndex(start);
                }
                throw new MeasurementParseException(e);
            } catch (TokenMgrError e) {
                cursor.setErrorIndex(start);
                throw new IllegalArgumentException(e.getMessage());
            }
        }

        @Override
        public Unit<? extends Quantity<?>> parse(CharSequence csq) throws MeasurementParseException {
            return parse(csq, new ParsePosition(0));
        }

		@Override
		public String toString() {
			return "UCUM Parsing [" +  
					(caseSensitive ? "Case Sensitive" : "Case Insensitive") + 
					"]";
		}
    }
}
