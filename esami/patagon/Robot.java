/**
 * Interfaccia che rappresenta un robot
 */
public interface Robot {
    
    /**
     * Sposta un certo numero di pacchi da una scaffalatura ad un'altra
     * 
     * @param num numero di pacchi da spostare
     * @param from la scaffalatura di partenza
     * @param to la scaffalatura d'arrivo
     * @throws NullPointerException se from o to sono null
     * 
     */
    public void move(int num, Scaffalatura from, Scaffalatura to);
}
