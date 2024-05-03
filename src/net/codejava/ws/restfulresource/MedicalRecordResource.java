package net.codejava.ws.restfulresource;

import net.codejava.ws.dao.MedicalRecordDAO;
import net.codejava.ws.models.MedicalRecord;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/medicalrecords")
public class MedicalRecordResource {
    private MedicalRecordDAO dao = MedicalRecordDAO.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MedicalRecord> list() {
        return dao.listAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(MedicalRecord medicalRecord) throws URISyntaxException {
        int newMedicalRecordId = dao.add(medicalRecord);
        URI uri = new URI("/medicalrecords/" + newMedicalRecordId);
        return Response.created(uri).entity(medicalRecord).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response update(@PathParam("id") int id, MedicalRecord medicalRecord) {
        medicalRecord.setId(id);
        if (dao.update(medicalRecord)) {
            return Response.ok().entity(medicalRecord).build();
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
        MedicalRecord medicalRecord = dao.get(id);
        if (medicalRecord != null) {
            return Response.ok().entity(medicalRecord).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
