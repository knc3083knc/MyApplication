package com.example.windows.myapplication.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.windows.myapplication.dao.FavMusic;
import com.example.windows.myapplication.view.FavMusicList;

import java.util.List;

public class FavMusicListAdapter extends BaseAdapter {

    List<String> favList;
    public void setFavList(List<String> favList)
    {
        this.favList = favList;
    }
    @Override
    public int getCount() {
        if(favList ==null)
        {
            return  0;
        }
        return favList.size();
    }

    @Override
    public Object getItem(int position) {
        return favList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FavMusicList item;
        if(convertView!=null)
        {
            item = (FavMusicList) convertView;
        }
        else
        {
            item = new FavMusicList(parent.getContext());
        }
        String favString =(String) getItem(position);
        String[] favPart = favString.split(" ");

        FavMusic favMusic= new FavMusic();
        favMusic.setId(Integer.parseInt(favPart[0]));
        favMusic.setMusicName(favPart[1]);
        favMusic.setArtist(favPart[2]);
        favMusic.setAlbum(favPart[3]);
        favMusic.setDescription(favPart[4]);
        item.setTvID(favMusic.getId()+"");
        item.setTvMusicName(favMusic.getMusicName());
        return item;
    }
}
