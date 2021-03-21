package serverSide;

import java.util.Random;

public class Article {

	private static enum articleType {
		musique,
		films,
		clips;
		
		public static articleType getRandomType() {
			Random random = new Random();
			return values()[(random.nextInt(values().length))];
		}
	}
	
	private articleType type;
	private String text;
	private double prix;
	private int dispo;
	
	public Article(String text) {
		this.type = articleType.getRandomType();
		this.text = text;
		this.prix = new Random().nextDouble();
		this.dispo = 0;	
	}
}

