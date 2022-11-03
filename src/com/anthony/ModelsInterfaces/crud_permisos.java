
package com.anthony.ModelsInterfaces;

import com.anthony.Models.*;
import java.util.List;

/**
 *
 * @author ThonyDev0726
 */
public interface crud_permisos {
    
    public List listar();

    public String add(Integer id_usuario);
    
    public PERMISOS list(int idCliente);

    public String update(PERMISOS mp);

}
