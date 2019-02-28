package com.main.Blocks;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.logging.Level;

public class BlockFactory {
    private static final Logger logger = Logger.getLogger(BlockFactory.class.getName());
    private Map<String, Class<BlockInterface>> _classMap;

    private static volatile BlockFactory instance;

    public static BlockFactory getInstance() {
        BlockFactory localInstance = instance;
        if (instance == null)
            synchronized (BlockFactory.class) {
                if (instance == null) {
                    localInstance = new BlockFactory();
                    instance = localInstance;
                }
            }
        return localInstance;
    }

    private BlockFactory() {
        try {
            _classMap = new HashMap<>();
            Properties properties = new Properties();

            properties.load(BlockFactory.class.getResourceAsStream("blocks.properties"));
            properties.forEach((action, className) -> {
                try {
                    Class classQual = Class.forName(className.toString());
                    _classMap.put(action.toString(), classQual);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Error occurred: ", e.fillInStackTrace());
                }
            });
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred: ", e.fillInStackTrace());
        }
    }

    public BlockInterface getBlock(String blockName) {
        try {
            return _classMap.get(blockName).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred: ", e.fillInStackTrace());
        }
        return null;
    }
}
