package com.myproject.demoproject.services;

import com.myproject.demoproject.beans.Country;
import com.myproject.demoproject.controllers.AddResponse;
import com.myproject.demoproject.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Component
@Service
public class CountryService {

//    static HashMap <Integer, Country>countryIdMap;

//    public CountryService(){
//        countryIdMap =new HashMap<Integer,Country>();
//
//        Country india=new Country(1,"india","delhi");
//        Country usa=new Country(2,"usa","texas");
//        Country uk=new Country(3,"uk","london");
//
//        countryIdMap.put(1,india);
//        countryIdMap.put(2,usa);
//        countryIdMap.put(3,uk);
//
//    }

    @Autowired
    CountryRepository countryRepository;
    public List<Country> getAllCountries(){
//        List countries=new ArrayList(countryIdMap.values());
//        return  countries;
        return countryRepository.findAll();
    }
    public Country getCountryById(int id){
//        Country country= countryIdMap.get(id);
//        return  country;
        return countryRepository.findById(id).get();
    }

    public  Country getCountryByName(String name){

        List <Country> countries=countryRepository.findAll();
        Country country=null;
        for (Country con :countries){
            if (con.getCountryName().equalsIgnoreCase(name)){
                country=con;
            }
        }
        return  country;
//        for(int i:countryIdMap.keySet()){
//            if (countryIdMap.get(i).getCountryName().equals(name)){
//                country=countryIdMap.get(i);
//            }
//        }
    }

    public Country addCountry(Country country){

        country.setId(getmaxId());
        countryRepository.save(country);
//        countryIdMap.put(country.getId(),country);
        return  country;
    }
    public int getmaxId(){

        return countryRepository.findAll().size()+1;
//        int max=0;
//        for(int i:countryIdMap.keySet()){
//            if (i>=max){
//                max=i;
//            }
//        }
//        return  max+1;
    }
    public Country updateCountry(Country country){

        countryRepository.save(country);

//        if (country.getId()>0){
//            countryIdMap.put(country.getId(),country);
//        }
        return  country;
    }

    public AddResponse deleteCountry(int id){

        countryRepository.deleteById(id);
//        countryIdMap.remove(id);
//
        AddResponse addResponse=new AddResponse();
        addResponse.setId(id);
        addResponse.setMsg("Country Deleted..");
//
        return addResponse;
    }


}
