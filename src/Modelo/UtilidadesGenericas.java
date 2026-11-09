package Modelo;

import java.util.ArrayList;

public class UtilidadesGenericas {

    public static <T> int contarElementos(ArrayList<T> lista) {
        return lista.size();
    }

    public static <T> void mostrarDato(T dato) {
        System.out.println(dato);
    }
}

