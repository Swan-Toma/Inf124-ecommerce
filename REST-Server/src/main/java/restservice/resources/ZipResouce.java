package restservice.resources;

import restservice.service.ProductService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import restservice.model.ZipCode;


@Path("/zipcode")
public class ZipResouce {
	// This method represents an endpoint with the URL /todos/{id} and a GET request
	// ( Note that {id} is a placeholder for a path parameter)
	@Path("{zip}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getZipResourceById(@PathParam("zip") int zip/* The {id} placeholder parameter is resolved */) {
		// invokes the DB method which will fetch a todo_list item object by id
		ZipCode zipCode = ProductService.getZipById(zip);

		// Respond with a 404 if there is no such todo_list item for the id provided
		if (zipCode == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		// Respond with a 200 OK if you have a todo_list_item object to return as
		// response
		return Response.ok(zipCode).build();
	}
}