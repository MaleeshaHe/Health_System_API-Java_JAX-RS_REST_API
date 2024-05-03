package net.codejava.ws.restfulresource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.codejava.ws.dao.PatientDAO;
import net.codejava.ws.models.Patient;

@Path("/patients")
public class PatientResource {
	private PatientDAO dao = PatientDAO.getInstance();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Patient> list() {
		return dao.listAll();
	}
	
	@POST 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Patient patient) throws URISyntaxException {
		int newPatientId = dao.add(patient);
		URI uri = new URI("/patients/" + newPatientId);
		return Response.created(uri).entity(patient).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response update(@PathParam("id") int id, Patient patient) {
		patient.setId(id);
		if (dao.update(patient)) {
			return Response.ok().entity(patient).build();

		} else {
			return Response.notModified().build();
		}
	}	
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") int id) {
		if (dao.delete(id)) {
			return Response.ok().build();
		} else {
			return Response.notModified().build();
		}
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
		Patient patient = dao.get(id);
		if (patient != null) {
			return Response.ok(patient, MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
}
