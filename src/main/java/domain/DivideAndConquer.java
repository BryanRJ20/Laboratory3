package domain;

public class DivideAndConquer {

    // Búsqueda binaria (iterativa)
    public static int binarySearch(int[] sortedArray, int value) {
        int left = 0;
        int right = sortedArray.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Verificar si el valor está en la posición media
            if (sortedArray[mid] == value)
                return mid;

            // Si el valor es mayor, ignorar la mitad izquierda
            if (sortedArray[mid] < value)
                left = mid + 1;

                // Si el valor es menor, ignorar la mitad derecha
            else
                right = mid - 1;
        }

        // Elemento no encontrado
        return -1;
    }

    // Búsqueda binaria (recursiva)
    public static int binarySearch(int[] sortedArray, int value, int low, int high) {
        // Caso base: elemento no encontrado
        if (low > high)
            return -1;

        int mid = low + (high - low) / 2;

        // Si el elemento está presente en el medio
        if (sortedArray[mid] == value)
            return mid;

        // Si el elemento es menor que el valor del medio, sólo puede estar en el subarreglo izquierdo
        if (sortedArray[mid] > value)
            return binarySearch(sortedArray, value, low, mid - 1);

        // De lo contrario, el elemento sólo puede estar en el subarreglo derecho
        return binarySearch(sortedArray, value, mid + 1, high);
    }

    // Encontrar mínimo y máximo en un arreglo
    public static ArrayMinMax findMinMax(int[] a, int low, int high) {
        // Caso base: un elemento
        if (low == high) {
            return new ArrayMinMax(a[low], a[high]);
        }

        // Caso base: dos elementos
        if (high == low + 1) {
            return new ArrayMinMax(
                    Math.min(a[low], a[high]),
                    Math.max(a[low], a[high])
            );
        }

        // Caso recursivo: dividir el arreglo y encontrar min/max en los subarreglos
        int mid = low + (high - low) / 2;

        ArrayMinMax leftMinMax = findMinMax(a, low, mid);
        ArrayMinMax rightMinMax = findMinMax(a, mid + 1, high);

        // Combinar resultados
        return new ArrayMinMax(
                Math.min(leftMinMax.getMin(), rightMinMax.getMin()),
                Math.max(leftMinMax.getMax(), rightMinMax.getMax())
        );
    }
}