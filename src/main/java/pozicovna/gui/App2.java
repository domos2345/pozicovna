package pozicovna.gui;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pozicovna.entities.Naradie;
import pozicovna.entities.Pouzivatel;
import pozicovna.storage.*;

public class App2 {

	public static void main(String[] args) {
		DaoFactory.INSTANCE.testing(); // NEMAZAT!!! :D

		NaradieDao naradieDao = DaoFactory.INSTANCE.getNaradieDao();
		PouzivatelDao pouzivatelDao = DaoFactory.INSTANCE.getPouzivatelDao();
		DruhNaradiaDao druhNaradiaDao = DaoFactory.INSTANCE.getDruhNaradiaDao();

		System.out.println(druhNaradiaDao.getAll());

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

		List<Naradie> naradieList = naradieDao.getAll();
		System.out.println("LIST NARADI");
		for (int i = 0; i < naradieList.size(); i++) {
			System.out.println(naradieList.get(i).toString());
		}

		// System.out.println(naradieDao.getAll());
		System.out.println("POZICANE MNOU");

		System.out.println(naradieDao.getAllLentByVlastnikId(10));

	}

}