package br.com.residencia.poo.sistema_bancario_principal.menu;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.residencia.poo.sistema_bancario_principal.contas.Conta;
import br.com.residencia.poo.sistema_bancario_principal.pessoas.Pessoa;

public class Login {

	public static void logar() {
		String usuario;
		String senha;

		Scanner sc = new Scanner(System.in);

		System.out.println("Informe o usuário: ");
		usuario = sc.nextLine();

		System.out.println("Informe a senha..: ");
		senha = sc.nextLine();

		if (ValidaUsuario.validarUsuario(usuario, senha) == null) {
			System.out.println("Usuário ou senha inválido!");
			logar();

		} else {

			try {
				Pessoa pessoa = ValidaUsuario.validarUsuario(usuario, senha);
				ArrayList<Conta> contas = LeitorDeDados.leitorContas();
				
				System.out.println("Usuário logado com sucesso!");
				
				for (int i = 0; i < contas.size(); i++) {
					if (contas.get(i).getCpfTitular().equals(pessoa.getCpf())) {
						MenuPrincipal.selecionarMenu(pessoa, contas.get(i));
					}
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		sc.close();

	}

}