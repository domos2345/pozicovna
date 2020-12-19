package pozicovna.business;

import java.util.List;

public interface RequestsManager {

    public List<Request> getRequestsForNaradieOfPouzivatel(Long idVlastnik);

    public List<Request> getRequestsForNaradie(Long idNaradie);

}
