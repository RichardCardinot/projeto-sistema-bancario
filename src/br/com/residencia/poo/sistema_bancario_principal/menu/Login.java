package br.com.residencia.poo.sistema_bancario_principal.menu;

import java.util.Scanner;

public class Login {

	public static void logar() {
		String usuario;
		String senha;

		Scanner sc = new Scanner(System.in);

		System.out.println("Informe o usuário: ");
		usuario = sc.nextLine();

		System.out.println("Informe a senha..: ");
		senha = sc.nextLine();

		;
		if (ValidaUsuario.validarUsuario(usuario, senha) == null) {
			System.out.println("Usuário ou senha inválido!");
			logar();

		} else {
			System.out.println("Usuário logado com sucesso!");
			MenuPrincipal.selecionarMenu(ValidaUsuario.validarUsuario(usuario, senha));

		}

		sc.close();
	}
}
