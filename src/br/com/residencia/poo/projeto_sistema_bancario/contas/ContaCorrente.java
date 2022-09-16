package br.com.residencia.poo.projeto_sistema_bancario.contas;

import java.io.IOException;

import br.com.residencia.poo.projeto_sistema_bancario.movimentacao.Movimentacao;
import br.com.residencia.poo.projeto_sistema_bancario.repositorio.GeradorDeArquivos;

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
		if (getSaldoTitular() >= valor) {
			double saldo = getSaldoTitular();
			saldo -= valor + 0.1;// cada saque custa 0.10 centavos
			setSaldoTitular(saldo);
			Movimentacao movimentacao = new Movimentacao("Saque", valor); //Incluí esse linha para poder ter a movitação nos dois paramtros abaixo
			setMovimentacoes(movimentacao);
			try {
				GeradorDeArquivos.escreverArquivo(movimentacao, "HistoricoConta" + this.getNumeroConta());
			} catch (IOException e) {
				System.out.println("O arquivo de movimentações não pode ser gerado!");
			}; //Coloquei para gerar o arquivo aqui como teste !!!
			System.out.println("Seu saque foi realizado com sucesso!");
			System.out.println("Você sacou R$" + valor + ". Seu saldo agora é R$" + this.getSaldoTitular());
			return true;
		} else {
			System.out.println("Valor insuficiente!" + "Seu saldo é R$" + this.getSaldoTitular());
			return false;
		}
	}

	@Override
	public boolean depositar(double valor) {
		if (valor > 0) {
			setSaldoTitular(valor - 0.1); // cada deposito custa 0.10 centavos
			setMovimentacoes(new Movimentacao("Depósito", valor));
			System.out.println("Seu depósito foi realizado com sucesso!");
			System.out.println("Você depositou R$" + valor + ". Seu saldo agora é R$" + this.getSaldoTitular());
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
			System.out.println("Você transferiu R$" + valor + " para a conta: " + conta + ". Seu saldo atual agora é R$"
					+ this.getSaldoTitular());
			return true;
		} else {
			return false;
		}
	}
}
