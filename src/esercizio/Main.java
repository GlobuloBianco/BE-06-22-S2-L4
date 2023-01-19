package esercizio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	@SuppressWarnings("serial")
	public static void main(String[] args) {

		//prodotti
		ArrayList<Product> prodLista = new ArrayList<>() {{
			add(new Product(4123, "Libro Informatico", "Books", 132));
			add(new Product(4533, "Libro Controverso", "Books", 142));
			add(new Product(8512, "Fiabe per cani", "Books", 14.99));
			add(new Product(5126, "Giocattoli", "Baby", 68.99));
			add(new Product(9532, "Skateboard", "Boys", 32.99));
		}};
		//clienti
		Customer c1 = new Customer(1, "Cianni Norandi", 2);
		
		//ordini
		ArrayList<Order> orderLista = new ArrayList<>() {{
			add(new Order(412, "In Spedizione", LocalDate.of(2022, 12, 25),
					LocalDate.of(2023, 01, 20), prodLista, c1));
		}};
		
		//#1
		filtra(prodLista, "Books", 100);
		
		//#2
		filtraOrdine(orderLista, "Baby");
		
		//#3

	}
	
	//Esercizio #1
	//Ottenere una lista di prodotti che appartengono alla categoria «Books» ed hanno un prezzo > 100
	public static void filtra(ArrayList<Product> prodLista, String ctg, int prz) {
		
		Stream<Product> filtro = prodLista.stream()
					.filter( e -> e.getCategory().equals(ctg) && e.getPrice() > prz);
		
		filtro.forEach(e -> System.out.printf("Id: %d | Nome: %s | Categoria: %s | Prezzo: %.02fEuro%n",
												e.getId(), e.getName(), e.getCategory(), e.getPrice()));
	}
	
	//Esercizio #2
	//Ottenere una lista di ordini con prodotti che appartengono alla categoria «Baby»
	public static void filtraOrdine(ArrayList<Order> ordine, String ctg) {
		Stream<Order> ordiniFiltrati = ordine.stream()
				.filter( e -> e.getProducts().stream().anyMatch(p -> p.getCategory().equals(ctg)) );
		
		System.out.println("--------------------");
		ordiniFiltrati.forEach(e -> System.out.printf(" Id: %d%n Status: %s%n Data Ordine: %s%n Data Arrivo: %s%n Prodotti: %s%n Cliente: %s%n", e.getId(), e.getStatus(), e.getOrderDate(), e.getDeliveryDate(), e.getProducts(), e.getCustomer().getName()));
		System.out.println("--------------------");
	}
}
