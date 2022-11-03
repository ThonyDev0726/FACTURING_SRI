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
public interface crud_empleado {

    public List listar();

    public EMPLEADO list(int idEmpleado);

    public String add(EMPLEADO mp);

    public String update(EMPLEADO mp);

    public String delete(int id);

    public List listar_en_linea();

    public String actualizar_estado(int id, String estado);
}
