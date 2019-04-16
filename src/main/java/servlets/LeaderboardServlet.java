package servlets;

import lombok.extern.java.Log;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Log
@WebServlet(name = "LeaderboardServlet", urlPatterns = "/leaderboard")
public class LeaderboardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        return;

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        File fXmlFile = new File("D:\\Temp\\Studia\\javaprojectwww\\src\\main\\resources\\leaderboard.xml");
//        File fXmlFile = new File("/home/exomat/Pulpit/javaproject/src/main/resources/leaderboard.xml");
//        File fXmlFile = new File("D:\\Temp\\Studia\\javaprojectwww\\src\\main\\resources\\leaderboard.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        List<Vector> leaderboardUsers = new ArrayList<>();
        HttpSession session = request.getSession();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("user");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Vector user = new Vector();
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    user.add(element.getElementsByTagName("name").item(0).getTextContent());
                    user.add(element.getElementsByTagName("points").item(0).getTextContent());
                }
                leaderboardUsers.add(user);
            }
            ServletContext sc = this.getServletContext();
            leaderboardUsers.sort(Comparator.comparingInt(o -> Integer.parseInt((String) o.get(1))));
            Collections.reverse(leaderboardUsers);
            synchronized (getServletContext()) {
                sc.setAttribute("leaderboardList", leaderboardUsers);
            }
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            Element rootElement = (Element) doc.getElementsByTagName(
                    "leaderboard").item(0);
            Element newUser = doc.createElement("user");
            Element newUserName = doc.createElement("name");
            Element newUserPoints = doc.createElement("points");
            newUserName.appendChild(doc.createTextNode((String) session.getAttribute("login")));
            newUserPoints.appendChild(doc.createTextNode((String) session.getAttribute("points")));
            newUser.appendChild(newUserName);
            newUser.appendChild(newUserPoints);
            rootElement.appendChild(newUser);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fXmlFile.getAbsolutePath()));
            transformer.transform(source, result);

            Vector newUserVector = new Vector();
            newUserVector.add(session.getAttribute("login"));
            newUserVector.add((int) session.getAttribute("points"));
            ServletContext sc = this.getServletContext();
            leaderboardUsers.add(newUserVector);
            leaderboardUsers.sort(Comparator.comparingInt(o -> Integer.parseInt((String) o.get(1))));
            Collections.reverse(leaderboardUsers);
            synchronized (getServletContext()) {
                sc.setAttribute("leaderboardList", leaderboardUsers);
            }
        } catch (ParserConfigurationException | SAXException | TransformerException e) {
            e.printStackTrace();
        }

        session.setAttribute("points", 0);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("leaderboard");
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        return;

    }
}
