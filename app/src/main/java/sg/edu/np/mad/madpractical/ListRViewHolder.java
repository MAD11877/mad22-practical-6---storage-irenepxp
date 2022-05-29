package sg.edu.np.mad.madpractical;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ListRViewHolder extends RecyclerView.ViewHolder{


    TextView uname;
    TextView desctxt;
    ImageView profilepic;

    public ListRViewHolder(View item)
    {
        super(item);
        uname = item.findViewById(R.id.username);
        desctxt = item.findViewById(R.id.desc);
        profilepic = item.findViewById(R.id.profile);

    }
}


