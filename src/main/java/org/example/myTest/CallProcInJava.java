package org.example.myTest;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleType;
import oracle.jdbc.OracleTypes;


import java.sql.*;

public class CallProcInJava {


    public void connections() throws Exception {
        String url = "jdbc:oracle:thin:@192.168.151.26:1521:orcl";
        String username = "ZRECERP_1";
        String password = "ZRECERP_1";
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            System.out.println("______________________");
            System.out.println("Connected to database");
            System.out.println("_____________________");
            System.out.println("                     ");
            CallableStatement callableStatement = null;
            ResultSet resultSet;


            StringBuffer  Query= new StringBuffer();
            Query.append("{call ExecuteLargeQuery(?,?)}");
//            Query.append("select * from hr_cmd_command");
            callableStatement = con.prepareCall(Query.toString());
//            callableStatement.setInt(1, 104100223);
//            callableStatement.setString(2, "105005760, 105005458, 105005437, 105005365, 105005364, 105005354, 105005311, 105005272, 105005270, 105005268, 105005232, 105005205, 105005126, 105004950, 105004942, 105004827, 105004825, 105004824, 105004823, 105004820, 105004813, 105004812, 105004807, 105004806, 105004802, 105004800, 105004798, 105004797, 105004796, 105004793");
//            callableStatement.setString(1,"ALT, BIRTH_CERTIFICAT, CARD, CITY_ADDRESS, COMPDESTIN,SPFTHMS, STATUS, SURNAME, TABAGHESHOGHLI, TAFAVOTMOZD, TAFAVOTTATBIGH, TASKTYPE, TBAT12GBS97, TBYT12RBS98, TDARSADBIMEBIKARI, TFTSZSK, THMH, TSH, TTMJ1BAT12, TTMJ1BAT12GBS99, TTMJ3, VACW, VFZSK, VTH, VZSK, WORK_MOBILE, YEAR_D");
            callableStatement.setString(1,"ALT, BIRTH_CERTIFICAT, CARD, CITY_ADDRESS");
//            callableStatement.setString(1,"hr_cmd_command_id, HR_C_BPARTNERS_ID, DESCRIPTION, VALID_FROM, PERSON_ESHTEGHAL, VAZYAT_EYSARGARI ");
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);

            callableStatement.execute();

            resultSet = ((OracleCallableStatement) callableStatement).getCursor(2);
//            resultSet = (ResultSet) callableStatement.getObject(2);

//            ResultSetMetaData metaData = callableStatement.getMetaData();
            ResultSetMetaData metaData = resultSet.getMetaData();


          /*  PreparedStatement preparedStatement = con.prepareStatement(Query.toString());
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();

           */
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <columnCount ; i++) {
                    String columnName = metaData.getColumnName(i);
                    String columnValue = resultSet.getString(i);
                    System.out.println(columnName + " : " + columnValue);
                }

                System.out.println("---------------------------------");

            }
        }
        catch (Exception exception)
        {
            System.out.println(exception);
        }

        }
    }
