package com.radicallabsinc.pakarhero.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.radicallabsinc.pakarhero.R;

public final class ViewUtils {
    private ViewUtils() {
        // This utility class is not publicly instantiable
    }

    public static float pxToDp(float px) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return px / (densityDpi / 160f);
    }

    public static int dpToPx(float dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static void changeIconDrawableToGray(Context context, Drawable drawable) {
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(ContextCompat
                    .getColor(context, R.color.dark_gray), PorterDuff.Mode.SRC_ATOP);
        }
    }

    public static void setClickableSpanForSignUp(TextView textView, final String str, int linkColorRes, final View.OnClickListener listener) {
        SpannableString ss = new SpannableString(str);
        String signUp = textView.getContext().getString(R.string.or_join);
        ClickableSpan signUpSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                view.setTag("join");
                listener.onClick(view);
            }
        };
        ss.setSpan(signUpSpan, str.indexOf(signUp), str.indexOf(signUp) + signUp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(textView.getContext().getResources().getColor(linkColorRes)), str.indexOf(signUp), str.indexOf(signUp) + signUp.length(), 0);
        textView.setText(ss, TextView.BufferType.SPANNABLE);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static void setClickableSpanForTerms(TextView textView, final String str, int linkColorRes, final View.OnClickListener listener) {
        SpannableString ss = new SpannableString(str);
        String terms = textView.getContext().getString(R.string.terms_of_use);
        ClickableSpan termsSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                view.setTag("terms");
                listener.onClick(view);
            }
        };
        ss.setSpan(termsSpan, str.indexOf(terms), str.indexOf(terms)+terms.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(textView.getContext().getResources().getColor(linkColorRes)), str.indexOf(terms), str.indexOf(terms)+terms.length(),0);
        textView.setText(ss, TextView.BufferType.SPANNABLE);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
