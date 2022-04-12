package vacinacao;

import util.Data;

public class Vacina {

	private String nome;
	private Data dataFabricacao;
	private Data dataAplicacao;
	private Data dataValidade;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Data getDataFabricacao() {
		return dataFabricacao;
	}
	public void setDataFabricacao(Data dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}
	public Data getDataAplicacao() {
		return dataAplicacao;
	}
	public void setDataAplicacao(Data dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}
	public Data getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(Data dataValidade) {
		this.dataValidade = dataValidade;
	}
	
	
	
}
