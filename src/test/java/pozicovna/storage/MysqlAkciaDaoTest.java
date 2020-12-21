package pozicovna.storage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import pozicovna.entities.Akcia;
import pozicovna.entities.Naradie;
import pozicovna.entities.Pouzivatel;

class MysqlAkciaDaoTest {
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

	public MysqlAkciaDaoTest() {
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
		naradieDao.delete(savedNaradie.getId());
		pouzivatelDao.delete(savedPouzivatel.getId());
		pouzivatelDao.delete(savedPouzivatel2.getId());
	}

	@Test
	void testGetByNaradieId() {
		List<Akcia> originalList = new ArrayList<Akcia>();
		originalList.add(savedAkcia);

		List<Akcia> byIdList = akciaDao.getByNaradieId(savedNaradie.getId());

		assertEquals(originalList.size(), byIdList.size());
		assertEquals(originalList.get(0).getId(), byIdList.get(0).getId());
		assertEquals(originalList.get(0).getPozicane(), byIdList.get(0).getPozicane());
		assertEquals(originalList.get(0).getVratene(), byIdList.get(0).getVratene());
		assertEquals(originalList.get(0).getZamietnute(), byIdList.get(0).getZamietnute());
		assertEquals(originalList.get(0).getZiadatel(), byIdList.get(0).getZiadatel());
		assertEquals(originalList.get(0).getZiadost().toString().substring(0, 16),
				byIdList.get(0).getZiadost().toString().substring(0, 16));
		// sekundu trvá kým prejde žiadosť a dovtedy prejde akurát sekunda, aby sa už
		// údaje nerovnali na sekundu

		Akcia newAkcia2 = new Akcia(savedPouzivatel2);

		Akcia savedAkcia2 = akciaDao.save(newAkcia2, savedNaradie.getId());

		originalList.add(savedAkcia2);
		byIdList = akciaDao.getByNaradieId(savedNaradie.getId());
		assertEquals(byIdList.size(), originalList.size());
		for (Akcia a : byIdList)
			for (Akcia a2 : originalList)
				if (a.getId() == a2.getId()) {
					assertEquals(a.getPozicane(), a2.getPozicane());
					assertEquals(a.getVratene(), a2.getVratene());
					assertEquals(a.getZamietnute(), a2.getZamietnute());
					assertEquals(a.getZiadatel(), a2.getZiadatel());
					assertEquals(a.getZiadost().toString().substring(0, 16),
							a2.getZiadost().toString().substring(0, 16));
				}

		assertTrue(akciaDao.getByNaradieId(-1L).size() == 0);

	}

	@Test
	void testInsert() {

		Pouzivatel newPouzivatelNotSaved = new Pouzivatel("Dominik", "Džama", "mymail3@gmail.com", "0918263577",
				"bezpecneHeslo", "Bystré", "Družstevna", "422", "09434", "VnT");

		Akcia tempAkcia = new Akcia(newPouzivatelNotSaved);

		assertThrows(NullValueNotAllowedException.class, new Executable() { // ULOZIT AKCIU S NEULOZENYM POUZIVATELOM

			@Override
			public void execute() throws Throwable {

				akciaDao.save(tempAkcia, savedNaradie.getId());
			}
		});

		assertThrows(NullValueNotAllowedException.class, new Executable() {

			@Override
			public void execute() throws Throwable {

				akciaDao.save(tempAkcia, -3L);
			}
		});

	}

	@Test
	void testUpdate() {

		Pouzivatel newPouzivatelNotSaved = new Pouzivatel("Dominik", "Džama", "mymail3@gmail.com", "0918263577",
				"bezpecneHeslo", "Bystré", "Družstevna", "422", "09434", "VnT");

		// REJECT
		savedAkcia.reject(savedNaradie.getId()); // v tejto metóde je zahrnutý UPDATE

		Akcia afterChangeInDb = akciaDao.getByNaradieId(savedNaradie.getId()).get(0);
		assertNotNull(savedAkcia.getZamietnute());
		assertEquals(savedAkcia.getZamietnute().toString().substring(0, 16),
				afterChangeInDb.getZamietnute().toString().substring(0, 16));
		assertNotNull(savedAkcia.getZiadost());
		assertEquals(savedAkcia.getZiadost().toString().substring(0, 16),
				afterChangeInDb.getZiadost().toString().substring(0, 16));
		assertNull(savedAkcia.getPozicane());
		assertEquals(savedAkcia.getPozicane(), afterChangeInDb.getPozicane());
		assertNull(savedAkcia.getVratene());
		assertEquals(savedAkcia.getVratene(), afterChangeInDb.getVratene());

		assertEquals(savedAkcia.getId(), afterChangeInDb.getId());

		savedAkcia.setZamietnute(null);
		savedAkcia = akciaDao.save(savedAkcia, savedNaradie.getId());
		afterChangeInDb = akciaDao.getByNaradieId(savedNaradie.getId()).get(0);
		assertNull(savedAkcia.getZamietnute());
		assertEquals(savedAkcia.getZiadost().toString().substring(0, 16),
				afterChangeInDb.getZiadost().toString().substring(0, 16));
		assertEquals(savedAkcia.getPozicane(), afterChangeInDb.getPozicane());
		assertEquals(savedAkcia.getVratene(), afterChangeInDb.getVratene());
		assertEquals(savedAkcia.getId(), afterChangeInDb.getId());

		Akcia tempAkcia = new Akcia(newPouzivatelNotSaved);

		assertThrows(NullValueNotAllowedException.class, new Executable() { // ULOZIT AKCIU S NEULOZENYM POUZIVATELOM

			@Override
			public void execute() throws Throwable {

				akciaDao.save(tempAkcia, savedNaradie.getId());
			}
		});

		assertThrows(NullValueNotAllowedException.class, new Executable() {

			@Override
			public void execute() throws Throwable {

				akciaDao.save(tempAkcia, -3L);
			}
		});

	}

}
