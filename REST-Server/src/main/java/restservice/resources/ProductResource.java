package restservice.resources;

import restservice.model.Order;
import restservice.model.Product;
import restservice.model.Category;
import restservice.service.ProductService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


// @Path on a method.
@Path("/products")
public class ProductResource {
	// This method represents an endpoint with the URL /todos/{id} and a GET request
	// ( Note that {id} is a placeholder for a path parameter)
	@Path("categories/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON }) // This provides only JSON responses
	public Response getCategoryByPid(@PathParam("id") int id/* The {id} placeholder parameter is resolved */) {
		// invokes the DB method which will fetch a todo_list item object by id
		List<Category> categories = ProductService.getCategoriesByPid(id);

		// Respond with a 404 if there is no such todo_list item for the id provided
		if (categories == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		// Respond with a 200 OK if you have a todo_list_item object to return as
		// response
		return Response.ok(categories).build();
	}

	@Path("{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON }) // This provides only JSON responses
	public Response getProductResourceByPid(@PathParam("id") int id/* The {id} placeholder parameter is resolved */) {
		// invokes the DB method which will fetch a todo_list item object by id
		Product todo = ProductService.getProductByPid(id);

		// Respond with a 404 if there is no such todo_list item for the id provided
		if (todo == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		// Respond with a 200 OK if you have a todo_list_item object to return as
		// response
		return Response.ok(todo).build();
	}

	// This method represents an endpoint with the URL /todos and a GET request.
	// Since there is no @PathParam mentioned, the /todos as a relative path and a
	// GET request will invoke this method.
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAllTodos() {
		List<Product> todoList = ProductService.getAllProducts();

		if (todoList == null || todoList.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		return Response.ok(todoList).build();
	}

	// This method represents an endpoint with the URL /todos and a POST request.
	// Since there is no @PathParam mentioned, the /todos as a relative path and a
	// POST request will invoke this method.
	@POST
	@Consumes({ MediaType.APPLICATION_JSON }) // This method accepts a request of the JSON type
	public Response addTodo(Order order) {

		// The todo object here is automatically constructed from the json request.
		// Jersey is so cool!
		int oid = ProductService.AddOrder(order);
		if (oid != -1) {

			return Response.ok().entity(Integer.toString(oid)).build();
		}

		// Return an Internal Server error because something wrong happened. This should
		// never be executed
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
}
