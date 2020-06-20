/*
 *  ProductPage servlet will display details for a specific product.
 *  You can add a product to cart using the button on this page.
 */
package productpage;

import model.Product;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import javax.servlet.ServletException;
import javax.ws.rs.NotFoundException;

public class ProductPage extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            PrintWriter out = response.getWriter();
            String pid = request.getParameter("pid");
            
            ClientConfig config = new ClientConfig();
            Client client = ClientBuilder.newClient(config);
            WebTarget target = client.target(getBaseURI());
            String jsonResponse = target.path("v1").path("api").path("products").path(pid).request(). // send a request
                    accept(MediaType.APPLICATION_JSON). // specify the media type of the response
                    get(String.class); // use the get method and return the response as a string
            ObjectMapper objectMapper = new ObjectMapper(); 

            Product product = objectMapper.readValue(jsonResponse, Product.class);
            String details = product.getDetails();
            String[] detailsSplit = details.split(",");

            out.println("<div id=\"main\">");
            out.println("<div id=\"info\">");
            out.println("<img id=\"product_image\" src=\"" + product.getImage() + "\" alt=\"Product image\">");
            out.println("<div>");
            out.println("<h3 id=\"name\">" + product.getName() + " - $" + product.getPrice() + " </h3>");
            out.println("<form method = \"post\" action = \"/REST-Client/Cart\">");
            out.println("<input type = \"text\" name =\"pid\" value=\"" + product.getPid() + "\" hidden>");
            out.println("<input type = \"submit\" name =\"cart\" value=\"Add To Cart\">");
            out.println("");
            out.print("</form>");
            out.println("<h5 id=\"pid\">pid: " + product.getPid() + "</h5>");
            out.println("<p id=\"description\">" + product.getDescription() + "</p>");
            out.println("<p id=\"details\">");
            out.println("<ul>");
            for (String s : detailsSplit) {
                out.println("<li>" + s + "</li>");
            }
            out.println("</ul></p>");
            out.println("</div>");
            out.println("</div>");

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
