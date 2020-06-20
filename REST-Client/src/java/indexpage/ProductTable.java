/*
 *  Servlet will display all product listing when included 
 *  in index.jsp.
 */
package indexpage;

import model.Category;
import model.Product;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.WebTarget;
import org.glassfish.jersey.client.ClientConfig;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.List;
import javax.ws.rs.NotFoundException;
import org.codehaus.jackson.type.TypeReference;

public class ProductTable extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            PrintWriter out = response.getWriter();

            // HTTP based fields
            ClientConfig config = new ClientConfig();
            Client client = ClientBuilder.newClient(config);
            WebTarget target = client.target(getBaseURI());
            String jsonResponse = target.path("v1").path("api").path("products").request(). // send a request
                    accept(MediaType.APPLICATION_JSON). // specify the media type of the response
                    get(String.class); // use the get method and return the response as a string
            ObjectMapper objectMapper = new ObjectMapper();
            List<Product> productList = objectMapper.readValue(jsonResponse, new TypeReference<List<Product>>() {
            });

            String catsResponse, categoriesStr;
            List<Category> categories;
            ObjectMapper catObjectMapper;

            for (Product product : productList) {
                catsResponse = target.path("v1").path("api").path("products").path("categories")
                        .path(Integer.toString(product.getPid())).request().accept(MediaType.APPLICATION_JSON)
                        .get(String.class);

                catObjectMapper = new ObjectMapper();

                categories = catObjectMapper.readValue(catsResponse,
                        new TypeReference<List<Category>>() {
                });

                categoriesStr = "";
                for (Category c : categories) {
                    categoriesStr += c.getCategory() + " ";
                }

                out.println("<a href=http://localhost:8080/REST-Client/productpage.jsp?pid=" + product.getPid()
                        + ">");
                out.println("<div id = " + product.getPid() + " class='" + categoriesStr + "'>");
                out.println("<img src='" + product.getImage() + "'/>");
                out.println("<div><p>" + product.getName() + "</p><p>" + product.getPrice() + "</p></div></div>");
                out.println("</a>");
            }
        } catch (IOException e) {
            // printwriter code
            System.out.println(e.getMessage());
            System.out.println(e.toString());
            e.printStackTrace();
        } catch (NotFoundException nfe) {
            // target.path request
            System.out.println(nfe.getMessage());
            System.out.println(nfe.toString());
            nfe.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/REST-Server").build();
    }
}
