package br.com.residencia.poo.projeto_sistema_bancario.menu;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import br.com.residencia.poo.projeto_sistema_bancario.contas.Conta;
import br.com.residencia.poo.projeto_sistema_bancario.date.FormataData;
import br.com.residencia.poo.projeto_sistema_bancario.pessoas.Pessoa;
import br.com.residencia.poo.projeto_sistema_bancario.repositorio.LeitorDeDados;

public class Login {

	public static void logar() {
		String usuario;
		String senha;
		Scanner sc = new Scanner(System.in);
		
		apresentar();
		System.out.println("Informe o usuário: ");
		usuario = sc.nextLine();

		System.out.println("Informe a senha..: ");
		senha = sc.nextLine();

		validarLogin(usuario, senha);

		sc.close();

	}

	public static void validarLogin(String usuario, String senha) {
		if (ValidaUsuario.validarUsuario(usuario, senha) == null) {
			System.out.println("\nUsuário ou senha inválido!\n");
			logar();

		} else {

			try {
				Pessoa pessoa = ValidaUsuario.validarUsuario(usuario, senha);
				ArrayList<Conta> contas = LeitorDeDados.leitorContas();

				System.out.println("\nUsuário logado com sucesso!\n");
				
				if(pessoa.getTipoPessoa().equals("CLIENTE")) {
					for (int i = 0; i < contas.size(); i++) {
						if (contas.get(i).getCpfTitular().equals(pessoa.getCpf())) {
							MenuPrincipal.selecionarMenu(pessoa, contas.get(i), contas);
						}
					}
				} else {
						MenuPrincipal.selecionarMenu(pessoa, contas.get(0), contas);
				}

			} catch (Exception e) {
				System.out.println("\nDados inválidos!\n");
			}
		}

	}
	
	public static void apresentar() {		
		String espacoDois = "";
		for (int t = 0; t <= 20; t++) {
			espacoDois += " ";
		}

		
		System.out.println("\n----------------------------------------");
		System.out.println("---    B A N C O  S E R R A T E C    ---");
		System.out.println("----------------------------------------");
		System.out.println("-" + espacoDois + FormataData.converterDateParaDataEHora(new Date()) + " -");
		System.out.println("----------------------------------------");
		System.out.println("-                                      -");
		System.out.println("-              Menu Login              -");
		System.out.println("-                                      -");
		System.out.println("- v1.0                                 -");
		System.out.println("----------------------------------------\n");
	}

}