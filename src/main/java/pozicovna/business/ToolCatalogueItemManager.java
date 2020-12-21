package pozicovna.business;

import java.util.List;

public interface ToolCatalogueItemManager {

	List<ToolCatalogueItem> getToolCatalogueItemsNotOwnedBy(Long vlastnikId);

	List<ToolCatalogueItem> getOwnedToolCatalogueItems(Long vlastnikId);

	List<ToolCatalogueItem> getAllToolCatalogueItems();
}
