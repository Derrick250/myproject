package geeks.freaky.contraceptive_sos;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by theTypan on 7/25/2016.
 */
public class CondomActivity extends AppCompatActivity {

    private static final String TAG = CondomActivity.class.getSimpleName();
    private GridAdapter gridAdapter;
    private Toolbar toolbar;
    private PopupWindow pw;
    private Button bCancel;
    private Button bOrder;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condom);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(NavUtils.getParentActivityName(this)!= null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridAdapter = new GridAdapter();
        gridView.setAdapter(gridAdapter);

        layout = new LinearLayout(this);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {

                String selected = (String) (gridView.getItemAtPosition(myItemInt));

                Log.d(TAG, selected + " was clicked");

                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popView = inflater.inflate(R.layout.pop_up, null, false);

                pw = new PopupWindow(popView, 600, 700, true);
                bCancel = (Button)popView.findViewById(R.id.bCancel);
                bOrder = (Button)popView.findViewById(R.id.bOrder);
                pw.showAtLocation(layout, Gravity.CENTER, 10, 10);
                bCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pw.dismiss();
                    }
                });



            }
        });



    }//end method onCreate

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){

            case android.R.id.home:
                if(NavUtils.getParentActivityName(this) != null)
                    NavUtils.navigateUpFromSameTask(this);
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }
    }//end method onOptiondItemSelected


    private class GridAdapter extends BaseAdapter {

        private LayoutInflater mInflater;


        public GridAdapter() {
            mInflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);




        }//end constructor
       public Integer[] mThumbIds = {
                R.drawable.durex,
               R.drawable.trust,
               R.drawable.trojan,
               R.drawable.bareback,
               R.drawable.impulse,
               R.drawable.salama,
               R.drawable.fantasy,
               R.drawable.femiplan,
               R.drawable.rough_rider
        };

        public String[] brands={"durex","trust","trojan","bareback","impulse","salama","fantasy",
                "femiplan","rough rider"};
        public int[] price = {150,80,180,200,200,60,180,130,200};




        @Override
        public int getCount() {
            return brands.length;
        }

        @Override
        public Object getItem(int position) {
            return brands[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //if we arent given a view inflate one
            //if there's no recycled view passed in inflate one
            final ViewHolder holder;
            if (convertView == null) {

                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.grid_item, null);
                holder.photo = (ImageView)
                        convertView.findViewById(R.id.grid_item_imageView);

                holder.name = (TextView)
                        convertView.findViewById(R.id.grid_item_name);
                convertView.setTag(holder);

                holder.price = (TextView)
                        convertView.findViewById(R.id.grid_item_price);
                convertView.setTag(holder);


            }else {

                holder = (ViewHolder)convertView.getTag();

            }

            holder.photo.setImageResource(mThumbIds[position]);
            holder.photo.setScaleType(ImageView.ScaleType.CENTER_CROP);
            holder.name.setText(brands[position]);
            holder.price.setText("Ksh " + price[position]);


            return convertView;



        }
    }//end class GridAdapter

    class ViewHolder {

        ImageView photo;
        TextView name;
        TextView price;

    }




}//end class CondomActivity


