
package com.nicolasluna.todoList.excepciones;

public class MisExcepciones extends Exception {

    public MisExcepciones(String mjs) {
        super(mjs);
        
        // Lo que hacemos es crear un contructor vacio para que cada vez que se llame mande el mensaje al padre
        // Permite diferenciar los errores propios que tengamos de otros propios del sistema
        // Tiene como beneficio Especificidad, Mensajes Claros, Separación de Errores del Sistema
    }

    
    
}
