
package geeks.freaky.contraceptive_sos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity{

    private static final String TAG = MainActivity.class.getSimpleName();
    private CategoryListAdapater adapter;
    private Toolbar toolbar;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(android.R.id.list);
        adapter = new CategoryListAdapater();
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                String selectedFromList = (String) (listView.getItemAtPosition(myItemInt));

                Log.d(TAG,  selectedFromList + " was clicked");

                if(selectedFromList.equals("Condoms")){

                    Intent i = new Intent(MainActivity.this,CondomActivity.class);
                    startActivity(i);
                }

            }
        });



    }//end method onCreate

    private class CategoryListAdapater extends BaseAdapter {

        private LayoutInflater mInflater;


        public CategoryListAdapater() {
            mInflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }//end constructor
        public Integer[] mThumbIds = {
                R.drawable.condom,
                R.drawable.pill,
                R.drawable.pregnancy
        };

        public String[] categories={"Condoms","Birth Control pills","Pregnancy testing kits"};

        @Override
        public int getCount() {
            return categories.length;
        }

        @Override
        public Object getItem(int position) {
            return categories[position];
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
                convertView = mInflater.inflate(R.layout.list_item, null);
                holder.photo = (ImageView)
                        convertView.findViewById(R.id.item_list_view_imageView);

                holder.name = (TextView)
                        convertView.findViewById(R.id.item_list_name);
                convertView.setTag(holder);


            }else {

                holder = (ViewHolder)convertView.getTag();

            }

            holder.photo.setImageResource(mThumbIds[position]);
            holder.photo.setScaleType(ImageView.ScaleType.CENTER_CROP);
            holder.name.setText(categories[position]);

            return convertView;



        }
    }//end class GridAdapter

    class ViewHolder {

        ImageView photo;
        TextView name;

    }


}//end class MainActivity
