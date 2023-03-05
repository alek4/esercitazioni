/**
 * Classe astratta che rappresenta una piastrella
 */
public abstract class AbstractPiastrella implements Rivestimento{
    
    final private int costo;

    /**
     * RI: costo > 0
     */

    /**
     * Costruisce una piastrella astratta a partire dal suo costo unitario
     * 
     * @param costo
     * @throws IllegalArgumentException se il costo e' negativo
     */
    public AbstractPiastrella(final int costo) {
        if (costo <= 0) throw new IllegalArgumentException("il costo deve essere positivo");
        this.costo = costo;
    }

    @Override
    public int costo() {
        return costo;
    }
}
