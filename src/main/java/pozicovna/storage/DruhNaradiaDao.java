package pozicovna.storage;

import java.util.List;

import pozicovna.entities.DruhNaradia;

public interface DruhNaradiaDao {

	List<DruhNaradia> getAll();

	DruhNaradia getByMeno(String meno) throws EntityNotFoundException;

	DruhNaradia getById(long id) throws EntityNotFoundException;

	DruhNaradia save(DruhNaradia druhNaradia) throws EntityNotFoundException;

	DruhNaradia delete(long id) throws EntityNotFoundException;
}
