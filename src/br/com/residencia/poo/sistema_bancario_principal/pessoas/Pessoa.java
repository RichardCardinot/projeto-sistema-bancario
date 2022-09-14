package br.com.residencia.poo.sistema_bancario_principal.pessoas;

public abstract class Pessoa {

	private String senha;
	private String cpf;
	private String tipoPessoa;

	public Pessoa(String tipoPessoa, String cpf, String senha) {
		super();
		this.senha = senha;
		this.cpf = cpf;
		this.tipoPessoa = tipoPessoa;
	}

	public String getSenha() {
		return senha;
	}

	public String getCpf() {
		return cpf;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

}
