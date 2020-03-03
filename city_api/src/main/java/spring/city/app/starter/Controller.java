package spring.city.app.starter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class Controller {
	
	@Autowired
	private CityService cityService;
	
	private static final Logger log = LoggerFactory.getLogger(Controller.class);

	
	@RequestMapping("/")
	public String greet() {
		return "Welcome!";
	}
	@RequestMapping("/ping")
	public String ping() {
		return "Pong!";
	}
	@RequestMapping("/hello")
	public String sayHi() {
		return "Hi user!";
	}
	@RequestMapping("/cities")
	@Cacheable("city")
   	public List<City> getAllCities(){
		log.info("Database hit");
		return cityService.getAllCities();
	}
	
	@RequestMapping("/cities/{id}")
	@Cacheable("city")
	public City getCities(@PathVariable String id) {
		log.info("Database hit");
		return cityService.getCities(id);
	}
	@RequestMapping(method=RequestMethod.POST,value = "/cities")
	public void addCity(@RequestBody City newCity) {
		cityService.addCities(newCity);	
	}
	@RequestMapping(method=RequestMethod.PUT,value = "/cities/{id}")
	public void updateCity(@PathVariable String id,@RequestBody City newCity) {
		cityService.updateCities(id,newCity);
	}

	@RequestMapping(method=RequestMethod.DELETE,value = "/cities/{id}")
	public void deleteCity(@PathVariable String id) {
		cityService.deleteCities(id);
	}
	@RequestMapping("/clearcache")
	@CacheEvict(value="city", allEntries=true)
	public  String ClearCache()
	{
		log.info("Cache cleared");
		return "cache is cleared!";	}
}
