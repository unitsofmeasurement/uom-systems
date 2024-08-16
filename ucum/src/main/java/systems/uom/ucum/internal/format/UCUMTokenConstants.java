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
package systems.uom.ucum.internal.format;

/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
interface UCUMTokenConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int ATOM_CHAR = 1;
  /** RegularExpression Id. */
  int ESCAPED_ATOM_CHAR = 2;
  /** RegularExpression Id. */
  int TERMINAL_ATOM_CHAR = 3;
  /** RegularExpression Id. */
  int LCBRACKET = 4;
  /** RegularExpression Id. */
  int RCBRACKET = 5;
  /** RegularExpression Id. */
  int LSBRACKET = 6;
  /** RegularExpression Id. */
  int RSBRACKET = 7;
  /** RegularExpression Id. */
  int ANNOTATION = 8;
  /** RegularExpression Id. */
  int FACTOR = 9;
  /** RegularExpression Id. */
  int SIGN = 10;
  /** RegularExpression Id. */
  int DOT = 11;
  /** RegularExpression Id. */
  int SOLIDUS = 12;
  /** RegularExpression Id. */
  int ATOM = 13;

  /** Lexical state. */
  int DEFAULT = 0;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "<ATOM_CHAR>",
    "<ESCAPED_ATOM_CHAR>",
    "<TERMINAL_ATOM_CHAR>",
    "\"{\"",
    "\"}\"",
    "\"[\"",
    "\"]\"",
    "<ANNOTATION>",
    "<FACTOR>",
    "<SIGN>",
    "\".\"",
    "\"/\"",
    "<ATOM>",
    "\"(\"",
    "\")\"",
  };

}
