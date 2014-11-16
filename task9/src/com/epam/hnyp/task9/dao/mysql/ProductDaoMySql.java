package com.epam.hnyp.task9.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.epam.hnyp.task9.dao.ProductDao;
import com.epam.hnyp.task9.form.ProductFilterBean;
import com.epam.hnyp.task9.model.Product;


public class ProductDaoMySql implements ProductDao {

	public static final String SQL_SELECT_ALL = "SELECT * FROM products";
	public static final String SQL_SELECT_BY_ID = "SELECT * FROM products p WHERE p.id = ?";
	public static final String SQL_INSERT = "INSERT INTO products(name, manufacturer_id, price, category_id, weight, description, imgFile, available) "
			+ "VALUES (?,?,?,?,?,?,?)";
	public static final String SQL_REMOVE_BY_ID = "DELETE FROM products p WHERE p.id = ?";
	public static final String SQL_UPDATE_BY_ID = "UPDATE products SET name=?, manufacturer_id=?, price=?, category_id=?, weight=?, description=?, imgFile=?, available=? "
			+ "WHERE id = ?";

	public static final String SQL_FILTER_SELECT_PART = "p.id, p.name, p.manufacturer_id, p.price, p.category_id, p.weight, p.description, p.imgFile, p.available";
	public static final String SQL_FILTER_FROM_PRODUCTS = "products p";
	public static final String SQL_FILTER_FROM_CATEGORIES = "categories c";
	public static final String SQL_FILTER_FROM_MANUFACTURERS = "manufacturers m";
	//public static final String SQL_FILTER_FROM_PART = "products p, categories c, manufacturers m";
	

	@Override
	public int add(Product p, Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_INSERT,
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			int index = 1;
			prst.setString(index++, p.getName());
			prst.setInt(index++, p.getVendorId());
			prst.setLong(index++, p.getPrice());
			prst.setInt(index++, p.getCategoryId());
			prst.setDouble(index++, p.getWeight());
			if (p.getDescription() != null) {
				prst.setString(index++, p.getDescription());
			} else {
				prst.setNull(index++, Types.NULL);
			}
			if (p.getImgFile() != null) {
				prst.setString(index++, p.getImgFile());
			} else {
				prst.setNull(index++, Types.NULL);
			}
			prst.setBoolean(index++, p.isAvailable());
			prst.executeUpdate();
			ResultSet rs = prst.getGeneratedKeys();
			int generatedId = 0;
			if (rs.next()) {
				generatedId = rs.getInt(1);
			}
			rs.close();
			return generatedId;
		}
	}

	@Override
	public void update(Product p, Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_UPDATE_BY_ID)) {
			int index = 1;
			prst.setString(index++, p.getName());
			prst.setInt(index++, p.getVendorId());
			prst.setLong(index++, p.getPrice());
			prst.setInt(index++, p.getCategoryId());
			prst.setDouble(index++, p.getWeight());
			if (p.getDescription() != null) {
				prst.setString(index++, p.getDescription());
			} else {
				prst.setNull(index++, Types.NULL);
			}
			if (p.getImgFile() != null) {
				prst.setString(index++, p.getImgFile());
			} else {
				prst.setNull(index++, Types.NULL);
			}
			prst.setBoolean(index++, p.isAvailable());
			prst.setInt(index++, p.getId());
			prst.executeUpdate();
		}
	}

	@Override
	public void remove(int id, Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_REMOVE_BY_ID)) {
			prst.setInt(1, id);
			prst.executeUpdate();
		}

	}

	@Override
	public Product get(int id, Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_SELECT_BY_ID)) {
			prst.setInt(1, id);
			ResultSet rs = prst.executeQuery();
			Product p = null;
			if (rs.next()) {
				p = extractProduct(rs);
			}
			rs.close();
			return p;
		}
	}

	private Product extractProduct(ResultSet rs) throws SQLException {
		Product p = new Product();
		p.setAvailable(rs.getBoolean("available"));
		p.setCategoryId(rs.getInt("category_id"));
		p.setDescription(rs.getString("description"));
		p.setId(rs.getInt("id"));
		p.setImgFile(rs.getString("imgFile"));
		p.setName(rs.getString("name"));
		p.setPrice(rs.getLong("price"));
		p.setVendorId(rs.getInt("manufacturer_id"));
		p.setWeight(rs.getDouble("weight"));
		return p;
	}

	@Override
	public Collection<Product> getAll(Connection con) throws SQLException {
		// try (PreparedStatement prst = con.prepareStatement(SQL_SELECT_ALL)) {
		// ResultSet rs = prst.executeQuery();
		// Collection<Product> products = new ArrayList<>();
		// while (rs.next()) {
		// products.add(extractProduct(rs));
		// }
		// rs.close();
		// return products;
		// }
		return getAllByQuery(SQL_SELECT_ALL, null, con);
	}

	private Collection<Product> getAllByQuery(String query, List<Object> args,
			Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(query)) {
			if (args != null) {
				int index = 1;
				for (Object arg : args) {
					prst.setObject(index++, arg);
				}
			}
			ResultSet rs = prst.executeQuery();
			Collection<Product> products = new ArrayList<>();
			while (rs.next()) {
				products.add(extractProduct(rs));
			}
			rs.close();
			return products;
		}
	}

	@Override
	public Collection<Product> getByFilter(ProductFilterBean filter,
			Connection con) throws SQLException {
		SqlStatementBuilder builder = buildCompleteStatement(filter);
		return getAllByQuery(builder.buildSql(), builder.getArgs(), con);
	}

	private SqlStatementBuilder buildStatementWithoutSelectPart(
			ProductFilterBean filter) {
		SqlStatementBuilder query = new SqlStatementBuilder();
		// query.addSelectField(SQL_FILTER_SELECT_PART, null);
		query.addFrom(SQL_FILTER_FROM_PRODUCTS);
		query.addFrom(SQL_FILTER_FROM_CATEGORIES);
		query.addWhere("c.id = p.category_id");
		query.addFrom(SQL_FILTER_FROM_MANUFACTURERS);
		query.addWhere("m.id = p.manufacturer_id");
		if (filter.getCategoryIds() != null
				&& !filter.getCategoryIds().isEmpty()) {	
			query.addWhere(
					buildORExpression(filter.getCategoryIds().size(),
							"c.id = ?"), filter.getCategoryIds().toArray());
		}
		if (filter.getManufacturerIds() != null && !filter.getManufacturerIds().isEmpty()) {
			query.addWhere(
					buildORExpression(filter.getManufacturerIds().size(), "m.id = ?"),
					filter.getManufacturerIds().toArray());
		}
		if (filter.getPriceHigh() != null) {
			query.addWhere("p.price <= ?", filter.getPriceHigh());
		}
		if (filter.getPriceLow() != null) {
			query.addWhere("p.price >= ?", filter.getPriceLow());
		}
		if (filter.getSortMode() != null) {
			query.addOrder(filter.getSortMode().getSqlFieldName(),
					filter.getSortMode().isAscending());
		}
//		if (filter.getCurrentPage() != null && filter.getItemsOnPage() != null) {
//			int from = (filter.getCurrentPage() - 1) * filter.getItemsOnPage();
//			int sz = filter.getItemsOnPage();
//			query.setRange(from, sz);
//		}
		if (filter.getCurrentPage() != null && filter.getElementsOnPage() != null) {
			int from = (filter.getCurrentPage() - 1) * filter.getElementsOnPage().getCount();
			int sz = filter.getElementsOnPage().getCount();
			query.setRange(from, sz);
		}
		return query;
	}

	private SqlStatementBuilder buildCompleteStatement(ProductFilterBean filter) {
		SqlStatementBuilder query = buildStatementWithoutSelectPart(filter);
		query.addSelectField(SQL_FILTER_SELECT_PART, null);
		return query;
	}

	private String buildORExpression(int size, String orItem) {
		List<String> items = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			items.add(orItem);
		}
		StringBuilder str = SqlStatementBuilder.printAll(items, " OR ");
		str.insert(0, '(').append(')');
		return str.toString();
	}

	@Override
	public int getCountWithoutLimit(ProductFilterBean filter, Connection con)
			throws SQLException {
		SqlStatementBuilder builder = buildStatementWithoutSelectPart(filter);
		builder.addSelectField("COUNT(p.id)", "COUNT");
		builder.setRange(0, Integer.MAX_VALUE);
		try (PreparedStatement prst = con.prepareStatement(builder.buildSql())) {
			if (builder.getArgs() != null) {
				int index = 1;
				for (Object arg : builder.getArgs()) {
					prst.setObject(index++, arg);
				}
			}
			ResultSet rs = prst.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			return count;
		}
	}

//	public static void main(String[] args) {
//		ProductFilterBean bean = new ProductFilterBean();
//		//bean.setAscending(false);
//		bean.setSortMode(ProductSortMode.PRICE_ASC);
//		bean.setCategoryIds(Arrays.asList(5, 3, 8, 4));
//		bean.setPriceLow(100);
//		bean.setManufacturerIds(Arrays.asList(1, 7, 80, 11));
//		ProductDaoMySql prodDao = new ProductDaoMySql();
//		SqlStatementBuilder builder = prodDao.buildCompleteStatement(bean);
//		String query = builder.buildSql();
//		System.out.println(query);
//		for (Object o : builder.getArgs()) {
//			System.out.println(o);
//		}
//	}

}
