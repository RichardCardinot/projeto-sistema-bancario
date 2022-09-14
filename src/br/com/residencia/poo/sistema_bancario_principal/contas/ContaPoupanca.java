package br.com.residencia.poo.sistema_bancario_principal.contas;

import java.util.Date;

import br.com.residencia.poo.projeto_sistema_bancario.movimentacao.Movimentacao;

public class ContaPoupanca extends Conta {
	
	public ContaPoupanca(String cpfTitular, double saldoTitular, int agenciaTitular) {
		super(cpfTitular, saldoTitular, agenciaTitular);
	}

	public double simulacaoRendimento(double valorAplicacao, int quantidadeDias) {
		if (valorAplicacao > 0 && quantidadeDias > 0) {
			return valorAplicacao * (quantidadeDias * 0.01);
		}
		System.out.println("O valor da aplicação ou a quantidade de dias não pode ser zero.");
		return 0;
	}

	@Override
	public boolean saque(double valor) {
		if (getSaldoTitular() >= valor) {
			setSaldoTitular(getSaldoTitular() - valor);
			setMovimentacoes(new Movimentacao("Saque", valor));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deposito(double valor) {
		if (valor > 0) {
			setSaldoTitular(getSaldoTitular() + valor);
			setMovimentacoes(new Movimentacao("Depósito", valor));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean transferencia(double valor, Conta conta) {

		if (getSaldoTitular() <= valor) {
			double saldo = getSaldoTitular();
			setSaldoTitular(saldo -= valor);
			conta.deposito(valor);
			return true;
		} else {
			return false;
		}
	}

}
