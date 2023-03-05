import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Classe che rappresenta uno scalo navale
 */
public class ScaloNavale implements Iterable<Molo> {
    
    private final Molo[] moli;
    private final Rimorchiatore rimorchiatore;

    /**
     * Crea uno scalo navale partendo dalla lista di moli che possiede e dal suo rimorchiatore
     *
     * @param numMoli numero di moli che questo scalo navale possiede
     * @param moli la lista di moli
     * @param rimorchiatore
     * @throws IllegalArgumentException se il numero di moli e' diverso dalla lunghezza della lista di moli
     * @throws NullPointerException se la lista di moli e' nulla, contiene valori nulli o se rimorchiatore e' nullo
     */
    public ScaloNavale(int numMoli, final List<Molo> moli, final Rimorchiatore rimorchiatore) {
        Objects.requireNonNull(moli, "La lista di moli non puo' essere nulla");

        if (numMoli != moli.size())
            throw new IllegalArgumentException("Il numero di moli deve corrispodere alla lunghezza della lista di moli");

        Objects.requireNonNull(rimorchiatore, "Il rimorchiatore non puo' essere nullo");

        this.moli = moli.toArray(new Molo[0]);
        this.rimorchiatore = rimorchiatore;
    }


    /**
     * Sposta un certo numero di navi da un molo ad un altro
     * 
     * @param numNavi numero delle navi da spostare
     * @param fromIndex indice del molo di partenza
     * @param toIndex indice del molo di arrivo
     * @throws IndexOutOfBoundsException se gli indici sono out of bounds
     * @throws IllegalArgumentException se il numero delle navi non e' positivo
     */
    public void sposta(int numNavi, int fromIndex, int toIndex) {
        Objects.checkIndex(fromIndex, moli.length);
        Objects.checkIndex(toIndex, moli.length);
        
        if (numNavi <= 0) 
            throw new IllegalArgumentException("Il numero di navi da spostare deve essere positivo");

        Molo from = moli[fromIndex];
        Molo to = moli[toIndex];
        
        int moves = rimorchiatore.sposta(numNavi, from, to);
        System.out.println(moves);
    }

    @Override
    public Iterator<Molo> iterator() {
        return Collections.unmodifiableList(Arrays.asList(moli)).iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        for (int i = 0; i < moli.length; i++) {
            sb.append(i+":");
            sb.append(moli[i]);
            sb.append("\n");
        }
        
        return sb.toString();
    }
}
