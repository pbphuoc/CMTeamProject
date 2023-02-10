package dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import annotation.DBField;
import annotation.Table;
import global.GlobalConstant;
import mapper.AbstractMapper;

public abstract class BaseDAO<M extends AbstractMapper<E>, E, D> {
	protected M mapper;
	private static Logger logger;

	public BaseDAO(M mapper) {
		this.mapper = mapper;
		logger = LogManager.getLogger(getDAOClass());
	}

	private Class<M> getMapperClass() {
		final ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		return (Class<M>) type.getActualTypeArguments()[0];
	}	
	
	private Class<E> getEntityClass() {
		final ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		return (Class<E>) type.getActualTypeArguments()[1];
	}
	
	private Class<D> getDAOClass() {
		final ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		return (Class<D>) type.getActualTypeArguments()[2];
	}

	private <A extends Annotation> A getAnno(Class<A> anno) {
		return getEntityClass().getAnnotation(anno);
	}

	private void loadClass() {
		try {
			ClassLoader.getSystemClassLoader().loadClass(getEntityClass().getClass().toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	protected int create(E obj, Connection conn) {
		String sql = "insert into " + getAnno(Table.class).name() + " (";
		String values = "values(";

		for (Field field : obj.getClass().getDeclaredFields()) {
			DBField dbField = field.getAnnotation(DBField.class);
			if (dbField != null) {
				sql += dbField.name() + ",";
				values += "?,";
			}
		}
		sql = sql.substring(0, sql.length() - 1) + ") " + values.substring(0, values.length() - 1) + ");";

		Connection connection = conn == null ? getConnection() : conn;
		PreparedStatement insertStm = null;
		ResultSet rs = null;

		try {
			insertStm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			insertStm = mapper.map(insertStm, obj);

			logger.debug("Insert Order query: " + insertStm.toString());
			if (insertStm.executeUpdate() != 0) {
				rs = insertStm.getGeneratedKeys();
				if (rs.next()) {
					return rs.getInt(1);
				}
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (conn != null)
				connection = null;
			close(connection, insertStm, rs);
		}

		return -1;

	}

	protected List<E> getBy(String[] columns, Object[] args) {
		if ((columns == null || args == null) && columns.length != args.length)
			return null;

		String sql = "select * from " + getAnno(Table.class).name();

		if (columns.length != 0) {
			sql += " where ";
			sql += Arrays.stream(columns).map(x -> x + "=? and ").collect(Collectors.joining());
			sql = sql.substring(0, sql.length() - 4);
		}
		Connection connection = getConnection();
		PreparedStatement selectStm = null;
		ResultSet rs = null;
		List<E> listE = new ArrayList<E>();
		boolean successful = false;

		try {
			selectStm = connection.prepareStatement(sql);

			if (columns.length != 0) {
				selectStm = mapper.mapParamValue(selectStm, args);
			}

			logger.debug(String.format("Get List<E> %s query %s",
					(columns.length != 0 ? "by " + columns.toString() : ""), selectStm.toString()));

			rs = selectStm.executeQuery();

			while (rs.next()) {
				successful = true;
				E obj = mapper.map(rs);
				if (obj != null)
					listE.add(obj);
			}

			logger.debug(String.format("Get List<E> %s %s", (columns.length != 0 ? "by " + columns.toString() : ""),
					(successful ? "sucessful" + listE.toString() : "failed")));

			return listE;

		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error(String.format("Get List<E> %s %s", (columns.length != 0 ? "by " + columns.toString() : ""),
					(successful ? "sucessful" + listE.toString() : "failed")));
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
			logger.error(String.format("Get List<E> %s %s", (columns.length != 0 ? "by " + columns.toString() : ""),
					(successful ? "sucessful" + listE.toString() : "failed")));
		} finally {
			close(connection, selectStm, rs);
		}

		return listE;
	}

	protected List<E> getAll() {
		return getBy(new String[] {}, new Object[] {});
	}

	protected static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(GlobalConstant.DO_DB_URL, GlobalConstant.DO_DB_USERNAME,
					GlobalConstant.DO_DB_PASSWORD);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		} catch (NullPointerException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	protected static void rollBack(Connection connection) {
		try {
			if (connection != null)
				connection.rollback();
			;
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	protected static void close(Statement stm) {
		try {
			if (stm != null)
				stm.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	protected static void close(Connection connection, PreparedStatement stm, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (stm != null)
				stm.close();
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}
}
