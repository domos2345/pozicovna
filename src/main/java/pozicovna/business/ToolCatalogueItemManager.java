package pozicovna.business;

import pozicovna.entities.Naradie;
import pozicovna.entities.Pouzivatel;

import java.util.List;

public interface ToolCatalogueItemManager {

	List<ToolCatalogueItem> getToolCatalogueItemsNotOwnedBy(Long vlastnikId);

	List<ToolCatalogueItem> getOwnedToolCatalogueItems(Long vlastnikId);

	List<ToolCatalogueItem> getAllToolCatalogueItems();
}
