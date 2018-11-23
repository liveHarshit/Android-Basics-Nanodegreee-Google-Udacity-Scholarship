package com.itskshitizsh.musicalstructureapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itskshitizsh.musicalstructureapp.songClass.Song;

import java.util.ArrayList;

public class SongListAdaptor extends BaseAdapter {

    private Context mContext;
    private ArrayList<Song> mSongList;

    private View mView;

    public SongListAdaptor(Context context, ArrayList<Song> SongList) {
        mContext = context;
        mSongList = SongList;
    }

    @Override
    public int getCount() {
        return mSongList.size();
    }

    @Override
    public Object getItem(int i) {
        return mSongList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        mView = View.inflate(mContext, R.layout.item_song_list, null);

        TextView songName = mView.findViewById(R.id.song_name_text_view);
        TextView songDetails = mView.findViewById(R.id.song_details_text_view);
        TextView songTime = mView.findViewById(R.id.total_song_time_text_view);
        ImageView imageView = mView.findViewById(R.id.song_image);

        songName.setText(mSongList.get(i).getSongTitle());
        songDetails.setText(mSongList.get(i).getSongAlbum());
        songTime.setText(mSongList.get(i).getTotalPlayTime());
        imageView.setImageResource(R.drawable.music_update2);

        return mView;
    }
}
