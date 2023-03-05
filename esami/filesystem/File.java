/**
 * Classe concreta immutabile che rappresenta un file
 */
public class File extends Entry {
    private final int size;

    /**
     * Costruisce un file dato nome e dimensione
     * 
     * @param name
     * @param dim
     */
    protected File(String name, int dim) {
        super(name);
        if (dim <= 0) throw new IllegalArgumentException("la dimensione non puo' essere negativa");
        size = dim;
    }

    @Override
    public boolean isDir() {
        return false;
    }

    @Override
    public int size() {
        return size;
    }
    
    @Override
    public String toString() {
        return String.format("%s(%d)", name, size);
    }
}
