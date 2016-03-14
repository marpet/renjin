package org.renjin.primitives.special;

import org.renjin.eval.Context;
import org.renjin.eval.EvalException;
import org.renjin.sexp.*;

/**
 * {@code $<-} function
 */
public class DollarAssignFunction extends SpecialFunction {
  public DollarAssignFunction() {
    super("$<-");
  }

  @Override
  public SEXP apply(Context context, Environment rho, FunctionCall call, PairList args) {


    // Even though this function is generic, it MUST be called with exactly three arguments
    if(args.length() != 3) {
      throw new EvalException(String.format("%d argument(s) passed to '$<-' which requires 3", args.length()));
    }


    SEXP object = context.evaluate(args.getElementAsSEXP(0), rho);
    StringVector nameArgument = DollarFunction.evaluateName(args.getElementAsSEXP(1));
    SEXP rhs = args.getElementAsSEXP(2);
    
    throw new UnsupportedOperationException();
    
  }
}
