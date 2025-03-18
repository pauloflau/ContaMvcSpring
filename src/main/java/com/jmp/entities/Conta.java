package com.jmp.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="conta")
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 200)
	private String nome;
	
	@Column(nullable = false)
	private Date data;
	
	@Column(nullable = false)
	private Double valor;
	
	@Column(nullable = false, length = 500)
	private String descricao;
	
	@Column(nullable = false)
	private Integer tipo;
	
	@ManyToOne
    @JoinColumn(name = "idusuario", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

	public Conta() {
		// TODO Auto-generated constructor stub
	}

	public Conta(Integer id, String nome, Date data, Double valor, String descricao, Integer tipo, Integer idUsuario,
			Usuario usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.data = data;
		this.valor = valor;
		this.descricao = descricao;
		this.tipo = tipo;
		this.usuario = usuario;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ", nome=" + nome + ", data=" + data + ", valor=" + valor + ", descricao=" + descricao
				+ ", tipo=" + tipo + ", usuario=" + usuario + "]";
	}
	
}
