import org.apache.commons.math3.fraction.Fraction;
import java.util.*;
public class Resultat{
    HashMap<String,Fraction> base;
    Fraction z;

    public void afficher() {
    for (String cle : base.keySet()) {
        Fraction fraction = base.get(cle);
        System.out.println(cle + " : " + fraction.getNumerator()+"/"+fraction.getDenominator());
    }
    System.out.println("z : " + z.toString());
}

}