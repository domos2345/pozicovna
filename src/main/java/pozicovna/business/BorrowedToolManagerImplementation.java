package pozicovna.business;

import pozicovna.entities.Akcia;
import pozicovna.entities.Naradie;
import pozicovna.storage.DaoFactory;
import pozicovna.storage.NaradieDao;

import java.util.ArrayList;
import java.util.List;

public class BorrowedToolManagerImplementation implements BorrowedToolManager {
    NaradieDao naradieDao = DaoFactory.INSTANCE.getNaradieDao();

    @Override
    public List<BorrowedTool> getBorrowedTools(Long id) {
        List<Naradie> pozicaneNaradie = naradieDao.getByBorrowedToId(id);
        System.out.println(pozicaneNaradie.size());
        List<BorrowedTool> result = new ArrayList<>();
        for(Naradie naradie: pozicaneNaradie){
            for (Akcia akcia: naradie.getAkcie()){
                if(akcia.getZiadatel().getId() == id){
                    result.add(new BorrowedTool(naradie, akcia));
                }
            }
        }
        return result;
    }
}
