/*
 *  Servlet will handle check out process. A form will be 
 *  displayed and a summary of items that were added to cart.
 */
package checkout;

import model.Product;
import model.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;
import java.util.Map;
import javax.ws.rs.NotFoundException;

public class Checkout extends HttpServlet {

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
            HttpSession session = request.getSession(true);
            ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");
            ClientConfig config = new ClientConfig();
            Client client = ClientBuilder.newClient(config);
            WebTarget target = client.target(getProductsURI());
            double total = 0;

            out.println("<div id=\"main\">");
            out.println("<div id=\"cart\">");
            out.println("<ul>");

            for (String pid : cart) {
                String jsonResponse = target.path(pid).request(). // send a request
                        accept(MediaType.APPLICATION_JSON). // specify the media type of the response
                        get(String.class); // use the get method and return the response as a string

                ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library
                Product product = objectMapper.readValue(jsonResponse, Product.class);
                double price = product.getPrice();
                out.println("<li>" + product.getName() + " - $" + price + "</li>");
                total += price;
            }

            out.println("</ul>");
            out.println("</div>");
            out.println("<div id=\"form\">");
            out.println("<h3>Enter your details</h3>");
            out.println("<form action=\"/REST-Client/CheckoutServlet\" method=\"post\" >");
            out.println("<div class=\"name\">");
            out.println("<div>");
            out.println("<label for=\"fname\">First name</label>");
            out.println("<input type=\"text\" id=\"fname\" name=\"fname\">");
            out.println("</div>");
            out.println("<div>");
            out.println("<label for=\"lname\">Last name</label>");
            out.println("<input type=\"text\" id=\"lname\" name=\"lname\">");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"phone\">");
            out.println("<label for=\"phonenum\">Phone Number</label>");
            out.println("<input placeholder=\"123-456-7890\" type=\"tel\" id=\"phonenum\" name=\"phonenum\"");
            out.println("pattern=\"[0-9]{3}[-][0-9]{3}[-][0-9]{4}\" title=\"Format: 123-456-7891\">");
            out.println("</div>");
            out.println("<label for=\"addr\">Shipping Address</label>");
            out.println("<input type=\"text\" id=\"addr\" name=\"addr\">");
            out.println("<div class=\"addr\">");
            out.println("<div>");
            out.println("<label for=\"city\">City</label>");
            out.println("<input type=\"text\" id=\"city\" name=\"city\" />");
            out.println("</div>");
            out.println("<div>");
            out.println("<label for=\"state\">State</label>");
            out.println("<select id=\"state\" name=\"state\">");
            out.println("<option value=\"AL\">AL</option>");
            out.println("<option value=\"AK\">AK</option>");
            out.println("<option value=\"AZ\">AZ</option>");
            out.println("<option value=\"AR\">AR</option>");
            out.println("<option value=\"CA\">CA</option>");
            out.println("<option value=\"CO\">CO</option>");
            out.println("<option value=\"CT\">CT</option>");
            out.println("<option value=\"DE\">DE</option>");
            out.println("<option value=\"DC\">DC</option>");
            out.println("<option value=\"FL\">FL</option>");
            out.println("<option value=\"GA\">GA</option>");
            out.println("<option value=\"HI\">HI</option>");
            out.println("<option value=\"ID\">ID</option>");
            out.println("<option value=\"IL\">IL</option>");
            out.println("<option value=\"IN\">IN</option>");
            out.println("<option value=\"IA\">IA</option>");
            out.println("<option value=\"KS\">KS</option>");
            out.println("<option value=\"KY\">KY</option>");
            out.println("<option value=\"LA\">LA</option>");
            out.println("<option value=\"ME\">ME</option>");
            out.println("<option value=\"MD\">MD</option>");
            out.println("<option value=\"MA\">MA</option>");
            out.println("<option value=\"MI\">MI</option>");
            out.println("<option value=\"MN\">MN</option>");
            out.println("<option value=\"MS\">MS</option>");
            out.println("<option value=\"MO\">MO</option>");
            out.println("<option value=\"MT\">MT</option>");
            out.println("<option value=\"NE\">NE</option>");
            out.println("<option value=\"NV\">NV</option>");
            out.println("<option value=\"NH\">NH</option>");
            out.println("<option value=\"NJ\">NJ</option>");
            out.println("<option value=\"NM\">NM</option>");
            out.println("<option value=\"NY\">NY</option>");
            out.println("<option value=\"NC\">NC</option>");
            out.println("<option value=\"ND\">ND</option>");
            out.println("<option value=\"OH\">OH</option>");
            out.println("<option value=\"OK\">OK</option>");
            out.println("<option value=\"OR\">OR</option>");
            out.println("<option value=\"PA\">PA</option>");
            out.println("<option value=\"RI\">RI</option>");
            out.println("<option value=\"SC\">SC</option>");
            out.println("<option value=\"SD\">SD</option>");
            out.println("<option value=\"TN\">TN</option>");
            out.println("<option value=\"TX\">TX</option>");
            out.println("<option value=\"UT\">UT</option>");
            out.println("<option value=\"VT\">VT</option>");
            out.println("<option value=\"VA\">VA</option>");
            out.println("<option value=\"WA\">WA</option>");
            out.println("<option value=\"WV\">WV</option>");
            out.println("<option value=\"WI\">WI</option>");
            out.println("<option value=\"WY\">WY</option>");
            out.println("</select>");
            out.println("</div>");
            out.println("<div>");
            out.println("<label for=\"zipcode\">Zip Code</label>");
            out.println("<input type=\"text\" id=\"zipcode\" name=\"zipcode\" />");
            out.println("</div>");
            out.println("</div>");
            out.println("<label for=\"shipping\">Shipping Method</label>");
            out.println("<select id=\"shipping\" name=\"shipping\">");
            out.println("<option value=\"overnight\">Overnight</option>");
            out.println("<option value=\"expedited\">2-days Expedited</option>");
            out.println("<option value=\"ground\">6-days Ground</option>");
            out.println("</select>");
            out.println("<label for=\"ccn\">Credit Card Number</label>");
            out.println("<input type=\"text\" id=\"ccn\" name=\"ccn\">");
            out.println("<div class=\"expDate\">");
            out.println("<label for=\"expdate\">Expiration Date</label>");
            out.println("<select id=\"expmo\" name=\"expmo\">");
            out.println("<option value=\"1\">1</option>");
            out.println("<option value=\"2\">2</option>");
            out.println("<option value=\"3\">3</option>");
            out.println("<option value=\"4\">4</option>");
            out.println("<option value=\"5\">5</option>");
            out.println("<option value=\"6\">6</option>");
            out.println("<option value=\"7\">7</option>");
            out.println("<option value=\"8\">8</option>");
            out.println("<option value=\"9\">9</option>");
            out.println("<option value=\"10\">10</option>");
            out.println("<option value=\"11\">11</option>");
            out.println("<option value=\"12\">12</option>");
            out.println("</select>");
            out.println("<select id=\"expyr\" name=\"expyr\">");
            out.println("<option value=\"2020\">2020</option>");
            out.println("<option value=\"2021\">2021</option>");
            out.println("<option value=\"2022\">2022</option>");
            out.println("<option value=\"2023\">2023</option>");
            out.println("<option value=\"2024\">2024</option>");
            out.println("<option value=\"2025\">2025</option>");
            out.println("<option value=\"2026\">2026</option>");
            out.println("<option value=\"2027\">2027</option>");
            out.println("<option value=\"2028\">2028</option>");
            out.println("<option value=\"2029\">2029</option>");
            out.println("<option value=\"2030\">2030</option>");
            out.println("<option value=\"2031\">2031</option>");
            out.println("</select>");
            out.println("</div>");
            out.println("<label for=\"security\">Security Code</label>");
            out.println("<input type=\"text\" id=\"security\" name=\"security\" placeholder=\"CVV\" />");
            out.println("<label for=\"price\">Price</label>");
            out.println("<input type =\"text\" id=\"price\" name=\"price\" value=$" + total + " readonly/>");
            out.println("<label for=\"tax\">Tax</label>");
            out.println("<input type =\"text\" id=\"tax\" name=\"tax\" readonly/>");
            out.println("<label for = \"total\">Total</label>");
            out.println("<input type =\"text\" id=\"total\" name=\"total\" readonly/>");
            out.println("<input type=\"submit\" value=\"Purchase\" />");
            out.println("</form>");
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
        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            if (validateForm(request) == false) {
                out.println("<script>alert(\"Database processing error\")</script>");
                processRequest(request, response);
            } else {

                // HTTP session based fields
                HttpSession session = request.getSession(true);
                ArrayList<String> pids = (ArrayList<String>) session.getAttribute("cart");
                Hashtable<String, Integer> productQty = new Hashtable<String, Integer>();
                String pids_qts = "";

                // Form based fields
                String firstName = request.getParameter("lname");
                String lastName = request.getParameter("fname");
                String phoneNumber = request.getParameter("phonenum");
                String shippingAddress = request.getParameter("addr");
                int zip = Integer.valueOf(request.getParameter("zipcode"));
                String shipping = request.getParameter("shipping");
                Long ccn = Long.valueOf(request.getParameter("ccn"));
                int expmo = Integer.valueOf(request.getParameter("expmo"));
                int expyr = Integer.valueOf(request.getParameter("expyr"));
                int security = Integer.valueOf(request.getParameter("security"));
                String total_str = request.getParameter("total");
                total_str = total_str.substring(1);
                Order order = new Order();
                Double total = Double.valueOf(total_str);
                String city = request.getParameter("city");
                String state = request.getParameter("state");

                // HTTP based fields
                ClientConfig config = new ClientConfig();
                Client client = ClientBuilder.newClient(config);
                WebTarget target = client.target(getOrdersURI());
                Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
                Response api_respn;

                // Quantify products using hash table
                for (String pid : pids) {
                    if (productQty.containsKey(pid)) {
                        productQty.put(pid, productQty.get(pid) + 1);
                    } else {
                        productQty.put(pid, 1);
                    }
                }

                // Encode pids and their quanity to be used in database
                for (Map.Entry<String, Integer> entry : productQty.entrySet()) {
                    pids_qts += entry.getKey() + ":" + entry.getValue() + ",";
                }
                pids_qts = pids_qts.substring(0, pids_qts.length() - 1); // remove last comma

                // Set Order fields
                order.setFirstName(firstName);
                order.setLastName(lastName);
                order.setPhoneNumber(phoneNumber);
                order.setShippingAddress(shippingAddress);
                order.setZipCode(zip);
                order.setShippingMethod(shipping);
                order.setCreditCard(ccn);
                order.setExpMonth(expmo);
                order.setExpYear(expyr);
                order.setSecurityCode(security);
                order.setPriceTotal(total);
                order.setPids(pids_qts);
                order.setCity(city);
                order.setState(state);

                api_respn = invocationBuilder.post(Entity.entity(order, MediaType.APPLICATION_JSON));

                if (api_respn.hasEntity()) {
                    String oid = api_respn.readEntity(String.class);
                    session.setAttribute("cart", null);
                    RequestDispatcher rd = request.getRequestDispatcher("/Confirmation?oid=" + oid);
                    rd.forward(request, response);
                } else {
                    out.println("<h1>Databse Processing Error</h1>");
                }
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

    private boolean validateForm(HttpServletRequest request) {
        String firstName = request.getParameter("lname");
        String lastName = request.getParameter("fname");
        String phoneNumber = request.getParameter("phonenum");
        String shippingAddress = request.getParameter("addr");
        String zip = request.getParameter("zipcode");
        String shipping = request.getParameter("shipping");
        String ccn = request.getParameter("ccn");
        String expmo = request.getParameter("expmo");
        String expyr = request.getParameter("expyr");
        String security = request.getParameter("security");
        String total = request.getParameter("total");
        total = total.substring(1);

        String regex = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(phoneNumber);

        if (firstName == null || firstName.equals("")) {
            return false;
        } else if (lastName == null || lastName.equals("")) {
            return false;
        } else if (phoneNumber == null || !match.matches()) {
            return false;
        } else if (shippingAddress == null) {
            return false;
        } else if (zip == null || zip.length() != 5 || !isNumeric(zip)) {
            return false;
        } else if (shipping == null) {
            return false;
        } else if (ccn == null || ccn.length() != 16 || !isNumeric(ccn)) {
            return false;
        } else if (expmo == null || !isNumeric(expmo)) {
            return false;
        } else if (expyr == null || !isNumeric(expyr)) {
            return false;
        } else if (security == null || !isNumeric(security) || security.length() != 3) {
            return false;
        } else if (total == null) {
            return false;
        }
        return true;
    }

    public boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static URI getOrdersURI() {
        return UriBuilder.fromUri("http://localhost:8080/REST-Server/v1/api/orders").build();
    }

    private static URI getProductsURI() {
        return UriBuilder.fromUri("http://localhost:8080/REST-Server/v1/api/products").build();
    }
}
