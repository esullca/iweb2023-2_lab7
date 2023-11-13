package com.example.webapphr1_2023.Controllers;

import com.example.webapphr1_2023.Beans.*;
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
            case "formCrear":
                req.setAttribute("listaPaises",countryDao.obtenerListaPaises());

                view = req.getRequestDispatcher("location/formularioNuevo.jsp");
                view.forward(req, resp);
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
                        req.setAttribute("location", loc);
                        req.setAttribute("listaPaises",countryDao.obtenerListaPaises());

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
                        locationDao.borrarLocation(locationId);
                    }
                }

                resp.sendRedirect("LocationServlet");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        String action = req.getParameter("action") == null ? "lista" : req.getParameter("action");

        LocationDao locationDao = new LocationDao();

        Location location = new Location();
        location.setStreetAddress(req.getParameter("street_address"));
        location.setPostalCode(req.getParameter("postal_code"));
        location.setCity(req.getParameter("city"));
        location.setStateProvince(req.getParameter("state_province"));


        Country country = new Country();
        country.setCountryId(req.getParameter("country_id"));
        location.setCountry(country);





        switch (action) {
            case "guardar":
                int employ = locationDao.searchNextId();
                location.setLocationId(employ);
                locationDao.guardarLocation(location);

                resp.sendRedirect("LocationServlet");
                break;
            case "actualizar":
                location.setLocationId(Integer.parseInt(req.getParameter("location_id"))); //no olvidar que para actualizar se debe enviar el ID

                locationDao.actualizarLocation(location);

                resp.sendRedirect("LocationServlet");

                break;
        }


    }
}