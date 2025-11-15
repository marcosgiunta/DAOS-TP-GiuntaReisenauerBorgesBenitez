package tuti.daos.entidades;


import jakarta.persistence.*;
@Entity
@PrimaryKeyJoinColumn(name = "id_ingrediente")
public class Producto extends Ingrediente {

	private Double stock;
	private Float precio;
	
	public Double getStock() {
		return stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}
	
	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

}
