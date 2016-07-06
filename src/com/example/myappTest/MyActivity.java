package com.example.myappTest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyActivity extends Activity
{
    private ListView listView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listView= (ListView) findViewById(R.id.list_view);


        Items item1=new Items();
        item1.setId(1001);
        item1.setName("Albert Einstein");
        item1.setImg(R.drawable.albert_einstein);

        Items item2=new Items();
        item2.setId(1002);
        item2.setName("Isaac Newton");
        item2.setImg(R.drawable.isaac_newton);


        Items item3=new Items();
        item3.setId(1003);
        item3.setName("Galileo Galilei");
        item3.setImg(R.drawable.galileo_galilei);


        Items item4=new Items();
        item4.setId(1004);
        item4.setName("Charles Darwin");
        item4.setImg(R.drawable.charles_darwin);



        Items item5=new Items();
        item5.setId(1005);
        item5.setName("Marie Curie");
        item5.setImg(R.drawable.marie_curie);


        Items item6=new Items();
        item6.setId(1006);
        item6.setName("Michael Faraday");
        item6.setImg(R.drawable.michael_faraday);


        Items item7=new Items();
        item7.setId(1007);
        item7.setName("Alexander Graham Bell");
        item7.setImg(R.drawable.alexander_graham_bell);

        Items item8=new Items();
        item8.setId(1008);
        item8.setName("James Clerk Maxwell");
        item8.setImg(R.drawable.james_clerk_maxwell);


        Items item9=new Items();
        item9.setId(1009);
        item9.setName("Aristotle");
        item9.setImg(R.drawable.aristotle);


        Items item10=new Items();
        item10.setId(1010);
        item10.setName("Archimedes");
        item10.setImg(R.drawable.archimedes);


        ArrayList<Items> list=new ArrayList<>();
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);
        list.add(item6);
        list.add(item7);
        list.add(item8);
        list.add(item9);
        list.add(item10);

        ListViewCustomAdapter adapter=new ListViewCustomAdapter(this,list);
        listView.setAdapter(adapter);
    }
}
