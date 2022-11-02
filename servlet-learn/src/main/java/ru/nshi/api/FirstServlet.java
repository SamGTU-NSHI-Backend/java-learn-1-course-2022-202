package ru.nshi.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class FirstServlet extends HttpServlet {
    public static final String JSON_VALUE = "application/json";
    private ObjectMapper mapper;

    @Override
    public void destroy() {
        System.out.println("Destroying servlet");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("Initializing servlet");
        mapper = new ObjectMapper();
    }

    //host/path?par1=val1&par2=var2&par2=var3
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("req.getParameter(\"par1\") = " + req.getParameter("par1"));
        System.out.println("req.getParameterValues(\"par2\") = " + Arrays.toString(req.getParameterValues("par2")));
        System.out.println("req.getServletPath() = " + req.getServletPath());
        System.out.println("req.getPathInfo() = " + req.getPathInfo());

        resp.getWriter().println("<h1>Hello first servlet!</h1>");
        resp.setContentType("text/plane");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(JSON_VALUE);
        if (!req.getContentType().contains(JSON_VALUE)) {
            resp.setStatus(400);
            mapper.writeValue(resp.getWriter(), Map.of("error", "Expected " + JSON_VALUE));
            return;
        }

        Message value = mapper.readValue(req.getInputStream(), Message.class);

        if (value == null || value.getValue() == null) {
            resp.setStatus(400);
            mapper.writeValue(resp.getWriter(), Map.of("error", "message is null"));
            return;
        }

        String message = value.getValue();
        mapper.writeValue(resp.getOutputStream(), new Message(message.toUpperCase()));
        resp.setStatus(200);
    }
}
