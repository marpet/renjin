package org.renjin.gcc.gimple.expr;

import com.google.bc.common.base.Predicate;

import java.util.List;

public class GimpleMemRef extends GimpleLValue {

  private GimpleExpr pointer;
  private GimpleExpr offset;

  public GimpleMemRef() {
  }

  public GimpleMemRef(GimpleExpr pointer) {
    this.pointer = pointer;
    this.offset = new GimpleIntegerConstant();
    this.offset.setType(pointer.getType());
    setType(pointer.getType().getBaseType());
  }

  public GimpleExpr getPointer() {
    return pointer;
  }

  public void setPointer(GimpleExpr pointer) {
    this.pointer = pointer;
  }

  public GimpleExpr getOffset() {
    return offset;
  }

  public void setOffset(GimpleExpr offset) {
    this.offset = offset;
  }

  public String toString() {
    if(isOffsetZero()) {
      return "*" + pointer;
    } else {
      return "*(" + pointer + "+" + offset + ")";
    }
  }

  @Override
  public void find(Predicate<? super GimpleExpr> predicate, List<GimpleExpr> results) {
    findOrDescend(pointer, predicate, results);
    findOrDescend(offset, predicate, results);
  }

  @Override
  public boolean replace(Predicate<? super GimpleExpr> predicate, GimpleExpr replacement) {
    if(predicate.apply(pointer)) {
      pointer = replacement;
      return true;
    } else if(predicate.apply(offset)) {
      offset = replacement;
      return true;
    } else {
      return false;
    }
  }

  public boolean isOffsetZero() {
    return offset instanceof GimpleIntegerConstant && 
        ((GimpleIntegerConstant) offset).getNumberValue().intValue() == 0;
  }
}
