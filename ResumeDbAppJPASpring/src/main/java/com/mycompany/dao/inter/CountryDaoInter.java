package com.mycompany.dao.inter;

import com.mycompany.entity.Country;


import java.util.List;

public interface CountryDaoInter {
    public List<Country> getAllCountry() throws Exception;
    public List<Country> getAllNationality() throws Exception;
    public Country getCountryByString(String countryName) throws Exception;
    public Country getNationalityByString(String nationality) throws Exception;
}
