package br.com.residencia.poo.sistema_bancario_principal.pessoas;

public class Gerente extends Pessoa {

	private int agenciaGerenciada;

	public Gerente(String tipoPessoa, String cpf, String senha, String nome, int agenciaGerenciada) {
		super(tipoPessoa, cpf, senha, nome);
		this.agenciaGerenciada = agenciaGerenciada;
	}

	public int getAgenciaGerenciada() {
		return agenciaGerenciada;
	}

	public void setAgenciaGerenciada(int agenciaGerenciada) {
		this.agenciaGerenciada = agenciaGerenciada;
	}

}
