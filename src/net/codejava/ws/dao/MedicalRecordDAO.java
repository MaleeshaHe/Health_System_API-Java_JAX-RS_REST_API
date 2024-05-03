package net.codejava.ws.dao;

import net.codejava.ws.models.MedicalRecord;
import net.codejava.ws.models.Patient;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDAO {
    private static MedicalRecordDAO instance;
    private static List<MedicalRecord> data = new ArrayList<>();

    static {
        // Adding sample medical records
        Patient patient1 = new Patient(1,"Maleesha","077-8236207","Kegalle","Blood Presure","Good");

        List<String> diagnoses1 = new ArrayList<>();
        diagnoses1.add("Diabetes");
        diagnoses1.add("Hypertension");

        List<String> treatments1 = new ArrayList<>();

        MedicalRecord record1 = new MedicalRecord(1, patient1, diagnoses1, treatments1);
        data.add(record1);
    }

    private MedicalRecordDAO() {

    }

    public static MedicalRecordDAO getInstance() {
        if (instance == null) {
            instance = new MedicalRecordDAO();
        }
        return instance;
    }

    public List<MedicalRecord> listAll() {
        return new ArrayList<MedicalRecord>(data);
    }

    public int add(MedicalRecord medicalRecord) {
        int newId = data.size() + 1;
        medicalRecord.setId(newId);
        data.add(medicalRecord);

        return newId;
    }

    public MedicalRecord get(int id) {
        MedicalRecord medicalRecordToFind = new MedicalRecord(id);
        int index = data.indexOf(medicalRecordToFind);
        if (index >= 0) {
            return data.get(index);
        }
        return null;
    }

    public boolean delete(int id) {
        MedicalRecord medicalRecordToFind = new MedicalRecord(id);
        int index = data.indexOf(medicalRecordToFind);
        if(index >= 0) {
            data.remove(index);
            return true;
        }
        return false;
    }

    public boolean update(MedicalRecord medicalRecord) {
        int index = data.indexOf(medicalRecord);
        if(index >= 0) {
            data.set(index, medicalRecord);
            return true;
        }
        return false;
    }
}
