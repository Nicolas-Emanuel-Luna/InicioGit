
package com.nicolasluna.todoList.repository;

import com.nicolasluna.todoList.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITareaRepository extends JpaRepository<Tarea, Long> {

}
