package pozicovna.gui;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pozicovna.entities.Pouzivatel;
import pozicovna.storage.*;

public class App2 {

	public static void main(String[] args) {
		DaoFactory.INSTANCE.testing(); // NEMAZAT!!! :D

		PouzivatelDao pouzivatelDao = DaoFactory.INSTANCE.getPouzivatelDao();
		 pouzivatelDao.save(new Pouzivatel("a", "a", "a@", "01", "h", "bb", "dr",
		 "423", "094", "vr"));
		//System.out.println(pouzivatelDao.delete(pouzivatelDao.getById(4).getId()));
		List<Pouzivatel> pouzivatelia = pouzivatelDao.getAll();
		System.out.println(pouzivatelia);

//		String sol_hash = BCrypt.gensalt();
//		String heslo_hash = BCrypt.hashpw("heslo123", sol_hash);
//		System.out.println(heslo_hash);

	}

}