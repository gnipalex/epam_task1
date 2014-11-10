package com.epam.hnyp.task9.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SqlStatementBuilder {
	// private int argId = 0;
	private int from = 0;
	private int to = 1000;

	private List<String> selectPart = new ArrayList<>();
	private List<String> fromPart = new ArrayList<>();
	private List<String> wherePart = new ArrayList<>();
	private List<String> orderPart = new ArrayList<>();
	private List<Object> arguments = new ArrayList<>();

	public SqlStatementBuilder setRange(int from, int to) {
		if (from < 0 || to - from < 0) {
			throw new IllegalArgumentException();
		}
		this.from = from;
		this.to = to;
		return this;
	}

	public SqlStatementBuilder addSelectField(String fieldName, String asName) {
		String fieldString = fieldName;
		if (asName != null) {
			fieldString += " as " + asName;
		}
		selectPart.add(fieldString);
		return this;
	}

	public SqlStatementBuilder addWhere(String expr, Object... args) {
		if (expr == null || expr.isEmpty()) {
			throw new IllegalArgumentException();
		}
		wherePart.add(expr);
		if (args != null && args.length > 0) {
			arguments.add(args);
		}
		return this;
	}

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

	public SqlStatementBuilder addFrom(String table) {
		if (table == null || table.isEmpty()) {
			throw new IllegalArgumentException();
		}
		fromPart.add(table);
		return this;
	}

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
		query.append(" LIMIT ").append(from).append(',').append(to);
		return query.toString();
	}

	private void printAll(StringBuilder dest, List<String> items,
			String delimiter) {
		for (int i = 0; i < items.size(); i++) {
			dest.append(items.get(i));
			if (i < items.size() - 1) {
				dest.append(delimiter);
			}
		}
	}

	public List<Object> getArgs() {
		return Collections.unmodifiableList(this.arguments);
	}

//	public static void main(String[] args) {
//		SqlStatementBuilder c = new SqlStatementBuilder();
//		c.setRange(0, 20).addSelectField("p.name", "NaMe")
//				.addFrom("products p").addFrom("categories c")
//				.addWhere("p.weight < ?", 2).addWhere("c.id = p.category_id")
//				.addOrder("p.weight", false).setRange(3, 5);
//		System.out.println(c.buildSql());
//		System.out.println(c.getArgs().size());
//	}
}
