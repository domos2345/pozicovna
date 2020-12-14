package pozicovna.gui;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pozicovna.entities.Akcia;
import pozicovna.entities.Naradie;
import pozicovna.entities.Pouzivatel;
import pozicovna.storage.*;

public class App2 {

	public static void main(String[] args) {
		DaoFactory.INSTANCE.testing(); // NEMAZAT!!! :D

		NaradieDao naradieDao = DaoFactory.INSTANCE.getNaradieDao();
		PouzivatelDao pouzivatelDao = DaoFactory.INSTANCE.getPouzivatelDao();

//		 pouzivatelDao.save(new Pouzivatel("please", "a", "cmon", "01", "h", "bb", "dr",
//		 "423", "094", "vr"));
//		pouzivatelDao.save(new Pouzivatel(null, "a", "test@", "01", "h", "bb", "dr", "423", "094", "vr"));
//		System.out.println(pouzivatelDao.getById(3).toString());
//		System.out.println(pouzivatelDao.getByEmail("a@").toString());

		// System.out.println(pouzivatelDao.delete(pouzivatelDao.getById(4).getId()));

//		List<Pouzivatel> pouzivatelia = pouzivatelDao.getAll();
//		System.out.println("LIST POUZIVATELOV");
//		for (int i = 0; i < pouzivatelia.size(); i++) {
//			System.out.println(pouzivatelia.get(i).toString());
//		}

//		List<Naradie> naradieList = naradieDao.getAll();
//		System.out.println("LIST NARADI");
//		for (int i = 0; i < naradieList.size(); i++) {
//			System.out.println(naradieList.get(i).toString());
//		}

//		AkciaDao akciaDao = DaoFactory.INSTANCE.getAkciaDao();
//
//		Akcia akcia1 = akciaDao.getByNaradieId(1L).get(0);
//		System.out.println(akcia1);
//		Akcia saved = akciaDao.save(new Akcia(akcia1.getKomu(), akcia1.getZiadost(), akcia1.getZamietnute(), akcia1.getPozicane(), akcia1.getVratene()), 1L);
//		System.out.println(saved);
//		saved = akciaDao.save(new Akcia(akcia1.getId(), akcia1.getKomu(), akcia1.getZiadost(), akcia1.getZiadost(), akcia1.getPozicane(), akcia1.getVratene()), 1L);
//		System.out.println(saved);


	}

}