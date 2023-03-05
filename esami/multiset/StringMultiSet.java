/**
 * Interfaccia che rappresenta un multiset contenente stringhe
 * 
 * Un multiset e' un insieme in cui, ciascun elemento puo' essere
 * contenuto piu' di una volta. Il numero di volte che un elemento
 * compare in un multiset e' detto molteplicita'.
 * 
 * Un esempio di multiset:
 *      {a, a, b} l’elemento a ha molteplicità 2, mentre b ha molteplicità 1;
 *
 * La cardinalità di un multiset è la somma delle molteplicità dei suoi elementi; mentre il supporto 
 * di un multiset è l’insieme (senza ripetizioni) dei suoi elementi.
 */
interface StringMultiSet extends Iterable<String> {

    /**
     * Aggiunge un elemento a questo multiset, restituendo la molteplicità di tale elemento dopo l’inserimento
     * @param s
     * @return la molteplicita' aggiornata dell'elemento inserito
     * @throws NullPointerException se s e' nullo
     */
    int add(String s);

    /**
     * Rimuove un elemento da questo multiset, restituendo la molteplicità di tale elemento prima della rimozione
     * 
     * @param s
     * @return la molteplicita' aggiornata dell'elemento rimosso
     * @throws NullPointerException se s e' nullo
     */
    int remove(String s);

    /**
     * Restituisce true se s e' contenuto in questo multiset
     * 
     * @param s
     * @return true se s e' contenuto, false se non lo e'
     * @throws NullPointerException se s e' nullo
     */
    default boolean contains(String s) {
        return multiplicity(s) > 0;
    }

    /**
     * Restituisce la molteplicità dell’elemento in questo multiset
     * 0 se l'elemento non appartiene a questo multiset
     * 
     * @param s
     * @return la molteplicita' dell'elemento
     * @throws NullPointerException se s e' nullo
     */
    int multiplicity(String s);

    /**
     * Restituisce la cardinalita' di questo multiset
     * 
     * @return la cardinalita' di questo multiset
     */
    default int size() {
        int size = 0;
        for (String s : this) {
            size += multiplicity(s);
        }

        return size;
    }

    /**
     * Restituisce il multiset ottenuto come unione di 
     * questo multiset con quello indicato come argomento
     * 
     * (non modifica questo multiset e neanche quello passato come argomento)
     * 
     * @param o l'altro multiset
     * @return il multiset risultato dall'unione di questo e o
     * @throws NullPointerException se o e' nullo
     */
    StringMultiSet union(StringMultiSet o);

    /**
     * restituire il multiset ottenuto come intersezione di
     * questo multiset con quello indicato come argomenti
     * 
     * (non modifica questo multiset e neanche quello passato come argomento)
     * 
     * @param o l'altro multiset
     * @return il multiset risultato dall'intersezione di questo e o
     * @throws NullPointerException se o e' nullo
     */
    StringMultiSet intersection(StringMultiSet o);
}