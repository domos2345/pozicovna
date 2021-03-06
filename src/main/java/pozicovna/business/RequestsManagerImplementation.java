package pozicovna.business;

import pozicovna.entities.Akcia;
import pozicovna.entities.Naradie;
import pozicovna.storage.DaoFactory;
import pozicovna.storage.NaradieDao;

import java.util.ArrayList;
import java.util.List;

public class RequestsManagerImplementation implements RequestsManager {
	NaradieDao naradieDao = DaoFactory.INSTANCE.getNaradieDao();

	@Override
	public List<Request> getRequestsForNaradieOfPouzivatel(Long idVlastnik) {
		List<Naradie> mojeNaradie = naradieDao.getByVlastnikId(idVlastnik);
		List<Request> result = new ArrayList<>();

		for (Naradie naradie : mojeNaradie) {
			for (Akcia akcia : naradie.getAkcie()) {
				if (	akcia.getZiadost() != null &&
						akcia.getPozicane() == null &&
						akcia.getZamietnute() == null ) {
					result.add(new Request(naradie, akcia));
				}
			}
		}
		return result;
	}

	@Override
	public List<Request> getRequestsForNaradie(Long idNaradie) {
		Naradie naradie = naradieDao.getById(idNaradie);
		List<Request> result = new ArrayList<>();

		for (Akcia akcia : naradie.getAkcie()) {
			if (	akcia.getZiadost() != null &&
					akcia.getPozicane() == null &&
					akcia.getZamietnute() == null ) {
				result.add(new Request(naradie, akcia));
			}
		}
		return result;
	}
}