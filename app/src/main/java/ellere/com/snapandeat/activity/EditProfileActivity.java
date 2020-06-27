package ellere.com.snapandeat.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ellere.com.snapandeat.Constants;
import ellere.com.snapandeat.R;

/**
 * Created by DELL on 6/25/2020.
 */

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {
    Button select,upload;
    ImageView imageView;
    private Bitmap bitmap;
    Uri file_uri;
    String encoded_string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        select=(Button) findViewById(R.id.editprofile_buttonChoose);
        upload=(Button) findViewById(R.id.editprofile_buttonUpload);
        imageView=(ImageView) findViewById(R.id.editprofile_imageView);
        select.setOnClickListener(this);
        upload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == select) {
            selectImage(EditProfileActivity.this);
        }
        if (v == upload) {
            uploadImage();

        }

    }

    private  void selectImage(Context context){
        final CharSequence[] options={"Take Photo","Choose from Gallery","Cancel"};
        AlertDialog.Builder builder= new AlertDialog.Builder(context);
        builder.setTitle("Choose a picture");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if(options[item].equals("Take Photo")){
                    Intent takePicture=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    startActivityForResult(takePicture,0);

                }
                else if (options[item].equals("Choose from Gallery")) {
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
        // TODO : camera part isn't working
        if(requestCode==0 && resultCode==RESULT_OK && data!=null){
            File f = new File(Environment.getExternalStorageDirectory().toString());
//                for (File temp : f.listFiles()) {
//                    if (temp.getName().equals("temp.jpg")) {
//                        f = temp;
//                        break;
//                    }
//                }
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            bitmapOptions.inSampleSize = 8;
            Bitmap bitmap1 = BitmapFactory.decodeFile(f.getPath(), bitmapOptions);
            imageView.setImageBitmap(bitmap1);
            imageView.setVisibility(View.VISIBLE);

        }
        else if(requestCode==1 && resultCode==RESULT_OK && data!=null){
            file_uri=data.getData();
            try {
                bitmap=MediaStore.Images.Media.getBitmap(EditProfileActivity.this.getContentResolver(),file_uri);
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private void uploadImage(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String success = jsonObject.getString("flag");
                            if (success.equals("1")) {
                                Toast.makeText(EditProfileActivity.this, " successfully added", Toast.LENGTH_SHORT).show();
                                imageView.setImageResource(0);
                                imageView.setVisibility(View.GONE);


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditProfileActivity.this, "Failed"+e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditProfileActivity.this, "Failed"+error.toString(), Toast.LENGTH_SHORT).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("encoded_string",imageToString(bitmap));



                return map;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(EditProfileActivity.this);
        requestQueue.add(stringRequest);

    }
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        encoded_string = Base64.encodeToString(imgBytes, Base64.DEFAULT);
        return  encoded_string;

    }

}
