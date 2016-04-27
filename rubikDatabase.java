package com.Brendon;

import java.sql.*;

public class rubikDatabase {

    private static String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "rubiks";
    private static final String USER = "brendon";
    private static final String PASS = "password";

    static Statement statement = null;
    static Connection conn = null;
    static ResultSet rs = null;

    public final static String SolverTable = "cubes";
    public final static String PK_COLUMN = "id"; // this will be the primary key for the rubik's cube solvers.

    public final static String SolverColumn = "Solver";
    public final static int TimeColumn = 0;

    private static rubik rubikData;


    public static void main(String[] args) {

        /*
        I like the idea of using boolean check and loading methods rather than haviong a ton of code here.
        Sorry for stealing your ideas.
         */
        if (!setup()) {
            System.exit(-1);
        }

        if (!loadSolvers()) {
            System.exit(-1);
        }


        rubik_GUI gui = new rubik_GUI(rubikData);


    }

    public static boolean loadSolvers(){

        try{

            if (rs!=null) {
                rs.close();
            }

            String getAllData = "SELECT * FROM " + SolverTable;
            rs = statement.executeQuery(getAllData);

            if (rubikData == null) {
                //If no current movieDataModel, then make one
                rubikData = new rubik(rs);
            } else {
                //Or, if one already exists, update its ResultSet
                rubikData.updateResultSet(rs);
            }

            return true;

        } catch (Exception e) {
            System.out.println("Error loading or reloading database");
            System.out.println(e);
            e.printStackTrace();
            return false;
        }

    }

    public static boolean setup() {

        try {


            try {
                String Driver = "com.mysql.jdbc.Driver";
                Class.forName(Driver);

            } catch (ClassNotFoundException cnfe) {
                System.out.println("No database drivers found.");
                return false;
            }

            conn = DriverManager.getConnection(DB_CONNECTION_URL + DB_NAME, USER, PASS);
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); // I'm not enitrely sure how this works, but I want to be able to update the DB on the fly, so i piut it in.

            if (!checkTables()) {

                String createTableSQL = "CREATE TABLE " + SolverTable +  (SolverColumn + " varchar(50), " + TimeColumn + " int, ");
                System.out.println(createTableSQL);
                statement.executeUpdate(createTableSQL);

                String prepStatement = "insert into SolverTable value ( ? , ? )";
                PreparedStatement prepstate = conn.prepareStatement(prepStatement);
                String updatePrep = "update SolverTable set Solver = ? where timeSolve = ?";
                PreparedStatement prepUpdate = conn.prepareStatement(updatePrep);


                prepUpdate.setString(1, SolverColumn);
                prepUpdate.setDouble(2, TimeColumn);
                prepUpdate.executeUpdate();

            }

            return true;

        } catch (SQLException SQLe) {

            SQLe.printStackTrace();
            return false;
        }
    }


    private static boolean checkTables() throws SQLException {

        String checkTable = "SHOW TABLES LIKE '" + SolverTable + "'";
        ResultSet table = statement.executeQuery(checkTable);

        if (table.next()) {
            return true;
        }
        return false;
    }


}
