package io.github.jr1811.ashbornrp.util;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

@FunctionalInterface
public interface CommandFunction<S, R> {
    R apply(S source) throws CommandSyntaxException;
}
