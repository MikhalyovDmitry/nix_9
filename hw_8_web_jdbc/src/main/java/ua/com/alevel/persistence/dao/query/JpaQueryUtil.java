package ua.com.alevel.persistence.dao.query;

public final class JpaQueryUtil {

    private JpaQueryUtil() {
    }

    public static final String CREATE_ORDER_QUERY = "INSERT INTO orders VALUES(default,?,?,?,?)";
    public static final String UPDATE_BY_ID_QUERY = "UPDATE orders SET customer = ?, delivered = ?, updated = ? WHERE id = ";
    public static final String DELETE_ORDER_BY_ID_QUERY = "DELETE FROM orders WHERE id = ";
    public static final String EXIST_ORDER_BY_ID_QUERY = "SELECT COUNT(*) FROM orders WHERE id = ";
    public static final String FIND_ALL_ORDERS_QUERY = "SELECT * FROM orders";
    public static final String FIND_ORDER_BY_ID_QUERY = "SELECT * FROM orders WHERE id = ";
    public static final String FIND_ALL_ORDERS_BY_PRODUCT_ID_QUERY = "SELECT * FROM order_product WHERE product_id = ";

    public static final String CREATE_PRODUCT_QUERY = "INSERT INTO products VALUES(default,?,?,?)";
    public static final String UPDATE_PRODUCT_BY_ID_QUERY = "UPDATE products SET name = ?, price = ?, instock = ? WHERE id = ";
    public static final String DELETE_PRODUCT_BY_ID_QUERY = "DELETE FROM products WHERE id = ";
    public static final String EXIST_PRODUCT_BY_ID_QUERY = "SELECT COUNT(*) FROM products WHERE id = ";
    public static final String FIND_PRODUCT_BY_ID_QUERY = "SELECT * FROM products WHERE id = ";
    public static final String FIND_ALL_PRODUCTS_QUERY = "SELECT * FROM products";
    public static final String FIND_ALL_PRODUCTS_BY_ORDER_ID_QUERY = "SELECT * FROM order_product WHERE order_id = ";

    public static final String CREATE_ORDER_PRODUCT_QUERY = "INSERT INTO order_product VALUES(default,?,?)";
    public static final String UPDATE_ORDER_PRODUCT_BY_ID_QUERY = "UPDATE order_product SET order_id = ?, product_id = ? WHERE id = ";
    public static final String DELETE_ORDER_PRODUCT_BY_ID_QUERY = "DELETE FROM order_product WHERE id = ";
    public static final String EXIST_ORDER_PRODUCT_BY_ID_QUERY = "SELECT COUNT(*) FROM order_product WHERE id = ";
    public static final String FIND_ALL_ORDER_PRODUCTS_QUERY = "SELECT * FROM order_product";
    public static final String FIND_ORDER_PRODUCT_BY_ID_QUERY = "SELECT * FROM order_product WHERE id = ";
    public static final String FIND_ORDER_PRODUCT_BY_PRODUCT_ID_AND_ORDER_ID_QUERY = "SELECT * FROM order_product WHERE product_id = ? AND order_id = ?";
}
