package br.com.residencia.poo.sistema_bancario_principal.menu;

import java.util.Scanner;

import br.com.residencia.poo.sistema_bancario_principal.contas.Conta;
import br.com.residencia.poo.sistema_bancario_principal.pessoas.Pessoa;

public class MenuPrincipal {
	static Scanner sc = new Scanner(System.in);

	public static void selecionarMenu(Pessoa pessoa, Conta conta) {
		System.out.println("Entrou aqui");

		if (pessoa.getTipoPessoa().equals("CLIENTE")) {
				apresentar(pessoa);
				
				if (opcoesCliente(pessoa, conta) == 1) { //Chama o menu "movimentações da conta"
					System.out.println("Informe a operação desejada: ");
					if (conta.getTipoConta().equals("CORRENTE")) { //incluir aqui o tipo de conta para poder chamar as operações disponíveis para tal conta!!!
						opcoesMovimentacaoContaCorrente(pessoa, conta);
					}
					
				}
				

		} else {

			try {
				apresentar(pessoa);
				System.out.println("Escolha a opção desejada:\n");
				opcoesGerente(pessoa, conta);

			} catch (Exception e) {
				System.out.println("Opção inválida!");
				selecionarMenu(pessoa, conta);
			}

		}

	}

	public static void apresentar(Pessoa pessoa) {
		String nome = "", espaco = "";
		int i = 35 - (pessoa.getNome().length());
		i /= 2;
		for (int t = 0; t <= i; t++) {
			espaco += " ";
		}

		nome = espaco + pessoa.getNome() + espaco;
		System.out.println("-----------------------------------");
		System.out.println("--- B A N C O  S E R R A T E C ---");
		System.out.println("-----------------------------------");
		System.out.println(nome);
		System.out.println("-----------------------------------");
	}

	public static int opcoesCliente(Pessoa pessoa, Conta conta) { // Se for cliente

		try {
			System.out.println("Escolha a opção desejada:\n");
			System.out.println("1 - Movimentações na Conta");
			System.out.println("2 - Relatórios");
			return sc.nextInt();

		} catch (Exception e) {
			System.out.println("Opção inválida!");
			selecionarMenu(pessoa, conta);
			return 0;
		}

	}

	public static int opcoesGerente(Pessoa pessoa, Conta conta) { // Se for funcionário
		try {
			System.out.println("Escolha a opção desejada:\n");
			System.out.println("1 - Relatórios");
			return sc.nextInt();

		} catch (Exception e) {
			System.out.println("Opção inválida!");
			selecionarMenu(pessoa, conta);
			return 0;
		}
	}
	
	public static int opcoesMovimentacaoContaCorrente(Pessoa pessoa, Conta conta) { // Se for funcionário
		try {
			System.out.println("Escolha a opção desejada:\n");
			System.out.println("1 - Saque");
			System.out.println("2 - Deposito");
			System.out.println("3 - Transferencia");
			return sc.nextInt();

		} catch (Exception e) {
			System.out.println("Opção inválida!");
			selecionarMenu(pessoa, conta);
			return 0;
		}
	}

}