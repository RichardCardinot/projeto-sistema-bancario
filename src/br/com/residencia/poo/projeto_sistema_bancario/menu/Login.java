package br.com.residencia.poo.projeto_sistema_bancario.menu;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.residencia.poo.projeto_sistema_bancario.contas.Conta;
import br.com.residencia.poo.projeto_sistema_bancario.pessoas.Pessoa;

public class Login {

	public static void logar() {
		String usuario;
		String senha;

		Scanner sc = new Scanner(System.in);

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

				for (int i = 0; i < contas.size(); i++) {
					if (contas.get(i).getCpfTitular().equals(pessoa.getCpf())) {
						MenuPrincipal.selecionarMenu(pessoa, contas.get(i), contas);
					}
				}

			} catch (Exception e) {
				System.out.println("\nDados inválidos!\n");
			}
		}

	}

}