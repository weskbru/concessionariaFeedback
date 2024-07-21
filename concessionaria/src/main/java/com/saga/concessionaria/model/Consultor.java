package com.saga.concessionaria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;



@Entity
@Table(name = "consultores", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = "cpf"), indexes = @Index(name = "idx_nome_cpf", columnList = "nome, cpf"))
public class Consultor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Email é obrigatório")
	@Email(message = "Email deve ser válido")
	@Column(name = "email", nullable = false)
	private String email;

	@NotBlank(message = "Nome é obrigatório")
	@Column(name = "nome", nullable = false)
	private String nome;

	@NotBlank(message = "CPF é obrigatório")
	@Column(name = "cpf", nullable = false)
	private String cpf;

	@NotBlank(message = "Senha é obrigatória")
	@Column(name = "senha", nullable = false)

	private String senha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

}
