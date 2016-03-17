package com.example.anshul.calculator_xlabs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatemen

        return super.onOptionsItemSelected(item);
    }

    //clear button click response
    int sign=0;
    public void clearEnter(View v) {
        Button button = (Button) v;
        TextView tview = (TextView) findViewById(R.id.display_calc);
        String tmp = tview.getText().toString();
        if(tmp != null && !tmp.isEmpty()) {
            if (button.getId() == R.id.bce) {
                tview.setText(cleardisplay(tmp, 1));
            } else if (button.getId() == R.id.bc) {
                tview.setText(cleardisplay(tmp, 2));
            }
        }
    }

    //Sign button click response
    public void signEnter(View v) {
        Button button = (Button) v;
        TextView tview = (TextView) findViewById(R.id.display_calc);
        String tmp = tview.getText().toString();
        if(tmp != null && !tmp.isEmpty()) {

            if (button.getId() == R.id.bplus) {
                sign++;
                display(11, '+');
            } else if (button.getId() == R.id.bmin) {
                sign++;
                display(12, '-');
            } else if (button.getId() == R.id.bmul) {
                sign++;
                display(13, '*');
            } else if (button.getId() == R.id.bdiv) {
                sign++;
                display(14, '/');
            } else if (button.getId() == R.id.bequal) {
                display(15, '=');
            }
        }
        else return;
    }
    //number Button click Responses
    public void numEnter(View v) {
        Button button = (Button) v;

        if (button.getId() == R.id.b1) {
            display(1,'1');}
        else if (button.getId() == R.id.b2)  {
            display(2,'2');
        }
        else if (button.getId() == R.id.b3)  {
            display(3,'3');
        }
        else if (button.getId() == R.id.b4)  {
            display(4,'4');
        }
        else if (button.getId() == R.id.b5)  {
            display(5,'5');
        }
        else if (button.getId() == R.id.b6)  {
            display(6,'6');
        }

        else if (button.getId() == R.id.b7) {
            display(7,'7');
        }

        else if (button.getId() == R.id.b8)  {
            display(8,'8');
        }
        else if (button.getId() == R.id.b9)  {
            display(9,'9');
        }
        else if (button.getId() == R.id.b0)  {
            display(0,'0');
        }

    }


    public void display(int num,char cursign) {
        //extracting the string
        TextView tview = (TextView) findViewById(R.id.display_calc);
        String tmp = tview.getText().toString();

        //if equal to is pressed without any expression in stack
        if ((num==15)&& (sign<1)) {
            return;
        }
        // if more than 1 sign element in stack
        else if(sign>2){
            sign--;
            return;
        }

        //If continuosly 2 sign symbols are pushed
        else if(num>10  && (int)tmp.charAt(tmp.length()-1)<48){
            if(num==15){
                return;
            }
            tmp = tmp.substring(0,tmp.length() - 1);
            tview.setText(tmp + cursign);
            sign--;
            return;
        }

        else
        {

            //Calculation of expression if equal to or any sign is pushed in stack
            if (sign == 2 ||(sign==1 && num==15)) {
                tmp = calculate(tmp, num);
            }

            //Displaying the result
            if (num == 15) {
                tview.setText(tmp);
                return;
            } else {
                tview.setText(tmp + cursign);
            }
        }
    }

    //Calculating the expression
    public String calculate(String tmp,int num ){
        int len=0,i=0,num1=0,num2=0,answer=0;

        String stringNum1="";
        String stringNum2="";

        len= tmp.length();
        //Extracting the operators and operands
        for( i=len-1; (int)tmp.charAt(i)>=48 && i>=0 ;i--);
        int signpos= i;
        for(i=0;i<signpos;i++) {
            stringNum1 = stringNum1 + tmp.charAt(i);
        }
        char operator=tmp.charAt(signpos);
        for(i=i+1;i<len;i++){
            stringNum2= stringNum2 + tmp.charAt(i);
        }
        num1 =Integer.parseInt(stringNum1);
        num2 = Integer.parseInt(stringNum2);

        //Calculating final answer
        if(operator=='+'){
            answer=num1+num2;
        }
        else if(operator=='-'){
            answer=num1-num2;
        }
        else if(operator=='*'){
            answer=num1*num2;
        }
        else if(operator=='/'){
            answer=num1/num2;
        }
        sign--;

        String stranswer = String.valueOf(answer);
        return stranswer;
    }

    //Execute when display character needs to be cleared
    public String cleardisplay(String tmp,int num){
            if (num == 1) {
                tmp = "";
                sign=0;
            } else if (num == 2) {
                char lastchar = tmp.charAt(tmp.length() - 1);
                tmp = tmp.substring(0, tmp.length() - 1);
                if ((int) lastchar < 48) {
                    sign--;
                }
            }
            return (tmp);
        }

}
