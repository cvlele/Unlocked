package src.unlocked;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class NumberActivity extends AppCompatActivity {

    private ConfigureUtils configureUtils;
    private Button savenotebutton1;
    private SharedPreferences savednotes;
    private SharedPreferences savednotes1;
    private SharedPreferences savednotes2;
    private SharedPreferences savednotes3;
    public EditText editText1;
    public EditText editText2;
    public EditText editText3;
    public EditText editText4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        configureUtils = new ConfigureUtils(this);
        configureUtils.open();
        // insertRecord();
        savenotebutton1 = (Button) findViewById(R.id.saveButton);
        editText1 = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        savednotes = getSharedPreferences("notes", MODE_PRIVATE);
        savednotes1 = getSharedPreferences("notes1", MODE_PRIVATE);
        savednotes2 = getSharedPreferences("notes2", MODE_PRIVATE);
        savednotes3 = getSharedPreferences("notes3", MODE_PRIVATE);
        editText1.setText(savednotes.getString("tag", "")); //add this line
        editText2.setText(savednotes1.getString("tag1", ""));
        editText3.setText(savednotes2.getString("tag2", "")); //add this line
        editText4.setText(savednotes3.getString("tag3", ""));
        savenotebutton1.setOnClickListener(saveButtonListener);

}

    private void makeTag(String tag) {
        String or = savednotes.getString(tag, null);
        SharedPreferences.Editor preferencesEditor = savednotes.edit();
        preferencesEditor.putString("tag", tag); //change this line to this
        preferencesEditor.apply();
    }

    private void makeTag1(String tag1) {
        String or1 = savednotes1.getString(tag1, null);
        SharedPreferences.Editor preferencesEditor = savednotes1.edit();
        preferencesEditor.putString("tag1", tag1); //change this line to this
        preferencesEditor.apply();
    }
    private void makeTag2(String tag2) {
        String or2 = savednotes2.getString(tag2, null);
        SharedPreferences.Editor preferencesEditor = savednotes2.edit();
        preferencesEditor.putString("tag2", tag2); //change this line to this
        preferencesEditor.apply();
    }

    private void makeTag3(String tag3) {
        String or3 = savednotes3.getString(tag3, null);
        SharedPreferences.Editor preferencesEditor = savednotes3.edit();
        preferencesEditor.putString("tag3", tag3); //change this line to this
        preferencesEditor.apply();
    }
    public View.OnClickListener saveButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

             //  final Configure globalVariable= (Configure) getApplicationContext();
            if (editText1.getText().length() == 10) {
                makeTag(editText1.getText().toString());
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText1.getWindowToken(), 0);
                editText1.setError(null);
            } else {
                editText1.setError("Enter a valid Number");
            }
            if (editText2.getText().length() <= 160) {
                makeTag1(editText2.getText().toString());
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText2.getWindowToken(), 0);
                editText2.setError(null);
            } else {
                editText2.setError("Message Length Exceeded");
            }

         //   if (editText3.getText().length() == 3) {
                makeTag2(editText3.getText().toString());
          //      ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText3.getWindowToken(), 0);
          //      editText3.setError(null);
        //    } else {
         //       editText3.setError("T");
         //   }

           // if (editText4.getText().length() == 3) {
                makeTag3(editText4.getText().toString());
          //      ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText4.getWindowToken(), 0);
          //      editText4.setError(null);
          //  } else {
            //    editText4.setError("Enter a valid Number");
           // }
          /*  globalVariable.setsmsNumberToSend(editText1.getText().toString());
            globalVariable.setsms(editText2.getText().toString());
            globalVariable.settime(editText4.getText().toString());
            globalVariable.setkm(editText3.getText().toString());
*/
          /* MainActivity.mMyAppsBundle.putString("key",editText1.getText().toString());
           MainActivity.mMyAppsBundle.putString("key1",editText2.getText().toString());
           MainActivity.mMyAppsBundle.putString("key2",editText3.getText().toString());
           MainActivity.mMyAppsBundle.putString("key3",editText4.getText().toString());
*/
            updateRecord();}
    };
    /*private void insertRecord(){
        //configureUtils.insertRecord(editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString(),editText4.getText().toString());
        configureUtils.insertRecord("00000","Default Message","1","1");

    }*/
    private void updateRecord(){
        configureUtils.updateRecord(editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString(),editText4.getText().toString());
    }

}

