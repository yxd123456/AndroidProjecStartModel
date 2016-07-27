package asus.model.base.design;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;

/*
    textInputUtil = new TextInputUtil(til, R.id.et){
    @Override
    protected void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            check(charSequence.length()>10, "长度不能超过十位");
        }
    };
*/
/**
 * Created by asus on 2016/7/26.
 * EditText升级版
 */
public class TextInputUtil {

    private TextInputLayout layout;

    public TextInputUtil(TextInputLayout layout, int editId){
        this.layout = layout;
        TextInputEditText editText = (TextInputEditText) layout.findViewById(editId);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               TextInputUtil.this.onTextChanged(charSequence, i, i1, i2);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    protected void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }


    public void check(boolean errorFlag, String errorMsg) {
        if (errorFlag){
            layout.setError(errorMsg);
            layout.setErrorEnabled(true);
        } else {
            layout.setErrorEnabled(false);
        }
    }


}
