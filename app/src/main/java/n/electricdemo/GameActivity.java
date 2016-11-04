package n.electricdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by huangchuanyi on 11/4/16.
 *
 * 开始界面
 *
 */

public class GameActivity extends Activity {


    private NewMouseView newMouseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        newMouseView = (NewMouseView) findViewById(R.id.nm);

        newMouseView.setOnFinishClickListener(new NewMouseView.OnFinishClickListener() {
            @Override
            public void finish(int count) {
                finishDialog(count);
            }
        });

    }

    /**
     * 结束弹框
     */
    private void finishDialog(int count) {
        final AlertDialog myDialog = new AlertDialog.Builder(this).create();
        myDialog.setCanceledOnTouchOutside(false);
        myDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        myDialog.show();
        View view = View.inflate(this, R.layout.dialog_finish, null);

        TextView tipsTV = (TextView) view.findViewById(R.id.tips);
        tipsTV.setText(count + "");

        TextView finishTV = (TextView) view.findViewById(R.id.finish);
        finishTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView restartTV = (TextView) view.findViewById(R.id.restart);
        restartTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                newMouseView.startAgainGame();
            }
        });
        myDialog.setContentView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        newMouseView.startGame();
    }

    @Override
    protected void onPause() {
        super.onPause();
        newMouseView.stopGame();
    }


}
