package DomainModel;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany
	private ArrayList<Contract> contractList = new ArrayList<Contract>();
	
	private String name;
	private RecognitionStrategy recognitionStrategy;
	
	

	// Maintain the default constructor
	public Product() {

	}

	public Product(String name, RecognitionStrategy recognitionStrategy) {
		this.name = name;
		this.recognitionStrategy = recognitionStrategy;
	}

	public static Product newWordProcessor(String name) {
		return new Product(name, new CompleteRecognitionStrategy());
	}

	public static Product newSpreadsheet(String name) {
		return new Product(name, new ThreeWayRecognitionStrategy(60, 90));
	}

	public static Product newDatabase(String name) {
		return new Product(name, new ThreeWayRecognitionStrategy(30, 60));
	}

	void calculateRevenueRecognitions(Contract contract) {
		recognitionStrategy.calculateRevenueRecognitions(contract);
	}
}
