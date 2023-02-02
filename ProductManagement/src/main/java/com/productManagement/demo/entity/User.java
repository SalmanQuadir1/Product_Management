package com.productManagement.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "phone_number")
	private String phone;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "pincode")
	private Integer pincode;
	
	@Column(name = "location")
	private String location;
	          
	@Column(name = "country")   
	private String country;
	
	@Column(name = "recent_searches")
	private String recentSearches;
	
	@Column(name = "popular_searches")
	private String popularSearches;
	
	@Column(name = "active")
	private Boolean active;
	
	@Column(name = "user_image")
	private String userImage;

	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Role> roles;
	
	
	
	public User() {}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public Integer getPincode() {
		return pincode;
	}



	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getRecentSearches() {
		return recentSearches;
	}



	public void setRecentSearches(String recentSearches) {
		this.recentSearches = recentSearches;
	}



	public String getPopularSearches() {
		return popularSearches;
	}



	public void setPopularSearches(String popularSearches) {
		this.popularSearches = popularSearches;
	}



	public Boolean getActive() {
		return active;
	}



	public void setActive(Boolean active) {
		this.active = active;
	}



	public List<Role> getRoles() {
		return roles;
	}



	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
	



	public String getUserImage() {
		return userImage;
	}



	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}







	public User(Long id, String firstName, String lastName, String username, String email, String password,
			String phone, String address, Integer pincode, String location, String country, String recentSearches,
			String popularSearches, Boolean active, String userImage, List<Role> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.pincode = pincode;
		this.location = location;
		this.country = country;
		this.recentSearches = recentSearches;
		this.popularSearches = popularSearches;
		this.active = active;
		this.userImage = userImage;
		this.roles = roles;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", email=" + email + ", password=" + password + ", phone=" + phone + ", address=" + address
				+ ", pincode=" + pincode + ", location=" + location + ", country=" + country + ", recentSearches="
				+ recentSearches + ", popularSearches=" + popularSearches + ", active=" + active + ", roles=" + roles
				+ "]";
	}

	
}
