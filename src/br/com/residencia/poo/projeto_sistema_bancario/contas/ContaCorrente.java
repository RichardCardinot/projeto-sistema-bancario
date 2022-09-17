package br.com.residencia.poo.projeto_sistema_bancario.contas;

import java.io.IOException;

import br.com.residencia.poo.projeto_sistema_bancario.movimentacao.Movimentacao;
import br.com.residencia.poo.projeto_sistema_bancario.repositorio.GeradorDeArquivos;

public class ContaCorrente extends Conta {
	final double TAXA_SAQUE = 0.1;
	final double TAXA_DEPOSITO = 0.1;
	final double TAXA_TRANSFERENCIA = 0.2;

	public ContaCorrente() {
		super();
	}

	public ContaCorrente(String cpfTitular, double saldoTitular, int agenciaTitular, String tipoConta,
			int numeroConta) {
		super(cpfTitular, saldoTitular, agenciaTitular, tipoConta, numeroConta);

	}

	@Override
	public boolean sacar(double valor) {
		if (getSaldoTitular() > valor && valor > 0) {
			double saldo = getSaldoTitular();
			saldo -= valor + TAXA_SAQUE;// cada saque custa 0.10 centavos
			setSaldoTitular(saldo);
			Movimentacao movimentacao = new Movimentacao("Saque", valor, TAXA_SAQUE);

			movimentacoes.add(movimentacao);
			try {
				GeradorDeArquivos.escreverArquivo(movimentacao, "HistoricoConta" + this.getNumeroConta());
			} catch (IOException e) {
				System.out.println("O arquivo de movimentações não pode ser gerado!");
			}

			System.out.println("Seu saque foi realizado com sucesso!");
			System.out.printf("Você sacou R$%.2f. Seu saldo agora é R$%.2f.", valor, this.getSaldoTitular());
			return true;
		} else {
			System.out.println("Valor inválido!");
			return false;
		}
	}

	@Override
	public boolean depositar(double valor, boolean operacao) {
		if (valor > 0) {
			this.saldoTitular = (this.saldoTitular + (valor - TAXA_DEPOSITO));

			if (!operacao) {
				Movimentacao movimentacao = new Movimentacao("Depósito", valor, TAXA_DEPOSITO);
				System.out.println("Seu depósito foi realizado com sucesso!");
				System.out.printf("Você depositou R$%.2f. Seu saldo agora é R$%.2f", valor, this.getSaldoTitular());
				movimentacoes.add(movimentacao);
				try {
					GeradorDeArquivos.escreverArquivo(movimentacao, "HistoricoConta" + this.getNumeroConta());
				} catch (IOException e) {
					System.out.println("O arquivo de movimentações não pode ser gerado!");
				}
				
			} else {
				Movimentacao movimentacao = new Movimentacao("Transferência", valor - TAXA_DEPOSITO, 0.0);
				movimentacoes.add(movimentacao);
				try {
					GeradorDeArquivos.escreverArquivo(movimentacao, "HistoricoConta" + this.getNumeroConta());
				} catch (IOException e) {
					System.out.println("O arquivo de movimentações não pode ser gerado!");
				}
			}
			
			




			return true;
		} else {
			System.out.println("Valor inválido!");
			return false;
		}
	}

	@Override
	public boolean transferir(double valor, Conta conta) {

		valor += TAXA_TRANSFERENCIA; // Criação da tributação dentro do método

		if (saldoTitular >= valor) {
			this.saldoTitular -= valor;

			conta.depositar((valor - TAXA_TRANSFERENCIA) + TAXA_DEPOSITO, true); 

			Movimentacao movimentacao = new Movimentacao("Transferência", valor - TAXA_TRANSFERENCIA, TAXA_TRANSFERENCIA);
			movimentacoes.add(movimentacao);

			try {
				GeradorDeArquivos.escreverArquivo(movimentacao, "HistoricoConta" + this.getNumeroConta());
			} catch (IOException e) {
				System.out.println("O arquivo de movimentações não pode ser gerado!");
			}

			System.out.println("\nSua transferência foi realizada com sucesso!");
			System.out.printf("Você transferiu R$%.2f", valor);
			System.out.println("\nConta favorecida: " + conta.numeroConta);
			return true;
		} else {
			return false;
		}
	}
}
