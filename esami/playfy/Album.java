import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Album
 */
public class Album implements Iterable<Album.Brano> {

    /**
     * Classe immutabile che rappresenta un brano musicale
     */
    public class Brano {
    
        public final String titolo;
        public final Durata durata;

        /**
         * AF: "titolo" (durata), "album a cui appartiene"
         * 
         * RI: titolo non e' nullo e vuoto
         *     durata non nulla
         */

        /**
         * Costruisce un brano partendo dalla sua durata e dal suo titolo
         * 
         * @param titolo
         * @param durata
         * @throws NullPointerException se titolo o durata sono nullo
         * @throws IllegalArgumentException se il titolo e' vuoto
         */
        private Brano(final String titolo, final Durata durata) {
            if (Objects.requireNonNull(titolo).isEmpty()) {
                throw new IllegalArgumentException("Il titolo non puo' essere vuoto");
            }

            Objects.requireNonNull(durata);

            this.titolo = titolo;
            this.durata = durata;
        }

        public boolean appartiene(final Album album) {
            Objects.requireNonNull(album, "L'album non puo' essere nullo");

            return Album.this.equals(album);
        }

        /**
         * Restituisce l'album a cui appartiene questo brano
         * 
         * @return l'album
         */
        public Album album() {
            return Album.this;
        }

        /**
         * Restituisce una rappresentazione di questo brano come stringa
         * 
         * @param daAlbum
         * @return
         */
        public String asString(final boolean daAlbum) {
            return String.format("%s (%s)%s", titolo, durata.toString(), daAlbum ? ", (da \"" + album().titolo + "\")" : "");
        }

        @Override
        public String toString() {
            return asString(false);
        }
    }

    public final String titolo;
    private final List<Brano> elenco = new ArrayList<>();
    public final Durata durata;

    public Album(final String titolo, final List<String> titoli, final List<Durata> durate) {
        if (Objects.requireNonNull(titolo, "Il titolo non puo' essere nullo").isEmpty()) {
            throw new IllegalArgumentException("Il titolo non puo' essere vuoto");
        }

        this.titolo = titolo;

        Objects.requireNonNull(titoli, "L' elenco dei titoli non puo' essere null");
        Objects.requireNonNull(durate, "L'elenco delle durate non puo' essere nullo");

        if (titoli.size() != durate.size()) {
            throw new IllegalArgumentException("Le lunghezze di titoli e durate devono essere uguali");
        }

        if (titoli.size() == 0) {
            throw new IllegalArgumentException("Il numero di brani deve essere maggiore di 0");
        }

        Durata durata = new Durata(0);
        for (int i = 0; i < titoli.size(); i++) {
            Durata di = durate.get(i);
            try {
                elenco.add(new Brano(titoli.get(i), di));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Le specifiche del brano " + (i+1) + " sono errate" + e.getMessage());
            } 
            durata = durata.add(di);
        }

        this.durata = durata;
    }

    public Brano trova(final String titolo) {
        Objects.requireNonNull(titolo, "Il titolo non puo' essere nullo");

        if (titolo.isEmpty()) return null;

        for (Brano brano : elenco) {
            if (brano.titolo.equals(titolo)) {
                return brano;
            }
        }

        return null;
    }

    public Brano trova(final int pos) {
        try {
            return elenco.get(pos-1);
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("La posizione del brano non e' compresa tra 1 e " + elenco.size());
        }
    }

    public int posizione(final Brano brano) {
        Objects.requireNonNull(brano, "Il brano non deve essere nullo");

        return elenco.indexOf(brano) + 1;
    }

    @Override
    public Iterator<Album.Brano> iterator() {
        return Collections.unmodifiableCollection(elenco).iterator();
    }
}