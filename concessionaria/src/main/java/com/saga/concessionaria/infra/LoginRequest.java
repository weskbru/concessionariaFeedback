package com.saga.concessionaria.infra;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {
	@NotBlank(message = "CPF não pode estar vazio")
	@Size(min = 11, max = 11)
	private String cpf;

	@NotBlank(message = "Senha não pode estar vazia")
	private String senha;

	// Construtor vazio (default)
	public LoginRequest() {
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	//Metodo para verificar a validade da senhar 
	public boolean isSenhaValida() {
		return senha != null && senha.length() >= 8;
	}
}
