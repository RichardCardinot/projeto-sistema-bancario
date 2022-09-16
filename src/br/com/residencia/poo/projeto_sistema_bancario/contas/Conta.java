package br.com.residencia.poo.projeto_sistema_bancario.contas;

import java.util.ArrayList;
import br.com.residencia.poo.projeto_sistema_bancario.movimentacao.Movimentacao;

public abstract class Conta {
	private String cpfTitular;
	private double saldoTitular;
	private int agenciaTitular;
	private String tipoConta;
	private int numeroConta;

	private ArrayList<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
	
	public Conta() {}
	
	public Conta(String cpfTitular, double saldoTitular, int agenciaTitular, String tipoConta, int numeroConta) {
		super();
		this.cpfTitular = cpfTitular;
		this.saldoTitular = saldoTitular;
		this.agenciaTitular = agenciaTitular;
		this.tipoConta = tipoConta;
		this.numeroConta = numeroConta;
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
	
	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public ArrayList<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(Movimentacao movimentacao) {
		this.movimentacoes.add(movimentacao);
	}

	public abstract boolean sacar(double valor);

	public abstract boolean depositar(double valor);

	public abstract boolean transferir(double valor, Conta conta);

}
