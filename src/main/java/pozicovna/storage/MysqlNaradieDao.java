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

	public MysqlNaradieDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.pouzivatelDao = DaoFactory.INSTANCE.getPouzivatelDao();
		this.akciaDao = DaoFactory.INSTANCE.getAkciaDao();
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
		String sql =
				"SELECT n.id, dn.meno AS druh_naradia, n.znacka, n.typ, n.je_dostupne, n.popis, n.vlastnik_id " +
				"FROM naradie AS n " +
				"JOIN druh_naradia AS dn ON dn.id = n.druh_naradia_id ";
		return jdbcTemplate.query(sql, new NaradieRowMapper());
	}

	public Naradie getById(long id) throws EntityNotFoundException {
		String sql =
				"SELECT n.id, dn.meno AS druh_naradia, n.znacka, n.typ, n.je_dostupne, n.popis, n.vlastnik_id " +
				"FROM naradie AS n " +
				"JOIN druh_naradia AS dn ON dn.id = n.druh_naradia_id " +
				"WHERE n.id = " + id;
		try {
			return jdbcTemplate.queryForObject(sql, new NaradieRowMapper());
		} catch (DataAccessException e) {
			throw new EntityNotFoundException("Náradie s id " + id + " not found");
		}
	}

	public List<Naradie> getByVlastnikId(long id) throws EntityNotFoundException {
		String sql =
				"SELECT n.id, dn.meno AS druh_naradia, n.znacka, n.typ, n.je_dostupne, n.popis, n.vlastnik_id " +
				"FROM naradie AS n " +
				"JOIN druh_naradia AS dn ON dn.id = n.druh_naradia_id " +
				"WHERE n.vlastnik_id = " + id;
		try {
			return jdbcTemplate.query(sql, new NaradieRowMapper());
		} catch (DataAccessException e) {
			throw new EntityNotFoundException("Náradie vlastníka s id " + id + " not found");
		}
	}

	@Override
	public Naradie save(Naradie naradie) throws EntityNotFoundException {
//		try {
//
//			if (naradie.getId() == null) { // INSERT
//				SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
//				insert.withTableName("naradie");
//				insert.usingGeneratedKeyColumns("id");
//				insert.usingColumns("znacka", "typ", "je_dostupne", "druh_naradia_id", "vlastnik_id", "popis");
//
//				Map<String, Object> valuesMap = new HashMap<String, Object>();
//				Map<String, Long> valuesMapLong = new HashMap<String, Long>();
//
//				valuesMap.put("znacka", naradie.getZnacka());
//				valuesMap.put("typ", naradie.getTyp());
//				valuesMap.put("popis", naradie.getPopis());
//				valuesMap.put("je_dostupne", naradie.getJe_dostupne());
//				valuesMapLong.put("druh_naradia_id", naradie.getDruh_naradia_id());
//				valuesMapLong.put("vlastnik_id", naradie.getVlastnik_id());
//
//				// ToDo: riesim aj zmenu vlastnika???
//
//				insert.execute(valuesMapLong);
//				return new Naradie(insert.executeAndReturnKey(valuesMap).longValue(), naradie.getZnacka(),
//						naradie.getTyp(), naradie.getJe_dostupne(), naradie.getDruh_naradia_id(),
//						naradie.getVlastnik_id(), naradie.getPopis());
//
//			}
//
//			else {// UPDATE
//				String sql = "UPDATE naradie SET znacka = ?, typ = ?, je_dostupne = ?,"
//						+ " druh_naradia_id = ?, vlastnik_id = ?, popis = ?" + " WHERE id = ?";
//				int changed = jdbcTemplate.update(sql, naradie.getZnacka(), naradie.getTyp(), naradie.getJe_dostupne(),
//						naradie.getDruh_naradia_id(), naradie.getVlastnik_id(), naradie.getTyp(), naradie.getId());
//				if (changed == 1) {
//					return naradie;
//				} else {
//					throw new EntityNotFoundException("Náradie s id " + naradie.getId() + " not found");
//				}
//			}
//		} catch (DataIntegrityViolationException e) {
//			throw new EntityNotFoundException("Naradie s hodnotou null na povinných miestach ");
//		}
		return null;
	}

	@Override
	public Naradie delete(long id) throws EntityNotFoundException {
		try {
			//ToDo: treba mazat aj vsetky akcie
			Naradie naradie = getById(id);
			String sql = "DELETE FROM naradie WHERE id = " + id;
			jdbcTemplate.update(sql);
			return naradie;
		} catch (DataAccessException e) {
			throw new EntityNotFoundException("Naradie s id " + id + " not found");
		}
	}

}
