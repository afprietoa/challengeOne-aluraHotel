/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import access.HuespedDAO;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import service.Fachada;
import model.Huesped;
/**
 *
 * @author Hp
 */
public class HuespedController {
    private HuespedDAO huespedDAO;

    public HuespedController() {
        Connection connection = new Fachada().getConnexion();
        this.huespedDAO = new HuespedDAO(connection);
    }
    
    
    public void addHuesped(Huesped huesped){
        this.huespedDAO.saveReserva(huesped);
    }
    
    public List<Huesped> listHuespedes(){
        return this.huespedDAO.getListHuespedes();
    }
    
    public List<Huesped> findHuesped(String id){
        return this.huespedDAO.getHuesped(id);
    }
    
    public void editHuesped(String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad, String telefono, Integer idReserva, Integer id){
        this.huespedDAO.updateHuesped(nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva, id);
    }
    
    public void removeHuesped(Integer idReserva){
        this.huespedDAO.deleteHuesped(idReserva);
    }
}
