package com.anthony.Controller;

/**
 *
 * @author The Thøny
 */
public class Convertidor {

    public Convertidor() {
    }

    public Integer obtenerNumero(String cadena) {
        String ingreso = cadena;
        char[] cadena_div = ingreso.toCharArray();
        String respuesta = "";
        for (int i = 0; i < cadena_div.length; i++) {
            if (Character.isDigit(cadena_div[i])) {
                respuesta += cadena_div[i];
            }
        }
        return Integer.parseInt(respuesta);
    }

    public String obtenerPrimeraLetra(String str) {        
        char firstChar = str.charAt(0);
        String res = String.valueOf(firstChar).toUpperCase();        
        return res;
    }
    
    

}
