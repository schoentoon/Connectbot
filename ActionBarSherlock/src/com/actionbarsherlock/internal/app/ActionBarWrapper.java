package com.actionbarsherlock.internal.app;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.SpinnerAdapter;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class ActionBarWrapper extends ActionBar implements android.app.ActionBar.OnNavigationListener, android.app.ActionBar.OnMenuVisibilityListener {
    private final Activity mActivity;
    private final android.app.ActionBar mActionBar;
    private ActionBar.OnNavigationListener mNavigationListener;
    private Set<OnMenuVisibilityListener> mMenuVisibilityListeners = new HashSet<OnMenuVisibilityListener>(1);
    private FragmentTransaction mFragmentTransaction;


    public ActionBarWrapper(Activity activity) {
        mActivity = activity;
        mActionBar = activity.getActionBar();
        if (mActionBar != null) {
            mActionBar.addOnMenuVisibilityListener(this);
        }
    }


    public void setHomeButtonEnabled(boolean enabled) {
        mActionBar.setHomeButtonEnabled(enabled);
    }

    public Context getThemedContext() {
        return mActionBar.getThemedContext();
    }

    public void setCustomView(View view) {
        mActionBar.setCustomView(view);
    }

    public void setCustomView(View view, LayoutParams layoutParams) {
        android.app.ActionBar.LayoutParams lp = new android.app.ActionBar.LayoutParams(layoutParams);
        lp.gravity = layoutParams.gravity;
        lp.bottomMargin = layoutParams.bottomMargin;
        lp.topMargin = layoutParams.topMargin;
        lp.leftMargin = layoutParams.leftMargin;
        lp.rightMargin = layoutParams.rightMargin;
        mActionBar.setCustomView(view, lp);
    }

    public void setCustomView(int resId) {
        mActionBar.setCustomView(resId);
    }

    public void setIcon(int resId) {
        mActionBar.setIcon(resId);
    }

    public void setIcon(Drawable icon) {
        mActionBar.setIcon(icon);
    }

    public void setLogo(int resId) {
        mActionBar.setLogo(resId);
    }

    public void setLogo(Drawable logo) {
        mActionBar.setLogo(logo);
    }

    public void setListNavigationCallbacks(SpinnerAdapter adapter, OnNavigationListener callback) {
        mNavigationListener = callback;
        mActionBar.setListNavigationCallbacks(adapter, (callback != null) ? this : null);
    }

    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        //This should never be a NullPointerException since we only set
        //ourselves as the listener when the callback is not null.
        return mNavigationListener.onNavigationItemSelected(itemPosition, itemId);
    }

    public void setSelectedNavigationItem(int position) {
        mActionBar.setSelectedNavigationItem(position);
    }

    public int getSelectedNavigationIndex() {
        return mActionBar.getSelectedNavigationIndex();
    }

    public int getNavigationItemCount() {
        return mActionBar.getNavigationItemCount();
    }

    public void setTitle(CharSequence title) {
        mActionBar.setTitle(title);
    }

    public void setTitle(int resId) {
        mActionBar.setTitle(resId);
    }

    public void setSubtitle(CharSequence subtitle) {
        mActionBar.setSubtitle(subtitle);
    }

    public void setSubtitle(int resId) {
        mActionBar.setSubtitle(resId);
    }

    public void setDisplayOptions(int options) {
        mActionBar.setDisplayOptions(options);
    }

    public void setDisplayOptions(int options, int mask) {
        mActionBar.setDisplayOptions(options, mask);
    }

    public void setDisplayUseLogoEnabled(boolean useLogo) {
        mActionBar.setDisplayUseLogoEnabled(useLogo);
    }

    public void setDisplayShowHomeEnabled(boolean showHome) {
        mActionBar.setDisplayShowHomeEnabled(showHome);
    }

    public void setDisplayHomeAsUpEnabled(boolean showHomeAsUp) {
        mActionBar.setDisplayHomeAsUpEnabled(showHomeAsUp);
    }

    public void setDisplayShowTitleEnabled(boolean showTitle) {
        mActionBar.setDisplayShowTitleEnabled(showTitle);
    }

    public void setDisplayShowCustomEnabled(boolean showCustom) {
        mActionBar.setDisplayShowCustomEnabled(showCustom);
    }

    public void setBackgroundDrawable(Drawable d) {
        mActionBar.setBackgroundDrawable(d);
    }

    public void setStackedBackgroundDrawable(Drawable d) {
        mActionBar.setStackedBackgroundDrawable(d);
    }

    public void setSplitBackgroundDrawable(Drawable d) {
        mActionBar.setSplitBackgroundDrawable(d);
    }

    public View getCustomView() {
        return mActionBar.getCustomView();
    }

    public CharSequence getTitle() {
        return mActionBar.getTitle();
    }

    public CharSequence getSubtitle() {
        return mActionBar.getSubtitle();
    }

    public int getNavigationMode() {
        return mActionBar.getNavigationMode();
    }

    public void setNavigationMode(int mode) {
        mActionBar.setNavigationMode(mode);
    }

    public int getDisplayOptions() {
        return mActionBar.getDisplayOptions();
    }

    public class TabWrapper extends ActionBar.Tab implements android.app.ActionBar.TabListener {
        final android.app.ActionBar.Tab mNativeTab;
        private Object mTag;
        private TabListener mListener;

        public TabWrapper(android.app.ActionBar.Tab nativeTab) {
            mNativeTab = nativeTab;
            mNativeTab.setTag(this);
        }

        public int getPosition() {
            return mNativeTab.getPosition();
        }

        public Drawable getIcon() {
            return mNativeTab.getIcon();
        }

        public CharSequence getText() {
            return mNativeTab.getText();
        }

        public Tab setIcon(Drawable icon) {
            mNativeTab.setIcon(icon);
            return this;
        }

        public Tab setIcon(int resId) {
            mNativeTab.setIcon(resId);
            return this;
        }

        public Tab setText(CharSequence text) {
            mNativeTab.setText(text);
            return this;
        }

        public Tab setText(int resId) {
            mNativeTab.setText(resId);
            return this;
        }

        public Tab setCustomView(View view) {
            mNativeTab.setCustomView(view);
            return this;
        }

        public Tab setCustomView(int layoutResId) {
            mNativeTab.setCustomView(layoutResId);
            return this;
        }

        public View getCustomView() {
            return mNativeTab.getCustomView();
        }

        public Tab setTag(Object obj) {
            mTag = obj;
            return this;
        }

        public Object getTag() {
            return mTag;
        }

        public Tab setTabListener(TabListener listener) {
            mNativeTab.setTabListener(listener != null ? this : null);
            mListener = listener;
            return this;
        }

        public void select() {
            mNativeTab.select();
        }

        public Tab setContentDescription(int resId) {
            mNativeTab.setContentDescription(resId);
            return this;
        }

        public Tab setContentDescription(CharSequence contentDesc) {
            mNativeTab.setContentDescription(contentDesc);
            return this;
        }

        public CharSequence getContentDescription() {
            return mNativeTab.getContentDescription();
        }

        public void onTabReselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {
            if (mListener != null) {
                FragmentTransaction trans = null;
                if (mActivity instanceof SherlockFragmentActivity) {
                    trans = ((SherlockFragmentActivity)mActivity).getSupportFragmentManager().beginTransaction()
                            .disallowAddToBackStack();
                }

                mListener.onTabReselected(this, trans);

                if (trans != null && !trans.isEmpty()) {
                    trans.commit();
                }
            }
        }

        public void onTabSelected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {
            if (mListener != null) {

                if (mFragmentTransaction == null && mActivity instanceof SherlockFragmentActivity) {
                    mFragmentTransaction = ((SherlockFragmentActivity)mActivity).getSupportFragmentManager().beginTransaction()
                            .disallowAddToBackStack();
                }

                mListener.onTabSelected(this, mFragmentTransaction);

                if (mFragmentTransaction != null) {
                    if (!mFragmentTransaction.isEmpty()) {
                        mFragmentTransaction.commit();
                    }
                    mFragmentTransaction = null;
                }
            }
        }

        public void onTabUnselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {
            if (mListener != null) {
                FragmentTransaction trans = null;
                if (mActivity instanceof SherlockFragmentActivity) {
                    trans = ((SherlockFragmentActivity)mActivity).getSupportFragmentManager().beginTransaction()
                            .disallowAddToBackStack();
                    mFragmentTransaction = trans;
                }

                mListener.onTabUnselected(this, trans);
            }
        }
    }

    public Tab newTab() {
        return new TabWrapper(mActionBar.newTab());
    }

    public void addTab(Tab tab) {
        mActionBar.addTab(((TabWrapper)tab).mNativeTab);
    }

    public void addTab(Tab tab, boolean setSelected) {
        mActionBar.addTab(((TabWrapper)tab).mNativeTab, setSelected);
    }

    public void addTab(Tab tab, int position) {
        mActionBar.addTab(((TabWrapper)tab).mNativeTab, position);
    }

    public void addTab(Tab tab, int position, boolean setSelected) {
        mActionBar.addTab(((TabWrapper)tab).mNativeTab, position, setSelected);
    }

    public void removeTab(Tab tab) {
        mActionBar.removeTab(((TabWrapper)tab).mNativeTab);
    }

    public void removeTabAt(int position) {
        mActionBar.removeTabAt(position);
    }

    public void removeAllTabs() {
        mActionBar.removeAllTabs();
    }

    public void selectTab(Tab tab) {
        mActionBar.selectTab(((TabWrapper)tab).mNativeTab);
    }

    public Tab getSelectedTab() {
        android.app.ActionBar.Tab selected = mActionBar.getSelectedTab();
        return (selected != null) ? (Tab)selected.getTag() : null;
    }

    public Tab getTabAt(int index) {
        android.app.ActionBar.Tab selected = mActionBar.getTabAt(index);
        return (selected != null) ? (Tab)selected.getTag() : null;
    }

    public int getTabCount() {
        return mActionBar.getTabCount();
    }

    public int getHeight() {
        return mActionBar.getHeight();
    }

    public void show() {
        mActionBar.show();
    }

    public void hide() {
        mActionBar.hide();
    }

    public boolean isShowing() {
        return mActionBar.isShowing();
    }

    public void addOnMenuVisibilityListener(OnMenuVisibilityListener listener) {
        mMenuVisibilityListeners.add(listener);
    }

    public void removeOnMenuVisibilityListener(OnMenuVisibilityListener listener) {
        mMenuVisibilityListeners.remove(listener);
    }

    public void onMenuVisibilityChanged(boolean isVisible) {
        for (OnMenuVisibilityListener listener : mMenuVisibilityListeners) {
            listener.onMenuVisibilityChanged(isVisible);
        }
    }
}
