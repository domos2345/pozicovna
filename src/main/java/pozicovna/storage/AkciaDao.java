package pozicovna.storage;

import pozicovna.entities.Akcia;

import java.util.List;

public interface AkciaDao {

    List<Akcia> getByNaradieId(Long id);

}
