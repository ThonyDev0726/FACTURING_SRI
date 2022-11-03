package com.anthony.ModelsInterfaces;

import com.anthony.Models.*;
import java.util.List;

/**
 *
 * @author perez
 */
public interface crud_cliente {

    public List listar();

    public CLIENTE list(int idCliente);

    public String add(CLIENTE cli);

    public String update(CLIENTE cli);

    public String delete(int id);

    public List listar_en_linea();

    public String actualizar_estado(int id, String estado);

}
