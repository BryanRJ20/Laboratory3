package domain;

import java.math.BigInteger;
import java.util.Random;

public class Probabilistic {

    private static final Random random = new Random();

    // Prueba de primalidad de Miller-Rabin
    public boolean isProbablePrime(int number, int certainty) {
        if (number <= 1) return false;
        if (number <= 3) return true;
        if (number % 2 == 0) return false; // Números pares (excepto 2) no son primos

        // Usar el método isProbablePrime de BigInteger que implementa Miller-Rabin
        BigInteger bigInteger = BigInteger.valueOf(number);
        return bigInteger.isProbablePrime(certainty);
    }

    // Paradoja del cumpleaños - probabilidad de que al menos 2 personas compartan cumpleaños
    public double birthdayParadox(int n) {
        if (n <= 0) return 0;
        if (n > 365) return 1.0; // Si hay más de 365 personas, la probabilidad es 100%

        double probability = 1.0;
        for (int i = 1; i < n; i++) {
            probability *= (366 - i) / 365.0;
        }

        // Devolver la probabilidad de que al menos 2 personas compartan cumpleaños
        return 1.0 - probability;
    }

    // Búsqueda aleatoria en un arreglo
    public int[] randomSearch(int[] a, int value, int attempts) {
        int index = -1;
        int count = 0;

        if (a == null || a.length == 0) {
            return new int[]{index, count};
        }

        // Usar un arreglo booleano para llevar registro de qué índices ya hemos comprobado
        boolean[] checked = new boolean[a.length];
        int uncheckedIndices = a.length;

        while (count < attempts && uncheckedIndices > 0) {
            // Generar un índice aleatorio
            int randomIndex = random.nextInt(a.length);

            // Si no hemos revisado este índice antes
            if (!checked[randomIndex]) {
                checked[randomIndex] = true;
                uncheckedIndices--;
                count++;

                // Comprobar si este es el valor que estamos buscando
                if (a[randomIndex] == value) {
                    index = randomIndex;
                    break;
                }
            }
        }

        return new int[]{index, count};
    }
}