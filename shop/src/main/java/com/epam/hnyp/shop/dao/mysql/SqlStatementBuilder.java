package com.epam.hnyp.shop.dao.mysql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SqlStatementBuilder {
	public static int DEFAULT_LIMIT_FROM = 0;
	public static int DEFAULT_LIMIT_SZ = 1000;
	
	private int from = DEFAULT_LIMIT_FROM;
	private int size = DEFAULT_LIMIT_SZ;

	private List<String> selectPart = new ArrayList<>();
	private List<String> fromPart = new ArrayList<>();
	private List<String> wherePart = new ArrayList<>();
	private List<String> orderPart = new ArrayList<>();
	private List<Object> arguments = new ArrayList<>();

	/**
	 * Sets LIMIT part of SQL query
	 * @param from
	 * @param sz
	 * @return
	 */
	public SqlStatementBuilder setRange(int from, int sz) {
		if (from < 0 || sz < 0) {
			throw new IllegalArgumentException();
		}
		this.from = from;
		this.size = sz;
		return this;
	}

	/**
	 * Adds to query SELECT field. Specify asName if you want column in result to have other name
	 * @param fieldName
	 * @param asName
	 * @return
	 */
	public SqlStatementBuilder addSelectField(String fieldName, String asName) {
		String fieldString = fieldName;
		if (asName != null) {
			fieldString += " as " + asName;
		}
		selectPart.add(fieldString);
		return this;
	}

	/**
	 * Adds one WHERE item to query. All WHERE items are combined through AND
	 * @param expr expression with ? argument 
	 * @param args arguments in order of appearance ? in expression
	 * @return
	 */
	public SqlStatementBuilder addWhere(String expr, Object... args) {
		if (expr == null || expr.isEmpty()) {
			throw new IllegalArgumentException();
		}
		wherePart.add(expr);
		if (args != null && args.length > 0) {
			arguments.addAll(Arrays.asList(args));
		}
		return this;
	}

	/**
	 * Adds one ORDER item to query
	 * @param field ordering field
	 * @param asc true if ascending order, false - descending
	 * @return
	 */
	public SqlStatementBuilder addOrder(String field, boolean asc) {
		if (field == null || field.isEmpty()) {
			throw new IllegalArgumentException();
		}
		String orderString = field;
		if (asc) {
			orderString += " ASC";
		} else {
			orderString += " DESC";
		}
		orderPart.add(orderString);
		return this;
	}

	/**
	 * Adds one FROM item to query
	 * @param table name of the table
	 * @return
	 */
	public SqlStatementBuilder addFrom(String table) {
		if (table == null || table.isEmpty()) {
			throw new IllegalArgumentException();
		}
		fromPart.add(table);
		return this;
	}

	/**
	 * Builds final SQL query
	 * @return
	 */
	public String buildSql() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT ");
		printAll(query, selectPart, ",");
		query.append(" FROM ");
		printAll(query, fromPart, ",");
		if (!wherePart.isEmpty()) {
			query.append(" WHERE ");
			printAll(query, wherePart, " AND ");
		}
		if (!orderPart.isEmpty()) {
			query.append(" ORDER BY ");
			printAll(query, orderPart, ",");
		}
		query.append(" LIMIT ").append(from).append(',').append(size);
		return query.toString();
	}

	private static void printAll(StringBuilder dest, List<String> items,
			String delimiter) {
		for (int i = 0; i < items.size(); i++) {
			dest.append(items.get(i));
			if (i < items.size() - 1) {
				dest.append(delimiter);
			}
		}
	}
	
	/**
	 * Prints all items to StringBuilder separated by delimiter string
	 * @param items
	 * @param delimiter
	 * @return
	 */
	public static StringBuilder printAll(List<String> items, String delimiter) {
		StringBuilder str = new StringBuilder();
		printAll(str, items, delimiter);
		return str;
	}

	/**
	 * Gets arguments list in order of appearing in SQL query
	 * @return
	 */
	public List<Object> getArgs() {
		return Collections.unmodifiableList(this.arguments);
	}
}
