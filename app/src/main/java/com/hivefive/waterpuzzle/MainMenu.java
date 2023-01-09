package com.hivefive.waterpuzzle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainMenu extends AppCompatActivity {

    Button game_start, game_exit, ok_but, cancel_but;
    TextInputLayout jug1, jug2, target;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getSupportActionBar().hide();

        game_start = findViewById(R.id.start);
        game_exit = findViewById(R.id.exit);


        game_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(MainMenu.this);
                dialog.setTitle("Select Jug Sizes");
                dialog.setContentView(R.layout.modal_entries);
                dialog.show();

                jug1 = dialog.findViewById(R.id.jugsize1);
                jug2 = dialog.findViewById(R.id.jugsize2);
                target = dialog.findViewById(R.id.main_target);
                ok_but = dialog.findViewById(R.id.but_ok);
                cancel_but = dialog.findViewById(R.id.but_cancel);

                ok_but.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            int j1 = Integer.parseInt(jug1.getEditText().getText().toString());
                            int j2 = Integer.parseInt(jug2.getEditText().getText().toString());
                            int tar = Integer.parseInt(target.getEditText().getText().toString());
                            BreathFirst checking = new BreathFirst(j1, j2, tar);
                            Result check = checking.getPathIfPossible(j1, j2, tar);
                            if (check.res.equals("possible") && j1 < 20 && j2 < 20 && tar < 20){
                                dialog.dismiss();
                                Intent intent = new Intent(MainMenu.this, PlayAIScreen.class);
                                intent.putExtra("res_j1", j1);
                                intent.putExtra("res_j2", j2);
                                intent.putExtra("results", check);
                                startActivity(intent);
                            }
                            else{

                                dialog.dismiss();
                                Toast.makeText(MainMenu.this, "Kindly select the appropriate jug size", Toast.LENGTH_SHORT).show();

                            }


                        } catch (NumberFormatException e) {
                            Toast.makeText(MainMenu.this, "Please enter a number", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                cancel_but.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });



            }
        });



    }
    public void exit(View view) {
       // MainMenu.this.finish();

        // on below line we are exiting our activity
        //System.exit(0);
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public void Usermanual(View view) {
        Intent intent = new Intent(getApplicationContext(), UserManual.class);
        startActivity(intent);
        finish();

    }


    public void back(View view) {
        Intent intent = new Intent(getApplicationContext(), MainMenu.class);
        startActivity(intent);
        finish();

    }
}