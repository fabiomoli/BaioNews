package feed;

import java.io.IOException;

import baionetta.Articolo;
import baionetta.Mostrina;
import baionetta.Penna;
import controller.BaioController;

public class Main {

	public static void main(String[] args) {

		BaioController bc = new BaioController();

		//for(Mostrina m : bc.getAllMostrine()){
		//	System.out.println(m.getMostrina());
		//}

		//for(Penna p : bc.getAllPenne()){
		//	System.out.println(p.getNome());
		//}

		//for(Articolo a : bc.getAllArticoliFromPenna(new Penna("Daniele Barale"))){
		//	System.out.println(a.getTitolo());
		//}
		//for(Articolo a : bc.getAllArticoliFromMostrina(new Mostrina("Obice"))){
		//	System.out.println(a.getTitolo());
		//}
		try {
			bc.getArticoliFromFile();
			bc.getArticoliFromRss();
			System.out.println(bc.getAllArticoli().size());
			bc.updateFileBackup();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//for(Articolo a : bc.getAllArticoliFromPenna(new Penna("Daniele Barale"))){
		//	System.out.println(a.getTitolo());
		//}

		//for(Articolo a : bc.getArticoloFromTitolo("realismo")){
		//	System.out.println(a.getTitolo() + a.getArticoloId());
		//}

		for(int i = 0; i<5; i++){
			System.out.println(bc.get5ArticoliOrderByDate().get(i).getTitolo());
			System.out.println(bc.get5ArticoliOrderByDate().get(i).getData());
		}
		
		for(Articolo a : bc.getAllArticoliFromMostrina(new Mostrina("Cinematografo dell'alpino"))){
				System.out.println(a.getTitolo());
	}

    }

}
