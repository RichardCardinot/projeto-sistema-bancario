package br.com.residencia.poo.sistema_bancario_principal.contas;

import java.util.Date;

import br.com.residencia.poo.projeto_sistema_bancario.movimentacao.Movimentacao;

public class ContaPoupanca extends Conta {

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
		if (getSaldoTitular() >= valor) {
			setSaldoTitular(getSaldoTitular() - valor);
			setMovimentacoes(new Movimentacao("Saque", valor));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean depositar(double valor) {
		if (valor > 0) {
			setSaldoTitular(getSaldoTitular() + valor);
			setMovimentacoes(new Movimentacao("Depósito", valor));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean transferir(double valor, Conta conta) {

		if (getSaldoTitular() <= valor) {
			double saldo = getSaldoTitular();
			setSaldoTitular(saldo -= valor);
			conta.depositar(valor);
			return true;
		} else {
			return false;
		}
	}

}
