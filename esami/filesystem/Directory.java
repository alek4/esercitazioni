import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Classe mutabile che rappresenta una directory
 */
public class Directory extends Entry implements Iterable<Entry> {

    private final List<Entry> entries = new ArrayList<>();

    /**
     * RI: entries non deve essere null e le entry contenute 
     *     non devono essere nulle
    */

    /**
     * Costruisce una directory vuota dato il nome
     * 
     * @param name
     */
    protected Directory(String name) {
        super(name);
    }

    /**
     * Trova la entry di nome name se e' presente in questa directory
     * 
     * @param name
     * @return la entry se e' presente oppure null
     * @throws NullPointerException se name e' nullo
     */
    public Entry find(final String name) {
        Objects.requireNonNull(name, "il nome non puo' essere nullo");

        for (Entry entry : entries) {
            if (name.equals(entry.name)) return entry;
        }

        return null;
    }

    /**
     * Aggiunge una nuova entry a questa directory
     * 
     * @param entry
     * @throws FileAlreadyExistsException se la entry e' gia' presente nella directory
     * @throws NullPointerException se entry e' null
     */
    public void add(Entry entry) throws FileAlreadyExistsException {
        Objects.requireNonNull(entry);
        if (find(entry.name) != null) throw new FileAlreadyExistsException("la entry e' gia' presente nella directory");

        entries.add(entry);
    }

    @Override
    public boolean isDir() {
        return true;
    }

    @Override
    public int size() {
        int size = 0;
        for (Entry e : entries) {
            size += e.size();
        }
        return size;
    }
    
    @Override
    public String toString() {
        return String.format("%s*", name);
    }

    @Override
    public Iterator<Entry> iterator() {
        return Collections.unmodifiableList(entries).iterator();
    }
}
