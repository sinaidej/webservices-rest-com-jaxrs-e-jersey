package br.com.alura.loja.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Projeto {
	private long id;
	private String nome;	
	private int anoDeInicio;
	
	
	public Projeto (long id, String nome, int anoDeInicio) {
		this.id = id;
		this.nome = nome;		
		this.anoDeInicio = anoDeInicio;
	}
	
	public Projeto() { }
	
	public String getNome() {
		return nome;
	}
	public long getId() {
		return id;
	}
	public int getAnoDeInicio() {
		return anoDeInicio;
	}
	
	public void setId( long id ) {
		this.id = id;
	}
	
	public String toXML() {
		return new XStream().toXML(this);
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
	
	
	
}
