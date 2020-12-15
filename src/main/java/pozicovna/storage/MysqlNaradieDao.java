package pozicovna.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import pozicovna.entities.Akcia;
import pozicovna.entities.Naradie;
import pozicovna.entities.Pouzivatel;

public class MysqlNaradieDao implements NaradieDao {

	private JdbcTemplate jdbcTemplate;
	private PouzivatelDao pouzivatelDao;
	private AkciaDao akciaDao;
	private DruhNaradiaDao druhNaradiaDao;

	public MysqlNaradieDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.pouzivatelDao = DaoFactory.INSTANCE.getPouzivatelDao();
		this.akciaDao = DaoFactory.INSTANCE.getAkciaDao();
		this.druhNaradiaDao = DaoFactory.INSTANCE.getDruhNaradiaDao();
	}

	private class NaradieRowMapper implements RowMapper<Naradie> {
		public Naradie mapRow(ResultSet rs, int rowNum) throws SQLException {
			Long id = rs.getLong("id");
			String znacka = rs.getString("znacka");
			String typ = rs.getString("typ");
			Boolean jeDostupne = rs.getBoolean("je_dostupne");
			String popis = rs.getString("popis");
			String druhNaradia = rs.getString("druh_naradia");

			Long vlastnikId = rs.getLong("vlastnik_id");
			Pouzivatel vlastnik = pouzivatelDao.getById(vlastnikId);

			List<Akcia> akcie = akciaDao.getByNaradieId(id);

			return new Naradie(id, znacka, typ, jeDostupne, druhNaradia, vlastnik, popis, akcie);
		}
	}

	public List<Naradie> getAll() {
		String sql = "SELECT n.id, dn.meno AS druh_naradia, n.znacka, n.typ, n.je_dostupne, n.popis, n.vlastnik_id "
				+ "FROM naradie AS n " + "JOIN druh_naradia AS dn ON dn.id = n.druh_naradia_id ";
		return jdbcTemplate.query(sql, new NaradieRowMapper());
	}

	public Naradie getById(long id) throws EntityNotFoundException {
		String sql = "SELECT n.id, dn.meno AS druh_naradia, n.znacka, n.typ, n.je_dostupne, n.popis, n.vlastnik_id "
				+ "FROM naradie AS n " + "JOIN druh_naradia AS dn ON dn.id = n.druh_naradia_id " + "WHERE n.id = " + id;
		try {
			return jdbcTemplate.queryForObject(sql, new NaradieRowMapper());
		} catch (DataAccessException e) {
			throw new EntityNotFoundException("Náradie s id " + id + " not found");
		}
	}

	@Override
	public List<Naradie> getByVlastnikId(long id) throws EntityNotFoundException {
		String sql = "SELECT n.id, dn.meno AS druh_naradia, n.znacka, n.typ, n.je_dostupne, n.popis, n.vlastnik_id "
				+ "FROM naradie AS n " + "JOIN druh_naradia AS dn ON dn.id = n.druh_naradia_id "
				+ "WHERE n.vlastnik_id = " + id;
		try {
			return jdbcTemplate.query(sql, new NaradieRowMapper());
		} catch (DataAccessException e) {
			throw new EntityNotFoundException("Náradie vlastníka s id " + id + " not found");
		}
	}

	@Override
	public List<Naradie> getByBorrowedToId(long id) {
		String sql = "SELECT n.id, dn.meno AS druh_naradia, n.znacka, n.typ, n.je_dostupne, n.popis, n.vlastnik_id "
				+ "FROM naradie AS n " + "JOIN druh_naradia AS dn ON dn.id = n.druh_naradia_id "
				+ "JOIN akcia AS a ON a.naradie_id = n.id " + "WHERE a.pouzivatel_id = " + id;
		try {
			return jdbcTemplate.query(sql, new NaradieRowMapper());
		} catch (DataAccessException e) {
			throw new EntityNotFoundException("Náradie vlastníka s id " + id + " not found");
		}
	}

	public Naradie save(Naradie naradie) throws EntityNotFoundException {
		try {
			if (naradie.getId() == null) { // INSERT
				SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
				insert.withTableName("naradie");
				insert.usingGeneratedKeyColumns("id");
				insert.usingColumns("znacka", "typ", "je_dostupne", "druh_naradia_id", "vlastnik_id", "popis");

				Map<String, Object> valuesMap = new HashMap<String, Object>();

				valuesMap.put("znacka", naradie.getZnacka());
				valuesMap.put("typ", naradie.getTyp());
				valuesMap.put("popis", naradie.getPopis());
				valuesMap.put("je_dostupne", naradie.getJe_dostupne());
				valuesMap.put("vlastnik_id", naradie.getVlastnik().getId());

				long druhNaradiaId = druhNaradiaDao.getByMeno(naradie.getDruhNaradia()).getId();
				valuesMap.put("druh_naradia_id", druhNaradiaId);

				return new Naradie(insert.executeAndReturnKey(valuesMap).longValue(), naradie.getZnacka(),
						naradie.getTyp(), naradie.getJe_dostupne(), naradie.getDruhNaradia(), naradie.getVlastnik(),
						naradie.getPopis(), naradie.getAkcie());

			}

			else {// UPDATE

				String sql = "UPDATE naradie SET je_dostupne = ?, vlastnik_id = ?, popis = ? " + "WHERE id = ?";
				int changed = jdbcTemplate.update(sql, naradie.getJe_dostupne(), naradie.getVlastnik().getId(),
						naradie.getPopis(), naradie.getId());

				if (changed == 1) {
					return naradie;
				} else {
					throw new EntityNotFoundException("Náradie s id " + naradie.getId() + " not found");
				}
			}
		} catch (DataIntegrityViolationException e) {
			throw new NullValueNotAllowedException("Insertion of null value into not null column of Naradie table");
		}
	}

	@Override
	public Naradie delete(long id) throws EntityNotFoundException {
		try {
			// ToDo: treba mazat aj vsetky akcie
			Naradie naradie = getById(id);
			String sql = "DELETE FROM naradie WHERE id = " + id;
			jdbcTemplate.update(sql);
			return naradie;
		} catch (DataAccessException e) {
			throw new EntityNotFoundException("Naradie s id " + id + " not found");
		}
	}

}
