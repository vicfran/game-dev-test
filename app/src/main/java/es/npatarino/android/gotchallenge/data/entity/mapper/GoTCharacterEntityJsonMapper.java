package es.npatarino.android.gotchallenge.data.entity.mapper;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.data.entity.GoTCharacterEntity;

/**
 * Transforms JSON data to {@link GoTCharacterEntity} objects
 */
public final class GoTCharacterEntityJsonMapper {

    private static final Gson sGson = new Gson();

    private GoTCharacterEntityJsonMapper() {}

    /**
     * Transforms a JSON into a {@link GoTCharacterEntity}
     * @param json JSON representing a {@link GoTCharacterEntity}
     * @return {@link GoTCharacterEntity} obtained from a JSON
     */
    public static GoTCharacterEntity transform(String json) {
        GoTCharacterEntity entity = new GoTCharacterEntity();

        if (TextUtils.isEmpty(json))
            return entity;

        if (TextUtils.isEmpty(json))
            return entity;

        Type entityType = new TypeToken<GoTCharacterEntity>() {}.getType();

        try {
            entity = sGson.fromJson(json, entityType);
        } catch (JsonSyntaxException e) {
            return new GoTCharacterEntity();
        }

        return entity;
    }

    /**
     * Transforms a JSON into a {@link List} of {@link GoTCharacterEntity}
     * @param json JSON representing a {@link List} of {@link GoTCharacterEntity}
     * @return {@link List} of {@link GoTCharacterEntity} obtained from a JSON
     */
    public static List<GoTCharacterEntity> transformList(String json) {
        List<GoTCharacterEntity> entities = new ArrayList<>();

        if (TextUtils.isEmpty(json))
            return entities;

        Type entityListType = new TypeToken<List<GoTCharacterEntity>>() {}.getType();

        try {
            entities = sGson.fromJson(json, entityListType);
        } catch (JsonSyntaxException e) {
            return new ArrayList<>();
        }

        return entities;
    }
}
