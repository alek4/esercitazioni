import java.util.Objects;

/**
 * Record immutabile che rappresenta una nave cargo
 */
public record NaveCargo(String nome, int peso) {

    /**
     * AF: naveCargo = {nome, peso}
     * 
     * RI: nome non nullo o vuoto
     *     peso non negativo
     */

    /**
     * Costruisce una nave cargo partendo dal suo nome e dal suo peso
     * 
     * @param nome
     * @param peso
     * @throws IllegalArgumentException se il peso e' negativo oppure se il nome e' vuoto
     * @throws NullPointerException se il nome e' nullo
     */
    public NaveCargo {
        if (Objects.requireNonNull(nome, "Il nome non puo' essere nullo").isEmpty())
            throw new IllegalArgumentException("Il nome non puo' essere vuoto");

        if (peso <= 0)
            throw new IllegalArgumentException("Il peso deve essere positivo");
    } 

    @Override
    public String toString() {
        return String.format("%s[%d]", nome, peso);
    }
}