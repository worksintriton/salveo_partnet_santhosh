package com.salveo.mysalveopartners.requestpojo;

public class ShippingAddrMarkAsLastUsedResponse {


    /**
     * Status : Success
     * Message : Shipping address Updated
     * Data : {"_id":"6058f4ebe748565ddb1fc515","user_id":"604081d12c2b43125f8cb840","user_first_name":"San","user_last_name":"Sam","user_flat_no":"225/1,","user_stree":"Duraiswamy Nagar Main Road","user_landmark":"Mariyamman kovil Near By","user_picocode":"636009","user_state":"TamilNadu","user_mobile":"9487735306","user_alter_mobile":"9159207294","user_address_stauts":"Last Used","user_address_type":"Home","user_display_date":"23-03-2021","updatedAt":"2021-03-22T19:50:32.162Z","createdAt":"2021-03-22T19:50:03.803Z","__v":0}
     * Code : 200
     */

    private String Status;
    private String Message;
    /**
     * _id : 6058f4ebe748565ddb1fc515
     * user_id : 604081d12c2b43125f8cb840
     * user_first_name : San
     * user_last_name : Sam
     * user_flat_no : 225/1,
     * user_stree : Duraiswamy Nagar Main Road
     * user_landmark : Mariyamman kovil Near By
     * user_picocode : 636009
     * user_state : TamilNadu
     * user_mobile : 9487735306
     * user_alter_mobile : 9159207294
     * user_address_stauts : Last Used
     * user_address_type : Home
     * user_display_date : 23-03-2021
     * updatedAt : 2021-03-22T19:50:32.162Z
     * createdAt : 2021-03-22T19:50:03.803Z
     * __v : 0
     */

    private DataBean Data;
    private int Code;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public static class DataBean {
        private String _id;
        private String user_id;
        private String user_first_name;
        private String user_last_name;
        private String user_flat_no;
        private String user_stree;
        private String user_landmark;
        private String user_picocode;
        private String user_state;
        private String user_mobile;
        private String user_alter_mobile;
        private String user_address_stauts;
        private String user_address_type;
        private String user_display_date;
        private String updatedAt;
        private String createdAt;
        private int __v;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_first_name() {
            return user_first_name;
        }

        public void setUser_first_name(String user_first_name) {
            this.user_first_name = user_first_name;
        }

        public String getUser_last_name() {
            return user_last_name;
        }

        public void setUser_last_name(String user_last_name) {
            this.user_last_name = user_last_name;
        }

        public String getUser_flat_no() {
            return user_flat_no;
        }

        public void setUser_flat_no(String user_flat_no) {
            this.user_flat_no = user_flat_no;
        }

        public String getUser_stree() {
            return user_stree;
        }

        public void setUser_stree(String user_stree) {
            this.user_stree = user_stree;
        }

        public String getUser_landmark() {
            return user_landmark;
        }

        public void setUser_landmark(String user_landmark) {
            this.user_landmark = user_landmark;
        }

        public String getUser_picocode() {
            return user_picocode;
        }

        public void setUser_picocode(String user_picocode) {
            this.user_picocode = user_picocode;
        }

        public String getUser_state() {
            return user_state;
        }

        public void setUser_state(String user_state) {
            this.user_state = user_state;
        }

        public String getUser_mobile() {
            return user_mobile;
        }

        public void setUser_mobile(String user_mobile) {
            this.user_mobile = user_mobile;
        }

        public String getUser_alter_mobile() {
            return user_alter_mobile;
        }

        public void setUser_alter_mobile(String user_alter_mobile) {
            this.user_alter_mobile = user_alter_mobile;
        }

        public String getUser_address_stauts() {
            return user_address_stauts;
        }

        public void setUser_address_stauts(String user_address_stauts) {
            this.user_address_stauts = user_address_stauts;
        }

        public String getUser_address_type() {
            return user_address_type;
        }

        public void setUser_address_type(String user_address_type) {
            this.user_address_type = user_address_type;
        }

        public String getUser_display_date() {
            return user_display_date;
        }

        public void setUser_display_date(String user_display_date) {
            this.user_display_date = user_display_date;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }
    }
}
