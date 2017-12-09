package org.storm.clover.dao.jdbc;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.storm.clover.dao.AddressSearchDao;
import org.storm.clover.domain.Address;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

public class AddressSearchJdbcDaoTest {

    static DataSource _dataSource;
    AddressSearchDao _dao;

    @BeforeClass
    public static void init() throws Exception {
        Properties props = new Properties();
        props.load(new FileInputStream(new File(System.getProperty("user.home"), ".gradle/gradle.properties")));

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
        while (!(addresses = _dao.nextAfter(address, 1000)).isEmpty()) {
            assertThat(addresses.size(), lessThanOrEqualTo(1000));
            size += addresses.size();
            address = addresses.get(addresses.size() - 1);

            addresses.forEach(System.out::println);
        }

        assertEquals(_dao.count(), size);
    }

    @Test
    public void count() throws Exception {
        assertThat(_dao.count(), greaterThan(10000L));
    }
}