package com.example.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button ;
    private Button addButton;
    private Button updateButton;
    private Button deleteButton;
    private Button listButton;
    private MyDataBaseHelper myDataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        sqlLite 数据库创建
//        myDataBaseHelper = new MyDataBaseHelper(this,"BookStore.db",null,1);
//        button = findViewById(R.id.create_button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myDataBaseHelper.getWritableDatabase();
//            }
//        });

        //LitePal数据库创建方法
         button = findViewById(R.id.create_button);
        button.setOnClickListener(this);

        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(this);
        updateButton = findViewById(R.id.update_button);
        updateButton.setOnClickListener(this);
        deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(this);
        listButton = findViewById(R.id.list_button);
        listButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.create_button:
                LitePal.getDatabase();
                break;
            case R.id.add_button:
                Book book = new Book();
                book.setName("人间失格");
                book.setAuthor("泰·洛夫斯基");
                book.setPages(501);
                book.setPrice(10.15);
                book.save();
                break;
            case R.id.update_button:
                Book book1 = new Book();
                book1.setPrice(10.00);
                book1.setPages(100);
                book1.setAuthor("zc");
                book1.setName("???");
//                book1.update(2);
                book1.updateAll("id = ?","4");
                break;
            case R.id.delete_button:
                LitePal.delete(Book.class,4);
//                LitePal.deleteAll(Book.class,"pages > ?","200");
                break;
            case R.id.list_button:
//                Book book2 = LitePal.find(Book.class,2);

//                List<Book> list = LitePal.findAll(Book.class);
//                for (Book book2:list){
//                    Log.d("book:",book2.toString());
//                }
                break;
            default:
                break;
        }
    }
}
