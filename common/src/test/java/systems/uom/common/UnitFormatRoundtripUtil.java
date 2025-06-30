/*
 * Units of Measurement Reference Implementation
 * Copyright (c) 2005-2025, Jean-Marie Dautelle, Werner Keil, Otavio Santana.
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
 * 3. Neither the name of JSR-385, Indriya nor the names of their contributors may be used to endorse or promote products
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Iterator;
import java.util.Map;

import javax.measure.IncommensurableException;
import javax.measure.MetricPrefix;
import javax.measure.Prefix;
import javax.measure.Quantity;
import javax.measure.UnconvertibleException;
import javax.measure.Unit;
import javax.measure.format.UnitFormat;

/**
 * package private utility class to consolidate format testing idioms
 *  
 * @author Werner Keil
 * @author Andi Huber
 */
class UnitFormatRoundtripUtil {

  /**
   * (Supposed) complete set of built-in non-prefixed Imperial Units.
   */
  static enum NonPrefixedImperial {
    Litre(Imperial.LITRE),
    Pint(Imperial.PINT),
    TonUK(Imperial.TON_UK)

    ;

    final Unit<?> unit;

    private NonPrefixedImperial(Unit<?> unit) {
      this.unit = unit;
    }

    /**
     * Does round-trip testing of the given UnitFormat {@code format}.
     * <p>
     * For each built-in non-prefixed Unit we verify, whether this assertion holds:
     * <p>
     * {@code format(unit) === format(parse(format(unit)))}
     * <p>
     * We are doing this for the non-prefixed case as well as all metric-prefixed cases.  
     * 
     * @param format
     */
    void roundtrip(final UnitFormat format) {

      test(format);

// For now we don't test prefixes 
//      for(Prefix prefix : MetricPrefix.values()) {
//        test(format, prefix);
//      }

    }
    
    // -- HELPER

    /** whether the UnitFormat {@code format} can correctly handle the non-prefixed unit */
    private void test(final UnitFormat format) {

      // (1) formatting
      final String unitLiteral = format.format(unit);
      assertNotNull(unitLiteral);
      assertTrue(unitLiteral.length()>0);
      
      // (2) parsing
      Unit<?> parsedUnit;
      try {
        parsedUnit = format.parse(unitLiteral);
      } catch (Exception e) {
        fail(
            String.format("testing '%s', parsing literal '%s' threw an exception", 
                this.name(), unitLiteral), 
            e);
        return;
      }
      
      //assertEquivalent(unit, parsedUnit, null);
      
      // (3) and formatting again
//      String unitLiteralAfterRountrip = format.format(parsedUnit);
//      
//      assertEquals(unitLiteral, unitLiteralAfterRountrip, 
//          ()->String.format("testing '%s', unit literal diverted after roundtrip '%s' -> '%s'", 
//              this.name(), unitLiteral, unitLiteralAfterRountrip));
            
    }

    /** whether the UnitFormat {@code format} can correctly handle the {@code prefix}-ed unit */
    private void test(final UnitFormat format, Prefix prefix) {

      // formatting
      final Unit<?> prefixedUnit = unit.prefix(prefix);
      final String prefixedUnitLiteral = format.format(prefixedUnit);
      assertNotNull(prefixedUnitLiteral);
      assertTrue(prefixedUnitLiteral.length()>0);

      // parsing
      Unit<?> parsedPrefixedUnit;
      try {
        parsedPrefixedUnit = format.parse(prefixedUnitLiteral);
      } catch (Exception e) {
        fail(
            String.format("testing '%s' with prefix '%s', parsing literal '%s' threw an exception", 
                this.name(), prefix.getName(), prefixedUnitLiteral), 
            e);
        return;
      }
      
      assertEquivalent(prefixedUnit, parsedPrefixedUnit, prefix);

    }
    
    /**
     * Fails with detailed message in case given units a and b are not equivalent.
     * @param a
     * @param b
     * @param prefix nullable
     */
    private <Q1 extends Quantity<Q1>, Q2 extends Quantity<Q2>> void assertEquivalent(
        Unit<Q1> a, Unit<Q2> b, Prefix prefix) {
      
      if(prefix!=null) {
      
        assertTrue(areEquivalent(a.getBaseUnits(), b.getBaseUnits()), 
            ()->String.format("testing '%s' with prefix '%s', base unit mismatch", this.name(), prefix.getName()));
        
        assertTrue(areEquivalent(a, b), 
            ()->String.format("testing '%s' with prefix '%s'", this.name(), prefix.getName()));
        
      } else {
        
//        assertTrue(areEquivalent(a.getBaseUnits(), b.getBaseUnits()), 
//            ()->String.format("testing '%s' w/o prefix, base unit mismatch", this.name()));
        
        assertTrue(areEquivalent(a, b), 
            ()->String.format("testing '%s' w/o prefix", this.name()));
        
      }
            
    }
    
    
    /** unit equivalence test */
    private <Q1 extends Quantity<Q1>, Q2 extends Quantity<Q2>> boolean areEquivalent(Unit<Q1> a, Unit<Q2> b) {
      try {
        return a.getConverterToAny(b).isIdentity();
      } catch (UnconvertibleException | IncommensurableException e) {
        return false;
      }
    }
    
    /** base unit (map) equivalence test */
    private <U1 extends Unit<?>, U2 extends Unit<?>> boolean areEquivalent(
        Map<U1, Integer> a, 
        Map<U2, Integer> b) {
      
      if(a==null) {
        return b==null;
      }
      
      if(b==null) {
        return false;
      }
      
      if(a.size()!=b.size()) {
        return false;
      }
      
      Iterator<Map.Entry<U1, Integer>> it1 = a.entrySet().iterator();
      Iterator<Map.Entry<U2, Integer>> it2 = b.entrySet().iterator();
      
      while(it1.hasNext()) {
        Map.Entry<U1, Integer> b1 = it1.next();
        Map.Entry<U2, Integer> b2 = it2.next();

        if(Integer.compare(b1.getValue(), b2.getValue())!=0) {
          return false;
        }
        
        Unit<?> u1 = (Unit<?>) b1.getKey();
        Unit<?> u2 = (Unit<?>) b2.getKey();
        
        if(!u1.equals(u2)) {
          return false;
        }
        
      }
      
      return true;
    }

  }
  
  /**
   * (Supposed) complete set of built-in non-prefixed US Units.
   */
  static enum NonPrefixedUS {
	FootSurvey(USCustomary.FOOT_SURVEY),
    Liter(USCustomary.LITER),
    Pint(USCustomary.PINT),
    Yard(USCustomary.YARD)

    ;

    final Unit<?> unit;

    private NonPrefixedUS(Unit<?> unit) {
      this.unit = unit;
    }

    /**
     * Does round-trip testing of the given UnitFormat {@code format}.
     * <p>
     * For each built-in non-prefixed Unit we verify, whether this assertion holds:
     * <p>
     * {@code format(unit) === format(parse(format(unit)))}
     * <p>
     * We are doing this for the non-prefixed case as well as all metric-prefixed cases.  
     * 
     * @param format
     */
    void roundtrip(final UnitFormat format) {

      test(format);

// For now we don't test prefixes 
//      for(Prefix prefix : MetricPrefix.values()) {
//        test(format, prefix);
//      }

    }
    
    // -- HELPER

    /** whether the UnitFormat {@code format} can correctly handle the non-prefixed unit */
    private void test(final UnitFormat format) {

      // (1) formatting
      final String unitLiteral = format.format(unit);
      assertNotNull(unitLiteral);
      assertTrue(unitLiteral.length()>0);
      
      // (2) parsing
      Unit<?> parsedUnit;
      try {
        parsedUnit = format.parse(unitLiteral);
      } catch (Exception e) {
        fail(
            String.format("testing '%s', parsing literal '%s' threw an exception", 
                this.name(), unitLiteral), 
            e);
        return;
      }
      
      //assertEquivalent(unit, parsedUnit, null);
      
      // (3) and formatting again
//      String unitLiteralAfterRountrip = format.format(parsedUnit);
//      
//      assertEquals(unitLiteral, unitLiteralAfterRountrip, 
//          ()->String.format("testing '%s', unit literal diverted after roundtrip '%s' -> '%s'", 
//              this.name(), unitLiteral, unitLiteralAfterRountrip));
            
    }

    /** whether the UnitFormat {@code format} can correctly handle the {@code prefix}-ed unit */
    private void test(final UnitFormat format, Prefix prefix) {

      // formatting
      final Unit<?> prefixedUnit = unit.prefix(prefix);
      final String prefixedUnitLiteral = format.format(prefixedUnit);
      assertNotNull(prefixedUnitLiteral);
      assertTrue(prefixedUnitLiteral.length()>0);

      // parsing
      Unit<?> parsedPrefixedUnit;
      try {
        parsedPrefixedUnit = format.parse(prefixedUnitLiteral);
      } catch (Exception e) {
        fail(
            String.format("testing '%s' with prefix '%s', parsing literal '%s' threw an exception", 
                this.name(), prefix.getName(), prefixedUnitLiteral), 
            e);
        return;
      }
      
      assertEquivalent(prefixedUnit, parsedPrefixedUnit, prefix);

    }
    
    /**
     * Fails with detailed message in case given units a and b are not equivalent.
     * @param a
     * @param b
     * @param prefix nullable
     */
    private <Q1 extends Quantity<Q1>, Q2 extends Quantity<Q2>> void assertEquivalent(
        Unit<Q1> a, Unit<Q2> b, Prefix prefix) {
      
      if(prefix!=null) {
      
        assertTrue(areEquivalent(a.getBaseUnits(), b.getBaseUnits()), 
            ()->String.format("testing '%s' with prefix '%s', base unit mismatch", this.name(), prefix.getName()));
        
        assertTrue(areEquivalent(a, b), 
            ()->String.format("testing '%s' with prefix '%s'", this.name(), prefix.getName()));
        
      } else {
        
//        assertTrue(areEquivalent(a.getBaseUnits(), b.getBaseUnits()), 
//            ()->String.format("testing '%s' w/o prefix, base unit mismatch", this.name()));
        
        assertTrue(areEquivalent(a, b), 
            ()->String.format("testing '%s' w/o prefix", this.name()));
        
      }
            
    }
    
    
    /** unit equivalence test */
    private <Q1 extends Quantity<Q1>, Q2 extends Quantity<Q2>> boolean areEquivalent(Unit<Q1> a, Unit<Q2> b) {
      try {
        return a.getConverterToAny(b).isIdentity();
      } catch (UnconvertibleException | IncommensurableException e) {
        return false;
      }
    }
    
    /** base unit (map) equivalence test */
    private <U1 extends Unit<?>, U2 extends Unit<?>> boolean areEquivalent(
        Map<U1, Integer> a, 
        Map<U2, Integer> b) {
      
      if(a==null) {
        return b==null;
      }
      
      if(b==null) {
        return false;
      }
      
      if(a.size()!=b.size()) {
        return false;
      }
      
      Iterator<Map.Entry<U1, Integer>> it1 = a.entrySet().iterator();
      Iterator<Map.Entry<U2, Integer>> it2 = b.entrySet().iterator();
      
      while(it1.hasNext()) {
        Map.Entry<U1, Integer> b1 = it1.next();
        Map.Entry<U2, Integer> b2 = it2.next();

        if(Integer.compare(b1.getValue(), b2.getValue())!=0) {
          return false;
        }
        
        Unit<?> u1 = (Unit<?>) b1.getKey();
        Unit<?> u2 = (Unit<?>) b2.getKey();
        
        if(!u1.equals(u2)) {
          return false;
        }
        
      }
      
      return true;
    }

  }



}
