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

import java.util.function.Predicate;

public class IsGreaterThan implements Predicate<Integer> {
  public final int threshold;

  public IsGreaterThan(final int threshod) {
    this.threshold = threshod;
  }

  @Override
  public boolean test(Integer t) {
    return t > threshold;
  }

  public static void main(String[] args) {
    Predicate<Integer> isGreaterThanFive = new IsGreaterThan(5);

    for (int i = 0; i < 10; i++) if (isGreaterThanFive.test(i)) System.out.println(i);
  }
}
