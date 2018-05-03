package com.jjbars.jjbarscalisthenics;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

public class CreateCustomWorkout extends AppCompatActivity {

    Button goBack;
    ToggleButton tb;

    SearchView filter;
    RecyclerView recyclerView1;
    static RecyclerView recyclerView2;
    RecyclerView.Adapter rvadapter1;
    static RecyclerView.Adapter rvadapter2;
    RecyclerView.LayoutManager rvLayoutManager1;
    RecyclerView.LayoutManager rvLayoutManager2;
    public static ArrayList<thenxObjects> selectedList;
    Button add_round_button;

    public static ArrayList<thenxObjects> data;
    ArrayList<String> exerciseList;
    DatabaseHelper db ;
    public static boolean good = true ;
    public static Context c;
    public static boolean round;
    static int num = 1;


    private Button save_Workout_Button;
    public ArrayList<ArrayList<thenxObjects>> savedWorkouts;// = new ArrayList<ArrayList<thenxObjects>>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_custom_workout);

        recyclerView2 = findViewById(R.id.create_custom_workout_recyclerView);
        recyclerView2.setHasFixedSize(true);
        recyclerView1 =  findViewById(R.id.find_exercises_recyclerView);
        recyclerView1.setHasFixedSize(true);

        db = new DatabaseHelper(this);
        filter =  findViewById(R.id.filter_searchView);
        filter.setQueryHint("Search Here");
        filter.setQueryRefinementEnabled(true);
        rvLayoutManager2 = new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(rvLayoutManager2);
        rvLayoutManager1 = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(rvLayoutManager1);
        selectedList = new ArrayList<>();
        data = new ArrayList<thenxObjects>();
        c = this;
        loadData();
        fetchData();

        add_round_button = findViewById(R.id.add_round_button);
        add_round_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateCustomWorkout.setRound();

            }
        });







        tb = findViewById(R.id.toggleButton);
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    good=true;
                } else {
                    good=false;
                }
            }
        });






        int searchCloseButtonId = filter.getContext().getResources()
                .getIdentifier("android:id/search_close_btn", null, null);
        ImageView closeButton = (ImageView) this.filter.findViewById(searchCloseButtonId);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData();
                filter.setQuery("",false);
                filter.onActionViewCollapsed();

            }
        });




        filter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                query = query.toLowerCase();
                fetchData(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                   query = query.toLowerCase();
                    final ArrayList<thenxObjects>filteredList = new ArrayList<thenxObjects>();
                    for (int i = 0; i < exerciseList.size(); i++) {
                        final String text = exerciseList.get(i).toLowerCase();
                        if (text.contains(query)) {

                            filteredList.add(new thenxObjects(exerciseList.get(i)));
                        }
                       // good = true;
                        rvadapter1 = new RvAdapterKlasse(filteredList);
                        recyclerView1.setAdapter(rvadapter1);

                    }
                    return true;
                }
        });

        goBack = (Button) findViewById(R.id.back_home_button);
        goBack.setOnClickListener(e->{
            startActivity(new Intent(this, Home.class));
            num = 1;
        });


        save_Workout_Button = findViewById(R.id.save_workout_button);
        save_Workout_Button.setOnClickListener(v -> {

            savedWorkouts.add(selectedList);
            saveData();
            System.out.println("size of workoutlist: " + savedWorkouts.size());
        });

    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(savedWorkouts);
        editor.putString("Workout X",json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Workout X",null);
        Type type = new TypeToken<ArrayList<ArrayList<thenxObjects>>>() {}.getType();
        savedWorkouts = gson.fromJson(json,type);

        if(savedWorkouts== null){
            savedWorkouts = new ArrayList<>();
        }
    }

    public static void setSelectedList(thenxObjects thenxObject){

        round=false;
        if (good){
        boolean exist = false;
        Iterator<thenxObjects> i = selectedList.iterator();
        while (i.hasNext()){
            if(i.next().equals(thenxObject)){
                exist = true;
            }
        }
        if (!exist) {
            selectedList.add(thenxObject);
        }else{

            Toast.makeText(c,"This exercise already exist!",Toast.LENGTH_SHORT).show();
        }
        rvadapter2 = new RvAdapterKlasse(selectedList);
        recyclerView2.setAdapter(rvadapter2);
        recyclerView2.smoothScrollToPosition(selectedList.size());
    }}

    public static void delSelectedList(thenxObjects thenxObject){
       round=false;
       int count = -1;
        if(!good){
        boolean deleted = false;
        Iterator<thenxObjects> i = selectedList.iterator();
        while (i.hasNext()&& !deleted){
            count++;
            if(i.next().equals(thenxObject)){


                if(thenxObject.getExercise().toString().charAt(0) == '<' && ((int) (thenxObject.getExercise().toString().charAt(12)) - 48) != (num - 1)){
                    Toast.makeText(c,"Delete last round!",Toast.LENGTH_SHORT).show();

                }else {
                    selectedList.remove(thenxObject);
                    deleted = true;

                    if (thenxObject.getExercise().toString().charAt(0) == '<' ){
                    num--;
                    }
                }
            }

        }

        rvadapter2 = new RvAdapterKlasse(selectedList);
        recyclerView2.setAdapter(rvadapter2);
        recyclerView2.smoothScrollToPosition(count+2);
    }}

    public static void setRound(){


        round = true;
        selectedList.add(new thenxObjects("Round " + String.valueOf(num),""));
        rvadapter2 = new RvAdapterKlasse(selectedList);
        recyclerView2.setAdapter(rvadapter2);
        recyclerView2.smoothScrollToPosition(selectedList.size());
        num++;
    }

    public void fetchData() {
        db = new DatabaseHelper(this);
        try {

            db.createDataBase();
            db.openDatabase();

        } catch (Exception e) {
            e.printStackTrace();
        }


        exerciseList = new ArrayList<>();

        SQLiteDatabase sd = db.getReadableDatabase();
        Cursor cursor = sd.query("exercises", null, null, null, null, null, null);
        int c = cursor.getColumnIndex("exercise_name");
        while (cursor.moveToNext()) {
            exerciseList.add(cursor.getString(c));
        }


        for (int i = 0; i < exerciseList.size(); i++) {
            data.add(new thenxObjects(exerciseList.get(i)));
        }


        cursor.close();
        //good=true;
        rvadapter1 = new RvAdapterKlasse(data);
        recyclerView1.setAdapter(rvadapter1);
    }

    public void fetchData(String query){
       final String shoulder = "shoulders";
        final String chest  = "chest";
        final String abs = "abs";
        final String back = "back";
        final String bizeps = "bizeps";
        final String trizeps = "trizeps";
        final String legs = "legs";
        String MY_QUERY="SELECT * FROM exercises;";

        switch (query) {
            case shoulder:
                MY_QUERY = "SELECT exercises.exercise_name\n" +
                        "FROM exercises\n" +
                        "JOIN ex_cat ON exercises.exercise_name = ex_cat.exercise_name\n" +
                        "JOIN category ON category.muscle_group = ex_cat.muscle_group\n" +
                        " WHERE category.muscle_group = 'Shoulders'";
                break;

            case chest: MY_QUERY = "SELECT exercises.exercise_name\n" +
                "FROM exercises\n" +
                "JOIN ex_cat ON exercises.exercise_name = ex_cat.exercise_name\n" +
                "JOIN category ON category.muscle_group = ex_cat.muscle_group\n" +
                " WHERE category.muscle_group = 'Chest'";
            break;

            case abs: MY_QUERY = "SELECT exercises.exercise_name\n" +
                    "FROM exercises\n" +
                    "JOIN ex_cat ON exercises.exercise_name = ex_cat.exercise_name\n" +
                    "JOIN category ON category.muscle_group = ex_cat.muscle_group\n" +
                    " WHERE category.muscle_group = 'Abs'";
                break;

            case back: MY_QUERY = "SELECT exercises.exercise_name\n" +
                    "FROM exercises\n" +
                    "JOIN ex_cat ON exercises.exercise_name = ex_cat.exercise_name\n" +
                    "JOIN category ON category.muscle_group = ex_cat.muscle_group\n" +
                    " WHERE category.muscle_group = 'Back'";
                break;

            case bizeps: MY_QUERY = "SELECT exercises.exercise_name\n" +
                    "FROM exercises\n" +
                    "JOIN ex_cat ON exercises.exercise_name = ex_cat.exercise_name\n" +
                    "JOIN category ON category.muscle_group = ex_cat.muscle_group\n" +
                    " WHERE category.muscle_group = 'Bizeps'";
                break;

            case trizeps: MY_QUERY = "SELECT exercises.exercise_name\n" +
                    "FROM exercises\n" +
                    "JOIN ex_cat ON exercises.exercise_name = ex_cat.exercise_name\n" +
                    "JOIN category ON category.muscle_group = ex_cat.muscle_group\n" +
                    " WHERE category.muscle_group = 'Trizeps'";
                break;

            case legs: MY_QUERY = "SELECT exercises.exercise_name\n" +
                    "FROM exercises\n" +
                    "JOIN ex_cat ON exercises.exercise_name = ex_cat.exercise_name\n" +
                    "JOIN category ON category.muscle_group = ex_cat.muscle_group\n" +
                    " WHERE category.muscle_group = 'Legs'";
                break;

            default:
                Toast.makeText(this,"Try shoulders, chest, abs, back, bizeps, trizeps or legs!",Toast.LENGTH_SHORT).show();
                break;


        }

            db = new DatabaseHelper(this);
            try {

                db.createDataBase();
                db.openDatabase();

            } catch (Exception e) {
                e.printStackTrace();
            }

            exerciseList = new ArrayList<>();
            final ArrayList<thenxObjects>filteredList = new ArrayList<thenxObjects>();

            SQLiteDatabase sd = db.getReadableDatabase();
            Cursor cursor = sd.rawQuery(MY_QUERY, null);
            int c = cursor.getColumnIndex("exercise_name");
            while (cursor.moveToNext()) {
                exerciseList.add(cursor.getString(c));
            }
            for (int i = 0; i < exerciseList.size(); i++) {
                filteredList.add(new thenxObjects(exerciseList.get(i)));
            }
            cursor.close();
           // good=true;
            rvadapter1 = new RvAdapterKlasse(filteredList);
            recyclerView1.setAdapter(rvadapter1);

    }





}


