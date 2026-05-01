package mdtxcompat;

import arc.math.geom.Vec2;
import arc.util.Log;

import java.lang.reflect.Method;

public class MindustryXMarkerBridge implements MarkerBridge {
    private final Method markMethod;
    private boolean available;

    public MindustryXMarkerBridge() {
        Method method = null;
        try {
            Class<?> markerType = Class.forName("mindustryX.features.MarkerType");
            method = markerType.getMethod("newMarkFromChat", String.class, Vec2.class);
            available = true;
        } catch (Throwable ignored) {
            available = false;
        }
        markMethod = method;
    }

    @Override
    public boolean isSupported() {
        return available;
    }

    @Override
    public void mark(String text, int tileX, int tileY) {
        if (!available || markMethod == null) return;
        try {
            markMethod.invoke(null, text, new Vec2(tileX, tileY));
        } catch (Throwable t) {
            available = false;
            Log.err("MindustryX marker call failed; disabling integration.", t);
        }
    }
}
