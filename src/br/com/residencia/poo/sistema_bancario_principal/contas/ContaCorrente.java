package br.com.residencia.poo.sistema_bancario_principal.contas;

public class ContaCorrente extends Conta {

	public ContaCorrente(String cpfTitular, double saldoTitular, int agenciaTitular) {
		super(cpfTitular, saldoTitular, agenciaTitular);

	}

	@Override
	public boolean saque(double valor) {
		if (getSaldoTitular() <= valor) {
			double saldo = getSaldoTitular();
			saldo -= valor + 0.1;//cada saque custa 0.10 centavos
			setSaldoTitular(saldo);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deposito(double valor) {
		if (valor > 0) {
			setSaldoTitular(valor - 0.1); //cada deposito custa 0.10 centavos
			return true;
		} else {

			return false;
		}
	}

	@Override
	public boolean transferencia(double valor, Conta conta) {
		if (this.saque(valor + 0.1)) {
			conta.deposito(valor); //Atributacoes ja foram feitas em outros metodos
			return true;
		} else {
			return false;  //// Implantar a taxa dessa operacao
		}
	}
}
