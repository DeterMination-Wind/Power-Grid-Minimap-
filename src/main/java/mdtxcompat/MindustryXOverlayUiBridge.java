package mdtxcompat;

import arc.func.Prov;
import arc.scene.Element;
import arc.scene.ui.layout.Table;

import arc.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MindustryXOverlayUiBridge implements OverlayUiBridge {
    private static final OverlayWindowHandle NO_WINDOW = new OverlayWindowHandle() {
        @Override
        public void configure(boolean autoHeight, boolean resizable) {
        }

        @Override
        public void setEnabledAndPinned(boolean enabled, boolean pinned) {
        }

        @Override
        public Boolean getEnabled() {
            return null;
        }

        @Override
        public Element asElement() {
            return null;
        }
    };

    private final Object overlayUiInstance;
    private final Method registerWindowMethod;
    private final Method getOpenMethod;
    private final Method toggleMethod;
    private boolean available;

    public MindustryXOverlayUiBridge() {
        Object instance = null;
        Method register = null;
        Method getOpen = null;
        Method toggle = null;
        try {
            Class<?> overlayUiClass = Class.forName("mindustryX.features.ui.OverlayUI");
            Field instanceField = overlayUiClass.getField("INSTANCE");
            instance = instanceField.get(null);
            register = overlayUiClass.getMethod("registerWindow", String.class, Table.class);
            getOpen = overlayUiClass.getMethod("getOpen");
            toggle = overlayUiClass.getMethod("toggle");
            available = instance != null;
        } catch (Throwable ignored) {
            available = false;
        }
        overlayUiInstance = instance;
        registerWindowMethod = register;
        getOpenMethod = getOpen;
        toggleMethod = toggle;
    }

    @Override
    public boolean isSupported() {
        return available;
    }

    @Override
    public OverlayWindowHandle registerWindow(String name, Table table, Prov<Boolean> availability) {
        if (!available || overlayUiInstance == null || registerWindowMethod == null) return NO_WINDOW;
        try {
            Object window = registerWindowMethod.invoke(overlayUiInstance, name, table);
            if (window == null) return NO_WINDOW;
            if (availability != null) {
                Method setAvailability = findMethod(window.getClass(), "setAvailability", 1);
                if (setAvailability != null) {
                    setAvailability.invoke(window, availability);
                }
            }
            return new WindowHandle(window, this);
        } catch (Throwable t) {
            disable("MindustryX OverlayUI registerWindow failed; disabling integration.", t);
            return NO_WINDOW;
        }
    }

    @Override
    public void closeEditorIfOpen() {
        if (!available || overlayUiInstance == null || getOpenMethod == null || toggleMethod == null) return;
        try {
            Object open = getOpenMethod.invoke(overlayUiInstance);
            if (Boolean.TRUE.equals(open)) {
                toggleMethod.invoke(overlayUiInstance);
            }
        } catch (Throwable t) {
            disable("MindustryX OverlayUI close call failed; disabling integration.", t);
        }
    }

    private void disable(String message, Throwable t) {
        available = false;
        Log.err(message, t);
    }

    private static Method findMethod(Class<?> type, String name, int parameterCount) {
        for (Method method : type.getMethods()) {
            if (method.getName().equals(name) && method.getParameterTypes().length == parameterCount) {
                return method;
            }
        }
        return null;
    }

    private static class WindowHandle implements OverlayWindowHandle {
        private final Object window;
        private final MindustryXOverlayUiBridge owner;

        private WindowHandle(Object window, MindustryXOverlayUiBridge owner) {
            this.window = window;
            this.owner = owner;
        }

        @Override
        public void configure(boolean autoHeight, boolean resizable) {
            try {
                Method setAutoHeight = findMethod(window.getClass(), "setAutoHeight", 1);
                Method setResizable = findMethod(window.getClass(), "setResizable", 1);
                if (setAutoHeight != null) setAutoHeight.invoke(window, autoHeight);
                if (setResizable != null) setResizable.invoke(window, resizable);
            } catch (Throwable t) {
                owner.disable("MindustryX OverlayUI window configure failed; disabling integration.", t);
            }
        }

        @Override
        public void setEnabledAndPinned(boolean enabled, boolean pinned) {
            try {
                Object data = call(window, "getData");
                if (data == null) return;
                call(data, "setEnabled", enabled);
                call(data, "setPinned", pinned);
            } catch (Throwable t) {
                owner.disable("MindustryX OverlayUI window state update failed; disabling integration.", t);
            }
        }

        @Override
        public Boolean getEnabled() {
            try {
                Object data = call(window, "getData");
                if (data == null) return null;
                Object enabled = call(data, "getEnabled");
                if (enabled instanceof Boolean) {
                    return (Boolean) enabled;
                }
                return null;
            } catch (Throwable t) {
                owner.disable("MindustryX OverlayUI window read failed; disabling integration.", t);
                return null;
            }
        }

        @Override
        public Element asElement() {
            return window instanceof Element ? (Element) window : null;
        }

        private Object call(Object target, String methodName, Object... args) throws Exception {
            Method method = findMethod(target.getClass(), methodName, args.length);
            if (method == null) return null;
            return method.invoke(target, args);
        }
    }
}
