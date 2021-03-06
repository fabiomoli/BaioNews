package it.molis.baionetta.beans;

import java.util.ArrayList;
import java.util.List;

public class Penna {

	private String nome;
	private List<Articolo> articoli = new ArrayList<>();
	private List<Mostrina> mostrine = new ArrayList<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Penna(String nome) {
		super();
		this.nome = nome;
	}

	public List<Articolo> getAllArticoli() {
		return articoli;
	}

	public void addArticoli(Articolo a) {
		articoli.add(a);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Penna other = (Penna) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public List<Mostrina> getMostrine() {
		return mostrine;
	}

	public void setMostrine(List<Mostrina> mostrine) {
		this.mostrine = mostrine;
	}

}
