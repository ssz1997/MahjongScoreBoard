package com.example.mahjong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class game extends AppCompatActivity {

    private Button onlyButton;
    private Button saveGame;
    private EditText person1;
    private EditText person2;
    private EditText person3;
    private EditText person4;
    private TextView setNamesIns;
    private ArrayList<EditText> players = new ArrayList<>();
    private Button person1Win;
    private Button person2Win;
    private Button person3Win;
    private Button person4Win;
    private TextView loser;
    private Spinner loserDropdown;
    private TextView pointsText;
    private EditText points;
    private int winnerIndex = -1;
    private ArrayList<Integer> scores = new ArrayList<>();
    private ArrayList<Integer> numOfWins = new ArrayList<>();
    private ArrayList<Integer> dianPao = new ArrayList<>();
    private ArrayList<Integer> ziMo = new ArrayList<>();
    private ArrayList<TextView> scores_show = new ArrayList<>();
    private ArrayList<TextView> numOfWins_show = new ArrayList<>();
    private ArrayList<TextView> dianPao_show = new ArrayList<>();
    private ArrayList<TextView> ziMo_show = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        loser = findViewById(R.id.loser);
        loserDropdown = findViewById(R.id.loserDropdown);
        pointsText = findViewById(R.id.pointsText);
        points = findViewById(R.id.points);
        setNamesIns = findViewById(R.id.setNamesIns);
        onlyButton = findViewById(R.id.onlyButton);
        saveGame = findViewById(R.id.saveGame);
        person1 = findViewById(R.id.person1);
        person2 = findViewById(R.id.person2);
        person3 = findViewById(R.id.person3);
        person4 = findViewById(R.id.person4);
        players.add(person1);
        players.add(person2);
        players.add(person3);
        players.add(person4);
        person1Win = findViewById(R.id.person1Win);
        person2Win = findViewById(R.id.person2Win);
        person3Win = findViewById(R.id.person3Win);
        person4Win = findViewById(R.id.person4Win);
        onlyButton.setOnClickListener(v -> saveNames());
        person1Win.setOnClickListener(v -> win(0));
        person2Win.setOnClickListener(v -> win(1));
        person3Win.setOnClickListener(v -> win(2));
        person4Win.setOnClickListener(v -> win(3));
        saveGame.setOnClickListener(v -> save());
        for (int i = 0; i < 4; i++) {
            scores.add(0);
            numOfWins.add(0);
            dianPao.add(0);
            ziMo.add(0);
        }
        scores_show.add(findViewById(R.id.p1score));
        scores_show.add(findViewById(R.id.p2score));
        scores_show.add(findViewById(R.id.p3score));
        scores_show.add(findViewById(R.id.p4score));
        numOfWins_show.add(findViewById(R.id.p1win));
        numOfWins_show.add(findViewById(R.id.p2win));
        numOfWins_show.add(findViewById(R.id.p3win));
        numOfWins_show.add(findViewById(R.id.p4win));
        dianPao_show.add(findViewById(R.id.p1dp));
        dianPao_show.add(findViewById(R.id.p2dp));
        dianPao_show.add(findViewById(R.id.p3dp));
        dianPao_show.add(findViewById(R.id.p4dp));
        ziMo_show.add(findViewById(R.id.p1zm));
        ziMo_show.add(findViewById(R.id.p2zm));
        ziMo_show.add(findViewById(R.id.p3zm));
        ziMo_show.add(findViewById(R.id.p4zm));
        for (int i = 0; i < 4; i++) {
            scores_show.get(i).setText(scores.get(i).toString());
            numOfWins_show.get(i).setText(numOfWins.get(i).toString());
            dianPao_show.get(i).setText(dianPao.get(i).toString());
            ziMo_show.get(i).setText(ziMo.get(i).toString());
        }
    }

    protected void saveNames() {
        for (int i = 0; i < 4; i++) {
            players.get(i).setFocusable(false);
            players.get(i).setEnabled(false);
            players.get(i).setCursorVisible(false);
            players.get(i).setBackgroundColor(Color.TRANSPARENT);
        }
        onlyButton.setText("End game");
        List<String> playersDropdown = new ArrayList<String>();
        playersDropdown.add("All");
        playersDropdown.add(person1.getText().toString());
        playersDropdown.add(person2.getText().toString());
        playersDropdown.add(person3.getText().toString());
        playersDropdown.add(person4.getText().toString());
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, playersDropdown);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loserDropdown.setAdapter(dataAdapter);
        onlyButton.setVisibility(View.INVISIBLE);
        onlyButton.setClickable(false);
        person1Win.setEnabled(true);
        person2Win.setEnabled(true);
        person3Win.setEnabled(true);
        person4Win.setEnabled(true);
        person1Win.setVisibility(View.VISIBLE);
        person2Win.setVisibility(View.VISIBLE);
        person3Win.setVisibility(View.VISIBLE);
        person4Win.setVisibility(View.VISIBLE);
        setNamesIns.setVisibility(View.INVISIBLE);
    }

    protected void win(int i) {
        loser.setVisibility(View.VISIBLE);
        loserDropdown.setVisibility(View.VISIBLE);
        onlyButton.setVisibility(View.INVISIBLE);
        onlyButton.setClickable(false);
        saveGame.setVisibility(View.VISIBLE);
        saveGame.setClickable(true);
        pointsText.setVisibility(View.VISIBLE);
        points.setEnabled(true);
        ColorStateList colorStateList = ColorStateList.valueOf(Color.WHITE);
        points.setBackgroundTintList(colorStateList);
        person1Win.setClickable(false);
        person2Win.setClickable(false);
        person3Win.setClickable(false);
        person4Win.setClickable(false);
        winnerIndex = i;
    }

    protected void save() {
        if (loserDropdown.getSelectedItemPosition() - 1 == winnerIndex) {
            return;
        }
        numOfWins.set(winnerIndex, numOfWins.get(winnerIndex) + 1);
        int losePoints = Integer.parseInt(points.getText().toString());
        if (loserDropdown.getSelectedItemPosition() == 0) {
            scores.set(winnerIndex, scores.get(winnerIndex) + losePoints * 3);
            ziMo.set(winnerIndex, ziMo.get(winnerIndex) + 1);
            for (int i = 0; i < 4; i++) {
                if (i != winnerIndex) {
                    scores.set(i, scores.get(i) - losePoints);
                }
            }
        }
        else {
            scores.set(winnerIndex, scores.get(winnerIndex) + losePoints);
            int loserIndex = loserDropdown.getSelectedItemPosition() - 1;
            scores.set(loserIndex, scores.get(loserIndex) - losePoints);
            dianPao.set(loserIndex, dianPao.get(loserIndex) + 1);
        }
        loser.setVisibility(View.INVISIBLE);
        loserDropdown.setVisibility(View.INVISIBLE);
        onlyButton.setVisibility(View.VISIBLE);
        onlyButton.setClickable(true);
        saveGame.setVisibility(View.INVISIBLE);
        saveGame.setClickable(false);
        pointsText.setVisibility(View.INVISIBLE);
        points.setEnabled(false);
        ColorStateList colorStateList = ColorStateList.valueOf(Color.TRANSPARENT);
        points.setBackgroundTintList(colorStateList);
        winnerIndex = -1;
        points.setText("");
        person1Win.setClickable(true);
        person2Win.setClickable(true);
        person3Win.setClickable(true);
        person4Win.setClickable(true);

        for (int i = 0; i < 4; i++) {
            scores_show.get(i).setText(scores.get(i).toString());
            numOfWins_show.get(i).setText(numOfWins.get(i).toString());
            dianPao_show.get(i).setText(dianPao.get(i).toString());
            ziMo_show.get(i).setText(ziMo.get(i).toString());
        }

    }

}