package LoginAndRegister;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dd.CircularProgressButton;
import com.example.leet.graduatedesign.R;

import MyThread.RegisterThread;

/**
 * Created by leet on 17-11-25.
 */

public class RegisterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.register,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final EditText registeruser=(EditText)getView().findViewById(R.id.registeruser);
        final EditText registerpwd=(EditText)getView().findViewById(R.id.registerpwd);
        final EditText registerpwdcon=getView().findViewById(R.id.registerpwdcon);
        final CircularProgressButton register=(CircularProgressButton)getView().findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=registeruser.getText().toString();
                String pwd=registerpwd.getText().toString();
                String pwdcon=registerpwdcon.getText().toString();
                Log.i("注册信息","用户名为"+user+"   密码为"+pwd+"   确认密码为"+pwdcon);
//                Intent intent=new Intent(getActivity(), MainActivity.class);
//                startActivity(intent);
//                getActivity().finish();
                register.setIndeterminateProgressMode(true);
                register.setProgress(50);
                new RegisterThread().start();
                register.setProgress(CircularProgressButton.SUCCESS_STATE_PROGRESS);
            }
        });
    }
}
