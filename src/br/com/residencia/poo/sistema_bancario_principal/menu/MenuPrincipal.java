package br.com.residencia.poo.sistema_bancario_principal.menu;

import br.com.residencia.poo.sistema_bancario_principal.pessoas.Pessoa;

public class MenuPrincipal {

	public static void selecionarMenu(Pessoa pessoa) {
		switch (pessoa.getTipoPessoa()) {
		case "1":
			System.out.println("Entrei em 1");
			break;

		case "2":
			System.out.println("Entrei em 2");
			break;

		case "3":
			System.out.println("Entrei em 3");
			break;

		case "4":
			System.out.println("Entrei em 4");
			break;

		case "5":
			System.out.println("Entrei em 5");
			break;

		default:
			break;
		}
	}

}
