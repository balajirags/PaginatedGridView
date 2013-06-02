package balaji.example.paginatedgridview;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import balaji.example.adapter.MovieAdapter;
import balaji.example.model.Movie;

import java.util.Arrays;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        ListView gridView = (ListView) findViewById(R.id.gridView);
        Movie movieOne = new Movie("http://images.moviebuff.in/ecab0fbc-d8a3-4769-9958-389758e58654?w=500");
        Movie movieTwo = new Movie("http://images.moviebuff.in/e4191484-3938-4801-bd9d-018292fb86f0?w=500");
        Movie movieThree = new Movie("http://images.moviebuff.in/4a6814aa-a47a-460b-9b0a-a001f4d1ac8b?w=500");
        MovieAdapter movieAdapter = new MovieAdapter(Arrays.asList(movieOne, movieTwo, movieThree), this);
        gridView.setAdapter(movieAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
