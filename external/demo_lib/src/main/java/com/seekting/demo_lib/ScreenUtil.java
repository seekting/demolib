package com.seekting.demo_lib;

import android.app.KeyguardManager;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * ScreenUtils
 * <ul>
 * <strong>Convert between dp and px</strong>
 * <li>{@link ScreenUtil#dpToPx(Context, float)}</li>
 * <li>{@link ScreenUtil#pxToDp(Context, float)}</li>
 * </ul>
 *
 * @author xiaozhongzhong
 */
public class ScreenUtil {

    public static float dpToPx(Context context, float dp) {
        if (context == null) {
            return -1;
        }
        float density = context.getResources().getDisplayMetrics().density;
        return density * dp;

        //        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics())
        //                * Float.parseFloat(context.getResources().getString(R.dimen.booster_density));
    }

    public static int dpToPxInt(Context context, float dp) {
        return (int) (ScreenUtil.dpToPx(context, dp) + 0.5f);
    }

    public static int getDimenPx(Context context, int dimenId) {
        return context.getResources().getDimensionPixelSize(dimenId);
    }

    public static float pxToDp(Context context, float px) {
        if (context == null) {
            return -1;
        }
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static int pxToDpCeilInt(Context context, float px) {
        return (int) (ScreenUtil.pxToDp(context, px) + 0.5f);
    }

    public static int getScreenWidth(Context context) {
        if (context == null) {
            return 0;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int w = displayMetrics.widthPixels;
        if (w <= 0) {
            w = 1080;
        }
        return w;
    }

    /**
     * 获取 DisplayMetrics
     * 
     * @param context
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static boolean isSreenLocked(Context mContext) {
        KeyguardManager km = (KeyguardManager) mContext.getSystemService(Context.KEYGUARD_SERVICE);
        boolean locked = km.inKeyguardRestrictedInputMode();

        return locked;
    }
}
