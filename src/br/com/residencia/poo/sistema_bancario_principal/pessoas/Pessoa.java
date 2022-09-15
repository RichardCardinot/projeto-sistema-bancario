package br.com.residencia.poo.sistema_bancario_principal.pessoas;

public abstract class Pessoa {

	private String tipoPessoa;
	private String cpf;
	private String senha;
	private String nome;

	public Pessoa(String tipoPessoa, String cpf, String senha, String nome) {
		super();
		this.tipoPessoa = tipoPessoa;
		this.cpf = cpf;
		this.senha = senha;
		this.nome = nome;
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

	public String getNome() {
		return nome;
	}

}
