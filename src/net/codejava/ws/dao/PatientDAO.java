package net.codejava.ws.dao;

import java.util.ArrayList;
import java.util.List;

import net.codejava.ws.models.Patient;

public class PatientDAO {
	private static PatientDAO instance;
	private static List<Patient> data = new ArrayList<>();
	
	static {
		data.add(new Patient(1,"Maleesha","077-8236207","Kegalle","Blood Presure","Good"));
		data.add(new Patient(2,"Sonali","071-4567123","Pilimathalawa","Blood Presure","Good"));
	}
	
	private PatientDAO() {
		
	}
	
	public static PatientDAO getInstance() {
		if (instance == null) {
			instance = new PatientDAO();
		}
		return instance;
	}
	
	public List<Patient> listAll() {
		return new ArrayList<Patient>(data);
	}
	
	public int add(Patient patient) {
		int newId = data.size() + 1;
		patient.setId(newId);
		data.add(patient);
		
		return newId;
	}
	
	public Patient get(int id) {
		Patient patientToFind = new Patient(id);
		int index = data.indexOf(patientToFind);
		if (index >= 0) {
			return data.get(index);
		}
		return null;
	}
	
	public boolean delete(int id) {
		Patient patientToFind = new Patient(id);
		int index = data.indexOf(patientToFind);
		if(index >= 0) {
			data.remove(index);
			return true;
		}
		return false;
	}
	
	public boolean update(Patient patient) {
		int index = data.indexOf(patient);
		if (index >= 0) {
			data.set(index, patient);
			return true;
		}
		return false;
	}
}
