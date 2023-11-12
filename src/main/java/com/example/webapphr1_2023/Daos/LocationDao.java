package com.example.webapphr1_2023.Daos;

import com.example.webapphr1_2023.Beans.Country;
import com.example.webapphr1_2023.Beans.Department;
import com.example.webapphr1_2023.Beans.Employee;
import com.example.webapphr1_2023.Beans.Location;

import java.sql.*;
import java.util.ArrayList;

public class LocationDao extends DaoBase {
    public ArrayList<Location> listarLocations() {

        ArrayList<Location> listaLocation = new ArrayList<>();

        String sql = "select * from hr.locations l\n"+
                    "inner join countries c on l.country_id = c.country_id";



        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Location location = fetchLocationData(rs);
                listaLocation.add(location);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaLocation;
    }

    private Location fetchLocationData(ResultSet rs) throws SQLException {
        Location location = new Location();
        location.setLocationId(rs.getInt(1));
        location.setStreetAddress(rs.getString(2));
        location.setPostalCode(rs.getString(3));
        location.setCity(rs.getString(4));
        location.setStateProvince(rs.getString(5));


        Country country = new Country();
        country.setCountryId(rs.getString("c.country_id"));
        country.setCountryName(rs.getString("country_name"));
        country.setRegionId(rs.getBigDecimal("region_name"));
        location.setCountry(country);

        return location;
    }

    public Location obtenerLocation(int lcoationId) {

        Location location = null;

        String sql = "select * from hr.locations l\n"+
                "inner join countries c on l.country_id = c.country_id\n" +
                "where e.employee_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, lcoationId);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    location = fetchLocationData(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return location;
    }


    

}
