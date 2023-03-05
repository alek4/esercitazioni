import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Classe che rappresenta un molo
 */
public class Molo implements Iterable<NaveCargo> {
    
    // fila di navi cargo
    private final List<NaveCargo> stackNavi;

    /**
     * AF: molo = 
     * 
     * RI: stackNavi non nullo e non contiene valori nulli
     */

    /**
     * Costruisce un molo vuoto
     */
    public Molo() {
        stackNavi = new LinkedList<>();
    }

    /**
     * Costruisce un molo partendo da una lista di navi
     *
     * @param navi
     * @throws IllegalArgumentException se la lista contiene valori nulli
     * @throws NullPointerException se la lista delle navi e' nulla
     */
    public Molo(final List<NaveCargo> navi) {
        Objects.requireNonNull(navi, "La lista di navi non puo' essere nulla");

        stackNavi = new LinkedList<>();
        for (NaveCargo nave : navi) {
            Objects.requireNonNull(nave, "La lista non puo' contenere valori nulli");

            stackNavi.add(0, nave);
        }
    }

    /**
     * Restituisce e rimuove l'ultima nave attraccata
     * 
     * @return la nave
     */
    public NaveCargo salpa() {
        return stackNavi.remove(0);
    }

    /**
     * Aggiunge la nave che deve attraccare a questo molo
     *
     * @param nv la nave
     */
    public void attracca(final NaveCargo nv) {
        Objects.requireNonNull(nv, "La nave non puo' essere nulla");
        stackNavi.add(0, nv);
    }


    /**
     * Restituisce il peso del primo cargo che salpera' 
     * 
     * @return il pese del primo cargo
     */
    public int pesoPrimoCargo() {
        return stackNavi.get(0).peso();
    }

    @Override
    public Iterator<NaveCargo> iterator() {
        return Collections.unmodifiableList(stackNavi).iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("< ");
        final Iterator<NaveCargo> ie = iterator();
        while (ie.hasNext()) {
            final NaveCargo nv = ie.next();
            sb.append(nv);
            if (ie.hasNext()) sb.append(", ");
        }
        sb.append(" #");

        return sb.toString();
    }
}
