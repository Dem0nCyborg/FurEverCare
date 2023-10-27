package com.chandan.furever_care.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.chandan.furever_care.R
import com.chandan.furever_care.databinding.ActivityReelsBinding

class Reels : AppCompatActivity() {

    private lateinit var binding: ActivityReelsBinding
    private val videos0 = ArrayList<videoModel>()
    private lateinit var  adapter1 : ReelsAdapter

    private val exoPlayerItems = ArrayList<ExoPlayerItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReelsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        videos0.add(videoModel("https://firebasestorage.googleapis.com/v0/b/furever-care-fd411.appspot.com/o/Baby%20dog%23cute%20puppy%20barking%234kviral%23shorts.mp4?alt=media&token=fa4a072b-d44a-47bc-9fb4-4b4eb18c525a"))
        videos0.add(videoModel("https://firebasestorage.googleapis.com/v0/b/furever-care-fd411.appspot.com/o/This%20dog%20surprises%20his%20owner%20again%20%F0%9F%A5%BA%20%23shorts.mp4?alt=media&token=e78d50eb-f587-48c1-a2bc-67582749b61b"))
        videos0.add(videoModel("https://firebasestorage.googleapis.com/v0/b/furever-care-fd411.appspot.com/o/Niki%20surprise%20me%F0%9F%A4%A3%F0%9F%A4%A3%F0%9F%A4%A3.mp4?alt=media&token=a82f1934-db98-40d0-8624-8d11d6cb026f"))
        videos0.add(videoModel("https://firebasestorage.googleapis.com/v0/b/furever-care-fd411.appspot.com/o/Dog%20sound__dog%20barking__%23shorts%20%23dogbarking%20%23viralshorts%20%23youtubeshorts%20%23dog%20sound.mp4?alt=media&token=8c4c826d-41a3-4787-8f1e-114c1bbe0c7a"))
        videos0.add(videoModel("https://firebasestorage.googleapis.com/v0/b/furever-care-fd411.appspot.com/o/Baby%20dog%23cute%20puppy%20barking%234kviral%23shorts.mp4?alt=media&token=fa4a072b-d44a-47bc-9fb4-4b4eb18c525a"))


        adapter1 = ReelsAdapter(this,videos0,object : ReelsAdapter.OnVideoPreparedListener{
            override fun onVideoPrepared(exoPlayerItem: ExoPlayerItem) {
                exoPlayerItems.add(exoPlayerItem)
            }
        })

        binding.vpager0.adapter = adapter1
        binding.vpager0.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val previousIndex = exoPlayerItems.indexOfFirst { it.exoPlayer.isPlaying }
                if (previousIndex != -1) {
                    val player = exoPlayerItems[previousIndex].exoPlayer
                    player.pause()
                    player.playWhenReady = false
                }
                val newIndex = exoPlayerItems.indexOfFirst { it.position == position }
                if (newIndex != -1) {
                    val player = exoPlayerItems[newIndex].exoPlayer
                    player.playWhenReady = true
                }
            }
        })


    }
}