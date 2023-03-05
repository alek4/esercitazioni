import java.util.Objects;

/**
 * Classe che rappresenta un Pacco 
 */
public class Pacco {
   
    // prodotto
    final public String prodotto;

    // altezza del prodotto
    final public int altezza;

    /**
     * Costruisce un pacco partendo dal prodotto e dall'altezza
     * 
     * @param nome
     * @param altezza
     * @throws NullPointerException se nome e' nullo
     * @throws IllegalArgumentException se nome e' vuoto o se l'altezza e' negativa
     */
    public Pacco(final String prodotto, int altezza) {
        if (Objects.requireNonNull(prodotto, "Il nome non puo' essere nullo").isEmpty())
            throw new IllegalArgumentException("Il nome non puo' essere vuoto");

        this.prodotto= prodotto;

        if (altezza <= 0) 
            throw new IllegalArgumentException("L'altezza deve essere positiva");
        
        this.altezza = altezza;
    }

    @Override
    public String toString() {
        return String.format("%s[%d]", prodotto, altezza);
    }
}
