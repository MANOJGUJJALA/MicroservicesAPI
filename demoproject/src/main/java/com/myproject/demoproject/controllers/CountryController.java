package com.myproject.demoproject.controllers;

import com.myproject.demoproject.beans.Country;
import com.myproject.demoproject.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {
        @Autowired
        CountryService countryservice;

        @GetMapping("/getcountries")

        public List<Country> getAllcountries(){

        return countryservice.getAllCountries();
        }

        @GetMapping("/getcountries/{id}")
    public ResponseEntity<Country> getAllcountrybyid(@PathVariable(value = "id")int id){

            try {
                Country country=countryservice.getCountryById(id);
                return new ResponseEntity<Country>(country,HttpStatus.OK);
            }
            catch (Exception e){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }

    @GetMapping("/getcountries/countryname")
    public ResponseEntity<Country> getCountryByName(@RequestParam(value = "name")String name){
        try {
            Country country=countryservice.getCountryByName(name);
            return new ResponseEntity<Country>(country,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

//            return countryservice.getCountryByName(name);
    }

    @PostMapping("/addcountry")
    public Country addCountry(@RequestBody Country country){

            return countryservice.addCountry(country);
    }

    @PutMapping("/updatecountry/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable(value = "id")int id, @RequestBody Country country){
        try{

            Country exsistCountry=countryservice.getCountryById(id);
            exsistCountry.setCountryName(country.getCountryName());
            exsistCountry.setCountryCapital(country.getCountryCapital());
            Country updatedCountry= countryservice.updateCountry(exsistCountry);
            return new ResponseEntity<Country>(updatedCountry,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping("/deletecountry/{id}")
    public AddResponse deleteCountry(@PathVariable(value = "id")int id){

            return countryservice.deleteCountry(id);

    }



}
