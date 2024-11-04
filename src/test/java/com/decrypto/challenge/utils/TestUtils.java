package com.decrypto.challenge.utils;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class TestUtils {

    public static EasyRandom easyRandom() {
        return new EasyRandom(
                new EasyRandomParameters()
                        .collectionSizeRange(5, 10)
                        .scanClasspathForConcreteTypes(true)
        );
    }

}
