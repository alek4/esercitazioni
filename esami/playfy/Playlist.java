import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

public class Playlist implements Iterable<Album.Brano> {
    
    public final String titolo;
    private final List<Album.Brano> elenco = new ArrayList<>();
    public Durata durata;
    
    public Playlist(final String titolo) {
        if (Objects.requireNonNull(titolo, "Il titolo non puo' essere nullo").isEmpty()) {
            throw new IllegalArgumentException("Il titolo non puo' essere vuoto");
        }

        this.titolo = titolo;
        this.durata = new Durata(0);
    }

    public void accoda(final Album.Brano brano) {
        elenco.add(Objects.requireNonNull(brano, "Il brano non puo' essere null"));
        durata.add(brano.durata);
    }

    public void rimuovi(final Album.Brano brano) {
        elenco.remove(Objects.requireNonNull(brano, "Il brano non puo' essere null"));
        durata.remove(brano.durata);
    }


    public int posizione(final Album.Brano brano) {
        return 1 + elenco.indexOf(Objects.requireNonNull(brano, "Il brano non deve essere nullo"));
    }

    public Playlist fondi(final String titolo, final Playlist playlist) {
        Playlist pl = new Playlist(titolo);

        for (Album.Brano brano : elenco) {
            pl.accoda(brano);
        }
        for (Album.Brano brano : Objects.requireNonNull(playlist, "La playlist non puo' essere nulla")) {
            if (pl.posizione(brano) != 0) pl.accoda(brano);            
        }

        return pl;
    }

    @Override
    public Iterator<Album.Brano> iterator() {
        return Collections.unmodifiableList(elenco).iterator();
    }

    public Iterator<Album.Brano> brani(final Album album) {
        Objects.requireNonNull(album, "L'album non puo' essere null");

        return new Iterator<Album.Brano>() {
            private final Iterator<Album.Brano> it = iterator();

            private Album.Brano next = null;

            @Override
            public boolean hasNext() {
                if (next != null) return true;
                while (it.hasNext()) {
                    next = it.next();
                    if (next.appartiene(album)) return true;
                }

                next = null;
                return false;
            }

            @Override
            public Album.Brano next() {
                if (!hasNext()) throw new NoSuchElementException();
                final Album.Brano ret = next;
                next = null;
                return ret;
            }
        };
    }

    public Iterator<Album> album() {
        return new Iterator<Album>() {

            private final Set<Album> albums = new HashSet<>();
            private Album next = null;

            private final Iterator<Album.Brano> it = iterator();

            @Override
            public boolean hasNext() {
                if (next != null) return true;
                while (it.hasNext()) {
                    next = it.next().album();
                    if (!albums.contains(next)) {
                        albums.add(next);
                        return true;
                    }
                }

                next = null;
                return false;
            }

            @Override
            public Album next() {
                if (!hasNext()) throw new NoSuchElementException();
                final Album ret = next;
                next = null;
                return ret;
            }
            
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Nome playlist: ");
        sb.append(titolo + "\n");

        for (int i = 0; i < elenco.size(); i++) {
            sb.append(String.format("%d - %s", i+1, elenco.get(i).asString(true)));
        }

        sb.append("Durata totale: " + durata.toString());

        return sb.toString();
    }
}
