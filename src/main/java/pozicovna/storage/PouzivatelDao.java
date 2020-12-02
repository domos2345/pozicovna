package pozicovna.storage;

import pozicovna.entities.Pouzivatel;

import java.util.List;

public interface PouzivatelDao {

	List<Pouzivatel> getAll();

	Pouzivatel getById(long id) throws EntityNotFoundException;

	Pouzivatel save(Pouzivatel pouzivatel) throws EntityNotFoundException;

	Pouzivatel delete(long id) throws EntityNotFoundException;
}
