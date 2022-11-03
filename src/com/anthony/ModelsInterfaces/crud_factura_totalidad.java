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
public interface crud_factura_totalidad {

    public List listar();

    public FACTURA_TOTALIDAD list(int idFactura);

    public String add(FACTURA_TOTALIDAD mp);

    public String delete(int id);

}
