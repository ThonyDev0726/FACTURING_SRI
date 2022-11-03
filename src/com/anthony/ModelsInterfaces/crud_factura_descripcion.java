/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anthony.ModelsInterfaces;

import com.anthony.Models.*;
import java.util.List;

/**
 *
 * @author perez
 */
public interface crud_factura_descripcion {

    public List listar();

    public FACTURA_DESCRIPCION list(int idFactura);

    public String add(FACTURA_DESCRIPCION mp);

    public String delete(int id);

}
