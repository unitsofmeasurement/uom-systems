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

import static systems.uom.ucum.internal.format.UnitTokenConstants.*;
import static tech.units.indriya.AbstractUnit.ONE;

import javax.measure.Unit;
import javax.measure.Prefix;

import tech.units.indriya.format.SymbolMap;
import tech.units.indriya.function.LogConverter;
import tech.units.indriya.function.MultiplyConverter;


@SuppressWarnings({"rawtypes", "unchecked"})
public final class UnitFormatParser {

    private static class Exponent {

        public final int pow;

        public final int root;

        public Exponent(int pow, int root) {
            this.pow = pow;
            this.root = root;
        }
    }
    private SymbolMap symbols;

    public UnitFormatParser(SymbolMap symbols, java.io.Reader in) {
        this(in);
        this.symbols = symbols;
    }


    final public Unit parseUnit() throws TokenException {
        Unit result = CompoundExpr();
        consumeToken(0);
        {
            return result;
        }
    }

    final public Unit CompoundExpr() throws TokenException {
        throw new UnsupportedOperationException("Compound units not supported");
    }

    final public Unit AddExpr() throws TokenException {
        Unit result = ONE;
        Number n1 = null;
        Token sign1 = null;
        Number n2 = null;
        Token sign2 = null;
        if (jj_2_1(2147483647)) {
            n1 = NumberExpr();
            sign1 = Sign();
        } else {
        }
        result = MulExpr();
        switch ((nextTokenIndex == -1) ? jj_ntk() : nextTokenIndex) {
            case PLUS:
            case MINUS:
                sign2 = Sign();
                n2 = NumberExpr();
                break;
            default:
                laA[1] = genInt;
        }
        if (n1 != null) {
            if (sign1.image.equals("-")) {
                result = result.multiply(-1);
            }
            result = result.shift(n1.doubleValue());
        }
        if (n2 != null) {
            double offset = n2.doubleValue();
            if (sign2.image.equals("-")) {
                offset = -offset;
            }
            result = result.shift(offset);
        }
        {
            return result;
        }
    }

    final public Unit MulExpr() throws TokenException {
        Unit result = ONE;
        Unit temp = ONE;
        result = ExponentExpr();
        label_2:
        while (true) {
            switch ((nextTokenIndex == -1) ? jj_ntk() : nextTokenIndex) {
                case ASTERISK:
                case MIDDLE_DOT:
                case SOLIDUS:
                    break;
                default:
                    laA[2] = genInt;
                    break label_2;
            }
            switch ((nextTokenIndex == -1) ? jj_ntk() : nextTokenIndex) {
                case ASTERISK:
                case MIDDLE_DOT:
                    switch ((nextTokenIndex == -1) ? jj_ntk() : nextTokenIndex) {
                        case ASTERISK:
                            consumeToken(ASTERISK);
                            break;
                        case MIDDLE_DOT:
                            consumeToken(MIDDLE_DOT);
                            break;
                        default:
                            laA[3] = genInt;
                            consumeToken(-1);
                            throw new TokenException();
                    }
                    temp = ExponentExpr();
                    result = result.multiply(temp);
                    break;
                case SOLIDUS:
                    consumeToken(SOLIDUS);
                    temp = ExponentExpr();
                    result = result.divide(temp);
                    break;
                default:
                    laA[4] = genInt;
                    consumeToken(-1);
                    throw new TokenException();
            }
        }
        {
            return result;
        }
    }

    final public Unit ExponentExpr() throws TokenException {
        Unit result = ONE;
        Exponent exponent = null;
        Token token = null;
        if (jj_2_2(2147483647)) {
            switch ((nextTokenIndex == -1) ? jj_ntk() : nextTokenIndex) {
                case INTEGER:
                    token = consumeToken(INTEGER);
                    break;
                case E:
                    token = consumeToken(E);
                    break;
                default:
                    laA[5] = genInt;
                    consumeToken(-1);
                    throw new TokenException();
            }
            consumeToken(CARET);
            result = AtomicExpr();
            double base;
            if (token.kind == INTEGER) {
                base = Integer.parseInt(token.image);
            } else {
                base = StrictMath.E;
            }
            {
                return result.transform(new LogConverter(base).inverse());
            }
        } else {
            switch ((nextTokenIndex == -1) ? jj_ntk() : nextTokenIndex) {
                case OPEN_PAREN:
                case INTEGER:
                case FLOATING_POINT:
                case UNIT_IDENTIFIER:
                    result = AtomicExpr();
                    switch ((nextTokenIndex == -1) ? jj_ntk() : nextTokenIndex) {
                        case CARET:
                        case SUPERSCRIPT_INTEGER:
                            exponent = Exp();
                            break;
                        default:
                            laA[6] = genInt;
                    }
                    if (exponent != null) {
                        if (exponent.pow != 1) {
                            result = result.pow(exponent.pow);
                        }
                        if (exponent.root != 1) {
                            result = result.root(exponent.root);
                        }
                    } {
                    return result;
                }
                case LOG:
                case NAT_LOG:
                    switch ((nextTokenIndex == -1) ? jj_ntk() : nextTokenIndex) {
                        case LOG:
                            consumeToken(LOG);
                            switch ((nextTokenIndex == -1) ? jj_ntk() : nextTokenIndex) {
                                case INTEGER:
                                    token = consumeToken(INTEGER);
                                    break;
                                default:
                                    laA[7] = genInt;
                            }
                            break;
                        case NAT_LOG:
                            token = consumeToken(NAT_LOG);
                            break;
                        default:
                            laA[8] = genInt;
                            consumeToken(-1);
                            throw new TokenException();
                    }
                    consumeToken(OPEN_PAREN);
                    result = AddExpr();
                    consumeToken(CLOSE_PAREN);
                    double base = 10;
                    if (token != null) {
                        if (token.kind == INTEGER) {
                            base = Integer.parseInt(token.image);
                        } else if (token.kind == NAT_LOG) {
                            base = StrictMath.E;
                        }
                    } {
                    return result.transform(new LogConverter(base));
                }
                default:
                    laA[9] = genInt;
                    consumeToken(-1);
                    throw new TokenException();
            }
        }
    }

    final public Unit AtomicExpr() throws TokenException {
        Unit result = ONE;
        Number n = null;
        Token token = null;
        switch ((nextTokenIndex == -1) ? jj_ntk() : nextTokenIndex) {
            case INTEGER:
            case FLOATING_POINT:
                n = NumberExpr();
                if (n instanceof Integer) {
                    {
                        return result.multiply(n.intValue());
                    }
                } else {
                    {
                        return result.multiply(n.doubleValue());
                    }
                }
            case UNIT_IDENTIFIER:
                token = consumeToken(UNIT_IDENTIFIER);
                Unit unit = symbols.getUnit(token.image);
                if (unit == null) {
                    Prefix prefix = symbols.getPrefix(token.image);
                    if (prefix != null) {
                        String prefixSymbol = symbols.getSymbol(prefix);
                        unit = symbols.getUnit(token.image.substring(prefixSymbol.length()));
                        if (unit != null) {
                            {
                                return unit.transform(MultiplyConverter.ofPrefix(prefix));
                            }
                        }
                    }
                    {
                        throw new TokenException();
                    }
                } else {
                    {
                        return unit;
                    }
                }
            case OPEN_PAREN:
                consumeToken(OPEN_PAREN);
                result = AddExpr();
                consumeToken(CLOSE_PAREN); {
                return result;
            }
            default:
                laA[10] = genInt;
                consumeToken(-1);
                throw new TokenException();
        }
    }

    final public Token Sign() throws TokenException {
        Token result = null;
        switch ((nextTokenIndex == -1) ? jj_ntk() : nextTokenIndex) {
            case PLUS:
                result = consumeToken(PLUS);
                break;
            case MINUS:
                result = consumeToken(MINUS);
                break;
            default:
                laA[11] = genInt;
                consumeToken(-1);
                throw new TokenException();
        }
        {
            return result;
        }
    }

    final public Number NumberExpr() throws TokenException {
        Token token = null;
        switch ((nextTokenIndex == -1) ? jj_ntk() : nextTokenIndex) {
            case INTEGER:
                token = consumeToken(INTEGER); {
                return Long.valueOf(token.image);
            }
            case FLOATING_POINT:
                token = consumeToken(FLOATING_POINT); {
                return Double.valueOf(token.image);
            }
            default:
                laA[12] = genInt;
                consumeToken(-1);
                throw new TokenException();
        }
    }

    final public Exponent Exp() throws TokenException {
        Token powSign = null;
        Token powToken = null;
        Token rootSign = null;
        Token rootToken = null;
        switch ((nextTokenIndex == -1) ? jj_ntk() : nextTokenIndex) {
            case CARET:
                consumeToken(CARET);
                switch ((nextTokenIndex == -1) ? jj_ntk() : nextTokenIndex) {
                    case PLUS:
                    case MINUS:
                    case INTEGER:
                        switch ((nextTokenIndex == -1) ? jj_ntk() : nextTokenIndex) {
                            case PLUS:
                            case MINUS:
                                powSign = Sign();
                                break;
                            default:
                                laA[13] = genInt;
                        }
                        powToken = consumeToken(INTEGER);
                        int pow = Integer.parseInt(powToken.image);
                        if ((powSign != null) && powSign.image.equals("-")) {
                            pow = -pow;
                        } {
                        return new Exponent(pow, 1);
                    }
                    case OPEN_PAREN:
                        consumeToken(OPEN_PAREN);
                        switch ((nextTokenIndex == -1) ? jj_ntk() : nextTokenIndex) {
                            case PLUS:
                            case MINUS:
                                powSign = Sign();
                                break;
                            default:
                                laA[14] = genInt;
                        }
                        powToken = consumeToken(INTEGER);
                        switch ((nextTokenIndex == -1) ? jj_ntk() : nextTokenIndex) {
                            case SOLIDUS:
                                consumeToken(SOLIDUS);
                                switch ((nextTokenIndex == -1) ? jj_ntk() : nextTokenIndex) {
                                    case PLUS:
                                    case MINUS:
                                        rootSign = Sign();
                                        break;
                                    default:
                                        laA[15] = genInt;
                                }
                                rootToken = consumeToken(INTEGER);
                                break;
                            default:
                                laA[16] = genInt;
                        }
                        consumeToken(CLOSE_PAREN);
                        pow = Integer.parseInt(powToken.image);
                        if ((powSign != null) && powSign.image.equals("-")) {
                            pow = -pow;
                        }
                        int root = 1;
                        if (rootToken != null) {
                            root = Integer.parseInt(rootToken.image);
                            if ((rootSign != null) && rootSign.image.equals("-")) {
                                root = -root;
                            }
                        } {
                        return new Exponent(pow, root);
                    }
                    default:
                        laA[17] = genInt;
                        consumeToken(-1);
                        throw new TokenException();
                }
            case SUPERSCRIPT_INTEGER:
                powToken = consumeToken(SUPERSCRIPT_INTEGER);
                int pow = 0;
                for (int i = 0; i < powToken.image.length(); i += 1) {
                    pow *= 10;
                    switch (powToken.image.charAt(i)) {
                        case '\u00b9':
                            pow += 1;
                            break;
                        case '\u00b2':
                            pow += 2;
                            break;
                        case '\u00b3':
                            pow += 3;
                            break;
                        case '\u2074':
                            pow += 4;
                            break;
                        case '\u2075':
                            pow += 5;
                            break;
                        case '\u2076':
                            pow += 6;
                            break;
                        case '\u2077':
                            pow += 7;
                            break;
                        case '\u2078':
                            pow += 8;
                            break;
                        case '\u2079':
                            pow += 9;
                            break;
                    }
                } {
                return new Exponent(pow, 1);
            }
            default:
                laA[18] = genInt;
                consumeToken(-1);
                throw new TokenException();
        }
    }

    private boolean jj_2_1(int xla) {
        laInt = xla;
        lastpos = scanpos = token;
        try {
            return !jj_3_1();
        } catch (LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(0, xla);
        }
    }

    private boolean jj_2_2(int xla) {
        laInt = xla;
        lastpos = scanpos = token;
        try {
            return !jj_3_2();
        } catch (LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(1, xla);
        }
    }

    private boolean jj_3R_3() {
        Token xsp;
        xsp = scanpos;
        if (jj_3R_5()) {
            scanpos = xsp;
            if (jj_3R_6())
                return true;
        }
        return false;
    }

    private boolean jj_3R_6() {
        return scanToken(FLOATING_POINT);
    }

    private boolean jj_3_2() {
        Token xsp;
        xsp = scanpos;
        if (scanToken(14)) {
            scanpos = xsp;
            if (scanToken(19))
                return true;
        }
        return scanToken(CARET);
    }

    private boolean jj_3_1() {
        return jj_3R_3() || jj_3R_4();
    }

    private boolean jj_3R_4() {
        Token xsp;
        xsp = scanpos;
        if (scanToken(5)) {
            scanpos = xsp;
            if (scanToken(6))
                return true;
        }
        return false;
    }

    private boolean jj_3R_5() {
        return scanToken(INTEGER);
    }
    /** Generated Token Manager. */
    public UnitTokenManager tokenSource;

    UCUMCharStream inputStream;

    /** Current token. */
    public Token token;

    /** Next token. */
    public Token nextToken;

    private int nextTokenIndex;

    private Token scanpos, lastpos;

    private int laInt;

    private int genInt;

    final private int[] laA = new int[19];

    static private int[] laB;

    static {
        init();
    }

    private static void init() {
        laB = new int[]{0x800, 0x60, 0x380, 0x180, 0x380, 0x84000, 0x8400, 0x4000, 0x60000, 0x175000, 0x115000, 0x60, 0x14000, 0x60, 0x60, 0x60, 0x200, 0x5060, 0x8400,};
    }
    final private JJCalls[] rtns = new JJCalls[2];

    private boolean rescan = false;

    private int gcInt = 0;

    /** Constructor with InputStream. */
    public UnitFormatParser(java.io.InputStream stream) {
        this(stream, null);
    }

    /** Constructor with InputStream and supplied encoding */
    public UnitFormatParser(java.io.InputStream stream, String encoding) {
        try {
            inputStream = new UCUMCharStream(stream, encoding, 1, 1);
        } catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        tokenSource = new UnitTokenManager(inputStream);
        token = new Token();
        nextTokenIndex = -1;
        genInt = 0;
        for (int i = 0; i < 19; i++) {
            laA[i] = -1;
        }
        for (int i = 0; i < rtns.length; i++) {
            rtns[i] = new JJCalls();
        }
    }

    /** Reinitialise. */
    public void ReInit(java.io.InputStream stream) {
        ReInit(stream, null);
    }

    /** Reinitialise. */
    public void ReInit(java.io.InputStream stream, String encoding) {
        try {
            inputStream.ReInit(stream, encoding, 1, 1);
        } catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        tokenSource.ReInit(inputStream);
        token = new Token();
        nextTokenIndex = -1;
        genInt = 0;
        for (int i = 0; i < 19; i++) {
            laA[i] = -1;
        }
        for (int i = 0; i < rtns.length; i++) {
            rtns[i] = new JJCalls();
        }
    }

    /** Constructor. */
    public UnitFormatParser(java.io.Reader stream) {
        inputStream = new UCUMCharStream(stream, 1, 1);
        tokenSource = new UnitTokenManager(inputStream);
        token = new Token();
        nextTokenIndex = -1;
        genInt = 0;
        for (int i = 0; i < 19; i++) {
            laA[i] = -1;
        }
        for (int i = 0; i < rtns.length; i++) {
            rtns[i] = new JJCalls();
        }
    }

    /** Reinitialise. */
    public void ReInit(java.io.Reader stream) {
        inputStream.ReInit(stream, 1, 1);
        tokenSource.ReInit(inputStream);
        token = new Token();
        nextTokenIndex = -1;
        genInt = 0;
        for (int i = 0; i < 19; i++) {
            laA[i] = -1;
        }
        for (int i = 0; i < rtns.length; i++) {
            rtns[i] = new JJCalls();
        }
    }

    /** Constructor with generated Token Manager. */
    public UnitFormatParser(UnitTokenManager tm) {
        tokenSource = tm;
        token = new Token();
        nextTokenIndex = -1;
        genInt = 0;
        for (int i = 0; i < 19; i++) {
            laA[i] = -1;
        }
        for (int i = 0; i < rtns.length; i++) {
            rtns[i] = new JJCalls();
        }
    }

    /** Reinitialise. */
    public void ReInit(UnitTokenManager tm) {
        tokenSource = tm;
        token = new Token();
        nextTokenIndex = -1;
        genInt = 0;
        for (int i = 0; i < 19; i++) {
            laA[i] = -1;
        }
        for (int i = 0; i < rtns.length; i++) {
            rtns[i] = new JJCalls();
        }
    }

    private Token consumeToken(int kind) throws TokenException {
        Token oldToken;
        if ((oldToken = token).next != null)
            token = token.next;
        else
            token = token.next = tokenSource.getNextToken();
        nextTokenIndex = -1;
        if (token.kind == kind) {
            genInt++;
            if (++gcInt > 100) {
                gcInt = 0;
                for (JJCalls jj_2_rtn : rtns) {
                    JJCalls c = jj_2_rtn;
                    while (c != null) {
                        if (c.gen < genInt)
                            c.first = null;
                        c = c.next;
                    }
                }
            }
            return token;
        }
        token = oldToken;
        this.kind = kind;
        throw raiseTokenException();
    }

    static private final class LookaheadSuccess extends java.lang.RuntimeException {
        private static final long serialVersionUID = 2205332054119123041L;
    }

    private boolean scanToken(int kind) {
        if (scanpos == lastpos) {
            laInt--;
            if (scanpos.next == null) {
                lastpos = scanpos = scanpos.next = tokenSource.getNextToken();
            } else {
                lastpos = scanpos = scanpos.next;
            }
        } else {
            scanpos = scanpos.next;
        }
        if (rescan) {
            int i = 0;
            Token tok = token;
            while (tok != null && tok != scanpos) {
                i++;
                tok = tok.next;
            }
            if (tok != null)
                jj_add_error_token(kind, i);
        }
        if (scanpos.kind != kind)
            return true;
        if (laInt == 0 && scanpos == lastpos)
            throw new LookaheadSuccess();
        return false;
    }

    /** Get the next Token. */
    final public Token getNextToken() {
        if (token.next != null)
            token = token.next;
        else
            token = token.next = tokenSource.getNextToken();
        nextTokenIndex = -1;
        genInt++;
        return token;
    }

    /** Get the specific Token. */
    final public Token getToken(int index) {
        Token t = token;
        for (int i = 0; i < index; i++) {
            if (t.next != null)
                t = t.next;
            else
                t = t.next = tokenSource.getNextToken();
        }
        return t;
    }

    private int jj_ntk() {
        if ((nextToken = token.next) == null) {
            return (nextTokenIndex = (token.next = tokenSource.getNextToken()).kind);
        }
        else {
            return (nextTokenIndex = nextToken.kind);
        }
    }
    private java.util.List<int[]> expentries = new java.util.ArrayList<>();

    private int[] expentry;

    private int kind = -1;

    private int[] lastTokens = new int[100];

    private int endpos;

    private void jj_add_error_token(int kind, int pos) {
        if (pos >= 100)
            return;
        if (pos == endpos + 1) {
            lastTokens[endpos++] = kind;
        } else if (endpos != 0) {
            expentry = new int[endpos];
            System.arraycopy(lastTokens, 0, expentry, 0, endpos);
            entriesLoop:
            for (int[] jj_expentry1 : expentries) {
                if (jj_expentry1.length == expentry.length) {
                    for (int i = 0; i < expentry.length; i++) {
                        if (jj_expentry1[i] != expentry[i]) {
                            continue entriesLoop;
                        }
                    }
                    expentries.add(expentry);
                    break;
                }
            }
            if (pos != 0)
                lastTokens[(endpos = pos) - 1] = kind;
        }
    }

    /** Generate TokenException. */
    TokenException raiseTokenException() {
        expentries.clear();
        boolean[] la1tokens = new boolean[21];
        if (kind >= 0) {
            la1tokens[kind] = true;
            kind = -1;
        }
        for (int i = 0; i < 19; i++) {
            if (laA[i] == genInt) {
                for (int j = 0; j < 32; j++) {
                    if ((laB[i] & (1 << j)) != 0) {
                        la1tokens[j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < 21; i++) {
            if (la1tokens[i]) {
                expentry = new int[1];
                expentry[0] = i;
                expentries.add(expentry);
            }
        }
        endpos = 0;
        jj_rescan_token();
        jj_add_error_token(0, 0);
        int[][] exptokseq = new int[expentries.size()][];
        for (int i = 0; i < expentries.size(); i++) {
            exptokseq[i] = expentries.get(i);
        }
        return new TokenException(token, exptokseq, tokenImage);
    }

    /** Enable tracing. */
    final public void enable_tracing() {
    }

    /** Disable tracing. */
    final public void disable_tracing() {
    }

    private void jj_rescan_token() {
        rescan = true;
        for (int i = 0; i < 2; i++) {
            try {
                JJCalls p = rtns[i];
                do {
                    if (p.gen > genInt) {
                        laInt = p.arg;
                        lastpos = scanpos = p.first;
                        switch (i) {
                            case 0:
                                jj_3_1();
                                break;
                            case 1:
                                jj_3_2();
                                break;
                        }
                    }
                    p = p.next;
                } while (p != null);
            } catch (LookaheadSuccess ls) {
            }
        }
        rescan = false;
    }

    private void jj_save(int index, int xla) {
        JJCalls p = rtns[index];
        while (p.gen > genInt) {
            if (p.next == null) {
                p = p.next = new JJCalls();
                break;
            }
            p = p.next;
        }
        p.gen = genInt + xla - laInt;
        p.first = token;
        p.arg = xla;
    }

    static final class JJCalls {

        int gen;

        Token first;

        int arg;

        JJCalls next;

    }
}
