package com.sola.testing.solatesting;

import android.app.Application;

import de.greenrobot.event.EventBus;


/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/9/16
 */
public class MainApplication extends Application {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

//    @Inject
//    LocationManager locationManager;
//
//    ApplicationComponent component;

    public static final EventBus BUS = new EventBus();

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void onCreate() {
        super.onCreate();
//        component = DaggerMainApp
//        NotificationCompat
    }


    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
//    @Singleton
//    @Component(modules = ApplicationModule.class)
//    public interface ApplicationComponent {
////        void inject(MainApplication application);
////
////        void inject(MainActivity mainActivity);
//    }
}
