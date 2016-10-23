package com.feeds.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.feeds.model.FeedModel;

public class DBManager {

	private static Properties props;
	private static DBManager _INSTANCE = new DBManager();

	private DBManager() {
		props = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream("dbConnection.properties");
			props.load(inputStream);
		} catch (IOException e) {
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
			}
		}
	}

	/**
	 * @return
	 */
	public static DBManager getDBInstance() {
		return _INSTANCE;
	}

	/**
	 * @return
	 * @throws DAOException
	 */
	private Connection openDBConnection() throws DAOException {
		Connection conn = null;
		try {
			String drivers = props.getProperty("jdbc.driver");
			String connectionURL = props.getProperty("jdbc.url");
			String username = props.getProperty("jdbc.username");
			String password = props.getProperty("jdbc.password");

			Class.forName(drivers).newInstance();
			conn = DriverManager.getConnection(connectionURL, username, password);
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException ex) {
			throw new DAOException("Exception while creating DB connection", ex);
		} catch (InstantiationException ex) {
			throw new DAOException("Exception while creating DB connection", ex);
		} catch (IllegalAccessException ex) {
			throw new DAOException("Exception while creating DB connection", ex);
		} catch (SQLException ex) {
			throw new DAOException("Exception while creating DB connection", ex);
		}
		return conn;
	}

	/**
	 * @param conn
	 */
	private void closeDBConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	public List<FeedModel> select() throws DAOException {
		List<FeedModel> result = null;
		Connection conn = null;
		try {
			conn = openDBConnection();
			String sql = "SELECT CONTENT, PUBLISH_DATE FROM FEEDS ORDER BY PUBLISH_DATE DESC";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if (rs != null) {
				result = new LinkedList<FeedModel>();
				while (rs.next()) {
					FeedModel feedModel = new FeedModel();
					feedModel.setContent(rs.getString("CONTENT"));
					feedModel.setPublishDate(rs.getDate("PUBLISH_DATE").toLocalDate());
					result.add(feedModel);
				}
			}
		} catch (SQLException sqlExp) {
			throw new DAOException("SQL Exception while inserting data", sqlExp);
		} finally {
			closeDBConnection(conn);
		}
		return result;
	}

	/**
	 * @param feed
	 * @throws DAOException
	 */
	public void insert(FeedModel feed) throws DAOException {
		Connection conn = null;
		try {
			conn = openDBConnection();
			String sql = "INSERT INTO FEEDS (CONTENT, PUBLISH_DATE) VALUES (?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, feed.getContent());
			pst.setDate(2, Date.valueOf(feed.getPublishDate()));
			pst.execute();
			conn.commit();
		} catch (SQLException sqlExp) {
			throw new DAOException("SQL Exception while inserting data", sqlExp);
		} finally {
			closeDBConnection(conn);
		}
	}
}
