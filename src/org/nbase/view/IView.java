/*
 * nbase is a library for android application to develop base features
 * It includes data , network and tab view.
 * For more information http://code.google.com/p/nbase/
 * 
 * 
 * NBase is deliver with MIT licence
 */

package org.nbase.view;

import android.view.View;
import org.nbase.tab.Tab;

/**
 *
 * @author rayyildiz
 */
public interface IView {
	View render();
	void addTab(Tab tab);
}
