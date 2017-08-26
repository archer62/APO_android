package layout;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.biryuk.apo_app_and.Data;
import com.example.biryuk.apo_app_and.MRSD;
import com.example.biryuk.apo_app_and.School;
import com.example.biryuk.apo_app_and.Teacher;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.example.biryuk.apo_app_and.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.example.biryuk.apo_app_and.SchoolsSpinnerAdapter;

/*/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    /*private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(/*String param1, String param2*/) {
        InfoFragment fragment = new InfoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    View info;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        GetSchools a = new GetSchools();
        a.execute("http://apocrm.ru/api/school.gets/");
        //mySpinner = (Spinner)
        info = inflater.inflate(R.layout.fragment_info, container, false);
        mySpinner = (Spinner)info.findViewById(R.id.coursesSpinner);

        return info;
    }

    /*// TODO: Rename method, update argument and hook method into UI event
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
    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
    Spinner mySpinner;
    public class GetSchools extends AsyncTask<String, Void, List<School>> {

        private ProgressDialog nDialog;
        private List<MRSD> response;
        private List<School> response1;
        private Gson gson;
        public List<School> CurSchools;

        @Override
        protected List<School> doInBackground(String... params) {
            URL url;
            HttpURLConnection conn;
            BufferedReader rd;
            String line;
            String resp = "";
            Type itemsListType = new TypeToken<List<MRSD>>() {}.getType();
            Type itemsListType1 = new TypeToken<List<School>>() {}.getType();

            try {
                url = new URL(getString(R.string.server_url) + "mrsd.gets/");
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Token", Data.GetToken());
                conn.setDoOutput(true);
                conn.setDoInput(true);

                Teacher a = Data.GetTeacher();
                String urlParams = String.format("query=[%s]", Data.GetTeacher().curator_in);

                OutputStreamWriter wr= new OutputStreamWriter(conn.getOutputStream());

                wr.write(urlParams);
                wr.flush();
                wr.close();

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
/**
 * ----------------------------------------------------------
 */
            try {
                url = new URL(getString(R.string.server_url) + "school.gets/");
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Token", Data.GetToken());
                conn.setDoOutput(true);
                conn.setDoInput(true);

                ArrayList<String> s = response.get(0).schools;
                for (int i = 0; i<s.size(); i++)
                {
                    s.set(i, String.format("\"%s\"", s.get(i)));
                }

                String urlParams = String.format("query=%s", response.get(0).schools);

                OutputStreamWriter wr= new OutputStreamWriter(conn.getOutputStream());

                wr.write(urlParams);
                wr.flush();
                wr.close();
                resp = "";

                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = rd.readLine()) != null) {
                    resp += line;
                }
                rd.close();
                gson = new Gson();
                response1 = gson.fromJson(resp, itemsListType1);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                //toast(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                //toast(e.getMessage());
            }
            return response1;
        }

        @Override
        protected void onPostExecute(List<School> result) {
            CurSchools = result;
            ArrayAdapter<School> adapter = new SchoolsSpinnerAdapter(getActivity(), CurSchools);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mySpinner.setAdapter(adapter);
            /*if (typeOfInfo) {
                nDialog.cancel();
            }
            else {
                Toast.makeText(getActivity(), "Список обновлен", Toast.LENGTH_SHORT).show();
                refreshLayout.setRefreshing(false);
            }*/

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
