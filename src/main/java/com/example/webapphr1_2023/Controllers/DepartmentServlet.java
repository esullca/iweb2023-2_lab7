package com.example.webapphr1_2023.Controllers;

import com.example.webapphr1_2023.Beans.Department;
import com.example.webapphr1_2023.Beans.Employee;
import com.example.webapphr1_2023.Beans.Job;
import com.example.webapphr1_2023.Beans.Location;
import com.example.webapphr1_2023.Daos.DepartmentDao;
import com.example.webapphr1_2023.Daos.EmployeeDao;
import com.example.webapphr1_2023.Daos.JobDao;
import com.example.webapphr1_2023.Daos.LocationDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "DepartmentServlet", urlPatterns = {"/DepartmentServlet"})
public class DepartmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String action = req.getParameter("action") == null ? "lista" : req.getParameter("action");


        RequestDispatcher view;
        DepartmentDao departmentDao = new DepartmentDao();
        EmployeeDao employeeDao = new EmployeeDao();
        LocationDao locationDao = new LocationDao();

        switch (action) {
            case "lista":
                req.setAttribute("departmentList", departmentDao.listarDepartamentos());
                view = req.getRequestDispatcher("department/list.jsp");
                view.forward(req, response);
                break;

            case "agregar":
                req.setAttribute("listaJefes",employeeDao.listarEmpleados());
                req.setAttribute("listaLocaciones",departmentDao.listarDepartamentos());
                view = req.getRequestDispatcher("department/formularioNuevo.jsp");
                view.forward(req, response);
                break;

            case "editar":
                if (req.getParameter("id") != null) {
                    String departmentIdString = req.getParameter("id");
                    int departmentId = 0;
                    try {
                        departmentId = Integer.parseInt(departmentIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("DepartmentServlet");

                    }

                    Department departmento = departmentDao.obtenerDepartamento(departmentId);

                    if (departmento != null) {
                        req.setAttribute("departamento", departmento);
                        req.setAttribute("listaJefes",employeeDao.listarEmpleados());
                        req.setAttribute("listaLocaciones",locationDao.listarLocations());
                        view = req.getRequestDispatcher("department/formularioEditar.jsp");
                        view.forward(req, response);
                    } else {
                        response.sendRedirect("DepartmentServlet");
                    }

                } else {
                    response.sendRedirect("DepartmentServlet");
                }

                break;


            case "borrar":
                if (req.getParameter("id") != null) {
                    String departamentoIdString = req.getParameter("id");
                    int departmentId = 0;
                    try {
                        departmentId = Integer.parseInt(departamentoIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("DepartmentServlet");
                    }

                    Department department = departmentDao.obtenerDepartamento(departmentId);

                    if (department != null) {
                        departmentDao.borrarDepartamento(departmentId);
                    }
                }

                response.sendRedirect("DepartmentServlet");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        DepartmentDao departmentDao = new DepartmentDao();

        Department departamento = new Department();
        departamento.setDepartmentName(request.getParameter("departmentName"));

        Employee manager = new Employee();
        manager.setEmployeeId(Integer.parseInt(request.getParameter("manager_id")));
        departamento.setManager(manager);



        Location location = new Location();
        location.setLocationId(Integer.parseInt(request.getParameter("location_id")));
        departamento.setLocation(location);

        switch (action) {
            case "guardar":
                departmentDao.guardarDepartamento(departamento);

                response.sendRedirect("DepartmentServlet");
                break;


            case "actualizar":

                departamento.setDepartmentId(Integer.parseInt(request.getParameter("departmentId"))); //no olvidar que para actualizar se debe enviar el ID

                departmentDao.actualizarDepartamento(departamento);

                response.sendRedirect("DepartmentServlet");

                break;
        }




        super.doPost(request, response);
    }
}
