/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import access.ReservaDAO;
import model.Reserva;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import service.Fachada;
/**
 *
 * @author Hp
 */
public class ReservaController {
    private ReservaDAO reservaDAO;

    public ReservaController() {
        Connection connection = new Fachada().getConnexion();
        this.reservaDAO = new ReservaDAO(connection);
    }
    
    public void addReserva(Reserva reserva){
        this.reservaDAO.saveReserva(reserva);
    }
    
    public List<Reserva> listReservas(){
        return this.reservaDAO.getListReservas();
    }
    
    public List<Reserva> findReserva(String id){
        return this.reservaDAO.getReserva(id);
    }
    
    public void editReserva(LocalDate fechaEntrada, LocalDate fechaSalida, String valor, String formaPago, Integer id){
        this.reservaDAO.updateReserva(fechaEntrada, fechaSalida, valor, formaPago, id);
    }
    
    public void removeReserva(Integer id){
        this.reservaDAO.deleteReserva(id);
    }
    
}
