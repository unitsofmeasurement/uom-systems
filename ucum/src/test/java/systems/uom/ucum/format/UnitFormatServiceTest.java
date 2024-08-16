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

import static org.junit.jupiter.api.Assertions.*;

import javax.measure.spi.ServiceProvider;
import javax.measure.format.UnitFormat;
import javax.measure.spi.FormatService;

import org.junit.jupiter.api.Test;


/**
 * Tests for services provided via {@link ServiceProvider}.
 */
public class UnitFormatServiceTest {

  @Test
  public void testGetService() throws Exception {
	final FormatService fs = ServiceProvider.current().getFormatService();
    assertNotNull(fs);
    final UnitFormat uf = fs.getUnitFormat(); 
    assertNotNull(uf);    
    assertEquals("Parsing", uf.getClass().getSimpleName());
    assertEquals("UCUM Parsing [Case Sensitive]", uf.toString());
  }

  @Test
  public void testGetCIFound() throws Exception {
	final FormatService fs = ServiceProvider.current().getFormatService();
    assertNotNull(fs);
    final UnitFormat uf = fs.getUnitFormat("UCUM", "CI");
    assertNotNull(uf);
    assertEquals("UCUM Parsing [Case Insensitive]", uf.toString());
  }
  
  @Test
  public void testGetCSFound() throws Exception {
	final FormatService fs = ServiceProvider.current().getFormatService();
    assertNotNull(fs);
    final UnitFormat uf = fs.getUnitFormat("UCUM", "CS");
    assertNotNull(uf);
    assertEquals("UCUM Parsing [Case Sensitive]", uf.toString());
  }

  @Test
  public void testGetPrintFound() throws Exception {
	final FormatService fs = ServiceProvider.current().getFormatService();
    assertNotNull(fs);
    final UnitFormat uf = fs.getUnitFormat("UCUM", "Print");
    assertNotNull(uf);
    assertEquals("UCUM Print", uf.toString());
  }
  
  @Test
  public void testGetFormatNotFound() throws Exception {
	final FormatService fs = ServiceProvider.current().getFormatService();
    assertNotNull(fs);
    assertNull(fs.getUnitFormat("XYZ"));
  }
  
  @Test
  public void testGetFormatAlias() throws Exception {
	final FormatService fs = ServiceProvider.current().getFormatService();
    assertNotNull(fs);
    assertNotNull(fs.getUnitFormat("UCUM"));
  }
  
  @Test
  public void testGetFormatAliasCI() throws Exception {
	final FormatService fs = ServiceProvider.current().getFormatService();
    assertNotNull(fs);
    assertNotNull(fs.getUnitFormat("CI"));
  }
  
  @Test
  public void testGetFormatAliasCS() throws Exception {
	final FormatService fs = ServiceProvider.current().getFormatService();
    assertNotNull(fs);
    assertNotNull(fs.getUnitFormat("CS"));
  }

  @Test
  public void testGetFormatAliasPrint() throws Exception {
	final FormatService fs = ServiceProvider.current().getFormatService();
    assertNotNull(fs);
    assertNotNull(fs.getUnitFormat("Print"));
  }
}
