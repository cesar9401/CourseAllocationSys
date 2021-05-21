package com.cesar31.system.structures;

/**
 *
 * @author cesar31
 * @param <T>
 */
public class HashTable<T> {

    private Integer size;
    private Integer inserted;
    private final double FACTOR = 0.55;

    private Container<T>[] arrayHash;

    public HashTable() {
        this.size = 37;
        this.inserted = 0;
        this.arrayHash = new Container[this.size];
    }

    /**
     * Agregar elemento
     *
     * @param key
     * @param data
     */
    public void put(String key, T data) {
        int id = getInt(key);
        int index = hashFunction(id);

        // insertar
        if (arrayHash[index] == null) {
            System.out.println("Insertando " + data.toString() + " -> " + index);
            Container<T> tmp = new Container<>(key, data);
            arrayHash[index] = tmp;
            this.inserted++;
        } else {
            boolean insert = true;
            while (arrayHash[index] != null) {
                // System.out.println("no null -> " + index + " -> " + arrayHash[index].getKey());
                if (!key.equals(arrayHash[index].getKey())) {
                    arrayHash[index].setMarked(true);
                    index = hashFunction(collisionFunction(id, index));
                    // System.out.println(index);
                } else {
                    System.out.println("Cambiar " + data.toString() + " -> " + index);
                    arrayHash[index].setData(data);
                    insert = false;
                    break;
                }
            }

            if (insert) {
                System.out.println("Colision, insertar " + data.toString() + " -> " + index);
                Container<T> tmp = new Container<>(key, data);
                arrayHash[index] = tmp;
                this.inserted++;
            }
        }

        double f = (double) this.inserted / (double) this.size;
        // System.out.printf("Factor: %.5f\n", f);

        // Hacer rehashing
        if (f >= this.FACTOR) {
            // System.out.println("\n\n*************hacer rehashing*****************");
            rehash();
        }
    }

    /**
     * Obtener elemento
     *
     * @param key
     * @return
     */
    public T get(String key) {
        Container<T> aux = getContainer(key);
        if (aux != null) {
            if (!aux.isDeleted()) {
                return aux.getData();
            }
        }

        return null;
    }

    /**
     * Eliminar elemento
     *
     * @param key
     * @return
     */
    public T remove(String key) {
        Container<T> aux = getContainer(key);
        if (aux != null) {
            if (!aux.isDeleted()) {
                aux.setDeleted(true);
                return aux.getData();
            }
        }

        return null;
    }

    /**
     * Obtener contenedor para metodos get y remove
     *
     * @param key
     * @return
     */
    private Container<T> getContainer(String key) {
        int id = getInt(key);
        int index = hashFunction(id);

        Container<T> aux = arrayHash[index];
        while (aux != null) {
            if (aux.getKey().equals(key)) {
                return aux;
            }

            if (aux.isMarked()) {
                index = hashFunction(collisionFunction(id, index));
                aux = arrayHash[index];
            } else {
                return null;
            }
        }

        return null;
    }

    private void rehash() {
        this.inserted = 0;
        this.size = getNexPrime((int) (this.size * Math.sqrt(5)));
        System.out.println("******** Size -> " + size);

        Container<T>[] aux = this.arrayHash;// Guardar referencia
        this.arrayHash = new Container[this.size]; //inicializar nuevo array con tamaño nuevo

        for (Container<T> i : aux) {
            if (i != null) {
                if (!i.isDeleted()) {
                    put(i.getKey(), i.getData());
                }
            }
        }
    }

    private int getNexPrime(int n) {
        int count = 0;
        while (count != 2) {
            int divisor = 1;
            count = 0;
            n++;
            while (divisor <= n) {
                if (n % divisor == 0) {
                    count++;
                    if (count > 2) {
                        break;
                    }
                }
                divisor++;
            }
        }

        return n;
    }

    /**
     *
     * @param id -> carnet del estudiante
     * @return
     */
    private int hashFunction(int id) {
        return (id & 0x7fffffff) % this.size;
    }

    private int collisionFunction(int id, int index) {
        return (id % 7 + 1) + index;
    }

    private int getInt(String id) {
        return Integer.valueOf(id);
    }

    public Integer getSize() {
        return size;
    }

    /**
     * Obtener el numero de elementos insertados
     *
     * @return
     */
    public Integer getInserted() {
        return inserted;
    }

    public Container<T>[] getArrayHash() {
        return arrayHash;
    }
}
