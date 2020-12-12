package com.example.marvelcomics.repository.gateway;

import com.example.marvelcomics.repository.gateway.dto.CharactersResponse;
import com.example.marvelcomics.repository.gateway.dto.ComicsResponse;
import com.example.marvelcomics.repository.gateway.dto.CreatorsResponse;
import com.example.marvelcomics.repository.gateway.dto.Result;
import com.example.marvelcomics.repository.gateway.service.MarvelService;

public class MarvelGateway extends MarvelService {
    MarvelGateway() {
        super();
    }

    /**
     * This call blocks the Thread from which it is invoked and should NOT be called from the UI Thread
     *
     * @param creatorId the id of the creator to fetch
     * @return Result<CreatorsResponse> a holder object to represent the result of the Http Call
     */
    public Result<CreatorsResponse> getCreatorById(final Integer creatorId) {
        return super.request("/creators/" + creatorId, CreatorsResponse.class, 0);
    }

    /**
     * This call blocks the Thread from which it is invoked and should NOT be called from the UI Thread
     *
     * @return Result<CreatorsResponse> a holder object to represent the result of the Http Call
     */
    public Result<CreatorsResponse> getCreators(int offset) {
        return super.request("/creators", CreatorsResponse.class, offset);
    }

    /**
     * This call blocks the Thread from which it is invoked and should NOT be called from the UI Thread
     *
     * @param characterId the id of the creator to fetch
     * @return Result<CharactersResponse> a holder object to represent the result of the Http Call
     */
    public Result<CharactersResponse> getCharacterById(final Integer characterId) {
        return super.request("/characters/" + characterId, CharactersResponse.class, 0);
    }

    /**
     * This call blocks the Thread from which it is invoked and should NOT be called from the UI Thread
     *
     * @return Result<CharactersResponse> a holder object to represent the result of the Http Call.
     */
    public Result<CharactersResponse> getCharacters(int offset) {
        return super.request("/characters?offset=" + offset + "&orderBy=name&limit=" + 100, CharactersResponse.class, offset);
    }

    /**
     * This call blocks the Thread from which it is invoked and should NOT be called from the UI Thread
     *
     * @return Result<CharactersResponse> a holder object to represent the result of the Http Call.
     */
    public Result<CharactersResponse> getCharacters(String query) {
        return super.request("/characters?nameStartsWith=" + query + "&orderBy=name&limit=" + 100, CharactersResponse.class);
    }

    /**
     * This call blocks the Thread from which it is invoked and should NOT be called from the UI Thread
     *
     * @param comicId the id of the creator to fetch
     * @return Result<ComicsResponse> a holder object to represent the result of the Http Call
     */
    public Result<ComicsResponse> getComicById(Integer comicId) {
        return super.request("/comics/" + comicId, ComicsResponse.class, 0);
    }

    /**
     * This call blocks the Thread from which it is invoked and should NOT be called from the UI Thread
     *
     * @return Result<ComicsResponse> a holder object to represent the result of the Http Call
     */
    public Result<ComicsResponse> getComics(int offset) {
        return super.request("/comics?offset=" + offset + "&orderBy=title&limit=" + 100, ComicsResponse.class, offset);
    }

    /**
     * This call blocks the Thread from which it is invoked and should NOT be called from the UI Thread
     *
     * @return Result<ComicsResponse> a holder object to represent the result of the Http Call
     */
    public Result<ComicsResponse> getComics(String title) {
        return super.request("/comics?titleStartsWith=" + title + "&orderBy=title&limit=" + 100, ComicsResponse.class);
    }

    /**
     * This call blocks the Thread from which it is invoked and should NOT be called from the UI Thread
     *
     * @return Result<ComicsResponse> a holder object to represent the result of the Http Call
     */
    public Result<ComicsResponse> getComicsOnSale() {
        return super.request("/comics?orderBy=-onsaleDate&limit=100&limit=" + 10, ComicsResponse.class);
    }

    /**
     * This call blocks the Thread from which it is invoked and should NOT be called from the UI Thread
     *
     * @return Result<ComicsResponse> a holder object to represent the result of the Http Call
     */
    public <T> Result<T> getResource(String uri, Class<T> tClass) {
        return super.requestResource(uri, tClass);
    }

    /**
     * Async call, safe to call from the UI Thread,
     * the Http request will be executed in a worker Thread.
     *
     * @param creatorId the id of the creator to fetch
     * @param callback  a callback that will run on the UIThread
     */
    public void getCreatorById(final Integer creatorId, final Gateways.MarvelCallback<CreatorsResponse> callback) {
        super.request("/creators/" + creatorId +"?", callback, CreatorsResponse.class);
    }

    /**
     * Async call, safe to call from the UI Thread,
     * the Http request will be executed in a worker Thread.
     *
     * @param callback a callback that will run on the UIThread
     */
    public void getCreators(final Gateways.MarvelCallback<CreatorsResponse> callback) {
        super.request("/creators?", callback, CreatorsResponse.class);
    }

    /**
     * Async call, safe to call from the UI Thread,
     * the Http request will be executed in a worker Thread.
     *
     * @param characterId the id of the character we want ot fetch
     * @param callback    a callback that will run on the UIThread
     */
    public void getCharacterById(final Integer characterId, final Gateways.MarvelCallback<CharactersResponse> callback) {
        super.request("/characters/" + characterId +"?", callback, CharactersResponse.class);
    }

    /**
     * Async call, safe to call from the UI Thread,
     * the Http request will be executed in a worker Thread.
     *
     * @param callback a callback that will run on the UIThread
     */
    public void getCharacters(final Gateways.MarvelCallback<CharactersResponse> callback) {
        super.request("/characters?", callback, CharactersResponse.class);
    }

    /**
     * Async call, safe to call from the UI Thread,
     * the Http request will be executed in a worker Thread.
     *
     * @param comicId  the id of the comic we want ot fetch
     * @param callback a callback that will run on the UIThread
     */
    public void getComicById(Integer comicId, final Gateways.MarvelCallback<ComicsResponse> callback) {
        super.request("/comics/" + comicId +"?", callback, ComicsResponse.class);
    }

    /**
     * Async call, safe to call from the UI Thread,
     * the Http request will be executed in a worker Thread.
     *
     * @param callback a callback that will run on the UIThread
     */
    public void getComics(final Gateways.MarvelCallback<ComicsResponse> callback) {
        super.request("/comics?", callback, ComicsResponse.class);
    }
    /**
     * This call blocks the Thread from which it is invoked and should NOT be called from the UI Thread
     *
     * @return Result<ComicsResponse> a holder object to represent the result of the Http Call
     */
    public void getComicsOnSale(final Gateways.MarvelCallback<ComicsResponse> callback) {
        super.request("/comics?orderBy=-onsaleDate&limit=100&limit=" + 10, callback, ComicsResponse.class);
    }

}
