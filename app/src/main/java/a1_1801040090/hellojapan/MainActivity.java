package a1_1801040090.hellojapan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mp;
    private ScrollView view1;
    private ScrollView view2;
    private boolean end = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void swap(View view){
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        String s = view.getResources().getResourceName(view.getId());
        String table = s.substring(28);

        if(table.equals("hiragana") && view1.getVisibility()==View.INVISIBLE && end){
            end = false;
            view2.animate().translationXBy(700f).alpha(0).setDuration(1000).withEndAction(new Runnable() {
                @Override
                public void run() {
                    view2.setVisibility(View.INVISIBLE);
                    view2.setAlpha(1);
                    view2.animate().translationXBy(-700f).setDuration(10);
                }
            });

            view1.scrollTo(0,0);
            view1.setAlpha(0);
            view1.setVisibility(View.VISIBLE);
            view1.animate().translationXBy(-700f).setDuration(10).withEndAction(new Runnable() {
                @Override
                public void run() {
                    view1.animate().translationXBy(700f).alpha(1).setDuration(1000).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            end = true;
                        }
                    });
                }
            });

        }else if(table.equals("katakana") && view2.getVisibility()==View.INVISIBLE && end){
            end = false;
            view1.animate().translationXBy(-700f).alpha(0).setDuration(1000).withEndAction(new Runnable() {
                @Override
                public void run() {
                    view1.setVisibility(View.INVISIBLE);
                    view1.setAlpha(1);
                    view1.animate().translationXBy(700f).setDuration(10);
                }
            });

            view2.scrollTo(0,0);
            view2.setAlpha(0);
            view2.setVisibility(View.VISIBLE);
            view2.animate().translationXBy(700f).setDuration(10).withEndAction(new Runnable() {
                @Override
                public void run() {
                    view2.animate().translationXBy(-700f).alpha(1).setDuration(1000).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            end = true;
                        }
                    });
                }
            });

        }
    }


    public void clickA(View view){
        String s = view.getResources().getResourceName(view.getId());
        String name = s.substring(28);
        Resources res = getResources();

        int sound = res.getIdentifier(name, "raw", getPackageName());
        if(mp != null){
            mp.stop();
            mp.release();
            mp = null;
        }
        mp = MediaPlayer.create(this, sound);
        mp.start();
    }

    public void clickB(View view){
        String s = view.getResources().getResourceName(view.getId());
        int leng = s.length();
        String name = s.substring(28,leng-1);

        Resources res = getResources();

        int sound = res.getIdentifier(name, "raw", getPackageName());
        if(mp != null){
            mp.stop();
            mp.release();
            mp = null;
        }
        mp = MediaPlayer.create(this, sound);
        mp.start();
    }

}