/**
 * ADOBE SYSTEMS INCORPORATED
 * Copyright 2009-2013 Adobe Systems Incorporated
 * All Rights Reserved.
 *
 * NOTICE: Adobe permits you to use, modify, and distribute
 * this file in accordance with the terms of the MIT license,
 * a copy of which can be found in the LICENSE.txt file or at
 * http://opensource.org/licenses/MIT.
 */
package compile.gen.java.inline;

import compile.gen.java.StatementFormatter;
import compile.term.ApplyTerm;
import compile.term.Term;
import compile.term.TupleTerm;

import java.util.List;

/**
 * Try inlining calls to {@link runtime.intrinsic._shiftl}.
 *
 * @author Keith McGuigan
 */
public class ShiftlInliner implements Inliner
{
    public String tryInlining(
        final ApplyTerm apply, final StatementFormatter fmt,
        final boolean stmtsOkay)
    {
        final List<Term> args = ((TupleTerm)apply.getArg()).getItems();

        final String l = fmt.formatTermAs(args.get(0), int.class);
        final String r = fmt.formatTermAs(args.get(1), int.class);

        final String expr = "((" + l + ") << (" + r + "))";

        if (stmtsOkay)
            return expr;

        return fmt.fixup(apply.getLoc(), expr, int.class);
    }
}
