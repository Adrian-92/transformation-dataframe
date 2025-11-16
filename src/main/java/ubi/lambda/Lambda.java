package ubi.lambda;

import java.util.Map;

@FunctionalInterface
public interface Lambda {
    Object execute(Map<String, Object> inputs);
}
