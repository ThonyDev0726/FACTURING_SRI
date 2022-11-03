
package com.anthony.ModelsInterfaces;

import com.anthony.Models.*;
import java.util.List;

/**
 *
 * @author perez
 */
public interface crud_cotizacion {

    public List listar();

    public COTIZACION list(int idFactura);

    public String add(COTIZACION mp);

    public String update(COTIZACION mp);

    public String delete(int id);

    public List listar_en_linea();

    public String actualizar_estado(int id, String estado);
}
