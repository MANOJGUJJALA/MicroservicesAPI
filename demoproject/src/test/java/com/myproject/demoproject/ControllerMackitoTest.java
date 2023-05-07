package com.myproject.demoproject;

import com.myproject.demoproject.beans.Country;
import com.myproject.demoproject.controllers.AddResponse;
import com.myproject.demoproject.controllers.CountryController;
import com.myproject.demoproject.services.CountryService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {ControllerMackitoTest.class})
public class ControllerMackitoTest {

    @Mock
    CountryService countryService;
    @InjectMocks
    CountryController countryController;

    List <Country> mycountries;
    Country country;

    @Test
    @Order(1)
    public void testgetAllCountries(){

        List<Country> mycountries=new ArrayList<Country>();
        mycountries.add(new Country(1,"India","delhi"));
        mycountries.add(new Country(2,"us","texas"));
        mycountries.add(new Country(3,"Uk","London"));

        when(countryService.getAllCountries()).thenReturn(mycountries);

        assertEquals(3,  countryController.getAllcountries().size());

    }

    @Test
    @Order(2)
    public void testgetcountrybyId(){

        Country country=new Country(1,"India","delhi");
        int id=1;
        when(countryService.getCountryById(id)).thenReturn(country);

        ResponseEntity <Country>res=countryController.getAllcountrybyid(id);
        assertEquals(HttpStatus.OK,res.getStatusCode());
        assertEquals(country.getCountryName(),res.getBody().getCountryName());
    }
    @Test
    @Order(3)

    public void testgetcountrybyName(){
            Country country=new Country(1,"India","delhi");
            String name="India";
            when(countryService.getCountryByName(name)).thenReturn(country);
            ResponseEntity<Country>res= countryController.getCountryByName(name);

            assertEquals(HttpStatus.FOUND,res.getStatusCode());
            assertEquals(country.getId(),res.getBody().getId());
    }

    @Test
    @Order(4)
    public void testaddcountry(){
        Country country=new Country(1,"India","delhi");
        when(countryService.addCountry(country)).thenReturn(country);
        assertEquals(country,countryController.addCountry(country));
    }

    @Test
    @Order(5)
    public void testupdatecountry(){
        int id=1;
        Country country=new Country(1,"India","delhi");
        when(countryService.getCountryById(id)).thenReturn(country);
        when(countryService.updateCountry(country)).thenReturn(country);
        ResponseEntity<Country>res= countryController.updateCountry(id,country);

        assertEquals(HttpStatus.OK,res.getStatusCode());
        assertEquals(country.getCountryCapital(),res.getBody().getCountryCapital());
    }

    @Test
    @Order(6)

    public void testdeleteCountry(){

        int id=1;
        Country country=new Country(1,"India","delhi");

//        doNothing().when(countryService).deleteCountry(id);

        countryController.deleteCountry(id);

        verify(countryService, times(1)).deleteCountry(id);
//        verify(countryService,times(1)).deleteCountry(id);
//         when( countryService.deleteCountry(id));
//         ResponseEntity response=countryController.deleteCountry(id);
//         assertEquals(HttpStatus.OK,response.getStatusCode());

//        assertEquals("Country Deleted..",response.getBody().equals("Country Deleted.."));
    }
}
