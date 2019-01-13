package com.example.dausque.pje_dausque_fournier.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dausque.pje_dausque_fournier.R;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class UpdateTripActivity extends AppCompatActivity {

    String Titre;
    int idTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_trip);
        getIncomingIntent();

    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("image_titre")) {
            Titre = getIntent().getStringExtra("image_titre");

            setValues(Titre);
        }
        if (getIntent().hasExtra("idTrip")) {
            idTrip = getIntent().getIntExtra("idTrip", 0);
            System.out.println(idTrip);
        }

    }

    private void setValues(String Titre) {
        TextView name = findViewById(R.id.titre_trip);
        name.setText(Titre);
    }

    public void createTextNote(View view) {
        Intent createTextNoteIntent = new Intent(this, CreateTextNoteActivity.class);
        createTextNoteIntent.putExtra("idTrip", idTrip);
        startActivity(createTextNoteIntent);
    }

    public void viewMap(View view){

        Intent viewNoteLoc = new Intent(this, MapsActivity.class);
        viewNoteLoc.putExtra("idTrip", idTrip);
        startActivity(viewNoteLoc);
    }

    public void viewRecord(View view){


        Intent viewRecordsIntent = new Intent(this,RecordActivity.class);
        viewRecordsIntent.putExtra("idTrip",idTrip);
        viewRecordsIntent.putExtra("titre",Titre);
        System.out.println(Titre);
        startActivity(viewRecordsIntent);

    }
    public void viewRecordDirect(View view){
        Intent viewRecordsIntent = new Intent(this,RecordingListActivity.class);
        viewRecordsIntent.putExtra("idTrip",idTrip);
        viewRecordsIntent.putExtra("folder",Titre);
        System.out.println(Titre);
        startActivity(viewRecordsIntent);
    }

    public void viewImageDirect(View view){
        Intent viewImageIntent = new Intent(this,ViewImagesActivity.class);
        viewImageIntent.putExtra("idTrip",idTrip);
        viewImageIntent.putExtra("folder",Titre);
        System.out.println(Titre);
        startActivity(viewImageIntent);
    }

    public void viewTrip(View view) {
        Intent viewNotesIntent = new Intent(this, TriSelectActivity.class);
        viewNotesIntent.putExtra("idTrip", idTrip);
        startActivity(viewNotesIntent);
    }

    public void editTrip(View view) {
        Intent editTripIntent = new Intent(this, EditTripActivity.class);
        editTripIntent.putExtra("idTrip", idTrip);
        startActivity(editTripIntent);
    }

    ImageView mImageView;
    String mCurrentPhotoPath;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        galleryAddPic();
        //setPic();
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new java.text.SimpleDateFormat("yyMMddHHmmssZ").format(new Date());
        String imageFileName = timeStamp;
        File root = android.os.Environment.getExternalStorageDirectory();
        File storageDir = new File(root.getAbsolutePath() + "/MyGoodMedia/Photo/"+Titre+"/");
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
        System.out.println(storageDir);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    static final int REQUEST_TAKE_PHOTO = 1;

    public void dispatchTakePictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                System.out.println(ex);
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }
    /*
    private void setPic() {
        mImageView = findViewById(R.id.bitmapHandler);
        // Get the dimensions of the View
        int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        mImageView.setImageBitmap(bitmap);
    }
    */

}
