package com.main.Blocks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class SortBlockTest {

    @Test
    void run() {
        BlockInterface testBlock = new SortBlock();
        String result = testBlock.run(new String[]{"B\nA\nC"});
        Assertions.assertEquals(result, "A\nB\nC");
    }
}