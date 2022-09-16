package br.com.residencia.poo.sistema_bancario_principal.contas;

import br.com.residencia.poo.projeto_sistema_bancario.movimentacao.Movimentacao;

public class ContaCorrente extends Conta {

	public ContaCorrente() {
		super();
	}

	public ContaCorrente(String cpfTitular, double saldoTitular, int agenciaTitular, String tipoConta,
			int numeroConta) {
		super(cpfTitular, saldoTitular, agenciaTitular, tipoConta, numeroConta);

	}

	@Override
	public boolean sacar(double valor) {
		if (getSaldoTitular() > valor && valor > 0){
			double saldo = getSaldoTitular();
			saldo -= valor + 0.1;// cada saque custa 0.10 centavos
			setSaldoTitular(saldo);
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
			setSaldoTitular(this.getSaldoTitular() + (valor - 0.1)); // cada deposito custa 0.10 centavos
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

		valor += 0.20; // Criação da tributação dentro do método

		if (getSaldoTitular() <= valor) {
			double saldo = getSaldoTitular();
			setSaldoTitular(saldo -= valor);
			conta.depositar(valor - 0.20);
			System.out.println("Sua transferência foi realizada com sucesso!");
			System.out.printf("Você transferiu R$%.2f", valor);
			System.out.println("Conta favorecida: "+ conta);
			System.out.printf("Seu saldo atual agora é R$%.2f", this.getSaldoTitular());
			return true;
		} else {
			return false;
		}
	}
}
