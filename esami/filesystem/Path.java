import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Classe immutabile che rappresenta un percorso 
 */
public class Path implements Iterable<String> {
    private static final String SEPARATOR = ":"; 

    private static final Path ROOT = new Path(true, Collections.emptyList());

    private static final Path EMPTY = new Path(false, Collections.emptyList());

    private final boolean isAbsolute;
    private final List<String> parts;

    /**
     * AF: alcuni esempi di path possono essere:
     *     - ":"
     *     - ""
     *     - ":A:B:C:D"
     *     - "A:B:C:D"
     * 
     * RI: parts non deve essere null e non deve contenere stringhe vuote o nulle
     */

    /**
     * Costruisce un path a partire da un elenco di stringhe e
     * dall'informazione se e' assoluto oppure no
     * 
     * @param isAbsolute indica se il path e' assoluto
     * @param parts la lista di stringhe che costituiscono le componenti del path
     * @throws InvalidPathException se una delle componenti e' una stringa nulla o se e' uguale al separatore
     * @throws NullPointerException se parts e' o contiene null
     */
    private Path(final boolean isAbsolute, final List<String> parts) {
        this.isAbsolute = isAbsolute;
        this.parts = List.copyOf(parts);
        for (String part : parts) {
            if (part.isEmpty()) throw new InvalidPathException(part, "La componente e' vuota");
            if (part.indexOf(SEPARATOR) != -1)
                throw new InvalidPathException("la componente contiene il separatore", null);
        }
    }

    /**
     * Metodo di fabbricazione per creare un path da una stringa
     * 
     * @param path
     * @return il path corrispondente alla stringa
     */
    public static Path fromString(final String path) {
        Objects.requireNonNull(path);
        if (path.isEmpty()) return EMPTY;
        final String[] parts = path.split(SEPARATOR);
        if (parts.length == 0) return ROOT;
        if (parts[0].isEmpty()) return new Path(true, Arrays.asList(parts).subList(1, parts.length));
        return new Path(false, Arrays.asList(parts));
    }

    public boolean isAbsolute() {
        return isAbsolute;
    }

    /**
     * Restituisce il prefisso di questo path senza l'ultima componente,
     * se vuoto, restituisce questo
     * 
     * @return un path senza l'ultima componente
     */
    public Path parent() {
        if(parts.isEmpty()) return this;
        return new Path(isAbsolute, parts.subList(0, parts.size() - 1));
    }

    /**
     * Restituisce l'ultima componente di questo path, 
     * null se non ci sono componenti
     * 
     * @return l'ultima componente di questo path oppure null se il path e' vuoto
     */
    public String name() {
        if (parts.isEmpty()) return null;
        return parts.get(parts.size()-1);
    }

    /**
     * Risolve il path dato rispetto a questo
     * 
     * @param other il percorso da risolvere
     * @return il percorso risolto
     */
    public Path resolve(final Path other) {
        if (Objects.requireNonNull(other, "il path da risolvere non puo' essere nullo").isAbsolute)
            return other;

        final List<String> parts = new ArrayList<>(this.parts);
        parts.addAll(other.parts);
        return new Path(isAbsolute, parts);
    }
    
    /**
     * Costruisce un path relativo rispetto a questo e a quello dato
     * 
     * @param other l'altro path
     * @return il percorso relativizzato 
     * @throws IllegalArgumentException se questo path non e' assoluto, ma l'argomento lo e',
     *         o se l'elenco di parti di quest questo path non è (come lista) prefisso di quelle
     *         dell'argomento.
     */
    public Path relativize(final Path other) {
        Objects.requireNonNull(other, "il path da relativizzare non puo' essere nullo");
        if (!isAbsolute() && other.isAbsolute)
            throw new IllegalArgumentException("non si puo' relativizzare un path assoluto rispetto ad un relativo");

        if (!parts.equals(other.parts.subList(0, parts.size())))
            throw new IllegalArgumentException("il path non ha prefisso in comune con questo path");
        
        return new Path(false, other.parts.subList(parts.size(), other.parts.size()));
    }

    @Override
    public String toString() {
        return (isAbsolute ? SEPARATOR : "") + String.join(SEPARATOR, parts);
    }

    @Override
    public Iterator<String> iterator() {
        return Collections.unmodifiableList(parts).iterator();
    }
}
