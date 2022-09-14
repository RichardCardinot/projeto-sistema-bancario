package br.com.residencia.poo.sistema_bancario_principal.pessoas;

public abstract class Pessoa {

	private String senha;
	private String cpf;

	public Pessoa() {
		super();

	}

	public Pessoa(String senha, String cpf) {
		super();
		this.senha = senha;
		this.cpf = cpf;

	}

	public String getSenha() {
		return senha;
	}

	public String getCpf() {
		return cpf;
	}

}
