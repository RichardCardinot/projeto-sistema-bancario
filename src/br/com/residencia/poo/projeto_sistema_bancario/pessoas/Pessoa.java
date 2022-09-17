package br.com.residencia.poo.projeto_sistema_bancario.pessoas;

public abstract class Pessoa {

	protected String tipoPessoa;
	protected String cpf;
	protected String senha;
	protected String nome;
	protected String agenciaGerenciada;

	public Pessoa(String tipoPessoa, String cpf, String senha, String nome, String agenciaGerenciada) {
		super();
		this.tipoPessoa = tipoPessoa;
		this.cpf = cpf;
		this.senha = senha;
		this.nome = nome;
		this.agenciaGerenciada = agenciaGerenciada;
	}
	
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

	public String getAgenciaGerenciada() {
		return agenciaGerenciada;
	}
	
	

}
