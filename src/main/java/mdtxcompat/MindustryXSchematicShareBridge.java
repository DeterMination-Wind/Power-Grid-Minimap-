package mdtxcompat;

import arc.util.Log;
import mindustry.game.Schematic;

import java.lang.reflect.Method;

public class MindustryXSchematicShareBridge implements SchematicShareBridge {
    private final Method shareMethod;
    private final Method shareClipboardMethod;
    private boolean available;

    public MindustryXSchematicShareBridge() {
        Method chatMethod = null;
        Method clipboardMethod = null;
        try {
            Class<?> shareFeature = Class.forName("mindustryX.features.ShareFeature");
            chatMethod = shareFeature.getMethod("shareSchematic", Schematic.class);
            clipboardMethod = shareFeature.getMethod("shareSchematicClipboard", Schematic.class);
            available = true;
        } catch (Throwable ignored) {
            available = false;
        }
        shareMethod = chatMethod;
        shareClipboardMethod = clipboardMethod;
    }

    @Override
    public boolean isSupported() {
        return available;
    }

    @Override
    public void shareToChat(Schematic schematic) {
        if (!available || shareMethod == null) return;
        try {
            shareMethod.invoke(null, schematic);
        } catch (Throwable t) {
            available = false;
            Log.err("MindustryX schematic share call failed; disabling integration.", t);
        }
    }

    @Override
    public void shareToClipboard(Schematic schematic) {
        if (!available || shareClipboardMethod == null) return;
        try {
            shareClipboardMethod.invoke(null, schematic);
        } catch (Throwable t) {
            available = false;
            Log.err("MindustryX clipboard share call failed; disabling integration.", t);
        }
    }
}
