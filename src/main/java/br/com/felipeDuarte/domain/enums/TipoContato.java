package br.com.felipeDuarte.domain.enums;

public enum TipoContato {

	TELEFONE(1,"Telefone"),
	CELULAR(2, "Celular");
	
	private int cod;
	private String descricao;
	
	private TipoContato(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static TipoContato toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoContato x: TipoContato.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id Inválido " + cod);
	}
	
}
