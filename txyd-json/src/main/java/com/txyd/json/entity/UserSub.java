package com.txyd.json.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSub  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("users")
	private List<UserEntity> users;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<UserEntity> getUsers() {
		return users;
	}
	
	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
	
	
	@Override
	public String toString() {
		return "UserSub{" +
				"id=" + id +
				", name='" + name + '\'' +
				", users=" + users +
				'}';
	}
}
