package br.com.residencia.poo.projeto_sistema_bancario.menu;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import br.com.residencia.poo.projeto_sistema_bancario.contas.Conta;
import br.com.residencia.poo.projeto_sistema_bancario.contas.ContaPoupanca;
import br.com.residencia.poo.projeto_sistema_bancario.date.FormataData;
import br.com.residencia.poo.projeto_sistema_bancario.pessoas.Pessoa;
import br.com.residencia.poo.projeto_sistema_bancario.repositorio.GeradorDeArquivos;
import br.com.residencia.poo.projeto_sistema_bancario.repositorio.LeitorDeDados;

public class MenuPrincipal {
	static Scanner sc = new Scanner(System.in);
	static DecimalFormat formataDecimais = new DecimalFormat("#,##0.00");
	static String dadosGeradorArquivo = "";

	public static void selecionarMenu(Pessoa pessoa, Conta conta, ArrayList<Conta> contas) {

		if (pessoa.getTipoPessoa().equals("CLIENTE")) {
			menuCliente(pessoa, conta);
		} else {
			menuFuncionario(pessoa, contas);
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
		int d = 36 - (String.format("%.2f", conta.getSaldoTitular()).length() + 27);
		for (int t = 0; t <= d; t++) {
			espacoDois += " ";
		}

		System.out.println("\n----------------------------------------");
		System.out.println("---    B A N C O  S E R R A T E C    ---");
		System.out.println("----------------------------------------");
		System.out.println("- Saldo: R$ " + String.format("%.2f", conta.getSaldoTitular()) + espacoDois
				+ FormataData.converterDateParaDataEHora(new Date()) + " -");
		System.out.println("----------------------------------------");
		System.out.println("-                                      -");
		System.out.println("-" + nome + "-");
		System.out.println("-                                      -");
		System.out.println("- v1.0                                 -");
		System.out.println("----------------------------------------");
		System.out.println("\nEscolha a opção desejada:");

	}

	public static int opcoesCliente(Pessoa pessoa, Conta conta) { // Se for cliente

		try {
			System.out.println(" (1) - Movimentações na Conta");
			System.out.println(" (2) - Relatórios");
			return sc.nextInt();

		} catch (Exception e) {
			System.out.println("Opção inválida!");
			menuCliente(pessoa, conta);
			return 0;
		}

	}

	public static int opcoesGerente(Pessoa pessoa, Conta conta) { // Se for funcionário
		try {
			System.out.println("1 - Relatórios");
			return sc.nextInt();

		} catch (Exception e) {
			System.out.println("Opção inválida!");
			menuCliente(pessoa, conta);
			return 0;
		}
	}

	public static int opcoesMovimentacaoConta(Pessoa pessoa, Conta conta) { // Se for cliente
		try {
			System.out.println("1 - Saque");
			System.out.println("2 - Deposito");
			System.out.println("3 - Transferencia");
			return sc.nextInt();

		} catch (Exception e) {
			System.out.println("Opção inválida!");
			opcoesMovimentacaoConta(pessoa, conta);
			return 0;
		}
	}

	public static int opcoesRelatoriosConta(Pessoa pessoa, Conta conta) {
		try {
			if (conta.getTipoConta().equals("CORRENTE")) {
				System.out.println("1 - Relatório de tributação da conta corrente");
				return sc.nextInt();
			} else if (conta.getTipoConta().equals("POUPANCA")) {
				int opcao = 0;
				System.out.println("1 - Relatório de Rendimento da poupança");
				if (sc.nextInt() == 1) {
					opcao = 2;
				}
				return opcao;
			} else {
				System.out.println("Tipo de conta inválida!\nEntre em contato com a sua agência.");
				menuCliente(pessoa, conta);
				return 0;
			}

		} catch (Exception e) {
			System.out.println("Opção inválida!");
			menuCliente(pessoa, conta);
			return 0;
		}
	}

	public static void menuCliente(Pessoa pessoa, Conta conta) {
		apresentar(pessoa, conta);

		if (opcoesCliente(pessoa, conta) == 1) { // 1 - Movimentações na Conta
			apresentar(pessoa, conta);

			switch (opcoesMovimentacaoConta(pessoa, conta)) {
			case 1: {// 1-Saque
				try {
					System.out.println("\nInforme o valor a ser sacado: ");
					conta.sacar(sc.nextDouble());
					menuCliente(pessoa, conta);

				} catch (Exception e) {
					System.out.println("\nDado inválido!\n");
					menuCliente(pessoa, conta);
				}

			}
			case 2: {// 2-Depósito
				try {
					System.out.println("\nInforme o valor a ser depositado: ");
					conta.depositar(sc.nextDouble(), false);
					menuCliente(pessoa, conta);
				} catch (Exception e) {
					System.out.println("\nDado inválido!\n");
					menuCliente(pessoa, conta);
				}
			}

			case 3: {// 3-Transferência

				try {
					double valor;
					System.out.println("\nInforme o valor a ser transferido: ");
					valor = sc.nextDouble();
					System.out.println("\nInforme o CPF do usuário de destino: ");
					Conta contaEncontrada = LeitorDeDados.buscaContaPorCpf(sc.next());
					if (conta != null) {
						conta.transferir(valor, contaEncontrada);
						menuCliente(pessoa, conta);
					}
					menuCliente(pessoa, conta);
				} catch (Exception e) {
					System.out.println("Conta não localizada!");
					menuCliente(pessoa, conta);
				}
			}

			}

		} else { // 2 - Relatórios
			if (opcoesRelatoriosConta(pessoa, conta) == 1) { // 1 - Relatório de tributação da conta corrente
				System.out.println("\n----------------------------------------");
				System.out.println("Relatório de tributação da conta corrente:");
				System.out.println("----------------------------------------");
				for (int i = 0; i < conta.getMovimentacoes().size(); i++) {

					String dataFormatada = FormataData
							.converterDateParaDataEHora(conta.getMovimentacoes().get(i).getData());

					dadosGeradorArquivo += " - " + conta.getMovimentacoes().get(i).getDescricao() + " em "
							+ dataFormatada + " foi taxado em R$ "
							+ formataDecimais.format(conta.getMovimentacoes().get(i).getTaxa()) + "\n";

				}

				if (conta.getMovimentacoes().isEmpty()) {

					System.out.println("- Não há dados a serem impressos!");
				}

				dadosGeradorArquivo += "\nTributações por operação\n- Saque e Depósito: R$ 0.10 e Transferência R$ 0.20.\n";

				System.out.println(dadosGeradorArquivo);
				System.out.println("----------------------------------------\n");

				try {
					GeradorDeArquivos.escreverArquivoMovimentacao(dadosGeradorArquivo, "Relatório de tributação CC");
				} catch (IOException e) {
					System.out.println("Não foi possível gerar o arquivo");
				}

				menuCliente(pessoa, conta);

			} else { // 2 - Relatório de Rendimento da poupança
				double valorAplicao = 0.0;
				int quantidadeDias = 0;

				System.out.println("Informe o valor da aplicação:");
				valorAplicao = sc.nextDouble();
				System.out.println("Informe a quantidade de dias para simulação:");
				quantidadeDias = sc.nextInt();

				System.out.println("\n----------------------------------------");
				System.out.println("Relatório de Rendimento da poupança:");
				System.out.println("----------------------------------------\n");

				ContaPoupanca contaSimulacao = new ContaPoupanca();

				dadosGeradorArquivo = contaSimulacao.simularRendimento(valorAplicao, quantidadeDias);
				try {
					GeradorDeArquivos.escreverArquivoMovimentacao(dadosGeradorArquivo, "Simulação de rendimentos");
				} catch (IOException e) {
					System.out.println("Não foi possível gerar o arquivo");
				}
				System.out.println("----------------------------------------\n");

				menuCliente(pessoa, conta);
			} 
		}

	}

	public static void menuFuncionario(Pessoa pessoa, ArrayList<Conta> contas) {
		switch (pessoa.getTipoPessoa()) {
		case "GERENTE":
			int opcaoGerente;
			System.out.println("Informe o relatório desejado: ");
			System.out.println(" 1 - Relatório de contas gerenciadas: ");
			opcaoGerente = sc.nextInt();

			if (opcaoGerente == 1) {
				System.out.println("Relatório de contas gerenciadas:");
				for (int i = 0; i < contas.size(); i++) {
					if (Integer.parseInt(pessoa.getAgenciaGerenciada()) == contas.get(i).getAgenciaTitular()) {
						dadosGeradorArquivo += " - Conta: " + contas.get(i).getNumeroConta() + "\n";
					}
				}
				try {
					GeradorDeArquivos.escreverArquivoMovimentacao(dadosGeradorArquivo,
							"Relatório de contas gerenciadas");
				} catch (IOException e) {
					System.out.println("Não foi possível gerar o arquivo");
				}
				System.out.println(dadosGeradorArquivo);

				voltar(pessoa, contas);
			} else {
				System.out.println("Opção inválida!");
				voltar(pessoa, contas);
			}
			break;

		case "DIRETOR":
			int opcaoDiretor;
			System.out.println("Informe o relatório desejado: ");
			System.out.println(" 1 - Relatório de contas gerenciadas: ");
			System.out.println(" 2 - Relatório de clientes: ");
			opcaoDiretor = sc.nextInt();

			if (opcaoDiretor == 1) {
				System.out.println("Relatório de contas gerenciadas:");
				for (int i = 0; i < contas.size(); i++) {
					if (Integer.parseInt(pessoa.getAgenciaGerenciada()) == contas.get(i).getAgenciaTitular()) {
						dadosGeradorArquivo += " - Conta: " + contas.get(i).getNumeroConta() + "\n";
					}
				}
				try {
					GeradorDeArquivos.escreverArquivoMovimentacao(dadosGeradorArquivo,
							"Relatório de contas gerenciadas");
				} catch (IOException e) {
					System.out.println("Não foi possível gerar o arquivo");
				}
				System.out.println(dadosGeradorArquivo);

				voltar(pessoa, contas);
			} else if (opcaoDiretor == 2) {
				System.out.println("\nRelatório de clientes:");

				for (Conta conta : contas) {
					dadosGeradorArquivo += " - Conta: " + conta.getNumeroConta() + "\n";
				}
				try {
					GeradorDeArquivos.escreverArquivoMovimentacao(dadosGeradorArquivo, "Relatório de clientes");
				} catch (IOException e) {
					System.out.println("Não foi possível gerar o arquivo");
				}
				System.out.println(dadosGeradorArquivo);

				voltar(pessoa, contas);
			} else {
				System.out.println("Opção inválida!");
				voltar(pessoa, contas);
			}

			break;

		case "PRESIDENTE":
			int opcaoPresidente;
			System.out.println("Informe o relatório desejado: ");
			System.out.println(" 1 - Relatório de contas gerenciadas: ");
			System.out.println(" 2 - Relatório de clientes: ");
			System.out.println(" 3 - Relatório com o valor total do capital armazenado no banco: ");
			opcaoPresidente = sc.nextInt();

			if (opcaoPresidente == 1) {
				System.out.println("Relatório de contas gerenciadas:");
				for (int i = 0; i < contas.size(); i++) {
					if (Integer.parseInt(pessoa.getAgenciaGerenciada()) == contas.get(i).getAgenciaTitular()) {
						dadosGeradorArquivo += " - Conta: " + contas.get(i).getNumeroConta() + "\n";
					}
				}
				try {
					GeradorDeArquivos.escreverArquivoMovimentacao(dadosGeradorArquivo,
							"Relatório de contas gerenciadas");
				} catch (IOException e) {
					System.out.println("Não foi possível gerar o arquivo");
				}
				System.out.println(dadosGeradorArquivo);

				voltar(pessoa, contas);
			} else if (opcaoPresidente == 2) {
				System.out.println("\nRelatório de clientes:");

				for (Conta conta : contas) {
					dadosGeradorArquivo += " - Conta: " + conta.getNumeroConta() + "\n";
				}
				try {
					GeradorDeArquivos.escreverArquivoMovimentacao(dadosGeradorArquivo, "Relatório de clientes");
				} catch (IOException e) {
					System.out.println("Não foi possível gerar o arquivo");
				}
				System.out.println(dadosGeradorArquivo);

				voltar(pessoa, contas);
			} else if (opcaoPresidente == 3) {
				System.out.println("\nRelatório com o valor total do capital armazenado no banco:");
				double saldoTotal = 0.0;
				for (Conta conta : contas) {
					saldoTotal += conta.getSaldoTitular();
				}

				dadosGeradorArquivo = "\nTotal: R$ " + saldoTotal;
				try {
					GeradorDeArquivos.escreverArquivoMovimentacao(dadosGeradorArquivo, "Capital armazedo");
				} catch (IOException e) {
					System.out.println("Não foi possível gerar o arquivo");
				}

				System.out.println("\n");
				System.out.println("Total: R$ " + dadosGeradorArquivo);
				voltar(pessoa, contas);
			} else {
				System.out.println("Opção inválida!");
				voltar(pessoa, contas);
			}

			break;

		}
	}

	public static void voltar(Pessoa pessoa, ArrayList<Conta> contas) {
		int opcaoVoltar;
		System.out.println("Digite a opção desejada:/n 1- Menu funcionário /n 2-Deslogar");
		opcaoVoltar = sc.nextInt();

		if (opcaoVoltar == 1) {
			menuFuncionario(pessoa, contas);
		} else {
			Login.logar();
		}
	}

}