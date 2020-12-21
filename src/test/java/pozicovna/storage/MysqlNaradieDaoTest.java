package pozicovna.storage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pozicovna.entities.Akcia;
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
		newNaradie = new Naradie("znackaa", "t4", true, "vrtacka", vlastnik, "pp", new ArrayList<Akcia>(), akciaDao,
				naradieDao);
		savedNaradie = naradieDao.save(newNaradie);

		newAkcia = new Akcia(savedPouzivatel);
		savedAkcia = akciaDao.save(newAkcia, savedNaradie.getId());

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAll() {
		fail("Not yet implemented");
	}

	@Test
	void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	void testGetByVlastnikId() {
		fail("Not yet implemented");
	}

	@Test
	void testGetByBorrowedToId() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllLentByVlastnikId() {
		fail("Not yet implemented");
	}

	@Test
	void testSave() {
		assertTrue(savedNaradie.getAkcie().size() > 0);
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

}
