/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * 
 *
 * @author Hp
 */
public class Fachada {
    public DataSource dataSource;
    
    public Fachada(){
        ComboPooledDataSource comboPool = new ComboPooledDataSource();
        comboPool.setJdbcUrl("jdbc:mysql://localhost:3306/alura_hotel");
        comboPool.setUser("root");
        comboPool.setPassword("454403");
        this.dataSource = comboPool;
    }
    
    public Connection getConnexion(){
       try{
           return this.dataSource.getConnection();
       }catch(SQLException e){
           System.out.println("There was a mistake!");
           throw new RuntimeException(e);
       }
    };
}
