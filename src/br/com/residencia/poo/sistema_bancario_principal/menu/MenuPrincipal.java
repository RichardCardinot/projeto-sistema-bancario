package br.com.residencia.poo.sistema_bancario_principal.menu;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import br.com.residencia.poo.projeto_sistema_bancario.date.FormataData;
import br.com.residencia.poo.sistema_bancario_principal.contas.Conta;
import br.com.residencia.poo.sistema_bancario_principal.pessoas.Pessoa;

public class MenuPrincipal {
	static Scanner sc = new Scanner(System.in);

	public static void selecionarMenu(Pessoa pessoa, Conta conta, ArrayList<Conta> contas) {

		if (pessoa.getTipoPessoa().equals("CLIENTE")) {
			apresentar(pessoa, conta);

			if (opcoesCliente(pessoa, conta, contas) == 1) { // Chama o menu "movimentações da conta"
				apresentar(pessoa, conta);
				if (conta.getTipoConta().equals("CORRENTE")) {

					switch (opcoesMovimentacaoContaCorrente(pessoa, conta, contas)) {
					case 1: {// 1-Saque
						try {
							System.out.println("\nSaldo atual R$ " + conta.getSaldoTitular());
							System.out.println("\nInforme o valor a ser sacado: ");
							conta.sacar(sc.nextDouble());
							System.out.println("\nNovo saldo R$ \n" + conta.getSaldoTitular());
							selecionarMenu(pessoa, conta, contas);
							
						} catch (Exception e) {
							System.out.println("\nDado inválido!\n");
							selecionarMenu(pessoa, conta, contas);
						}

					}
					case 2: {// 2-Depósito
						try {
							System.out.println("\nInforme o valor a ser depositado: ");
							conta.depositar(sc.nextDouble());
							selecionarMenu(pessoa, conta, contas);
						} catch (Exception e) {
							System.out.println("\nDado inválido!\n");
							selecionarMenu(pessoa, conta, contas);
						}
					}

					case 3: {// 3-Transferência

						try {
							double valor;
							System.out.println("\nInforme o valor a ser transferido: ");
							valor = sc.nextDouble();
							System.out.println("\nInforme o CPF do usuário de destino: ");
							conta = LeitorDeDados.buscaContaPorCpf(sc.next());
							if (conta != null) {
								conta.transferir(valor, conta);
								selecionarMenu(pessoa, conta, contas);
							} else {
								System.out.println("Conta não localizada!");
								selecionarMenu(pessoa, conta, contas);
							}

						} catch (Exception e) {
							System.out.println("Dado inválido!");
							selecionarMenu(pessoa, conta, contas);
						}
					}

					}

				}

			} else {

				try {
					apresentar(pessoa, conta);
					System.out.println("Escolha a opção desejada:\n");
					opcoesGerente(pessoa, conta, contas);

				} catch (Exception e) {
					System.out.println("Opção inválida!");
					selecionarMenu(pessoa, conta, contas);
				}

			}
		}
	}

	public static void apresentar(Pessoa pessoa, Conta conta) {
		String nome = "", espaco = "";
		int i = 24 - (pessoa.getNome().length());
		i /= 2;
		for (int t = 0; t <= i; t++) {
			espaco += " ";
		}
		nome = espaco + "Bem-vindo, " + pessoa.getNome() + "!" + espaco;
		
		String espacoDois = "";
		int d = 36 - (String.valueOf(conta.getSaldoTitular()).length() + 27);
		for (int t = 0; t <= d; t++) {
			espacoDois += " ";
		}

		
		System.out.println("\n----------------------------------------");
		System.out.println("---    B A N C O  S E R R A T E C    ---");
		System.out.println("----------------------------------------");
		System.out.println("- Saldo: R$ " + conta.getSaldoTitular() + espacoDois + FormataData.converterDateParaDataEHora(new Date()) + " -");
		System.out.println("----------------------------------------");
		System.out.println("-                                      -");
		System.out.println("-" + nome + "-");
		System.out.println("-                                      -");
		System.out.println("- v1.0                                 -");
		System.out.println("----------------------------------------");
		System.out.println("\nEscolha a opção desejada:");
		
	}

	public static int opcoesCliente(Pessoa pessoa, Conta conta, ArrayList<Conta> contas) { // Se for cliente

		try {
			System.out.println(" (1) - Movimentações na Conta");
			System.out.println(" (2) - Relatórios");
			return sc.nextInt();

		} catch (Exception e) {
			System.out.println("Opção inválida!");
			selecionarMenu(pessoa, conta, contas);
			return 0;
		}

	}

	public static int opcoesGerente(Pessoa pessoa, Conta conta, ArrayList<Conta> contas) { // Se for funcionário
		try {
			System.out.println("1 - Relatórios");
			return sc.nextInt();

		} catch (Exception e) {
			System.out.println("Opção inválida!");
			selecionarMenu(pessoa, conta, contas);
			return 0;
		}
	}

	public static int opcoesMovimentacaoContaCorrente(Pessoa pessoa, Conta conta, ArrayList<Conta> contas) { // Se for
																												// funcionário
		try {
			System.out.println("1 - Saque");
			System.out.println("2 - Deposito");
			System.out.println("3 - Transferencia");
			return sc.nextInt();

		} catch (Exception e) {
			System.out.println("Opção inválida!");
			selecionarMenu(pessoa, conta, contas);
			return 0;
		}
	}

}