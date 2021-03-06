package com.kbear.noknok.service.response;

import com.google.gson.Gson;
import com.kbear.noknok.dtos.Account;
import com.kbear.noknok.dtos.CustomError;

import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by allen on 2/13/15.
 */
public final class ResponseHandler {

    public interface IBooleanResponseParser {
        public boolean parse(int statusCode, Header[] headers, JSONObject response);
    }

    public interface IStringResponseParser {
        public String parse(int statusCode, Header[] headers, JSONObject response);
    }

    public interface IAccountResponseParser {
        public Account parse(int statusCode, Header[] headers, JSONObject response);
    }

    public interface ICustomErrorParser {
        public CustomError parse(int statusCode, Header[] headers, JSONObject response);
    }

    public IBooleanResponseParser booleanResponseParser = new IBooleanResponseParser() {
        @Override
        public boolean parse(int statusCode, Header[] headers, JSONObject response) {
            if (statusCode == HttpStatus.SC_OK) {
                try {
                    JSONObject result = response.getJSONObject("result");
                    return result.getInt("success") == 1;
                } catch (JSONException e) {
                    return false;
                }
            } else {
                return false;
            }
        }
    };

    public IStringResponseParser stringResponseParser = new IStringResponseParser() {
        @Override
        public String parse(int statusCode, Header[] headers, JSONObject response) {
            if (statusCode == HttpStatus.SC_OK) {
                try {
                    return response.getString("result");
                } catch (JSONException ex) {
                    return null;
                }
            } else {
                return null;
            }
        }
    };

    public IAccountResponseParser accountResponseParser = new IAccountResponseParser() {
        @Override
        public Account parse(int statusCode, Header[] headers, JSONObject response) {
            if (statusCode == HttpStatus.SC_OK) {
                try {
                    return new Gson().fromJson(response.getString("result"), Account.class);
                } catch (JSONException ex) {
                    return null;
                }
            } else {
                return null;
            }
        }
    };

    public ICustomErrorParser customErrorParser = new ICustomErrorParser() {
        @Override
        public CustomError parse(int statusCode, Header[] headers, JSONObject response) {
            if (statusCode == HttpStatus.SC_OK) {
                try {
                    return new Gson().fromJson(response.getString("error"), CustomError.class);
                } catch (JSONException ex) {
                    return null;
                }
            }
            return null;
        }
    };
}
