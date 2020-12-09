package pozicovna.storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import pozicovna.entities.Naradie;
import pozicovna.entities.Pouzivatel;

public class MysqlNaradieDao implements NaradieDao {

	private JdbcTemplate jdbcTemplate;

	private class NaradieRowMapper implements RowMapper<Naradie> {
		public Naradie mapRow(ResultSet rs, int rowNum) throws SQLException {
			long id = rs.getLong("id");
			String znacka = rs.getString("znacka");
			String typ = rs.getString("typ");
			long druh_naradia_id = rs.getLong("druh_naradia_id");
			long vlastnik_id = rs.getLong("vlastnik_id");

			return new Naradie(id, znacka, typ, druh_naradia_id, vlastnik_id);
		}
	}

	public List<Naradie> getAll() {
		return jdbcTemplate.query("SELECT id, znacky, typ, druh_naradia_id, vlastnik_id FROM pouzivatel",
				new NaradieRowMapper());
	}

	public Naradie getById(long id) throws EntityNotFoundException {
		try {
			return jdbcTemplate.queryForObject(
					"SELECT id, znacky, typ, druh_naradia_id, vlastnik_id FROM pouzivatel WHERE id = " + id,
					new NaradieRowMapper());
		} catch (DataAccessException e) {
			throw new EntityNotFoundException("Náradie s id " + id + " not found");
		}

	}

	public List<Naradie> getByVlastnikId(long id) throws EntityNotFoundException {
		try {
			return jdbcTemplate.query(
					"SELECT id, znacky, typ, druh_naradia_id, vlastnik_id FROM pouzivatel WHERE vlastnik_id = " + id,
					new NaradieRowMapper());
		} catch (DataAccessException e) {
			throw new EntityNotFoundException("Náradie vlastníka s id " + id + " not found");
		}

	}

	@Override
	public Naradie save(Naradie naradie) throws EntityNotFoundException {
		if (naradie.getId() == null) {// INSERT
			SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
			insert.withTableName("naradie");
			insert.usingGeneratedKeyColumns("id");
			insert.usingColumns("znacka", "typ", "druh_naradia_id", "vlastnik_id");

			Map<String, String> valuesMap = new HashMap<String, String>();
			Map<String, Long> valuesMapLong = new HashMap<String, Long>();

			valuesMap.put("znacka", naradie.getZnacka());
			valuesMap.put("typ", naradie.getTyp());
			valuesMapLong.put("druh_naradia_id", naradie.getDruh_naradia_id());
			valuesMapLong.put("vlastnik_id", naradie.getVlastnik_id());

			insert.execute(valuesMapLong);
			return new Naradie(insert.executeAndReturnKey(valuesMap).longValue(), naradie.getZnacka(), naradie.getTyp(),
					naradie.getDruh_naradia_id(), naradie.getVlastnik_id());

		}

		else {// UPDATE
			String sql = "UPDATE pouzivatel SET znacka = ?, typ = ?," + " druh_naradia_id = ?, vlastnik_id = ?"
					+ " WHERE id = ?";
			int changed = jdbcTemplate.update(sql, naradie.getZnacka(), naradie.getTyp(), naradie.getDruh_naradia_id(),
					naradie.getVlastnik_id(), naradie.getId());
			if (changed == 1) {
				return naradie;
			} else {
				throw new EntityNotFoundException("Náradie s id " + naradie.getId() + " not found");
			}
		}
	}

	@Override
	public Naradie delete(long id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
