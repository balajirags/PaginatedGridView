package balaji.example.paginatedgridview;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import balaji.example.adapter.MovieAdapter;
import balaji.example.model.Movie;
import com.origamilabs.library.views.StaggeredGridView;

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
        Movie movieFour = new Movie("http://images.moviebuff.in/b767ef46-b4ff-459c-95ba-1d08f79d2259?w=500");
        Movie movieFive = new Movie("http://images.moviebuff.in/6292e326-ac0a-439c-95b2-700aa8124997?w=500");
        Movie movieSix = new Movie("http://images.moviebuff.in/ba6529a5-7758-420f-8d88-0cc3e6d089af?w=500");
        Movie movieSeven = new Movie("http://images.moviebuff.in/4889e5f5-4fa4-4011-8f56-9604d23a1f5b?w=500");

        MovieAdapter movieAdapter = new MovieAdapter(Arrays.asList(movieOne, movieTwo, movieThree,movieThree,movieFour,movieFive,movieSix,movieSeven), this);
        gridView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
