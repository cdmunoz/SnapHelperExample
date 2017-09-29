package co.cdmunoz.snaphelperexample;

import android.app.Application;
import android.content.Context;
import co.cdmunoz.snaphelperexample.di.component.ApplicationComponent;
import co.cdmunoz.snaphelperexample.di.component.DaggerApplicationComponent;
import co.cdmunoz.snaphelperexample.di.module.ApplicationModule;

public class CandyApplication extends Application {

  protected ApplicationComponent applicationComponent;

  public static CandyApplication get(Context context) {
    return (CandyApplication) context.getApplicationContext();
  }

  @Override public void onCreate() {
    super.onCreate();
    applicationComponent =
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    applicationComponent.inject(this);
  }

  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }
}
