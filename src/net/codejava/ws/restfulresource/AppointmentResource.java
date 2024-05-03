package net.codejava.ws.restfulresource;

import net.codejava.ws.dao.AppointmentDAO;
import net.codejava.ws.models.Appointment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/appointments")
public class AppointmentResource {
    private AppointmentDAO dao = AppointmentDAO.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Appointment> list() {
        return dao.listAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Appointment appointment) throws URISyntaxException {
        int newAppointmentId = dao.add(appointment);
        URI uri = new URI("/appointments/" + newAppointmentId);
        return Response.created(uri).entity(appointment).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response update(@PathParam("id") int id, Appointment appointment) {
        appointment.setId(id);
        if (dao.update(appointment)) {
            return Response.ok().entity(appointment).build();
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
        Appointment appointment = dao.get(id);
        if (appointment != null) {
            return Response.ok().entity(appointment).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
