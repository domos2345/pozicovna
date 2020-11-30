package pozicovna;


import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;


public enum DaoFactory {
	INSTANCE;
	
	private boolean testing = false;
	private JdbcTemplate jdbcTemplate;
//	private StudentDao studentDao;
//	private PredmetDao predmetDao;
	
	public void testing() {
		testing = true;
	}
	
//	public StudentDao getStudentDao() {
//		if (studentDao==null) {
//			studentDao = new MysqlStudentDao(getJdbc()); 
//			
//		}
//		return studentDao;
//		
//	}
//	
//	public PredmetDao getPredmetDao() {
//		if (predmetDao==null) {
//			predmetDao= new MysqlPredmetDao(getJdbc());
//		}
//		return predmetDao;
//	}

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

