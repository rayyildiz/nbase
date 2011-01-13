/*
 * nbase is a library for android application to develop base features
 * It includes data , network and tab view.
 * For more information http://kenai.com/projects/nbase
 * 
 * 
 * nbase is deliver with MIT licence
 */

package org.nbase.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import java.util.ArrayList;
import java.util.List;
import org.nbase.tab.Orientation;
import org.nbase.tab.Tab;

/**
 *
 * @author rayyildiz
 */
public class TabView implements IView {

    @SuppressWarnings("unused")
	private static final String TAG = TabView.class.getSimpleName();
    private List<Tab> tabSet = new ArrayList<Tab>();
    private Bitmap mHeader;
    @SuppressWarnings("unused")
	private int mHeaderWidth;
    private int mHeaderHeight;
    private Context context;
    private View currentView;
    private Orientation orientation;
    private int backgroundID;
    private Bitmap iconSeparator;
    private int separatorID;

    public TabView(TabViewConfig config) {
        this.context = config.context;
        this.orientation = config.orientation;
        this.backgroundID = config.headerResourceId;
        this.separatorID = config.separatorId;
        preapareViewResources();
    }

    private void preapareViewResources() {
        mHeader = BitmapFactory.decodeResource(context.getResources(), backgroundID);
        mHeaderWidth = mHeader.getWidth();
        mHeaderHeight = mHeader.getHeight();
    }

    public void addTab(Tab tab) {
        tab.preferedHeight = mHeaderHeight;
        tabSet.add(tab);
    }

    public View render() {

        switch (orientation) {
            case TOP:
                return renderTOP();

            case BOTTOM:
                return renderBOTTOM();

        }
        return null;
    }

    public View renderBOTTOM() {
        int tabsize = tabSet.size();
        FrameLayout.LayoutParams pTable = new FrameLayout.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT);

        TableLayout table = new TableLayout(context);
        table.setLayoutParams(pTable);

        TableRow rowTop = new TableRow(context);

        TableLayout.LayoutParams pRowTop = new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.FILL_PARENT);
        pRowTop.weight = 1;


        TableRow.LayoutParams pSpan = new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.FILL_PARENT);
        pSpan.span = tabsize;
        pSpan.weight = 1;
        /*

        ViewGroup.LayoutParams scrollerParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        ScrollView scroller=new ScrollView(context);
        scroller.addView(currentView,   scrollerParams);

        rowTop.addView(scroller, pSpan);

         */
        rowTop.addView(currentView, pSpan);


        TableRow rowBottom = new TableRow(context);
        rowBottom.setBackgroundResource(backgroundID);

        TableLayout.LayoutParams pRowBottom = new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);

        int j = 0;
        for (int i = 0; i < tabsize; i++) {
            Tab tab = tabSet.get(i);
            View view = tab.getView();
            TableRow.LayoutParams pCol = new TableRow.LayoutParams();
            pCol.weight = 1;

            rowBottom.addView(view, pCol);

            //Handle the separators between tabs
            if (j % 2 == 0 && i < tabsize - 1) {
                TableRow.LayoutParams pSpanCol = new TableRow.LayoutParams();
                pSpan.weight = 1;
                rowBottom.addView(getSeparatorView(), pSpanCol);
                j++;
            }
            j++;
        }

        table.addView(rowTop, pRowTop);
        table.addView(rowBottom, pRowBottom);

        return table;
    }

    private View getSeparatorView() {
        if (iconSeparator == null) {
            iconSeparator = BitmapFactory.decodeResource(context.getResources(), separatorID);
        }
        int separatorHeigth = iconSeparator.getHeight();
        ImageButton separatorView = new ImageButton(context);
        separatorView.setMaxHeight(separatorHeigth);
        separatorView.setMinimumHeight(separatorHeigth);
        separatorView.setMaxWidth(10);

        int topPadding = 0;
        if (mHeaderHeight > separatorHeigth) {
            topPadding = (mHeaderHeight - separatorHeigth) / 2;
        }

        separatorView.setPadding(5, topPadding, 5, 0);
        separatorView.setBackgroundColor(0x00);
        separatorView.setImageBitmap(iconSeparator);
        return separatorView;
    }

    /**
     * This method needs to be called after all tabs have been added
     */
    public View renderTOP() {
        int tabsize = tabSet.size();

        FrameLayout.LayoutParams pTable = new FrameLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.FILL_PARENT);

        TableLayout table = new TableLayout(context);
        table.setLayoutParams(pTable);


        TableRow rowContent = new TableRow(context);
        TableRow.LayoutParams pRowContent = new TableRow.LayoutParams();
        pRowContent.span = tabsize;
        pRowContent.width = TableRow.LayoutParams.FILL_PARENT;
        pRowContent.height = TableRow.LayoutParams.WRAP_CONTENT;
        pRowContent.weight = 1;

        ViewGroup.LayoutParams scrollerParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);

        ScrollView scroller = new ScrollView(context);
        scroller.setLayoutParams(scrollerParams);

        scroller.addView(currentView, scrollerParams);
        rowContent.addView(scroller, pRowContent);

        TableRow rowTabs = new TableRow(context);
        rowTabs.setBackgroundResource(backgroundID);

        for (int i = 0; i < tabsize; i++) {
            Tab tab = tabSet.get(i);
            View view = tab.getView();
            TableRow.LayoutParams pCol = new TableRow.LayoutParams();
            pCol.weight = 1;
            rowTabs.addView(view, pCol);
        }


        TableRow.LayoutParams pRowTabs = new TableRow.LayoutParams();
        pRowTabs.height = TableRow.LayoutParams.WRAP_CONTENT;
        pRowTabs.weight = 1;

        table.addView(rowTabs, pRowTabs);
        table.addView(rowContent);

        return table;
    }

    /*
    @Override
    protected void dispatchDraw(Canvas canvas) {
    //canvas.drawBitmap(mHeader, 0, 0, null);
    super.dispatchDraw(canvas);
    }

     */
    public void setCurrentView(View currentView) {
        this.currentView = currentView;
    }

    public void setCurrentView(int resourceViewID) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resourceViewID, null);
        setCurrentView(view);
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void setBackgroundID(int backgroundID) {
        this.backgroundID = backgroundID;
    }

    public Tab getTab(String tag) {
        for (int i = 0; i < tabSet.size(); i++) {
            Tab t = tabSet.get(i);
            if (tag.equals(t.getTag())) {
                return t;
            }
        }
        throw new IllegalArgumentException("Tab \"" + tag + "\" not found");
    }
}
