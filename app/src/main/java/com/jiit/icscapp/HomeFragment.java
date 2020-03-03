package com.jiit.icscapp;

import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    private static final String TAG = "tag";
    SliderView sliderView;
    TextView information;
    private SliderAdapterExample adapter;
    DatabaseReference databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        TextView t = root.findViewById(R.id.slidingmessagetop);
        t.setSelected(true);
        setImageSlider(root);

        information = root.findViewById(R.id.information);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference("ICSC2020");
        databaseReference.keepSynced(true);


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<String> stringArrayList = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String s = dataSnapshot1.getValue(String.class);
                    stringArrayList.add(s);

                }

                information.setText(stringArrayList.get(0));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getContext(), "Please check internet connectivity.", Toast.LENGTH_SHORT).show();
            }
        });


        return root;
    }

    private void setImageSlider(View root) {
        sliderView = root.findViewById(R.id.imageSlider);

        StorageReference storageReference = FirebaseStorage.getInstance().getReference("icsc2020");

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
            for (int i = 1; i <= 20; i++) {

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


    private void setHTMLText(TextView info) {

        String distInfo = info.getText().toString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            info.setText(Html.fromHtml(distInfo, Html.FROM_HTML_MODE_LEGACY));
        } else {
            info.setText(Html.fromHtml(distInfo));
        }
        info.setMovementMethod(LinkMovementMethod.getInstance());

    }
}