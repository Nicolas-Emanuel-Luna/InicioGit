
package com.nicolasluna.todoList.service;

import com.nicolasluna.todoList.excepciones.MisExcepciones;
import com.nicolasluna.todoList.model.Tarea;
import java.util.List;

public interface ITareaService {
    
    public void crearTarea(Tarea tareaNueva);
    public Tarea obtenerUnaTarea(Long id) throws MisExcepciones;
    public List<Tarea> obtenerListaTareas();
    public void modificarTarea(Long id, Tarea tareaNueva) throws MisExcepciones;
    public void eliminarUnaTarea(Long id);
    

}
