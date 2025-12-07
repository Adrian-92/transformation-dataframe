package ubi.lambda;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * extracts the dependencies from lambda function to hand them over to execution layer
 */
public class DependencyMapper extends HashMap<String, Object> {
    private final Set<String> accessedKeys = new HashSet<>();

    @Override
    public Object get(Object key) {
        if (key instanceof String) {
            accessedKeys.add((String) key);
        }
        return 0.0; // some default value, tweak this to match requirements
    }

    public Set<String> getAccessedKeys() {
        return accessedKeys;
    }
}
