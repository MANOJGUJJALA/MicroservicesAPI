package com.myproject.demoproject.repositories;

import com.myproject.demoproject.beans.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Integer> {


}
