package com.apirest.jogorpg.common;

import java.util.List;
import java.util.Random;

public enum TipoPersonagem {
    ORC, GIGANTE, LOBISOMEN;

    private static final List<TipoPersonagem> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static TipoPersonagem getRandom() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}

