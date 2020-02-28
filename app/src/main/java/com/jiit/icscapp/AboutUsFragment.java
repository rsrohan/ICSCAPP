package com.jiit.icscapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.jiit.icscapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AboutUsFragment extends Fragment {


    private TextView aboutPhone, aboutEmail;
    private Button callBtn, emailBtn;
    SliderView sliderView;
    TextView information;
    private SliderAdapterExample adapter;
    private String TAG=" tag";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_about_us, container, false);

        aboutEmail = root.findViewById(R.id.aboutEmail);
        aboutPhone = root.findViewById(R.id.aboutphone);
        callBtn = root.findViewById(R.id.aboutCallBtn);
        emailBtn = root.findViewById(R.id.aboutMailBtn);

        setImageSlider(root);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callbtn = new Intent(Intent.ACTION_DIAL);
                String number = getString(R.string.Authority_number);

                callbtn.setData(Uri.parse("tel:" + number));

                if(ActivityCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getActivity(),"please grant permission to call", Toast.LENGTH_SHORT).show();
                    requestPermission();
                }
                else{
                    startActivity(callbtn);
                }
            }
        });

        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "webadmin@jiit.ac.in" });
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(intent, ""));
            }
        });

        aboutPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callbtn = new Intent(Intent.ACTION_DIAL);
                String number = getString(R.string.Authority_number);

                callbtn.setData(Uri.parse("tel:" + number));

                if(ActivityCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getActivity(),"please grant permission to call", Toast.LENGTH_SHORT).show();
                    requestPermission();
                }
                else{
                    startActivity(callbtn);
                }
            }
        });

        aboutEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.jiit.ac.in/"));
                //intent.setType("plain/text");
                //intent.putExtra(Intent.ACTION_WEB_SEARCH, new String[] { "" });
                //intent.putExtra(Intent.EXTRA_SUBJECT, "");
                //intent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(intent, ""));

            }
        });

        return root;
    }


    private void requestPermission(){
        ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()),new String[] {Manifest.permission.CALL_PHONE}, 1);
    }

    private void setImageSlider(View root) {
        sliderView = root.findViewById(R.id.imageSlider);

        StorageReference storageReference = FirebaseStorage.getInstance().getReference("aboutus");

        adapter = new SliderAdapterExample(getContext());
        renewItems(storageReference);
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(5);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

    }

    public void renewItems(final StorageReference storageReference) {


        final List<SliderItem> sliderItemList = new ArrayList<>();

        try {
            for (int i = 1; i <=5; i++) {

                storageReference.child(i + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        SliderItem sliderItem = new SliderItem();
                        sliderItem.setDescription("");
                        sliderItem.setImageUrl(uri.toString());
                        sliderItemList.add(sliderItem);
                        Log.d(TAG, "onSuccess: " + uri.toString());
                        adapter.renewItems(sliderItemList);
                    }
                });
                //Log.d(TAG, "renewItems: "+storageReference.child(""+i+".jpg").getDownloadUrl().toString());
            }
        } catch (Exception e) {
            Log.d(TAG, "renewItems: " + e);
        }

    }


}