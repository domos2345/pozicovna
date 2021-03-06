package pozicovna.storage;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;


public enum DaoFactory {
	INSTANCE;

	private boolean testing = false;
	private JdbcTemplate jdbcTemplate;
	private PouzivatelDao PouzivatelDao;
	private NaradieDao NaradieDao;
	private AkciaDao akciaDao;
	private DruhNaradiaDao druhNaradiaDao;

	public void testing() {
		testing = true;
	}

	public PouzivatelDao getPouzivatelDao() {
		if (PouzivatelDao == null) {
			PouzivatelDao = new MysqlPouzivatelDao(getJdbc());

		}
		return PouzivatelDao;

	}

	public NaradieDao getNaradieDao() {
		if (NaradieDao == null) {
			NaradieDao = new MysqlNaradieDao(getJdbc());

		}
		return NaradieDao;

	}

	public AkciaDao getAkciaDao() {
		if (akciaDao == null) {
			akciaDao = new MysqlAkciaDao(getJdbc());
		}
		return akciaDao;
	}

	public DruhNaradiaDao getDruhNaradiaDao() {
		if (druhNaradiaDao == null) {
			druhNaradiaDao = new MysqlDruhNaradiaDao(getJdbc());
		}
		return druhNaradiaDao;
	}

	private JdbcTemplate getJdbc() {
		if (jdbcTemplate == null) {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser("paz1c");
			dataSource.setPassword("paz1cHeslo");
			if (testing) {
				dataSource.setUrl("jdbc:mysql://localhost/pozicovna_test?serverTimezone=Europe/Bratislava");
			} else {
				dataSource.setUrl("jdbc:mysql://localhost/pozicovna?serverTimezone=Europe/Bratislava");
			}

			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		return jdbcTemplate;

	}

}
