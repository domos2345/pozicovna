package pozicovna.gui;

import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pozicovna.entities.Pouzivatel;
import pozicovna.storage.*;

public class App2  {

	public static void main(String[] args) {
		PouzivatelDao pouzivatelDao = DaoFactory.INSTANCE.getPouzivatelDao();
		List<Pouzivatel> pouzivatelia = pouzivatelDao.getAll();
		System.out.println(pouzivatelia);

	}

	
}