package pozicovna.business;

import pozicovna.entities.Naradie;
import pozicovna.storage.DaoFactory;
import pozicovna.storage.NaradieDao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ToolCatalogueItemManagerImplementation implements ToolCatalogueItemManager {
	NaradieDao naradieDao = DaoFactory.INSTANCE.getNaradieDao();

	@Override
	public List<ToolCatalogueItem> getToolCatalogueItemsNotOwnedBy(Long vlastnikId) {
		List<Naradie> naradia = naradieDao.getAll();
		List<ToolCatalogueItem> result = new ArrayList<>();
		for (Naradie naradie : naradia)
			if (naradie.getVlastnik().getId() != vlastnikId)
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

	@Override
	public List<ToolCatalogueItem> getAllToolCatalogueItems() {
		return naradieDao.getAll().stream().map(naradie -> new ToolCatalogueItem(naradie)).collect(Collectors.toList());
	}
}
