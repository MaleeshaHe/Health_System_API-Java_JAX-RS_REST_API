package net.codejava.ws.dao;

import net.codejava.ws.models.Doctor;
import net.codejava.ws.models.Patient;
import net.codejava.ws.models.Prescription;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO {
    private static PrescriptionDAO instance;
    private static List<Prescription> data = new ArrayList<>();

    static {
        data.add(new Prescription(1,new Patient(1,"Maleesha","077-8236207","Kegalle","Blood Presure","Good"),new Doctor(1,"Dr. Prageeth","077-8746321","Kegalle","General Physician"),"Panadol", "2 tablets", "After meal", "1 week"));
        data.add(new Prescription(2,new Patient(2,"Sonali","071-4567123","Pilimathalawa","Blood Presure","Good"),new Doctor(2,"Dr. Nimal","071-4567123","Pilimathalawa","General Physician"),"Panadol", "2 tablets", "After meal", "1 week"));
    }

    private PrescriptionDAO() {

    }

    public static PrescriptionDAO getInstance() {
        if (instance == null) {
            instance = new PrescriptionDAO();
        }
        return instance;
    }

    public List<Prescription> listAll() {
        return new ArrayList<Prescription>(data);
    }

    public int add(Prescription prescription) {
        int newId = data.size() + 1;
        prescription.setId(newId);
        data.add(prescription);

        return newId;
    }

    public Prescription get(int id) {
        Prescription prescriptionToFind = new Prescription(id);
        int index = data.indexOf(prescriptionToFind);
        if (index >= 0) {
            return data.get(index);
        }
        return null;
    }

    public boolean delete(int id) {
        Prescription prescriptionToFind = new Prescription(id);
        int index = data.indexOf(prescriptionToFind);
        if(index >= 0) {
            data.remove(index);
            return true;
        }
        return false;
    }

    public boolean update(Prescription prescription) {
        int index = data.indexOf(prescription);
        if (index >= 0) {
            data.set(index, prescription);
            return true;
        }
        return false;
    }
}
