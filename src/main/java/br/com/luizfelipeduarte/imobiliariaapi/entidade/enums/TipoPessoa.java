package br.com.luizfelipeduarte.imobiliariaapi.entidade.enums;

public enum TipoPessoa {
	
	INQUILINO(1,"Inquilino"),
	PROPRIETARIO(2, "Proprietário"),
	PROPRIETARIO_E_INQUILINO(3,"Proprietário e Inquilino");
	
	private int cod;
	private String descricao;
	
	private TipoPessoa(int cod, String descricao) {
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
	
	public static TipoPessoa toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(TipoPessoa x: TipoPessoa.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id Inválido " + cod);
	}
	
}
