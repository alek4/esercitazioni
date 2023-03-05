import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Classe immutabile che rappresenta una pavimentazione
 */
public class Pavimentazione implements Rivestimento, Iterable<Pavimentazione.Componente> {

    /**
     * Classe immutabile che rappresenta una componente 
     * di una pavimentazione dato il suo tipo di rivestimento 
     * e la quantita' del rivestimento
     */
    public class Componente implements Rivestimento {

        public final Rivestimento rivestimento;

        public final int quantita;

        /**
         * AF: una componente e' rappresentata da tupla {rivestimento, quantita}
         * 
         * RI: rivestimento != null
         *     quantita > 0
         */

        /**
         * Costruisce una componente partendo dal tipo di rivestimento 
         * e la sua quantita'
         * 
         * @param rivestimento
         * @param quantita
         * @throws NullPointerException se il rivestimento e' nullo
         * @throws IllegalArgumentException se la quantita' e' negativa
         */
        public Componente(Rivestimento rivestimento, int quantita) {
            Objects.requireNonNull(rivestimento, "il rivestimento non puo' essere nullo");
            if (quantita <= 0) throw new IllegalArgumentException("la quantita' deve essere positiva");
            
            this.rivestimento = rivestimento;
            this.quantita = quantita;
        }

        @Override
        public int superficie() {
            return rivestimento.superficie() * quantita;
        }

        @Override
        public int costo() {
            return rivestimento.costo() * quantita;
        }
    }

    private final Collection<Componente> componenti;

    /**
     * Costruisce una pavimentazione data la collezione di componenti che la compone
     * 
     * @param componenti
     * @throws NullPointerException se componenti e' o contiene null
     * @throws IllegalArgumentException se la collezione e' vuota
     */
    public Pavimentazione(final Collection<Componente> componenti) {
        this.componenti = List.copyOf(componenti);
        if (this.componenti.isEmpty())
            throw new IllegalArgumentException("la collezione deve contenere almeno una componente");
    }

    @Override
    public int superficie() {
        int tot = 0;
        for (Componente componente : componenti) {
            tot += componente.superficie();
        }

        return tot;
    }

    @Override
    public int costo() {
        int tot = 0;
        for (Componente componente : componenti) {
            tot += componente.costo();
        }

        return tot;   
    }

    @Override
    public Iterator<Pavimentazione.Componente> iterator() {
        return componenti.iterator();
    }
    
}
