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

	public MysqlPouzivatelDaoTest() {
		DaoFactory.INSTANCE.testing();
		pouzivatelDao = DaoFactory.INSTANCE.getPouzivatelDao();
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAll() {
		List<Pouzivatel> pouzivatelia = pouzivatelDao.getAll();
		assertTrue(pouzivatelia.size() > 0);
		assertNotNull(pouzivatelia.get(0).getMeno());
		assertNotNull(pouzivatelia.get(0).getPriezvisko());
	}

	@Test()
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

	void testSave() {
		assertThrows(EntityNotFoundException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				Pouzivatel novyPouzivatel = new Pouzivatel(null, null, null, "as", "asd", "asdad", "asd", "asd", "01",
						"vrr");
				pouzivatelDao.save(novyPouzivatel);
				pouzivatelDao.delete(novyPouzivatel.getId());

			}
		});
	}
}
