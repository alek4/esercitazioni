import java.util.ArrayList;
import java.util.List;

/**
 * Classe concreta che implementa un Rimorchiatore in grado di spostare un numero illimitato di navi cargo alla volta
 */
public class SuperRimorchiatore implements Rimorchiatore {

    @Override
    public int sposta(int numeroNavi, Molo from, Molo to) {
        final List<NaveCargo> navi = new ArrayList<>();
        for (int i = 0; i < numeroNavi; i++) {
            navi.add(from.salpa());
        }

        for (int i = navi.size() - 1; i >= 0; i--) {
            to.attracca(navi.get(i));
        }

        return 1;
    }
    
}
