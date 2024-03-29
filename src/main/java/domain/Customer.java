package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Actor {
	// ==============Constructor=============
	public Customer() {
		super();
	}

	// ==============Atributos==============
	private CreditCard creditCard;

	@Valid
	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	// =============== Relaciones ==============

	private Collection<Route> routes;
	private Collection<Rating> ratings;
	private Collection<Comment> comments;
	private Collection<Activity> activities;

	@OneToMany(mappedBy = "customer")
	public Collection<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Collection<Activity> activities) {
		this.activities = activities;
	}

	@ManyToMany(mappedBy = "customers")
	public Collection<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(Collection<Route> routes) {
		this.routes = routes;
	}

	@Valid
	@OneToMany(mappedBy = "customer")
	public Collection<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(Collection<Rating> ratings) {
		this.ratings = ratings;
	}

	@Valid
	@OneToMany(mappedBy = "customer")
	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

}
