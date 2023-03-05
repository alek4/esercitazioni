/**
 * Interfaccia che rappresenta un generico rimorchiatore
 */
public interface Rimorchiatore {

    /**
     * Sposta un certo numero di navi da un molo a un altro
     * e restituisce il numero di viaggi che il questo rimorchiatore ha compiuto
     * per effettuare lo spostamento
     * 
     * @param numeroNavi
     * @param from il molo di partenza
     * @param to il molo d'arrivo
     * @throws NullPointerException se from o to sono nulli
     * @throws IllegalArgumentException se numeroNavi e' negativo
     * @return il numero di viaggi che ha compiuto
     */
    public int sposta(int numeroNavi, Molo from, Molo to);
}
