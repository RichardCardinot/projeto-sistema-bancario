package br.com.residencia.poo.sistema_bancario_principal.contas;

public abstract class Conta {
	private String cpfTitular;
	private double saldoTitular;
	private int agenciaTitular;

	public Conta(String cpfTitular, double saldoTitular, int agenciaTitular) {
		super();
		this.cpfTitular = cpfTitular;
		this.saldoTitular = saldoTitular;
		this.agenciaTitular = agenciaTitular;
	}

	public String getCpfTitular() {
		return cpfTitular;
	}

	public double getSaldoTitular() {
		return saldoTitular;
	}

	public int getAgenciaTitular() {
		return agenciaTitular;
	}

	public void setAgenciaTitular(int agenciaTitular) {
		this.agenciaTitular = agenciaTitular;
	}

	public void setSaldoTitular(double saldoTitular) {
		this.saldoTitular = saldoTitular;
	}

	public abstract boolean saque(double valor);

	public abstract boolean deposito(double valor);

	public abstract boolean transferencia(double valor, Conta conta);

}
