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

import pozicovna.entities.DruhNaradia;
import pozicovna.entities.Naradie;

public class MysqlDruhNaradiaDao implements DruhNaradiaDao {

	private JdbcTemplate jdbcTemplate;

	public MysqlDruhNaradiaDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private class DruhNaradiaRowMapper implements RowMapper<DruhNaradia> {
		public DruhNaradia mapRow(ResultSet rs, int rowNum) throws SQLException {
			Long id = rs.getLong("id");
			String meno = rs.getString("meno");

			return new DruhNaradia(id, meno);
		}
	}

	@Override
	public List<DruhNaradia> getAll() {
		return jdbcTemplate.query("SELECT id, meno FROM naradie", new DruhNaradiaRowMapper());
	}

	@Override
	public DruhNaradia getByMeno(String meno) throws EntityNotFoundException {
		try {
			return jdbcTemplate.queryForObject("SELECT id, meno WHERE meno = " + meno, new DruhNaradiaRowMapper());
		} catch (DataAccessException e) {
			throw new EntityNotFoundException("N�radie s id " + meno + " not found");
		}
	}

	@Override
	public DruhNaradia getById(long id) throws EntityNotFoundException {
		try {
			return jdbcTemplate.queryForObject("SELECT id, meno WHERE id = " + id, new DruhNaradiaRowMapper());
		} catch (DataAccessException e) {
			throw new EntityNotFoundException("DruhNaradia s id " + id + " not found");
		}
	}

	@Override
	public DruhNaradia save(DruhNaradia druhNaradia) throws EntityNotFoundException {
		try {

			if (druhNaradia.getId() == null) {// INSERT
				SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
				insert.withTableName("druh_naradia");
				insert.usingGeneratedKeyColumns("id");
				insert.usingColumns("meno");

				Map<String, Object> valuesMap = new HashMap<String, Object>();
				valuesMap.put("meno", druhNaradia.getMeno());

				return new DruhNaradia(insert.executeAndReturnKey(valuesMap).longValue(), druhNaradia.getMeno());

			}

			else {// UPDATE
				String sql = "UPDATE druh_naradia SET meno = ? WHERE id = ?";
				int changed = jdbcTemplate.update(sql, druhNaradia.getMeno(), druhNaradia.getId());
				if (changed == 1) {
					return druhNaradia;
				} else {
					throw new EntityNotFoundException("DruhNaradia s id " + druhNaradia.getId() + " not found");
				}
			}
		} catch (DataIntegrityViolationException e) {
			throw new EntityNotFoundException("DruhNaradia s hodnotou null na povinn�ch miestach (meno) ");
		}
	}

	@Override
	public DruhNaradia delete(long id) throws EntityNotFoundException {
		try {
			DruhNaradia druhNaradia = getById(id);
			String sql = "DELETE FROM druh_naradia WHERE id = " + id;
			jdbcTemplate.update(sql);
			return druhNaradia;
		} catch (DataAccessException e) {
			throw new EntityNotFoundException("Naradie s id " + id + " not found");
		}

	}

}