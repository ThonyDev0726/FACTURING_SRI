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
public interface crud_factura {

    public List listar();

    public FACTURA list(int idFactura);

    public String add(FACTURA mp);

    public String update(FACTURA mp);

    public String delete(int id);

}
