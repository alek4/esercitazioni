/**
 * Classe concreta che implementa un Rimorchiatore in grado di spostare solo una nave alla volta
 */
public class MiniRimorchiatore implements Rimorchiatore {

    @Override
    public int sposta(int numeroNavi, Molo from, Molo to) {
        for (int i = 0; i < numeroNavi; i++) {
            NaveCargo n = from.salpa();
            to.attracca(n);
        }

        return numeroNavi;
    }

    
}