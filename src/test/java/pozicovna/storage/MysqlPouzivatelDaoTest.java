package pozicovna.storage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import pozicovna.entities.Pouzivatel;

class MysqlPouzivatelDaoTest {

	private PouzivatelDao pouzivatelDao;
	private Pouzivatel newPouzivatel;
	private Pouzivatel savedPouzivatel;

	public MysqlPouzivatelDaoTest() {
		DaoFactory.INSTANCE.testing();
		pouzivatelDao = DaoFactory.INSTANCE.getPouzivatelDao();

	}

	@BeforeEach
	void setUp() throws Exception {
		newPouzivatel = new Pouzivatel("Dominik", "Džama", "mymail@gmail.com", "0918263577", "bezpecneHeslo", "Bystré",
				"Družstevna", "422", "09434", "VnT");
		savedPouzivatel = pouzivatelDao.save(newPouzivatel);
	}

	@AfterEach
	void tearDown() throws Exception {

		pouzivatelDao.delete(savedPouzivatel.getId());
	}

	@Test
	void testGetAll() {
		List<Pouzivatel> pouzivatelia = pouzivatelDao.getAll();
		assertTrue(pouzivatelia.size() > 0);
		assertNotNull(pouzivatelia.get(0).getMeno());
		assertNotNull(pouzivatelia.get(0).getPriezvisko());
	}

	// id, meno, priezvisko, email, tel_cislo, sol_hash,"
	// heslo_hash, mesto, ulica, cislo_domu, psc, okres

//	pouzivatel.getMeno()
//	pouzivatel.getPriezvisko()
//	pouzivatel.getEmail()
//	pouzivatel.getTel_cislo()
//	pouzivatel.getSol_hash()
//	pouzivatel.getHeslo_hash()
//	pouzivatel.getMesto()
//	pouzivatel.getUlica()
//	pouzivatel.getPsc()
//	pouzivatel.getOkres()
//	pouzivatel.getId()

	@Test()
	void testGetById() {
		assertThrows(EntityNotFoundException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				pouzivatelDao.getById(-1L);

			}
		});

		Pouzivatel newPouzivatel = new Pouzivatel("lol", "dzam", "sfes", "546465", "fsfsfe", "bystre", "druz", "423",
				"09434", "vranrov");
		Pouzivatel savedPouzivatel = pouzivatelDao.save(newPouzivatel);
		Pouzivatel byId = pouzivatelDao.getById(savedPouzivatel.getId());
		pouzivatelDao.getById(savedPouzivatel.getId());

		assertEquals(savedPouzivatel.getMeno(), byId.getMeno());
		assertEquals(savedPouzivatel.getPriezvisko(), byId.getPriezvisko());
		assertEquals(savedPouzivatel.getEmail(), byId.getEmail());
		assertEquals(savedPouzivatel.getTel_cislo(), byId.getTel_cislo());
		assertEquals(savedPouzivatel.getSol_hash(), byId.getSol_hash());
		assertEquals(savedPouzivatel.getHeslo_hash(), byId.getHeslo_hash());
		assertEquals(savedPouzivatel.getMesto(), byId.getMesto());
		assertEquals(savedPouzivatel.getUlica(), byId.getUlica());
		assertEquals(savedPouzivatel.getPsc(), byId.getPsc());
		assertEquals(savedPouzivatel.getOkres(), byId.getOkres());
		assertEquals(savedPouzivatel.getId(), byId.getId());

		pouzivatelDao.delete(savedPouzivatel.getId());
	}

	@Test
	void testGetByEmail() {
		assertThrows(NullPointerException.class, () -> {
			pouzivatelDao.getByEmail(null);
		});
		assertThrows(EntityNotFoundException.class, () -> {
			pouzivatelDao.getByEmail("vymysleny_mail???");
		});

		Pouzivatel newPouzivatel = new Pouzivatel("x", "x", "moj_mail", "1", "bla", "M", "U", "0", "444", "aa");
		Pouzivatel savedPouzivatel = pouzivatelDao.save(newPouzivatel);
		Pouzivatel byEmail = pouzivatelDao.getByEmail(savedPouzivatel.getEmail());
		pouzivatelDao.getById(savedPouzivatel.getId());

		assertEquals(savedPouzivatel.getMeno(), byEmail.getMeno());
		assertEquals(savedPouzivatel.getPriezvisko(), byEmail.getPriezvisko());
		assertEquals(savedPouzivatel.getEmail(), byEmail.getEmail());
		assertEquals(savedPouzivatel.getTel_cislo(), byEmail.getTel_cislo());
		// assertEquals(savedPouzivatel.getSol_hash(), byEmail.getSol_hash());
		// assertEquals(savedPouzivatel.getHeslo_hash(), byEmail.getHeslo_hash());
		assertEquals(savedPouzivatel.getMesto(), byEmail.getMesto());
		assertEquals(savedPouzivatel.getUlica(), byEmail.getUlica());
		assertEquals(savedPouzivatel.getPsc(), byEmail.getPsc());
		assertEquals(savedPouzivatel.getOkres(), byEmail.getOkres());
		assertEquals(savedPouzivatel.getId(), byEmail.getId());

		pouzivatelDao.delete(savedPouzivatel.getId());
	}

	@Test()
	void testSave() { // INSERT
		assertThrows(EntityNotFoundException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				Pouzivatel novyPouzivatel = new Pouzivatel(null, null, null, "as", "asd", "asdad", "asd", "asd", "01",
						"vrr");
				pouzivatelDao.save(novyPouzivatel);
				pouzivatelDao.delete(novyPouzivatel.getId());

			}
		});

		List<Pouzivatel> originalList = pouzivatelDao.getAll();
		Pouzivatel newLocalPouzivatel = new Pouzivatel("AA", "BB", "CCC", "DD", "EE", "FF", "GG", "HH", "II", "JJ");
		Pouzivatel savedNewPouzivatel = pouzivatelDao.save(newLocalPouzivatel);
		assertEquals("AA", savedNewPouzivatel.getMeno());
		assertEquals("BB", savedNewPouzivatel.getPriezvisko());
		assertEquals("CCC", savedNewPouzivatel.getEmail());
		assertEquals("DD", savedNewPouzivatel.getTel_cislo());
		assertNotEquals("EE", savedNewPouzivatel.getHeslo_hash());
		assertEquals("FF", savedNewPouzivatel.getMesto());
		assertEquals("GG", savedNewPouzivatel.getUlica());
		assertEquals("HH", savedNewPouzivatel.getCislo_domu());
		assertEquals("II", savedNewPouzivatel.getPsc());
		assertEquals("JJ", savedNewPouzivatel.getOkres());
		assertNotNull(savedNewPouzivatel.getId());
		assertEquals(originalList.size() + 1, pouzivatelDao.getAll().size());

		Pouzivatel pouzivatelInDb = pouzivatelDao.getById(savedNewPouzivatel.getId());
		assertEquals("AA", pouzivatelInDb.getMeno());
		assertEquals("BB", pouzivatelInDb.getPriezvisko());
		assertEquals("CCC", pouzivatelInDb.getEmail());
		assertEquals("DD", pouzivatelInDb.getTel_cislo());
		assertNotEquals("EE", pouzivatelInDb.getHeslo_hash());
		assertEquals("FF", pouzivatelInDb.getMesto());
		assertEquals("GG", pouzivatelInDb.getUlica());
		assertEquals("HH", pouzivatelInDb.getCislo_domu());
		assertEquals("II", pouzivatelInDb.getPsc());
		assertEquals("JJ", pouzivatelInDb.getOkres());
		assertNotNull(pouzivatelInDb.getId());

		newLocalPouzivatel.setId(-1L);

		assertThrows(NullPointerException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				pouzivatelDao.save(null);

			}
		});

		pouzivatelDao.delete(savedNewPouzivatel.getId());
	}

	@Test()
	void testUpdate() {
		Pouzivatel changedPouzivatel = new Pouzivatel(savedPouzivatel.getId(), "AA", "BB", "CC", "DD", "EE", "FF", "GG",
				"HH", "II", "JJ", "KK");
		Pouzivatel saveChanged = pouzivatelDao.save(changedPouzivatel);
		assertEquals("AA", saveChanged.getMeno());
		assertEquals("BB", saveChanged.getPriezvisko());
		assertEquals("CC", saveChanged.getEmail());
		assertEquals("DD", saveChanged.getTel_cislo());
		assertEquals("EE", saveChanged.getSol_hash());
		assertEquals("FF", saveChanged.getHeslo_hash());
		assertEquals("GG", saveChanged.getMesto());
		assertEquals("HH", saveChanged.getUlica());
		assertEquals("II", saveChanged.getCislo_domu());
		assertEquals("JJ", saveChanged.getPsc());
		assertEquals("KK", saveChanged.getOkres());
		assertEquals(savedPouzivatel.getId(), saveChanged.getId());

		Pouzivatel pouzivatelInDb = pouzivatelDao.getById(savedPouzivatel.getId());
		assertEquals("AA", pouzivatelInDb.getMeno());
		assertEquals("BB", pouzivatelInDb.getPriezvisko());
		assertEquals("CC", pouzivatelInDb.getEmail());
		assertEquals("DD", pouzivatelInDb.getTel_cislo());
		assertEquals("EE", pouzivatelInDb.getSol_hash());
		assertEquals("FF", pouzivatelInDb.getHeslo_hash());
		assertEquals("GG", pouzivatelInDb.getMesto());
		assertEquals("HH", pouzivatelInDb.getUlica());
		assertEquals("II", pouzivatelInDb.getCislo_domu());
		assertEquals("JJ", pouzivatelInDb.getPsc());
		assertEquals("KK", pouzivatelInDb.getOkres());

		changedPouzivatel.setId(-1L);

		assertThrows(EntityNotFoundException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
//				Pouzivatel novyPouzivatel = new Pouzivatel(null, null, null, "as", "asd", "asdad", "asd", "asd", "01",
//						"vrr");
				pouzivatelDao.save(changedPouzivatel);

			}
		});

		assertThrows(NullPointerException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
//				Pouzivatel novyPouzivatel = new Pouzivatel(null, null, null, "as", "asd", "asdad", "asd", "asd", "01",
//						"vrr");
				pouzivatelDao.save(null);

			}
		});

	}

	@Test()
	void testDelete() {
		Pouzivatel newLocalPouzivatel = new Pouzivatel("AA", "BB", "delete@mail", "DD", "EE", "FF", "GG", "HH", "II",
				"JJ");
		pouzivatelDao.save(newLocalPouzivatel);
		List<Pouzivatel> originalList = pouzivatelDao.getAll();
		pouzivatelDao.delete(pouzivatelDao.getByEmail("delete@mail").getId());
		assertEquals(originalList.size() - 1, pouzivatelDao.getAll().size());

		assertThrows(EntityNotFoundException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
//				Pouzivatel novyPouzivatel = new Pouzivatel(null, null, null, "as", "asd", "asdad", "asd", "asd", "01",
//						"vrr");
				pouzivatelDao.delete(-1L);

			}
		});

	}
}
