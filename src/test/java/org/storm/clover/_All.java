package org.storm.clover;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.storm.clover.algorithm.FactorialTest;
import org.storm.clover.dao.jdbc.AddressSearchJdbcDaoTest;
import org.storm.clover.euler._EulerSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        FactorialTest.class,
        AddressSearchJdbcDaoTest.class,
        _EulerSuite.class
})
public class _All {
}
