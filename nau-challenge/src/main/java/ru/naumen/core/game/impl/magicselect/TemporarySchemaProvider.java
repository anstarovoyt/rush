package ru.naumen.core.game.impl.magicselect;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.lang.math.RandomUtils;

/**
 *
 * @author achernin
 * @since 30.10.13
 */
public class TemporarySchemaProvider
{

    private static ComboPooledDataSource cpds = null;

    private static final String POSTGRES_ADMIN = "postgres";
    private static final String POSTGRES_ADMIN_PWSD = "manager";

    private static final String POSTGRES_USER = "swaldman";
    private static final String POSTGRES_PWSD = "5hjksd9bksdf803";

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/";

    private static synchronized void ensureInitConnectionPool() throws PropertyVetoException
    {
        if (cpds == null)
        {
            cpds = new ComboPooledDataSource();
            cpds.setDriverClass( "org.postgresql.Driver" );
            //loads the jdbc driver
            cpds.setJdbcUrl( DB_URL + "klingon_template" );
            cpds.setUser(POSTGRES_ADMIN);
            cpds.setPassword(POSTGRES_ADMIN_PWSD);
//            the settings below are optional -- c3p0 can work with defaults
            cpds.setMinPoolSize(5);
            cpds.setAcquireIncrement(5);
            cpds.setMaxPoolSize(20);
            cpds.setAutoCommitOnClose(true);

        }
    }

    private synchronized static String getBaseName()
    {
        return "klingon" + RandomUtils.nextLong();
    }

    public static String execute(String sqlCode) throws PropertyVetoException, SQLException, IOException
    {
        ensureInitConnectionPool();
        Connection connection = cpds.getConnection();
        String baseName = getBaseName();
        String res = "";

        try
        {
            createDB(connection, baseName);
            res = runSQL(baseName, sqlCode);
        }
        finally
        {
            dropDB(connection, baseName);
        }

        return res;
    }

    private static String runSQL(String baseName, String sqlCode) throws SQLException
    {
        String url = DB_URL + baseName;

        Integer start_year = 0;

        String default_sql = "select start_year from klingon_leader where leader = '" + sqlCode + "'";
        try (Connection conn = DriverManager.getConnection(url, POSTGRES_USER, POSTGRES_PWSD))
        {
            conn.setAutoCommit(false);
            createTable(conn);
            String[] sqls = default_sql.split(";");

            for (int i=0; i < sqls.length - 2; i++)
            {
                String sql = sqls[i];
                conn.createStatement().execute(sql);
                conn.commit();
            }

            ResultSet res = conn.createStatement().executeQuery(sqls[sqls.length - 1]);
            if (res.next())
                start_year = res.getInt(1);
            res.close();
            conn.commit();

        } catch (Exception e)
        {
            e.printStackTrace();
            return e.getMessage();
        }

        return start_year.toString();
    }

    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE klingon_leader\n" +
            "(\n" +
            "  leader character varying(100),\n" +
            "  start_year integer\n" +
            ")\n" +
            "WITH (\n" +
            "  OIDS=FALSE\n" +
            ");\n" +
            "\n" +
            "ALTER TABLE klingon_leader\n" +
            "  OWNER TO swaldman;";

    private static void createTable(Connection conn) throws SQLException, IOException
    {
        conn.createStatement().execute(CREATE_TABLE_SQL);
        conn.commit();
    }

    private static final String[] CREATE_DB_SQL = new String[] {
        "CREATE DATABASE klingon_template\n"+
        "  WITH "+
        "       ENCODING = 'UTF8'\n"+
        "       TABLESPACE = pg_default\n"+
        "       LC_COLLATE = 'Russian_Russia.1251'\n"+
        "       LC_CTYPE = 'Russian_Russia.1251'\n"+
        "       CONNECTION LIMIT = -1\n" +
        "       OWNER = swaldman\n",

        "grant CREATE ON DATABASE klingon_template to swaldman",

        "grant CONNECT ON DATABASE klingon_template to swaldman"
    };

    private static void createDB(Connection conn, String baseName) throws SQLException, IOException
    {
        for (String qry : CREATE_DB_SQL)
        {
            String sqlBatch = qry.replace("klingon_template", baseName);
            conn.createStatement().execute(sqlBatch);
        }
    }


    private static void dropDB(Connection conn, String baseName) throws SQLException
    {
        conn.createStatement().execute("DROP DATABASE " + baseName );
    }

}
