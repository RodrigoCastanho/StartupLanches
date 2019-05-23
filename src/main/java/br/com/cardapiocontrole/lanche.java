package br.com.cardapiocontrole;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "lanchesEscolha")
@ViewScoped
public class lanche {

	private BigDecimal valor = new BigDecimal("0.0");
	private BigDecimal valor2 = new BigDecimal("0.0");
	private BigDecimal valor3 = new BigDecimal("0.0");
	private BigDecimal valor4 = new BigDecimal("0.0");
	private BigDecimal totalvalorlanche = new BigDecimal("0.0");
	private int cont = 0;
	private int cont2 = 0;
	private int cont3 = 0;
	private int cont4 = 0;

	private HashMap<String, BigDecimal> listapedido = new HashMap<String, BigDecimal>();
	private HashMap<String, Integer> pedido = new HashMap<String, Integer>();

	ingredientes ing = new ingredientes();
	lanchePersonalizado lp = new lanchePersonalizado();

	public HashMap<String, BigDecimal> getlistapedido() {
		return listapedido;

	}

	public HashMap<String, Integer> getpedido() {
		return pedido;

	}

	public BigDecimal gettotalvalorlanche() {
		return totalvalorlanche;

	}

	public BigDecimal getvalor() {
		return valor;

	}

	public BigDecimal getvalor2() {
		return valor2;

	}

	public BigDecimal getvalor3() {
		return valor3;

	}

	public BigDecimal getvalor4() {
		return valor4;

	}

	public int getcont() {
		return cont;

	}

	public int getcont2() {
		return cont2;

	}

	public int getcont3() {
		return cont3;

	}

	public int getcont4() {
		return cont4;

	}

	HashMap<String, BigDecimal> ingredientes = ing.getingredientes();
	
    //Metodo utilizado para fazer pedido do lanche de valor padrão no cardapio
	public void pedidoLanche(String lanche) {

		if (lanche.equals("X-Bacon")) {
			cont++;

			for (Entry<String, BigDecimal> ingred : ingredientes.entrySet()) {

				if (ingred.getKey().equals("Bacon") || ingred.getKey().equals("Hamburguer de carne")
						|| ingred.getKey().equals("Queijo")) {

					valor = valor.add(ingred.getValue());

				}

			}
			listapedido.put(lanche, valor);
			pedido.put(lanche, cont);
			
			//DEBUG de valor e quantidade do metodo PedidoLanche()
			System.out.println("Valor Lanche: R$: " + valor.toString() + cont);

		}
		if (lanche.equals("X-Burger")) {
			cont2++;

			for (Entry<String, BigDecimal> ingred : ingredientes.entrySet()) {

				if (ingred.getKey().equals("Hamburguer de carne") || ingred.getKey().equals("Queijo")) {

					valor2 = valor2.add(ingred.getValue());
				}

			}
			listapedido.put(lanche, valor2);
			pedido.put(lanche, cont2);
			
			//DEBUG de valor e quantidade do metodo PedidoLanche()
			System.out.println("Valor Lanche: R$: " + valor2.toString() + cont2);

		}
		if (lanche.equals("X-Egg")) {
			cont3++;

			for (Entry<String, BigDecimal> ingred : ingredientes.entrySet()) {

				if (ingred.getKey().equals("Hamburguer de carne") || ingred.getKey().equals("Ovo")
						|| ingred.getKey().equals("Queijo")) {

					valor3 = valor3.add(ingred.getValue());

				}

			}
			listapedido.put(lanche, valor3);
			pedido.put(lanche, cont3);
			
			//DEBUG de valor e quantidade do metodo PedidoLanche()
			System.out.println("Valor Lanche: R$: " + valor3.toString() + cont3);

		}
		if (lanche.equals("X-EggBacon")) {
			cont4++;

			for (Entry<String, BigDecimal> ingred : ingredientes.entrySet()) {

				if (ingred.getKey().equals("Hamburguer de carne") || ingred.getKey().equals("Ovo")
						|| ingred.getKey().equals("Queijo") || ingred.getKey().equals("Bacon")) {

					valor4 = valor4.add(ingred.getValue());

				}

			}
			listapedido.put(lanche, valor4);
			pedido.put(lanche, cont4);
			
			//DEBUG de valor e quantidade do metodo PedidoLanche()
			System.out.println("Valor Lanche: R$: " + valor4.toString() + cont4);

		}
		totalvalorlanche = valor.add(valor2).add(valor3).add(valor4);
		
		//DEBUG da soma total dos valores do metodo PedidoLanche()
		System.out.println("Totalvalorlanche: " + totalvalorlanche);

	}
    //Metodo que faz o cancelamento de um pedido
	public void cancelarLanche(String lanche) {

		if (lanche.equals("X-Bacon")) {

			cont -= cont = 1;

			for (Entry<String, BigDecimal> ingred : ingredientes.entrySet()) {

				if (ingred.getKey().equals("Bacon") || ingred.getKey().equals("Hamburguer de carne")
						|| ingred.getKey().equals("Queijo")) {

					valor = valor.subtract(ingred.getValue());
					listapedido.put(lanche, valor);
					pedido.put(lanche, cont);
					totalvalorlanche = totalvalorlanche.subtract(ingred.getValue());

					if (cont <= 0) {
						cont = 0;
						valor = new BigDecimal(0.0);
						listapedido.put(lanche, valor);
						pedido.put(lanche, cont);
						totalvalorlanche = totalvalorlanche.subtract(valor);
					}
				}
			}

		} else if (lanche.equals("X-Burger")) {

			cont2 -= cont2 = 1;

			for (Entry<String, BigDecimal> ingred : ingredientes.entrySet()) {

				if (ingred.getKey().equals("Hamburguer de carne") || ingred.getKey().equals("Queijo")) {

					valor2 = valor2.subtract(ingred.getValue());
					listapedido.put(lanche, valor2);
					pedido.put(lanche, cont2);
					totalvalorlanche = totalvalorlanche.subtract(ingred.getValue());

					if (cont2 <= 0) {
						cont2 = 0;
						valor2 = new BigDecimal(0.0);
						listapedido.put(lanche, valor2);
						pedido.put(lanche, cont2);
						totalvalorlanche = totalvalorlanche.subtract(valor2);

					}
				}
			}

		} else if (lanche.equals("X-Egg")) {

			cont3 -= cont3 = 1;

			for (Entry<String, BigDecimal> ingred : ingredientes.entrySet()) {

				if (ingred.getKey().equals("Hamburguer de carne") || ingred.getKey().equals("Ovo")
						|| ingred.getKey().equals("Queijo")) {

					valor3 = valor3.subtract(ingred.getValue());
					listapedido.put(lanche, valor3);
					pedido.put(lanche, cont3);
					totalvalorlanche = totalvalorlanche.subtract(ingred.getValue());

					if (cont3 <= 0) {
						cont3 = 0;
						valor3 = new BigDecimal(0.0);
						listapedido.put(lanche, valor3);
						pedido.put(lanche, cont3);
						totalvalorlanche = totalvalorlanche.subtract(valor3);

					}
				}
			}

		} else if (lanche.equals("X-EggBacon")) {

			cont4 -= cont4 = 1;

			for (Entry<String, BigDecimal> ingred : ingredientes.entrySet()) {

				if (ingred.getKey().equals("Hamburguer de carne") || ingred.getKey().equals("Ovo")
						|| ingred.getKey().equals("Queijo") || ingred.getKey().equals("Bacon")) {

					valor4 = valor4.subtract(ingred.getValue());
					listapedido.put(lanche, valor4);
					pedido.put(lanche, cont4);
					totalvalorlanche = totalvalorlanche.subtract(ingred.getValue());

					if (cont4 <= 0) {
						cont4 = 0;
						valor4 = new BigDecimal(0.0);
						listapedido.put(lanche, valor4);
						pedido.put(lanche, cont4);
						totalvalorlanche = totalvalorlanche.subtract(valor4);

					}
				}
			}

		} else {
		}
	}
}
