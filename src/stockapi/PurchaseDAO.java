package stockapi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.login.DataConnect;
import com.login.UserBean;
import stockapi.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;


public class PurchaseDAO {
	
	
	public static List<StockApiBean> getStocks() {
		// TODO Auto-generated method stub
		
		Connection con = null;
        PreparedStatement ps = null;
        String role = "";
        List<StockApiBean> stocks = new ArrayList<StockApiBean>();
        StockApiBean stock ;
       // PreparedStatement ps1 = null;
        System.out.println("Before Try block in getStocks");
        try {
        	int uid = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("uid").toString());
            System.out.println("The uid is : "+ uid);

            con = DataConnect.getConnection();
            ps = con.prepareStatement("select * from purchase where uid = ?"); 
            ps.setInt(1, uid);
            System.out.println(uid);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
            	stock = new StockApiBean();
            	stock.setSymbol(rs.getString("stock_symbol"));
            	stock.setQty(rs.getInt("qty"));
            	stock.setPrice(rs.getDouble("price"));
            	stock.setAmt(rs.getDouble("amt"));
            	System.out.println("The stock  symbol is : "+rs.getString("stock_symbol"));
                stocks.add(stock);
            }
            System.out.println(stocks);
           return stocks;
        
        }
        catch (SQLException e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
    return null;
}
}
