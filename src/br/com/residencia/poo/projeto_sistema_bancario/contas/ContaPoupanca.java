package br.com.residencia.poo.projeto_sistema_bancario.contas;

import java.util.Date;

import br.com.residencia.poo.projeto_sistema_bancario.movimentacao.Movimentacao;

public class ContaPoupanca extends Conta {

	public ContaPoupanca() {
		super();
	}

	public ContaPoupanca(String cpfTitular, double saldoTitular, int agenciaTitular, String tipoConta,
			int numeroConta) {
		super(cpfTitular, saldoTitular, agenciaTitular, tipoConta, numeroConta);
	}

	public void simularRendimento(double valorAplicacao, int quantidadeDias) {
		if (valorAplicacao > 0 && quantidadeDias > 0) {
			double valorRendimento = quantidadeDias * 0.10;
			double valorRendimentoTotal = valorAplicacao + valorRendimento;
			System.out.println("Para o valor de aplicação R$" + valorAplicacao + ", seu rendimento será de R$"
					+ valorRendimento + ", ficando com o total de R$" + valorRendimentoTotal);
		} else {
			System.out.println("O valor da aplicação ou a quantidade de dias não pode ser zero.");
		}
	}

	@Override
	public boolean sacar(double valor) {
		if (getSaldoTitular() > valor && valor > 0) {
			setSaldoTitular(getSaldoTitular() - valor);
			setMovimentacoes(new Movimentacao("Saque", valor));
			System.out.println("Seu saque foi realizado com sucesso!");
			System.out.printf("Você sacou R$%.2f. Seu saldo agora é R$%.2f.", valor, this.getSaldoTitular());
			return true;
		} else {
			System.out.println("Valor inválido!");
			return false;
		}
	}

	@Override
	public boolean depositar(double valor) {
		if (valor > 0) {
			setSaldoTitular(getSaldoTitular() + valor);
			setMovimentacoes(new Movimentacao("Depósito", valor));
			System.out.println("Seu depósito foi realizado com sucesso!");
			System.out.printf("Você depositou R$%.2f. Seu saldo agora é R$%.2f", valor, this.getSaldoTitular());
			return true;
		} else {
			System.out.println("Valor inválido!");
			return false;
		}
	}

	@Override
	public boolean transferir(double valor, Conta conta) {

		if (getSaldoTitular() <= valor) {
			double saldo = getSaldoTitular();
			setSaldoTitular(saldo -= valor);
			conta.depositar(valor);
			System.out.println("Sua transferência foi realizada com sucesso!");
			System.out.printf("Você transferiu R$%.2f", valor);
			System.out.println("Conta favorecida: "+ conta);
			System.out.printf("Seu saldo atual agora é R$%.2f", this.getSaldoTitular());
			return true;
		} else {
			System.out.println("Saldo insuficiente!");
			return false;
		}
	}

}