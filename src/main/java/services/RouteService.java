package services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Activity;
import domain.Category;
import domain.Customer;
import domain.Route;
import forms.TripForm;
import repositories.RouteRepository;

@Service
@Transactional
public class RouteService {

	public RouteService() {
		super();
	}

	// ========== Managed Repository =================

	@Autowired
	private RouteRepository routeRepository;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CustomerService customerService;

	// ========== Supporting services ================

	// ========== Simple CRUD Methods ================

	public Route create() {

		Route result;

		result = new Route();

		return result;
	}

	public Collection<Route> findAll() {

		Collection<Route> result;

		result = routeRepository.findAll();

		return result;
	}

	public Route findOne(int routeId) {
		Assert.notNull(routeId);

		Route result;

		result = routeRepository.findOne(routeId);

		return result;
	}

	public void save(Route route) {
		Assert.notNull(route);
		Customer customer;

		customer = customerService.findByPrincipal();
		route.getCustomers().add(customer);
		
		if(route.getPrice() == null){
			route.setPrice(0.);
		}
		System.out.println("pero entra en el save");
		route = routeRepository.save(route);
		
		System.out.println(route);
	}
	
	
	public void saveOnly(Route route) {
		Assert.notNull(route);

		route = routeRepository.save(route);
		
	
	}
	
	public Integer saveFlushed(Route route) {
		Assert.notNull(route);
								
		route = routeRepository.save(route);
		System.out.println(route);
		return route.getId();
	}

	public void delete(Route route) {
		Assert.notNull(route);

		routeRepository.delete(route);
	}

	// ========== Other Business Methods =============

	public Route reconstruct(TripForm tripForm) {

		Route res = new Route();
		String automaticName;
		SimpleDateFormat formatter;
		String startingDateString;
		String endDateString;
		Collection<Customer> customers = new ArrayList<>();
		
		formatter = new SimpleDateFormat("dd/MM/yyyy");
		startingDateString = formatter.format(tripForm.getStartingDate());
		endDateString = formatter.format(tripForm.getEndDate());
			
		automaticName = "AutomaticRoute " + startingDateString + "-" + endDateString;
		
		
		res.setId(0);
		res.setVersion(0);
		res.setDescription("Automatic Route");
		res.setEndDate(tripForm.getEndDate());
		res.setStartingDate(tripForm.getStartingDate());
		res.setName(automaticName);
		res.setCustomers(customers);
		res.setRating(null);
		res.setComments(null);
		res.setPrice(null);
		
		

		// Categories

		Collection<Category> categories = new ArrayList<>();

		if (tripForm.getCheckCulturalCategory() == true) {
			categories.add(categoryService.getCategoryByName("Cultural"));
		}
		if (tripForm.getCheckChurchesCategory() == true) {
			categories.add(categoryService.getCategoryByName("Churches"));
		}
		if (tripForm.getCheckRestaurantsCategory() == true) {
			categories.add(categoryService.getCategoryByName("Restaurants"));
		}
		if (tripForm.getCheckMuseumsCategory() == true) {
			categories.add(categoryService.getCategoryByName("Museums"));
		}
		if (tripForm.getCheckDrinksCategory() == true) {
			categories.add(categoryService.getCategoryByName("Drinks"));
		}
		if (tripForm.getCheckPaintingsCategory() == true) {
			categories.add(categoryService.getCategoryByName("Paintings"));
		}
		if (tripForm.getCheckMusicCategory() == true) {
			categories.add(categoryService.getCategoryByName("Music"));
		}
		if (tripForm.getCheckMonumentsCategory() == true) {
			categories.add(categoryService.getCategoryByName("Monuments"));
		}

		res.setCategories(categories);

		// Activities

		Collection<Activity> activities;
		Collection<Activity> actividadesFiltradas;

		// Filtrado por fecha
		Date startingDate = tripForm.getStartingDate();
		Date endingDate = tripForm.getEndDate();

		activities = activityService.findInDateRange(startingDate, endingDate);
		actividadesFiltradas = new ArrayList<>();

		// Filtrado por categoria

		for (Activity a : activities) {
			Collection<Category> cat = a.getCategories();
			for (Category c : categories) {
				if (cat.contains(c) && !actividadesFiltradas.contains(a)) {
					actividadesFiltradas.add(a);
				}
			}
		}

		res.setActivities(actividadesFiltradas);
		// res.setActivities(activityService.findAll());

		return res;

	}

	public Route filtraPrecio(Route route, double precio) {
		Route res;
		Collection<Activity> actividades;
		ArrayList<Activity> aRestantes;
		double p;

		res = route;
		actividades = new ArrayList<>();
		aRestantes = new ArrayList<>();
		p = precio;

		for (Activity ac : route.getActivities()) {
			aRestantes.add(ac);
		}

		while (p > 0 && aRestantes.size() > 0) {

			Random rnd = new Random();
			int i = rnd.nextInt(aRestantes.size());
			Activity a = aRestantes.get(i);
			aRestantes.remove(a);

			if (a.getCost() <= p) {
				actividades.add(a);
				p = p - a.getCost();

			}
		}

		// SIN RANDOM
		// for (Activity a : route.getActivities()) {
		// if (a.getCost() <= p) {
		// actividades.add(a);
		// p = p - a.getCost();
		// }
		// }

		res.setActivities(actividades);

		return res;
	}

	public Collection<Route> findRoutesByCustomer(Customer customer) {

		Collection<Route> res;

		res = routeRepository.findRoutesByCustomer(customer);

		return res;
	}
	
	public Collection<Route> findAllCustom(){
		Customer c = customerService.findByPrincipal();
		Collection<Route> res = routeRepository.findAllCustom();
		Collection<Route> todas = routeRepository.findAll();
		
		for(Route r : todas){
			if(r.getCustomers().contains(c)){
				res.remove(r);
			}
		}
		
		return res;
	}
}
