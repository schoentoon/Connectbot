package com.actionbarsherlock.app;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app._ActionBarSherlockTrojanHorse;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import static com.actionbarsherlock.ActionBarSherlock.OnActionModeFinishedListener;
import static com.actionbarsherlock.ActionBarSherlock.OnActionModeStartedListener;

/** @see {@link _ActionBarSherlockTrojanHorse} */
public class SherlockFragmentActivity extends _ActionBarSherlockTrojanHorse implements OnActionModeStartedListener, OnActionModeFinishedListener {
    private static final boolean DEBUG = false;
    private static final String TAG = "SherlockFragmentActivity";

    private ActionBarSherlock mSherlock;
    private boolean mIgnoreNativeCreate = false;
    private boolean mIgnoreNativePrepare = false;
    private boolean mIgnoreNativeSelected = false;

    protected final ActionBarSherlock getSherlock() {
        if (mSherlock == null) {
            mSherlock = ActionBarSherlock.wrap(this, ActionBarSherlock.FLAG_DELEGATE);
        }
        return mSherlock;
    }


    ///////////////////////////////////////////////////////////////////////////
    // Action bar and mode
    ///////////////////////////////////////////////////////////////////////////

    public ActionBar getSupportActionBar() {
        return getSherlock().getActionBar();
    }

    public ActionMode startActionMode(ActionMode.Callback callback) {
        return getSherlock().startActionMode(callback);
    }

    public void onActionModeStarted(ActionMode mode) {}

    public void onActionModeFinished(ActionMode mode) {}


    ///////////////////////////////////////////////////////////////////////////
    // General lifecycle/callback dispatching
    ///////////////////////////////////////////////////////////////////////////

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getSherlock().dispatchConfigurationChanged(newConfig);
    }

    protected void onPostResume() {
        super.onPostResume();
        getSherlock().dispatchPostResume();
    }

    protected void onPause() {
        getSherlock().dispatchPause();
        super.onPause();
    }

    protected void onStop() {
        getSherlock().dispatchStop();
        super.onStop();
    }

    protected void onDestroy() {
        getSherlock().dispatchDestroy();
        super.onDestroy();
    }

    protected void onPostCreate(Bundle savedInstanceState) {
        getSherlock().dispatchPostCreate(savedInstanceState);
        super.onPostCreate(savedInstanceState);
    }

    protected void onTitleChanged(CharSequence title, int color) {
        getSherlock().dispatchTitleChanged(title, color);
        super.onTitleChanged(title, color);
    }

    public final boolean onMenuOpened(int featureId, android.view.Menu menu) {
        if (getSherlock().dispatchMenuOpened(featureId, menu)) {
            return true;
        }
        return super.onMenuOpened(featureId, menu);
    }

    public void onPanelClosed(int featureId, android.view.Menu menu) {
        getSherlock().dispatchPanelClosed(featureId, menu);
        super.onPanelClosed(featureId, menu);
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (getSherlock().dispatchKeyEvent(event)) {
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    ///////////////////////////////////////////////////////////////////////////
    // Native menu handling
    ///////////////////////////////////////////////////////////////////////////

    public MenuInflater getSupportMenuInflater() {
        if (DEBUG) Log.d(TAG, "[getSupportMenuInflater]");

        return getSherlock().getMenuInflater();
    }

    public void invalidateOptionsMenu() {
        if (DEBUG) Log.d(TAG, "[invalidateOptionsMenu]");

        getSherlock().dispatchInvalidateOptionsMenu();
    }

    public void supportInvalidateOptionsMenu() {
        if (DEBUG) Log.d(TAG, "[supportInvalidateOptionsMenu]");

        invalidateOptionsMenu();
    }

    public final boolean onCreatePanelMenu(int featureId, android.view.Menu menu) {
        if (DEBUG) Log.d(TAG, "[onCreatePanelMenu] featureId: " + featureId + ", menu: " + menu);

        if (featureId == Window.FEATURE_OPTIONS_PANEL && !mIgnoreNativeCreate) {
            mIgnoreNativeCreate = true;
            boolean result = getSherlock().dispatchCreateOptionsMenu(menu);
            mIgnoreNativeCreate = false;

            if (DEBUG) Log.d(TAG, "[onCreatePanelMenu] returning " + result);
            return result;
        }
        return super.onCreatePanelMenu(featureId, menu);
    }

    public final boolean onCreateOptionsMenu(android.view.Menu menu) {
        return true;
    }

    public final boolean onPreparePanel(int featureId, View view, android.view.Menu menu) {
        if (DEBUG) Log.d(TAG, "[onPreparePanel] featureId: " + featureId + ", view: " + view + ", menu: " + menu);

        if (featureId == Window.FEATURE_OPTIONS_PANEL && !mIgnoreNativePrepare) {
            mIgnoreNativePrepare = true;
            boolean result = getSherlock().dispatchPrepareOptionsMenu(menu);
            mIgnoreNativePrepare = false;

            if (DEBUG) Log.d(TAG, "[onPreparePanel] returning " + result);
            return result;
        }
        return super.onPreparePanel(featureId, view, menu);
    }

    public final boolean onPrepareOptionsMenu(android.view.Menu menu) {
        return true;
    }

    public final boolean onMenuItemSelected(int featureId, android.view.MenuItem item) {
        if (DEBUG) Log.d(TAG, "[onMenuItemSelected] featureId: " + featureId + ", item: " + item);

        if (featureId == Window.FEATURE_OPTIONS_PANEL && !mIgnoreNativeSelected) {
            mIgnoreNativeSelected = true;
            boolean result = getSherlock().dispatchOptionsItemSelected(item);
            mIgnoreNativeSelected = false;

            if (DEBUG) Log.d(TAG, "[onMenuItemSelected] returning " + result);
            return result;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    public final boolean onOptionsItemSelected(android.view.MenuItem item) {
        return false;
    }

    public void openOptionsMenu() {
        if (!getSherlock().dispatchOpenOptionsMenu()) {
            super.openOptionsMenu();
        }
    }

    public void closeOptionsMenu() {
        if (!getSherlock().dispatchCloseOptionsMenu()) {
            super.closeOptionsMenu();
        }
    }


    ///////////////////////////////////////////////////////////////////////////
    // Sherlock menu handling
    ///////////////////////////////////////////////////////////////////////////

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }


    ///////////////////////////////////////////////////////////////////////////
    // Content
    ///////////////////////////////////////////////////////////////////////////

    public void addContentView(View view, LayoutParams params) {
        getSherlock().addContentView(view, params);
    }

    public void setContentView(int layoutResId) {
        getSherlock().setContentView(layoutResId);
    }

    public void setContentView(View view, LayoutParams params) {
        getSherlock().setContentView(view, params);
    }

    public void setContentView(View view) {
        getSherlock().setContentView(view);
    }

    public void requestWindowFeature(long featureId) {
        getSherlock().requestFeature((int)featureId);
    }


    ///////////////////////////////////////////////////////////////////////////
    // Progress Indication
    ///////////////////////////////////////////////////////////////////////////

    public void setSupportProgress(int progress) {
        getSherlock().setProgress(progress);
    }

    public void setSupportProgressBarIndeterminate(boolean indeterminate) {
        getSherlock().setProgressBarIndeterminate(indeterminate);
    }

    public void setSupportProgressBarIndeterminateVisibility(boolean visible) {
        getSherlock().setProgressBarIndeterminateVisibility(visible);
    }

    public void setSupportProgressBarVisibility(boolean visible) {
        getSherlock().setProgressBarVisibility(visible);
    }

    public void setSupportSecondaryProgress(int secondaryProgress) {
        getSherlock().setSecondaryProgress(secondaryProgress);
    }
}