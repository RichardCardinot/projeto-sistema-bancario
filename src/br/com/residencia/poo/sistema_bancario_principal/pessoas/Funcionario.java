package br.com.residencia.poo.sistema_bancario_principal.pessoas;

public class Funcionario extends Pessoa {

	private int tipoPessoa;

	public Funcionario(int tipoPessoa, String senha, String cpf) {
		super(senha, cpf);
		this.tipoPessoa = tipoPessoa;
	}

	public int getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(int tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

}
