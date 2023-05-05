package com.agencia.vousuave.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private Integer id;
	private String nome;
	private String email;
	private List<String> roles;

	public JwtResponse(String token, Integer id, String nome, String email, List<String> roles) {
		super();
		this.token = token;
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.roles = roles;
	}

}
