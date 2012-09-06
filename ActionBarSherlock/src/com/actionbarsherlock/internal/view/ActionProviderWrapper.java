package com.actionbarsherlock.internal.view;

import com.actionbarsherlock.internal.view.menu.SubMenuWrapper;
import com.actionbarsherlock.view.ActionProvider;
import android.view.View;

public class ActionProviderWrapper extends android.view.ActionProvider {
    private final ActionProvider mProvider;


    public ActionProviderWrapper(ActionProvider provider) {
        super(null/*TODO*/); //XXX this *should* be unused
        mProvider = provider;
    }


    public ActionProvider unwrap() {
        return mProvider;
    }

    public View onCreateActionView() {
        return mProvider.onCreateActionView();
    }

    public boolean hasSubMenu() {
        return mProvider.hasSubMenu();
    }

    public boolean onPerformDefaultAction() {
        return mProvider.onPerformDefaultAction();
    }

    public void onPrepareSubMenu(android.view.SubMenu subMenu) {
        mProvider.onPrepareSubMenu(new SubMenuWrapper(subMenu));
    }
}
