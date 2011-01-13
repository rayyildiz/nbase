/*
 * nbase is a library for android application to develop base features
 * It includes data , network and tab view.
 * For more information http://kenai.com/projects/nbase
 * 
 * 
 * nbase is deliver with MIT licence
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
