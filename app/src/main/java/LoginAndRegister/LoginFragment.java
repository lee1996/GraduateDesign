package LoginAndRegister;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dd.CircularProgressButton;
import com.example.leet.graduatedesign.MainActivity;
import com.example.leet.graduatedesign.R;

import MyThread.LoginThread;

import static java.lang.Thread.sleep;

/**
 * Created by leet on 17-11-25.
 */

public class LoginFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.login,container,false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final EditText loginuser=(EditText)getView().findViewById(R.id.loginuser);
        final EditText loginpwd=(EditText)getView().findViewById(R.id.loginpwd);
        final CircularProgressButton login=(CircularProgressButton)getView().findViewById(R.id.login);
        login.setIndeterminateProgressMode(true);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=loginuser.getText().toString();
                String pwd=loginpwd.getText().toString();
                Log.i("登录信息","用户名为"+user+"   密码为"+pwd);
//                getActivity().finish();
//                login.setProgress(50);
                //new LoginThread().start();
                LoginThread testThread=new LoginThread();
                login.setProgress(50);
//                if(testThread.test()) {
//                    login.setProgress(100);
//                }
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

}
