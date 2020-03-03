package spring.city.app.starter;

import java.util.List;
import java.util.ArrayList;
//import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
	@Autowired
	private CityRepository cityRepository;
	
	public List<City> getAllCities() {
		List<City> cities=new ArrayList<>();
		 cityRepository.findAll().forEach(cities::add);
		 return cities;
	} 
	public City getCities(String id) {
		return cityRepository.findById(id).get();
	
	}
	public void addCities(City newCity) {
		cityRepository.save(newCity);

		
	}
	public void updateCities(String id, City newCity) {
		cityRepository.save(newCity);
	}
	public void deleteCities(String id) {
		cityRepository.deleteById(id);
		

	}
}
