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

        String sql = "select * from locations l\n"+
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
        country.setRegionId(rs.getBigDecimal("region_id"));
        location.setCountry(country);

        return location;
    }

    public Location obtenerLocation(int lcoationId) {

        Location location = null;

        String sql = "select * from hr.locations l\n"+
                "inner join countries c on l.country_id = c.country_id\n" +
                "where l.location_id = ?";

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


    public void guardarLocation(Location location) {

        String sql = "INSERT INTO locations (street_address, postal_code, city, state_province,country_id,location_id) "
                + "VALUES (?,?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setLocationData(location, pstmt);
            pstmt.setInt(6, location.getLocationId());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void actualizarLocation(Location location) {

        String sql = "UPDATE locations "
                + "SET street_address = ?, "
                + "postal_code = ?, "
                + "city = ?, "
                + "state_province = ?, "
                + "country_id = ? "
                + "WHERE location_id = ?";;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            System.out.println("hola2");
            setLocationData(location, pstmt);
            pstmt.setInt(6, location.getLocationId());
            System.out.println("hola2");
            pstmt.executeUpdate();
            System.out.println("hola3");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void borrarLocation(int locationId) {

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM locations WHERE location_id = ?")

        ) {

            pstmt.setInt(1, locationId);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
        private void setLocationData (Location location, PreparedStatement pstmt) throws SQLException {

            pstmt.setString(1, location.getStreetAddress());
            pstmt.setString(2, location.getPostalCode());
            pstmt.setString(3, location.getCity());
            pstmt.setString(4, location.getStateProvince());
            if ("0".equalsIgnoreCase(location.getCountry().getCountryId())){
                pstmt.setNull(5,Types.CHAR);
            }else{
                pstmt.setString(5, location.getCountry().getCountryId());
            };



        }
    public int searchNextId() {
        int lastId = 0;
        String sql = "SELECT MAX(location_id) as last_id FROM locations";
        try (Connection conn = getConnection();
             Statement statement  = conn.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                lastId = resultSet.getInt("last_id");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(lastId);
        return lastId+100;

    }
    }

