/**
 * Classe immutabile che rappresenta una piastrella quadrata
 */
public class PiastrellaQuadrata extends AbstractPiastrella {

    private final int lato;

    /**
     * AF: il lato e il costo unitario, rappresentano la piastrella
     * RI: lato > 0
     */

    /**
     * Costruisce una piastrella quadrata, partendo dal suo costo unitario 
     * e dalla lunghezza di un lato
     * 
     * @param costo
     * @param lato
     * @throws IllegalArgumentException se la lunghezza del lato e' negativa
     */
    public PiastrellaQuadrata(final int costo, final int lato) {
        super(costo);

        if (lato <= 0) throw new IllegalArgumentException("la lunghezza del lato deve essere positiva");
        this.lato = lato;
    }

    @Override
    public int superficie() {
        return lato*lato;
    }
    
}
