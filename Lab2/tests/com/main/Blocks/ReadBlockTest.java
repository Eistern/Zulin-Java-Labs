package com.main.Blocks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReadBlockTest {

    @Test
    void run() {
        BlockInterface testBlock = new ReadBlock();
        String result = testBlock.run(new String[]{"src\\com\\main\\Blocks\\test.txt", null});
        Assertions.assertEquals(result, "testtest1");
    }
}