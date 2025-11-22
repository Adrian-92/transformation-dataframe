package ubi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

class TransformerRegister {

    private static final AtomicInteger transformerId = new AtomicInteger(0);
    private final static Map<Integer, Transformer> references = Collections.synchronizedMap(new HashMap<>());

    static Transformer get(int transId) {
        return references.get(transId);
    }

    static void add(Transformer transformer) {
        references.put(transformer.getTransId(), transformer);
    }

    static int nextTransformerId() {
        return transformerId.incrementAndGet();
    }
}
