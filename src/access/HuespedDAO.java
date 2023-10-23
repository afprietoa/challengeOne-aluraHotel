/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Huesped;
/**
 *
 * @author Hp
 */
public class HuespedDAO {
    private Connection connection;
    
    public HuespedDAO(Connection connection) {
        this.connection = connection;
    }
    
     public void saveReserva(Huesped huesped){
         try {
             String sql = "INSERT INTO huesped(nombre,apellido,fecha_nacimiento, nacionalidad" 
             + ",telefono,id_reserva) VALUES(?,?,?,?,?,?)";
             try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                 statement.setString(1, huesped.getNombre());
                 statement.setString(2,huesped.getApellido());
                 statement.setObject(3, huesped.getFechaNacimiento());
                 statement.setString(4,huesped.getNacionalidad());
                 statement.setString(5, huesped.getTelefono());
                 statement.setInt(6, huesped.getIdReserva());
                 statement.execute();
                 try(ResultSet result = statement.getGeneratedKeys()){
                     while(result.next()){
                         huesped.setId(result.getInt(1));
                     }
                 }
             }
         } catch (SQLException e) {
             throw new RuntimeException();
         }
     }
     
        public List<Huesped> getListHuespedes(){
        List<Huesped> huespedes = new ArrayList<Huesped>();
        try {
            String sql = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM huesped";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.execute();
                
                transformResultado(huespedes, statement);
            }
            return huespedes;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    
    public List<Huesped> getHuesped(String id){
        List<Huesped> huespedes = new ArrayList<Huesped>();
        try {
            String sql = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM huesped WHERE id=?";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, id);
                statement.execute();
                
                transformResultado(huespedes, statement);
            }
            return huespedes;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }    
        
         public void transformResultado(List<Huesped> huespedes, PreparedStatement statement) throws SQLException{
        try(ResultSet result = statement.executeQuery()) {
            while(result.next()){
                int id = result.getInt("id");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                LocalDate fechaNacimiento = result.getDate("fecha_nacimiento").toLocalDate().plusDays(1);
                String nacionalidad = result.getString("nacionalidad");
                String telefono = result.getString("telefono");
                int idReserva = result.getInt("id_reserva");
                
                Huesped huesped = new Huesped(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);
                huespedes.add(huesped);
            }
        } catch (Exception e) {
        }
    }
         
    public void updateHuesped(String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad, String telefono, Integer idReserva, Integer id){
        
        try(PreparedStatement statement = connection.prepareStatement(""
                + "UPDATE huesped SET nombre=?, apellido=?, fecha_nacimiento=?, nacionalidad=?,"
                + "telefono=?, id_reserva=? WHERE id=?")) {
            statement.setString(1, nombre);
            statement.setString(2,apellido);
            System.out.println(fechaNacimiento);
            System.out.println(id);
            statement.setObject(3, fechaNacimiento);
            statement.setString(4, nacionalidad);
            statement.setString(5, telefono);
            statement.setInt(6, idReserva);
            statement.setInt(7,id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void deleteHuesped(Integer id){
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM huesped WHERE id=?")){
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
