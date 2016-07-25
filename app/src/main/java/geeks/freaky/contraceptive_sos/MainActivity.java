
package geeks.freaky.contraceptive_sos;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    CardView cardView;
    CardView cardView1;
    CardView cardView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardView = (CardView)findViewById(R.id.card_view);
        cardView1 = (CardView)findViewById(R.id.card_view1);
        cardView2 = (CardView)findViewById(R.id.card_view2);

        cardView.setOnClickListener(this);
        cardView1.setOnClickListener(this);
        cardView2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card_view:
                System.out.println(">>>>>>>>>>>>>> Clicked 1");
                break;
            case R.id.card_view1:
                System.out.println(">>>>>>>>>>>>>> Clicked 2");
                break;
            case R.id.card_view2:
                System.out.println(">>>>>>>>>>>>>> Clicked 3");
                break;

        }
    }
}
