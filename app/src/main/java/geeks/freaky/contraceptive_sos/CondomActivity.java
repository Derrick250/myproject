package geeks.freaky.contraceptive_sos;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by theTypan on 7/25/2016.
 */
public class CondomActivity extends AppCompatActivity {

    private GridAdapter gridAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condom);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(NavUtils.getParentActivityName(this)!= null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridAdapter = new GridAdapter();
        gridView.setAdapter(gridAdapter);


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
               R.drawable.trojan
        };

        public String[] brands={"durex","trust","trojan"};
        public int[] price = {150,80,180};




        @Override
        public int getCount() {
            return mThumbIds.length;
        }

        @Override
        public Object getItem(int position) {
            return mThumbIds[position];
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


