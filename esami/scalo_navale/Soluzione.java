import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Soluzione {
    
    public static void main(String[] args) {
        List<Molo> moliList = new ArrayList<>();
        int n;
        
        try (final Scanner s = new Scanner(System.in)) {
            String nStr = s.nextLine();
            n = Integer.parseInt(nStr);

            while (s.hasNextLine()) {
                final String line = s.nextLine();

                String[] lineSplit = line.split(" ");
                List<NaveCargo> naviList = new ArrayList<>();
                for (int i = 0; i < lineSplit.length; i = i + 2) {
                    String nome = lineSplit[i];
                    int peso = Integer.parseInt(lineSplit[i+1]);
                    NaveCargo nv = new NaveCargo(nome, peso);
                    naviList.add(nv);
                }
                Molo molo = new Molo(naviList);
                moliList.add(molo);
            }
        }

        Rimorchiatore rm = new SuperRimorchiatore();
        ScaloNavale sn = new ScaloNavale(n, moliList, rm);
        System.out.println(sn);
        int numNavi = Integer.parseInt(args[0]); 
        int from = Integer.parseInt(args[1]); 
        int to = Integer.parseInt(args[2]); 
        sn.sposta(numNavi, from, to);
        System.out.println(sn);
    }
}
