package com.example.tp2;

public interface Lista {
    void crear();
    void agregar(Object elemento);
    Object obtener(int indice);
    int longitud();
    boolean esVacia();
    void insertar(Object elemento, int indice);
    void eliminar(int indice);
}
