package br.com.residencia.poo.sistema_bancario_principal.pessoas;

public abstract class Pessoa {

	private String senha;
	private String cpf;
	private char tipoPessoa;

	public Pessoa() {
		super();

	}

	public Pessoa(String senha, String cpf, char tipoPessoa) {
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

	public char getTipoPessoa() {
		return tipoPessoa;
	}

}
