
import com.globokas.model.Racal;

/**
 *
 * @author pvasquez
 */
public class DecifrarTarjeta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        RacalService racalService = RacalService.getInstance();
//        Racal racal = racalService.getNumeroTrajeta(getRacal());
//        racalService.closeSixFactory();
//        System.out.println("Tarjeta Desencriptada="+racal.getEncryptCard());
        
    }

    public static Racal getRacal() {
        Racal racal = new Racal();
        racal.setTransDate("20170404");
        racal.setTrace("1096");
        racal.setTerminal("00030101");

        return racal;
    }
}
