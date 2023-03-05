import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Objects;

public class Filesystem {
    private final Directory root = new Directory("ROOT");

    public Entry find(final Path path) throws FileNotFoundException {
        if (!Objects.requireNonNull(path, "il path non puo' essere nullo").isAbsolute()) {
            throw new IllegalArgumentException("il path deve essere assoluto");
        }

        Directory d = root;
        Entry e = d;
        for (final String p : path) {
            if (d == null)
                throw new FileNotFoundException("la parte prima di " + p + " non e' una directory");
            
            e = d.find(p);
            if (e == null) throw new FileNotFoundException("la entry " + e + " non e' stata trovata");
            d = e.isDir() ? (Directory) e : null;
        }

        return e;
    }

    public Directory findDir(final Path path) throws FileNotFoundException {
        if (!Objects.requireNonNull(path, "il path non puo' essere nullo").isAbsolute()) {
            throw new IllegalArgumentException("il path deve essere assoluto");
        }

        final Entry e = find(path);
        if (!e.isDir()) throw new FileNotFoundException("non e' una directory");
        return (Directory) e;
    }

    public Iterable<Entry> ls(final Path path) throws FileNotFoundException {
        if (Objects.requireNonNull(path, "il path non puo' essere nullo").isAbsolute())
            throw new IllegalArgumentException("il path deve essere assoluto");
        
        return findDir(path);
    }

    public int size(final Path path) throws FileNotFoundException {
        if (Objects.requireNonNull(path, "il path non puo' essere nullo").isAbsolute())
            throw new IllegalArgumentException("il path deve essere assoluto");
 
        return find(path).size();
    }

    public void mkdir(final Path path) throws FileAlreadyExistsException, FileNotFoundException {
        if (Objects.requireNonNull(path, "il path non puo' essere nullo").isAbsolute())
            throw new IllegalArgumentException("il path deve essere assoluto");

        final String name = path.name();
        if (name == null) throw new IllegalArgumentException("il nome della directory non puo' essere vuoto");
        
        findDir(path.parent()).add(new Directory(name));
    }

    public void makefile(final Path path, int size) throws FileAlreadyExistsException, FileNotFoundException {
        if (Objects.requireNonNull(path, "il path non puo' essere nullo").isAbsolute())
            throw new IllegalArgumentException("il path deve essere assoluto");

        final String name = path.name();
        if (name == null) throw new IllegalArgumentException("il nome del file non puo' essere vuoto");
        
        findDir(path.parent()).add(new File(name, size));
    }
}
