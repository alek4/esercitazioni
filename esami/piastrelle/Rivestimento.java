/**
 * Interfaccia che descrive un rivestimento, un'entita' che
 * ha come proprieta' una superficie e un costo
 */
public interface Rivestimento {

    /**
     * Restituisce la superficie del rivestimento
     * 
     * @return la superficie 
    */
    int superficie(); 

    /**
     * Restituisce il costo del rivestimento
     * 
     * @return il costo
     */
    int costo(); 
}