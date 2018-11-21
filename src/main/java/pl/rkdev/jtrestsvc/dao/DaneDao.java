package pl.rkdev.jtrestsvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import pl.rkdev.jtrestsvc.models.Dane;

@Repository
public class DaneDao {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private static final String sqlBase = "select dane.id as dane_id, dane.date as dane_date,"
			+ " dane.value as dane_value, dane.deviceid as dane_deviceid,"
			+ " dane.eventdate as dane_eventdate"
			+ " from dane";

	public List<Dane> findAll(Date dateFrom, Date dateTo) {
		String sql = sqlBase;
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		if(dateFrom != null || dateTo != null) {
			sql += " where";
			if(dateFrom != null) {
				sql += " eventdate > :from";
				paramMap.addValue("from", dateFrom);
				if(dateTo != null) sql += " and";
			}
			if(dateTo != null) {
				sql += " eventdate < :to";
				paramMap.addValue("to", dateTo);
			}
		}
			
		return namedParameterJdbcTemplate.query(sql, paramMap, new DaneMapper());
	}
	
	public Dane findById(Long id) throws Exception {
		String sql = sqlBase;
		sql += " where id = :id";
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		
		return namedParameterJdbcTemplate.queryForObject(sql, paramMap, new DaneMapper());
	}
	
	public Long save(Dane dane) {
		String sql = "insert into dane (date, value, deviceid, eventdate)"
				+ " values (:date, :value, :deviceid, :eventdate)";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("date", dane.getDate());
		paramMap.addValue("value", dane.getValue());
		paramMap.addValue("deviceid", dane.getDeviceId());
		paramMap.addValue("eventdate", dane.getEventDate());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int rowsUpdated = namedParameterJdbcTemplate.update(sql, paramMap, keyHolder);
		if(rowsUpdated > 0)
			return keyHolder.getKey().longValue();
		return null;
	}
	
	public int update(Dane dane) {
		String sql = "update dane set value = :value"
				+ " where id = :id";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("value", dane.getValue());
		paramMap.addValue("id", dane.getId());
		return namedParameterJdbcTemplate.update(sql, paramMap);
	}
	
	public int delete(Long id) {
		String sql = "delete from dane where id = :id";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);
		return namedParameterJdbcTemplate.update(sql, paramSource);
	}
	
	private static final class DaneMapper implements RowMapper<Dane> {

		@Override
		public Dane mapRow(ResultSet rs, int row) throws SQLException {
			Dane dane = new Dane();
			dane.setId(rs.getLong("dane_id"));
			dane.setDate(rs.getTimestamp("dane_date"));
			dane.setValue(rs.getFloat("dane_value"));
			dane.setDeviceId(rs.getInt("dane_deviceid"));
			dane.setEventDate(rs.getTimestamp("dane_eventdate"));
			return dane;
		}
		
	}
}
