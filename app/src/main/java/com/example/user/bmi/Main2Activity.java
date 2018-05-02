package com.example.user.bmi;

import android.support.v7.app.AppCompatActivity;
import java.text.DecimalFormat;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class Main2Activity extends Activity {
 Bundle bundle;
 Intent intent;
 /** Called when the activity is first created. */
 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.mylayout);
  intent = this.getIntent();
  bundle = intent.getExtras();
  String Sex = bundle.getString("Sex");
  double height = bundle.getDouble("height");
  double weight = bundle.getDouble("weight");
  String BMI_result = this.getBMI(height, weight);
  String BMI_advice = this.getAdvice(Sex, height, weight);
  TextView tvBMI = (TextView) findViewById(R.id.tvBMI);
  tvBMI.setText(BMI_result);
  TextView tvAdvice = (TextView) findViewById(R.id.tvAdvice);
  tvAdvice.setText(BMI_advice);
  Button b1 = (Button) findViewById(R.id.button1);
  b1.setOnClickListener(new Button.OnClickListener() {
   public void onClick(View v) {

    Main2Activity.this.setResult(RESULT_OK, intent);

    Main2Activity.this.finish();
   }
  });
 }
 // BMI值格式化
 private String format(double num) {
  DecimalFormat df = new DecimalFormat("0.00");
  return df.format(num);

 }
 // 取得BMI值
 private String getBMI(double height, double weight) {
  double bmi = weight / (height*height);
  return this.getString(R.string.report_result) + format(bmi);

 }
 // 依BMI值取得建議
 private String getAdvice(String Sex, double height, double weight) {
  double bmi_min, bmi_max;
  double bmi = weight / (height*height);
  if (Sex.equals("M")) {
   bmi_min = 20;
   bmi_max = 25;
  } else {
   bmi_min = 18;
   bmi_max = 22;
  }
  if (bmi > bmi_max) {
   return getString(R.string.advice_heavy);
  } else if (bmi < bmi_min) {
   return getString(R.string.advice_light);
  } else {
   return getString(R.string.advice_average);
  }
 }
}
