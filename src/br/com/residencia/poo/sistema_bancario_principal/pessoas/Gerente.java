package br.com.residencia.poo.sistema_bancario_principal.pessoas;

public class Gerente extends Pessoa {

	private int agenciaGerenciada;

	public Gerente(int agenciaGerenciada, String tipoPessoa, String senha, String cpf) {
		super(tipoPessoa, senha, cpf);
		this.agenciaGerenciada = agenciaGerenciada;
	}

	public int getAgenciaGerenciada() {
		return agenciaGerenciada;
	}

	public void setAgenciaGerenciada(int agenciaGerenciada) {
		this.agenciaGerenciada = agenciaGerenciada;
	}

}
