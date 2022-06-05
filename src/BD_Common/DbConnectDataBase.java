package BD_Common;

import ObservListTable.Table_BuySell_Service;

import java.sql.*;

public class DbConnectDataBase extends DbConfig {
    Connection conn;

    //Соединение с БД
    public static Connection connect()
            throws ClassNotFoundException, SQLException {
        // MySQL connection string
        String url = "jdbc:mysql://" + dbHost + ":" + dbPort +
                "/" + dbName;
        Connection conn = null;
        Class.forName("com.mysql.jdbc.Driver");
        // Class.forName(" com.mysql.cj.jdbc.Driver");
        try {
            conn = DriverManager.getConnection(url, dbUser, dbPass);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }


    //------------------------------------------------------------------------------------------------------------------

    //Вставить данные в БД в таблицу CardService
    public static void insert_CardService
    (
            String _Name_Service,
            int _UnitInitialCost_Service,
            int _UnitWithAddedCost_Service,
            String _DateRecording_Service,
            String _Additionally_Service
    ) {

        String insert =
                "INSERT " + "Directory_Service" + "(" +
                        "Name_Service" + "," + "UnitInitialCost_Service" + "," + "UnitWithAddedCost_Service" + ","
                        + "DateRecording_Service" + "," + "Additionally_Service" + ")" + "VALUES (?,?,?,?,?)";

        try (Connection conn = connect();

             PreparedStatement PrSt = conn.prepareStatement(insert)) {
            PrSt.setString(1, _Name_Service);
            PrSt.setInt(2, _UnitInitialCost_Service);
            PrSt.setInt(3, _UnitWithAddedCost_Service);
            PrSt.setString(4, _DateRecording_Service);
            PrSt.setString(5, _Additionally_Service);
            PrSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //------------------------------------------------------------------------------------------------------------------


    //Вставить данные в БД в таблицу CardProduct
    public static void insert_CardProduct
    (
            String _Name_Product,
            String _Product_Description,
            int _UnitInitialCost_Product,
            int _UnitWithAddedCost_Product,
            String _DateRecording_Product,
            String _Assembly_Product,
            String _Company_Product,
            String _Additionally_Product
    ) {
        String insert =
                "INSERT " + "directory_product" + "(" +
                        "Name_Product " + "," + "Product_Description " + "," +
                        "UnitInitialCost_Product " + "," + "UnitWithAddedCost_Product " + "," +
                        "DateRecording_Product " + "," + "Assembly_Product " + "," +
                        "Company_Product " + "," + "Additionally_Product "
                        + ")" + "VALUES (?,?,?,?,?,?,?,?)";

        try (Connection conn = connect();

             PreparedStatement PrSt = conn.prepareStatement(insert)) {
            PrSt.setString(1, _Name_Product);
            PrSt.setString(2, _Product_Description);
            PrSt.setInt(3, _UnitInitialCost_Product);
            PrSt.setInt(4, _UnitWithAddedCost_Product);
            PrSt.setString(5, _DateRecording_Product);
            PrSt.setString(6, _Assembly_Product);
            PrSt.setString(7, _Company_Product);
            PrSt.setString(8, _Additionally_Product);
            PrSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //------------------------------------------------------------------------------------------------------------------

    //Вставить данные в БД в таблицу CardOrder
    public static void insert_CardOrder
    (
            String _id_NameClient_Orders,
            String _NumberOrder_Orders,
            int _AmountPrepaymentOrder_Orders,
            int _AmountlastpaymentOrder_Orders,
            String _DateOrderAcception_Orders,
            String _DateDeliveryOrder_Orders,
            String _DateCompletionOrder_Orders,
            String _DatePrepaymentOrder_Orders,
            String _DatelastpaymentOrder_Orders,
            String _Coment_Orders,
            int _TotalPaymentCounterparty,
            int _TotalAddedCost,
            int _TotalCost


    ) {
        String insert =
                "INSERT " + "INTO " + "directory_orders" + "(" +
                        "id_NameClient_Orders" + "," +
                        "NumberOrder_Orders" + "," +
                        "AmountPrepaymentOrder_Orders" + "," +
                        "AmountlastpaymentOrder_Orders" + "," +
                        "DateOrderAcception_Orders" + "," +
                        "DateDeliveryOrder_Orders" + "," +
                        "DateCompletionOrder_Orders" + "," +
                        "DatePrepaymentOrder_Orders" + "," +
                        "DatelastpaymentOrder_Orders" + "," +
                        "Coment_Orders" + "," +
                        "TotalPaymentCounterparty_Orders" + "," +
                        "TotalAddedCost_Orders" + "," +
                        "TotalCostOrder_Orders" +

                        ")" + "VALUES" +
                        "(" + "(" + "SELECT " + "id_Client" + " FROM " + "Directory_Client " + "WHERE " + "Name_Client" + "=" + "'" + _id_NameClient_Orders + "'" + ")" + "," +
                        "'" + _NumberOrder_Orders + "'" + "," + "'" + _AmountPrepaymentOrder_Orders + "'" + "," + "'" + _AmountlastpaymentOrder_Orders + "'" + "," +
                        "'" + _DateOrderAcception_Orders + "'" + "," + "'" + _DateDeliveryOrder_Orders + "'" + "," + "'" + _DateCompletionOrder_Orders + "'" + "," +
                        "'" + _DatePrepaymentOrder_Orders + "'" + "," + "'" + _DatelastpaymentOrder_Orders + "'" + "," + "'" + _Coment_Orders + "'" + "," +
                        "'" + _TotalPaymentCounterparty + "'" + "," + "'" + _TotalAddedCost + "'" + "," + "'" + _TotalCost + "'" +
                        ")";

        try (Connection conn = connect();

             PreparedStatement PrSt = conn.prepareStatement(insert)) {
            PrSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //------------------------------------------------------------------------------------------------------------------


    //Вставить данные в БД в таблицу CardCounterparty
    public static void insert_CardCounterparty
    (
            String _Name_Counterparty,
            String _Company_Counterparty,
            String _Address_Counterparty,
            String _Phone_Counterparty,
            String _Mail_Counterparty,
            String _DateRecording_Counterparty,
            String _Specialization,
            String _Additionally_Counterparty
    ) {
        String insert =
                String.format("INSERT directory_counterparty(Name_Counterparty ,Company_Counterparty ," +
                        "Address_Counterparty ,Phone_Counterparty  ,Mail_Counterparty ,DateRecording_Counterparty ," +
                        "Specialization ,Additionally_Counterparty )VALUES (?,?,?,?,?,?,?,?)");

        try (Connection conn = connect();

             PreparedStatement PrSt = conn.prepareStatement(insert)) {
            PrSt.setString(1, _Name_Counterparty);
            PrSt.setString(2, _Company_Counterparty);
            PrSt.setString(3, _Address_Counterparty);
            PrSt.setString(4, _Phone_Counterparty);
            PrSt.setString(5, _Mail_Counterparty);
            PrSt.setString(6, _DateRecording_Counterparty);
            PrSt.setString(7, _Specialization);
            PrSt.setString(8, _Additionally_Counterparty);
            PrSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //------------------------------------------------------------------------------------------------------------------

    //Вставить данные в БД в таблицу CardProduct
    public static void insert_CardClient
    (
            String _Name_Client,
            String _Address_Client,
            String _Phone_Client,
            String _Mail_Client,
            String _DateRecording_Client,
            String _Additionally_Client
    ) {
        String insert =
                String.format("INSERT directory_client(Name_Client ,Address_Client ," +
                        "Phone_Client ,Mail_Client  ,DateRecording_Client ,Additionally_Client " +
                        " )VALUES (?,?,?,?,?,?)");

        try (Connection conn = connect();

             PreparedStatement PrSt = conn.prepareStatement(insert)) {
            PrSt.setString(1, _Name_Client);
            PrSt.setString(2, _Address_Client);
            PrSt.setString(3, _Phone_Client);
            PrSt.setString(4, _Mail_Client);
            PrSt.setString(5, _DateRecording_Client);
            PrSt.setString(6, _Additionally_Client);
            PrSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //------------------------------------------------------------------------------------------------------------------

    //Вставить данные в БД в таблицу BuySell_Service
    public static void insert_BuySell_Service
    (
            String _id_NameService_BuySell_Service,
            String _id_OrderNumber_BuySell_Service,
            String _DateRecording_BuySell_Service,
            int _Amount_BuySell_Service,
            String _id_ServiceProvider_BuySell_Service,
            String _id_ServiceRecipient_BuySell_Service,
            String _Additionally_BuySell_Service
    ) throws SQLException, ClassNotFoundException {

        String insert = "INSERT " + "INTO " + "`buy_sell_service`" + "(" +
                "`id_NameService_BuySell_Service`" + "," +
                "id_OrderNumber_BuySell_Service" + "," +
                "`DateRecording_BuySell_Service`" + "," +
                "`Amount_BuySell_Service`" + "," +
                "`id_ServiceProvider_BuySell_Service`" + "," +
                "`id_ServiceRecipient_BuySell_Service`" + "," +
                "`Additionally_BuySell_Service`" + "," +
                "`select_product_service`" +
                ")" +
                "VALUES" +
                "(" + "(" + "SELECT " + "id_Service" + " FROM " + "Directory_Service" + " WHERE " + "Name_Service" + "=" + "'" + _id_NameService_BuySell_Service + "'" + ")" + "," +
                "(" + " SELECT " + "id_Orders" + " FROM " + "Directory_Orders" + " WHERE " + "NumberOrder_Orders" + "=" + "'" + _id_OrderNumber_BuySell_Service + "'" + ")" + "," +
                "'" + _DateRecording_BuySell_Service + "'" + "," + "'" + _Amount_BuySell_Service + "'" + "," +
                "(" + "SELECT" + "`id_Counterparty`" + " FROM" + "`Directory_Counterparty`" + " WHERE" + "`Name_Counterparty`" + "=" + "'" + _id_ServiceProvider_BuySell_Service + "'" + ")" + "," +
                "(" + " SELECT " + "id_Counterparty" + " FROM " + "Directory_Counterparty" + " WHERE " + "Name_Counterparty" + "=" + "'" + _id_ServiceRecipient_BuySell_Service + "'" + ")" + "," +
                "'" + _Additionally_BuySell_Service + "'" + "," + "'" + "s" + "'" + ")";
        String select = "select " + " LAST_INSERT_ID() ";

        Connection conn = connect();
             PreparedStatement PrSt = conn.prepareStatement(insert) ;

            PrSt.executeUpdate();


            PreparedStatement PrSt2 = conn.prepareStatement(select) ;
                ResultSet ResSet = PrSt2.executeQuery();

                while (ResSet.next()) {
                    Const.id_BuySellService = ResSet.getInt(1);
                   // System.out.println(Const.id_BuySellService);
                }

    }

    //------------------------------------------------------------------------------------------------------------------

    //Вставить данные в БД в таблицу BuySell_Product
    public static void insert_BuySell_Product
    (
            String _id_NameProduct_BuySell_Products,
            String _id_OrderNumber_BuySell_Products,
            String _DateRecording_BuySell_Products,
            int _Amount_BuySell_Products,
            String _id_ProductProvider_BuySell_Products,
            String _id_ProductRecipient_BuySell_Products,
            String _Additionally_BuySell_Products
    )throws SQLException, ClassNotFoundException {

        String insert = "INSERT " + "INTO " + "`buy_sell_products`" + "(" +
                "`id_NameProduct_BuySell_Products`" + "," +
                "id_OrderNumber_BuySell_Products" + "," +
                "`DateRecording_BuySell_Products`" + "," +
                "`Amount_BuySell_Products`" + "," +
                "`id_ProductProvider_BuySell_Products`" + "," +
                "`id_ProductRecipient_BuySell_Products`" + "," +
                "`Additionally_BuySell_Products`" + "," +
                "`select_product_service`" +
                ")" +
                "VALUES" +
                "(" + "(" + "SELECT " + "id_Product" + " FROM " + "directory_product" + " WHERE " + "Name_Product" + "=" + "'" + _id_NameProduct_BuySell_Products + "'" + ")" + "," +
                "(" + " SELECT " + "id_Orders" + " FROM " + "Directory_Orders" + " WHERE " + "NumberOrder_Orders" + "=" + "'" + _id_OrderNumber_BuySell_Products + "'" + ")" + "," +
                "'" + _DateRecording_BuySell_Products + "'" + "," + "'" + _Amount_BuySell_Products + "'" + "," +
                "(" + "SELECT" + "`id_Counterparty`" + " FROM" + "`Directory_Counterparty`" + " WHERE" + "`Name_Counterparty`" + "=" + "'" + _id_ProductProvider_BuySell_Products + "'" + ")" + "," +
                "(" + " SELECT " + "id_Counterparty" + " FROM " + "Directory_Counterparty" + " WHERE " + "Name_Counterparty" + "=" + "'" + _id_ProductRecipient_BuySell_Products + "'" + ")" + "," +
                "'" + _Additionally_BuySell_Products + "'"  + "," + "'" + "p" + "'" +  ")";
        String select = "select " + " LAST_INSERT_ID() ";

        Connection conn = connect();
        PreparedStatement PrSt = conn.prepareStatement(insert) ;

        PrSt.executeUpdate();


        PreparedStatement PrSt2 = conn.prepareStatement(select) ;
        ResultSet ResSet = PrSt2.executeQuery();

        while (ResSet.next()) {
            Const.id_BuySellProducts = ResSet.getInt(1);
           // System.out.println(Const.id_BuySellProducts);
        }

    }

    //------------------------------------------------------------------------------------------------------------------

    //Вставить данные в БД в таблицу BuySell_Product
    public static void insert_AssemblyProducts
    (
            String _id_Name_AssemblyProducts,
            String _id_NameAssemblyItem_AssemblyProducts,
            int _AmountAssemblyItem_AssemblyProducts,
            String _DateRecording_AssemblyProducts,
            String _Additionally_AssemblyProducts
    ) throws SQLException, ClassNotFoundException {

        String insert = "INSERT " + "INTO " + "assemblyproducts" + "(" +
                "id_Name_AssemblyProducts" + "," +
                "id_NameAssemblyItem_AssemblyProducts" + "," +
                "AmountAssemblyItem_AssemblyProducts" + "," +
                "DateRecording_AssemblyProducts" + "," +
                "Additionally_AssemblyProducts" + ")" +
                "VALUES" +
                "(" + "(" + "SELECT " + "id_Product" + " FROM " + "Directory_Product" + " WHERE " + "Name_Product" + "=" + "'" + _id_Name_AssemblyProducts + "'" + ")" + "," +
                "(" + "SELECT " + "id_Product" + " FROM " + "Directory_Product" + " WHERE " + "Name_Product" + "=" + "'" + _id_NameAssemblyItem_AssemblyProducts + "'" + ")" + "," +
                "'" + _AmountAssemblyItem_AssemblyProducts + "'" + "," + "'" + _DateRecording_AssemblyProducts + "'" + "," + "'" + _Additionally_AssemblyProducts + "'" + ")";

        String select = "select " + " LAST_INSERT_ID() ";

        Connection conn = connect();
        PreparedStatement PrSt = conn.prepareStatement(insert) ;

        PrSt.executeUpdate();


        PreparedStatement PrSt2 = conn.prepareStatement(select) ;
        ResultSet ResSet = PrSt2.executeQuery();

        while (ResSet.next()) {
            Const.id_AssemblyProducts = ResSet.getInt(1);
            // System.out.println(Const.id_BuySellService);
        }

    }

    //------------------------------------------------------------------------------------------------------------------


}
