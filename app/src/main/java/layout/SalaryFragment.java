package layout;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.biryuk.apo_app_and.Data;
import com.example.biryuk.apo_app_and.Payday;
import com.example.biryuk.apo_app_and.R;
import com.example.biryuk.apo_app_and.SchoolsSpinnerAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/*
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SalaryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SalaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SalaryFragment extends Fragment {

    public TextView salaryText;
    public TextView salaryUntillText;
    View Salary;

    public static SalaryFragment newInstance(/*String param1, String param2*/) {
        SalaryFragment fragment = new SalaryFragment();
        //Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Salary = inflater.inflate(R.layout.fragment_salary, container, false);
        salaryText = (TextView) Salary.findViewById(R.id.salary_text_view);
        salaryUntillText = Salary.findViewById(R.id.untill_salary_text_view);

        GetSalary a = new GetSalary();
        a.execute();

        return Salary;
    }

    /*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/

    public class GetSalary extends AsyncTask<String, Void, Payday> {

        private ProgressDialog nDialog;
        private Payday response;
        private Gson gson;

        @Override
        protected Payday doInBackground(String... params) {
            URL url;
            HttpURLConnection conn;
            BufferedReader rd;
            String line;
            String resp = "";
            Type itemsListType = new TypeToken<Payday>() {}.getType();
            try {
                url = new URL(getString(R.string.server_url) + "payday.get/");
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Token", Data.GetToken());

                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = rd.readLine()) != null) {
                    resp += line;
                }
                rd.close();

                gson = new Gson();
                response = gson.fromJson(resp, itemsListType);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                //toast(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                //toast(e.getMessage());
            }
            return response;
        }

        @Override
        protected void onPostExecute(Payday result) {
            salaryText.setText(result.GetSalary());
            salaryUntillText.setText("Еще чуть-чуть...");

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*if (typeOfInfo) {
                nDialog = new ProgressDialog(getActivity());
                nDialog.setMessage("Пожалуйста, подождите..");
                nDialog.setTitle("Загрузка данных");
                nDialog.setIndeterminate(false);
                nDialog.setCancelable(true);
                nDialog.show();
            }*/
        }

    }
}
