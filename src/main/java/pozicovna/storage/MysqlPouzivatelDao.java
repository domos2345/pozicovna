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

import pozicovna.entities.Pouzivatel;

public class MysqlPouzivatelDao implements PouzivatelDao {

	private JdbcTemplate jdbcTemplate;

	private class PouzivatelRowMapper implements RowMapper<Pouzivatel>{
		public Pouzivatel mapRow(ResultSet rs, int rowNum) throws SQLException {
			long id = rs.getLong("id");
			String meno = rs.getString("meno");
			String priezvisko = rs.getString("priezvisko");
			String email = rs.getString("email");
			String tel_cislo = rs.getString("tel_cislo");
			String heslo = rs.getString("heslo");
			String mesto = rs.getString("mesto");
			String ulica = rs.getString("ulica");
			String cislo_domu = rs.getString("cislo_domu");
			String psc = rs.getString("psc");
			String okres = rs.getString("okres");
			return new Pouzivatel(id, meno, priezvisko, email, tel_cislo, heslo, mesto, ulica, cislo_domu,
					psc, okres);
		}
	}

	public MysqlPouzivatelDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Pouzivatel> getAll() {
		return jdbcTemplate.query("SELECT id, meno, priezvisko, email, tel_cislo,"
				+ " heslo, mesto, ulica, cislo_domu, psc, okres " + "FROM pouzivatel", new PouzivatelRowMapper() );

	}

	public Pouzivatel save(Pouzivatel pouzivatel) throws EntityNotFoundException {
		if (pouzivatel.getId() == null) {// INSERT
			SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);		
			insert.withTableName("pouzivatel");
			insert.usingGeneratedKeyColumns("id");
			insert.usingColumns("meno", "priezvisko", "email", "tel_cislo", "heslo", "mesto", "ulica", "cislo_domu",
					"psc", "okres");
			Map<String, String> valuesMap = new HashMap<String, String>();

			valuesMap.put("meno", pouzivatel.getMeno());
			valuesMap.put("priezvisko", pouzivatel.getPriezvisko());
			valuesMap.put("email", pouzivatel.getEmail());
			valuesMap.put("tel_cislo", pouzivatel.getTel_cislo());
			valuesMap.put("heslo", pouzivatel.getHeslo());
			valuesMap.put("mesto", pouzivatel.getMesto());
			valuesMap.put("ulica", pouzivatel.getUlica());
			valuesMap.put("cislo_domu", pouzivatel.getCislo_domu());
			valuesMap.put("psc", pouzivatel.getPsc());
			valuesMap.put("okres", pouzivatel.getOkres());

			return new Pouzivatel(insert.executeAndReturnKey(valuesMap).longValue(), pouzivatel.getMeno(),
					pouzivatel.getPriezvisko(), pouzivatel.getEmail(), pouzivatel.getTel_cislo(), pouzivatel.getHeslo(),
					pouzivatel.getMesto(), pouzivatel.getUlica(), pouzivatel.getCislo_domu(), pouzivatel.getPsc(),
					pouzivatel.getOkres());

		}

		else {// UPDATE
			String sql = "UPDATE pouzivatel SET meno = ?, priezvisko = ?,"
					+ " email = ?, tel_cislo = ?, heslo = ?, mesto = ?, ulica = ?,"
					+ " cislo_domu = ?, psc = ?, okres = ?" + " WHERE id = ?";
			int changed = jdbcTemplate.update(sql, pouzivatel.getMeno(), pouzivatel.getPriezvisko(),
					pouzivatel.getEmail(), pouzivatel.getTel_cislo(), pouzivatel.getHeslo(), pouzivatel.getMesto(),
					pouzivatel.getUlica(), pouzivatel.getPsc(), pouzivatel.getOkres(), pouzivatel.getId());
			if (changed == 1) {
				return pouzivatel;
			} else {
				throw new EntityNotFoundException("Pouzivatel s id " + pouzivatel.getId() + " not found");
			}
		}
	}

	public Pouzivatel getById(long id) throws EntityNotFoundException {
		try {
			return jdbcTemplate.queryForObject("SELECT id, meno, priezvisko, email, tel_cislo,"
					+ " heslo, mesto, ulica, cislo_domu, psc, okres " + "FROM pouzivatel WHERE id = " + id,
					new PouzivatelRowMapper());
		} catch (DataAccessException e) {
			throw new EntityNotFoundException("Pouzivatel s id " + id + " not found");
		}

	}

	public Pouzivatel delete(long id) throws EntityNotFoundException {
		Pouzivatel pouzivatel = getById(id);
		String sql = "DELETE FROM pouzivatel WHERE id = " + id;
		jdbcTemplate.update(sql);
		return pouzivatel;

	}

	public Pouzivatel getByEmail(String email) throws NullPointerException, EntityNotFoundException {
		if (email == null)
			throw new NullPointerException("Email cannot be null");
		try {
			String sql = "SELECT id, meno, priezvisko, email, tel_cislo, heslo, mesto, ulica, cislo_domu, psc, okres "
					+ "FROM pouzivatel "
					+ "WHERE email = ? ";
			return jdbcTemplate.queryForObject(sql, new PouzivatelRowMapper(), email);
		} catch (DataAccessException e) {
			throw new EntityNotFoundException("Pouzivatel s emailom " + email + " neexistuje");
		}
	}

}
