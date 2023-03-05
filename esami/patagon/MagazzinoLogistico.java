import java.util.List;
import java.util.Objects;

/**
 * Classe che rappresenta un magazzino logistico
 */
public class MagazzinoLogistico {

    // elenco numerato di scaffalature
    private final Scaffalatura[] scaffalature;

    private final Robot robot;

    /**
     * Costruisce un magazzino partendo da una lista di scaffali, la quantita' di essi e un robot
     * 
     * @param scaffs la lista di scaffali
     * @param numScaffs la lunghezza della lista scaffs
     * @param rb il robot
     * @throws NullPointerException se scaffs o rb son nulli
     * @throws IllegalArgumentException se la lunghezza della lista e numScaffs non sono uguali
     */
    public MagazzinoLogistico(final List<Scaffalatura> scaffs, int numScaffs, final Robot rb ) {
    
        Objects.requireNonNull(scaffs);
        if (scaffs.size() != numScaffs)
            throw new IllegalArgumentException();

        scaffalature = scaffs.toArray(new Scaffalatura[0]);


        Objects.requireNonNull(rb);
        robot = rb;
    }

    /**
     * Sposta un certo numero di pacchi da uno scaffale all'altro
     * @param num il numero di pacchi da spostare
     * @param from lo scaffale di partenza
     * @param to lo scaffale d'arrivo
     * @throws IndexOutOfBoundsException se from o to non sono compresi negli indici di scaffalatu
     */
    public void move(int num, int from, int to) {
        Objects.checkIndex(from, scaffalature.length);
        Objects.checkIndex(to, scaffalature.length);
        robot.move(num, scaffalature[from], scaffalature[to]);
    }
}