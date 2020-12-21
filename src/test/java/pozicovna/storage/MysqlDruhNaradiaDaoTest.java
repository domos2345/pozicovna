package pozicovna.storage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import pozicovna.entities.DruhNaradia;

class MysqlDruhNaradiaDaoTest {

	private DruhNaradiaDao druhNaradiaDao;
	private DruhNaradia newDruhNaradia;
	private DruhNaradia savedDruhNaradia;

	public MysqlDruhNaradiaDaoTest() {
		DaoFactory.INSTANCE.testing();
		druhNaradiaDao = DaoFactory.INSTANCE.getDruhNaradiaDao();

	}

	@BeforeEach
	void setUp() throws Exception {
		newDruhNaradia = new DruhNaradia("speciDruh");
		savedDruhNaradia = druhNaradiaDao.save(newDruhNaradia);
	}

	@AfterEach
	void tearDown() throws Exception {
		druhNaradiaDao.delete(savedDruhNaradia.getId());
	}

	@Test

	void testGetAll() {
		List<DruhNaradia> druhyNaradia = druhNaradiaDao.getAll();
		assertTrue(druhyNaradia.size() > 0);
		assertNotNull(druhyNaradia.get(0).getMeno());

	}

	@Test()
	void testGetById() {
		assertThrows(EntityNotFoundException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				druhNaradiaDao.getById(-1L);

			}
		});

		DruhNaradia byIdDruhNaradia = druhNaradiaDao.getById(savedDruhNaradia.getId());

		assertEquals(savedDruhNaradia.getMeno(), byIdDruhNaradia.getMeno());
	}

	@Test
	void testGetByMeno() {
		assertThrows(NullPointerException.class, () -> {
			druhNaradiaDao.save(null);
		});
		assertThrows(EntityNotFoundException.class, () -> {
			druhNaradiaDao.getByMeno("vymyslene meno");
		});

		DruhNaradia byMeno = druhNaradiaDao.getByMeno(savedDruhNaradia.getMeno());

		assertEquals(savedDruhNaradia.getMeno(), byMeno.getMeno());

	}

	@Test()
	void testSave() { // INSERT
		assertThrows(EntityNotFoundException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				DruhNaradia dn = new DruhNaradia(null);
				druhNaradiaDao.save(dn);
				druhNaradiaDao.delete(dn.getId());

			}
		});

		List<DruhNaradia> originalList = druhNaradiaDao.getAll();
		DruhNaradia dn2 = new DruhNaradia("hey Yo");
		DruhNaradia dn2Saved = druhNaradiaDao.save(dn2);
		assertEquals("hey Yo", dn2Saved.getMeno());

		assertNotNull(dn2Saved.getId());
		assertEquals(originalList.size() + 1, druhNaradiaDao.getAll().size());

		DruhNaradia dnInDb = druhNaradiaDao.getById(dn2Saved.getId());
		assertEquals("hey Yo", dnInDb.getMeno());

		assertNotNull(dnInDb.getId());

		assertThrows(NullPointerException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				druhNaradiaDao.save(null);

			}
		});

		druhNaradiaDao.delete(dn2Saved.getId());
	}

	@Test()
	void testUpdate() {
		DruhNaradia changedDn = new DruhNaradia(savedDruhNaradia.getId(), "lolDn");
		DruhNaradia saveDn = druhNaradiaDao.save(changedDn);
		assertEquals("lolDn", saveDn.getMeno());
		assertEquals(saveDn.getId(), changedDn.getId());

		DruhNaradia dnInDb = druhNaradiaDao.getById(saveDn.getId());
		assertEquals("lolDn", dnInDb.getMeno());

		changedDn.setId(-1L);

		assertThrows(EntityNotFoundException.class, new Executable() {

			@Override
			public void execute() throws Throwable {

				druhNaradiaDao.save(changedDn);

			}
		});

		assertThrows(NullPointerException.class, new Executable() {

			@Override
			public void execute() throws Throwable {

				druhNaradiaDao.save(null);

			}
		});

	}

}
