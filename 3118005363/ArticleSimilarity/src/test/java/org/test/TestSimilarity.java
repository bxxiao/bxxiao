package org.test;

import org.junit.Test;
import org.similarity.Main;

public class TestSimilarity {
    // FileReader reader = new FileReader(Main.class.getClassLoader().getResource("a.txt").getPath());

    @Test
    public void test(){
        Main.main(new String[]{"a", "d"});
    }
}
