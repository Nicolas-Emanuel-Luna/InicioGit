
package com.nicolasluna.todoList.controller;

import com.nicolasluna.todoList.excepciones.MisExcepciones;
import com.nicolasluna.todoList.model.Tarea;
import com.nicolasluna.todoList.service.ITareaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarea")
public class TareaController {
    
    @Autowired
    private ITareaService itareaSer;

    @PostMapping("/crear")
    public void crearTarea(@RequestBody Tarea tareaNueva){
        itareaSer.crearTarea(tareaNueva);
    }
    
    @GetMapping("/obtener/{id}")
    @ResponseBody
    public ResponseEntity<?> obtenerTarea(@PathVariable Long id){
        try {
            Tarea tarea = itareaSer.obtenerUnaTarea(id);
            return new ResponseEntity<>(tarea,HttpStatus.OK);
        } catch (MisExcepciones e) {
            return new ResponseEntity<>("El id no corresponde a ninguna tarea",HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/obtener/lista")
    @ResponseBody
    public List<Tarea> obtenerListaTarea (){
        return itareaSer.obtenerListaTareas();
    }
    
    @PutMapping("/modificar/{id}")
    public ResponseEntity<String> modificarTarea(@PathVariable Long id, @RequestBody Tarea tarea){
        try {
            itareaSer.modificarTarea(id, tarea);
            return new ResponseEntity<>("Modificacion Realizada",HttpStatus.OK);
        } catch (MisExcepciones e) {
            return new ResponseEntity<>("El id no corresponde a ninguna tarea", HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/borrar/{id}")
    public void eliminarTarea(@PathVariable Long id){
        itareaSer.eliminarUnaTarea(id);
    }
    
    
    
}
