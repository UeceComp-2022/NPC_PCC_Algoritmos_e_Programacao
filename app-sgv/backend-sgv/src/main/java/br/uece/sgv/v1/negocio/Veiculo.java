package br.uece.sgv.v1.negocio;

import java.time.LocalDateTime;

/**
 * Classe de domínio de negócio
 * @author marcos.eduardo
 */
public class Veiculo {
	
    private Long id;
    private String valor1;//marca
    private String valor2;//modelo
    private LocalDateTime valor3;//data
    private String valor4;// placa
    private String valor5;// quilometragem

    // Construtor
    public Veiculo(Long id, String valor1, String valor2, String valor4, String valor5) {
        this.id = id;
        this.valor1 = valor1;
        this.valor2 = valor2;
        this.valor4 = valor4;
        this.valor5 = valor5;
    }
    public Veiculo(Long id, String valor1, String valor2, String valor3, String valor4, String valor5) {
        this.id = id;
        this.valor1 = valor1;
        this.valor2 = valor2;
        this.valor3 = valor3 != null ? LocalDateTime.parse(valor3) : null;
        this.valor4 = valor4;
        this.valor5 = valor5;
    }
    // Construtor
    public Veiculo(String valor1, String valor2) {
        this.valor1 = valor1;
        this.valor2 = valor2;
    }
    public Veiculo(Long id) {
        this.id = id;
    }
    public Veiculo() {
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
    public String getValor4() {
        return valor4;
    }

    public void setValor4(String valor4) {
        this.valor4 = valor4;
    }
    public String getValor5() {
        return valor5;
        }

    public void setValor5(String valor5) {
         this.valor5 = valor5;        
    }     
    
}
