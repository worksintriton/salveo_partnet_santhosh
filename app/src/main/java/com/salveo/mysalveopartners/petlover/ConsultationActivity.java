package com.salveo.mysalveopartners.petlover;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.salveo.mysalveopartners.R;
import com.salveo.mysalveopartners.adapter.MyPetsListAdapter;
import com.salveo.mysalveopartners.api.APIClient;
import com.salveo.mysalveopartners.api.RestApiInterface;
import com.salveo.mysalveopartners.interfaces.MyPetsSelectListener;
import com.salveo.mysalveopartners.requestpojo.PetListRequest;
import com.salveo.mysalveopartners.responsepojo.PetListResponse;
import com.salveo.mysalveopartners.sessionmanager.SessionManager;
import com.salveo.mysalveopartners.utils.ConnectionDetector;
import com.salveo.mysalveopartners.utils.RestUtils;
import com.wang.avi.AVLoadingIndicatorView;
import java.util.HashMap;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsultationActivity extends AppCompatActivity implements View.OnClickListener, MyPetsSelectListener {

    private static final String TAG = "ConsultationActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.avi_indicator)
    AVLoadingIndicatorView avi_indicator;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_back)
    ImageView img_back;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_pet)
    RecyclerView rv_pet;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_no_records)
    TextView txt_no_records;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_name)
    TextView txt_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_save_continue)
    LinearLayout ll_save_continue;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.ll_add_new_pet)
    LinearLayout ll_add_new_pet;

    private String userid;
    private List<PetListResponse.DataBean> petList;


    private String selectedAppointmentType = "Normal";
    private String selectedVisitType = "";
    private String petId;
    private String doctorid;
    private String fromactivity;
    private String fromto;
    private String Payment_id = "";
    private String Doctor_ava_Date = "";
    private String selectedTimeSlot = "";
    private int amount;
    private String communicationtype = "";


    private String spid,catid,from;
    private String spuserid;
    private String selectedServiceTitle;
    private String petcolor;
    private double petweight;
    private String servicetime;
    private int serviceamount;
    private String petage;
    private int distance;
    private String SP_ava_Date;



    @SuppressLint({"LogNotTimber", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);
        ButterKnife.bind(this);
        avi_indicator.setVisibility(View.GONE);
        ll_save_continue.setVisibility(View.GONE);


        SessionManager sessionManager = new SessionManager(ConsultationActivity.this);
        HashMap<String, String> user = sessionManager.getProfileDetails();
        userid = user.get(SessionManager.KEY_ID);
        String name = user.get(SessionManager.KEY_FIRST_NAME);

        txt_name.setText("Hey "+name+", ");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            doctorid = extras.getString("doctorid");
            fromactivity = extras.getString("fromactivity");
            fromto = extras.getString("fromto");
            Doctor_ava_Date = extras.getString("Doctor_ava_Date");
            selectedTimeSlot = extras.getString("selectedTimeSlot");
            amount = extras.getInt("amount");
            Log.w(TAG,"amount : "+amount);
            communicationtype = extras.getString("communicationtype");
            Log.w(TAG,"Bundle "+" doctorid : "+doctorid+" selectedTimeSlot : "+selectedTimeSlot+"communicationtype : "+communicationtype+" amount : "+amount);


            /*PetServiceAppointment_Doctor_Date_Time_Activity*/
            fromactivity = extras.getString("fromactivity");
            spid = extras.getString("spid");
            catid = extras.getString("catid");
            from = extras.getString("from");
            spuserid = extras.getString("spuserid");
            selectedServiceTitle = extras.getString("selectedServiceTitle");
            serviceamount = extras.getInt("serviceamount");
            servicetime = extras.getString("servicetime");
            SP_ava_Date = extras.getString("SP_ava_Date");
            selectedTimeSlot = extras.getString("selectedTimeSlot");
            distance = extras.getInt("distance");
            Log.w(TAG,"spid : "+spid +" catid : "+catid+" from : "+from+" serviceamount : "+serviceamount+" servicetime : "+servicetime+" SP_ava_Date : "+SP_ava_Date+" selectedTimeSlot : "+selectedTimeSlot);

            Log.w(TAG,"fromactivity : "+fromactivity+" from : "+from);

        }
        img_back.setOnClickListener(this);
        if (new ConnectionDetector(getApplicationContext()).isNetworkAvailable(getApplicationContext())) {
            petListResponseCall();
        }

        ll_save_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fromactivity != null && fromactivity.equalsIgnoreCase("PetServiceAppointment_Doctor_Date_Time_Activity")){
                    Intent intent = new Intent(getApplicationContext(),ServiceBookAppointmentActivity.class);
                    intent.putExtra("spid",spid);
                    intent.putExtra("catid",catid);
                    intent.putExtra("from",from);
                    intent.putExtra("spuserid",spuserid);
                    intent.putExtra("selectedServiceTitle",selectedServiceTitle);
                    intent.putExtra("serviceamount",serviceamount);
                    intent.putExtra("servicetime",servicetime);
                    intent.putExtra("SP_ava_Date",SP_ava_Date);
                    intent.putExtra("selectedTimeSlot",selectedTimeSlot);
                    intent.putExtra("distance",distance);
                    intent.putExtra("fromactivity",fromactivity);
                    intent.putExtra("petId", petId);
                    startActivity(intent);
                    Log.w(TAG, "petId : " + petId);
                }
                else {
                    Intent intent = new Intent(ConsultationActivity.this, ConsultationIssuesActivity.class);
                    intent.putExtra("doctorid", doctorid);
                    intent.putExtra("fromactivity", fromactivity);
                    intent.putExtra("Doctor_ava_Date", Doctor_ava_Date);
                    intent.putExtra("selectedTimeSlot", selectedTimeSlot);
                    intent.putExtra("amount", amount);
                    intent.putExtra("communicationtype", communicationtype);
                    intent.putExtra("fromto", fromto);
                    intent.putExtra("petId", petId);
                    startActivity(intent);
                    Log.w(TAG, "petId : " + petId);
                }
            }
        });

        ll_add_new_pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fromactivity != null && fromactivity.equalsIgnoreCase("PetServiceAppointment_Doctor_Date_Time_Activity")){
                    Intent intent = new Intent(getApplicationContext(),AddNewPetActivity.class);
                    intent.putExtra("spid",spid);
                    intent.putExtra("catid",catid);
                    intent.putExtra("from",from);
                    intent.putExtra("spuserid",spuserid);
                    intent.putExtra("selectedServiceTitle",selectedServiceTitle);
                    intent.putExtra("serviceamount",serviceamount);
                    intent.putExtra("servicetime",servicetime);
                    intent.putExtra("SP_ava_Date",SP_ava_Date);
                    intent.putExtra("selectedTimeSlot",selectedTimeSlot);
                    intent.putExtra("distance",distance);
                    intent.putExtra("fromactivity",fromactivity);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(ConsultationActivity.this, AddNewPetActivity.class);
                    intent.putExtra("doctorid", doctorid);
                    intent.putExtra("fromactivity", TAG);
                    intent.putExtra("Doctor_ava_Date", Doctor_ava_Date);
                    intent.putExtra("selectedTimeSlot", selectedTimeSlot);
                    intent.putExtra("amount", amount);
                    intent.putExtra("communicationtype", communicationtype);
                    intent.putExtra("fromto", TAG);
                    intent.putExtra("petId", petId);
                    startActivity(intent);
                    Log.w(TAG, "communicationtype : " + communicationtype);
                }

            }
        });




    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(fromactivity != null && fromactivity.equalsIgnoreCase("PetServiceAppointment_Doctor_Date_Time_Activity")){
            Intent intent = new Intent(getApplicationContext(),PetServiceAppointment_Doctor_Date_Time_Activity.class);
            intent.putExtra("spid",spid);
            intent.putExtra("catid",catid);
            intent.putExtra("from",from);
            intent.putExtra("spuserid",spuserid);
            intent.putExtra("selectedServiceTitle",selectedServiceTitle);
            intent.putExtra("serviceamount",serviceamount);
            intent.putExtra("servicetime",servicetime);
            intent.putExtra("SP_ava_Date",SP_ava_Date);
            intent.putExtra("selectedTimeSlot",selectedTimeSlot);
            intent.putExtra("distance",distance);
            intent.putExtra("fromactivity",TAG);
            startActivity(intent);
        }else{
            Intent intent = new Intent(getApplicationContext(),PetAppointment_Doctor_Date_Time_Activity.class);
            intent.putExtra("doctorid",doctorid);
            intent.putExtra("communicationtype",communicationtype);
            intent.putExtra("fromactivity",fromactivity);
            intent.putExtra("fromto",fromto);
            startActivity(intent);
        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;


        }
    }

    @SuppressLint("LogNotTimber")
    private void petListResponseCall() {
        avi_indicator.setVisibility(View.VISIBLE);
        avi_indicator.smoothToShow();
        RestApiInterface apiInterface = APIClient.getClient().create(RestApiInterface.class);
        Call<PetListResponse> call = apiInterface.petListResponseCall(RestUtils.getContentType(), petListRequest());
        Log.w(TAG,"PetListResponse url  :%s"+" "+ call.request().url().toString());

        call.enqueue(new Callback<PetListResponse>() {
            @SuppressLint({"SetTextI18n", "LogNotTimber"})
            @Override
            public void onResponse(@NonNull Call<PetListResponse> call, @NonNull Response<PetListResponse> response) {

                Log.w(TAG,"PetListResponse"+ "--->" + new Gson().toJson(response.body()));

                avi_indicator.smoothToHide();

                if (response.body() != null) {
                    if(response.body().getCode() == 200){

                        if(response.body().getData() != null && response.body().getData().size()>0){
                            txt_no_records.setVisibility(View.GONE);
                            rv_pet.setVisibility(View.VISIBLE);
                            petList = response.body().getData();
                            setView();

                        }
                        else{
                            txt_no_records.setVisibility(View.VISIBLE);
                            txt_no_records.setText(getResources().getString(R.string.no_new_pets));
                            rv_pet.setVisibility(View.GONE);
                        }



                    }

                }


            }

            @Override
            public void onFailure(@NonNull Call<PetListResponse> call, @NonNull Throwable t) {

                avi_indicator.smoothToHide();
                Log.w(TAG,"PetListResponse flr"+"--->" + t.getMessage());
            }
        });

    }
    @SuppressLint("LogNotTimber")
    private PetListRequest petListRequest() {
        PetListRequest petListRequest = new PetListRequest();
        petListRequest.setUser_id(userid);
        Log.w(TAG,"petListRequest"+ "--->" + new Gson().toJson(petListRequest));
        return petListRequest;
    }
    private void setView() {
        rv_pet.setLayoutManager(new GridLayoutManager(this, 2));
        rv_pet.setItemAnimator(new DefaultItemAnimator());
        MyPetsListAdapter myPetsListAdapter = new MyPetsListAdapter(getApplicationContext(), petList, this);
        rv_pet.setAdapter(myPetsListAdapter);

    }


    @SuppressLint("LogNotTimber")
    @Override
    public void myPetsSelectListener(String petid) {
        Log.w(TAG,"myPetsSelectListener : petid "+petid);
        if(petid != null){
            petId = petid;
            ll_save_continue.setVisibility(View.VISIBLE);
        }else{
            ll_save_continue.setVisibility(View.GONE);
        }

    }
}