/*
 * nbase is a library for android application to develop base features
 * It includes data , network and tab view.
 * For more information http://code.google.com/p/nbase/
 * 
 * 
 * NBase is deliver with MIT licence
 */


package org.nbase.tab;

import android.view.View;
import org.nbase.view.TabView;
import org.nbase.view.TabViewConfig;

/**
 *
 * @author rayyildiz
 */
public class TabHost {
    private TabView tabView;

    public TabHost(TabViewConfig config) {
        tabView = new TabView(config);
    }

    public void addTab(Tab tab) {
        tabView.addTab(tab);
    }

    public Tab getTab(String tag) {
        return tabView.getTab(tag);
    }

    public void setCurrentView(View currentView) {
        tabView.setCurrentView(currentView);
    }

    public void setCurrentView(int resourceViewID) {
        tabView.setCurrentView(resourceViewID);
    }

    public View render() {
        return tabView.render();
    }
}
