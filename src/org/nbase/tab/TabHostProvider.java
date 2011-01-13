/*
 * nbase is a library for android application to develop base features
 * It includes data , network and tab view.
 * For more information http://kenai.com/projects/nbase
 * 
 * 
 * nbase is deliver with MIT licence
 */

package org.nbase.tab;

import android.app.Activity;

/**
 *
 * @author rayyildiz
 */
public abstract class TabHostProvider {

    public Activity context;

    public TabHostProvider(Activity context) {
        this.context = context;
    }

    public abstract TabHost getTabHost(String category);
}
