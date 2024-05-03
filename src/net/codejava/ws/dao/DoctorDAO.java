package net.codejava.ws.dao;

import java.util.ArrayList;
import java.util.List;

import net.codejava.ws.models.Doctor;

public class DoctorDAO {
    private static DoctorDAO instance;
    private static List<Doctor> data = new ArrayList<>();

    static {
        data.add(new Doctor(1,"Dr. Prageeth","077-8746321","Kegalle","General Physician"));
        data.add(new Doctor(2,"Dr. Kasun","077-4157412","Pilimathalawa","Dermatologist"));
    }

    private DoctorDAO() {

    }

    public static DoctorDAO getInstance() {
        if (instance == null) {
            instance = new DoctorDAO();
        }
        return instance;
    }

    public List<Doctor> listAll() {
        return new ArrayList<Doctor>(data);
    }

    public int add(Doctor doctor) {
        int newId = data.size() + 1;
        doctor.setId(newId);
        data.add(doctor);

        return newId;
    }

    public Doctor get(int id) {
        Doctor doctorToFind = new Doctor(id);
        int index = data.indexOf(doctorToFind);
        if (index >= 0) {
            return data.get(index);
        }
        return null;
    }

    public boolean delete(int id) {
        Doctor doctorToFind = new Doctor(id);
        int index = data.indexOf(doctorToFind);
        if(index >= 0) {
            data.remove(index);
            return true;
        }
        return false;
    }

    public boolean update(Doctor doctor) {
        int index = data.indexOf(doctor);
        if (index >= 0) {
            data.set(index, doctor);
            return true;
        }
        return false;
    }
}
