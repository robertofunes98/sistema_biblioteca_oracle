/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author funes
 */
public class UserModel {
    public String nombre;
    public int tipoUsuario;
    
    public UserModel(String nombre,int tipoUsuario)
    {
        this.nombre=nombre;
        this.tipoUsuario=tipoUsuario;
    }
}
