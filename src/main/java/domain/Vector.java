package domain;

public class Vector implements VectorInterface {

    private int n; // tamaño máximo del vector
    private int data []; // arreglo de elementos tipo enteros
    private int counter; // cantidad de elementos agregados

    public Vector(int n) {
        this.n = n;
        this.data = new int[n];
        this.counter = 0;
    }

    public int[] getData() {
        return data;
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public void clear() {
        this.data = new int[n];
        this.counter = 0;
    }

    @Override
    public boolean isEmpty() {
        return counter == 0;
    }

    @Override
    public boolean contains(Object element) {
        int value = (int) element;
        for (int i = 0; i < counter; i++) {
            if (data[i] == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(Object element) {
        if (counter < this.data.length)
            this.data[counter++] = (int) element;
    }

    @Override
    public void add(int index, Object element) {
        if (index < data.length) {
            // Si el índice está dentro del contador actual, desplazar elementos para hacer espacio
            if (index < counter) {
                // Verificar si el vector ya está lleno
                if (counter >= n) return;

                // Desplazar elementos a la derecha
                for (int i = counter; i > index; i--) {
                    data[i] = data[i - 1];
                }
                counter++; // Incrementar contador ya que estamos agregando un elemento
            } else {
                // Si el índice está más allá del contador actual, ajustar el contador
                counter = index + 1;
            }

            // Agregar el elemento en el índice especificado
            data[index] = (int) element;
        }
    }

    @Override
    public boolean remove(Object element) {
        int value = (int) element;
        for (int i = 0; i < counter; i++) {
            if (data[i] == value) {
                // Encontrado el elemento, eliminarlo desplazando todos los elementos posteriores
                for (int j = i; j < counter - 1; j++) {
                    data[j] = data[j + 1];
                }
                data[counter - 1] = 0; // Limpiar la última posición
                counter--;
                return true;
            }
        }
        return false;
    }

    @Override
    public Object remove(int index) {
        if (index < 0 || index >= counter) {
            return null; // Índice inválido
        }

        int removedElement = data[index];

        // Desplazar elementos a la izquierda
        for (int i = index; i < counter - 1; i++) {
            data[i] = data[i + 1];
        }

        data[counter - 1] = 0; // Limpiar la última posición
        counter--;

        return removedElement;
    }

    @Override
    public void sort() {
        bubbleSort(this.data, counter);
    }

    @Override
    public int indexOf(Object element) {
        int value = (int) element;
        for (int i = 0; i < counter; i++) {
            if (data[i] == value) {
                return i;
            }
        }
        return -1; // Elemento no encontrado
    }

    @Override
    public Object get(int index) {
        if (index >= 0 && index < counter) {
            return data[index];
        }
        return null; // Índice inválido
    }

    @Override
    public void fill() {
        util.Utility.fill(data);
        counter = n;
    }

    // Implementación de Bubble Sort (ya proporcionada)
    static void bubbleSort(int arr[], int n) {
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Intercambiar arr[j] y arr[j+1]
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            // Si no se intercambiaron dos elementos en el bucle interno, entonces terminar
            if (swapped == false)
                break;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < counter; i++) {
            result.append(data[i]);
            if (i < counter - 1) {
                result.append(", ");
            }
        }
        return result.toString();
    }
}