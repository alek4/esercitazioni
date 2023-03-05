import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * Classe che rappresenta una scaffalatura in cui i pacchi verranno depositati in cima 
 * e prelevati dal fondo 
 */
public class Scaffalatura implements Iterable<Pacco> {
    
    // coda che contiene i pacchi 
    private final Queue<Pacco> coda = new LinkedList<>();

    // public Scaffalatura() {}
    
    /**
     * Costruisce una scaffalatura partendo da una lista di pacchi
     * 
     * @param pacchi
     * @throws NullPointerException se la lista di pacchi e' oppure contiene valori nulli
     */
    public Scaffalatura(final List<Pacco> pacchi) {
        
        Objects.requireNonNull(pacchi, "La lista di prodotti non puo' essere nulla");
        for (Pacco pr : pacchi) {
            Objects.requireNonNull(pr, "La lista non puo' contenere elementi nulli");
            coda.add(pr);    
        }
    }

    /**
     * Aggiunge il pacco in cima a questo scaffale
     * 
     * @param pc il pacco
     * @throws NullPointerException se il pacco e' nullo
     */
    public void deposita(final Pacco pc) {
        coda.add(Objects.requireNonNull(pc, "Il pacco non puo' essere nullo"));
    }

    /**
     * Rimuove e restituisce il pacco in fondo a questa scaffalatura
     * 
     * @return il pacco
     */
    public Pacco preleva() {
        return coda.remove();
    }

    @Override
    public Iterator<Pacco> iterator() {
        return Collections.unmodifiableCollection(coda).iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Pacco pr : this) {
            sb.append(" ");
            sb.append(pr.toString()); 
            sb.append(",");
        }

        sb.append(" ]");
        return sb.toString();
    }

    // static Scaffalatura scaffalaturaFromString(final String row) {

    //     Objects.requireNonNull(row, "La stringa non puo' essere nulla");
    //     String[] data = row.split(" ");

    //     Scaffalatura sc = new Scaffalatura(); 
    //     for (int i = 0; i < data.length; i = i + 2){
    //        String nome = data[i];
    //        int altezza = Integer.parseInt(data[i+1]);

    //        Prodotto pr = new Prodotto(nome, altezza);
    //        sc.coda.add(pr);
    //     }

    //     return sc;
    // }

    
}
