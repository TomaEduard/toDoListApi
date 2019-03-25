package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.domain.ToDoItem;
import org.fasttrackit.service.ToDoItemService;
import org.fasttrackit.transfer.SaveToDoItemRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// servelul are rolul de a prelua datele si de a le trimite mai departe

// sau controller
@WebServlet(urlPatterns = "/to-do-items")
public class ToDoItemServlet extends HttpServlet {

    private ToDoItemService toDoItemService = new ToDoItemService();

    // Create
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);

        ObjectMapper objectMapper = new ObjectMapper();
        SaveToDoItemRequest saveToDoItemRequest = objectMapper.readValue(req.getReader(), SaveToDoItemRequest.class);
        try {
            toDoItemService.createToDoItem(saveToDoItemRequest);
        } catch (Exception e) {
            resp.sendError(500, "Internal error: " + e.getMessage());
        }
    }

    // Read
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAccessControlHeaders(resp);

        try {
            List<ToDoItem> toDoItems = toDoItemService.getToDoItems();

            ObjectMapper objectMapper = new ObjectMapper();
//            Cand transformi un obiect in string se numeste serializare(serializing/marshalling)
//            objectMapper.writeValueAsString(topWinners);
            String responseJson = objectMapper.writeValueAsString(toDoItems);

//            content type or mime type
            resp.setContentType("application/json");

            resp.getWriter().print(responseJson);
//            Pentru a anunta pe writer ca am terminat treaba cu el
            resp.getWriter().flush();

        } catch (Exception e) {
            resp.sendError(500, "There was an error processing your requet.");
            e.getMessage();
        }
    }


    //for Preflight request
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }

}
