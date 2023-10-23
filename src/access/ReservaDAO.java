/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package access;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Reserva;
/**
 *
 * @author Hp
 */
public class ReservaDAO {
    private Connection connection;

    public ReservaDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void saveReserva(Reserva reserva){
        try {
            String sql = "INSERT INTO reserva(fecha_entrada, fecha_salida, valor, forma_de_pago)"
                    + "VALUES(?,?,?,?)";
            try(PreparedStatement statement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)){
                statement.setObject(1, reserva.getFechaEntrada());
                statement.setObject(2, reserva.getFechaSalida());
                statement.setObject(3, reserva.getValor());
                statement.setObject(4, reserva.getFormaPago());
                statement.executeUpdate();
                try(ResultSet result = statement.getGeneratedKeys()){
                    while(result.next()){
                        reserva.setId(result.getInt(1));
                    }
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    
    public List<Reserva> getListReservas(){
        List<Reserva> reservas = new ArrayList<Reserva>();
        try {
            String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reserva";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.execute();
                
                transformResultado(reservas, statement);
            }
            return reservas;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

        public List<Reserva> getReserva(String id){
        List<Reserva> reservas = new ArrayList<Reserva>();
        try {
            String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reserva WHERE id=?";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, id);
                statement.execute();
                
                transformResultado(reservas, statement);
            }
            return reservas;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
       
     public void updateReserva(LocalDate fechaEntrada, LocalDate fechaSalida, String valor, String formaPago, Integer id){
         try (PreparedStatement statement = connection.prepareStatement("UPDATE reserva SET "
         + "fecha_entrada=?, fecha_salida=?, valor=?, forma_de_pago=? WHERE id=?")){
             statement.setObject(1, java.sql.Date.valueOf(fechaEntrada));
             statement.setObject(2, fechaSalida);
             statement.setString(3, valor);
             statement.setString(4, formaPago);
             statement.setInt(5,id);
             statement.execute();
         } catch (SQLException e) {
             throw new RuntimeException();
         }
     }
     
    public void deleteReserva(Integer id){
        try{
            Statement statement = connection.createStatement();
            statement.execute("SET FOREIGN_KEY_CHECKS=0");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM reserva WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            statement.execute("SET FOREIGN_KEY_CHECKS=1");
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
    public void transformResultado(List<Reserva> reservas, PreparedStatement statement) throws SQLException{
        try(ResultSet result = statement.getResultSet()) {
            while(result.next()){
                int id = result.getInt("id");
                LocalDate fechaEntrada = result.getDate("fecha_entrada").toLocalDate().plusDays(1);
                LocalDate fechaSalida = result.getDate("fecha_salida").toLocalDate().plusDays(1);
                String valor = result.getString("valor");
                String formaPago = result.getString("forma_de_pago");
                
                Reserva reserva = new Reserva(id, fechaEntrada, fechaSalida, valor, formaPago);
                reservas.add(reserva);
            }
        } catch (Exception e) {
        }
    }
}
