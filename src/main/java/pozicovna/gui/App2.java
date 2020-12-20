package pozicovna.gui;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pozicovna.entities.Akcia;
import pozicovna.entities.DruhNaradia;
import pozicovna.entities.Naradie;
import pozicovna.entities.Pouzivatel;
import pozicovna.storage.*;

public class App2 {

	public static void main(String[] args) {
		DaoFactory.INSTANCE.testing(); // NEMAZAT!!! :D

		NaradieDao naradieDao = DaoFactory.INSTANCE.getNaradieDao();
		PouzivatelDao pouzivatelDao = DaoFactory.INSTANCE.getPouzivatelDao();
		DruhNaradiaDao druhNaradiaDao = DaoFactory.INSTANCE.getDruhNaradiaDao();
		AkciaDao akciaDao = DaoFactory.INSTANCE.getAkciaDao();
		List<Akcia> akcie = new ArrayList<Akcia>();

//		pouzivatelDao.delete(pouzivatelDao.getByEmail("CCC").getId());

		System.out.println(druhNaradiaDao.getAll());

//		 pouzivatelDao.save(new Pouzivatel("please", "a", "cmon", "01", "h", "bb", "dr",
//		 "423", "094", "vr"));
//		pouzivatelDao.save(new Pouzivatel(null, "a", "test@", "01", "h", "bb", "dr", "423", "094", "vr"));
//		System.out.println(pouzivatelDao.getById(3).toString());
//		System.out.println(pouzivatelDao.getByEmail("a@").toString());

//		System.out.println(pouzivatelDao.delete(159));

		List<Pouzivatel> pouzivatelia = pouzivatelDao.getAll();
		System.out.println("LIST POUZIVATELOV");
		for (int i = 0; i < pouzivatelia.size(); i++) {
			System.out.println(pouzivatelia.get(i).toString());
		}

		Naradie naradie = new Naradie("EDIT", "t64", true, "vrtacka", pouzivatelDao.getById(13), "skuska ukladania",
				akcie, akciaDao, naradieDao);
//		naradieDao.save(naradie);

		List<Naradie> naradieList = naradieDao.getAll();
		System.out.println("LIST NARADI");
		for (int i = 0; i < naradieList.size(); i++) {
			System.out.println(naradieList.get(i).toString());
		}

		// System.out.println(naradieDao.getAll());
//		System.out.println("POZICANE MNOU");
//
//		System.out.println(naradieDao.getAllLentByVlastnikId(10));
//
		System.out.println("druhNaradiaSkuska");
		//druhNaradiaDao.save(new DruhNaradia("sraandy"));
		List<DruhNaradia> druhyNaradia = druhNaradiaDao.getAll();
		for (int i = 0; i < druhyNaradia.size(); i++) {
			System.out.println(druhyNaradia.get(i));
		}
//		DruhNaradia dn = druhNaradiaDao.getByMeno(naradie.getDruhNaradia());
//		System.out.println(dn);

	}

}