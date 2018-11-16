/*
 * springboot-multimodule-kotlin skeleton.
 * Under no licences and warranty.
 */
package test.com.github.fj.lib;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Function;

/**
 * @author Francesco Jo(nimbusob@gmail.com)
 * @since 27 - Oct - 2018
 */
public final class RandomHelper {
    public static <T extends Enum<T>> T randomEnumConst(final @Nonnull Class<T> klass) {
        return randomEnumConst(klass, null);
    }

    public static <T extends Enum<T>> T randomEnumConst(final @Nonnull Class<T> klass,
                                                        final @Nullable Function<T, Boolean> acceptFunction) {
        final T[] constants = klass.getEnumConstants();

        T randomValue;
        boolean isUndefined = false;
        boolean accepted = true;
        do {
            final int randomIndex = new Random().nextInt(constants.length);
            randomValue = constants[randomIndex];

            isUndefined = "UNDEFINED".equalsIgnoreCase(randomValue.name()) ||
                    "UNKNOWN".equalsIgnoreCase(randomValue.name()) ||
                    "EMPTY".equalsIgnoreCase(randomValue.name()) ||
                    "NULL".equalsIgnoreCase(randomValue.name()) ||
                    randomValue.name().isEmpty();
            if (acceptFunction != null) {
                accepted = acceptFunction.apply(randomValue);
            }
        } while (isUndefined || !accepted);

        return randomValue;
    }
}
