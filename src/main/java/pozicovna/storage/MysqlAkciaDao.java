package pozicovna.storage;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import pozicovna.entities.Akcia;
import pozicovna.entities.Naradie;
import pozicovna.entities.Pouzivatel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MysqlAkciaDao implements AkciaDao {

	private JdbcTemplate jdbcTemplate;
	private PouzivatelDao pouzivatelDao;

	public MysqlAkciaDao(JdbcTemplate jdbc) {
		this.jdbcTemplate = jdbc;
		this.pouzivatelDao = DaoFactory.INSTANCE.getPouzivatelDao();
	}

	private class AkciaRowMapper implements RowMapper<Akcia> {
		public Akcia mapRow(ResultSet rs, int rowNum) throws SQLException {
			Long id = rs.getLong("id");
			LocalDateTime ziadost = rs.getTimestamp("ziadost").toLocalDateTime();
			// ak mam null
			Timestamp zamietnutieTS = rs.getTimestamp("zamietnutie");
			Timestamp pozicanieTS = rs.getTimestamp("pozicanie");
			Timestamp vratenieTS = rs.getTimestamp("vratenie");
			LocalDateTime zamietnutie = zamietnutieTS == null ? null : zamietnutieTS.toLocalDateTime();
			LocalDateTime pozicanie = pozicanieTS == null ? null : pozicanieTS.toLocalDateTime();
			LocalDateTime vratenie = vratenieTS == null ? null : vratenieTS.toLocalDateTime();

			Long ziadatelId = rs.getLong("ziadatel");
			Pouzivatel ziadatel = pouzivatelDao.getById(ziadatelId);

			return new Akcia(id, ziadatel, ziadost, zamietnutie, pozicanie, vratenie);
		}
	}

	@Override
	public List<Akcia> getByNaradieId(Long id) {
		String sql = "SELECT id, ziadost, zamietnutie, pozicanie, vratenie, ziadatel_id " + "FROM akcia "
				+ "WHERE akcia.naradie_id = ?";
		return jdbcTemplate.query(sql, new AkciaRowMapper(), id);
	}

	@Override
	public Akcia save(Akcia akcia, Long naradieId) {
		try {
			if (akcia.getId() == null) { // INSERT
				SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
				insert.withTableName("akcia");
				insert.usingGeneratedKeyColumns("id");
				insert.usingColumns("naradie_id", "pouzivatel_id", "ziadost", "pozicanie", "zamietnutie", "vratenie");

				Map<String, Object> valuesMap = new HashMap<>();

				valuesMap.put("naradie_id", naradieId);
				valuesMap.put("pouzivatel_id", akcia.getKomu().getId());
				valuesMap.put("ziadost", akcia.getZiadost());
				valuesMap.put("pozicanie", akcia.getPozicane());
				valuesMap.put("zamietnutie", akcia.getZamietnute());
				valuesMap.put("vratenie", akcia.getVratene());

				return new Akcia(insert.executeAndReturnKey(valuesMap).longValue(), akcia.getKomu(), akcia.getZiadost(),
						akcia.getZamietnute(), akcia.getPozicane(), akcia.getVratene());

			} else { // UPDATE

				String sql = "UPDATE akcia SET zamietnutie = ?, pozicanie = ?, vratenie = ?" + " WHERE id = ? ";
				int changed = jdbcTemplate.update(sql, akcia.getZamietnute(), akcia.getPozicane(), akcia.getVratene(),
						akcia.getId());
				if (changed == 1) {
					return akcia;
				} else {
					throw new EntityNotFoundException("Akcia s id " + akcia.getId() + " not found");
				}
			}
		} catch (DataIntegrityViolationException e) {
			throw new NullValueNotAllowedException("Insertion of null value into not null column of Akcia table");
		}
	}

}
