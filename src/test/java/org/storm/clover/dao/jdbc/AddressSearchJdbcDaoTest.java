package org.storm.clover.dao.jdbc;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.storm.clover.dao.AddressSearchDao;
import org.storm.clover.domain.Address;

import javax.sql.DataSource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class AddressSearchJdbcDaoTest {

    static DataSource _dataSource;
    AddressSearchDao _dao;

    @BeforeClass
    public static void init() throws Exception {
        Properties props = new Properties();

        // load properties
        Path path = Paths.get(System.getProperty("user.home"), ".gradle/gradle.properties");
        props.load(Files.newInputStream(path));

        // init datasource
        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL("jdbc:mysql://localhost:3306/periscope");
        ds.setUser(props.getProperty("flyway.user"));
        ds.setPassword(props.getProperty("flyway.password"));
        _dataSource = ds;
    }

    @Before
    public void setUp() throws Exception {
        _dao = new AddressSearchJdbcDao(_dataSource);
    }

    @Test
    public void nextAfter() throws Exception {
        Address address = null;
        List<Address> addresses;
        long size = 0;

        while (!(addresses = _dao.findAfter(address, 1000)).isEmpty()) {
            assertThat(addresses.size(), lessThanOrEqualTo(1000));

            // progress the cursor
            address = addresses.get(addresses.size() - 1);

            // capture total size for assertion
            size += addresses.size();
        }

        assertEquals(_dao.count(), size);
    }

    @Test
    public void findAll() throws Exception {
        List<Address> addresses = _dao.findAll();
        assertNotNull(addresses);
        assertThat(addresses.size(), greaterThan(10000));
    }
}