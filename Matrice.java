import org.apache.commons.math3.fraction.Fraction;
import java.util.*;
public class Matrice{
    Fraction[][] content;

    public void afficher(){
        String space = null;
        for (int i = 0; i < content.length; i++) {
            for (int j = 0; j < content[0].length; j++) {
                space = "  ";
                if(content[i][j].getDenominator() == 1){
                    space= "      ";
                }
                System.out.print(content[i][j].toString() + space);
            }
            System.out.println();
        }
        System.out.println("-------------------------------------");
    }   
    public boolean possible(int type){
        int last=content.length-1;
        int m = content[0].length;
        Fraction zero=new Fraction(0.0);
        if(type == 1){
            for(int i=0;i<m;i++){
                if(content[last][i].compareTo(zero)==1){
                    return true;
                }
            }
            return false;
        }
        for(int i=0;i<m;i++){
            if(content[last][i].compareTo(zero)==-1){
                return true;
            }
        }
        return false;
    }
    public int getImaxZ(int type){
        Fraction max = new Fraction(0.0);
        int last=content.length-1;
        int m = content[0].length;
        int imax=0;
        if(type == 1){
            for(int i=0;i<m-1;i++){
                if(content[last][i].compareTo(max)==1){
                    imax=i;
                    max=new Fraction(content[last][i].getNumerator(),content[last][i].getDenominator());
                }
            }
            return imax;
        }
        for(int i=0;i<m-1;i++){
            if(content[last][i].compareTo(max)==-1){
                imax=i;
                max=new Fraction(content[last][i].getNumerator(),content[last][i].getDenominator());
            }
        }
        return imax;

    }
    public int getiPivot(int imax) throws Exception{
        Fraction min = new Fraction(1000000);
        int last=content.length-1;
        int m = content[0].length;
        Fraction zero = new Fraction(0.0);
        Fraction min_value=new Fraction(0.0);
        int ipivot=-1;
        for(int i=0;i<last;i++){

            if(content[i][imax].compareTo(zero)>0 && content[i][m-1].compareTo(zero)>0){
                min_value=content[i][m-1].divide(content[i][imax]);


                if(min.compareTo(min_value)==1){

                    min=min_value;
                    ipivot=i;
                }
            }
        }
        if(ipivot==-1){
            throw new Exception("Aucune solution");
        }
        return ipivot;
    }
    public Resultat Simplex(String[] base, String [] hors_base,int type) throws Exception{
        int len1 = base.length;
        int len2 = hors_base.length;
        String[] cles = new String[len1 + len2];

        System.arraycopy(base, 0, cles, 0, base.length);
        System.arraycopy(hors_base, 0, cles, base.length, hors_base.length);
        int m = content[0].length;
        Resultat resultat=new Resultat();
        Fraction alpha = new Fraction(0);
        Fraction beta = new Fraction(0);
        this.afficher();
        while(possible(type)){
            
            int imax = getImaxZ(type);
            int ipivot=getiPivot(imax);
            String clemax=cles[imax];
            cles[imax]=base[ipivot];
            base[ipivot]=clemax;
            
            //transformer la ligne pivot

            alpha =  content[ipivot][imax];
            for(int j=0;j<m;j++){
                content[ipivot][j]=content[ipivot][j].divide(alpha);
            }

            //pivot de Gauss
            
            for(int i=0;i<content.length;i++){
                if(i!=ipivot){
                    beta = content[i][imax].multiply(-1);
                    System.out.println("beta pour la ligne "+i+" est" + beta);
                    for(int j=0;j<m;j++){ 
                        content[i][j]=content[i][j].add(content[ipivot][j].multiply(beta));
                    }
                }
            }
        }
        
        resultat.base=new HashMap<String,Fraction>();
        for(int i=0;i<base.length;i++){
            resultat.base.put(base[i],content[i][m-1]);
        }
        this.afficher();
        resultat.z=content[content.length-1][m-1].multiply(-1);
        return resultat;
    }
}
