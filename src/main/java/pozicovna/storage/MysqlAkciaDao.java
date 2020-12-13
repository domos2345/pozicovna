package pozicovna.storage;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import pozicovna.entities.Akcia;
import pozicovna.entities.Naradie;
import pozicovna.entities.Pouzivatel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

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
            LocalDateTime zamietnutie = zamietnutieTS == null ?null :zamietnutieTS.toLocalDateTime();
            LocalDateTime pozicanie = pozicanieTS == null ?null :zamietnutieTS.toLocalDateTime();
            LocalDateTime vratenie = vratenieTS == null ?null :zamietnutieTS.toLocalDateTime();

            Long ziadatelId = rs.getLong("ziadatel");
            Pouzivatel ziadatel = pouzivatelDao.getById(ziadatelId);

            return new Akcia(id, ziadatel, ziadost, zamietnutie, pozicanie, vratenie );
        }
    }

    @Override
    public List<Akcia> getByNaradieId(Long id) {
        String sql =
                "SELECT id, ziadost, zamietnutie, pozicanie, vratenie, pouzivatel_id AS ziadatel " +
                "FROM akcia " +
                "WHERE akcia.naradie_id = ?";
        return jdbcTemplate.query(sql, new AkciaRowMapper(), id);
    }
}
