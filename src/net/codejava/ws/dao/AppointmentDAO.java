package net.codejava.ws.dao;

import java.util.ArrayList;
import java.util.List;

import net.codejava.ws.models.Appointment;
import net.codejava.ws.models.Doctor;
import net.codejava.ws.models.Patient;
public class AppointmentDAO {
    private static AppointmentDAO instance;
    private static List<Appointment> data = new ArrayList<>();

    static {
        data.add(new Appointment(1,"05/02/2024","10:30 P.M",new Patient(1,"Maleesha","077-8236207","Kegalle","Blood Presure","Good"),new Doctor(1,"Dr. Prageeth","077-8746321","Kegalle","General Physician")));
        data.add(new Appointment(2,"06/02/2024","11:30 P.M",new Patient(2,"Sonali","071-4567123","Pilimathalawa","Blood Presure","Good"),new Doctor(2,"Dr. Kasun","077-4157412","Pilimathalawa","Dermatologist")));
    }

    private AppointmentDAO() {

    }

    public static AppointmentDAO getInstance() {
        if (instance == null) {
            instance = new AppointmentDAO();
        }
        return instance;
    }

    public List<Appointment> listAll() {
        return new ArrayList<Appointment>(data);
    }

    public int add(Appointment appointment) {
        int newId = data.size() + 1;
        appointment.setId(newId);
        data.add(appointment);

        return newId;
    }

    public Appointment get(int id) {
        Appointment appointmentToFind = new Appointment(id);
        int index = data.indexOf(appointmentToFind);
        if (index >= 0) {
            return data.get(index);
        }
        return null;
    }

    public boolean delete(int id) {
        Appointment appointmentToFind = new Appointment(id);
        int index = data.indexOf(appointmentToFind);
        if(index >= 0) {
            data.remove(index);
            return true;
        }
        return false;
    }

    public boolean update(Appointment appointment) {
        int index = data.indexOf(appointment);
        if (index >= 0) {
            data.set(index, appointment);
            return true;
        }
        return false;
    }
}
