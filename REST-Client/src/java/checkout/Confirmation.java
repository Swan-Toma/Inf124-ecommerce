/*
 *  Sevley will handle the confirmation page logic and 
 *  display a summary of what was submitted through the form
 *  found in the Checkout servlet.
 */
package checkout;

import model.Product;
import model.Order;
import org.glassfish.jersey.client.ClientConfig;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import java.net.URI;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author swantoma
 */
public class Confirmation extends HttpServlet {

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
            String oid = request.getParameter("oid");
            
            // HTTP based fields
            ClientConfig config = new ClientConfig();
            Client client = ClientBuilder.newClient(config);
            WebTarget target = client.target(getBaseURI());
            String jsonResponse = target.path("v1").path("api").path("orders").path(oid).request(). // send a request
                    accept(MediaType.APPLICATION_JSON). // specify the media type of the response
                    get(String.class); // use the get method and return the response as a string
            ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library
            Order order = objectMapper.readValue(jsonResponse, Order.class);

            // Applicaiton logic fields
            String[] pids;
            String pid;
            String qt;

            out.println("<!doctype html>");
            out.println("<html lang=\"en\">");
            out.println("<!--");
            out.println("WRITTEN BY: Greg Zubatov, Swan Toma, Genesis Garcia");
            out.println("EMAIL: gzubatov@uci.edu, sktoma@uci.edu, genesirg@uci.edu");
            out.println("-->");
            out.println("<head>");
            out.println("<meta charset=\"utf-8\">");
            out.println("<meta name=\"author\" content=\"co-authored by Greg Zubatov, Genesis Garcia\">");
            out.println("<meta name=\"description\" content=\"ECommerce Website\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");
            out.println("<link rel=\"stylesheet\" href=\"./css/global.css\">");
            out.println("<link rel=\"stylesheet\" href=\"./css/confirmation.css\">");
            out.println("<title>Ant-R-Us</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<!-- Navbar -->");
            out.println("<div class=\"navbar\">");
            out.println("<div class=\"logo\"><img src=\"./imgs/ant_logo.png\" alt=\"Ants R Us Logo\"></div>");
            out.println("<div class=\"pages\">");
            out.println("<ul class=\"navigation\">");
            out.println("<li><a href=\"/REST-Client/index.jsp\">Home</a></li>");
            out.println("<li><a href=\"./pages/about.html\">About</a></li>");
            out.println("</ul>");
            out.println("</div>");
            out.println("</div>");
            out.println("<!--Confirmation Info-->");
            out.println("<div class = \"conf_info\">");
            out.println("<div class=\"thanks\">");
            out.println("<h1>Thank you," + order.getFirstName() + " " + order.getLastName() + " </h1>");
            out.println("</div>");
            out.println("<div class=\"details\">");
            out.println("<div>");
            out.println("<p><span>Order ID:</span>" + oid + "</p>");
            out.println("<p><span>Total:</span> $" + order.getPriceTotal() + "</p>");
            out.println("<p><span>Contact Number:</span>" + order.getPhoneNumber() + "</p>");
            out.println("</div>");
            out.println("<div>");
            out.println("<p><span>Shipping Address:</span></p>");
            out.println("<p>" + order.getShippingAddress() + "</p>");
            out.println("<p>" + order.getCity() + " ," + order.getState() + " " + order.getZipCode() + "</p>");																																																							// zipCode
            out.println("<p><span>Shipping Method: </span>" + order.getShippingMethod() + "</p>");																									// method
            out.println("<p><span>Credit card: </span> **** **** **** "
                    + Long.toString(order.getCreditCard()).substring(12) + "</p>");
            out.println("</div>");

            pids = order.getPids().split(",");

            for (int i = 0; i < pids.length; ++i) {
                String[] pidQt = pids[i].split(":");
                pid = pidQt[0];
                qt = pidQt[1];
                jsonResponse = target.path("v1").path("api").path("products").path(pid).request(). // send a request
                        accept(MediaType.APPLICATION_JSON). // specify the media type of the response
                        get(String.class); // use the get method and return the response as a string
                objectMapper = new ObjectMapper(); // This object is from the jackson library
                Product product = objectMapper.readValue(jsonResponse, Product.class);
                out.println("<p><span>Name: </span>" + product.getName() + "</p>");
                out.println("<p><span>Quantity: </span>" + qt + "</p>");
            }

            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
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
