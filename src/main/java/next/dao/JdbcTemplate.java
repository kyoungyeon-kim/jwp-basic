package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.exception.DataAccessException;

public class JdbcTemplate {
	public void update(String query, PreparedStatementSetter pstmtSetter) {
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {

			pstmtSetter.setValues(pstmt);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void update(String query, Object... params) {
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {

			for (int index = 0; index < params.length; index++) {
				pstmt.setObject(index + 1, params[index]);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public <T> T queryForObject(String query, PreparedStatementSetter pstmtSetter, RowMapper<T> mapper) {
		List<T> resultList = query(query, pstmtSetter, mapper);

		T result = null;

		if (!resultList.isEmpty()) {
			result = resultList.get(0);
		}

		return result;
	}

	public <T> T queryForObject(String query, RowMapper<T> mapper, Object... params) {
		List<T> resultList = query(query, mapper, params);

		T result = null;

		if (!resultList.isEmpty()) {
			result = resultList.get(0);
		}

		return result;
	}

	public <T> List<T> query(String query, PreparedStatementSetter pstmtSetter, RowMapper<T> mapper) {
		List<T> resultList = new ArrayList<T>();

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {

			pstmtSetter.setValues(pstmt);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					resultList.add(mapper.mapRow(rs));
				}
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}

		return resultList;
	}

	public <T> List<T> query(String query, RowMapper<T> mapper, Object... params) {
		List<T> resultList = new ArrayList<T>();

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query)) {
			for (int index = 0; index < params.length; index++) {
				pstmt.setObject(index + 1, params[index]);
			}

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					resultList.add(mapper.mapRow(rs));
				}
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}

		return resultList;
	}

}
