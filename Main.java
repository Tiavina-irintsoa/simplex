import org.apache.commons.math3.fraction.Fraction;
public class Main{
    public static void main(String[] args) throws Exception{
       
        Fraction[][] A = new Fraction[][]{
            { new Fraction(-3) , new Fraction(2) , new Fraction(1) , new Fraction(0) , new Fraction(0) , new Fraction(2)  },
            { new Fraction(-1) , new Fraction(2) , new Fraction(0) , new Fraction(1) , new Fraction(0) , new Fraction(4)  },
            { new Fraction(1) , new Fraction(1) , new Fraction(0) , new Fraction(0) , new Fraction(1) , new Fraction(5) },
            { new Fraction(-1) , new Fraction(-2) , new Fraction(0) , new Fraction(0) , new Fraction(0) , new Fraction(0) },
        };

        Matrice a =new Matrice();
        a.content=A;
        String[] base={"s1", "s2","s3" };
        String[] hors_base={"X1","X2"};

        a.Simplex(base,hors_base,1).afficher();
    };
}   