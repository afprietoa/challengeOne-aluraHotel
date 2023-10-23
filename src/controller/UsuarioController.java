package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import access.UsuarioDAO;
import javax.swing.JOptionPane;
import views.Login;
import views.MenuUsuario;
/**
 *
 * @author Hp
 */
public class UsuarioController implements ActionListener{
    private Login vista;
    
    public UsuarioController(Login vista){
        this.vista = vista;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String nombre = vista.getNombre();
        String contrasena =  vista.getContrasena();
        
        if(UsuarioDAO.validateUser(nombre, contrasena))
        {
            MenuUsuario menu = new MenuUsuario();
            menu.setVisible(true);
            vista.dispose();
        }
        else{
            JOptionPane.showMessageDialog(vista, "Usuario o Contraseña no validos");
        }
        
    }
}
