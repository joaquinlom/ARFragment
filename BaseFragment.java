package android.spinarplus.com.spinar;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.metaio.cloud.plugin.view.ARMetaioCloudPluginManager;
import com.metaio.sdk.MetaioWorldPOIManagerCallback;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * to handle interaction events.
 *
 * create an instance of this fragment.
 */
public abstract class BaseFragment extends Fragment {

    protected ARMetaioCloudPluginManager mMetaioCloudPluginManager;
    protected MetaioWorldPOIManagerCallback mMetaioWorldPOIManagerCallback;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // get the ARMetaioCloudPluginManager instance of the children if any, if not, create a
        // default one
        mMetaioCloudPluginManager = getARMetaioCloudPluginManagerInstance();
        if (mMetaioCloudPluginManager == null)
            mMetaioCloudPluginManager = new ARMetaioCloudPluginManager(getActivity());
        mMetaioCloudPluginManager.onActivityCreated();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        // Next line registers sensor callbacks, creates the views and inits metaioSDK
        mMetaioCloudPluginManager.onActivityStarted();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        // resumes sensors, resume surfaceview if mIsInLiveMode is true
        mMetaioCloudPluginManager.onActivityResumed();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        // pauses sensors, unregisters sensors callback, cancels requests, pauses surfaceview
        mMetaioCloudPluginManager.onActivityPaused();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        // stops sensors and movie textures, removes surface view, stops camera
        mMetaioCloudPluginManager.onActivityStopped();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        // cleans up Metaio SDK
        mMetaioCloudPluginManager.onActivityDestroyed();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        mMetaioCloudPluginManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mMetaioCloudPluginManager.onActivityConfigurationChanged(newConfig);
    }

    /**
     * Override this to provide your own implementation
     *
     * @return a class that extends {@link ARMetaioCloudPluginManager} which provides custom
     *         implementation of some of the methods
     */
    public abstract ARMetaioCloudPluginManager getARMetaioCloudPluginManagerInstance();

    /**
     * Override this to provide your own implementation
     *
     * @return a class that extends {@link MetaioWorldPOIManagerCallback} which provides custom
     *         implementation of some of the methods
     */
    public abstract MetaioWorldPOIManagerCallback getMetaioWorldPOIManagerCallback();

}
