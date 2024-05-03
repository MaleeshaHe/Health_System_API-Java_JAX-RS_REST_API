package net.codejava.ws.restfulresource;
import net.codejava.ws.dao.BillingDAO;
import net.codejava.ws.models.Billing;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/bills")
public class BillingResource {
    private BillingDAO dao = BillingDAO.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Billing> list() {
        return dao.listAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Billing billing) throws URISyntaxException {
        int newBillingId = dao.add(billing);
        URI uri = new URI("/bills/" + newBillingId);
        return Response.created(uri).entity(billing).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response update(@PathParam("id") int id, Billing billing) {
        billing.setId(id);
        if (dao.update(billing)) {
            return Response.ok().entity(billing).build();
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
        Billing billing = dao.get(id);
        if (billing != null) {
            return Response.ok().entity(billing).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
