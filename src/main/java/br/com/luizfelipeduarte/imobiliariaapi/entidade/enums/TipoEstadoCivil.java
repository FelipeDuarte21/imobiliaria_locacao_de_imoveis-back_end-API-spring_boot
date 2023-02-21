package br.com.luizfelipeduarte.imobiliariaapi.entidade.enums;

public enum TipoEstadoCivil {
	
	SOLTEIRO(1,"Solteiro(a)"),
	CASADO(2, "Casado(a)"),
	SEPARADO(3, "Separado(a)"),
	DIVORCIADO(4, "Divorciado(a)"),
	VIUVO(5, "Viúvo(a)");
	
	private int cod;
	private String descricao;
	
	private TipoEstadoCivil(int cod, String descricao) {
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
	
	public static TipoEstadoCivil toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(TipoEstadoCivil x: TipoEstadoCivil.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id Inválido" + cod);
	}
	
}
