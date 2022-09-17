package br.com.residencia.poo.projeto_sistema_bancario.movimentacao;

import java.util.Date;

import br.com.residencia.poo.projeto_sistema_bancario.date.FormataData;

public class Movimentacao {

	private String descricao;
	private Date data;
	private double valor;
	private double taxa;

	public Movimentacao(String descricao, double valor, double taxa) {
		super();
		this.descricao = descricao;
		this.data = new Date();
		this.valor = valor;
		this.taxa = taxa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public double getValor() {
		return valor;
	}

	public double getTaxa() {
		return taxa;
	}

	@Override
	public String toString() {
		String dataFormatada = FormataData.converterDateParaDataEHora(this.getData());
		return this.getDescricao() + " " + dataFormatada + " - R$ " + this.valor;
	}
}
