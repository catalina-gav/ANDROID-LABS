package com.example.laborator2;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("activity_lifecycle","on create called");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        textView= findViewById(R.id.textView);
        listView= findViewById(R.id.listview);
        String[] products={"Paine","Zacusca","Naut","Spanac","Zahar","Apa","Fasole","Ton"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,products);
        Button btn=findViewById(R.id.button2);
        registerForContextMenu(btn);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {

            textView.setText(arrayAdapter.getItem(position));
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent_callq = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0740060398"));

                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(intent_callq);
                }else{
                    Toast.makeText(this,"Permission denied",Toast.LENGTH_LONG);
                }
                return;
            case 2:
                if (grantResults.length > 0 && grantResults[0] ==  PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "The app was allowed to write to your storage!", Toast.LENGTH_LONG).show();
                    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/new_folder";
                    File storageDir = new File(path);
                    if (!storageDir.exists() && !storageDir.mkdirs()) {
                        // This should never happen - log handled exception!
                    }
                    // Reload the activity with permission granted or use the features what required the permission
                } else {
                    Toast.makeText(this, "The app was not allowed to write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }

        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextual_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.option1:
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        2);
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        2);
//                String filename = "filename.jpg";
//                File dir = this.getFilesDir();
//                File file = new File(dir, filename);
//
//                try {
//                    Log.d("aici", "The file path = "+file.getAbsolutePath());
//                    file.createNewFile();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return true;
////
//                File dir = new File(this.getFilesDir(), "mydir");
//                if(!dir.exists()){
//                    dir.mkdir();
//                }
//
//                try {
//                    File gpxfile = new File(dir, "file.txt");
//                    FileWriter writer = new FileWriter(gpxfile);
//                    writer.append("sBody");
//                    writer.flush();
//                    writer.close();
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//                Log.d("menu","inside contextual menu");
//                Uri uri=Uri.parse("smsto:0740123456");
//                Intent it = new Intent(Intent.ACTION_SENDTO,uri);
//                it.putExtra("sms_body","text to send");
//                startActivity(it);
                //return true;
            case R.id.option2:
                Log.d("menu","inside contextual menu option 2");
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                //astea 2 tre sa coincida cu itnent-filters ca sa identifice activitatea mea
                intent.putExtra("Test","TestData");
                startActivity(intent);
                return true;
            case R.id.option3:
                startActivity(new Intent(this,MyPrefActivity.class));
            default:
                return super.onContextItemSelected(item);
        }

    }
    public void ShowContextualMenu(View btn){
        btn.showContextMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId())
        {
            case R.id.option1:
                Intent intent=new Intent(this,SecondActivity.class);
                TextView editText = findViewById(R.id.textView);
                String message=editText.getText().toString();
                intent.putExtra("extra",message);
                startActivity(intent);
                return true;
            case R.id.option2:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("dialog title");
                builder.setMessage("This is an alert dialog");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void ShowPopupMenu(View btn){
        Log.d("call","i am calling");
        PopupMenu popupMenu = new PopupMenu(this,btn);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.CALL_PHONE },
                        1);
                return true;
            }
        });
        popupMenu.show();
    }
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        textView.setText(savedInstanceState.getString("listItem"));
    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putString("listItem",textView.getText().toString());

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onResume() {
        Log.d("activity_lifecycle","on resume called");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.d("activity_lifecycle","on start called");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.d("activity_lifecycle","on restart called");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.d("activity_lifecycle","on pause called");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.d("activity_lifecycle","on destroy called");
        super.onDestroy();
    }
}