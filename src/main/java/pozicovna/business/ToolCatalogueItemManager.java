package pozicovna.business;

import pozicovna.entities.Naradie;
import pozicovna.entities.Pouzivatel;

import java.util.List;

public interface ToolCatalogueItemManager {

	List<ToolCatalogueItem> getToolCatalogueItems();

	List<ToolCatalogueItem> getOwnedToolCatalogueItems(Long vlastnikId);

}
