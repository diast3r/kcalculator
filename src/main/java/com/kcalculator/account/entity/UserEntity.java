package com.kcalculator.account.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "user")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String profileImagePath;
	
	private String loginId;
	
	private String password;
	
	private String passwordSalt;
	
	private String nickname;
	
	private String email;
	
	@CreationTimestamp 
	private LocalDateTime createdAt;
	
	@UpdateTimestamp 
	private LocalDateTime updatedAt;
	
	public void updateProfile(String profileImagePath, String nickname, String email) {
		this.profileImagePath = profileImagePath;
		this.nickname = nickname;
		this.email = email;
	}
}
