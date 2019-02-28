package com.main.Blocks;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class BlockFactory {
    private static final Logger logger = Logger.getLogger(BlockFactory.class.getName());
    private Map<String, Class<BlockInterface>> _classMap;

    public BlockFactory(String fileName) {
        try {
            _classMap = new HashMap<>();
            Properties properties = new Properties();
            InputStream fin = new FileInputStream(fileName);

            properties.load(fin);
            properties.forEach((action, className) -> {
                try {
                    Class classQual = Class.forName(className.toString());
                    _classMap.put(action.toString(), classQual);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            logger.se
        }
    }

    public BlockInterface getBlock(String blockName) throws Exception{
        return _classMap.get(blockName).getDeclaredConstructor().newInstance();
    }
}
