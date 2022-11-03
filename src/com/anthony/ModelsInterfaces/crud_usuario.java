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
public interface crud_usuario {

    public List listar();

    public USUARIO list(int idUsuario);

    public String add(USUARIO mp);

    public String update(USUARIO mp);

    public String delete(int id);

    public String actualizar_estado(int id, String estado);

    /* =========== INICIO DE SESION =========== */
    public Integer consultarUsuario(String USU_USUARIO);

    public String obtenerClave(int ID_USUARIO);

    public String consultarCargo(int ID_USUARIO);

    public String consultarEstado(int ID_USUARIO);

    /* =========== ACTUALIZAR CLAVE =========== */
    public String update_password(int ID_USUARIO, String clave);
}
