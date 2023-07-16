package com.github.czyzby.autumn.fcs.scanner;

import java.lang.annotation.Annotation;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;
import com.github.czyzby.autumn.AutumnRoot;
import com.github.czyzby.autumn.scanner.ClassScanner;
import com.github.czyzby.kiwi.util.gdx.collection.GdxArrays;
import com.github.czyzby.kiwi.util.gdx.collection.GdxSets;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;

/** Default, efficient class scanner for desktop. Does not rely on reflection and does not load scanned classes. Uses
 * {@link ClassGraph} wrapped with and adapted to {@link ClassScanner} interface to serve as default class
 * scanner for desktop LibGDX applications using Autumn. If for some reason this scanner does not work for you, try
 * {@link com.github.czyzby.autumn.nongwt.scanner.FallbackDesktopClassScanner} (which is much slower, as it depends on
 * reflection) or {@link com.github.czyzby.autumn.scanner.FixedClassScanner} (which will force you to register class
 * pool to scan, sacrificing true component scanning).
 *
 * @author MJ
 * @author metaphore
 * @see ClassGraph */
public class DesktopClassScanner implements ClassScanner {
    @Override
    public Array<Class<?>> findClassesAnnotatedWith(final Class<?> root,
            final Iterable<Class<? extends Annotation>> annotations) {

        ScanResult scanResult = new ClassGraph()
//                .verbose()
                .enableAnnotationInfo()
                .acceptPackages(
                        root.getPackage().getName(),
                        AutumnRoot.class.getPackage().getName())
                .scan();

        final ObjectSet<Class<?>> result = GdxSets.newSet(); // Using set to remove duplicates.
        for (final Class<? extends Annotation> annotation : annotations) {
            ClassInfoList matchingClasses = scanResult.getClassesWithAnnotation(annotation);
            for (Class<?> matchingClass : matchingClasses.loadClasses()) {
                result.add(matchingClass);
            }
        }
        return GdxArrays.newArray(result);
    }
}
