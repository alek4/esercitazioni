import java.util.ArrayList;
import java.util.List;

public class Soluzione {
    
    public static void main(String[] args) {
        
        final String row = "MickeyMouse 4 Woody 6 BuzzLightyear 12 Baloo 21 LiloPelekai 5 Simba 9 Mowgli 5";
        String[] data = row.split(" ");

        List<Pacco> prodotti = new ArrayList<>();
        for (int i = 0; i < data.length; i = i + 2){
           String nome = data[i];
           int altezza = Integer.parseInt(data[i+1]);

           Pacco pr = new Pacco(nome, altezza);
           prodotti.add(pr);
        }
        Scaffalatura sc = new Scaffalatura(prodotti);

        System.out.println(sc.toString());

        Pacco pc = sc.preleva();
        System.out.println(pc);
        System.out.println(sc.toString());
        sc.deposita(pc);
        
        System.out.println(sc.toString());
    }
}
