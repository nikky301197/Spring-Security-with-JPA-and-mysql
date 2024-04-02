package com.springwithjpa.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "users")
@ToString
public class User {
	@Id
	private String username;
	@Nonnull
	@JsonIgnoreProperties
	private String password;
//	@Nonnull
//	private boolean Enabled;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "username", referencedColumnName = "username"),
	inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "roleId"))
	private Set<Role> roles = new HashSet<>();
}
