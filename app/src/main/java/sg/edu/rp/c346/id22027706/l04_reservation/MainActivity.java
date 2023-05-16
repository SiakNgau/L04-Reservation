package sg.edu.rp.c346.id22027706.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Name;
    EditText Phone;
    EditText People;
    CheckBox Smoke;
    DatePicker Date;
    TimePicker Time;
    Button Confirm;
    Button Reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.editTextName);
        Phone = findViewById(R.id.editTextPhone);
        People = findViewById(R.id.editTextNumber);
        Smoke = findViewById(R.id.checkBoxSmoke);
        Date = findViewById(R.id.datePicker);
        Time = findViewById(R.id.timePicker);
        Confirm = findViewById(R.id.buttonConfirm);
        Reset = findViewById(R.id.buttonReset);

        Time.setCurrentHour(19);
        Time.setCurrentMinute(30);
        Date.updateDate(2023, 5, 01);

        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String displayDate = "";
                displayDate = " | " + Date.getDayOfMonth() + "/" + (Date.getMonth() + 1) + "/" + Date.getYear();

                String displayTime = "";
                displayTime = " | " + Time.getCurrentHour() + ":" + Time.getCurrentMinute();

                String output = "";
                output = (Name.getText() + " | " + Phone.getText() + " | " + People.getText());

                String area = "";
                if (Smoke.isChecked()) {
                    area = " | Smoking Area";
                } else {
                    area = " | Non-Smoking Area";
                }

                if (Name.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Invalid Name", Toast.LENGTH_LONG).show();
                } else if (Phone.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,"Invalid Phone Number",Toast.LENGTH_LONG).show();
                } else if (People.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,"Invalid Group size",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, output + displayDate + displayTime + area, Toast.LENGTH_LONG).show();
                }

            }

        });

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name.setText("");
                Phone.setText("");
                People.setText("");
                Time.setCurrentHour(19);
                Time.setCurrentMinute(30);
                Date.updateDate(2023, 5, 01);
                Smoke.setChecked(false);
            }
        });

        Time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(Time.getCurrentHour()<8 || Time.getCurrentHour()>=21){
                    Toast.makeText(MainActivity.this,"Reservation time is only between 8AM and 8:59PM inclusive",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Date.setMinDate(System.currentTimeMillis() - 1000);

    }
}