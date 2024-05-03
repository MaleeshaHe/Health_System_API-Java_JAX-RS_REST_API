package net.codejava.ws.restfulresource;

import net.codejava.ws.dao.DoctorDAO;
import net.codejava.ws.models.Doctor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/doctors")
public class DoctorResource {
    private DoctorDAO dao = DoctorDAO.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Doctor> list() {
        return dao.listAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Doctor doctor) throws URISyntaxException {
        int newDoctorId = dao.add(doctor);
        URI uri = new URI("/doctors/" + newDoctorId);
        return Response.created(uri).entity(doctor).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response update(@PathParam("id") int id, Doctor doctor) {
        doctor.setId(id);
        if (dao.update(doctor)) {
            return Response.ok().entity(doctor).build();
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
        Doctor doctor = dao.get(id);
        if (doctor != null) {
            return Response.ok().entity(doctor).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
