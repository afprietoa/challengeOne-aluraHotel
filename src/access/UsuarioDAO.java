/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package access;

import service.Fachada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Hp
 */
public class UsuarioDAO {
    
    public static boolean validateUser(String name, String password){
        Fachada facade = new Fachada();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result=null;
        try {
            connection = facade.getConnexion();
            statement = connection.prepareStatement("SELECT * FROM usuario WHERE nombre=? AND contrasena=?");
            statement.setString(1, name);
            statement.setString(2, password);
            result = statement.executeQuery();
            return result.next();
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally{
            try{
                if(result != null)
                    result.close();
                if(statement != null)
                    statement.close();
                if(connection !=null)
                    connection.close();
            }catch(SQLException e){
                 e.printStackTrace();
            }
        }
    };
}
