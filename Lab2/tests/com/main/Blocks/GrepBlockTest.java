package com.main.Blocks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GrepBlockTest {

    @Test
    void run() {
        BlockInterface testBlock = new GrepBlock();
        String result = testBlock.run(new String[]{"A"}, "A\nB\nAbbb\nC");
        Assertions.assertEquals(result, "A\nAbbb");
    }
}