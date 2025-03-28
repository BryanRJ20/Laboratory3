package util;

import java.text.DecimalFormat;
import java.util.Random;

public class Utility {

    private static final Random random;

    //constructor estático, inicializador estático
    static {
        // semilla para el random
        long seed = System.currentTimeMillis();
        random = new Random(seed);
    }
    public static int random(int bound){
      // return(int) Math.floor(Math.random()*bound); //Forma 1
        return 1+random.nextInt(bound);

    }

    public static void fill(int[] a) {

    for (int i = 0; i < a.length; i++){
        a[i] = random(99);

    }

    }

    public static String format(long n) {

        return new DecimalFormat("###,###,###.##").format(n); //Establecer un formato para n

    }

    public static String format(double n) {

        return new DecimalFormat("###,###,###.##").format(n); //Establecer un formato para n

    }

    public static String $format(double n) {

        return new DecimalFormat("$###,###,###.##").format(n); //Establecer un formato para n

    }

    public static int min(int x, int y) {

        return x<y ? x : y;

    }

    public static int max(int x, int y) {

        return x>y ? x : y;

    }

    public static String show(int[] a) {

        String result ="";
        for (int item : a){
            if (item == 0) break; //si es cero es porque no hay más elementos
            result+=item + " ";

        }//End for

     return result;
    }



}
