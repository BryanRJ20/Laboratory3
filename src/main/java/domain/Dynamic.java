package domain;

public class Dynamic {


    public long factorial(int n) {
        //Caso base
        if (n <= 1) return 1;

        //Array que guarda el resultado
        long[] dp = new long[n + 1];
        dp[0] = dp[1] = 1;
        //Llena el array
        for (int i = 2; i <= n; i++) {
            dp[i] = i * dp[i - 1];
        }//End for

        return dp[n];
    }//End factorial


    public long fibonacci(int n) {
        //Casos base
        if (n <= 0) return 0;
        if (n == 1) return 1;

        long[] dp = new long[n + 1];

        //Inicializa los casos base
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }//End for

        return dp[n];
    }//End fibonacci

    // Problema de cambio de moneda
    public int coinChange(int[] coins, int amount) {
        //Caso base
        if (amount == 0)
            return 0; //Si el monto ingresado es cero entonces retorna es 0


        //dp[i] representa el número mínimo de monedas necesarias para obtener la cantidad i
        int[] dp = new int[amount + 1];

        //Inicializar con un valor mayor que cualquier solución posible
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        //Caso base
        dp[0] = 0;

        //Llenamos la tabla
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                if (dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }//End coinChange
}