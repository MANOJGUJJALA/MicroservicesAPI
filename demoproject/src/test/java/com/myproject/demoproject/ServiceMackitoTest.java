package com.myproject.demoproject;

import com.myproject.demoproject.beans.Country;
import com.myproject.demoproject.controllers.AddResponse;
import com.myproject.demoproject.repositories.CountryRepository;
import com.myproject.demoproject.services.CountryService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {ServiceMackitoTest.class})
public class ServiceMackitoTest {

    @Mock
    CountryRepository countryRepository;

    public List<Country>mycountries;

    public Country con;

    @InjectMocks
    CountryService countryService;
    
    @Test
    @Order(2)
    public void testgetAllCountries(){

        List<Country> mycountries=new ArrayList<Country>();
        mycountries.add(new Country(1,"India","delhi"));
        mycountries.add(new Country(2,"us","texas"));
        mycountries.add(new Country(3,"Uk","London"));

        when(countryRepository.findAll()).thenReturn(mycountries);

         assertEquals(3,  countryService.getAllCountries().size());
    }

    @Test
    @Order(1)
    public void testgetCountryById(){

        Country con=  new Country(1,"india","delhi");
        int countryid=1;
        when(countryRepository.findById(countryid)).thenReturn(Optional.of(con));

        assertEquals( 1,countryService.getCountryById(countryid).getId());
    }

    @Test
    @Order(3)
    public  void testcountrybyName(){
        List <Country> mycountries= new ArrayList<Country>();
        mycountries.add(new Country(1,"India","delhi"));
        mycountries.add(new Country(2,"us","texas"));
        mycountries.add(new Country(3,"Uk","London"));
        String name="India";

        when(countryRepository.findAll()).thenReturn(mycountries);

        assertEquals("India", countryService.getCountryByName(name).getCountryName());
    }
    @Test
    @Order(4)
     public void testaddCountry(){

        Country con=new Country(4,"Nepal","kathmandu");

        when(countryRepository.save(con)).thenReturn(con);

        assertEquals(con.getCountryCapital(),countryService.addCountry(con).getCountryCapital());

     }
    @Test
    @Order(5)
    public void testupdateCountry(){

        Country con=new Country(3,"Nepal","kathmandu");

        when(countryRepository.save(con)).thenReturn(con);

        assertEquals(con.getCountryCapital(),countryService.updateCountry(con).getCountryCapital());
    }

    @Test
    @Order(6)
    public void testdeleteCountry(){

        int id=1;

        assertEquals( "Country Deleted..",countryService.deleteCountry(id).getMsg());
        verify(countryRepository,times(1)).deleteById(id);

    }
}
