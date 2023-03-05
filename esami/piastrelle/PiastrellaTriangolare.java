/**
 * Classe immutabile che rappresenta una piastrella triangolare 
 */
public class PiastrellaTriangolare extends AbstractPiastrella {

    private final int base, altezza;

    /**
     * AF: la base, l'altezza e il costo unitario, rappresentano la piastrella
     * RI: base > 0, altezza > 0
     */

    /**
     * Costruisce una piastrella triangolare, partendo dal suo costo unitario 
     * e dalla lunghezza della base e altezza 
     * 
     * @param altezza
     * @param base
     * @param lato
     * @throws IllegalArgumentException se la lunghezza della base o dell'altezza e' negativa
     */
    public PiastrellaTriangolare(final int costo, final int altezza, final int base) {
        super(costo);

        if (base <= 0 || altezza <= 0) throw new IllegalArgumentException("la base e l'altezza devono essere positivi");
        this.altezza = altezza;
        this.base = base;
    }

    @Override
    public int superficie() {
        return base*altezza;
    }
    
}

