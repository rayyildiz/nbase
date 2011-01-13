/*
 * nbase is a library for android application to develop base features
 * It includes data , network and tab view.
 * For more information http://code.google.com/p/nbase/
 * 
 * 
 * NBase is deliver with MIT licence
 */

package org.nbase.view;

import android.content.Context;
import org.nbase.tab.Orientation;

/**
 *
 * @author rayyildiz
 */

public class TabViewConfig {

    Context context;
    int headerResourceId;
    int separatorId;
    Orientation orientation;

    public TabViewConfig context(Context context) {
        this.context = context;
        return this;
    }

    public TabViewConfig headerResourceId(int headerResourceId) {
        this.headerResourceId = headerResourceId;
        return this;
    }

    public TabViewConfig separatorId(int separatorId) {
        this.separatorId = separatorId;
        return this;
    }

    public TabViewConfig orientation(Orientation orientation) {
        this.orientation = orientation;
        return this;
    }
}
