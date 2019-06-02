package com.example.tinder.Connection;

import android.util.Log;

import com.example.tinder.Interfaces.DataBack;
import com.example.tinder.Interfaces.DataCallback;
import com.example.tinder.Interfaces.IIsAuthenticated;
import com.example.tinder.Interfaces.InviteRequestCallBack;
import com.example.tinder.Interfaces.LoginCallBack;
import com.example.tinder.Interfaces.GetMessageCallback;
import com.example.tinder.Interfaces.PostMessageCallback;
import com.example.tinder.Interfaces.RegisterCallBack;
import com.example.tinder.Interfaces.RelationShipCallBack;
import com.example.tinder.Model.CardOfPeople;
import com.example.tinder.Model.Invite;
import com.example.tinder.Model.Login;
import com.example.tinder.Model.Message;
import com.example.tinder.Model.Register;
import com.example.tinder.Model.UserToken;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class TinderManager {
    private static final String TAG = "TinderManager";
    private final String BASE_URL = "http://android3.byted.xyz/";

    private final TinderService service;
    private UserToken userToken;

    private CardOfPeople[] cardOfPeople;
    private CardOfPeople aaaaa;

    private TinderManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(TinderService.class);
    }

    public static TinderManager getInstance() {
        return Holder.instance;
    }

    private String getProblem(int responseCode) {
        switch (responseCode) {
            case 400: // Bad request
                return "Bad request";
            case 401: // Unauthorized
                return "Acces not authorized";
            default: // In case we don't know what the error is...
                return "Unknown error";
        }
    }

    public void searchUsers(DataBack mainActivity, Map<String, String> parameters) {
        if (parameters == null) {
            parameters = new HashMap<>();
        }
        Call<CardOfPeople[]> call = service.getProfiles("Bearer " + userToken.getToken(), parameters);
        call.enqueue(new Callback<CardOfPeople[]>() {
            @Override
            public void onResponse(Call<CardOfPeople[]> call, Response<CardOfPeople[]> response) {
                if (response.isSuccessful()) {
                    cardOfPeople = response.body();
                    mainActivity.onLogin2Success(null);
                } else {
                    Log.d(TAG, "onResponse error: " + response.raw());

                    mainActivity.onLogin2Failed(getProblem(response.code()));
                }
            }

            @Override
            public void onFailure(Call<CardOfPeople[]> call, Throwable t) {
                mainActivity.onLogin2Failed(t.getMessage());
            }
        });
    }

    public void authenticate(IIsAuthenticated callback, String token) {
        Call<String> call = service.authenticate("Bearer " + token);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    if (response.body() == null || response.body().isEmpty()) {
                        callback.failure();
                    } else {
                        callback.success();
                        setUserToken(token);
                    }
                } else {
                    callback.failure();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.failure();
            }
        });
    }

    public void login(LoginCallBack loginCallBack, Login login) {
        Call<UserToken> call = service.login(login);
        call.enqueue(new Callback<UserToken>() {
            @Override
            public void onResponse(Call<UserToken> call, Response<UserToken> response) {
                if (response.isSuccessful()) {
                    userToken = response.body();
                    loginCallBack.onLoginSuccess(userToken.getToken());
                } else {
                    Log.d(TAG, "onResponse error: " + response.raw());

                    loginCallBack.onLoginFailed(getProblem(response.code()));
                }
            }

            @Override
            public void onFailure(Call<UserToken> call, Throwable t) {
                loginCallBack.onLoginFailed(t.getMessage());
            }
        });
    }

    public void register(RegisterCallBack registerCallback, Register register) {
        Call<Void> call = service.register(register);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {
                    registerCallback.onRegisterSuccess();
                } else {
                    Log.d(TAG, "onResponse error: " + response.raw());

                    registerCallback.onRegisterFailed(getProblem(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                registerCallback.onRegisterFailed(t.getMessage());
            }
        });
    }

    public void profilePut(DataCallback dataCallback, CardOfPeople profile) {
        Call<Void> call = service.profilePut(userToken.getToken(), profile);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    dataCallback.onProfilePutSuccess();
                } else {
                    Log.d(TAG, "onResponse error: " + response.raw());

                    dataCallback.onProfilePutFailed(getProblem(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                dataCallback.onProfilePutFailed(t.getMessage());
            }
        });
    }

    public void ownProfileGet(DataCallback dataCallback) {
        Call<CardOfPeople> call = service.myProfileGet(userToken.getToken());

        call.enqueue(new Callback<CardOfPeople>() {
            @Override
            public void onResponse(Call<CardOfPeople> call, Response<CardOfPeople> response) {
                if (response.isSuccessful()) {
                    dataCallback.onOwnProfileGetSuccess(response.body());
                } else {
                    Log.d(TAG, "onResponse error: " + response.raw());

                    dataCallback.onOwnProfileGetFailed(getProblem(response.code()));
                }
            }

            @Override
            public void onFailure(Call<CardOfPeople> call, Throwable t) {
                dataCallback.onOwnProfileGetFailed(t.getMessage());
            }
        });
    }

    public void profileGet(DataCallback dataCallback, String userId) {
        Call<CardOfPeople> call = service.profileGet(userToken.getToken(), userId);

        call.enqueue(new Callback<CardOfPeople>() {
            @Override
            public void onResponse(Call<CardOfPeople> call, Response<CardOfPeople> response) {
                if (response.isSuccessful()) {
                    dataCallback.onProfileGetSuccess(response.body());
                } else {
                    Log.d(TAG, "onResponse error: " + response.raw());

                    dataCallback.onProfileGetFailed(getProblem(response.code()));
                }
            }

            @Override
            public void onFailure(Call<CardOfPeople> call, Throwable t) {
                dataCallback.onProfileGetFailed(t.getMessage());
            }
        });
    }

    public void profileInvite(InviteRequestCallBack dataCallback, long userId) {
        Call<Void> call = service.profileInvite("Bearer " + userToken.getToken(), userId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    dataCallback.onProfileInviteSuccess();
                } else {
                    Log.d(TAG, "onResponse error: " + response.raw());

                    dataCallback.onProfileInviteFailed(getProblem(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                dataCallback.onProfileInviteFailed(t.getMessage());
            }
        });
    }

    public void pendingInvites(DataCallback dataCallback) {
        Call<Invite[]> call = service.prendingInvites(userToken.getToken());

        call.enqueue(new Callback<Invite[]>() {
            @Override
            public void onResponse(Call<Invite[]> call, Response<Invite[]> response) {
                if (response.isSuccessful()) {
                    dataCallback.onPendingInvitesSuccess(response.body());
                } else {
                    Log.d(TAG, "onResponse error: " + response.raw());

                    dataCallback.onPendingInvitesFailed(getProblem(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Invite[]> call, Throwable t) {
                dataCallback.onPendingInvitesFailed(t.getMessage());
            }
        });
    }

    public void inviteAnswer(DataCallback dataCallback, long id, boolean state) {
        Call<Void> call = service.inviteAnswer(userToken.getToken(), id, state);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    dataCallback.onInviteAnswerSuccess();
                } else {
                    Log.d(TAG, "onResponse error: " + response.raw());

                    dataCallback.onInviteAnswerFailure(getProblem(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                dataCallback.onInviteAnswerFailure(t.getMessage());
            }
        });
    }

    public void acceptedInvites(DataCallback dataCallback) {
        Call<Invite[]> call = service.acceptedInvites(userToken.getToken());

        call.enqueue(new Callback<Invite[]>() {
            @Override
            public void onResponse(Call<Invite[]> call, Response<Invite[]> response) {
                if (response.isSuccessful()) {
                    dataCallback.onAcceptedInvitesSuccess(response.body());
                } else {
                    Log.d(TAG, "onResponse error: " + response.raw());

                    dataCallback.onAcceptedInvitesFailed(getProblem(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Invite[]> call, Throwable t) {
                dataCallback.onAcceptedInvitesFailed(t.getMessage());
            }
        });
    }

    public UserToken getUserToken() {
        return userToken;
    }

    private void setUserToken(String token) {
        if (this.userToken == null) {
            this.userToken = new UserToken(token);
        } else {
            this.userToken.setToken(token);
        }
    }

    public void setUserToken(UserToken userToken) {
        this.userToken = userToken;
    }

    public CardOfPeople[] getCardOfPeople() {
        return cardOfPeople;
    }

    public void getRelationship(RelationShipCallBack dataCallback, long id) {
        Call<CardOfPeople> call = service.getRelationship(userToken.getToken());
        call.enqueue(new Callback<CardOfPeople>() {
            @Override
            public void onResponse(Call<CardOfPeople> call, Response<CardOfPeople> response) {
                if (response.isSuccessful()) {
                    dataCallback.onRelationShipSuccess(response.body());
                } else {
                    Log.d(TAG, "onResponse error: " + response.raw());

                    dataCallback.onRelationShipFailed(getProblem(response.code()));
                }
            }

            @Override
            public void onFailure(Call<CardOfPeople> call, Throwable t) {
                dataCallback.onRelationShipFailed(t.getMessage());
            }
        });
    }

    public CardOfPeople getAaaaa() {

        return aaaaa;
    }

    public void setAaaaa(CardOfPeople aaaaa) {
        this.aaaaa = aaaaa;
    }

    public void getMessages(GetMessageCallback dataCallback, Message m){
        Call<Message[]> call = service.getMessages(userToken.getToken(), m.getRecipient().getId(), m.getSender().getId());

        call.enqueue(new Callback<Message[]>() {
            @Override
            public void onResponse(Call<Message[]> call, Response<Message[]> response) {
                if (response.isSuccessful()) {
                    dataCallback.onGetMessagesSuccess();
                } else {
                    Log.d(TAG, "onResponse error: " + response.raw());

                    dataCallback.onGetMessagesFailed(getProblem(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Message[]> call, Throwable t) {
                dataCallback.onGetMessagesFailed(t.getMessage());
            }
        });
    }

    public void postMessages(PostMessageCallback dataCallback, Message m){
        Call<Void> call = service.postMessage(userToken.getToken(), m);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    dataCallback.onPostMessageSuccess();
                } else {
                    Log.d(TAG, "onResponse error: " + response.raw());

                    dataCallback.onPostMessageFailed(getProblem(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                dataCallback.onPostMessageFailed(t.getMessage());
            }
        });
    }

    private static class Holder {
        private static final TinderManager instance = new TinderManager();
    }
}
