/**
 * Classe concreta che rappresenta un robot in grado di spostare solo un pacco alla volta
 */
public class MicroRobot implements Robot {

    @Override
    public void move(int num, Scaffalatura from, Scaffalatura to) {
        for (int i = 0; i < num; i++) {
            Pacco pc = from.preleva();
            to.deposita(pc);
        }
    }
    
}
