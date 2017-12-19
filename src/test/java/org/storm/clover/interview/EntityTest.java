package org.storm.clover.interview;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotEquals;

public class EntityTest {
    @Test
    public void whoami() throws Exception {
        Entity entity = new SubEntity();

        // explain why this is NOT equals and how to fix
        assertNotEquals("SubEntity", entity.whoami(new ArrayList()));
    }
}