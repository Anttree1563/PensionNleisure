/*
package com.truemind.pensionnleisure;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

*/
/**
 * Created by 현석 on 2017-01-18.
 *//*

public class WordComposer extends View {

    private EditableInputConnection mEii;

    public WordComposer(Context context, AttributeSet attrs){
        super(context, attrs);
        setFocusableInTouchMode(true);
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
        if(event.getAction() == MotionEvent.ACTION_UP){

            InputMethodManager imm;
            imm = (InputMethodManager) getContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(this,InputMethodManager.SHOW_FORCED);
        }
        return true;
    }

    @Override
    public boolean onCheckIsTextEditor() {
        return true;
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {

        outAttrs.actionLabel = null;
        outAttrs.label = "Composer Test";
        outAttrs.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;
        outAttrs.imeOptions = EditorInfo.IME_ACTION_DONE;
        if(mEii = null)
            mEii = new EditableInputConnection(this);
        return mEii;
    }

    public class EditableInputConnection extends BaseInputConnection
    {
        private final View mView;
        private SpannableStringBuilder mEditable;
        String mText = new String();

        public EditableInputConnection(View textview)
        {
            super(textview, true);
            mView = textview;
            mEditable = (SpannableStringBuilder) Editable.Factory.getInstance().newEditable("composer");
        }
        public Editable getEditable(){
            return mEditable;
        }

        //


        @Override
        public boolean commitText(CharSequence text, int newCursorPosition) {
            return super.commitText(text, newCursorPosition);
        }
    }
}
*/
