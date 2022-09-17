package br.com.residencia.poo.projeto_sistema_bancario.contas;

import java.io.IOException;

import br.com.residencia.poo.projeto_sistema_bancario.movimentacao.Movimentacao;
import br.com.residencia.poo.projeto_sistema_bancario.repositorio.GeradorDeArquivos;

public class ContaPoupanca extends Conta {
	final double TAXA_ZERADA = 0.0;
	final static double RENDIMENTO = 0.1;

	public ContaPoupanca() {
		super();
	}

	public ContaPoupanca(String cpfTitular, double saldoTitular, int agenciaTitular, String tipoConta,
			int numeroConta) {
		super(cpfTitular, saldoTitular, agenciaTitular, tipoConta, numeroConta);
	}

	public String simularRendimento(double valorAplicacao, int quantidadeDias) {
		String mensagem = "";
		if (valorAplicacao > 0 && quantidadeDias > 0) {
			double valorRendimento = quantidadeDias * RENDIMENTO;
			double valorRendimentoTotal = valorAplicacao + valorRendimento;
			
			Movimentacao movimentacao = new Movimentacao("Simulação de Rendimento", valorRendimentoTotal, RENDIMENTO); 
			movimentacoes.add(movimentacao);
	
			try {
				GeradorDeArquivos.escreverArquivo(movimentacao, "HistoricoConta" + this.numeroConta);
			} catch (IOException e) {
				System.out.println("O arquivo de movimentações não pode ser gerado!");
			}
			
			mensagem = "Para o valor de aplicação R$" + valorAplicacao + ", seu\nrendimento será de R$"
					+ valorRendimento + ", ficando com o\ntotal de R$" + valorRendimentoTotal;
			System.out.println(mensagem);
			return mensagem;
		} else {
			mensagem = "O valor da aplicação ou a quantidade de dias não pode ser zero.";
			System.out.println(mensagem);
			return mensagem;
		}
	}

	@Override
	public boolean sacar(double valor) {
		if (getSaldoTitular() > valor && valor > 0) {
			setSaldoTitular(getSaldoTitular() - valor);		
			Movimentacao movimentacao = new Movimentacao("Saque", valor, TAXA_ZERADA); 
			
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
			setSaldoTitular(getSaldoTitular() + valor);	
			Movimentacao movimentacao = new Movimentacao("Depósito", valor, TAXA_ZERADA); 
			
			movimentacoes.add(movimentacao);
			try {
				GeradorDeArquivos.escreverArquivo(movimentacao, "HistoricoConta" + this.getNumeroConta());
			} catch (IOException e) {
				System.out.println("O arquivo de movimentações não pode ser gerado!");
			}
			
			if (!operacao) {
				System.out.println("Seu depósito foi realizado com sucesso!");
				System.out.printf("Você depositou R$%.2f. Seu saldo agora é R$%.2f", valor, this.getSaldoTitular());
			}

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
			conta.depositar(valor, true);
			
			Movimentacao movimentacao = new Movimentacao("Transferência", valor, TAXA_ZERADA); 
			
			movimentacoes.add(movimentacao);
			try {
				GeradorDeArquivos.escreverArquivo(movimentacao, "HistoricoConta" + this.getNumeroConta());
			} catch (IOException e) {
				System.out.println("O arquivo de movimentações não pode ser gerado!");
			}
			
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