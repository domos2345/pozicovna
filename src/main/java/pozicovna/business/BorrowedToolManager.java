package pozicovna.business;

import pozicovna.entities.Pouzivatel;

import java.util.List;

public interface BorrowedToolManager {

    List<BorrowedTool> getBorrowedTools(Long id);

}
