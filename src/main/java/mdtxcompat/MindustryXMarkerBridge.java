package mdtxcompat;

import arc.math.geom.Vec2;
import arc.util.Log;

import java.lang.reflect.Method;

public class MindustryXMarkerBridge implements MarkerBridge {
    private Method markMethod;
    private boolean available;
    private boolean initialized = false;

    private void ensureInitialized() {
        if (initialized) return;
        initialized = true;
        try {
            Class<?> markerType = Class.forName("mindustryX.features.MarkerType");
            markMethod = markerType.getMethod("newMarkFromChat", String.class, Vec2.class);
            available = true;
        } catch (Throwable ignored) {
            available = false;
        }
    }

    @Override
    public boolean isSupported() {
        ensureInitialized();
        return available;
    }

    @Override
    public void mark(String text, int tileX, int tileY) {
        ensureInitialized();
        if (!available || markMethod == null) return;
        try {
            markMethod.invoke(null, text, new Vec2(tileX, tileY));
        } catch (Throwable t) {
            available = false;
            Log.err("MindustryX marker call failed; disabling integration.", t);
        }
    }
}
