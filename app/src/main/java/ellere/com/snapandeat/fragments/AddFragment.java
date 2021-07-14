package ellere.com.snapandeat.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ellere.com.snapandeat.Constants;
import ellere.com.snapandeat.R;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static com.android.volley.VolleyLog.TAG;

/**
 * Created by DELL on 4/24/2020.
 */

public class AddFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private String user_name;
    private Button buttonChoose;
    private Button buttonUpload;
    private ImageView imageView;
    private String sizeOfPizza;
    private Spinner pizzaSize;
    private Bitmap bitmap;
    Uri file_uri;
    SharedPreferences sharedPreferences;
    String username;
    private static final int STORAGE_PERMISSION_CODE = 123;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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


        View view = inflater.inflate(R.layout.fragment_add, container, false);
        requestStoragePermission();
        buttonChoose = view.findViewById(R.id.buttonChoose);
        buttonUpload = view.findViewById(R.id.buttonUpload);
        buttonUpload.setEnabled(false);
        imageView = view.findViewById(R.id.imageView);
        pizzaSize = view.findViewById(R.id.dropDownSize);
        pizzaSize.setOnItemSelectedListener(this);
        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
        sharedPreferences = this.getActivity().getSharedPreferences("user_details", MODE_PRIVATE);
        username = sharedPreferences.getString("username", "default value");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.pizza_sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pizzaSize.setAdapter(adapter);


        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == buttonChoose) {
            selectImage(getActivity());
        }
        if (v == buttonUpload) {
            uploadImage();

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

        Log.d("position", String.valueOf(position));
        switch (position) {
            case 0:
                sizeOfPizza = String.valueOf(adapterView.getItemAtPosition(position));
                Log.d(TAG, "size: " + sizeOfPizza);
                break;
            case 1:
                sizeOfPizza = String.valueOf(adapterView.getItemAtPosition(position));
                Log.d(TAG, "size: " + sizeOfPizza);
                break;
            case 2:
                sizeOfPizza = String.valueOf(adapterView.getItemAtPosition(position));
                Log.d(TAG, "size: " + sizeOfPizza);
                break;

        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
// TODO Auto-generated method stub
    }

    private void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose a picture");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);//one can be replaced with any action code

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getExtras();
            bitmap = (Bitmap) bundle.get("data");
            imageView.setImageBitmap(bitmap);

            imageView.setVisibility(View.VISIBLE);
            buttonUpload.setEnabled(true);
        } else if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            file_uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), file_uri);
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);
                buttonUpload.setEnabled(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void uploadImage() {
        buttonUpload.setEnabled(false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("flag");
                            if (success.equals("1")) {
                                Toast.makeText(getActivity(), " successfully added", Toast.LENGTH_SHORT).show();
                                imageView.setImageResource(0);
                                imageView.setVisibility(View.GONE);
                                buttonUpload.setEnabled(true);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Failed " + e.toString(), Toast.LENGTH_SHORT).show();
                            buttonUpload.setEnabled(true);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Upload Error: " + error.getMessage());
                Toast.makeText(getActivity(), "Failed " + error.toString(), Toast.LENGTH_SHORT).show();
                buttonUpload.setEnabled(true);

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("encoded_string", imageToString(bitmap));
                map.put("size", sizeOfPizza);
                map.put("username", username);
                return map;
            }
        };
        int socketTimeout = 5000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, 1, 1);
        stringRequest.setRetryPolicy(policy);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);

    }


    //ask permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously code will come to this block
            Toast.makeText(getActivity(), "You won't be able to use this app without granting permission", Toast.LENGTH_LONG).show();
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        // ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, STORAGE_PERMISSION_CODE);
    }

}