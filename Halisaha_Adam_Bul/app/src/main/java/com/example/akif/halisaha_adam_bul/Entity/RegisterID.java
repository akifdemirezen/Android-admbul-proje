package com.example.akif.halisaha_adam_bul.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by akif on 3.3.2017.
 */

public class RegisterID {



    @SerializedName("body")
    @Expose
    private RegisterID registerID;


    @SerializedName("app_id")
    @Expose
    private String app_id;
    @SerializedName("device_type")
    @Expose
    private int device_type;
    @SerializedName("identifier")
    @Expose
    private String identifier;

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("success")
    @Expose
    private String success;

    public String getid() {
        return id;
    }

    public void setRegisterid(String id) {
        this.id = id;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }


    public RegisterID(String identifier) {

        this.identifier = identifier;
    }
}
