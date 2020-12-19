package pozicovna.business;

import pozicovna.entities.Naradie;
import pozicovna.entities.Pouzivatel;
import pozicovna.storage.DaoFactory;
import pozicovna.storage.NaradieDao;

import java.util.ArrayList;
import java.util.List;

public class ToolCatalogueItemManagerImplementation implements ToolCatalogueItemManager {
	NaradieDao naradieDao = DaoFactory.INSTANCE.getNaradieDao();

	@Override
	public List<ToolCatalogueItem> getToolCatalogueItemsNotOwnedBy(Long vlastnikId) {
		List<Naradie> naradia = naradieDao.getAll();
		List<ToolCatalogueItem> result = new ArrayList<>();
		for (Naradie naradie : naradia)
			if(naradie.getVlastnik().getId()!=vlastnikId)
				result.add(new ToolCatalogueItem(naradie));
		return result;
	}

	@Override
	public List<ToolCatalogueItem> getOwnedToolCatalogueItems(Long vlastnikId) {
		List<Naradie> naradia = naradieDao.getByVlastnikId(vlastnikId);
		List<ToolCatalogueItem> result = new ArrayList<>();
		for (Naradie naradie : naradia)
			result.add(new ToolCatalogueItem(naradie));
		return result;
	}
}
