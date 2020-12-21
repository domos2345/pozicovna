package pozicovna.storage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import pozicovna.entities.Akcia;
import pozicovna.entities.DruhNaradia;
import pozicovna.entities.Naradie;
import pozicovna.entities.Pouzivatel;

class MysqlNaradieDaoTest {

	PouzivatelDao pouzivatelDao;
	AkciaDao akciaDao;
	NaradieDao naradieDao;
	Pouzivatel newPouzivatel;
	Pouzivatel savedPouzivatel; // ziadatel
	Pouzivatel newPouzivatel2;
	Pouzivatel savedPouzivatel2; // ziadatel2
	Pouzivatel vlastnik;
	Akcia newAkcia;
	Akcia savedAkcia;
	Naradie newNaradie;
	Naradie savedNaradie;

	public MysqlNaradieDaoTest() {
		DaoFactory.INSTANCE.testing();
		akciaDao = DaoFactory.INSTANCE.getAkciaDao();
		pouzivatelDao = DaoFactory.INSTANCE.getPouzivatelDao();
		naradieDao = DaoFactory.INSTANCE.getNaradieDao();
	}

	@BeforeEach
	void setUp() throws Exception {
		newPouzivatel = new Pouzivatel("Dominik", "Džama", "mymail@gmail.com", "0918263577", "bezpecneHeslo", "Bystré",
				"Družstevna", "422", "09434", "VnT");
		savedPouzivatel = pouzivatelDao.save(newPouzivatel);
		newPouzivatel2 = new Pouzivatel("Dominik", "Džama", "mymail2@gmail.com", "0918263577", "bezpecneHeslo",
				"Bystré", "Družstevna", "422", "09434", "VnT");
		savedPouzivatel2 = pouzivatelDao.save(newPouzivatel2);

		vlastnik = pouzivatelDao.getByEmail("dd@");
		newNaradie = new Naradie("znackaa", "t4", true, "vrtacka", savedPouzivatel, "pp", new ArrayList<Akcia>(),
				akciaDao, naradieDao);
		savedNaradie = naradieDao.save(newNaradie);

		newAkcia = new Akcia(savedPouzivatel);
		savedAkcia = akciaDao.save(newAkcia, savedNaradie.getId());

	}

	@AfterEach
	void tearDown() throws Exception {
		naradieDao.delete(savedNaradie.getId());
		pouzivatelDao.delete(savedPouzivatel.getId());
		pouzivatelDao.delete(savedPouzivatel2.getId());
	}

	@Test
	void testGetAll() {
		assertTrue(naradieDao.getAll().size() > 0);
		assertNotNull(naradieDao.getAll().get(0));
	}

	@Test
	void testGetById() {
		assertThrows(EntityNotFoundException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				naradieDao.getById(-1L);

			}
		});

		Naradie byIdNaradie = naradieDao.getById(savedNaradie.getId());

		assertEquals(savedNaradie.getZnacka(), byIdNaradie.getZnacka());
		assertEquals(savedNaradie.getTyp(), byIdNaradie.getTyp());
		assertEquals(savedNaradie.getDruhNaradia(), byIdNaradie.getDruhNaradia());
		assertEquals(savedNaradie.getPopis(), byIdNaradie.getPopis());
	}

	@Test
	void testGetByVlastnikId() {
		assertTrue(naradieDao.getByVlastnikId(savedPouzivatel.getId()).size() > 0);
		assertNotNull(naradieDao.getByVlastnikId(savedPouzivatel.getId()).get(0));
	}

	@Test
	void testSave() {
		List<Naradie> originalList = naradieDao.getAll();
		Naradie newSaved = naradieDao.save(newNaradie);
		assertEquals(originalList.size() + 1, naradieDao.getAll().size());

		// CISTENIE PO SEBE
		naradieDao.delete(newSaved.getId());

	}

	@Test
	void testDelete() {

		Naradie newSaved = naradieDao.save(newNaradie);
		List<Naradie> originalList = naradieDao.getAll();
		naradieDao.delete(newSaved.getId());
		assertEquals(originalList.size() - 1, naradieDao.getAll().size());
	}

}
