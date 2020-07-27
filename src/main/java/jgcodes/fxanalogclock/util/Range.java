package jgcodes.fxanalogclock.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

public class Range implements Iterable<Integer> {
  private final int from, to;
  
  public Range(int from, int to) {
    this.from = Math.min(from, to);
    this.to = Math.max(from, to);
  }
  
  public int from() {
    return from;
  }
  
  public int to() {
    return to;
  }
  
  @Override
  public Iterator<Integer> iterator() {
    return new RangeIterator(this);
  }
  
  @Override
  public void forEach(Consumer<? super Integer> action) {
    iterator().forEachRemaining(action);
  }
  
  @Override
  public Spliterator<Integer> spliterator() {
    return Spliterators.spliterator(this.iterator(), ((long) to - from + 1), Spliterator.IMMUTABLE | Spliterator.SORTED);
  }
}
class RangeIterator implements Iterator<Integer> {
  private final Range range;
  private int index = 0;
  
  public RangeIterator(Range range) {
    this.range = range;
  }
  
  @Override
  public boolean hasNext() {
    return range.from() + index <= range.to();
  }
  
  @Override
  public Integer next() {
    if (!hasNext()) throw new NoSuchElementException("Range is iterated over");
    return range.from() + index++;
  }
}
