package pozicovna.business;


import java.util.List;

public interface BorrowedToolManager {

    List<BorrowedTool> getBorrowedTools(Long id);

    List<BorrowedTool> getLendedTools(Long idVlastnika);

}
