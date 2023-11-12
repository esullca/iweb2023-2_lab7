package com.example.webapphr1_2023.Controllers;

import com.example.webapphr1_2023.Beans.Department;
import com.example.webapphr1_2023.Beans.Employee;
import com.example.webapphr1_2023.Beans.Job;
import com.example.webapphr1_2023.Beans.Location;
import com.example.webapphr1_2023.Daos.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

@WebServlet(name = "LocationServlet", urlPatterns = {"/LocationServlet"})
public class LocationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action") == null ? "lista" : req.getParameter("action");

        RequestDispatcher view;

        LocationDao locationDao = new LocationDao();
        CountryDao countryDao = new CountryDao();


        switch (action) {
            case "lista":
                req.setAttribute("locationList", locationDao.listarLocations());
                view = req.getRequestDispatcher("location/list.jsp");
                view.forward(req, resp);;
                break;


            case "editar":
                if (req.getParameter("id") != null) {
                    String locationIdString = req.getParameter("id");
                    int locationId = 0;
                    try {
                        locationId = Integer.parseInt(locationIdString);
                    } catch (NumberFormatException ex) {
                        resp.sendRedirect("LocationServlet");

                    }

                    Location loc = locationDao.obtenerLocation(locationId);

                    if (loc != null) {
                        req.setAttribute("lccation", loc);
                        req.setAttribute("listaTrabajos",countryDao.obtenerListaPaises());

                        view = req.getRequestDispatcher("location/formularioEditar.jsp");
                        view.forward(req, resp);
                    } else {
                        resp.sendRedirect("LocationServlet");
                    }

                } else {
                    resp.sendRedirect("LocationServlet");
                }

                break;
            case "borrar":
                if (req.getParameter("id") != null) {
                    String locationIdString = req.getParameter("id");
                    int locationId = 0;
                    try {
                        locationId = Integer.parseInt(locationIdString);
                    } catch (NumberFormatException ex) {
                        resp.sendRedirect("LocationServlet");
                    }

                    Location loc = locationDao.obtenerLocation(locationId);

                    if (loc != null) {
                        locationDao.borrarEmpleado(locationId);
                    }
                }

                resp.sendRedirect("LocationServlet");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);



    }
}