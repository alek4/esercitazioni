import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;


/**
 * Classe concreta che implementa l'interfaccia StringMultiSet,
 * facendo uso di una lista con ripetizioni
 */
public class ListMultiSet extends AbstractMultiSet {
    private final List<String> elems = new ArrayList<>();


    /**
     * Costruisce un multiset partendo da un array di stringhe
     * che costituiranno gli elementi del multiset
     *  
     * @param elems
     * @throws NullPointerException se elems e' nullo oppure se contiene elementi nulli
     */
    public ListMultiSet(final String[] elems) {
        this.elems.addAll(List.of(elems));
    }

    /**
     * Costruisce un multiset vuoto
     *  
     */
    public ListMultiSet() {
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {

            private String next = null;

            private final Iterator<String> it = elems.iterator();

            private final Set<String> set = new HashSet<>();

            @Override
            public boolean hasNext() {
                if (next != null) return true;
                while (it.hasNext()) {
                    next = it.next();
                    if (!set.contains(next)) {
                       set.add(next);
                       return true;
                    }
                }

                next = null;
                return false;
            }

            @Override
            public String next() {
                if (!hasNext()) throw new NoSuchElementException();
                final String ret = next;
                next = null;
                return ret;
            }
            
        };
    }

    @Override
    public int add(String s) {
        elems.add(Objects.requireNonNull(s, "L'elemento non puo' essere nullo"));
        return multiplicity(s);
    }

    @Override
    public int remove(String s) {
        if (contains(s)) {
            elems.remove(s);
            return multiplicity(s);
        }

        return 0;
    }

    @Override
    public int multiplicity(String s) {
        Objects.requireNonNull(s, "L'elemento non deve essere nullo");
        int sum = 0;
        for (String el : elems) {
            if (el.equals(s)) sum++;
        }
        return sum;

        // Collections.frequency(elems, s);
    }

    @Override
    public StringMultiSet union(StringMultiSet o) {
        ListMultiSet multiSet = new ListMultiSet();
        
        Objects.requireNonNull(o, "Il multiset non puo' essere null");

        for (String el : this) {
            if (multiplicity(el) > o.multiplicity(el)) multiSet.elems.addAll(Collections.nCopies(multiplicity(el), el));
        }

        for (String el : o) {
            if (multiplicity(el) < o.multiplicity(el)) multiSet.elems.addAll(Collections.nCopies(o.multiplicity(el), el));
        }

        return multiSet;
    }

    @Override
    public StringMultiSet intersection(StringMultiSet o) {
        ListMultiSet multiSet = new ListMultiSet();
        
        Objects.requireNonNull(o, "Il multiset non puo' essere null");

        for (String el : this) {
            if (multiplicity(el) < o.multiplicity(el)) multiSet.elems.addAll(Collections.nCopies(multiplicity(el), el));
        }

        for (String el : o) {
            if (multiplicity(el) > o.multiplicity(el)) multiSet.elems.addAll(Collections.nCopies(o.multiplicity(el), el));
        }

        return multiSet;
    }
    
}
