package sg.edu.np.mad.madpractical;

import static android.view.View.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<User> data = ListRAdapter.data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DbHandler db = new DbHandler(this);

        Intent i = getIntent();
        String Name = i.getStringExtra("name");
        String Desc = i.getStringExtra("description");
        Integer pos = i.getIntExtra("position",0);

        TextView uname = findViewById(R.id.textView2);
        TextView udesc = findViewById(R.id.textView);
        Button fllwButton = findViewById(R.id.fllwbutton);
        Button msgButton = findViewById(R.id.msgbutton);

        uname.setText(Name);
        udesc.setText(Desc);
        if(data.get(pos).followed){
            fllwButton.setText("Unfollow");
        }

        fllwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.get(pos).followed){
                    data.get(pos).followed = false;
                    db.updateUser(data.get(pos));
                    fllwButton.setText("Follow");
                    Toast.makeText(MainActivity.this,"Unfollowed",Toast.LENGTH_SHORT).show();
                }
                else{
                    data.get(pos).followed = true;
                    db.updateUser(data.get(pos));
                    fllwButton.setText("Unfollow");
                    Toast.makeText(MainActivity.this,"Followed",Toast.LENGTH_SHORT).show();
                }
            }
        });

        msgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,MessageGroup.class);
                startActivity(i);
            }
        });
    }
}