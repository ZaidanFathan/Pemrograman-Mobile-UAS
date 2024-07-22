package id.pemrogramanmobile.tugasutspemrogramanmobile;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class CreateData extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText title, desc;
    private ImageView imageView;
    private Button saveArticles, chooseImage;
    private Uri imageUri;

    private FirebaseFirestore dbArticles;
    private FirebaseStorage storage;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_data);

        dbArticles = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);
        saveArticles = findViewById(R.id.btnAdd);

        progressDialog = new ProgressDialog(CreateData.this);
        progressDialog.setTitle("Loading...");

        saveArticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String articleTitle = title.getText().toString().trim();
                String articleDesc = desc.getText().toString().trim();

                if (articleTitle.isEmpty() || articleDesc.isEmpty()){
                    Toast.makeText(CreateData.this, "Title and Description cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String, Object> articles = new HashMap<>();
                articles.put("title", articleTitle);
                articles.put("desc", articleDesc);
              dbArticles.collection("articles")
                      .add(articles)
                      .addOnSuccessListener(documentReference -> {
                          Toast.makeText(CreateData.this, "Article added successfully", Toast.LENGTH_SHORT).show();
                          Intent intent = new Intent(CreateData.this, news_portal.class);

                      })
                      .addOnFailureListener(e -> {
                          Toast.makeText(CreateData.this, "Error adding article" + e.getMessage(), Toast.LENGTH_SHORT).show();
                      });



            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}