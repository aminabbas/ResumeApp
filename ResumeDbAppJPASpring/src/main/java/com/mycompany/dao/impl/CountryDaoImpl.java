package com.mycompany.dao.impl;

import com.mycompany.dao.inter.AbstractDao;
import com.mycompany.dao.inter.CountryDaoInter;
import com.mycompany.entity.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl extends AbstractDao implements CountryDaoInter {

    @Override
    public List<Country> getAllCountry() throws Exception {
        List<Country> result = new ArrayList<>();
        try (Connection c = connect()) {
            Statement st = c.createStatement();
            st.execute("SELECT * from country");
            ResultSet rs = st.getResultSet();

            while (rs.next()) {
                Country ct = getCountry(rs);
                result.add(ct);
            }
        }
        return result;

    }

    public Country getCountryByString(String countryName) throws Exception {
        try (Connection c = connect()) {
            PreparedStatement pps = c.prepareStatement("select id, name from country where name = ?");
            pps.setString(1, countryName);
            pps.execute();
            ResultSet rs = pps.getResultSet();
            while (rs.next()) {
                return getCountry(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }


    private Country getCountry(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        return new Country(id, name, null);
    }

    @Override
    public List<Country> getAllNationality() throws Exception {
        List<Country> result = new ArrayList<>();
        try (Connection c = connect()) {
            Statement st = c.createStatement();
            st.execute("Select * from country");
            ResultSet rs = st.getResultSet();

            while (rs.next()) {
                Country ct = getNationality(rs);
                result.add(ct);
            }
        }
        return result;
    }

    private Country getNationality(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nationality = rs.getString("nationality");

        return new Country(id, null, nationality);
    }

    @Override
    public Country getNationalityByString(String nationality) throws Exception {
        try (Connection c = connect()) {
            PreparedStatement pps = c.prepareStatement("select id,nationality from country where nationality = ?");
            pps.setString(1, nationality);
            pps.execute();
            ResultSet rs = pps.getResultSet();
            while (rs.next()) {
                return getNationality(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
