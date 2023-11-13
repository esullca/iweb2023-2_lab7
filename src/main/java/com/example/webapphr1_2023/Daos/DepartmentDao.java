package com.example.webapphr1_2023.Daos;


import com.example.webapphr1_2023.Beans.Department;
import com.example.webapphr1_2023.Beans.Employee;
import com.example.webapphr1_2023.Beans.Location;

import java.sql.*;
import java.util.ArrayList;

public class DepartmentDao extends DaoBase {

    public ArrayList<Department> listarDepartamentos() {

        ArrayList<Department> listaDepartamentos = new ArrayList<>();

        String sql = "select * from hr.departments d\n" +
                "left join hr.employees m on d.manager_id = m.employee_id\n" +
                "left join hr.locations l  on d.location_id = l.location_id order by d.department_id";


        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Department departmento = fetchDepartmentData(rs);
                listaDepartamentos.add(departmento);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return listaDepartamentos;
    }

    public Department obtenerDepartamento(int departmentId) {

        Department departamento = null;

        String sql = "select * from departments d\n" +
                "left join employees m on d.manager_id = m.employee_id\n" +
                "left join locations l on d.location_id = l.location_id\n" +
                "where d.department_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, departmentId);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    departamento = fetchDepartmentData(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return departamento;
    }


    public void guardarDepartamento(Department department) {

        String sql = "INSERT INTO employees (departmentName, manager_id, location_id) "
                + "VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setDepartmentData(department, pstmt);

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    public void actualizarDepartamento(Department departmento) {

        String sql = "UPDATE departments "
                + "SET department_name = ?, "
                + "SET manager_id = ?"
                + "SET location_id = ?"
                + "WHERE department_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setDepartmentData(departmento, pstmt);
            pstmt.setInt(4, departmento.getDepartmentId());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    private Department fetchDepartmentData(ResultSet rs) throws SQLException{

        Department departamento=new Department();
        departamento.setDepartmentId(rs.getInt(1));
        departamento.setDepartmentName(rs.getString(2));


        Employee manager = null;
        if(rs.getInt("m.employee_id") != 0) {
            manager = new Employee();
            manager.setEmployeeId(rs.getInt("m.employee_id"));
            manager.setFirstName(rs.getString("m.first_name"));
            manager.setLastName(rs.getString("m.last_name"));
            departamento.setManager(manager);
        }

        Location locacion=new Location();
        locacion.setLocationId(rs.getInt("location_id"));
        locacion.setStreetAddress(rs.getString("street_address"));
        departamento.setLocation(locacion);






        return departamento;
    }


    public void borrarDepartamento(int departmentId) {

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM departments WHERE department_id = ?")) {

            pstmt.setInt(1, departmentId);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void setDepartmentData(Department departamento, PreparedStatement pstmt) throws SQLException {

        pstmt.setString(1, departamento.getDepartmentName());



        if (departamento.getManager().getEmployeeId() == 0) {
            pstmt.setNull(2, Types.INTEGER);
        } else {
            pstmt.setInt(2, departamento.getManager().getEmployeeId());
        }

        if (departamento.getLocation().getLocationId() == 0) {
            pstmt.setNull(3, Types.INTEGER);
        } else {
            pstmt.setInt(3, departamento.getLocation().getLocationId());
        }
    }




}