package com.example.webapphr1_2023.Daos;

import com.example.webapphr1_2023.Beans.Country;
import com.example.webapphr1_2023.Beans.Job;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CountryDao extends DaoBase {
    public ArrayList<Country> obtenerListaPaises() {
        ArrayList<Country> listaPaises = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM countries");) {

            while (rs.next()) {
                Country country = new Country();

                country.setCountryId(rs.getString(1));
                country.setCountryName(rs.getString(2));
                country.setRegionId(rs.getBigDecimal(3));


                listaPaises.add(country);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaPaises;

    }
}
