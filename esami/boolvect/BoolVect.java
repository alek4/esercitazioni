/**
 * BoolVect
 */
public interface BoolVect {

    int dimensione();

    int taglia();

    boolean leggi(final int i) throws IndexOutOfBoundsException;

    boolean scrivi(final int i, final boolean val) throws IndexOutOfBoundsException;

    void and(final BoolVect other);
    void or(final BoolVect other);
    void xor(final BoolVect other);
}