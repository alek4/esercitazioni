import java.util.Objects;

/**
 * Classe astratta che rappresenta una entry del filesystem con un nome
 */
public abstract class Entry {
    public final String name;

    /**
     * RI: name non deve essere nulla o vuota
     */

    /**
     * Costruisce una entry dato il nome
     * 
     * @param name
     * @throws NullPointerException se name e' nullo
     * @throws IllegalArgumentException se il nome e' vuoto
     */
    protected Entry(final String name) {
        Objects.requireNonNull(name);
        if (name.isEmpty()) throw new IllegalArgumentException("il nome non puo' essere nullo");
        this.name = name;
    }

    /**
     * Restituisce true se questa entry e' una directory
     * 
     * @return true se entry e' una directory
     */
    public abstract boolean isDir();

    /**
     * Restituisce la dimensione dell'entry
     * 
     * @return la dimensione
     */
    public abstract int size();
}