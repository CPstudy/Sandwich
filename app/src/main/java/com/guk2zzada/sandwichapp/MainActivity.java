package com.guk2zzada.sandwichapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.guk2zzada.sandwich.Sandwich;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnShort:
                Sandwich.makeText(getApplicationContext(), "샌드위치가 먹고싶어요!", Sandwich.LENGTH_SHORT).show();
                break;

            case R.id.btnLong:
                Sandwich.makeText(getApplicationContext(), "오늘 점심은 샌드위치!", Sandwich.LENGTH_LONG).show();
                break;

            case R.id.btnDone:
                Sandwich sandwichDone = new Sandwich(getApplicationContext());
                sandwichDone.setType(Sandwich.SANDWICH_DONE);
                sandwichDone.setDuration(Sandwich.LENGTH_SHORT);
                sandwichDone.setText("샌드위치 주문이 완료되었습니다.");
                sandwichDone.show();
                break;

            case R.id.btnWarning:
                Sandwich sandwichWarning = new Sandwich(getApplicationContext());
                sandwichWarning.setType(Sandwich.SANDWICH_WARNING);
                sandwichWarning.setDuration(Sandwich.LENGTH_SHORT);
                sandwichWarning.setText("샌드위치에 내용물이 없습니다.");
                sandwichWarning.show();
                break;

            case R.id.btnError:
                Sandwich sandwichError = new Sandwich(getApplicationContext());
                sandwichError.setType(Sandwich.SANDWICH_ERROR);
                sandwichError.setDuration(Sandwich.LENGTH_SHORT);
                sandwichError.setText("샌드위치가 상했습니다.");
                sandwichError.show();
                break;

            case R.id.btnCustom:
                Sandwich sandwichCustom = new Sandwich(getApplicationContext());
                sandwichCustom.setType(Sandwich.SANDWICH_DONE);
                sandwichCustom.setDuration(Sandwich.LENGTH_SHORT);
                sandwichCustom.setText("내가 샌드위치를 만들었습니다.");
                sandwichCustom.setIconBoxColor(Color.parseColor("#8E24AA"));
                sandwichCustom.setIcon(R.drawable.ic_subway_vector);
                sandwichCustom.show();
                break;
        }
    }
}
