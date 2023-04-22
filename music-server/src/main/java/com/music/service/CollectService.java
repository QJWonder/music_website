package com.music.service;

import com.music.pojo.Collect;

import java.util.List;

public interface CollectService {

    boolean addCollection(Collect collect);

    boolean existSongId(Integer userId, Integer songId);

    boolean deleteCollect(Integer userId, Integer songId);

    List<Collect> collectionOfUser(Integer userId);
}
