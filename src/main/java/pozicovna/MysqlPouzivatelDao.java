package pozicovna;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MysqlPouzivatelDao implements PouzivatelDao {

	private JdbcTemplate jdbcTemplate;

	public MysqlPouzivatelDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Pouzivatel> getAll() {
		return jdbcTemplate.query("SELECT id, meno, priezvisko, email,tel_cislo,"
				+ " heslo, mesto, ulica, cislo_domu, psc " + "FROM pouzivatel", new RowMapper<Pouzivatel>() {

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
						return new Pouzivatel(id, meno, priezvisko, email, tel_cislo, heslo, mesto, ulica, cislo_domu,
								psc);
					}

				});

	}

}
