package com.main.Blocks;

import com.main.Readers.FileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class WriteBlockTest {

    @Test
    void runReturn() {
        BlockInterface testBlock = new WriteBlock();
        String result = testBlock.run(new String[]{"src\\com\\main\\Blocks\\out.txt"}, "test");
        Assertions.assertNull(result);
    }

    @Test
    void runCorrect() throws Exception{
        BlockInterface testBlock = new WriteBlock();
        testBlock.run(new String[]{"src\\com\\main\\Blocks\\out.txt"}, "test" );
        FileReader fileReader = new FileReader("src\\com\\main\\Blocks\\out.txt");
        String result = fileReader.nextString();
        Assertions.assertEquals(result, "test");
    }
}