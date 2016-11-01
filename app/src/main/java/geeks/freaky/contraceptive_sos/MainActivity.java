
package geeks.freaky.contraceptive_sos;

import android.app.ProgressDialog;
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

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import geeks.freaky.contraceptive_sos.app.AppConfig;
import geeks.freaky.contraceptive_sos.app.AppController;
import geeks.freaky.contraceptive_sos.helper.CustomVolleyRequest;


public class MainActivity extends AppCompatActivity{

    private static final String TAG = MainActivity.class.getSimpleName();
    private CategoryListAdapater adapter;
    private Toolbar toolbar;
    private ListView listView;
    private ProgressDialog pDialog;
    private ArrayList<String> mCategories = new ArrayList();
    private ArrayList<String> mDescriptions = new ArrayList();
    private ArrayList<String> mImages = new ArrayList<>();
    private ImageLoader imageLoader;

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

                Log.d(TAG, selectedFromList + " was clicked");


                if (selectedFromList.equals("Condoms")) {

                    Intent i = new Intent(MainActivity.this, CondomActivity.class);
                    startActivity(i);
                }

            }
        });

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        loadCategories();

    }//end method onCreate

    private class CategoryListAdapater extends BaseAdapter {

        private LayoutInflater mInflater;


        public CategoryListAdapater() {
            mInflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }//end constructor

        @Override
        public int getCount() {
            return mCategories.size();
        }

        @Override
        public Object getItem(int position) {
            return mCategories.get(position);
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
                holder.photo = (NetworkImageView)
                        convertView.findViewById(R.id.item_list_view_imageView);
                holder.name = (TextView)
                        convertView.findViewById(R.id.item_list_name);
                holder.desc = (TextView)convertView.findViewById(R.id.item_list_desc);
                convertView.setTag(holder);

            }else {

                holder = (ViewHolder)convertView.getTag();

            }

            loadImage(holder.photo, AppConfig.URL_IMAGE_PREFIX + mImages.get(position));
            Log.d(TAG, AppConfig.URL_IMAGE_PREFIX + mImages.get(position));
                    holder.name.setText(mCategories.get(position));
            holder.desc.setText(mDescriptions.get(position));

            return convertView;
        }
    }//end class GridAdapter

    class ViewHolder {

        //ImageView photo;
        NetworkImageView photo;
        TextView name;
        TextView desc;

    }

    private void loadCategories(){

        String tag_json_array = "req_categories";

        pDialog.setMessage("Loading...");
        showDialog();

        JsonArrayRequest req = new JsonArrayRequest(AppConfig.URL_CATEGORY,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hideDialog();

                        for(int i=0; i<response.length(); i++){

                            try{
                                //get each JSON object in JSONArray
                                JSONObject category = response.getJSONObject(i);

                                //get each element of JSON object
                                String description = category.getString("description");
                                String name = category.getString("name");
                                String image = category.getString("image");

                                Log.d(TAG,image);
                                mCategories.add(name);
                                mDescriptions.add(description);
                                mImages.add(image);

                            }catch (JSONException e){

                               Log.d(TAG, "Error: " + e.getMessage());
                            }
                        }//end for
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hideDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, tag_json_array);

    }//end method loadCategories

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void loadImage(NetworkImageView imageView, String url){

        imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext())
                .getImageLoader();
        imageLoader.get(url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {

            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        imageView.setImageUrl(url, imageLoader);
    }


}//end class MainActivity
