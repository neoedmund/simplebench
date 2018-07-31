package neoe.simplebench;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.File;

import neoe.bench.Test1;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            final View comp = findViewById(R.id.navigation_home);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mTextMessage.setText("starting benchmark");
                                    comp.setEnabled(false);
                                }
                            });
                            runTest();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    comp.setEnabled(true);
                                }
                            });

                        }
                    }, 10);


                    return true;

            }
            return false;
        }
    };

    void runTest() {
        File dir = this.getFilesDir();
        String s = new Test1().run(dir);
        mTextMessage.setText(s);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
