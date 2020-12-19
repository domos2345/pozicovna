package pozicovna.business;

import pozicovna.entities.Akcia;
import pozicovna.entities.Naradie;
import pozicovna.storage.DaoFactory;
import pozicovna.storage.NaradieDao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BorrowedToolManagerImplementation implements BorrowedToolManager {
	NaradieDao naradieDao = DaoFactory.INSTANCE.getNaradieDao();

	@Override
	public List<BorrowedTool> getBorrowedTools(Long id) {
		List<Naradie> pozicaneNaradie = naradieDao.getByBorrowedToId(id);
		List<BorrowedTool> result = new ArrayList<>();
		for (Naradie naradie : pozicaneNaradie) {
			for (Akcia akcia : naradie.getAkcie()) {
				if (akcia.getZiadatel().getId() == id) {
					result.add(new BorrowedTool(naradie, akcia));
				}
			}
		}
		return result;
	}

	@Override
	public List<BorrowedTool> getLendedTools(Long vlastnikId) {
		return naradieDao.getAllLentByVlastnikId(vlastnikId)
			.stream()
			.flatMap(
				naradie -> naradie.getAkcie()
					.stream()
					.filter(akcia -> akcia.getPozicane() != null)
					.map(akcia -> new BorrowedTool(naradie, akcia))
			).collect(Collectors.toList());
	}
}