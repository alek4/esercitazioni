/*

Copyright 2021 Luca Prigioniero, Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

This is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This material is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this file.  If not, see <https://www.gnu.org/licenses/>.

*/

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class FilterIterator<T> implements Iterator<T> {

  private final Iterator<T> source;
  private final Predicate<T> filter;
  private boolean hasNext = false;
  private T next = null;

  public FilterIterator(final Iterator<T> source, Predicate<T> filter) {
    this.source = source;
    this.filter = filter;
  }

  @Override
  public boolean hasNext() {
    while (!hasNext && source.hasNext()) {
      next = source.next();
      hasNext = filter.test(next);
    }
    return hasNext;
  }

  @Override
  public T next() {
    if (!hasNext()) throw new NoSuchElementException();
    hasNext = false;
    return next;
  }

  public static void main(String[] args) {
    Iterator<Integer> filtered =
        new FilterIterator<>(
            List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).iterator(), new IsGreaterThan(5));
    while (filtered.hasNext()) System.out.println(filtered.next());
  }
}
