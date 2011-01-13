/*
 * nbase is a library for android application to develop base features
 * It includes data , network and tab view.
 * For more information http://code.google.com/p/nbase/
 * 
 * 
 * NBase is deliver with MIT licence
 */


package org.nbase.tab;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 *
 * @author rayyildiz
 */
public class Tab {

    @SuppressWarnings("unused")
	private static final String TAG = Tab.class.getSimpleName();
    private int resourceIcon;
    private int resourceIconSelected = 0;
    private Activity context;
    private Intent intent;
    private View view;
    private ImageButton btn;
    private String tabTag;
    public int preferedHeight = -1;
    private boolean isSelected;
    private int transparentResourceID;
    private Dialog dialog;
    private int requestCode = -1;

    public Tab(Activity context, String tabTag) {
        if (context == null) {
            throw new IllegalStateException("Context can't be null");
        }
        this.tabTag = tabTag;
        this.context = context;
    }

    public void setIcon(int resourceIcon) {
        this.resourceIcon = resourceIcon;

    }

    public void setIconSelected(int resourceIcon) {
        this.resourceIconSelected = resourceIcon;
    }

    public void setIntent(Intent intent, int requestForResult) {
        this.intent = intent;
        this.requestCode = requestForResult;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public Intent getIntent() {
        return intent;
    }

    public View getView() {
        if (view == null) {
            createView();
        }
        return view;
    }

    private void createView() {
        btn = new ImageButton(context);
        btn.setMaxHeight(preferedHeight);
        btn.setMinimumHeight(preferedHeight);
        btn.setPadding(0, 0, 0, 0);
        int iconId = resourceIcon;

        if (isSelected && resourceIconSelected != 0) {
            iconId = resourceIconSelected;
        }
        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), iconId);

        btn.setBackgroundResource(transparentResourceID);
        btn.setImageBitmap(icon);

        bindListeners();
        view = btn;
    }

    private void bindListeners() {
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                //	btn.setBackgroundColor(Color.BLUE);
                if (intent == null && dialog == null) {
                    Toast.makeText(context, "Intent or Dialog not set for '" + tabTag + "'", Toast.LENGTH_SHORT).show();
                    //  context.finish();
                } else if (intent != null && dialog != null) {
                    Toast.makeText(context, " Only ONE can be set Intent or Dialog for '" + tabTag + "'", Toast.LENGTH_SHORT).show();
                } else {
                    if (intent != null) {
                        if (requestCode != -1) {
                            context.startActivityForResult(intent, requestCode);
                        } else {
                            context.startActivity(intent);
                        }
                    } else if (dialog != null) {
                        dialog.show();
                    }

                }

            }
        });

        btn.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent e) {
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    btn.setBackgroundColor(0x200000FF);
                } else if (e.getAction() == MotionEvent.ACTION_UP) {
                    //Set back to transparent color 00==Full transparencty 255 == none
                    btn.setBackgroundColor(0x00FFFFFF);
                }
                return false;
            }
        });
    }

    public String getTag() {
        return tabTag;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public void setTransparentResourceID(int transparentResourceID) {
        this.transparentResourceID = transparentResourceID;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }
}
