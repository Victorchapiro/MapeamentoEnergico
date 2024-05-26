package com.example.mapeamentoenergico;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;


public class UserManagementActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextUsername, editTextPassword, editTextConfirmPassword;
    private Toolbar toolbarHome;
    private Button buttonUpdateData;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_management_activity);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        buttonUpdateData = findViewById(R.id.buttonUpdateData);
        toolbarHome = findViewById(R.id.toolbarHome);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            loadUserData();
        }

        setSupportActionBar(toolbarHome);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbarHome.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Direcionar para a MainActivity
                Intent intent = new Intent(UserManagementActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finaliza a atividade atual se necessário
            }
        });

        buttonUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserData();
            }
        });
    }

    private void loadUserData() {
        DocumentReference userRef = firestore.collection("users").document(currentUser.getUid());
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        editTextEmail.setText(document.getString("email"));
                        editTextUsername.setText(document.getString("username"));
                    } else {
                        Toast.makeText(UserManagementActivity.this, "No such document", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UserManagementActivity.this, "get failed with " + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateUserData() {
        final String newEmail = editTextEmail.getText().toString();
        final String newUsername = editTextUsername.getText().toString();
        final String newPassword = editTextPassword.getText().toString();
        final String confirmPassword = editTextConfirmPassword.getText().toString();

        if (TextUtils.isEmpty(newEmail) || TextUtils.isEmpty(newUsername)) {
            Toast.makeText(this, "Email and Username cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!TextUtils.isEmpty(newPassword) && !newPassword.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        final DocumentReference userRef = firestore.collection("users").document(currentUser.getUid());

        firestore.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {
                transaction.update(userRef, "email", newEmail);
                transaction.update(userRef, "username", newUsername);

                return null;
            }
        }).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    currentUser.updateEmail(newEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                if (!TextUtils.isEmpty(newPassword)) {
                                    currentUser.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(UserManagementActivity.this, "User data updated", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(UserManagementActivity.this, "Failed to update password: " + task.getException(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(UserManagementActivity.this, "User data updated", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(UserManagementActivity.this, "Failed to update email: " + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(UserManagementActivity.this, "Transaction failed: " + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

