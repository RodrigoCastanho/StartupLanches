package br.com.cardapiocontrole;

import java.math.BigDecimal;
import java.util.HashMap;

public class ingredientes {

  
	 HashMap<String, BigDecimal> ingredientesBase = new HashMap<>();

	//Metodo que se encarrega de controlar ingredientes e valores para as demais class do sistema
	//funciona como uma base de dados, pode facilmente se alterar os valores Ex: ("2.00") para ("4.00").
	public HashMap<String, BigDecimal> getingredientes() {

		ingredientesBase.merge("Bacon", new BigDecimal("2.00"), BigDecimal::add);
		ingredientesBase.merge("Hamburguer de carne", new BigDecimal("3.00"), BigDecimal::add);
		ingredientesBase.merge("Queijo", new BigDecimal("1.50"), BigDecimal::add);
		ingredientesBase.merge("Ovo", new BigDecimal("0.80"), BigDecimal::add);
		ingredientesBase.merge("Alface", new BigDecimal("0.40"), BigDecimal::add);

		return ingredientesBase;
	}
	

}
