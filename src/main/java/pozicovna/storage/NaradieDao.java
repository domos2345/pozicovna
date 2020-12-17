package pozicovna.storage;

import java.util.List;

import pozicovna.entities.Naradie;
import pozicovna.entities.Pouzivatel;

public interface NaradieDao {

	List<Naradie> getAll();

	Naradie getById(long id) throws EntityNotFoundException;

	List<Naradie> getByVlastnikId(long id) throws EntityNotFoundException;

	List<Naradie> getByBorrowedToId(long id);

	List<Naradie> getAllLentByVlastnikId(long id) throws EntityNotFoundException;

	Naradie save(Naradie naradie) throws EntityNotFoundException;

	Naradie delete(long id) throws EntityNotFoundException;
}
