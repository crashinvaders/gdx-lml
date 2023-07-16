package com.github.czyzby.desktop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.github.czyzby.Root;
import com.github.czyzby.autumn.fcs.scanner.DesktopClassScanner;
import com.github.czyzby.autumn.mvc.application.AutumnApplication;
import com.github.czyzby.config.Configuration;

/** Main entry point for desktop application. */
public class DesktopLauncher {
    public static void main(final String[] arg) {
        final Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("Simple Autumn MVC app");
        configuration.setWindowedMode(Configuration.WIDTH, Configuration.HEIGHT);
        createApplication(configuration);
    }

    private static Application createApplication(final Lwjgl3ApplicationConfiguration configuration) {
        // Note that our ApplicationListener is implemented by AutumnApplication - we just say which classes should be
        // scanned (Configuration.class is the root) and with which scanner (DesktopClassScanner in this case).
        return new Lwjgl3Application(new AutumnApplication(new DesktopClassScanner(), Root.class), configuration);
    }
}
