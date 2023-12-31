package br.uece.sgv.v1.apresentacao.dto;

import java.time.LocalDateTime;

/**
 * Classe de domínio de negócio
 * @author marcos.eduardo
 */
public class VeiculoDto {
	
    private Long id;
    private String valor1;
    private String valor2;
    private LocalDateTime valor3;

    // Construtor
    public VeiculoDto(Long id, String valor1, String valor2, LocalDateTime valor3) {
        this.id = id;
        this.valor1 = valor1;
        this.valor2 = valor2;
        this.valor3 = valor3;
    }
    // Construtor
    public VeiculoDto(String valor1, String valor2) {
        this.valor1 = valor1;
        this.valor2 = valor2;
    }
    
    public VeiculoDto() {
	}
    
	// Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
		this.id = id;
	}

	public String getValor1() {
        return valor1;
    }

    public void setValor1(String valor1) {
        this.valor1 = valor1;
    }

    public String getValor2() {
        return valor2;
    }

    public void setValor2(String valor2) {
        this.valor2 = valor2;
    }
	public LocalDateTime getValor3() {
		return valor3;
	}
	public void setValor3(LocalDateTime valor3) {
		this.valor3 = valor3;
	}
    
}
