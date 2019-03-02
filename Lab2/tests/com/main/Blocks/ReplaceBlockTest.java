package com.main.Blocks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReplaceBlockTest {

    @Test
    void run() {
        BlockInterface testBlock = new ReplaceBlock();
        String result = testBlock.run(new String[]{"test", "result", "test"});
        Assertions.assertEquals(result, "result");
    }
}