package asus.model.base.dialog;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import asus.model.R;
import butterknife.InjectView;
/*
        btn.setOnClickListener(view -> {
            MyDialog dialog = new MyDialog(getActivity(), "1", "2", "3")
                    .setConfirm(()->toast("确定了")).setCancel(()->toast("取消了"));
            dialog.setCancelable(false);
            dialog.show();
        });
 */

/**
 * Created by asus on 2016/7/26.
 */
public class MyDialog extends ModelDialog{

    @InjectView(R.id.title)
    TextView titleTV;
    @InjectView(R.id.cancel)
    TextView cancelTV;
    @InjectView(R.id.confirm)
    TextView confirmTV;
    private List<String> list;
    private ConfirmInterface confirmInterface;
    private CancelInterface cancelInterface;


    public MyDialog(Context context, String... strs) {
        super(context);
        list = Arrays.asList(strs);
    }

    @Override
    protected int getDialogLaout() {
        return R.layout.confirm_dialog;
    }

    @Override
    protected void init(Window dialogWindow, WindowManager.LayoutParams lp, DisplayMetrics d) {
        titleTV.setText(list.get(0));
        confirmTV.setText(list.get(1));
        cancelTV.setText(list.get(2));
        confirmTV.setOnClickListener(this);
        cancelTV.setOnClickListener(this);
        lp.width = (int) (d.widthPixels*(0.5));
        lp.height = (int)(d.heightPixels*0.5);
        dialogWindow.setAttributes(lp);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                confirmInterface.confirm();
                break;
            case R.id.cancel:
                cancelInterface.cancel();
                break;
        }
    }

    public MyDialog setConfirm(ConfirmInterface confirm){
        confirmInterface = confirm;
        return this;
    }

    public MyDialog setCancel(CancelInterface cancel){
        cancelInterface = cancel;
        return this;
    }

    public interface ConfirmInterface{
        void confirm();
    }

    public interface CancelInterface{
        void cancel();
    }

}
