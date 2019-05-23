package br.com.cardapiocontrole;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "lanchePersonalizado")
@ViewScoped
public class lanchePersonalizado {

	private BigDecimal valorprolight = new BigDecimal("0.0");
	private BigDecimal valorpromuito = new BigDecimal("0.0");
	private BigDecimal totalpromuito = new BigDecimal("0.0");
	private BigDecimal totalpromolight = new BigDecimal("0.0");
	private BigDecimal descontolight = new BigDecimal("0.0");
	private BigDecimal descontcarne = new BigDecimal("0.0");
	private BigDecimal descontqueijo = new BigDecimal("0.0");
	private BigDecimal descontocarn = new BigDecimal("0.0");
	private BigDecimal descontoqueij = new BigDecimal("0.0");
	private BigDecimal totaldescontos = new BigDecimal("0.0");
	private BigDecimal dezporcento = new BigDecimal("0.0");
	private BigDecimal plight = new BigDecimal("0.0");
	private BigDecimal pmuito = new BigDecimal("0.0");
	private BigDecimal totalpromocao = new BigDecimal("0.0");
	private BigDecimal valorcarne = new BigDecimal("0.0");
	private BigDecimal valorqueijo = new BigDecimal("0.0");
	private int qbacon = 0;
	private int qhamburguer = 0;
	private int qqueijo = 0;
	private int qovo = 0;
	private int qalface = 0;
	private HashMap<String, BigDecimal> controleingred = new HashMap<String, BigDecimal>();

	int quantiham = 0;
	int quantiqueijo = 0;
	boolean alface = false;
	boolean bacon = false;

	public BigDecimal gettotalpromocao() {
		return totalpromocao;

	}
	public BigDecimal getplight() {
		return plight; 
		
	}
	public BigDecimal getdescontocarn() {
		return descontocarn; 
		
	}
	public BigDecimal getdescontoqueij() {
		return descontoqueij;
		
	}
	
	public HashMap<String, BigDecimal> getcontroleingred() {
		return controleingred;

	}
	public int getqbacon() {
		return qbacon;
		
	}
	public int getqhamburguer() {
		return qhamburguer;
		
	}
	public int getqqueijo() {
		return qqueijo;
		
	}
	public int getqovo() {
		return qovo;
		
	}
	public int getqalface() {
		return qalface;
		
	}
	
	ingredientes ing = new ingredientes();


	HashMap<String, BigDecimal> ingredientes = ing.getingredientes();
    
    //Metodo que faz escolha dos ingredientes adicionais ou montagem de um pedido personalizado
	public void montaLanche(String ingredescolhido) {
		

		for (Entry<String, BigDecimal> ingred : ingredientes.entrySet()) {

			if (ingredescolhido.equals(ingred.getKey())) {

				plight = promocaoLight(ingredescolhido);

				pmuito = promocaoMuito(ingredescolhido);
				
				quantidadeIngredientes(ingredescolhido);
				 
				controleingred.merge(ingred.getKey(), ingred.getValue(), BigDecimal::add);

			}

		}
		totalpromocao = pmuito.subtract(plight);
		
		//DEBUG passagem de valores do metodo MontaLanche() para esse teste
		System.out.println("\nLight: " + plight + " Muito " + pmuito + " Total promocao: " + totalpromocao);

	}
    //Metodo utilizado para marca a quantidade de ingredientes foram pedidos 
	public void quantidadeIngredientes(String ingrediente) {

		if (ingrediente.equals("Bacon")) {
			qbacon++;

		}
		if (ingrediente.equals("Hamburguer de carne")) {
			qhamburguer++;

		}
		if (ingrediente.equals("Queijo")) {
			qqueijo++;

		}
		if (ingrediente.equals("Ovo")) {
			qovo++;

		}
		if (ingrediente.equals("Alface")) {
			qalface++;

		}

	}
	
	//Metodo utilizado para cancelar ingrediente 
	public void cancelar() {
        
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("lanchePersonalizado");
	
	}
    
	/*Regra de Negocio*/
	//Metodo utilizado para fazer o calculo da promoção light
	private BigDecimal promocaoLight(String ingredprolight) {

		for (Entry<String, BigDecimal> ingred : ingredientes.entrySet()) {

			if (ingredprolight.equals(ingred.getKey())) {

				if (ingredprolight.equals("Alface")) {

					alface = true;

				}
				if (ingredprolight.equals("Bacon")) {

					bacon = true;

				}
				valorprolight = valorprolight.add(ingred.getValue());

				System.out.println(ingred.getKey() + " : " + ingred.getValue());
			}

		}
		if (alface && !bacon) {
			System.out.println("Quanho Promocao Light");
			dezporcento = (new BigDecimal(10)).divide(new BigDecimal(100));
			descontolight = valorprolight.multiply(dezporcento).setScale(2, RoundingMode.DOWN);

		}
		totalpromolight = valorprolight;
		totalpromolight = totalpromolight.subtract(descontolight);
		
        //DEBUG dos valores do metodo promoção Light para esse teste
		System.out.println("Ingredientes: " + ingredprolight + " Total a pagar: " + "R$ " + totalpromolight.toString()
				+ " Valor com desconto 10% R$ " + descontolight.toString());

		return descontolight;

	}

	/*Regra de Negocio*/
	//Metodo utilizado para fazer o calculo da promoção muita carne e muito queijo
	private BigDecimal promocaoMuito(String ingredpromuito) {
		

		for (Entry<String, BigDecimal> ingred : ingredientes.entrySet()) {

			if (ingredpromuito.equals(ingred.getKey())) {
				if (ingredpromuito.equals("Queijo")) {
					quantiqueijo += quantiqueijo = 1;	
					valorqueijo = ingred.getValue();			
				}

				if (ingredpromuito.equals("Hamburguer de carne")) {
					quantiham += quantiham = 1;
					valorcarne = ingred.getValue();
				}
				System.out.println(ingred.getKey() + " : " + ingred.getValue());
				valorpromuito = valorpromuito.add(ingred.getValue());
			}
		}
		descontcarne = new BigDecimal(quantiham / 3).setScale(2, RoundingMode.DOWN);
		descontqueijo = new BigDecimal(quantiqueijo / 3).setScale(1, RoundingMode.DOWN);
		descontocarn =  valorcarne.multiply(descontcarne).setScale(2, RoundingMode.DOWN);;
		descontoqueij = valorqueijo.multiply(descontqueijo).setScale(2, RoundingMode.DOWN);
		totaldescontos = descontocarn.add(descontoqueij);

		totalpromuito = valorpromuito.subtract(totaldescontos);
        
		//DEBUG dos valores do metodo promoção muita carne e muito queijo em tempo de execução para esse teste
		System.out.println("Quantidade de Hamburguer de carne: " + quantiham);
		System.out.println("Ingredientes: " + ingredpromuito + " Total a pagar: " + "R$ " + totalpromuito.toString()
				+ "desconto carne:" + descontocarn + "Queijo desconta" + descontoqueij);

		return totalpromuito;
	}

}
