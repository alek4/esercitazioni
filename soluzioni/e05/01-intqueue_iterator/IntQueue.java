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
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/*-
 * OVERVIEW: Le istanze di questa classe rappresentano code (limitate) di interi.
 *           Gli oggetti di questo tipo sono mutabili.
 *           Una coda tipica è [x_1, x_2, ..., x_k], in cui k è minore della capienza della coda.
 *           Dato che è una struttura dati che opera in modalità FIFO,
 *           a seguito di una operazione di enqueue la coda sarà [x_1, x_2, ..., x_k, x_k+1],
 *           mentre a seguito di una operazione di dequeue la coda sarà [x_2, ..., x_k].
 */
public class IntQueue implements Iterable<Integer> {
  // CAMPI
  /*- La struttura dati contenente gli elementi dell'IntQueue this. */
  private final int[] elements;

  /*- Gli indici della testa e della coda della IntQueue.
   *  Nello specifico, head è l'indice del primo elemento di this (-1 se la coda è vuota),
   *  mentre tail indica l'indice della prima posizione disponibile di this (head = tail se la coda è piena).
   */
  private int head, tail;
  
  /** Se questo flag è true, elements è stato cambiato */
  private boolean changed;

  /*-
   * ABS FUN:  AF(elements, head, tail)
   *           = [ elements[i] | head <= i < tail ]
   *           = [elements[head], elements[head+1], ..., elements[tail-1]] se -1 < head <= tail
   *           o [elements[head], elements[head+1], ..., elements[elements.size-1], elements[0], ..., elements[tail-1]] se head > tail
   *
   * REP INV:  la coda non contiene più elementi della sua capienza massima,
   *          -1 <= head < size
   *           0 <= tail < size
   *           head == -1 ⇒ tail = 0
   */

  // COSTRUTTORI
  /*-
   * EFFECTS: Inizializza this affinché rappresenti una coda vuota con dimensione massima n.
   *          Solleva un'eccezione di tipo IllegalArgumentException se n <= 0.
   */
  public IntQueue(int n) {
		if (n <= 0) throw new IllegalArgumentException("Impossibile creare coda. Richiesta dimensione positiva.");

    elements = new int[n];
    head = -1;
    tail = 0;

    assert repOK();
  }

  // Metodi
  /*-
   * MODIFIES: this
   * EFFECTS: Aggiunge l'elemento x alla coda this se la coda non è piena,
   *          altrimenti solleva un'eccezione di tipo FullQueueException
   *          this_post = this + [x]
   */
  public void enqueue(int x) {
    if (isFull()) throw new FullQueueException("Impossibile aggiungere elemento. Coda piena.");
    if (isEmpty()) head = 0;
    elements[tail] = x;
    tail = (tail + 1) % elements.length;
    changed = true;

    assert repOK();
  }

  /*-
   * MODIFIES: this
   * EFFECTS: Rimuove e restituisce l'elemento in testa alla coda this, se presente.
   *          Solleva un'eccezione di tipo EmptyQueueException se la coda è vuota
   *          this = [x1, x2, ..., x_k], k < n, this_post = [x2, ..., x_k]
   */
  public int dequeue() {
    if (isEmpty()) throw new EmptyQueueException("Impossibile estrarre elemento. Coda vuota.");
    int r = elements[head];
    head = (head + 1) % elements.length;
    if (head == tail) {
      head = -1;
      tail = 0;
    }
    changed = true;

    assert repOK();

    return r;
  }

  /*-
   * EFFECTS: restituisce true se la coda this è piena, false altrimenti.
   */
  public boolean isFull() {
    return head == tail;
  }

  /*-
   * EFFECTS: restituisce true se la coda this è vuota, false altrimenti.
   */
  public boolean isEmpty() {
    return head == -1;
  }

  /*-
   * EFFECTS: restituisce il numero di elementi contenuti in this
   */
  public int size() {
    if (isEmpty()) return 0;
    if (isFull()) return elements.length;
    return (tail - head + elements.length) % elements.length;
  }

  private boolean repOK() {
    return size() <= elements.length
        && elements != null
        && head >= -1
        && head < elements.length
        && tail >= 0
        && tail < elements.length
        && (head != -1 || tail == 0);
  }

  @Override
  public String toString() {
    assert repOK();

    StringBuilder sb = new StringBuilder("IntQueue : [");
    if (!isEmpty()) {
      int i;
      for (i = 0; i < size() - 1; i++)
        sb.append(elements[(head + i) % elements.length]).append(", ");
      sb.append(elements[(head + i) % elements.length]);
    }
    sb.append(']');
    return sb.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof IntQueue)) return false;
    IntQueue other = (IntQueue) obj;

    if (size() != other.size() || elements.length != other.elements.length) return false;
    for (int i = 0; i < size(); i++)
      if (elements[(head + i) % elements.length]
          != other.elements[(other.head + i) % elements.length]) return false;

    return true;
  }

  @Override
  public int hashCode(){
    int result = Integer.hashCode(elements.length);
    for (int i = 0; i < size(); i++)
      result = 31 * result + Integer.hashCode(elements[(head + i) % elements.length]);

    return result;
  }

  public Iterator<Integer> iterator() {
    changed = false;

    return new Iterator<Integer>() {
      int nextIndex = head;
      boolean hasNext = isFull();

      @Override
      public Integer next() {
        if (changed) throw new ConcurrentModificationException();
        if (!hasNext()) throw new NoSuchElementException();

        int res = elements[nextIndex];
        nextIndex = (nextIndex + 1) % elements.length;
        hasNext = false;
        return res;
      }

      @Override
      public boolean hasNext() {
        if (!hasNext) hasNext = nextIndex > -1 && nextIndex != tail;
        return hasNext;
      }
    };
  }
}
