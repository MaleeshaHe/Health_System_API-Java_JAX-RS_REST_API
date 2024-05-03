package net.codejava.ws.restfulresource;

import net.codejava.ws.dao.PrescriptionDAO;
import net.codejava.ws.models.Prescription;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/prescriptions")
public class PrescriptionResource {
    private PrescriptionDAO dao = PrescriptionDAO.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prescription> list() {
        return dao.listAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Prescription prescription) throws URISyntaxException {
        int newPrescriptionId = dao.add(prescription);
        URI uri = new URI("/prescriptions/" + newPrescriptionId);
        return Response.created(uri).entity(prescription).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response update(@PathParam("id") int id, Prescription prescription) {
        prescription.setId(id);
        if (dao.update(prescription)) {
            return Response.ok().entity(prescription).build();
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
        Prescription prescription = dao.get(id);
        if (prescription != null) {
            return Response.ok().entity(prescription).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
