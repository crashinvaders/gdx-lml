package com.github.czyzby.kiwi.util.common;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Scaling;
import com.github.czyzby.kiwi.util.collection.IgnoreCaseStringMap;

public class ScalingUtilities {

    public static final ObjectMap<String, Scaling> scalingMap = new IgnoreCaseStringMap<>();
    static {
        scalingMap.put("fit", Scaling.fit);
        scalingMap.put("fill", Scaling.fill);
        scalingMap.put("fillX", Scaling.fillX);
        scalingMap.put("fillY", Scaling.fillY);
        scalingMap.put("stretch", Scaling.stretch);
        scalingMap.put("stretchX", Scaling.stretchX);
        scalingMap.put("stretchY", Scaling.stretchY);
        scalingMap.put("none", Scaling.none);
        scalingMap.put("contain", new ContainScaling());
    }

    /**
     * Tries to match the scaling from the {@link #scalingMap} collection ignoring case.
     * @return the {@link Scaling} instance or null if none found.
     */
    public static Scaling resolveScaling(String scalingName) {
        return scalingMap.get(scalingName, null);
    }

    /**
     * Tries to match the scaling from the {@link #scalingMap} collection ignoring case.
     * @return the {@link Scaling} instance or null if none found.
     */
    public static Scaling resolveScaling(String scalingName, Scaling defaultValue) {
        return scalingMap.get(scalingName, defaultValue);
    }

    /** Scales the source to fit the target while keeping the same aspect ratio, but the source is not scaled at all if smaller in
     * both directions. This may cause the source to be smaller than the target in one or both directions. */
    public static class ContainScaling extends Scaling {
        @Override
        public Vector2 apply(float sourceWidth, float sourceHeight, float targetWidth, float targetHeight) {
            float targetRatio = targetHeight / targetWidth;
            float sourceRatio = sourceHeight / sourceWidth;
            float scale = targetRatio > sourceRatio ? targetWidth / sourceWidth : targetHeight / sourceHeight;
            if (scale > 1) scale = 1;
            temp.x = sourceWidth * scale;
            temp.y = sourceHeight * scale;
            return temp;
        }
    }
}
