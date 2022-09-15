package br.com.residencia.poo.sistema_bancario_principal.contas;

import br.com.residencia.poo.projeto_sistema_bancario.movimentacao.Movimentacao;

public class ContaCorrente extends Conta {

	public ContaCorrente(String cpfTitular, double saldoTitular, int agenciaTitular, String tipoConta, int numeroConta) {
		super(cpfTitular, saldoTitular, agenciaTitular, tipoConta, numeroConta);
		
	}

	@Override
	public boolean sacar(double valor) {
		if (getSaldoTitular() >= valor) {
			double saldo = getSaldoTitular();
			saldo -= valor + 0.1;//cada saque custa 0.10 centavos
			setSaldoTitular(saldo);
			setMovimentacoes(new Movimentacao("Saque", valor));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean depositar(double valor) {
		if (valor > 0) {
			setSaldoTitular(valor - 0.1); //cada deposito custa 0.10 centavos
			setMovimentacoes(new Movimentacao("Depósito", valor));
			return true;
		} else {

			return false;
		}
	}

	@Override
	public boolean transferir(double valor, Conta conta) {
		
		valor += 0.20; // Criação da tributação dentro do método
		
		if (getSaldoTitular() <= valor) {
			double saldo = getSaldoTitular();
			setSaldoTitular(saldo -= valor);
			conta.depositar(valor - 0.20);
			return true;
		} else {
			return false;
		}
	}
}
