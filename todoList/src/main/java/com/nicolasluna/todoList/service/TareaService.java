package com.nicolasluna.todoList.service;

import com.nicolasluna.todoList.excepciones.MisExcepciones;
import com.nicolasluna.todoList.model.Tarea;
import com.nicolasluna.todoList.repository.ITareaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TareaService implements ITareaService {

    @Autowired
    private ITareaRepository tareaRepo;

    @Override
    @Transactional
    public void crearTarea(Tarea tareaNueva) {
        Tarea tareaAGuardar = new Tarea();
        tareaAGuardar.setTitulo(tareaNueva.getTitulo());
        tareaAGuardar.setDescripcion(tareaNueva.getDescripcion());
        tareaAGuardar.setCompletada(tareaNueva.getCompletada());
        tareaAGuardar.setFechaDeVencimiento(tareaNueva.getFechaDeVencimiento());
        tareaRepo.save(tareaAGuardar);
        // Se coloca 1 por 1 ya que si se recibia un id y se guardaba esto podria generar una modificacion en 1 creado
        // En caso de que se usara save directo en el POST no se deberia mandar el id (forma actual omite el envio de id)
        // Usamos @Transactional por si el metodo lanza una excepcion, en caso de que la arroje  
        // se hace un rollback para no afectar a la bd (usar solo en las que hacen trasnsacciones)
    }

    @Override
    public Tarea obtenerUnaTarea(Long id) throws MisExcepciones {
        Optional<Tarea> respuesta = tareaRepo.findById(id);
        if (respuesta.isPresent()) {
            return respuesta.get();
        } else {
            throw new MisExcepciones("El id no corresponde a ninguna tarea");
        }
        // El uso de opcional podemos asimilarlo a una caja, este podrá contener el objeto o no 
        // por lo que su respuesta será "no estoy vacio = objeto" o "estoy vacio = null"
        // Por lo que en caso de ser nulo podremos manejar esto
        // Manejamos el nulo con una excepcion personalizada (se debe colocar throws TareaExcepciones en el metodo y la interface)
        // En el controller se utiliza: new ResponseEntity<?>(objeto,HttpStatus.);
        // Usamos <?> porque puede retornar un Objeto Tarea o un String en caso de que atrape una excepcion
    }

    @Override
    public List<Tarea> obtenerListaTareas() {
        return tareaRepo.findAll();
        //  No se usa Optional porque el metodo findAll() devuelve una lista vacia o con contenido
        //  No se usa .get porque este se usa con Optional para obtener lo que esta "dentro de la caja"
        //  Por lo que nunca devolverá un null sino una lista vacia si no hay nada
    }

    @Override
    @Transactional
    public void modificarTarea(Long id, Tarea tareaNueva) throws MisExcepciones {

        Tarea tareaObtenida = this.obtenerUnaTarea(id);
        //El obtener una Tarea puede arrojar una excepcion en caso de que el id sea incorrecto
        // Por lo que se debe colocar throws TareaExcepciones en el metodo y la interface)
        // En el controller se utiliza: new ResponseEntity<>(Mensaje,HttpStatus.);
        // No usamos <?> ya que lo unico que puede retornar es un String
        
        tareaObtenida.setTitulo(tareaNueva.getTitulo());
        tareaObtenida.setDescripcion(tareaNueva.getDescripcion());
        tareaObtenida.setCompletada(tareaNueva.getCompletada());
        tareaObtenida.setFechaDeVencimiento(tareaNueva.getFechaDeVencimiento());
        tareaRepo.save(tareaObtenida);
    }

    @Override
    @Transactional
    public void eliminarUnaTarea(Long id) {
        tareaRepo.deleteById(id);
        // No se usa Optional porque no importa si el registro está presente o no; 
        // El método intenta eliminarlo independientemente de su existencia
        // Se debe evitar colocar el metodo delete, ya que al borrar, el id quedará eliminado del registro debido al identity
    }

}
