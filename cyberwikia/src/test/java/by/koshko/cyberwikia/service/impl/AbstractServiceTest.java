package by.koshko.cyberwikia.service.impl;

import by.koshko.cyberwikia.dao.pool.ConnectionPool;
import by.koshko.cyberwikia.service.ServiceException;
import by.koshko.cyberwikia.service.ServiceInitializer;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.SqlScriptSource;
import com.wix.mysql.config.MysqldConfig;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.ScriptResolver.classPathScript;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v8_0_11;

public abstract class AbstractServiceTest {
    protected static final String DATABASE = "cyberwikia_test";
    private static final String USER = "cyberwikia_test";
    private static final String PASSWORD = "";
    private static final int PORT = 3307;
    private static final SqlScriptSource[] SQL_SCRIPT_SOURCES
            = {classPathScript("sql/create_tables.sql"),
               classPathScript("sql/fill_countries.sql"),
               classPathScript("sql/fill_game.sql")};

    protected static EmbeddedMysql database;

    @BeforeSuite
    public void init() throws ServiceException {
        MysqldConfig config = aMysqldConfig(v8_0_11)
                .withPort(PORT)
                .withCharset(UTF8)
                .withUser(USER, PASSWORD)
                .build();

        database = anEmbeddedMysql(config)
                .addSchema(DATABASE, SQL_SCRIPT_SOURCES)
                .start();

        Properties properties = new Properties();
        properties.put("database", "database");
        properties.put("validation", "validation");
        properties.put("rootPath", "/");
        ServiceInitializer.init(properties);
    }

    @BeforeTest
    public void initMethod() {
        database.reloadSchema(DATABASE, SQL_SCRIPT_SOURCES);
    }

    @AfterSuite
    public void destroy() {
        database.stop();
        ConnectionPool.getInstance().close();
    }
}
