package org.renjin.stats.internals.models;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.google.bc.common.collect.Iterables;
import com.google.bc.common.collect.Iterators;
import com.google.bc.common.collect.Lists;

public class TermList implements Iterable<Term> {
  private List<Term> list = Lists.newArrayList();
  
  public void add(Term term) {
    if(!list.contains(term)) {
      list.add(term);
    }
  }
  
  public void add(TermList termsToAdd) {
    Iterables.addAll(this.list, termsToAdd);
  }
  
  public void subtract(Term term) {
    list.remove(term);
  }

  public void subtract(TermList toRemove) {
    for(Term term : toRemove) {
      subtract(term);
    }
  }
  
  public TermList sorted() {
    Collections.sort(list, new Comparator<Term>() {

      @Override
      public int compare(Term a, Term b) {
        return a.getExpressions().size() - b.getExpressions().size();
      }
    });
    return this;
  }
  
  @Override
  public Iterator<Term> iterator() {
    return Iterators.unmodifiableIterator(list.iterator());
  }
}
