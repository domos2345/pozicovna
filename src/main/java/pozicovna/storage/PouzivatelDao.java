package pozicovna.storage;

import pozicovna.entities.Pouzivatel;

import java.util.List;

public interface PouzivatelDao {

	List<Pouzivatel> getAll();
}
